package danh.controller;

import danh.registration.RegistrationCreateError;
import danh.registration.RegistrationDAO;
import danh.registration.RegistrationDTO;
import danh.util.ApplicationConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. Lấy context scope
        ServletContext context = this.getServletContext();
        //2. Lấy siteMaps 
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. Tra cứu value
        String url = siteMaps.getProperty(ApplicationConstants.SignUp_Features.ERROR_PAGE);
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        boolean isFound = false;
        RegistrationCreateError error = new RegistrationCreateError();
        try {
            //1. Check lỗi User
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                isFound = true;
                error.setUsernameLength("Must be from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                isFound = true;
                error.setPasswordLength("Must be from 6 to 30 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                isFound = true;
                error.setConfirmMatch("Not matched");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                isFound = true;
                error.setFullNameLength("Must be from 2 to 50 characters");
            }
            if (isFound) {
//                HttpSession session = request.getSession();
//                session.setAttribute("ERROR", error);
                request.setAttribute("ERROR", error);
            } else {
                //2. Call DAO
                //3. Process
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO account = new RegistrationDTO(username, password, fullName, isFound);
                boolean result = dao.addAccount(account);
                if (result) {
                    url = siteMaps.getProperty(ApplicationConstants.SignUp_Features.LOGIN_PAGE);
                }
            }
        } catch (NamingException ex) {
            log("SignUp _ Naming | " + ex.getMessage());
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("SignUp _ SQL | " + msg);
            if(msg.contains("duplicate")) {
                error.setExistedUsername(username + " HAS BEEN USED");
                request.setAttribute("ERROR", error); // update lỗi là phải update scope
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
