package danh.controller;

import danh.cart.Cart;
import danh.util.ApplicationConstants;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. Lấy context scope
        ServletContext context = this.getServletContext();
        //2. Lấy siteMaps 
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. Tra cứu value
        String url = ApplicationConstants.AddToCart_Features.PRINT_ALL_SHOPPING;

        String lastSearch = request.getParameter("searchHistory");
        String[] name = request.getParameterValues("isCheck");
        int quantity = Integer.parseInt(request.getParameter("quantityBox"));
        try {
            //1. Tìm chỗ để giỏ
            HttpSession session = request.getSession();
            //2. Lấy giỏ
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            //3. Bỏ hàng vào giỏ
//            for (String item : pair) {
//                String[] parts = item.split("\\:");
//                if (parts.length == 2) {
//                    String sku = parts[0];
//                    int quantity = Integer.parseInt(parts[1]);
//                    for (String n : name) {
//                        if(n.equals(sku))
//                        cart.addItemWithQuantity(sku, quantity);
//                    }
//                    session.setAttribute("CART", cart);
//                }
//            }     
            if (name != null && quantity > 0) {
                for (String item : name) {
                    cart.addItemWithQuantity(item, quantity);
                }
                session.setAttribute("CART", cart);
            }
            //4. Tiếp tục mua (về nhà sd URL rewriting để quay lại trang shopping dộng)
            if (lastSearch != null) {
                url = "Middle_Servlet"
                        + "?btAction=Find"
                        + "&txtSearchValueShopping=" + lastSearch;
            }
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
