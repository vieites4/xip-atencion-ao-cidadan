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
                    <h3>Por este medio puede solicitar un certificado de empadronamiento.
                        <br> El certificado acredita los datos que constan en el padrón municipal para todas las personas que residen en la misma vivienda que el interesado.</h3>
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        
                        <input type="hidden" name="action" value="solicitar_cert"/>
                        <input type="submit" value="Solicitar Certificado de Empadronamiento"/>
                    </form>

                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
