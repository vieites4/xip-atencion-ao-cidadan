<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/principal.css">
        <title>Atención ao cidadán - Certificado de empadronamento</title>
    </head>
    <body>
        <div class="main">
            <div class="main_resize">
                <%@include file="fragmentos/cabecera.jspf" %>
                <div class="main_center">
                    <h2>Certificado de empadronamento</h2>
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        Nombre<br>
                        <input name="name" type="text"/><br/>
                        Apellidos<br/>
                        <input name="surname" type="text"/><br/>
                        Nombre<br>
                        <input type="hidden" name="action" value="add_ciudadano"/>
                        <input type="submit" value="Dar de alta en el padrón"/>
                    </form>

                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
