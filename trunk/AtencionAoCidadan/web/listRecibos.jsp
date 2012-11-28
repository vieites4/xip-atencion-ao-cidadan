
                        
                        
<%-- 
    Document   : index
    Created on : 08-nov-2012, 20:32:30
    Author     : nessa
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
        <div class="main">
            <div class="main_resize">
                <%@include file="fragmentos/cabecera.jspf" %>
                <div class="main_center">
                    <p class="breadcrumbs">
                        <a href="FrontController?action=view_ciudadano&id=${ciudadano}">Ciudadano</a> > Recibos 
                    </p>
                    <h2>Recibos y autoliquidaciones</h2>
                    <%@include file="fragmentos/listRecibos.jspf" %>
                    <a class="enlace" href="FrontController?action=view_ciudadano&id=${ciudadano}">Volver al ciudadano</a>
                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
