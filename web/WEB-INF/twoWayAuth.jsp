<%-- 
    Document   : twoWayAuth
    Created on : Nov 29, 2021, 8:40:59 AM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home n Inventory</title>
    </head>
    <body>
        <h1>Hello ${emailTwoWay}</h1>
        <div id="google_translate_element"></div>

        <script type="text/javascript">
            function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
            }
        </script>

        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

        <form action="authentication" method="post">
            <label> Authentication Code received in your email address</label>
            <input type="text" name="twoWayCode">
            <input type="submit" name="submit" value="sumbit">


        </form>
        <c:if test="${errorExist==true}">

            <p>${error}</p>

        </c:if>
    </body>
</html>
