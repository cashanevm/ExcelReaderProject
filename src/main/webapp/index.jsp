<%-- 
    Document   : index
    Created on : 24.03.2021, 13:51:47
    Author     : NEVM PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataBase Operations</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/PagesRes/MainPage/css/style.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
</head>
  <body>
    <div class="mainPage">
        <div class="smallCBox">
            <img class="smallC" src="<%=request.getContextPath()%>/PagesRes/MainPage/img/smallCirc.png" alt=""></div>
        <div class="container">
            <div class="title">
                <p>Ми надаємо унікальний спектр послуг роботи та обслуговування
                    <span> баз даних.</span>
                </p>

            </div>
            <div class="blocks">
                <div class="process">
                    <h2>Процес 1</h2>
                    <p>Завантажити свій файл до бази данних. Програма надасть можливість завантажити свій Excel файл, та зберехти його в базі.</p>
                        <form action="<%=request.getContextPath()%>/Pages/LoadingFile.jsp">
                        <input type="submit" value="Обрати">
                    </form>
                </div>
                <div class="process">
                    <h2>Процес 2</h2>
                    <p>Перегляд таблиць, які добавлені в базу</p>
                    <form action="<%=request.getContextPath()%>/BaseTables">
                        <input type="submit" value="Обрати">
                    </form>
                </div>
                <div class="process">
                    <h2>Процес 3</h2>
                    <p>В розробці...</p>
                    <form action="/ExcelReaderProject/Pages/LoadingFile.jsp">
                        <input type="submit" value="Обрати">
                    </form>
                </div>
            </div>
        </div>
        <img class="bigC" src="<%=request.getContextPath()%>/PagesRes/MainPage/img/bigCirc.png" alt="">
    </div>
</body>
</html>
