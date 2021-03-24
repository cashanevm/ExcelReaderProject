<%-- 
    Document   : TableShaper
    Created on : 24.03.2021, 16:19:05
    Author     : NEVM PC
--%>

<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
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
        <h1>Hello World!</h1>
        
        <table>
        
          <%
   
    
        List<String> dataList = (List<String>) request.getAttribute("arrayFile");
for(String data:dataList) {
                
    
    %><tr><%=data%>
            
                
                
             </tr><%     
            }
       
       
       
       
   
   
   %>
        </table>
        
        
        
    </body>
</html>
