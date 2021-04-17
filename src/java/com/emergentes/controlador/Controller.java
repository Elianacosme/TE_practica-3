/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.modelo.GestorProductos;
import com.emergentes.modelo.Productos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hye
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Productos objProductos = new Productos();
        int id;
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("nuevo")){
            HttpSession ses = request.getSession();
            GestorProductos agenda = (GestorProductos) ses.getAttribute("agenda");
            objProductos.setId(agenda.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miProductos", objProductos);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorProductos agenda = (GestorProductos) ses.getAttribute("agenda");
            pos = agenda.ubicarProductos(id);
            objProductos = agenda.getLista().get(pos);
            request.setAttribute("op", op);
            request.setAttribute("miProductos", objProductos);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorProductos agenda = (GestorProductos) ses.getAttribute("agenda");
            pos = agenda.ubicarProductos (id);
            agenda.eliminarProductos (pos);
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       Productos  objProductos = new Productos ();
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("grabar")){
            //recuperar valores del formulario
            //verificar si es nuevo o es una modificación
            objProductos.setId(Integer.parseInt(request.getParameter("id")));
            objProductos.setProducto(request.getParameter("producto"));
            objProductos.setPrecio(request.getParameter("precio"));
            objProductos.setCantidad(request.getParameter("cantidad"));
            
            HttpSession ses = request.getSession();
            GestorProductos agenda = (GestorProductos ) ses.getAttribute("agenda");
            
            String opg = request.getParameter("opg");
            if(opg.equals("nuevo")){
                agenda.insertarProductos (objProductos);
            }
            else{
                pos = agenda.ubicarProductos(objProductos.getId());
                agenda.modificarProductos (pos, objProductos);
            }
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }
}
