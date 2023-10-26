package danh.controller;

import danh.registration.RegistrationDAO;
import danh.registration.RegistrationDTO;
import danh.util.ApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //1. Lấy context scope
        ServletContext context = this.getServletContext();
        //2. Lấy siteMaps 
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. Tra cứu value
        String url = siteMaps.getProperty(ApplicationConstants.LogIn_Features.INVALID_PAGE);
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            //10 (trong sơ đồ). call model - DAO
            //new obj
            RegistrationDAO dao = new RegistrationDAO();
            //call method
            RegistrationDTO result = dao.checkLogin(username, password);
            //process result
            if (result != null) {
                url = siteMaps.getProperty(ApplicationConstants.LogIn_Features.RESULT_PAGE);
                HttpSession session = request.getSession();
                session.setAttribute("USER", result); // lưu thông tin user nên sd get session true
//                    Cookie cookie = new Cookie(username, password); // GHI COOKIE (ĐỌC Ở STARTUP SERVLET)
//                    cookie.setMaxAge(60 * 1);
//                    response.addCookie(cookie);
            }
        } catch (SQLException ex) {
            log("SignUp _ SQL | " + ex.getMessage());
        } catch (NamingException ex) {
            log("SignUp _ Naming | " + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
