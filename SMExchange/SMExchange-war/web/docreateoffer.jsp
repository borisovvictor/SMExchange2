<%-- 
    Document   : docreateoffer
    Created on : Dec 6, 2016, 7:56:15 PM
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create offer</title>
    </head>
    <body>
        
        <%@page import="javax.naming.*, smexchange.*" %>
        <%@ page errorPage="error.jsp"%>
        <%!SMExchangeSessionBeanRemote ejbRef;%>
        
        <%InitialContext ic = new InitialContext();
        ejbRef = (SMExchangeSessionBeanRemote)ic.lookup("smexchange.SMExchangeSessionBeanRemote");%>   
        
        <%=ejbRef.createOffer(
            request.getParameter("price"),
            request.getParameter("period"), 
            request.getParameter("description"),
            request.getParameter("socialmedia"))%>
      
    </body>
</html>
