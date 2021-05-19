/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.DataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.obrii.fit.mit.NevmProject.ExcelReader.ReadExcelClass;

/**
 *
 * @author NEVM PC
 */
@WebServlet(name = "TableServlet", urlPatterns = {"/Table"})
public class TableServlet extends HttpServlet {
ReadExcelClass exel = new ReadExcelClass();
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
                
        DataStore dataTables = null;
        try {
            dataTables = new DataStore();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDisplayTablesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            request.setAttribute("table", exel.tableFormater(dataTables.GetTableFromBase(Integer.parseInt(dataTables.GetTablesId(request.getParameter("tableName")))),false)); 
            
        } catch (SQLException ex) {
            Logger.getLogger(TableServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           
        getServletContext().getRequestDispatcher("/Pages/TableShaper_2.jsp").forward(request, response);  
        
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
