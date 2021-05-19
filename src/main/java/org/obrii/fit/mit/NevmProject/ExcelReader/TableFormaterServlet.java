/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.ExcelReader;

//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.obrii.fit.mit.NevmProject.FileReader.FileName;
import org.obrii.fit.mit.NevmProject.ExcelReader.ReadExcelClass;
import org.obrii.fit.mit.NevmProject.Table.Table;
//import manilo.files.grouptasktest.FileName;
//import org.obrii.mit.dp2021.readmerged.ReadExcelClass;
/**
 *
 * @author NEVM PC
 */
@WebServlet(name = "new", urlPatterns = {"/new"})
public class TableFormaterServlet extends HttpServlet { 
    ReadExcelClass exel = new ReadExcelClass();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {          
                exel.setUrl(request.getContextPath());
                //System.out.println(request.getParameter("row"));
                //System.out.println(request.getParameter("pos"));
                //System.out.println(request.getParameter("value"));
                Table newtable = exel.getTable();
                String check = request.getParameter("row");
                if(check!=null){                    
                    
                    for(int i =0 ; i< newtable.getSheetList().size();i++){         
                        for(int h =0 ; h< newtable.getSheetList().get(i).getRowList().size();h++){                        
                            if(h == Integer.parseInt(request.getParameter("row"))){
                                for(int j =0 ; j< newtable.getSheetList().get(i).getRowList().get(h).getCellList().size();j++){                
                                    if(newtable.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getPossion().equals(request.getParameter("pos"))){
                                        newtable.getSheetList().get(i).getRowList().get(h).getCellList().get(j).setValue(request.getParameter("value"));                                  
                                    }
                                    //System.out.println(newtable.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getHieght());                                                    
                                }
                            }
                        }     
                        
                    }
                }
                exel.setTable(newtable);
                request.setAttribute("table", exel.tableFormater(exel.getTable(),true));
                getServletContext().getRequestDispatcher("/Pages/TableShaper_1.jsp").forward(request, response);                              
            }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
                HttpSession session = request.getSession();
                //Table tableInfo = (Table) session.getAttribute("tableInfo");      
               
                if(request.getParameter("canRead")==null){
                exel.getRead();
                }
                



                //ArrayList<String> list = new ArrayList<String>();      
                //for (int i = 0; i < exel.getList().size(); i++) {                                    
                 //   list.add(exel.getList().get(i));                      
                //}                                        
                //request.setAttribute("arrayFile", list);
                //request.setAttribute("nameFile", FileName.getFileName() );
                //exel.resetList();
                request.setAttribute("table", exel.tableFormater(exel.getTable(),false));
               System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                 session.setAttribute("tableInfo", exel.getTable());
                //request.setAttribute(, );
                getServletContext().getRequestDispatcher("/Pages/TableShaper.jsp").forward(request, response);
                // request.getRequestDispatcher("/Pages/TableShaper.jsp").forward(request, response);        
                //processRequest(request, response);
            }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
