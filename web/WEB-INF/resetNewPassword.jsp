<%-- 
    Document   : resetNewPassword
    Created on : Nov 18, 2021, 12:36:22 PM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset password</title>
    </head>
    <body>
        <h1>Enter a new password</h1>
        <form action="reset" method="post">
            
            <input type="hidden" name="uuid" value="${uuid}">
             <input type="password" name="password" >
             <input type="submit" name="submit" value="submit">
            
        </form>
    </body>
</html>
