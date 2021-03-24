/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.mit.dp2021.ExcelReader;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.obrii.mit.dp2021.FileReader.FileName;
import org.obrii.mit.dp2021.ExcelReader.ReadExcelClass;
//import manilo.files.grouptasktest.FileName;
//import org.obrii.mit.dp2021.readmerged.ReadExcelClass;
/**
 *
 * @author NEVM PC
 */
@WebServlet(name = "new", urlPatterns = {"/new"})
public class TableServletFormater extends HttpServlet {
 
    
    
 
 /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        //File filePart = (File) ;
        ReadExcelClass exel = new ReadExcelClass();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>"+ FileName.getFileName() +"</title>");            
         
            out.println("<style type=\"text/css\">\n" +
"   TABLE {\n" +
"    background: #dc0; /* Цвет фона таблицы */\n" +
"    border: 5px double #000; /* Рамка вокруг таблицы */\n" +
"   }\n" +
"   TD, TH {\n" +
"    padding: 5px; /* Поля вокруг текста */\n" +
"    border: 1px solid #fff; /* Рамка вокруг ячеек */\n" +
"   }\n" +
"  </style>");
            
               out.println("</head>");
            out.println("<body>");
             out.println("<table>");
            ArrayList<String> list =  exel.getList();
             
            for (int i = 0; i < list.size(); i++) {
                 out.println("<tr>");    
                out.println(list.get(i));
             out.println("</tr>");   
            }
            exel.resetList();
            out.println("<h1>Servlet NewServlet at "+ FileName.getFileName() +"</h1>");
            out.println("</body>");
            out.println("</html>");
            
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Part filePart = request.getParamaters("filedesc");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
