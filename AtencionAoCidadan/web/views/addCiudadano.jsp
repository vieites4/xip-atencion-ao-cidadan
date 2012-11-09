<%-- 
    Document   : addCiudadano
    Created on : 09-nov-2012, 11:06:15
    Author     : joseangel.pineiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2><%=session.getAttribute("top_message")%> </h2>
<div>
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
