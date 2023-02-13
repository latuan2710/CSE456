<%-- 
    Document   : index
    Created on : Feb 6, 2023, 9:48:00 AM
    Author     : Nhân
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">        <title>Banking Home Page</title>
        <style>
            .nav-item {
                margin-left: 20px;
            }
            .nav-item a{
                font-size: 20px;
                font-weight: 600;
            }
            .tab-panel input{
                margin-bottom: 15px;
            }
            .tab-content {
                padding-top: 2em;
            }

            .tab-content {
                position: relative;
                transition: opacity .5s ease 0s;
            }

            .tab-content > .tab-pane {
                display:    block; /* undo "display: none;" */
                opacity:    0;
                position:   absolute;
                width:      calc(100% - 15em);
            }

            .tab-content > .active {
                opacity: 100%;
            }
        </style>
    </head>

    <body>
        <% int userId = (int) session.getAttribute("userId");%>
        <div class="container mt-4">
            <div class="row">
                <div class="col">
                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#checkBalance">Check balance</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#withdraw" >Withdraw</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#deposit">Deposit</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#transfer" >Transfer</a>
                        </li>
                        <li class="nav-item " style="margin-left: 380px">
                            <a href="LoginServlet?mode=logOut" >Log out</a>
                        </li>
                    </ul>
                    <br>
                    <div class="tab-content">
                        <div class="tab-pane active" id="checkBalance">
                            <div class="row">
                                <div class="col">
                                    <h5>User ID:</h5>
                                    <p><%=userId%></p>

                                    <h5>User Name:</h5>
                                    <p><%=request.getAttribute("userName")%></p>

                                    <h5>Balance:</h5>
                                    <p><%=request.getAttribute("userBalance")%></p>
                                </div>
                            </div> 
                        </div>
                        <div class="tab-pane fade" id="withdraw">
                            <div class="row">
                                <div class="col">
                                    <form action="ManageUserServlet?mode=withdraw" method="POST">
                                        <h4>Withdraw Money</h4>
                                        <br>
                                        <h5>User ID:</h5>
                                        <p><%=userId%></p>

                                        <h5>Enter Money:</h5>
                                        <input type="text" name="moneyWithdraw">

                                        <button class="btn btn-success" type="submit">Submit</button>
                                    </form>
                                </div>
                            </div> 
                        </div>
                        <div class="tab-pane fade" id="deposit">
                            <div class="row">
                                <div class="col-6" style="align-items: center">
                                    <form action="ManageUserServlet?mode=depositToCur" method="POST">
                                        <h5>User ID:</h5>
                                        <p><%=userId%></p>
                                        <h4>Deposit To Current Account</h4>
                                        <br>
                                        <h5>Enter Money:</h5>
                                        <input type="text" name="moneyDeposit">
                                        <button class="btn btn-success" type="submit">Submit</button>
                                    </form>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>                    
    </body>
</html>
