<%-- 
    Document   : displayShop
    Created on : Jun 27, 2023, 8:40:20 PM
    Author     : admin
--%>

<%--<%@page import="java.util.List"%>
<%@page import="danh.product.ProductDTO"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/displayShopStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <title>Walmart</title>
    </head>
    <body>
        <div class="container">
            <div class="box">
                <form action="Middle_Servlet" method="POST">
                    <div class="search-section">
                        <div class="img-box">
                            <img src="CSS/image/Walmart_logo.svg.png" alt="">
                        </div>
                        <div class="txtField">
                            <input type="text" name="txtSearchValueShopping" value="" placeholder="What do you want?"/>
                            <span class="icon">
                                <ion-icon name="search-sharp"></ion-icon>
                            </span>
                            <button class="search-btn" type="submit" value="Find" name="btAction">Search</button>
                        </div>
                        <button class="view-btn" type="submit" value="viewCart" name="btAction">
                            <ion-icon name="cart-sharp" class="cart-icon"></ion-icon>
                        </button>
                    </div>
                </form>
                <div class="result-section">
                    <div class="table-section">
                        <c:set var="result" value="${requestScope.SHOPPING_ALL}"/>
                        <c:if test="${not empty result}">
                            <form action="Middle_Servlet">
                                <table>
                                    <thead>
                                        <tr>
                                            <th class="small-box"></th>
                                            <th class="small-box">No</th>
                                            <th class="normal-box">Name</th>
                                            <th class="medium-box-special">Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${result}" var="dto" varStatus="counter">
                                            <tr>
                                                <td class="small-box">
                                                    <input type="checkbox" name="isCheck" value="${dto.proName}" />
                                                </td>
                                                <td class="small-box">
                                                    ${counter.count}
                                                </td>
                                                <td class="normal-box">
                                                    ${dto.proName}
                                                </td>
                                                <td class="medium-box-special">
                                                    ${dto.price}
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                    </div>
                    <div class="button-section">        
                        <input type="number" name="quantityBox" placeholder="Enter quantity">
                        <button class="add-btn" type="submit" name="btAction" value="addToCart">Add to Cart</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

        <%--        <%
                    List<ProductDTO> result = (List<ProductDTO>) request.getAttribute("SHOPPING_RESULT");
                    if (result != null) { // nếu list ko trống thì in ra
                %>
                <form action="Middle_Servlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th></th>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for (ProductDTO dto : result) {
                            %>
                            <tr>
                                <td>
                                    <input type="checkbox" name="isCheck" value="<%= dto.getProName()%>"/>
                                </td>
                                <td>
                                    <%= ++count%>
                                </td>
                                <td>
                                    <%= dto.getProName()%>
                                </td>
                                <td>
                                    <%= dto.getQuantity()%>
                                </td>
                                <td>
                                    <%= dto.getPrice()%>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                    <input type="submit" name="btAction" value="addToCart">
                </form> --%>
    </body>
</html>
