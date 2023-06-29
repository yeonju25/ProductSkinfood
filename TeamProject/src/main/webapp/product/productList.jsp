<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="product/css/style.css">
<title>전체 상품</title>
</head>
<body>
<div align="center">
    <div class="category">
        <ul>
         <li>&nbsp;&nbsp; HOME </li>
         <li> > 상품 </li>
         <li> > 전체 상품 &nbsp;&nbsp;</li>
        </ul>
    </div>
	<form action="./main">
		<input type="hidden" name="command" value="product_search">
		<img class="search_icon" src="product/image/search-icon.png">&nbsp;&nbsp;
		<input class="search_product" type="text" name="search_product">
		<hr style="width: 600px;">
	</form><br><br><br>
	<div class="container">
        <c:forEach var="product" items="${productList}">
            <div class="item">
	            <a href="main?command=product_view&num=${product.code}"><img class="product_img" src="${product.image}"></a>
	            <a href="main?command=product_view&num=${product.code}"><span>${product.name}</span></a><br><br>
	            <span class="sale_price"><fmt:formatNumber value="${product.price}" type="currency"/></span>
            </div>
   		</c:forEach>
	</div>
	<div class="item_page"><br><br><br>
			<c:forEach var="num" begin="${pageVO.startPage}" end="${pageVO.endPage}">
	   			<div><a href="main?command=product_list&pageNum=${num}">${num}</a></div>
			</c:forEach>
	</div>
</div>
</body>
</html>