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
        <title>Atención ao cidadán</title>
    </head>
    <body>
        
        <div class="main">
            <div class="main_resize">
                <%@include file="fragmentos/cabecera.jspf" %>
                <div class="main_center" id="addDomiciliacion">
                    <h2>Domiciliación</h2>
                    <p>Domiciliación de un objeto tributario que devenga en recibos periódicamente.</p>
                    <%@include file="fragmentos/messages.jspf" %>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="action" value="add_domiciliacion"/>
                        <label for="categoria">Categoria: </label>
                        <select name="categoria">
                            <c:forEach var="cat" items="${listCategorias}">
                                <option value="${cat.id}"><c:out value="${cat.nombre}"></c:out></option>
                            </c:forEach>
                        </select>
                        <label for="referencia">Referencia</label>
                        <input name="referencia" type="text" ${disabled} value="${recibo.referencia}"/><br/>

                        <fieldset>
                            <legend>Conta bancaria</legend>
                            <%@include file="fragmentos/cuentaBancariaFields.jspf" %>
                        </fieldset>
                        
                        <p><input type="submit" value="Domiciliar"/></p>
                    </form>
                </div>
                <div class="clr"></div>
            </div>
        <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
