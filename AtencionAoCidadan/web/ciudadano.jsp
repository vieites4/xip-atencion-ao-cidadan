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
                    <h2>Ciudadano - <c:out value="${ciudadano.nombreCompleto}"></c:out></h2>
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        <%@include file="fragmentos/ciudadanoFields.jspf" %>
                        
                        <input type="hidden" name="id" value="${ciudadano.id}" />
                        <c:if test="${usuario.isAdministrativo()}">
                            <c:choose>
                                <c:when test="${ver}">
                                    <input type="hidden" name="action" value="edit_ciudadano"/>
                                    <p><input type="submit" value="Editar"/></p>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="action" value="update_ciudadano"/>
                                    <p>
                                        <a href="FrontController?action=view_ciudadano&id=${ciudadano.id}">Cancelar</a>
                                        <input type="submit" value="Guardar cambios"/>
                                    </p>
                                </c:otherwise>
                            </c:choose>
                            <a class="enlace" href="FrontController?action=view_recibos&ciudadano=${ciudadano.id}">Ver recibos y autoliquidaciones del ciudadano</a>
                        </c:if>
                    </form>
                    
                </div>
                <div class="clr"></div>
            </div>
        <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
