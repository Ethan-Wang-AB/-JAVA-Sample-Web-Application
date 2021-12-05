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
        <link type="text/css" rel="stylesheet" href="css/admin.css" charset="utf-8">

    </head>
    <body>
     
        <h1>Home Inventory! <div style="float: right"id="google_translate_element"></div></h1>
       
        <script type="text/javascript">
            function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
            }
        </script>

        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
        <div class="topnav">
            <a class="active" href="admin">Admin</a>
            <a href="inventory">Inventory</a>

            <a href="editprofile">editprofile</a>
            <a href="editcategory">Edit Category</a>
            <a href="login?logout">Logout</a>
            <div class="dropdown">
                <button class="dropbtn">Reports 
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="report?type=all" target="_blank" rel="noopener noreferrer">All Users Summary</a>
                    <a href="report?type=activeUser" target="_blank" rel="noopener noreferrer">Active Non-admin Summary</a>

                </div>
            </div>               

        </div>
        <hr class="rounded">

        <h2>Manage Users</h2>
        <table id="customers">
            <tr>
                <th >Email</th> 
                <th >First Name</th> 
                <th >Last Name</th> 
                <th >Company</th> 
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
<hr class="rounded">

        <div class="container">
            <h2>${adminAction}</h2>
            <form action="admin" method="post" >


                <div > 
                    <label >Email</label>
                    <input  type="email" name="email" placeholder="email" value="${email}"  <c:if test="${isEdit==true}">disabled</c:if>>
                    </div>
                    <!--            <div class="col-auto" style="display:flex;"> 
                                    <label style="flex:50%;" >Email:</label>
                                    <input style="flex:50%;" type="email" name="email" placeholder="email" value="${email}">
                                </div>-->
                <div > 
                    <label  >First Name:</label>
                    <input   type="text" name="firstname" placeholder="first name" value="${fistName}">
                </div>
                <div > 
                    <label >Last Name:</label>
                    <input  type="text" name="lastname" placeholder="last name" value="${lastName}">
                </div>


                <div class="select"> 
                    <label class="selectLabel">Company Name:</label>
                    <select id="companies" name="companyName">
                        <c:forEach var="item" items="${companies}"> 
                            <option value="${item.getCompanyName()}">${item.getCompanyName()}</option>

                        </c:forEach>
                    </select>            </div>
                <div class="select"> 
                    <label class="selectLabel">Role Position:</label>
                    <select id="roles" name="roleName">
                        <c:forEach var="item" items="${roles}"> 
                            <option value="${item.getRoleName()}">${item.getRoleName()}</option>

                        </c:forEach>
                    </select>            </div>
                <div class="select"> 

                    <label class="selectLabel" >User Status:</label>
                    <select id="status" name="status">

                        <option value="true">Active</option>
                        <option value="false">Inactive</option>

                    </select>            </div>


                <div > 
                    <label >Password:</label>
                    <input  type="password" name="password" placeholder="password" value="${password}">
                </div>



                <button class="registerbtn" type="submit" value="save">${adminAction}</button>
                <button style="background-color: lightgray"class="registerbtn" type="reset" value="Cancel">cancel</button>

            </form>
        </div>
        <c:if test="${errorExist==true}">
            <p style="text-align: center;">Error: ${error}</p>
        </c:if>
    </body>
</html>



