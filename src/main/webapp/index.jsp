<%-- 
    Document   : index
    Created on : 24.03.2021, 13:51:47
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
        <h1>Hello World!</h1>
       <form action="<%=request.getContextPath()%>/Pages/LoadingFile.jsp">
         <input type="submit" value="upload file">
        </form>
        
    </body>
</html>
