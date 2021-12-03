<%-- 
    Document   : login
    Created on : Nov 10, 2021, 8:00:24 PM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>

        <h1>Home Inventory</h1>
        <div id="google_translate_element"></div>

        <script type="text/javascript">
            function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
            }
        </script>

        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

        <h2>Login</h2>
        <form action="login" method="post">
            email: <input type="email" name="email"><br>
            password: <input type="password" name="password"><br>
            <input type="submit" value="Sign in">
        </form>
     
        <a href="registration">create account</a>
           <a href="reset">forget password</a>
        <c:if test="${errorExist==true}">

            <p>${error}</p>

        </c:if>

    </body>
</html>
