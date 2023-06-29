<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="product/css/style.css">
<title>상품 구매</title>
</head>
<body>
	<div align="center">
			<h2>상품 구매</h2>
			<br>
			<br>
		<table class="buy_product">
			<tr>
				<td><img src="${product.image}">
				<td style="padding:20px"><h4>${product.name}</h4></td>			
				<td style="width:100px"><h4><fmt:formatNumber value="${product.price}" type="currency"/></h4></td>			
			</tr>
			<tr>
				<td colspan="3"><strong>구매자 정보</strong></td>
			</tr>
			<tr>
				<th> 주문자 성명 </th> 		
				<td colspan="2"><input type="text" size="30"></td>			
			</tr>
			<tr>
				<th> 주 소 </th> 
				<td colspan="2"><input type="text" size="50"></td>			
			</tr>
			<tr>
				<th> 전화번호 </th> 
				<td colspan="2"><input type="text" size="30"></td>			
			</tr>
			<tr>
				<th rowspan="2"> 배송 메모 </th> 
				<td colspan="2">
					<select style="text-align:center">
						<option selected="selected">배송 전 연락 바랍니다.</option>
						<option>부재 시 문 앞에 놓아주세요.</option>
						<option>부재 시 연락 바랍니다.</option>
						<option>부재 시 경비실에 맡겨주세요.</option>
						<option>직접 입력</option>
					</select>
				</td>			
			</tr>
			<tr>
				<td colspan="2"><input type="text" size="50"></td>
			</tr>
			<tr>
				<td colspan="3"><button class="buy_btn">구매하기</button></td>
			</tr>
		</table>
		
	
	
	
	</div>
</body>
</html>