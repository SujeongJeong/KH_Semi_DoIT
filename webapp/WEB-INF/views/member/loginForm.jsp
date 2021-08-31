<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 - Do IT</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
.content {
	width: 40%;
	min-width: 400px;
	height: 600px;
	margin: 70px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.loginArea h1 {
	text-align: center;
	margin-bottom: 30px;
}

.input_area {
	border: solid 1px #dadada;
	padding: 10px 10px 14px 10px;
	background: white;
}

.input_area input {
	width: 300px;
	height: 30px;
	border: 0px;
}

.input_area input[type=submit] {
	color:white;
	background:#C8EBFF;
}

.login_btn {
	background:#C8EBFF; 
	border: 0px;
	margin-bottom: 10px;
}

.logo {
	margin-bottom: 20px;
}

.email_box {
	display: flex;
	justify-content: space-between;
}
#emailresult {
	color: red;
}

.email_box h4 {
	display: inline;
	margin: 0;
}

.fun_box * {
	margin-top: 10px;
}

.fun_box {
	display: flex;
	justify-content: space-between;
}

#findEmailBtn, #findPwdBtn, #joinBtn {
	border: 0;
	outline: 0;
	background: white;
}

.find_Btns  {
	float: left;
}

.join_box {
	float: right;
}

button[id$=Btn]:hover {
	text-decoration: underline;
}

button[id^=easy] {
	display: block; 
}

.easy_btn {
	border: solid 1px #dadada;
	padding: 10px 10px 14px 10px;
	text-align: center;
	margin: 10px 0;
}

.easy_btn:last-child {
	background: #F7E600;
}

button[id^=easy] {
	width: 300px;
	height: 30px;
	border: 0px;
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
	background: #F7E600;
	color: #3A1D1D;
}

</style>
</head>
<body>
	<div class="wrapper">
		<div class="content">
			<form class="loginArea" action="<%= request.getContextPath() %>/login" method="post" onsubmit="return validate();">
				<a href="/Do_IT"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo"></a><br>
				<div class="email_box">
					<h4>이메일</h4>
					<span id="emailresult"></span>
				</div><br>
				<span class="input_area"><input type="email" name="userEmail" id="userEmail" placeholder="이메일을 입력하세요"></span>
				<h4>비밀번호</h4>
				<span class="input_area"><input type="password" name="userPwd" id="userPwd" placeholder="비밀번호를 입력하세요"></span><br>
				<div class="fun_box">
					<div class="find_Btns">
						<button id="findEmailBtn" type="button" onclick="openPopup('<%= request.getContextPath() %>/findEmail', 'findEmail', 500, 500);">이메일 찾기</button>|<button id="findPwdBtn" type="button" onclick="openPopup('<%= request.getContextPath() %>/findPwd', 'findPwd', 500, 500);">비밀번호 찾기</button>
					</div>
					<div id="join_box"><button id="joinBtn" onclick="location.href ='<%= request.getContextPath() %>/memberJoin';">회원가입</button ></div>
				</div>
				<h5><input type="checkbox" name="remember" id="remember"><label for="remember">이메일 기억하기</label></h5>
				<span class="input_area login_btn"><input type="submit" value="로그인"></span><br><br>
				<hr>
				<div class="easy_btn"><button id="easy_google">google</button></div>
				<div class="easy_btn" style="background: #2BD400"><button id="easy_naver">naver</button></div>
				<div class="easy_btn"><button id="easy_kakao">kakao</button></div>
			</form>
		</div>
	</div>
	
	<script>
		let userEmail= document.getElementById('userEmail');
		let userPwd= document.getElementById('userPwd');
		let emailresult = document.getElementById('emailresult');
	
		userEmail.onchange = function() {
	        if(!this.value.includes('@')) { // @ 유무로 유효한 이메일 주소 인지 체크
	            emailresult.innerHTML = '올바른 이메일 주소를 입력하세요';
	            // emailresult는 span 태그 이기때문에 innerHTML로 값 설정
	        } else {
	        	emailresult.innerHTML = '';
	        }
	    }
		
		// 아이디와 비말번호가 공란이 아닐때만 submit 할 수 있도록 확인
		function validate(){
			/* let userEmail= document.getElementById('userEmail');
			let userPwd= document.getElementById('userPwd');
			let emailresult = document.getElementById('emailresult'); */
			// if 조건이 t일 때 조건문 실행
			// userId.value.trim().length = 0 이면 f 이므로
			// 조건은 부정을 해야한다
			
			if(!userEmail.value.trim().length){
				userEmail.focus();
				return false;
			}
			
			if(!userPwd.value.trim().length){
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
	<script src="<%= request.getContextPath() %>/resources/js/rememberUserId.js"></script>
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>