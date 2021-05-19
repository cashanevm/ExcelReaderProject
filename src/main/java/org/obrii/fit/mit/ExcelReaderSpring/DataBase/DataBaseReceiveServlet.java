/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.ExcelReaderSpring.DataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.obrii.fit.mit.ExcelReaderSpring.Table.Table;

/**
 *
 * @author NEVM PC
 */
@WebServlet(name = "DataBaseReceive", urlPatterns = {"/Receive"})
public class DataBaseReceiveServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


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
            

                
             HttpSession session = request.getSession();
                Table tableInfo = (Table) session.getAttribute("tableInfo");     
        
        try {
                DataStore dataTables = new DataStore();
                dataTables.addTable(tableInfo);
//request.setAttribute("data",data.getData() );
                // request.getRequestDispatcher("home.jsp").forward(request, response); 
            } 
            catch (SQLException ex) {
                //request.setAttribute("data", new ArrayList<Data>());
                //request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        //Table table = new Table();
        //table = (Table) request.getAttribute("tableInfoMain");
           
       
        request.setAttribute("nameFile", tableInfo.getName());
        getServletContext().getRequestDispatcher("/Pages/LoadingResult.jsp").forward(request, response);
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
