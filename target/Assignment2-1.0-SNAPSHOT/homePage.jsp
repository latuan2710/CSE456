<%@page import="Model.entity.Transaction"%>
<%@page import="java.util.ArrayList"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

        <style type="text/css">
            .left {
                border-right: solid 1px black;
                text-align: center;
            }
            .left h5 {
                font-weight: 600;
                font-size: 20px;
                margin: 30px 15px;
            }
            .left button{
                margin: 15px;
                width: 170px;
                height: 80px;
                display: block;
                margin-left: auto;
                margin-right: auto;
            }
            .right h2{
                margin: 30px 15px;
            }
        </style>

        <title>Home Page</title>
    </head>
    <body>
        <% int userId = (int) session.getAttribute("userId");
            String userName = (String) session.getAttribute("userName");
            int userBalance = (int) session.getAttribute("userBalance");
            String display=(String)session.getAttribute("displayTransaction");
            
            ArrayList<Transaction> list = (ArrayList<Transaction>) session.getAttribute("listTransactions");
        %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-2">
                    <div class="left">
                        <h5>User Id: <%=userId%></h5>
                        <h5>User Name: <%=userName%></h5>
                        <button class="btn btn-success" data_bt="checkBalance">Check Balance</button>
                        <br>
                        <a href="ManageUserServlet?mode=list"><button class="btn btn-success" data_bt="transactions">Show transactions</button></a>
                        
                        <br> 
                        <button class="btn btn-success" data_bt="withdraw">Withdraw</button>
                        <br>
                        <button class="btn btn-success" data_bt="deposit">Deposit</button>
                        <br>
                        <button class="btn btn-success" data_bt="transfer">Transfer</button>
                        <br>
                        <a href="LoginServlet?mode=logOut"><button class="btn btn-success">Logout</button></a>
                    </div>
                </div>
                <div class="col-9">
                    <div class="right">
                        <div class="item checkBalance">
                            <h2>Check balance</h2>
                            <hr>
                            <p>Money: <%=userBalance%></p>
                            <br>
                        </div>

                        <div class="item transactions" style="display: none">
                            <h2>Transactions</h2>
                            <div class="container">
                                
                                <%
                                    if (list != null) {
                                        for (Transaction transaction : list) {
                                            out.print("<p>" + transaction + "</p>");
                                        }
                                    }
                                %>
                            </div>
                        </div>

                        <div class="item withdraw" style="display: none;">
                            <h2>Withdraw</h2>
                            <hr>
                            <form action="ManageUserServlet?mode=withdraw" method="POST">
                                <input type="text" name="userMoney" placeholder="Enter your money">
                                <br>
                                <button type="submit" class="btn btn-success" style="margin-top:20px">Submit</button>
                            </form>
                        </div>

                        <div class="item deposit" style="display: none;">
                            <h2>Deposit</h2>
                            <hr>
                            <form action="ManageUserServlet?mode=depositToCur" method="POST">
                                <input type="text" name="userMoney" placeholder="Enter your money">
                                <br>
                                <button type="submit" class="btn btn-success" style="margin-top:20px">Submit</button>
                            </form>
                        </div>

                        <div class="item transfer" style="display: none;">
                            <h2>Transfer</h2>
                            <hr>
                            <form action="ManageUserServlet?mode=transfer" method="POST">
                                <p>Receiver's Id:</p>
                                <input type="text" name="receiverId" placeholder="Enter valid receiver's Id">
                                <br>
                                <br>
                                <input type="text" name="userMoney" placeholder="Enter your money">
                                <br>
                                <button type="submit" class="btn btn-success" style="margin-top:20px">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>

        <script type="text/javascript">

            var bt_elements = document.querySelectorAll(".left button");
            var item_elements = document.querySelectorAll(".item");

            for (var i = 0; i < bt_elements.length; i++) {
                bt_elements[i].addEventListener("click", function () {
//                    bt_elements.forEach(function (button) {
//                        button.classList.remove("active");
//                    });
//                    this.classList.add("active");
                    var bt_value = this.getAttribute("data_bt");
                    item_elements.forEach(function (item) {
                        item.style.display = "none";
                    });

                    if (bt_value == "checkBalance") {
                        document.querySelector("." + bt_value).style.display = "block";
                    } else if (bt_value == "withdraw") {
                        document.querySelector("." + bt_value).style.display = "block";
                    } else if (bt_value == "deposit") {
                        document.querySelector("." + bt_value).style.display = "block";
                    } else if (bt_value == "transfer") {
                        document.querySelector("." + bt_value).style.display = "block";
                    } else if (bt_value == "transactions") {
                        document.querySelector("." + bt_value).style.display = "block";
                    } else {
                        console.log("");
                    }
                });
            }
        </script>
    </body>
</html>
