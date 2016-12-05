<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Social media exchange application</title>
    </head>
    <body>
        <h1>Registration</h1>
        <form action="dosignup.jsp" method="POST">
            <p>Type: <select name="type" id="type">
                <option value="client">Client</option>
                <option value="performer">Performer</option>
                <option value="agency">Agency</option>
            </select></p>
            <p>Name: <input type="text" name="name" value="" /></p>
            <p>Username: <input type="text" name="username" value="" /></p>
            <p>Password: <input type="text" name="password" value="" /></p>
            <p>Phone: <input type="text" name="phone" value="" /></p>
            <p>Agency ID: <input type="text" name="agencyid" value="" /></p>
            <input type="submit" value="Sign up" />
        </form>
    </body>
</html>