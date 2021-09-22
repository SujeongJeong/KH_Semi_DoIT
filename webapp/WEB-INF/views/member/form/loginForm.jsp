<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 - Do IT</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<% if(session.getAttribute("msg") != null) { %>
<script>
	 alert('<%= session.getAttribute("msg") %>');
</script>
<% 
		session.removeAttribute("msg");	
	} 
%>
<style>
.content {
	min-width: 400px;
	margin: 70px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}
#loginArea {
	width: 300px;
}

.loginArea h1 {
	text-align: center;
	margin-bottom: 30px;
}

.input_area {
	border: solid 1px #dadada;
	padding: 10px 10px 14px 10px;
	background: white;
	border-radius : 5px; 
	
}

.input_area input {
	width: 280px;
	height: 30px;
	border: 0px;
}

.input_area input[type=submit] {
	color:white;
	background:#5FC5FF;
	font-size: 1.2em;
}

.login_btn {
	background:#5FC5FF; 
	border: 0px;
	margin-bottom: 10px;
}

.logo {
	margin-bottom: 20px;
}

.fun_box * {
	margin-top: 10px;
}

.fun_box {
	display: flex;
	justify-content: space-between;
}

button[id$=Btn] {
	border: 0;
	outline: 0;
	background: white;
}

.find_Btns  {
	float: left;
}

button[id$=Btn]:hover {
	cursor: pointer;
}

.easy_btn {
	width: 280px;
	height: 30px;
	border: 0px;
	margin-top: 30px;
}
.naver {
	border: 0;
	outline: 0;
	background: #2BD400;
}
.kakao {
	border: 0;
	outline: 0;
	background: #FFEA00;
}
#easy_google {
	background: white;
	color: red;
}

#easy_naver {
	background: #2BD400;
	color: white;
}

#easy_kakao {
	background: #FFEA00;
	color: #3A1D1D;
}

</style>
</head>
<body>
	<div class="content">
		<form id="loginArea" action="<%= request.getContextPath() %>/login" method="post" onsubmit="return validate();">
			<a href="/Do_IT"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo"></a><br>
			<h4>이메일</h4>
			<span class="input_area"><input type="email" name="userEmail" id="userEmail" placeholder="이메일을 입력하세요"></span>
			<h4>비밀번호</h4>
			<span class="input_area"><input type="password" name="userPwd" id="userPwd" placeholder="비밀번호를 입력하세요" autocomplete="on"></span>
			<div class="fun_box">
				<div class="find_Btns">
					<button id="findEmailBtn" type="button" onclick="openPopup('<%= request.getContextPath() %>/findEmail', 'findEmail', 500, 500);">이메일 찾기</button>|<button id="findPwdBtn" type="button" onclick="openPopup('<%= request.getContextPath() %>/findPwd', 'findPwd', 500, 500);">비밀번호 찾기</button>
				</div>
				<div id="join_box"><button id="joinBtn" type="button" onclick="location.href ='<%= request.getContextPath() %>/memberJoin';">회원가입</button ></div>
			</div>
			<h5><input type="checkbox" name="remember" id="remember"><label for="remember">이메일 기억하기</label></h5>
			<span class="input_area login_btn"><input type="submit" value="로그인"></span><br><br>
			<hr>
			<span class="input_area google"><button class="easy_btn" id="easy_google" type="button">google</button></span>
			<span class="input_area naver"><button class="easy_btn" id="easy_naver" type="button">naver</button></span>
			<span class="input_area kakao"><button class="easy_btn" id="easy_kakao" type="button">kakao</button></span>
		</form>
	</div>
	
	<script>
		// 아이디와 비말번호가 공란이 아닐때만 submit 할 수 있도록 확인
		function validate(){
			let userEmail= document.getElementById('userEmail');
			let userPwd= document.getElementById('userPwd');
	
			// if 조건이 t일 때 조건문 실행
			// userId.value.trim().length = 0 이면 f 이므로
			// 조건은 부정을 해야한다
			
			if(!userEmail.value.trim().length){
				alert("이메일을 입력하세요");
				userEmail.focus();
				return false;
			}
			
			if(!userPwd.value.trim().length){
				alert("비밀번호를 입력하세요");
				userPwd.focus();
				return false;
			}
		
			return true;
		}
		
		function openPopup(url, title, width, height) {
			let left = (document.body.clientWidth/2) - (width/2);
			// 듀얼모니터를 위한 계산
			left += window.screenLeft;
			let top = (screen.availHeight/2) - (height/2);
			
			let options = "width="+width+",height="+height+",left="+left+",top="+top;
			
			window.open(url, title, options);
		}
	</script>
	
	<!-- 쿠키 사용하여 아이디 기억하기 기능 외부 파일로 작성하여 하단에 넣기 -->
	<script src="<%= request.getContextPath() %>/resources/js/rememberUserEmail.js"></script>
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
</body>
</html>