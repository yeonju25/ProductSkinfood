<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
<link rel="stylesheet" href="product/css/style.css">
<script type="text/javascript" src="product/script/product.js"></script>
</head>
<body>
<div align="center">
	<div class="category">
        <ul><br>
         <li>&nbsp;&nbsp; HOME </li>
         <li> > <a href="main?command=product_list">상품</a> </li>
         <li> > 상품 상세 &nbsp;&nbsp;</li>
        </ul>
    </div>
    <div class="detail_product">
    	<br><br><br>
		<table class="detail_table">
			<tr>
				<td rowspan="9"><img class="detail_img" src="${product.image}"></td>
				<td class="detail_name" colspan="2"><h2>${product.name}</h2></td>
			</tr>
			<tr><td style="height:50px" colspan="2" class="underline"><h2><fmt:formatNumber value="${product.price}" type="currency"/></h2></td></tr>
			<tr>
				<th>관련태그</th>
				<td>${product.tag}</td>			
			</tr>
			<tr>
				<th>배송비</th>
				<td>
					20,000원 이상 주문 시 무료배송입니다.<br>
					20,000원 미만 주문 시 배송료 2500원이 추가됩니다.
				</td>			
			</tr>
			<tr>
				<th>리뷰</th>
				<td>( ${product.review} )건</td>			
			</tr>
			<tr>
				<td colspan="2" class="underline"></td>
			</tr>
			<tr>
				<td colspan="2" class="underline"><h3>
					${product.name}</h3><br>
					<form class="amount_wrap" name="frm">
						<input type="hidden" name="sell_price" value="${product.price}">
					    <button type="button" onclick="minus()">-</button>
					    <input type="text" name="amount" value="1" size="3" readonly="readonly"/>
					    <button type="button" onclick="plus()">+</button>
					</form>
					
				</td>
			</tr>			
			<tr>
				<th>합계</th>
				<td>
					<form name="frm_sum" class="sell_price_won">
						<input class="sell_price" type="text" name="sum" value="${product.price}" readonly="readonly">원
					</form>
				</td>
			</tr>	
			<tr>
				<td colspan="2">
					<div class="detail_buy">
						<div class="cart_add">장바구니 담기</div>
						<div class="buy_now" onclick="location.href='main?command=product_buy&num=${product.code}'">바로 구매</div>
			      	</div>
		      	</td>
			</tr>	
		</table>
	</div> 
</div>
</body>
</html>