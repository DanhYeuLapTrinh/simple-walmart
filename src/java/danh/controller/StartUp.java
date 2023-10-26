package danh.controller;

import danh.registration.RegistrationDAO;
import danh.registration.RegistrationDTO;
import danh.util.ApplicationConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StartUp", urlPatterns = {"/StartUp"})
public class StartUp extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ĐỌC COOKIE (GHI Ở LOGIN_SERVLET DÒNG 36)
        response.setContentType("text/html;charset=UTF-8");
        
        //1. Lấy context scope
        ServletContext context = this.getServletContext();
        //2. Lấy siteMaps 
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. Tra cứu value
        String url = siteMaps.getProperty(ApplicationConstants.StartUp_Features.LOGIN_PAGE);
        
        try {
            //1. GET danh sách cookie
            Cookie[] cookies = request.getCookies();
            //2. Check cookie
            if (cookies != null) {
                //3. Get latest cookie
                Cookie latestCookie = cookies[cookies.length - 1];
                //4. Get username and password from cookie
                String username = latestCookie.getName();
                String password = latestCookie.getValue();
                //5. Call DAO with checklogin method after having username and password
                RegistrationDAO dao = new RegistrationDAO();
//                boolean result = dao.checkLogin(username, password);
                RegistrationDTO result = dao.checkLogin(username, password);
                if (result != null) {
                    url = siteMaps.getProperty(ApplicationConstants.StartUp_Features.SEARCH_PAGE);;
                }
            }
        } catch (SQLException ex) {
            log("SignUp _ SQL | " + ex.getMessage());
        } catch (NamingException ex) {
            log("SignUp _ Naming | " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
