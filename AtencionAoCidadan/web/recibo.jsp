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
        
        <script type="text/javascript" src="js/jspdf/libs/base64.js"></script>
        <script type="text/javascript" src="js/jspdf/libs/sprintf.js"></script>
        <script type="text/javascript" src="js/jspdf/jspdf.js"></script>

        <script type="text/javascript">
    
            function generarPDF () {
                var doc = new jsPDF();
                doc.setFontSize(22);
                doc.text(20, 20, 'Recibo de '+ '${recibo.ciudadano.nombre}'+ ' '+'${recibo.ciudadano.apellidos}');
                doc.setFontSize(16);
                doc.text(20, 30, 'Categoría: '+ '${recibo.categoria.nombre}');
                doc.text(20, 40, 'Número de recibo: '+ '${recibo.numeroRecibo}');
                doc.text(20, 50, 'Tipo: '+ '${recibo.tipo}');
                doc.text(20, 60, 'Referencia: '+ '${recibo.referencia}');
                doc.text(20, 70, 'Importe: '+ '${recibo.importe}'+ ' euros');
                doc.text(20, 80, 'Estado: '+ '${recibo.estado}');
                doc.text(20, 90, 'Data de aprobación: '+ '${recibo.dataAprobacionFormateada}');
                doc.text(20, 100, 'Data de cobro: '+ '${recibo.dataCobroFormateada}');
                doc.text(20, 110, 'Data límite de pagamento'+ '${recibo.dataLimiteFormateada}');
  doc.output('datauri');
            }

        </script>
        <title>Atención ao cidadán</title>
    </head>
    <body>
        
        <div class="main">
            <div class="main_resize">
                <%@include file="fragmentos/cabecera.jspf" %>
                <div class="main_center">
                    <p class="breadcrumbs">
                        <c:choose>
                            <c:when test="${usuario.isAdministrativo()}">
                                <a href="FrontController?action=view_ciudadano&id=${ciudadano}">Ciudadano</a> > 
                                <a href="FrontController?action=view_recibos&ciudadano=${recibo.ciudadano.id}">Recibos</a>
                                > Recibo 
                            </c:when>
                            <c:otherwise>
                                <a href="FrontController?action=view_recibos">Recibos</a>
                                > Recibo 
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <h2>Recibo - <c:out value="${recibo.numeroRecibo}"></c:out></h2>
                    
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        <%@include file="fragmentos/reciboFields.jspf" %>
                        <input type="hidden" name="action" value="alter_recibo"/>
                        <input type="hidden" name="id" value="${recibo.id}" />
                        <%--<p><input type="submit" value="Guardar cambios"/></p>--%>
                    </form>
                    <c:if test="${recibo.estado == '0'}">
                        <br>
                        <fieldset>
                        <legend>Recibo Pendiente de Pago</legend>
                        Numero de Tarjeta: <br/>
                        <input name="numtarjeta" type="text" /><br/>
                        Nombre del Titular: <br/>
                        <input name="titular" type="text" /><br/>
                        Fecha de Caducidad de Tarjeta: <br/>
                        <input name="fechacad" type="text" /><br/>
                        Numero de Seguridad: <br/>
                        <input name="fechacad" type="text" /><br/>
                        <br><a href="FrontController?action=pay_recibo&id=${recibo.id}">Pagar Recibo</a>
                        </fieldset><br>
                    </c:if>
                    
                    <h3>Xerar PDF</h3>
                    <a href="javascript:generarPDF ()">Xerar PDF</a>
                    <h3>Domiciliar</h3>
                    <c:if test="${domiciliado}">
                        <p>Está domiciliado</p>
                    </c:if>
                    <c:if test="${!domiciliado && domiciliable}">
                        <c:set var="ver" value="false" />
                        <p>Domiciliar siguientes recibos:</p>
                        <form action="FrontController" method="post">
                        <input type="hidden" name="action" value="add_domiciliacion"/>
                        <input type="hidden" name="categoria" value="${recibo.categoria.id}"/>
                        <input type="hidden" name="referencia" value="${recibo.referencia}"/>
                        <fieldset>
                        <legend>Datos da conta bancaria</legend>
                        <%@include file="fragmentos/cuentaBancariaFields.jspf" %>
                        <p><input type="submit" value="Domiciliar"/></p>
                        </fieldset>
                    </c:if>
                    
                    <c:if test="${usuario.isAdministrativo()}">
                        <a class="enlace" href="FrontController?action=view_recibos&ciudadano=${recibo.ciudadano.id}">Volver al listado de recibos y autoliquidaciones</a>
 
                    </c:if>
                </div>
                <div class="clr"></div>
            </div>

        <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>