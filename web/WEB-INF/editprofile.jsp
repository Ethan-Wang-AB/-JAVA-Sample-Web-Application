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
    </head>

    <body>
        <div class="headL"></div>
        <script>

        </script>



        <a href="inventory">Inventory</a><br>

        <a href="login?logout">Logout</a><br>
        <form action="editprofile" method="post" enctype='multipart/form-data'>
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

            <input type="hidden" name="editAction" value="edit">
            <input type="hidden" name="editAction" value="edit">
            <label>Email: </label>
            <label>${email}</label><br>
            <label>First Name</label>

            <input type="text" name="firstname" value="${user.getFirstName()}" ><br>
            <label>Last Name</label>
            <input type="text" name="lastname" value="${user.lastName}" >  <br>   
            <label>Password</label>
            <input type="password" name="password" value="${user.password}" ><br>  
            <input type="submit" name="submit" value="submit">
            <input type="reset" name="reset" value="reset">
        </form><br>
        <form action="editprofile" method="post">
            <input type="hidden" name="editAction" value="deactive">
            <input type="submit" name="submit" value="deactive account">

        </form>
    </body>
</html>
