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
       <form action="FrontController" method="post">
                        <input type="hidden" name="action" value="listo_ciudadano"/>
                        <input type="submit" value="Buscar"/>
                    </form>
                    <c:if test="${list != null}">
                        <%@include file="fragmentos/listCiudadanos.jspf" %>
                    </c:if>
                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
