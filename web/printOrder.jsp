<%-- 
    Document   : printOrder
    Created on : Jul 10, 2023, 12:01:02 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/printOrderStyle.css">
        <title>Walmart</title>
    </head>
    <body>
        <div class="container">
            <div class="box">
                <div class="result-section">
                    <div class="announce">
                        <ion-icon class="icon" name="checkmark-circle-sharp"></ion-icon>
                        <p>Thanks for your ordering, MS#${requestScope.ORDER_ID}!</p>
                    </div>
                    <div class="table-section">
                        <c:set var="result" value="${requestScope.ORDER_PRINTER}"/>
                        <c:if test="${not empty result}">
                            <table>
                                <thead>
                                    <tr>
                                        <th class="small-box">No</th>
                                        <th class="normal-box">Name</th>
                                        <th class="medium-box">Price</th>
                                        <th class="medium-box-special">Quantity</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${result}" var="item" varStatus="counter">
                                        <tr>
                                            <td class="small-box">${counter.count}</td>
                                            <td class="normal-box">${item.pName}</td>
                                            <td class="medium-box">${item.price}</td>
                                            <td class="medium-box-special">${item.quantity}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td class="total" colspan="3">Total</td>
                                        <td class="total2">${requestScope.PRICE}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                    <form action="Middle_Servlet" method="POST">
                        <div class="button-section">        
                            <button class="bk-btn" type="submit" name="btAction" value="Back To Menu">Back To Menu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    </body>
</html>
