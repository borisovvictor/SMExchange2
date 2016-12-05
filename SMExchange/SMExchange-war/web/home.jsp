<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
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
                document.getElementById("createOrderBtn").style.visibility = 'hidden';
                document.getElementById("removeOrderBtn").style.visibility = 'hidden';        
                document.getElementById("createOfferBtn").style.visibility = 'hidden';                
                document.getElementById("createRequestBtn").style.visibility = 'hidden';
                document.getElementById("removeRequestBtn ").style.visibility = 'hidden';
                document.getElementById("payForBtn").style.visibility = 'hidden';
                document.getElementById("setCondBtn").style.visibility = 'hidden';
                document.getElementById("setCondText").style.visibility = 'hidden';
                document.getElementById("approveBtn").style.visibility = 'hidden';
                document.getElementById("rejectBtn").style.visibility = 'hidden';
                document.getElementById("performBtn").style.visibility = 'hidden';
                document.getElementById("confirmOrderBtn").style.visibility = 'hidden';
                document.getElementById("removeOfferBtn").style.visibility = 'hidden';            
            }
        </script>
        

    </head>
    <body>     
       
        
        <div id="infoSection" class="container">
            <h1>Dashboard</h1>
            <hr>
        
        <%@page import="javax.naming.*, smexchange.*" %>
        <%@ page errorPage="error.jsp"%>
        <%!SMExchangeSessionBeanRemote ejbRef;%>
        
        <%InitialContext ic = new InitialContext();
        ejbRef = (SMExchangeSessionBeanRemote)ic.lookup("smexchange.SMExchangeSessionBeanRemote");
        Object result = ejbRef.getCurrentUser();%>    
        
        <% if(result instanceof Client){ 
            Client client = (Client)result;
        %>
            <h3>You're signed in. (ID=<%=client.getId()%>) </h3>
            <p>Name: </p>
        
        <% } else if(result instanceof Performer){ 
            Performer performer = (Performer)result;
        %>
            <h3>You're signed in. (ID=<%=performer.getId()%>) </h3>
            <p>Name: </p>
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
            <p>Orders: <button id="refreshOrdersBtn" class="btn btn-info">Refresh</button></p>
            
            <table class="table" style="width:100%" name="ordersTable" id="ordersTable">
              <tr>
                <th>Firstname</th>
                <th>Lastname</th> 
                <th>Age</th>
              </tr>
              <tr>
                <td>Jill</td>
                <td>Smith</td> 
                <td>50</td>
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
        </div>
        
        
        
        <div name="findOffersDiv" class="container">
            <hr>
            
            <div class="form-group" >
                
                <div class="col-sm-2"><label>Keywords: </label></div>
                <div class="col-sm-10"><input type="text" class="form-control"/></div>
                <div class="col-sm-2"><label>Price (max):</label></div>
                <div class="col-sm-10"><input type="number" min="0" step="0.1" class="form-control" /></div>        
                <div class="col-sm-2"><label>Period (min): </label></div>
                <div class="col-sm-10"><input type="number" min="0" step="1" class="form-control" /></div>           
                <div class="col-sm-2"><label>Social media: </label></div>
                <div class="col-sm-10"><input type="text" class="form-control" /></div>
                <div class="col-sm-12"><button type="submit" id="findOffersBtn" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Find offers
                    </button>
                </div>
            </div>
        </div>
        
        <div id="offersSection" class="container">
            <hr>
            <p>Offers: <button id="refreshOffersBtn" class="btn btn-info">Refresh</button></p>
            <table class="table" style="width:100%" name="offersTable" id="offersTable">
              <tr>
                <th>Firstname</th>
                <th>Lastname</th> 
                <th>Age</th>
              </tr>
              <tr>
                <td>Jill</td>
                <td>Smith</td> 
                <td>50</td>
              </tr>
            </table>

            <p>
                <button id="createOrderBtn" class="btn btn-info">Create order</button> 
                <button id="createOfferBtn" class="btn btn-info">Create offer</button> 
                <button id="removeOfferBtn" class="btn btn-info">Remove order</button>     
            </p>         
        </div>
        
        
        <div class="container" id="requestsSection">
            <hr>
            <p>Requests: <button id="refreshRequestsBtn" class="btn btn-info">Refresh</button></p>
            <table class="table" style="width:100%" name="requestsTable" id="requestsTable">
              <tr>
                <th>Firstname</th>
                <th>Lastname</th> 
                <th>Age</th>
              </tr>
              <tr>
                <td>Jill</td>
                <td>Smith</td> 
                <td>50</td>
              </tr>
            </table>

            <p>
                <button id="createRequestBtn" class="btn btn-info">Create request</button> 
                <button id=" " class="btn btn-info">Remove request</button>   
                <button id="addOfferBtn" class="btn btn-info">Add offer</button>   
            </p>           
        </div>
        
        
        <% if(result instanceof Client){ 
            Client client = (Client)result;
        %>
        
        <% } else if(result instanceof Performer){ 
            Performer performer = (Performer)result;
        %>
        
        <% } else if(result instanceof Agency){ 
            Agency agency = (Agency)result;
            Users user = agency.getUser();
        %>
            <script type="text/javascript">
                updateUIagency();
            </script>
        <% }%>
        
        


    </body>
    <script>
        alert(<%=ejbRef.getCurrentUserId()%>);
        
        
    </script>
</html>