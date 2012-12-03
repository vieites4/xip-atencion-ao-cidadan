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

                <c:choose>
                    <c:when test="${usuario == null}">
                        <%@include file="fragmentos/login.jspf" %>
                    </c:when>
                    <c:otherwise>

                        <div class="main_center">
                            <h2>Inicio</h2>
                            <p>Pode escoller unha opción do menú para realizar algunha tarefa.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
                
                    <div class="clr"></div>
                </div>
                <%@include file="fragmentos/pie.jspf" %>
            </div>
        </body>
    </html>
