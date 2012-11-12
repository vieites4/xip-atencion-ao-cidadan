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
                    <h2>Buscar ciudadano</h2>
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="action" value="search_ciudadano"/>
                        <!--<label>Nombre</label>
                        <input name="name" type="text" /><br/>
                        <label>Apellidos</label>
                        <input name="surname" type="text"/><br/>-->
                        <label>DNI</label>
                        <input name="dni" type="text"/><br/>
                        <input type="reset" value="Borrar" />
                        <input type="submit" value="Buscar"/>
                    </form>

                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
