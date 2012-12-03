<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/principal.css">

        <script type="text/javascript" src="js/jspdf/libs/base64.js"></script>
        <script type="text/javascript" src="js/jspdf/libs/sprintf.js"></script>
        <script type="text/javascript" src="js/jspdf/jspdf.js"></script>

        <script type="text/javascript">
    
            function generarPDF() {
        
                var doc = new jsPDF();
                doc.setFontSize(22);
                doc.text(20, 20, 'This is a title');
                doc.setFontSize(16);
                doc.text(20, 30, 'This is some normal sized text underneath.');	
                doc.output('datauri');
            }

        </script>

        <title>Atención ao cidadán</title>
    </head>



    <a href="javascript:generarPDF()">Generar PDF</a>

</body>
</html>