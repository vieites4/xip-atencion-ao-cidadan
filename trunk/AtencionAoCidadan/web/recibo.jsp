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
                    <p class="breadcrumbs">
                        <c:choose>
                            <c:when test="${usuario.isAdministrativo()}">
                                <a href="FrontController?action=view_ciudadano&id=${ciudadano}">Ciudadano</a> > 
                                <a href="FrontController?action=view_recibos&ciudadano=${recibo.ciudadano.id}">Recibos</a>
                                > Recibo 
                            </c:when>
                            <c:otherwise>
                                <a href="FrontController?action=view_recibos">Recibos</a>
                                > Recibo 
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <h2>Recibo - <c:out value="${recibo.numeroRecibo}"></c:out></h2>
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        <%@include file="fragmentos/reciboFields.jspf" %>
                        <input type="hidden" name="action" value="alter_recibo"/>
                        <input type="hidden" name="id" value="${recibo.id}" />
                        <%--<p><input type="submit" value="Guardar cambios"/></p>--%>
                    </form>
                    <c:if test="${usuario.isAdministrativo()}">
                        <a class="enlace" href="FrontController?action=view_recibos&ciudadano=${recibo.ciudadano.id}">Volver al listado de recibos y autoliquidaciones</a>
                    </c:if>
                </div>
                <div class="clr"></div>
            </div>
        <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
