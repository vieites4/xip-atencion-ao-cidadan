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
                    <h2>Resolvo tarefa</h2>
                    <%@include file="fragmentos/messages.jspf" %>
                     <c:forEach var="t" items="${listTarefas}">
                     </tr>    
                        <td><a href="FrontController?action=view_tarea&id=${t.id}">Ver</a></td>
              

                     </tr>
                     </c:forEach>
                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
