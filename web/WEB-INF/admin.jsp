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
        <div id="google_translate_element"></div>

        <script type="text/javascript">
            function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
            }
        </script>

        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

        <h2>Menu</h2>
        <a href="inventory">Inventory</a><br>
        <a href="admin">Admin</a><br>
        <a href="editprofile">editprofile</a><br>
        <a href="editcategory">Edit Category</a><br>
        <a href="login?logout">Logout</a><br>

        <h2>Manage Users</h2>
        <table >
            <tr>
                <th >Email</th> 
                <th >First Name</th> 
                <th >Last Name</th> 
                <th >Company Name</th> 
                <th> Role</th>
                <th >Active</th> 
                <th >Delete</th> 
                <th >Edit </th> 

            </tr>
            <c:forEach var="item" items="${userList}">
                <tr>
                    <td>${item.getEmail()}</td>
                    <td>${item.getFirstName()}</td>
                    <td>${item.getLastName()}</td>
                    <td>${item.getCompanyID().getCompanyName()}</td>
                    <td>${item.getRole().getRoleName()}</td>
                    <td>${item.getActive()}</td>
                    <td>
                        <a href="<c:url value='admin'>
                               <c:param name='action' value='edit' />
                               <c:param name='email' value='${item.email}'/>
                           </c:url>" >edit</a>
                    </td>
                    <td>
                        <a href="<c:url value='admin'>
                               <c:param name='action' value='delete'/>
                               <c:param name='email' value='${item.email}'/>
                           </c:url>">delete</a>
                    </td>
                </c:forEach>  
        </table>

        <c:if test="${deletion==true}">
            <a href="<c:url value='admin'>
                   <c:param name='undoDelete' value='true' />
                   <c:param name='emailDeleted' value='${emailDeleted}'/>
               </c:url>" >undo deletion</a>


        </c:if>
        <p>Get Report for all user summary <a href="report?type=all" target="_blank" rel="noopener noreferrer">get report</a>.</p>
        <p>Get Report for active non-admin user summary <a href="report?type=activeUser" target="_blank" rel="noopener noreferrer">get report</a>.</p>  

        <h2>${adminAction}</h2>
        <form action="admin" method="post" style="max-width: 500px; background-color:dimgray;  border: groove;   border-radius: 15px; border-color:darkkhaki;  padding: 15px 32px;  text-decoration: none;  margin: 5px;">


            <div class="col-auto" style="display:flex;"> 
                <label style="flex:50%;">email</label>
                <input style="flex:50%;" type="email" name="email" placeholder="email" value="${email}"  <c:if test="${isEdit==true}">disabled</c:if>>
                </div>
                <!--            <div class="col-auto" style="display:flex;"> 
                                <label style="flex:50%;" >Email:</label>
                                <input style="flex:50%;" type="email" name="email" placeholder="email" value="${email}">
                            </div>-->
            <div class="col-auto" style="display:flex;"> 
                <label style="flex:50%;" >First Name:</label>
                <input  style="flex:50%;" type="text" name="firstname" placeholder="first name" value="${fistName}">
            </div>
            <div class="col-auto" style="display:flex;"> 
                <label style="flex:50%;">Last Name:</label>
                <input style="flex:50%;" type="text" name="lastname" placeholder="last name" value="${lastName}">
            </div>


            <div class="col-auto" style="display:flex;"> 
                <label style="flex:50%;">Company Name:</label>
                <select id="companies" name="companyName">
                    <c:forEach var="item" items="${companies}"> 
                        <option value="${item.getCompanyName()}">${item.getCompanyName()}</option>

                    </c:forEach>
                </select>            </div>
            <div class="col-auto" style="display:flex;"> 
                <label style="flex:50%;">Role Position:</label>
                <select id="roles" name="roleName">
                    <c:forEach var="item" items="${roles}"> 
                        <option value="${item.getRoleName()}">${item.getRoleName()}</option>

                    </c:forEach>
                </select>            </div>
            <div class="col-auto" style="display:flex;"> 
                <label style="flex:50%;" >Status:</label>
                <select id="status" name="status">

                    <option value="true">Active</option>
                    <option value="false">Inactive</option>

                </select>            </div>


            <div class="col-auto" style="display:flex;"> 
                <label style="flex:50%;">Password:</label>
                <input style="flex:50%;" type="password" name="password" placeholder="password" value="${password}">
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
