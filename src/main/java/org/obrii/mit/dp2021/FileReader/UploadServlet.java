/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.mit.dp2021.FileReader;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
       //C:\\Users\\NEVM PC\\Documents\\NetBeansProjects\\
//       String description = request.getParameter("description"); // Retrieves <input type="text" name="description"> Тоесть просто описание к файлу(не важное)
        //Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file"> Наш файл или же просто поток данных
        //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
       // InputStream fileContent = filePart.getInputStream();
        //byte[] bytes=fileName.getBytes("Cp1251");
        //String normNameFile = new String(bytes, "UTF-8");
        //FileHandler.setNameOfFile(normNameFile);
//
//        //Тест для чтения из текстового файла с кириллицей
//        final int bufferSize = 1024;
//        final char[] buffer = new char[bufferSize];
//        final StringBuilder out = new StringBuilder();
//        Reader in = new InputStreamReader(fileContent, "UTF-8");
//        for (;;) {
//            int rsz = in.read(buffer, 0, buffer.length);
//            if (rsz < 0) {
//                break;
//            }
//            out.append(buffer, 0, rsz);
//        }
//        String result = out.toString();
//
//        
//        //Отсылаем ответ на страницу с атрибутами 
//        request.setAttribute("filedesc", result);//Тело файла(содержимое)
       
       
       
       
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
