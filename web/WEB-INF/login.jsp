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
        <link type="text/css" rel="stylesheet" href="css/login.css" charset="utf-8">
        
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    </head>
    <body>
        <div style="display:flex">
            <h1 style="flex:70%; text-align: center;" class="blink"  >Home Inventory</h1>

            <div style="max-width: 300px;flex:30%;" id="google_translate_element"></div>

            <script type="text/javascript">
                function googleTranslateElementInit() {
                    new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
                }
            </script>

            <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
        </div>
        <div style="display:flex;margin-top: 150px;">       
            <figure >
            <img style="max-width:400px; height: auto;" src="assets/welcome2.jpg">
        </figure>
        <figure>
             <img style="max-width:400px; height: auto;" src="assets/welcome1.jpg">
        </figure>
</div>

        <button onclick="document.getElementById('id01').style.display = 'block'" style="width:auto; min-width: 150px; display:block;margin:0 auto" >Login</button>

        <div id="id01" class="modal">

            <form class="modal-content animate" action="login" method="post">
                <div class="imgcontainer">
                    <img src="assets/test.png" alt="Avatar" class="avatar">
                </div>

                <div class="container">
                    <label for="email"><b>Email</b></label>
                    <input type="email" placeholder="Enter Email" name="email" required>

                    <label for="psw"><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="password" required>

                    <button type="submit">Login</button>

                </div>

             
        </div>
           <span class="psw" > <a href="registration">Create Account</a>    </span>
                <span class="psw"> <a href="reset">Forgot password</a>     </span>
    </form>
</div>
<!--        <form action="login" method="post">
            <fieldset>
                <legend>
                    <h3 style="color:salmon">Login</h3>
                </legend>
                <div style="display:flex;align-items: center;">
                    <div style="flex:30%; text-align: right;"><label>Email Address*: </label></div>
                    <div style="flex:70%;">  <input type="email" name="email"></div>       </div>
                <div style="display:flex;">
                    <div style="flex:30%;text-align: right;"><label>Password*: </label>   </div>
                    <div style="flex:70%;">  <input type="password" name="password"></div>
                </div> 

            </fieldset>
          <input style="display:block; margin:0 auto;"   type="submit" value="Sign in">
              <div style="text-align: right;">        <a href="registration">create account</a>
        <a href="reset">forget password</a>
        </div>
        </form>-->

<script>
// Get the modal
    var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
<c:if test="${errorExist==true}">

    <p>${error}</p>

</c:if>

</body>
</html>
