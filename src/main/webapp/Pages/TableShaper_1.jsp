<%-- 
    Document   : TableShaper
    Created on : 24.03.2021, 16:19:05
    Author     : NEVM PC
--%>

<%@page import="org.obrii.mit.dp2021.FileReader.FileName"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=FileName.getFileName()%></title>
        
        <style>
   TABLE {
    background: #dc0; /* Цвет фона таблицы */
    border: 5px double #000; /* Рамка вокруг таблицы */
   }
   TD, TH { 
    padding: 5px; /* Поля вокруг текста */
    border: 1px solid #fff; /* Рамка вокруг ячеек */
   }
  </style>
        
        
    </head>
    <body>
        <h1><%=FileName.getFileName()%></h1>
        
        
        
          <%
   
    
       
 
       
       
   String data = (String) request.getAttribute("table");
   
   %>
       
        
        <%=data%>
        
       
        <form action="<%=request.getContextPath()%>/new" method="post">
            <input type="hidden" name="canRead" value="false">
            <input type="submit" value="save">
            
            
            
        </form>
         
    </body>
</html>
