<%-- 
    Document   : registration
    Created on : Nov 25, 2021, 11:24:35 AM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home n Inventory Registration Page</title>
    </head>
    <body>

        <a href="login?logout">Back to Login Page</a><br>
        <form action="registration" method="post">
            <label>Email </label>
            <input type="email" name="email" required><br>
            <label>First Name</label>

            <input type="text" name="firstname" required><br>
            <label>Last Name</label>
            <input type="text" name="lastname" required>    <br> 
            <label>Company: </label>
            <select id="company" name="company" value="${companies}" required>
                <c:forEach var="item" items="${companies}"> 
                    <option value="${item.getCompanyName()}">${item.getCompanyName()}</option>

                </c:forEach>
            </select><br>
            <label>Password</label>
            <input type="password" name="password" required>  <br> 
            <input type="submit" name="submit" value="register">

        </form>
    </body>
</html>
