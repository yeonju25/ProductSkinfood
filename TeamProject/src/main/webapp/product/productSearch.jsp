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
	<h3>' ${searchName} '의 검색 결과 : 총 ${searchCount}건</h3><br><br><br>
	<div class="container">
        <c:forEach var="product" items="${searchList}">
            <div class="item">
	            <a href="main?command=product_view&num=${product.code}"><img class="product_img" src="${product.image}"></a>
	            <a href="main?command=product_view&num=${product.code}"><span>${product.name}</span></a><br><br>
	            <span class="sale_price"><fmt:formatNumber value="${product.price}" type="currency"/></span>
            </div>
   		</c:forEach>
	</div>
	<div class="item_page"><br><br><br>
			<c:forEach var="num" begin="${pageVO.startPage}" end="${pageVO.endPage}">
	   			<div><a href="main?command=product_search&search_product=${searchName}&pageNum=${num}&amount=${pageVO.amount}">${num}</a></div>
			</c:forEach>
	</div>
</div>
</body>
</html>