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
        <c:if test="${isAdmin==true}">
            <a href="inventory">Inventory</a><br>

            <a href="admin">Admin</a><br>
        </c:if>  
        <a href="editprofile">editprofile</a><br>
        <a href="login?logout">Logout</a><br>
         <c:if test="${isCompanyAdmin==true}">
          <a href="companyadmin">companyadmin</a><br>
                 </c:if>  
        <h2>Inventory for ${name}</h2> 
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
        <h2>Add Item</h2>
        <form method="post" action="inventory">
            <label>Category</label>
            <select id="categories" name="category">
                <c:forEach var="item" items="${categories}"> 
                    <option value="${item.getCategoryName()}">${item.getCategoryName()}</option>

                </c:forEach>
            </select>
            <br>
            <label>Item Name</label>
            <input type="text" name="itemName">
            <br>
            <label>Item Price</label>
            <input type="number" step="0.01" name="price">

            <br>
            <input type="submit" name="add" value="Add">
        </form>
        <c:if test="${errorExist==true}">

            <p>${errorMessage}</p>

        </c:if>
        <p>${message}</p>
        <p>${valueMessage}</p>

    </body>
</html>
