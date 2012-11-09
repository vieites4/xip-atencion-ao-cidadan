<%-- 
    Document   : error
    Created on : 09-nov-2012, 11:49:54
    Author     : joseangel.pineiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h2>An error ocurred:</h2>
        <p>
            <%=session.getAttribute("error_cause")%>
        </p>
        
    </body>
</html>
