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
                    <h2>Cambio de dirección</h2>
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        Nombre<br>
                        <input name="name" type="text" value="${ciudadano.nombre}" disabled=""/><br/>
                        Apellidos<br/>
                        <input name="surname" type="text" value="${ciudadano.apellidos}" disabled=""/><br/>
                        Dirección Actual<br>
                        <input name="direccion" type="text" value="${ciudadano.direccion}" disabled=""/><br/>
                        Nueva Dirección<br>
                        <input name="direccion_nueva" type="text" /><br/>
                        <input type="hidden" name="action" value="change_direccion"/>
                        <input type="submit" value="Guardar Cambios"/>
                    </form>

                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
