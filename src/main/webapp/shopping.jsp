<%@page import="business.Customer"%>
<%@page import="business.OrderLine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.Cupcake"%>
<%@page import="business.CupcakeFacade"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Contact - Business Casual - Start Bootstrap Theme</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/business-casual.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    </head>

    <body>

        <div class="brand">Business Casual</div>
        <div class="address-bar">3481 Melrose Place | Beverly Hills, CA 90210 | 123.456.7890</div>

        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                    <a class="navbar-brand" href="index.html">Business Casual</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="index.html">Home</a>
                        </li>
                        <li>
                            <a href="login.jsp">Login</a>
                        </li>
                        <li>
                            <a href="shopping.html">Shop</a>
                        </li>
                        <li>
                            <a href="contact.html">Contact</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>
        <%
            ArrayList<String> toppings = CupcakeFacade.getToppings();
            ArrayList<String> bottoms = CupcakeFacade.getBottoms();
            ArrayList<OrderLine> cart;
            Customer customer = (Customer) session.getAttribute("currentUser");
            double totalPrice = 0;
        %>
        <div class="container">
            <div class="box">
                <h4> <% if (null != customer){ %>
                    Your balance: <%= customer.getBalance()%>
                <%} else {%>
                You need to LOGIN to shop <%}%></h4>
                <div class="row">
                    <div class="col-md-4">
                        <form name="AddCupcake" action="Order" method="POST">
                            <input type="hidden" name="formName" value="AddCupcake" />
                            <div class="form-group">
                                <label>Topping</label>
                                <select class="form-control" name="topping">
                                    <%
                                        for (String t : toppings) {%>
                                    <option><%=t%></option>
                                    <% } %>
                                </select>
                            </div>

                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>Bottom</label>
                            <select class="form-control" name="bottom">
                                <%
                                    for (String b : bottoms) {%>
                                <option><%=b%></option>
                                <% }%>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <label>Quantity</label>
                        <div class="form-group">
                            <input type='button' value='-' class='qtyminus' field='quantity' />
                            <input type='text' name='quantity' value='1' class='qty' name="quantity"/>
                            <input type='button' value='+' class='qtyplus' field='quantity' />
                        </div>
                    </div>
                    <div class="col-md-2">
                        <label></label>
                        <div class="form-group">
                            <input class="btn btn-primary" type="submit" value="Add to cart"/>
                        </div>
                    </div>
                    </form>
                </div>
                <hr id="line">
                <div class="row" id="orderTable">
                    <section class="col-xs-12">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Cupcake</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if (request.getSession().getAttribute("currentUserCart") != null) {
                                        cart = (ArrayList<OrderLine>) request.getSession().getAttribute("currentUserCart");
                                        totalPrice = 0;
                                        for (OrderLine ol : cart) {%>
                                <tr>
                                    <th scope="row"><%=ol.getCupcake().getTopping().getName() + "/"
                                                + ol.getCupcake().getBottom().getName()%></th>
                                    <td><%=ol.getQuantity()%></td>
                                    <td><%=ol.getTotalPrice()%></td>
                                </tr>
                                <% totalPrice += ol.getTotalPrice();
                                        }
                                    }%>
                            </tbody>
                        </table>
                    </section>
                    <div class="col-xs-offset-6 col-xs-2">
                        <h4> total order price: <%= totalPrice%></h4>
                    </div>
                    <div class="col-xs-offset-5 col-xs-4">
                        <form action="Order" method="POST" name="finalOrder">
                            <input type="hidden" name="formName" value="finalOrder" />
                            <input type="submit" value="Order" class="btn btn-success"/>
                            <% request.getSession().setAttribute("totalPrice", totalPrice);%>
                        </form>

                    </div>
                </div>

            </div>
            <!-- row -->
            <!-- content container -->
        </div>
        <!-- /.container -->

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <p>Copyright &copy; Your Website 2014</p>
                    </div>
                </div>
            </div>
        </footer>

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
    </body>

</html>
