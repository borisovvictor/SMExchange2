<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>AddressBook Application</title>
    </head>
    <body>
        <%@page import="javax.naming.*, smexchange.*" %>
        <%@ page errorPage="error.jsp"%>
        <%!SMExchangeSessionBeanRemote ejbRef;%>
        
        <%InitialContext ic = new InitialContext();
        ejbRef = (SMExchangeSessionBeanRemote)ic.lookup("smexchange.SMExchangeSessionBeanRemote");
        /*ejbRef.addEmailAddress(request.getParameter("firstName"),
            request.getParameter("lastName"),
            request.getParameter("email"));*/
        %>

        <h1>Record successfully added!</h1>
        <p><a href="index.jsp">Return</a></p>
    </body>
</html>