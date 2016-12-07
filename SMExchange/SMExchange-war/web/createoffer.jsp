<%-- 
    Document   : createoffer
    Created on : Dec 6, 2016, 7:11:12 PM
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create offer</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">         
        <script src="js/bootstrap.min.js"></script> 
                       
    </head>
    <body>        
        <div id="infoSection" class="container">       
            <form action="docreateoffer.jsp" method="POST">
                <h3>Creating offer</h3>
                <hr> 
                <div class="col-sm-2"><label>Description: </label></div>
                <div class="col-sm-10"><input name="description" type="text" class="form-control"/></div>
                <div class="col-sm-2"><label>Price:</label></div>
                <div class="col-sm-10"><input name="price" type="number" min="0" step="0.1" class="form-control" /></div>        
                <div class="col-sm-2"><label>Period: </label></div>
                <div class="col-sm-10"><input name="period" type="number" min="0" step="1" class="form-control" /></div>           
                <div class="col-sm-2"><label>Social media: </label></div>
                <div class="col-sm-10"><input name="socialmedia" type="text" class="form-control" /></div>
                <div class="col-sm-12"><button type="submit" id="createOfferBtn" class="btn btn-info" onclick="createOffer();">Create offer</button></div>
            </form>
        </div>
    </body>
</html>
