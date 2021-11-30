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

        <a href="inventory">Inventory</a><br>
        <a href="admin">Admin</a><br>
        <a href="login?logout">Logout</a><br>
        <form action="editcategory" method="post">
            <table>
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

            <a href="editcategory?action=add">add category</a>    

            <h2>${action}</h2>

            <input type="hidden" name="action" value="${action}">
            <c:if test="${action=='edit'}">
                <label>Category Num: ${categoryId}</label>
                <input type="hidden" name="categoryId" value="${categoryId}">
            </c:if>
            <br>
            <label>Category Name:</label>
            <input type="text" name="categoryName" value="${categoryName}">
            <input type="submit" name="submit" value="submit">





        </form>
    </body>
</html>
