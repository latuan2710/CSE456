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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Banking Login Page</title>
        <style>
            .divider:after,
            .divider:before {
                content: "";
                flex: 1;
                height: 1px;
                background: #eee;
            }
            .h-custom {
                height: calc(100% - 73px);
            }
            @media (max-width: 450px) {
                .h-custom {
                    height: 100%;
                }
            }
        </style>
    </head>

    <body>
        <section class="vh-100">
            <div class="container-fluid h-custom">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <img src="image/logo.png" alt=""/>
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <form action="LoginServlet?mode=login" method="POST">
                            <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                                <p class="lead fw-normal mb-0 me-3">Sign in</p>
                            </div>
                            <br>
                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <label class="form-label" for="userId">User ID</label>
                                <input type="text" id="userId" name="userId" class="form-control form-control-lg"
                                       placeholder="Enter a valid user ID" />

                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-3">
                                <label class="form-label" for="userPassword">Password</label>
                                <input type="password" id="userPassword" name="userPassword" class="form-control form-control-lg"
                                       placeholder="Enter password" />

                            </div>

                            <div class="d-flex justify-content-between align-items-center">
                                <a href="#!" class="text-body">Forgot password?</a>
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem; ">Login</button>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="register.jsp"
                                                                                                  class="link-danger">Register</a></p>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </section>

        <form action="ManageUserServlet" method="POST">
            <button type="submit">Get size</button>
        </form>
    </body>
</html>
