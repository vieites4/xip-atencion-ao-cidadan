<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/principal.css">
        <title>Atención ao cidadán - Pago de Recibo</title>
    </head>
    <body>
        <div class="main">
            <div class="main_resize">
                <%@include file="fragmentos/cabecera.jspf" %>
                <div class="main_center">
                    <form action="FrontController" method="post">
                    <h2>Pago de Recibos</h2>
                    <h3>Datos del Recibo</h3>
                    <label>
                    Numero de recibo: 
                    <input name="numrecibo" type="text" disabled="" value="recibo.numero" /><br/>
                    Categoria: 
                    <input name="categoria" type="text" disabled="" value="recibo.categoria.nombre" /><br/>
                    Importe Total: <input name="importe" type="text" disabled="" value="recibo.importe" /><br/>
                    <input name="id" type="hidden" value="recibo.id" /><br/>
                    <h3>Datos de Pago</h3>
                    Numero de Tarjeta: <input name="numtarjeta" type="text" /><br/>
                    Nombre del Titular: <input name="titular" type="text" /><br/>
                    Fecha de Caducidad de Tarjeta: <input name="fechacad" type="text" /><br/>
                    Numero de Seguridad: <input name="fechacad" type="text" /><br/>
                    </label>
                        
                        <input type="hidden" name="action" value="pay_recibo"/>
                        <input type="submit" value="Realizar Pago"/>
                    </form>

                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
