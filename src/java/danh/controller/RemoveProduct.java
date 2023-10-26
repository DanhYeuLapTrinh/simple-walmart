package danh.controller;

import danh.cart.Cart;
import danh.util.ApplicationConstants;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RemoveProduct", urlPatterns = {"/RemoveProduct"})
public class RemoveProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. Lấy context scope
        ServletContext context = this.getServletContext();
        //2. Lấy siteMaps 
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. Tra cứu value
        String url = siteMaps.getProperty(ApplicationConstants.RemoveProduct_Features.ERROR_PAGE);

        String[] pair = request.getParameterValues("checkItem");
        int newQuantity = Integer.parseInt(request.getParameter("quantityBoxRemove"));
        try {
            if (pair != null) {
                //1. Đến nơi để giỏ
                HttpSession session = request.getSession(false);
                if (session != null) {
                    //2. Lấy cái giỏ
                    Cart cart = (Cart) session.getAttribute("CART");
                    if (cart != null) {
                        for (String item : pair) {
                            String[] parts = item.split("\\:");
                            if (parts.length == 2) {
                                String sku = parts[0];
                                int oldQuantity = Integer.parseInt(parts[1]);
                                if(oldQuantity - newQuantity > 0) {
                                    cart.removeItemWithQuantity(sku, newQuantity);
                                } else if(oldQuantity - newQuantity <= 0) {
                                    cart.removeItem(sku);
                                }
                            }
                        }
                    }
                    session.setAttribute("CART", cart);
                }
            }
            //url rewriting
            url = "Middle_Servlet"
                    + "?btAction=viewCart";
//            url = siteMaps.getProperty(ApplicationConstants.RemoveProduct_Features.VIEW_CART);
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
