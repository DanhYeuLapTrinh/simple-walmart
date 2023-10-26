package danh.controller;

import danh.util.ApplicationConstants;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Middle_Servlet", urlPatterns = {"/Middle_Servlet"}) // ko nên đặt tên trùng với URL pattern (này làm z cho dễ)
public class Middle_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // User đã nhấn nút nào?
        String button = request.getParameter("btAction");
        //1. Lấy context scope
        ServletContext context = this.getServletContext();
        //2. Lấy siteMaps 
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. dùng nó để tra cứu value của thằng LOGIN vì nó đang là chuỗi rỗng sẽ lỗi
        String url = siteMaps.getProperty(ApplicationConstants.Middle_Features.LOGIN);
        try {
            if (button == null) {
                // check cookie
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.START_UP);
            } else if (button.equals("Login")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.CONTROLLER);
            } else if (button.equals("Search")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.SEARCH_USER);
            } else if (button.equals("delete")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.DELETE_USER);
            } else if (button.equals("Update")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.UPDATE_USER);
            } else if (button.equals("addToCart")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.ADD_TO_CART);
            } else if (button.equals("viewCart")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.VIEW_CART);
            } else if (button.equals("Find")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.SEARCH_SHOPPING);
            } else if (button.equals("showAllProduct")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.PRINT_ALL_SHOPPING);
            } else if (button.equals("Sign Up")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.SIGN_UP);
            } else if (button.equals("Remove")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.REMOVE_PRODUCT);
            } else if (button.equals("Log Out")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.LOG_OUT);
            } else if (button.equals("Check Out")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.CHECK_OUT);
            } else if (button.equals("Back To Menu")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.PRINT_ALL_SHOPPING);
            } else if (button.equals("signup")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.SIGN_UP);
            } else if (button.equals("Go Back")) {
                url = siteMaps.getProperty(ApplicationConstants.Middle_Features.PRINT_ALL_SHOPPING);
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
//***welcome-file trong web.xml phải là URL-pattern của servlet điều phối
//1. tạo điều phối, trung gian
//2. tạo view để add chức năng ra màn hình (login.html) action trong form phải là thằng điều phối ở b1 (lấy URL-pattern của nó)
//3. map tính năng vào trong điều phối đó (copy value của button và paste vào trong) dòng 27
//  dòng 27 là tạo URL trên dòng 15 trc
//  sau đó lấy URL đã tạo đặt thành tên của servlet chức năng ở b4
//4. tạo servlet chức năng
//5. tạo model (new obj, call method)
//6. nếu model có dữ liệu thì tạo DTO
