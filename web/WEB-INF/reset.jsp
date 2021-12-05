<%-- 
    Document   : reset
    Created on : Nov 18, 2021, 12:41:37 PM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <link type="text/css" rel="stylesheet" href="css/reset.css" charset="utf-8">

    </head>
    <body>
        <div class="container">
            <form action="reset" method="post">


                <h1>Reset Password</h1>
                <div id="google_translate_element"></div>

                <script type="text/javascript">
                    function googleTranslateElementInit() {
                        new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
                    }
                </script>

                <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

                <h3>Please Enter your email address to reset your password</h3>
                <label Address</label>
                <input type="email" name="email" >
                <button class="registerbtn" type="submit" name="submit" value="submit">Send Confirmation Email</button>

            </form>
            <a href="login">Back to Login Page</a><br>

        </div>
    </body>
</html>
