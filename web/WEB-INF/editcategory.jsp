<%-- 
    Document   : editcategory
    Created on : Nov 25, 2021, 8:36:01 PM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Category</title>
                <link type="text/css" rel="stylesheet" href="css/editcategory.css" charset="utf-8">

    </head>
    <body>
        <h1>Edit Category</h1>
        <div id="google_translate_element"></div>

        <script type="text/javascript">
            function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
            }
        </script>

        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

     <div class="topnav">
            <c:if test="${isAdmin==true}">
                <a  href="inventory">Inventory</a>

                <a href="admin">Admin</a>
            </c:if>  

            <a href="editprofile">Edit Profile</a>
            <a class="active" href="editcategory">Edit Category</a>
       
            <div class="dropdown">
                <button class="dropbtn">Reports 
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="report?type=all" target="_blank" rel="noopener noreferrer">All Users Summary</a>
                    <a href="report?type=activeUser" target="_blank" rel="noopener noreferrer">Active Non-admin Summary</a>

                </div>
                     <a href="login?logout">Logout</a>
            </div>  

        </div>
        <form action="editcategory" method="post">
            <table id="customers">
                <th>Number</th>
                <th>Category</th>
                <th>edit</th>
                    <c:forEach var="item" items="${categories}"> 
                    <tr>  
                        <td value="${item.getCategoryId()}">${item.getCategoryId()}</td>
                        <td value="${item.getCategoryName()}">${item.getCategoryName()}</td>
                        <td><a href="<c:url value='editcategory'>
                                   <c:param name='action' value="edit"/>
                                   <c:param name='categoryId' value="${item.categoryId}"/>
                               </c:url>">edit</a></td>
                    </tr>
                </c:forEach>
            </table>
    <c:if test="${action=='edit'}">
       <a class="two" href="editcategory?action=add">Add category</a>  
    </c:if>
            <h2>${action} Category</h2>

            <input type="hidden" name="action" value="${action}">
            <c:if test="${action=='edit'}">
                <label>Category Num: ${categoryId}</label>
                <input type="hidden" name="categoryId" value="${categoryId}">
            </c:if>
            <br>
            <label>Category Name:</label>
            <input type="text" name="categoryName" value="${categoryName}">
            <input type="submit" name="submit" value="${action}">





        </form>
    </body>
</html>
