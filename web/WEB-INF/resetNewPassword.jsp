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
                  <link type="text/css" rel="stylesheet" href="css/reset.css" charset="utf-8">

    </head>
    <body>
        
        <form action="reset" method="post">

          <div class="container">  
            
            <h1>Enter a new password</h1>
        <div id="google_translate_element"></div>

        <script type="text/javascript">
            function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
            }
        </script>

        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

            <input type="hidden" name="uuid" value="${uuid}">
            <input type="password" name="password" >
            <button class="registerbtn" type="submit" name="submit" value="submit"> Reset Password</button>>
            </div>
        </form>
    </body>
</html>
