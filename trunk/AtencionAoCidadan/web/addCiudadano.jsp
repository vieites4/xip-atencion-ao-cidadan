<%-- 
    Document   : addCiudadano
    Created on : 09-nov-2012, 11:06:15
    Author     : joseangel.pineiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/principal.css">
        <title>Atención ao cidadán</title>
    </head>
    <body>
        
        <div class="main">
            <div class="main_resize">
                <%@include file="fragmentos/cabecera.jspf" %>
                <div class="main_center">
                    <h2>Alta no padrón municipal</h2>
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="action" value="add_ciudadano"/>
                        <%@include file="fragmentos/ciudadanoFields.jspf" %>
                        <p><input type="submit" value="Dar de alta en el padrón"/></p>
                    </form>

                        
                       
                </div>
                <div class="clr"></div>
            </div>
        <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
