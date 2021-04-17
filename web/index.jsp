<%-- 
    Document   : indicex
    Created on : 17-abr-2021, 5:40:36
    Author     : hye
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Productos"%>
<%@page import="com.emergentes.modelo.GestorProductos"%>
<%
    if(session.getAttribute("agenda") == null){
        GestorProductos objeto1 = new GestorProductos();
        
        objeto1.insertarProductos (new Productos(1 , "Coca Cola","12 ","5"));
      objeto1.insertarProductos(new Productos(2 , "Pepsi","11","3"));
      objeto1.insertarProductos(new Productos(3 , "Frack","5,5","12"));
       objeto1.insertarProductos(new Productos(3 , "Serranitos","3,5","12"));
        session.setAttribute("agenda", objeto1);
    }
    %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>Lista de Productos </h1>
         
        <a href="Controller?op=nuevo">Nuevo</a>
        <table border="1">
            <tr>
                <th>Id</th>   
                <th>productos</th>  
                <th>precio</th>  
                 <th>cantidad</th>  
                <th></th>  
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.agenda.getLista()}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.producto}</td>
                    <td>${item.precio}</td>
                    <td>${item.cantidad}</td>
                    
                    <td><a href="Controller?op=modificar&id=${item.id}">Modificar</a></td>
                    <td><a href="Controller?op=eliminar&id=${item.id}">Eliminar</a></td>
                </tr>
            </c:forEach>  
            
        </table>
    </body>
</html>
