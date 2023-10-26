package danh.controller;

import danh.registration.RegistrationDAO;
import danh.util.ApplicationConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class Delete extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. Lấy context scope
        ServletContext context = this.getServletContext();
        //2. Lấy siteMaps 
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. Tra cứu value
        String url = siteMaps.getProperty(ApplicationConstants.DeleteUser_Features.ERROR_PAGE);
        
        String username = request.getParameter("pk");
        String searchVal = request.getParameter("searchHistory");
        try {
            //1. call Model
            //1.1 new DAO
            RegistrationDAO dao = new RegistrationDAO();
            //1.2 call method
            boolean result = dao.deleteAccount(username);
            //2. process result
            if(result) {
            //2.1 call the search function again using url-rewriting if delete successfully
                url = "Middle_Servlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchVal;
            }
            //2.2 if cannot delete move to invalid
        } catch (SQLException ex) {
            log("SignUp _ SQL | " + ex.getMessage());
        } catch (NamingException ex) {
            log("SignUp _ Naming | " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            // sd sendRedirect bởi vì nếu sd forward thì nó sẽ bị trùng parameter btAction ở bên search.jsp cho nên nó sẽ tạo mảng chứa tụi nó
            // và ko sắp xếp nếu get thì get thằng đầu nên sẽ ko bt khi nào seach khi nào delete
            // và search delete là 2 chức năng độc lập nên sd sendRedirect để tách ra
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
