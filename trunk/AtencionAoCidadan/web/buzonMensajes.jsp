<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : listMensajes
    Created on : 29-dic-2012, 18:48:14
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
                    <h2>Buzón de mensajes</h2>
                    <%@include file="fragmentos/messages.jspf" %>
                    
                    <h3>Mensajes recibidos</h3>
                    <c:set var="enviados" value="false" scope="request"/>
                    <%@include file="fragmentos/listMensajes.jspf" %>
                    
                    <h3>Mensajes enviados</h3>
                    <c:set var="enviados" value="true" scope="request"></c:set>
                    <%@include file="fragmentos/listMensajes.jspf" %>     
                    
                    <h3>Enviar mensaje</h3>
                    <form method="post" action="FrontController">
                        <input type="hidden" name="action" value="send_mensaje" />
                        <label>Destinatario (nombre de usuario)</label>
                        <input type="text" name="destinatario" />
                        <label>Asunto</label>
                        <input type="text" name="asunto" />
                        <label>Cuerpo</label>
                        <input type="text" name="texto" />
                        <input type="submit" />
                    </form>
                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>

