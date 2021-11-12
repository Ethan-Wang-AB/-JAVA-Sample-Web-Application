<%-- 
    Document   : admin
    Created on : Nov 10, 2021, 8:00:02 PM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Home Inventory!</h1>
        <h2>Menu</h2>
        <a href="inventory">Inventory</a><br>
        <a href="admin">Admin</a><br>
        <a href="login?logout">Logout</a><br>
        <h2>Manage Users</h2>
        <table >
            <tr>
                <th >Username</th> 
                <th >First Name</th> 
                <th >Last Name</th> 
                <th >Delete</th> 
                <th >Edit </th> 

            </tr>
            <c:forEach var="item" items="${userList}">
                <tr>
                    <td>${item.getUsername()}</td>
                    <td>${item.getFirstName()}</td>
                    <td>${item.getLastName()}</td>
                    <td>
                        <a href="<c:url value='admin'>
                               <c:param name='action' value='edit' />
                               <c:param name='username' value='${item.username}'/>
                           </c:url>" >edit</a>
                    </td>
                    <td>
                        <a href="<c:url value='admin'>
                               <c:param name='action' value='delete'/>
                               <c:param name='username' value='${item.username}'/>
                           </c:url>">delete</a>
                    </td>
                </c:forEach>  
        </table>
        <h2>${adminAction}</h2>
        <form action="admin" method="post" style=" background-color:dimgray;  border: groove;   border-radius: 15px; border-color:darkkhaki;  padding: 15px 32px;  text-decoration: none;  margin: 5px;">

         
            <div class="col-auto"> 
                <label>Username</label>
                <input type="username" name="username" placeholder="username" value="${username}"  >
            </div>
            <div class="col-auto"> 
                <label>Email:</label>
                <input type="email" name="email" placeholder="email" value="${email}">
            </div>
            <div class="col-auto"> 
                <label>First Name:</label>
                <input type="text" name="firstname" placeholder="first name" value="${fistName}">
            </div>
            <div class="col-auto"> 
                <label>Last Name:</label>
                <input type="text" name="lastname" placeholder="last name" value="${lastName}">
            </div>
            <div class="col-auto"> 
                <label>Password:</label>
                <input type="password" name="password" placeholder="password" value="${password}">
            </div>


            <div class="col-auto" style="display:flex;"> 
                <input class="button" type="submit" value="save">
                <input class="button" type="reset" value="Cancel">
            </div>
        </form>

        <!--        <h2>Add User</h2>
        
                <form action="users" method="post"  style=" background-color:lightgrey;  border: groove;    border-radius: 15px; padding: 15px 32px;  text-decoration: none;  margin: 5px;">
                    <div class="col-auto"> 
                        <input type="hidden" name="action" value="new">
                    </div>
                    <div class="col-auto"> 
                        <input type="email" name="emailN" placeholder="email">
                    </div>
                    <div class="col-auto"> 
                        <input type="text" name="firstName" placeholder="first name">
                    </div>
                    <div class="col-auto"> 
                        <input type="text" name="lastName" placeholder="last name">
                    </div>
                    <div class="col-auto"> 
                        <input type="text" name="password" placeholder="password">
                    </div>
                    <div class="col-auto"> 
                        <select name="status" id="status" class="form-select form-select-sm" aria-label=".form-select-sm example">
                            <option value="active">active</option>
                            <option value="inactive">inactive</option>
        
                        </select>
                    </div>
                    <div class="col-auto"> 
                        <select name="role" id="role">
                            <option value="system admin">system admin</option>
                            <option value="regular user">regular user</option>
                            <option value="company admin">company admin</option>
        
                        </select>
                    </div>
                    <div class="col-auto"> 
                        <input class="button" type="submit" value="save">
                    </div>
        
                </form>-->

             <c:if test="${errorExist==true}">
                <p style="text-align: center;">Error: ${error}</p>
            </c:if>
    </body>
</html>
