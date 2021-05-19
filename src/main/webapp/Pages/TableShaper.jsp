<%-- 
    Document   : TableShaper
    Created on : 24.03.2021, 16:19:05
    Author     : NEVM PC
--%>

<%@page import="org.obrii.fit.mit.ExcelReaderSpring.Table.Table"%>
<%@page import="org.obrii.fit.mit.ExcelReaderSpring.FileReader.FileName"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=FileName.getFileName()%></title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/PagesRes/TableDisplay/style/normalize.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/PagesRes/TableDisplay/style/style.css">
        <style>
  table {
font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
font-size: 14px;
border-collapse: collapse;
text-align: center;
}
th, td:first-child {
background: #AFCDE7;
color: white;
padding: 10px 20px;
}
th, td {
border-style: solid;
border-width: 0 1px 1px 0;
border-color: white;
}
td {
background: #D8E6F3;
}
th:first-child, td:first-child {
text-align: left;
}
  </style>
        
        
    </head>
    <body>
        
        
        <%
   
    
       
 
       
       
   String data = (String) request.getAttribute("table");
   
   %>
       
       <div class="wrapper">
        <div class="container">
            <section class="title">Процес 1 завершено <span>успішно.</span><%=FileName.getFileName()%></section>
            <section class="tables__fields-wrapper">
                <div class="tables__fields">
                    <%=data%>
                </div>
                <div class="edit__tables-field">
                    <form action="<%=request.getContextPath()%>/new" method="get">
                        <input type="submit" value="" class="btn-table__edit" id="btn-edit">
                    </form>
                </div>
            </section>
            <section class="gohome__btn">

                <form action="<%=request.getContextPath()%>/Receive" method="get" class="home__page-link">
                    <input type="submit" value="Надіслати" class="home__page-wrapper">
                </form>
                <form action="<%=request.getContextPath()%>/Pages/LoadingFile.jsp" class="home__page-link">
                    <input type="submit" value="Завантажте інший файл" class="home__page-wrapper">
                </form>
                    <form action="<%=request.getContextPath()%>/post" method="post" class="home__page-link">
                    <input type="submit" value="send" class="home__page-wrapper">
                </form>
            </section>
        </div>
    </div>
    <script src="./js/script.js"></script>
</body>
</html>
