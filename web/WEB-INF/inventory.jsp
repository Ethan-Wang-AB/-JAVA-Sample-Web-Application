<%-- 
    Document   : inventory
    Created on : Nov 10, 2021, 8:00:17 PM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    <body>
        <h1>Home Inventory!</h1>
        <h2>Menu</h2>
        <div id="google_translate_element"></div>

        <script type="text/javascript">
            function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
            }
        </script>

        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

        <a href="editprofile">editprofile</a><br>
        <a href="login?logout">Logout</a><br>

        <c:if test="${isAdmin==true}">
            <a href="inventory">Inventory</a><br>

            <a href="admin">Admin</a><br>
        </c:if>  


        <c:if test="${isCompanyAdmin==true}">
            <a href="companyadmin">companyadmin</a><br>
        </c:if>  
        <h2>Inventory for ${name}</h2> 
        <c:if test="${isAdmin==true or isCompanyAdmin==true}">
            <form action="inventory" method="get">
                <label> filter</label>
                <input type="text"  name="keyword">
                <input type="submit" name="submit" value="apply filter">
            </form>


        </c:if>
        <table >
            <tr>
                <th >Username</th> 
                <th >Category</th> 
                <th >Name</th> 
                <th >Price</th> 
                <th>Edit</th>
                <th >Delete </th> 

            </tr>
            <c:forEach var="item" items="${inventoryList}">
                <tr>
                    <td>${item.getOwner().getEmail()}</td>
                    <td>${item.getCategory().getCategoryName()}</td>
                    <td>${item.getItemName()}</td>
                    <td>${item.getPrice()}</td>
                    <td>
                        <a href="<c:url value='inventory'>
                               <c:param name='action' value='edit' />
                               <c:param name='itemID' value='${item.itemId}'/>
                           </c:url>" >edit</a>
                    </td>
                    <td><a href="<c:url value='inventory'>
                               <c:param name='action' value="delete"/>
                               <c:param name='itemID' value="${item.itemId}"/>
                           </c:url>">delete</a></td>
                </tr>
            </c:forEach>  
            <tr>
                <th >Total</th> 
                <th ></th> 
                <th >${total}</th> 
                <th > </th>  </tr>
        </table>
        <h2>${action}</h2>
        <c:if test="${deletion==true}">
            <a href="inventory?undoDelete&itemId=${itemId}">undo deletion</a>
        </c:if>
        <form method="post" action="inventory" enctype="multipart/form-data">
            <input type="hidden" name="itemId" value="${itemId}">
            <label>Category</label>
            <select id="categories" name="category" value="${categoryName}">
                <c:forEach var="item" items="${categories}"> 
                    <option value="${item.getCategoryName()}">${item.getCategoryName()}</option>

                </c:forEach>
            </select>
            <br>
            <label>Item Name</label>
            <input type="text" name="itemName" value="${itemName}">
            <br>
            <label>Item Price</label>
            <input type="number" step="0.01" name="price" value="${itemPrice}">

            <br>
            <figure>

                <img src="${itemphotopath}" id="previewImage" name="photo" width="100" height="100">
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
            <input type="submit" name="add" value="${action}">
        </form>
        <c:if test="${errorExist==true}">

            <p>${errorMessage}</p>

        </c:if>
        <p>${message}</p>
        <p>${valueMessage}</p>

    </body>
</html>
