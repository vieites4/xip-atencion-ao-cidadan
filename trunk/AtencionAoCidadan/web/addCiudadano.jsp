<%-- 
    Document   : addCiudadano
    Created on : 09-nov-2012, 11:06:15
    Author     : joseangel.pineiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/principal.css">
        <title>Atención ao cidadán</title>
    </head>
    <body>
        <%@include file="fragmentos/cabecera.jspf" %>
        <%@include file="fragmentos/menuAdministrativo.jspf" %>
        <h2>Alta no padrón municipal</h2>
        <%@include file="fragmentos/messages.jspf" %>
        <div>
            <form action="FrontController" method="post">
                Nombre<br>
                <input name="name" type="text"/><br/>
                Apellidos<br/>
                <input name="surname" type="text"/><br/>
                <input type="hidden" name="action" value="add_ciudadano"/>
                <input type="submit" value="Dar de alta en el padrón"/>
            </form>

        </div>
        <%@include file="fragmentos/pie.jspf" %>
    </body>
</html>
