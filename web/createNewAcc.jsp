<%-- 
    Document   : createNewAcc
    Created on : Jun 26, 2023, 4:57:48 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/signUpError.css" />
        <title>Sign Up</title>
    </head>
    <body>
        <div class="container">
            <div class="form-login">
                <p class="first">Sign Up</p>
                <form action="Middle_Servlet" method="POST">
                    <c:set var="error" value="${requestScope.ERROR}"/>

                    <c:if test="${not empty error.usernameLength}">
                        <div class="input-field">
                            <span class="test">Must be from 6 to 20 characters</span>
                            <span class="icon">
                                <ion-icon name="person-circle-sharp"></ion-icon>
                            </span>
                            <input type="text" name="txtUsername" value="${param.txtUsername}" placeholder="Username*">  
                        </div>
                    </c:if>
                    <c:if test="${empty error.usernameLength && empty error.existedUsername}">
                        <div class="input-field">
                            <span class="icon">
                                <ion-icon name="person-circle-sharp"></ion-icon>
                            </span>
                            <input type="text" name="txtUsername" value="${param.txtUsername}" placeholder="Username*">  
                        </div>
                    </c:if>
                    <c:if test="${empty error.usernameLength && not empty error.existedUsername}">
                        <div class="input-field">
                            <span class="test">${error.existedUsername}</span>
                            <span class="icon">
                                <ion-icon name="person-circle-sharp"></ion-icon>
                            </span>
                            <input type="text" name="txtUsername" value="${param.txtUsername}" placeholder="Username*">  
                        </div>
                    </c:if>

                    <c:if test="${not empty error.passwordLength}">
                        <div class="input-field">
                            <span class="test">Must be from 6 to 30 characters</span>
                            <span class="icon">
                                <ion-icon name="lock-closed"></ion-icon>
                            </span>
                            <input type="password" name="txtPassword" value="" placeholder="Password*">
                        </div>
                    </c:if>
                    <c:if test="${empty error.passwordLength}">
                        <div class="input-field">
                            <span class="icon">
                                <ion-icon name="lock-closed"></ion-icon>
                            </span>
                            <input type="password" name="txtPassword" value="" placeholder="Password*">
                        </div>
                    </c:if>

                    <c:if test="${not empty error.confirmMatch}">
                        <div class="input-field">
                            <span class="test">Not matched</span>
                            <span class="icon">
                                <ion-icon name="repeat-sharp"></ion-icon>
                            </span>
                            <input type="password" name="txtConfirm" value="" placeholder="Confirm password*">
                        </div>
                    </c:if>
                    <c:if test="${empty error.confirmMatch}">
                        <div class="input-field">
                            <span class="icon">
                                <ion-icon name="repeat-sharp"></ion-icon>
                            </span>
                            <input type="password" name="txtConfirm" value="" placeholder="Confirm password*">
                        </div>
                    </c:if>

                    <c:if test="${not empty error.fullNameLength}">
                        <div class="input-field">
                            <span class="test">Must be from 2 to 50 characters</span>
                            <span class="icon">
                                <ion-icon name="text-sharp"></ion-icon>
                            </span>
                            <input type="text" name="txtFullName" value="${param.txtFullName}" placeholder="Fullname*">
                        </div>   
                    </c:if>
                    <c:if test="${empty error.fullNameLength}">
                        <div class="input-field">
                            <span class="icon">
                                <ion-icon name="text-sharp"></ion-icon>
                            </span>
                            <input type="text" name="txtFullName" value="${param.txtFullName}" placeholder="Fullname*">
                        </div>   
                    </c:if>
                    <button type="submit" value="Sign Up" name="btAction" class="btn">SIGN UP</button>
                    <button type="reset" value="Reset" class="btn">RESET</button>
                </form>
            </div>
        </div>
    </body>
</html>
