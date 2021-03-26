<%-- 
    Document   : newjsp
    Created on : 25.03.2021, 9:45:12
    Author     : NEVM PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        
        
        
        
        
        
        
        
        
        
        
        <h1>You have successfully saved the file "<%=
        
        request.getAttribute("nameFile")
        
        %>" to the database 
        
        </h1>
        <form action="<%=request.getContextPath()%>/">
         <input type="submit" value="to the main page">
        </form>
        
    </body>
</html>
