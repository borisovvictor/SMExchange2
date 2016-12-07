<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@page import="javax.naming.*, smexchange.*" %>
        <%@ page errorPage="error.jsp"%>
        <%!SMExchangeSessionBeanRemote ejbRef;%>
        
        <%InitialContext ic = new InitialContext();
        ejbRef = (SMExchangeSessionBeanRemote)ic.lookup("smexchange.SMExchangeSessionBeanRemote");
        Object result = ejbRef.getCurrentUser();%>  
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Social media exchange application</title>
        <style>
            .brd {
             border: 4px double black; /* Параметры границы */
             background: #fc3; /* Цвет фона */
             padding: 10px; /* Поля вокруг текста */
            }
        </style>
        <link rel="stylesheet" href="css/bootstrap.min.css">         
        <script src="js/bootstrap.min.js"></script> 
        
        
        <script type="text/javascript">
            function updateUIagency() {
                var el = document.getElementById("createOrderBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("removeOrderBtn");//.style.visibility = 'hidden';       
                el.parentNode.removeChild(el); 
                el = document.getElementById("createOfferBtn");//.style.visibility = 'hidden';  
                el.parentNode.removeChild(el);              
                el = document.getElementById("createRequestBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("removeRequestBtn ");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("payForBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("setCondBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("setCondText");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("approveBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("rejectBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("performBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("confirmOrderBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("removeOfferBtn");//.style.visibility = 'hidden';   
                el.parentNode.removeChild(el);         
            }
            // TODO: implement
            function updateUIclient() {
                var el = document.getElementById("createOfferBtn");
                el.parentNode.removeChild(el);
                el = document.getElementById("removeOfferBtn");   
                el.parentNode.removeChild(el); 
                el = document.getElementById("payToBtn");//.style.visibility = 'hidden'; 
                el.parentNode.removeChild(el); 
                el = document.getElementById("approveBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("rejectBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("performBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);                
                el = document.getElementById("confirmPaymentBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);                
                el = document.getElementById("addOfferBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("findOffersDiv");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                //findSeparator.setVisible(false);
            }
            // TODO: implement
            function updateUIperformer() {
                var el = document.getElementById("createOrderBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("removeOrderBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("payForBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("payToBtn");//.style.visibility = 'hidden'; 
                el.parentNode.removeChild(el);       
                el = document.getElementById("setCondBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("setCondText");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("confirmOrderBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("confirmPaymentBtn");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("findOffersDiv");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                el = document.getElementById("requestsSection");//.style.visibility = 'hidden';
                el.parentNode.removeChild(el);
                //findSeparator.setVisible(false);
            }
            
            function refreshOrders() {
                var html = "";  
                html += "<tr><th>ID</th><th>Client ID</th> <th>Offer ID</th><th>Status</th><th>Conditions</th></tr>";
                <% List<OrdersBeanRemote> orders = ejbRef.getOrdersByCurrentUser();
                for (OrdersBeanRemote order_it : orders) {%>
                        html+= "<tr>"
                            + "<td><%=order_it.getId()%></td>"
                            + "<td><%=order_it.getClientid()%></td>"
                            + "<td><%=order_it.getOfferid()%></td>" 
                            + "<td><%=order_it.getOrderstatus()%></td>"
                            + "<td><%=order_it.getSpeccond()%></td>"
                            + "</tr>";
                <%}%>
                document.getElementById("ordersTable").innerHTML = html;            
            }
            
            function refreshOffers() {
                var html = "";  
                html += "<tr><th>ID</th><th>Performed ID</th><th>Price</th><th>Period</th><th>Description</th><th>Social media</th> </tr>";

                <% List<OfferBeanRemote> offers = new ArrayList(); 
                if (request.getParameter("findOffersBtn") != null) {                 
                    String price = request.getParameter("price");
                    String period = request.getParameter("period");
                    String socialmedia = request.getParameter("socialmedia");
                    String keywords = request.getParameter("keywords");
                    if (price != null && !price.isEmpty() && period != null && !period.isEmpty() 
                            && socialmedia != null && !socialmedia.isEmpty() && keywords != null &&  !keywords.isEmpty())
                        offers = ejbRef.findOffers(Float.parseFloat(price), Integer.parseInt(period), socialmedia, keywords);
                } else {
                    offers = ejbRef.getOffersByCurrentUser();
                }
                                       
                for (OfferBeanRemote offer_it : offers) {%>
                        html+= "<tr>"
                            + "<td><%=offer_it.getId()%></td>"
                            + "<td><%=offer_it.getPerformerid()%></td>"
                            + "<td><%=offer_it.getPrice()%></td>" 
                            + "<td><%=offer_it.getPeriod()%></td>"
                            + "<td><%=offer_it.getDescription()%></td>"
                            + "<td><%=offer_it.getSocialmedia()%></td>"
                            + "</tr>";
                <%}%>
                document.getElementById("offersTable").innerHTML = html;            
            }
            
            function refreshRequests() {
                var html = "";  
                html += "<tr><th>ID</th><th>Price</th><th>Period</th><th>Description</th><th>Social media</th><th>Status</th><th>Offers</th></tr>";
  
                <% List<RequestBeanRemote> requests = ejbRef.getRequestsByCurrentUser();
                for (RequestBeanRemote request_it : requests) {%>
                        html+= "<tr>"
                            + "<td><%=request_it.getId()%></td>"
                            + "<td><%=request_it.getPrice()%></td>"
                            + "<td><%=request_it.getPeriod()%></td>"
                            + "<td><%=request_it.getDescription()%></td>"
                            + "<td><%=request_it.getSocialmedia()%></td>"
                            + "<td><%=request_it.getStatus()%></td>" 
                            + "<td><%=request_it.getOffersids()%></td>" 
                            + "</tr>";
                <%}%>
                document.getElementById("requestsTable").innerHTML = html;  
            }         
        </script>
        

    </head>
    <body>     
        <div id="infoSection" class="container">
            <h1>Dashboard</h1>
            <hr>
   
        <% if(result instanceof Client){ 
            Client client = (Client)result;         
            Users user = client.getUser();
        %>
            <h3>You're signed in. (ID=<%=user.getId()%>) </h3>
            <p>Name: <%=user.getName()%></p>
            <p>Username: <%=user.getUsername()%></p>
            <p>Phone: <%=client.getPhonenum()%></p>
        
        <% } else if(result instanceof Performer){ 
            Performer performer = (Performer)result;     
            Users user = performer.getUser();
        %>
            <h3>You're signed in. (ID=<%=user.getId()%>) </h3>
            <p>Name: <%=user.getName()%></p>
            <p>Username: <%=user.getUsername()%></p>
            <p>Phone: <%=performer.getPhonenumber()%></p>
            <p>Agency ID: <%=performer.getAgencyid()%></p>
            <p>Money amount: <%=performer.getMoneyamount()%></p>
        <% } else if(result instanceof Agency){ 
            Agency agency = (Agency)result;
            Users user = agency.getUser();
        %>
            <h3>You're signed in. (ID=<%=user.getId()%>) </h3>
            <p>Name: <%=user.getName()%></p>
            <p>Username: <%=user.getUsername()%></p>
            <p>Phone: <%="-"%></p>
     
        <% } else { %>
            <h1>You're not signed in. Please sign in.</h1>
            <p><a href="signin.jsp">Sign in</a></p>
        <% } %>
        
        </div>
             
        <div id="ordersSection" class="container">
            <hr>
            <p>Orders: <button id="refreshOrdersBtn" class="btn btn-info" onclick="refreshOrders()">Refresh</button></p>
            <form action="home.jsp">
                <table class="table" style="width:100%" name="ordersTable" id="ordersTable">
                  <tr>
                    <th>ID</th>
                    <th>Client ID</th> 
                    <th>Offer ID</th>
                    <th>Status</th> 
                    <th>Conditions</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>101</td> 
                    <td>52</td>
                    <td>Performed</td> 
                    <td>spec cond.</td>
                  </tr>
                </table>
                <div class="form-group" >
                    <div class="col-sm-12">
                        <button id="removeOrderBtn" class="btn btn-info">Remove order</button> 
                        <button id="payForBtn" class="btn btn-info">Pay for</button> 
                        <button id="payToBtn" class="btn btn-info">Pay to</button> 
                        <button id="approveBtn" class="btn btn-info">Approve</button> 
                        <button id="rejectBtn" class="btn btn-info">Reject</button> 
                        <button id="performBtn" class="btn btn-info">Perform</button> 
                        <button id="confirmOrderBtn" class="btn btn-info">Confirm order</button> 
                        <button id="confirmPaymentBtn" class="btn btn-info">Confirm payment</button>      
                    </div>
                </div>
                <div class="form-group" >
                    <div class="col-sm-2">
                    <button id="setCondBtn" class="btn btn-info">Set conditions</button> 
                    </div>
                    <div class="col-sm-10">
                    <input type="text" class="form-control" id="setCondText" style="width:200px; ">
                    </div>
                </div>
            </form>
        </div>
        
        <div name="findOffersDiv" id="findOffersDiv" class="container">
            <hr>
            <form action="home.jsp">
                <div class="form-group" >  
                    <div class="col-sm-2"><label>Keywords: </label></div>
                    <div class="col-sm-10"><input name="keywords" type="text" class="form-control"/></div>
                    <div class="col-sm-2"><label>Price (max):</label></div>
                    <div class="col-sm-10"><input name="price" type="number" min="0" step="0.1" class="form-control" /></div>        
                    <div class="col-sm-2"><label>Period (min): </label></div>
                    <div class="col-sm-10"><input name="period" type="number" min="0" step="1" class="form-control" /></div>           
                    <div class="col-sm-2"><label>Social media: </label></div>
                    <div class="col-sm-10"><input name="socialmedia" type="text" class="form-control" /></div>
                    <div class="col-sm-12"><button type="submit" id="findOffersBtn" name="findOffersBtn" class="btn btn-info">
                        <span class="glyphicon glyphicon-search"></span> Find offers
                        </button>
                    </div>
                </div>
            </form>
        </div>
        
        <div id="offersSection" class="container">
            <hr>
            <p>Offers: <button id="refreshOffersBtn" class="btn btn-info" onclick="refreshOffers()">Refresh</button></p>
            <form action="home.jsp">
                <table class="table" style="width:100%" name="offersTable" id="offersTable">
                  <tr>
                    <th>ID</th>
                    <th>Performed ID</th> 
                    <th>Price</th>
                    <th>Period</th> 
                    <th>Description</th>
                    <th>Social media</th> 
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>102</td> 
                    <td>500</td>
                    <td>7</td>
                    <td>some description</td> 
                    <td>facebook</td>
                  </tr>
                </table>

                <p>
                    <button id="createOrderBtn" class="btn btn-info">Create order</button> 
                    <button type="button" id="createOfferBtn" class="btn btn-info" onclick="window.location.href = 'createoffer.jsp';">Create offer</button> 
                    <button id="removeOfferBtn" class="btn btn-info">Remove offer</button>     
                </p>   
            </form>
        </div>
        
        
        <div class="container" id="requestsSection">
            <hr>
            <p>Requests: <button id="refreshRequestsBtn" class="btn btn-info">Refresh</button></p>
            <form action="home.jsp">
                <table class="table" style="width:100%" name="requestsTable" id="requestsTable">
                  <tr>
                    <th>ID</th>
                    <th>Price</th> 
                    <th>Period</th>
                    <th>Description</th>
                    <th>Social media</th> 
                    <th>Status</th>
                    <th>Offers</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>600</td> 
                    <td>10</td>
                    <td>some description</td>
                    <td>facebook</td> 
                    <td>waiting</td>
                    <td>1,3</td>
                  </tr>
                </table>

                <p>
                    <button type="button" id="createRequestBtn" class="btn btn-info" onclick="window.location.href = 'createrequest.jsp';">Create request</button> 
                    <button id="removeRequestBtn" class="btn btn-info">Remove request</button>   
                    <button id="addOfferBtn" class="btn btn-info">Add offer</button>   
                </p> 
            </form>
        </div>
        
        
        <% if(result instanceof Client){ 
            Client client = (Client)result;
        %>
            <script type="text/javascript">
                updateUIclient();
            </script>
        <% } else if(result instanceof Performer){ 
            Performer performer = (Performer)result;
        %>
            <script type="text/javascript">
                updateUIperformer();
            </script>
        <% } else if(result instanceof Agency){ 
            Agency agency = (Agency)result;
            Users user = agency.getUser();
        %>
            <script type="text/javascript">
                updateUIagency();
            </script>
        <% }%>
        
        
        </form>

    </body>
    <script>
        //alert(<%=ejbRef.getCurrentUserId()%>);
        refreshOffers();
        refreshOrders();
        refreshRequests();
        
        
    </script>
</html>