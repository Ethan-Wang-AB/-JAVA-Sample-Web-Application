<%-- 
    Document   : confirmationregister
    Created on : Nov 28, 2021, 9:28:26 PM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Register</title>
                  <link type="text/css" rel="stylesheet" href="css/register.css" charset="utf-8">

    </head>
    <body>
              <div class="container">
        <form action="registration" method="post">
            
     
             <h1>Hello ${firstname} ${lastname}</h1>
        <div id="google_translate_element"></div>

        <script type="text/javascript">
            function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
            }
        </script>

        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
        <h3>Please provide your confirmation code to finish registration</h3>
            <label> Confirmation Code: </label>
            <input type="number" name="code" >
            <button type="submit" name="submit" value="submit">Register</button>
            
            
         
        </form>
                     <a href="login">Back to Login Page</a><br>
   </div>
    </body>
</html>
