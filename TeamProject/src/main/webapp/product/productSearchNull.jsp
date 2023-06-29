<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="product/css/style.css">
<title>상품 검색</title>
</head>
<body>
<div align="center">
    <div class="category">
        <ul>
         <li>&nbsp;&nbsp; HOME </li>
         <li> > <a href="main?command=product_list">상품</a> </li>
         <li> > 상품 검색 &nbsp;&nbsp;</li>
        </ul>
    </div>
	<form method="get" action="./main">
		<input type="hidden" name="command" value="product_search">
		<img class="search_icon" src="product/image/search-icon.png">&nbsp;&nbsp;
		<input class="search_product" type="text" name="search_product">
		<hr style="width: 600px;">
	</form><br><br><br>
	<div class="container">
       <h3>' ${searchName} '의 검색 결과가 없습니다.</h3>
	</div>
</div>
</body>
</html>