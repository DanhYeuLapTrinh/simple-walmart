<%-- 
    Document   : viewCart
    Created on : Jun 19, 2023, 1:36:45 PM
    Author     : admin
--%>

<%@page import="java.util.Map"%>
<%@page import="danh.cart.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/viewCartStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <title>Walmart</title>
    </head>
    <body>
        <div class="container">
            <div class="box">
                <div class="cart-section">
                    <div class="table-section">
                        <p>Your Cart</p>
                        <c:if test="${not empty sessionScope}">
                            <c:set var="cart" value="${sessionScope.CART}"/>
                            <c:if test="${not empty cart}">
                                <c:set var="item" value="${cart.item}"/>
                                <c:if test="${not empty item}">
                                    <form action="Middle_Servlet" method="POST">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th class="small-box">No</th>
                                                    <th class="normal-box">Name</th>
                                                    <th class="medium-box">Quantity</th>
                                                    <th class="medium-box-special">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${item.keySet()}" var="key" varStatus="counter">
                                                    <tr>
                                                        <td class="small-box">
                                                            ${counter.count}
                                                        </td>
                                                        <td class="normal-box">
                                                            ${key}
                                                        </td>
                                                        <td class="medium-box">
                                                            ${item.get(key)}
                                                        </td>
                                                        <td class="medium-box-special">
                                                            <input type="checkbox" name="checkItem" value="${key}:${item.get(key)}"/>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                    </c:if>
                                </c:if>
                            </c:if>
                    </div>
                </div>
                <div class="payment-section">
                    <div class="info">
                        <div class="img-box">
                            <img src="./CSS/image/Walmart_logo.svg.png" alt="">
                        </div>
                        <p></p>
                    </div>
                    <div class="function">
                        <input class="quan-box" type="number" name="quantityBoxRemove" placeholder="Enter quantity"/>
                        <div class="middle">
                            <button class="bk-btn" type="submit" value="Go Back" name="btAction">Go Back</button>
                            <button class="rm-btn" type="submit" value="Remove" name="btAction">Remove</button>
                        </div>
                        <button class="ck-btn" type="submit" value="Check Out" name="btAction">Check Out</button>
                    </div>
                </div>
                </form>
            </div>
        </div>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <%--        <%
                    //1. Khách hàng lấy giỏ của mình
                    if (session != null) {
                        //2. Lấy giỏ
                        Cart cart = (Cart) session.getAttribute("CART");
                        if (cart != null) {
                            //3. Lấy ngăn chứa đồ
                            Map<String, Integer> item = cart.getItem();
                            if (item != null) {
                                //4. Lấy các món đồ chứa trong ngăn
                %>
                <form action="Middle_Servlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for (String key : item.keySet()) {
                            %>
                            <tr>
                                <td>
                                    <%= ++count%>
                                </td>
                                <td>
                                    <%= key%>
                                </td>
                                <td>
                                    <%= item.get(key)%>
                                </td>
                                <td>
                                    <input type="checkbox" name="checkItem" value="<%= key%>" />
                                </td>
                            </tr> 
                            <%
                                }
                            %>
                            <tr>
                                <td colspan="3">
                                    <a href="Middle_Servlet?btAction=showAllProduct">Add new item to cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove" name="btAction"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <%
                                return;
                            }
                        }
                    }
                %>
                <h2>
                    Empty!!!
                </h2>
        --%>
    </body>
</html>
