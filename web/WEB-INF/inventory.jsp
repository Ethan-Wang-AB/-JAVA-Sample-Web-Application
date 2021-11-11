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
        <title>JSP Page</title>
    </head>
    <body>
      <h1>Home Inventory!</h1>
              <h2>Menu</h2>

     <a href="inventory?Inventory">Inventory</a><br>
        <a href="inventory?admin">Admin</a><br>
        <a href="inventory?logout">Logout</a><br>
        <h2>Inventory for ${user.getFirstName()+' '+user.getLastName()}</h2> 
        <table >
            <tr>
                <th >Category</th> 
                <th >Name</th> 
                <th >Price</th> 
                <th >Action </th> 

            </tr>
            <c:forEach var="item" items="${inventoryList}">
                <tr>
                    <td>${item.getCategory()}</td>
                    <td>${item.getPrice()}</td>

                    <td><a href="<c:url value='users'>
                               <c:param name='action' value="delete"/>
                               <c:param name='itemID' value="${item.itemID}"/>
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
                    <option value="${item.getCategory()}">${item.getCategory()}</option>

                </c:forEach>
            </select>
            <br>
            <label>Item Name</label>
            <input type="text" name="itemName">
            <br>
            <label>Item Price</label>
            <input type="number" name="price">
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
