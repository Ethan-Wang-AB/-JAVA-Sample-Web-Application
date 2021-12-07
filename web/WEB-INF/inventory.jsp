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
        <link type="text/css" rel="stylesheet" href="css/inventory.css" charset="utf-8">
<!--        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->

    </head>
    <body >
        <div class="blink"  >       
            <h1 class="blink"  >Home Inventory! <div style="float: right"id="google_translate_element"></div></h1>
        </div>

        <script type="text/javascript">
            function googleTranslateElementInit() {
                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
            }
        </script>

        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
        <hr>
        <div class="topnav">
            <c:if test="${isAdmin==true}">
                <a class="active" href="inventory">Inventory</a>

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

            <a href="editprofile">Edit Profile</a>
            <a href="editcategory">Edit Category</a>
            <a href="login?logout">Logout</a>


        </div>


        <c:if test="${isCompanyAdmin==true}">
            <a href="companyadmin">companyadmin</a><br>
        </c:if>  
        <hr class="rounded">
        <h2>Inventory for ${name}</h2> 

        <div class="wrap">
            <div class="search">
                <c:if test="${isAdmin==true or isCompanyAdmin==true}">
                    <form action="inventory" method="get">
                        <input  id="searchTerm" type="text"  placeholder="Search.." name="keyword">
                        <button id="searchButton" type="submit" placeholder="Search.." name="search">Search</button>
                    </form>
                </c:if>

            </div>
        </div>




        <div class="search-container" > 




        </div>
        <table  class="${company}" id="customers">
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
                <th ></th> 
                <th >${total}</th> 
                <th > </th>  
                <th ></th> </tr>
        </table>
        <hr class="rounded">    
        <h3>${action}</h3>

        <c:if test="${deletion==true}">
            <a href="inventory?undoDelete&itemId=${itemId}">undo deletion</a>
        </c:if>
        <form method="post" action="inventory" enctype="multipart/form-data">
            <div class="container">           <input type="hidden" name="itemId" value="${itemId}">

                <br>
                <label>Item Name</label>
                <input type="text" name="itemName" value="${itemName}">
                <br>
                <label>Category</label>
                <select id="categories" name="category" value="${categoryName}">
                    <c:forEach var="item" items="${categories}"> 
                        <option value="${item.getCategoryName()}">${item.getCategoryName()}</option>

                    </c:forEach>
                </select><br>
                <label>Item Price</label>
                <input type="number" step="0.01" name="price" value="${itemPrice}">

                <br>
                <label>Item Image</label>
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
                <button class="registerbtn" type="submit" name="add" value="${action}">${action}</button>
            </div>

        </form>
        <c:if test="${errorExist==true}">

            <p>${errorMessage}</p>

        </c:if>
        <p>${message}</p>
        <p>${valueMessage}</p>

    </body>
</html>
