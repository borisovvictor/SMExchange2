<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Social Media Exchange Web Application</title>
    </head>
    <body>
        <h1>Welcome to Social media exchange</h1>
        
        <form action="dosignin.jsp" method="POST">
            <p>Username: <input type="text" name="username" value="" /></p>
            <p>Password: <input type="text" name="password" value="" /></p>
            <input type="submit" value="Sign in" />
        </form>
        
        <form action="signup.jsp" method="POST">
            <input type="submit" value="Sign up" />
        </form>
        
        <!--<p><a href="add.jsp">Add new record</a></p>
        <p><a href="find.jsp">Find record</a></p>-->
    </body>
</html>