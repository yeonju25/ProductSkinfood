<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
<script type="text/javascript" src="script/login.js"></script>
</head>
<body>

	<div id="login_header" style="background-color: rgb(156, 153, 204);">
        <div class="backbnt"><a href="main.jsp">뒤로가기</a></div>
        <h1 class="shopName"><a href="/">SKINFOOD</a></h1>
    </div>

    <div id="login_section" style="background-color: gray;">
        <div class="content" style="background-color: aliceblue;">
            <h3 class="title">로그인</h3>
            <form name="frm" method ="post">
                <div id="memberLogin">
                    <div class="inputBox">
                        <input type="text" name="id" placeholder="아이디" value="${id}"> <br>
                        <input type="password" name="pass" placeholder="비밀번호" value="${pass}">
                    </div>
                    <div class="loginCheckBox" style="background-color: blue;">
                        <input type="checkbox" name="save_id" id="save_id" value="on">
                        <label for="save_id">아이디저장</label>
                    </div>
                    <div class="loginBtn" style="background-color: blueviolet;">
                        <input type="submit" value="기존회원 로그인" onclick="return loginCheck()">&nbsp;&nbsp;
                        <input type="button" value="가입하기" onclick="location.href='join.do'">
                    </div>

                </div>
            </form>
        </div>
    </div>
	

</body>
</html>