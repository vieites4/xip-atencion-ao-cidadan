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
    
            function generarPDF1 () {
                    var doc = new jsPDF();
                doc.setFontSize(22);
                                doc.setFontSize(16);
                doc.text(20, 110, 'Data límite de pagamento');
  doc.output('datauri');
            
        }
        </script>
        <title>Atención ao cidadán</title>
    <body>
        <div class="main">
            <div class="main_resize">
                <%@include file="fragmentos/cabecera.jspf" %>
            
                <div class="main_center">
                    <h2>Resuelvo tarea</h2>
                               <c:choose>
                                     <c:when test="${tarea==null}">
                                        <a href="javascript:generarPDF1 ()">Genero PDF</a>
                                     </c:when>
                                </c:choose>
                                        
                                <c:otherwise>
                                              <c:if test="${tarea.tipo!='Solicitud de Tarxeta Aparcamento'}">
                                                            <a href="javascript:generarPDF1 ()">Genero PDF</a>
                                              </c:if>
                                </c:otherwise> 
                </div>
                <div class="clr"></div>
            </div>
            <%@include file="fragmentos/pie.jspf" %>
        </div>
    </body>
</html>
