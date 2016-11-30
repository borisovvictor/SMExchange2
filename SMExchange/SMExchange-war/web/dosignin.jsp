<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Social media exchange application</title>
    </head>
    <body>
        <%@page import="javax.naming.*, smexchange.*" %>
        <%@ page errorPage="error.jsp"%>
        <%!SMExchangeSessionBeanRemote ejbRef;%>
        
        <%InitialContext ic = new InitialContext();
        ejbRef = (SMExchangeSessionBeanRemote)ic.lookup("smexchange.SMExchangeSessionBeanRemote");
        int result = ejbRef.auth(request.getParameter("username"),
           request.getParameter("password"));%>
           
        
        <% if (result == -1) { %>
            <h1>Sign in failed!</h1>
            <p><a href="index.jsp">Return</a></p>
        <% } else { %>
            <h1>Sign in succeeded!</h1>
            <p><a href="home.jsp">Return</a></p>
        <% } %>
        
    </body>
</html>