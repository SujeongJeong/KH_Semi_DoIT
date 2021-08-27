<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- 외부 스타일 시트 -->
<link href="<%= request.getContextPath()%>/resources/css/loginpage-style.css" rel="stylesheet">
</head>
<body>
<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	<div class="warpper">
		<div class="outer">
			<form class="loginArea" action ="<%= request.getContextPath()%>/login" method='post' onsubmit="return validate();">
				<h1>로그인</h1>
				<h4>아이디</h4>
				<span class="input_area"><input type="text" name="userId" id="userId"
				placeholder="아이디를 입력하세요"></span>
				<h4>비밀번호</h4>
				<span class="input_area"><input type="password" name="userPwd" id="userPwd"
				placeholder="비밀번호를 입력하세요"></span>
				<h5><input type="checkbox" name="remember" id="remember">
				<label for="remember">아이디 기억하기</h5>
				<span class="input_area"><input type="submit" value="로그인"></span>
			</form>
		</div>
	</div>
	
	<script>
		// 아이디와 비말번호가 공란이 아닐때만 submit 할 수 있도록 확인
		function validate(){
			let userId= document.getElementById('userId');
			let userPwd= document.getElementById('userPwd');
			// if 조건이 t일 때 조건문 실행
			// userId.value.trim().length =0 이면 f 이므로
			// 조건은 부정을 해야한다
			if(!userId.value.trim().length){
				alert("아이디를 입력하세요");
				userId.focus();
				return false;
			}
			if(!userPwd.value.trim().length){
				alert("비밀번호를 입력하세요");
				userPwd.focus();
				return false;
			}
			
			return true;
		}
	
	</script>
	
	<!-- 쿠키 사용하여 아이디 기억하기 기능 외부 파일로 작성하여 하단에 넣기 -->
	
	<script src="<%= request.getContextPath() %>/resources/js/rememberUserId.js"></script>
</body>
</html>





















