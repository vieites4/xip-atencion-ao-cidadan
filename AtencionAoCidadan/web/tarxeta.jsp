<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/principal.css">
        <title>Atención ao cidadán - Solicitar tarxeta de aparcamento</title>
    </head>
    <body>
        <div class="main">
            <div class="main_resize">
                <%@include file="fragmentos/cabecera.jspf" %>
                <div class="main_center">
                    <h2>Solicitude tarxeta de aparcamento</h2>
                    <h3>Por este medio puede solicitar unha tarxeta de aparcamento.
                        <br> A tarxeta terá que recollerse na oficina.</h3>
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        
                        <input type="hidden" name="action" value="tarx_aparcamento"/>
                        <input type="submit" value="Solicitar Tarxeta de Aparcamento"/>
                    </form>

                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
