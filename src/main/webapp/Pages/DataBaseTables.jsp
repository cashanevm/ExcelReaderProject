<%-- 
    Document   : DataBaseTables
    Created on : 05.04.2021, 21:44:41
    Author     : NEVM PC
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%
    
    
    
    %>
     
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        List<String> dataList = (List<String>) request.getAttribute("nameTables");
        %>
         <main id="home__main">
       <div id="projects__block">
        <%for(String data:dataList){%>
        
           <p><%=data%></p>
           <form action="<%=request.getContextPath()%>/Table">
               <input type="hidden" name="tableName" value="<%=data%>">  
                    <input type="submit" value="Переглянути">
                    </form>
           <%
        
            }
        
        %>
        <form action="<%=request.getContextPath()%>/" method="get" class="home__page-link">
                    <input type="submit" value="На головну" class="home__page-wrapper">
                </form>
    </body>
</html>
