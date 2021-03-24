<%-- 
    Document   : home
    Created on : 14 бер. 2021 р., 19:46:48
    Author     : Дмитрий
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/PagesRes/LoadingFile/css/style.css">
	<title>Process 1</title>
</head>

<body>
	<div class="wrapper">
		<div class="container">
			<form action = "<%=request.getContextPath()%>/upload" method="post" enctype="multipart/form-data" class="process-form">
				<fieldset class="first-process">
					<legend class="first-process__title">Процес 1</legend>
					<span class="first-process__subtitle">Будь ласка, завантажте ваш файл.</span>
					<div class="first-process__row">
						<input type="file" name="file" class="first-process__upload inputfile" id="upload"
							data-multiple-caption="{count} files selected" accept=".xls,.xlsx">
						<label class="first-process__upload-label" for="upload">
							<img class="first-process__upload-icon" src="<%=request.getContextPath()%>/PagesRes/LoadingFile/fonts/download.svg" alt="#">
							<span class="first-process__upload-file"></span>
						</label>
					</div>
					<div class="first-process__check">
						<div class="first-process__check-item">
							<span class="first-process__check-true">
								Файл унікальний</span>
						</div>
						<div class="first-process__check-item">
							<span class="first-process__check-false">
								Такий файл уже існує</span>
						</div>
					</div>
				</fieldset>
				<button class="first-process__btn" type="submit">Далі</button>
			</form>
		</div>
	</div>

	<script src="<%=request.getContextPath()%>/PagesRes/LoadingFile/js/script.js"></script>
</body>

</html>
