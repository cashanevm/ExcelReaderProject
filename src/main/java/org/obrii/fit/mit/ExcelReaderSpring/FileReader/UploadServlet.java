/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.ExcelReaderSpring.FileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;


/**
 *
 * @author Дмитрий
 */
@WebServlet(name = "upload", urlPatterns = {"/upload"})
//@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

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
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *\
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{File file = new File("file");
            if(file.delete()){
                System.out.println("file файл был удален с корневой папки проекта");
            }else System.out.println("Файл file.txt не был найден в корневой папке проекта");        
        }catch(Exception ex){        
        }                                
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List <FileItem> multi = fileUpload.parseRequest(request);
            for(FileItem fileItem : multi){
                fileItem.getFieldName();
            fileItem.write(new File(fileItem.getFieldName()));
            System.out.println(fileItem.getName());
            FileName.setFileName(fileItem.getName());
            //request.setAttribute("filedesc", fileItem.getInputStream());//Тело файла(содержимое)
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }                                                                                        
        //request.setAttribute("namefile", FileHandler.getNameOfFile());//Название файла         
        getServletContext().getRequestDispatcher("/new").forward(request, response);
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
