<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
	.content{
		width:40%;
		min-width : 400px;
		margin:auto;
		margin-top : 70px;
		margin-bottom : 70px;
	}
	
	#joinForm {
		width : 300px;
		margin: auto;
		padding: 10px;
	}
	
	#joinForm h4 {
		text-align:left;
	}
	
	#joinForm {
		text-align:center;
	}

	.input_area {
	    border: solid 1px #dadada;
	    padding : 10px 10px 14px 10px;
	    background : white;
	    margin-bottom: 30px;
	}
	
	.input_area input {
		width : 300px;
		height : 30px;
		border: 0px;
		margin-bottom: 15px;
	}
	
	.btnArea {
		text-align:center;
	}
	
	.joinBtnArea  {
		width : 300px;
		height : 30px;
		border : 0px;
		background:#C8EBFF;
	}
	
	#joinBtn {
		width : 300px;
		height : 30px;
		border : 0px;
		color:white;
		background:#C8EBFF;
		margin-top : 20px;
	}
	
	.logo {
	margin-bottom: 20px;
	}

	div[class$=box] {
		display: flex;
		justify-content: space-between;
		margin-bottom: 10px;
	}
	
	span[id$=Result] {
		color: red;
	}
	
	
	div[class$=box] h4 {
		display: inline;
		margin: 0;
	}
	
	div[class^=terms] {
		margin: 10px 0;
		text-align: left;
	}

</style>
</head>
<body>
	<div class="content">
		<div id="joinInfoArea">
			<form id="joinForm" action="<%= request.getContextPath() %>/memberJoin"
			method="post" onsubmit="return validate();">
				<a href="/Do_IT"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo"></a><br>
				<div class="email_box">
					<h4>이메일</h4>
					<span id="emailResult"></span>
				</div>
				<span class="input_area"><input type="email" name="userEmail" required></span><br>
				
				<div class="pwd_box">
					<h4>비밀번호</h4>
					<span id="pwdResult"></span>
				</div>
				<span class="input_area"><input type="password" minlength="8" maxlength="15" name="userPwd" required></span><br>
				
				<div class="pwd2_box">
					<h4>비밀번호 확인</h4>
					<span id="pwd2Result"></span>
				</div>
				<span class="input_area"><input type="password" maxlength="15" name="userPwd2" required></span><br>
				
				<div class="nick_box">
					<h4>닉네임</h4>
					<span id="nickResult"></span>
				</div>
				<span class="input_area"><input type="text" minlength="2" maxlength="10" name="nickName" required></span><br>
				
				<div class="terms_all"><input type="checkbox" id="termsAll" name="terms"><label for="termsAll">모두 동의</label></div>
				<div class="terms_tos"><input type="checkbox" name="terms"><a href="#" onclick="openPopup('<%= request.getContextPath() %>/tos', '서비스이용약관', 500, 500); return false">서비스 이용 약관 동의(필수)</a></div>
				<div class="terms_pp"><input type="checkbox" name="terms"><a href="#" onclick="openPopup('<%= request.getContextPath() %>/pp', '개인정보정책', 500, 500); return false">개인 정보 정책 동의(필수)</a></div>
				
				<span class="input_area joinBtnArea"><button id="joinBtn">회원가입</button></span>
			</form>
		</div>
	</div>
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	<script>
		let emailResult = document.getElementById('emailResult');
		let pwdResult = document.getElementById('pwdResult');
		let pwd2Result = document.getElementById('pwd2Result');
		let nickResult = document.getElementById('nickResult');
		
		emailResult.innerHTML = 'emailResult';
		pwdResult.innerHTML = 'pwdResult';
		pwd2Result.innerHTML = 'pwd2Result';
		nickResult.innerHTML = 'nickResult';
		
	
		// 사용자 입력 값 유효성 검사
		function vaildate(){
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

</body>
</html>









