package danh.controller;

import danh.product.ProductDAO;
import danh.product.ProductDTO;
import danh.util.ApplicationConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Search_Product_By_Name", urlPatterns = {"/Search_Product_By_Name"})
public class Search_Product_By_Name extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. Lấy context scope
        ServletContext context = this.getServletContext();
        //2. Lấy siteMaps 
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. Tra cứu value
        String url = siteMaps.getProperty(ApplicationConstants.SearchShopping_Features.SEARCH_PAGE);
        String searchVal = request.getParameter("txtSearchValueShopping");
        
        try {
            //1. check valid search value
            if(!searchVal.trim().isEmpty()) {
                //2. call model (new DAO)
                ProductDAO dao = new ProductDAO();
                //3. call method
                dao.searchProductByName(searchVal);
                //4. process kq
                List<ProductDTO> result = dao.getProList();
                url = siteMaps.getProperty(ApplicationConstants.SearchShopping_Features.RESULT_PAGE);;
                request.setAttribute("SHOPPING_SEARCH_RESULT", result);
            }
        } catch (NamingException ex) {
            log("SignUp _ Naming | " + ex.getMessage());
        } catch (SQLException ex) {
            log("SignUp _ SQL | " + ex.getMessage());
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
