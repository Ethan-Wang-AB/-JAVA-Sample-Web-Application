<%-- 
    Document   : newjsp
    Created on : Feb 12, 2022, 8:17:46 PM
    Author     : aphks
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alberta Bike Swap | Profile Page</title>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

        <link href="assets/css/ProfilePageStyle.css" rel="stylesheet" />

    </head>
    <body>
        <div class="container"> 
            <div class="box"> 
                <div class="box-row"> 
                    <div class="box-cell box1"> 
                        <div class="center">
                            <img src="assets/img/admin/logo-01.svg" alt="logo" width="50%"/> 
                        </div>
                    </div> 

                    <div class="box-cell box2"> 
                        <div class="center">
                            <a href="/" class="button">Logout</a>
                        </div> 
                    </div> 
                </div> 
            </div> 
        </div> 

        <div class="container-fluid">
            <div class="row">
                <div class="fb-profile">
                    <div class="fb-profile-text">
                        <h1></h1>

                    </div>
                </div>
            </div>
        </div> <!-- /container fluid-->  
        <div class="container">
            <div class="col-sm-8">

                <div data-spy="scroll" class="tabbable-panel">
                    <div class="tabbable-line">
                        <ul class="nav nav-tabs ">
                            <li class="active">
                                <a href="#tab_default_1" data-toggle="tab">
                                    Bike Information </a>
                            </li>
                            <li>
                                <a href="#tab_default_2" data-toggle="tab">
                                    Volunteering </a>
                            </li>
                            <li>
                                <a href="#tab_default_3" data-toggle="tab">
                                    Other</a>
                            </li>

                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_default_1">

                                <table class="table" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Bike Index</th>
                                            <th>Make</th>
                                            <th>Model</th>
                                            <th>Size</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="bike" items="${bikes}">
                                            <tr>
                                                <td>${bike.getBikeIndex()}</td>
                                                <td>${bike.getMaker()}</td>
                                                <td>${bike.getModel()}</td>
                                                <td>${bike.getSize()}</td>
                                            </tr>
                                        </c:forEach>
                                            <tr>
                                                <td><a href="#">CR1215066H ${Bikeindex}</a></td>
                                                <td>montague</td>
                                                <td>Boston</td>
                                                <td>L</td>

                                            </tr>
                                            <tr>
                                                <td><a href="#">M1407182 ${Bikeindex}</a></td>
                                                <td>giant</td>
                                                <td>REVOLT ADVANCED</td>
                                                <td>M</td>
                                            </tr>
                                            <tr>
                                                <td><a href="#">wsbc920332950p033019113546163 ${Bikeindex}</a></td>
                                                <td>Specialized</td>
                                                <td>Sirrus</td>
                                                <td>M</td>
                                            </tr>
                                            <tr>
                                                <td><a href="#">nor37775709k ${Bikeindex}</a></td>
                                                <td>Norco Bikes</td>
                                                <td>Bigfoot</td>
                                                <td>M</td>
                                            </tr>
                                        </tbody>
                                    </table>

                                </div>


                                <div class="tab-pane" id="tab_default_2">
                                    <p>
                                        Volunteering
                                    </p>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="tshirtSize">T-Shirt size:</label>
                                                <p> M ${tshirtSize}</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Dietary Restrictions:</label>
                                                <p> No ${diet}</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="firstposition">First Position Choice:</label>
                                                <p> Volunteer / Admin Desk - Supports volunteers and is central point for emergency services (AM/PM)${firstposition}</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Second Position Choice:</label>
                                                <p> Floater - Gets to do a little of everything (AM/PM) ${secondposition}</p>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="vShift">City & Shift:</label>
                                                <p> Calgary / 12 PM - 6 PM ${vShift} </p>
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Space for other information if needed:</label>
                                                <p> pune, maharashtra</p>
                                            </div>

                                        </div>
                                    </div>



                                </div>



                                <div class="tab-pane" id="tab_default_3">
                                    <p>
                                        Event Information
                                    </p>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="Location">Location:</label>
                                                <p> Calgary ${Location}</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="Date">Date 1:</label>
                                                <p> 25 August 2022 ${eventDate}</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="Date">Date 2:</label>
                                                <p> 26 August 2022 ${eventDate}</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Other information section:</label>
                                                <p> pune, maharashtra</p>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="email">Other information section::</label>
                                                <p> pune, maharashtra</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Other information section::</label>
                                                <p> pune, maharashtra</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Other information section::</label>
                                                <p> pune, maharashtra</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Other information section::</label>
                                                <p> pune, maharashtra</p>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-sm-4">
                    <div class="panel panel-default">
                        <div class="menu_title">
                            <h2>name ${name}</h2>

                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label for="ticket"> Ticket:</label>
                                        <p>${ticket}</p>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Phone Number:</label>
                                        <p>${phone}</p>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email:</label>
                                        <p>${email}</p>
                                    </div>
                                    <form action="EditProfile"  method="get">
                                        <button type="submit" class="btn btn-danger btn-block">Edit</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${roleId==2}">
                    <div id="bikesection" style="display:none">
                        <h2> Bike Information Section</h2>
                        <br/> <br/>



                        <div id="hideValuesOnSelect">
                            <div class="form-row p-t-20">
                                <div id="value">
                                    <div class="form-row">
                                        <div class="name">Bike Index</div>
                                        <div class="value">
                                            <div class="input-group">
                                                <input class="input--style-5" type="text" name="bikeindex">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="name">Bike Make</div>
                                        <div class="value">
                                            <div class="input-group">
                                                <input class="input--style-5" type="email" name="bikemake">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="name">Bike Type</div>
                                        <div class="value">
                                            <div class="input-group">
                                                <input class="input--style-5" type="email" name="biketype">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="name">What is the bike's size?</div>
                                        <div class="value">
                                            <div class="input-group">
                                                <div class="rs-select2 js-select-simple select--no-search">
                                                    <select name="bikesize" >
                                                        <option disabled="disabled" selected="selected" >Choose the bike size</option>
                                                        <option value=""></option>
                                                        <option value="01">S</option>
                                                        <option value="02">M</option>
                                                        <option value="03">L</option>

                                                    </select>
                                                    <div class="select-dropdown"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>
    </body>
</html>
