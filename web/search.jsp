<%-- 
Document   : search
Created on : Jun 5, 2023, 5:11:29 PM
Author     : admin
--%>

<%--<%@page import="danh.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!--tglb-->
<!--    MUỐN SỬ DỤNG ĐC JSTL
        add dc library
        import dc tag lib vao trang jsp-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="./CSS/searchStyle.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <div class="box">
                <form action="Middle_Servlet" method="POST">
                    <button class="logout-btn" type="submit" value="Log Out" name="btAction">Log Out</button>
                    <div class="search-section">
                        <h2>Welcome, ${sessionScope.USER.fullName}</h2>
                        <div class="txtField">
                            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" placeholder="Search for users"/>
                            <span class="icon">
                                <ion-icon name="search-sharp"></ion-icon>
                            </span>
                            <button class="search-btn" type="submit" value="Search" name="btAction">Search</button>

                        </div>
                </form>
            </div>
            <div class="result-section">
                <div class="table-section">
                    <c:set var="searchValue" value="${param.txtSearchValue}"/>
                    <c:if test="${not empty searchValue}"> 
                        <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
                        <c:if test= "${not empty result}">
                            <table>
                                <thead>
                                    <tr>
                                        <th class="small-box">No</th>
                                        <th class="normal-box">Username</th>
                                        <th class="medium-box">Password</th>
                                        <th class="normal-box">FullName</th>
                                        <th class="small-box">Role</th>
                                        <th class="small-box">Delete</th>
                                        <th class="small-box">Update</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${result}" var="dto" varStatus="counter">
                                    <form action="Middle_Servlet" method="POST">
                                        <tr>
                                            <td class="small-box">
                                                ${counter.count}
                                            </td>
                                            <td class="normal-box">
                                                ${dto.username}
                                                <input type="hidden" value="${dto.username}" name="txtUsername" />
                                            </td>
                                            <td>
                                                <input type="hidden" value="${dto.password}" name="txtPassword" />
                                            </td>
                                            <td class="normal-box">
                                                ${dto.fullName}
                                            </td>
                                            <td class="small-box">
                                                <input type="checkbox" value="ON" name="isAdmin" 
                                                       <c:if test="${dto.role}">
                                                           checked="checked"
                                                       </c:if>
                                                       />

                                            </td>
                                            <td class="small-box">
                                                <c:url var="deleteLink" value="Middle_Servlet">
                                                    <c:param name="btAction" value="delete"/>
                                                    <c:param name="pk" value="${dto.username}"/>
                                                    <c:param name="searchHistory" value="${searchValue}"/>
                                                </c:url>
                                                <a href="${deleteLink}">Delete</a>
                                            </td>
                                            <td class="small-box-special">
                                                <input type="submit" value="Update" name="btAction" class="update-btn" />
                                                <input type="hidden" name="lastSearchValue" value="${searchValue}"/>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${empty result}">
                            <div class="blank">
                                <ion-icon class="icon-eyes" name="eye-off-sharp"></ion-icon>
                                <p>We couldn't find the result for "${searchValue}"</p>
                                <p>Please try again!</p>
                            </div>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

    <%--        <%
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    Cookie latestCookie = cookies[cookies.length - 1];
                    String username = latestCookie.getName();
            %>
            <font color="red"> 
            Welcome, <%= username%>!
            </font>
            <%
                }
            %>
            <h1>Search Page</h1>
            <form action="Middle_Servlet" method="POST">
                Search Value <input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue")%>"/> <br> 
                <!--giữ giá trị trên thanh search sau khi nhấn nút Search-->
                <input type="submit" value="Search" name="btAction"/>
            </form>
            <br/>
            <%
                String searchValue = request.getParameter("txtSearchValue");

            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT"); // ép kiểu mới làm đc
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full Name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody> 
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "Middle_Servlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&searchHistory=" + searchValue;
                %>
            <form action="Middle_Servlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>  
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>"/>
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>"/>
                    </td>
                    <td>
                        <%= dto.getFullName()%> 
                    </td>
                    <td>
                        <input type="checkbox" name="isAdmin" value="ON"
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction"/>
                        <input type="hidden" name="lastSearchValue" value="<%=searchValue%>" />            
                    </td> 
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
    <%
    } else { // tách giữa code html và java để thông báo ra màn hình rằng ko tìm thấy (scriplets-fragment code)
    %>
    <h2 style="color: red">Cannot find!</h2>
    <%
            }
        }// ngăn user truy cập trực tiếp trang search thông qua url mà ko vào login 
    %>
    --%>
</body>
</html>
