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
                    <h2>Listado de ciudadanos</h2>
                    <%@include file="fragmentos/messages.jspf" %>
                    <c:choose>
                        <c:when test="${list.isEmpty()}">
                            <p>No hay resultados</p>
                        </c:when>
                        <c:otherwise>
                            <table>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Apellidos</th>
                                    <th>DNI</td>
                                </tr>
                                <c:forEach var="c" items="${list}">
                                    <tr>    
                                        <td>${c.nombre}</td>
                                        <td>${c.apellidos}</td>
                                        <td>${c.dni}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>

                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
