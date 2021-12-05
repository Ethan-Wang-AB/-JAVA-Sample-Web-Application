<%-- 
    Document   : editprofile
    Created on : Nov 25, 2021, 1:12:50 PM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <link type="text/css" rel="stylesheet" href="css/editprofile.css" charset="utf-8">

    </head>

    <body>



        <div class="container">
            <h1>Edit Profile </h1>

            <div style="float: right; "id="google_translate_element"></div>
            <script type="text/javascript">
                function googleTranslateElementInit() {
                    new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
                }
            </script>

            <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>


            <div class="topnav">


                <a class="active" href="editprofile">Edit profile</a>


                <c:if test="${isAdmin==true}">
                    <a  href="inventory">Inventory</a>
                    <a href="editcategory">Edit Category</a>
                    <a href="admin">Admin</a>
                    <div class="dropdown">
                        <button class="dropbtn">Reports 
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content">
                            <a href="report?type=all" target="_blank" rel="noopener noreferrer">All Users Summary</a>
                            <a href="report?type=activeUser" target="_blank" rel="noopener noreferrer">Active Non-admin Summary</a>

                        </div>
                    </div>  
                </c:if>  
                <a  href="login?logout">Logout</a>

            </div>
            <form action="editprofile" method="post" enctype='multipart/form-data'>
                <label>Profile Photo</label>
                <figure>

                    <img src="${photopath}" id="previewImage" name="photo" width="100" height="100">
                </figure>
                
                <input type="file" id="photoUp" name="photo" value="upload" accept="image/*" ><br>
                <script>
                photoUp.onchange = event => {
                    const [file] = photoUp.files
                    if (file) {
                        previewImage.src = URL.createObjectURL(file)
                    }
                }

                </script>
                <br>
                <input type="hidden" name="editAction" value="edit">
                <label>Email: </label>
                <input type="email" readonly value="${email}">
                <label>First Name</label>

                <input type="text" name="firstname" value="${user.getFirstName()}" ><br>
                <label>Last Name</label>
                <input type="text" name="lastname" value="${user.lastName}" >  <br>   
                <label>Password</label>
                <input type="password" name="password" value="${user.password}" ><br>  
                <button class="registerbtn" type="submit" name="submit" value="submit">Submit</button>
                <input class="registerbtn" style="background-color: graytext"type="reset" name="reset" value="Cancel">
            </form><br>
            <form action="editprofile" method="post" enctype='multipart/form-data'>
                <input type="hidden" name="editAction" value="deactive">
                <span class="psw"><input style="  background-color: white;
                                         font-size: medium;
                                         color: black;
                                         border: 2px solid green;
                                         padding: 10px 20px;
                                         text-align: center;
                                         text-decoration: none;
                                         display: inline-block;" type="submit" name="submit" value="deactive account">
                </span><span class="psw"> <a class="two" href="editprofile?twowayEnable">Enable Two Way Authentication</a></span>     </span>

            </form>
        </div>
    </body>
</html>
