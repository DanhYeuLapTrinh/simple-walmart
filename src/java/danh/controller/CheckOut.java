package danh.controller;

import danh.cart.Cart;
import danh.order.OrderDAO;
import danh.order.OrderDTO;
import danh.orderDetail.OrderDetailDAO;
import danh.orderDetail.OrderDetailDTO;
import danh.product.ProductDTO;
import danh.util.ApplicationConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CheckOut", urlPatterns = {"/CheckOut"})
public class CheckOut extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. Lấy context scope
        ServletContext context = this.getServletContext();
        //2. Lấy siteMaps 
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. dùng nó để tra cứu value
        String url = siteMaps.getProperty(ApplicationConstants.CheckOut_Features.ERROR_PAGE);
        boolean result = false;
        float total = 0;
        try {
            //1. Customer đến nơi lấy giỏ
            HttpSession session = request.getSession();
            if (session != null) {
                //2. Lấy giỏ
                Cart cart = (Cart) session.getAttribute("CART");
                //3. Lấy ngăn chứa đồ
                Map<String, Integer> item = cart.getItem();
                if (item != null) {
                    OrderDAO dao = new OrderDAO();
                    OrderDetailDAO daoD = new OrderDetailDAO();
                    //4. Duyệt từng món lấy total nhét dô Order
                    for (String key : item.keySet()) {
                        ProductDTO pro = dao.find(key);
                        total += item.get(key) * pro.getPrice();
                        dao.updateQuantity(pro.getSku(), pro.getQuantity(), item.get(key));
                    }
                    OrderDTO order = new OrderDTO(total);
                    int orderID = dao.createOrder(order);
                    //5. Duyệt tiếp nhét dô OrderDetail
                    for (String key : item.keySet()) {
                        ProductDTO pro = dao.find(key);
                        OrderDetailDTO orderD = new OrderDetailDTO(pro.getSku(), orderID, pro.getPrice(), item.get(key), key);
                        result = daoD.createOrderDetail(orderD);
                        
                    }
                    //6. In ra danh sách order và lưu vào trong attribute
                    daoD.printOrder(orderID);
                    List<OrderDetailDTO> od = daoD.getOdList();
                    url = siteMaps.getProperty(ApplicationConstants.CheckOut_Features.SHOW_PAGE);
                    request.setAttribute("ORDER_PRINTER", od);
                    request.setAttribute("PRICE", total);
                    request.setAttribute("ORDER_ID", orderID);
                    //7. Hủy giỏ
                    session.removeAttribute("CART");
                }
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
