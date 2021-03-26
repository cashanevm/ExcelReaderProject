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
        <title>DataBase Operations</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/PagesRes/MainPage/css/style.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    </head>
    <body>
    <div class="mainPage">
        <div class="smallCBox"><img class="smallC" src="../img/smallCirc.png" alt=""></div>
        <div class="container">
            <div class="contentResult">
                <h1>Ви успішно зберегли файл <span> "<%=
        
                    request.getAttribute("nameFile")
                    
                    %>" </span>у базі даних.</h1>
                    <form action="<%=request.getContextPath()%>/">
                    <input type="submit" value="На головну">
                </form>
            </div>
        </div>
        <img class="bigCRes" src="../img/bigCirc.png" alt="">
    </div>
</body>
</html>
