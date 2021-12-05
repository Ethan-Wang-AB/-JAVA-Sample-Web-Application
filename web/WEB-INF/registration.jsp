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
        <link type="text/css" rel="stylesheet" href="css/register.css" charset="utf-8">


    </head>
    <body>
        <div class="container">
        <form action="registration" method="post">
                <div class="container">
            <h1>Register to Home N Inventory</h1>
       
               
                <div id="google_translate_element"></div>

                <script type="text/javascript">
                    function googleTranslateElementInit() {
                        new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
                    }
                </script>

                <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
                <p>Please fill in this form to create an account.</p>
                <hr>

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
                <hr>
                <p>By creating an account you agree to common Terms & Privacy</a>.</p>
                <button class="registerbtn" type="submit" name="submit" value="register">register</button>

                    </form>
                     <a href="login">Back to Login Page</a><br>
            </div>
                    
    </body>
</html>
