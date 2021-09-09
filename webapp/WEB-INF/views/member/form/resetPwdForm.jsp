<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
	.content{
		width:40%;
		min-width : 400px;
		margin:auto;
		margin-top : 70px;
		margin-bottom : 70px;
	}
	
	#resetPwdForm {
		width : 300px;
		margin: auto;
		padding: 10px;
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
		background:#5FC5FF;
	}
	
	#joinBtn {
		width : 300px;
		height : 30px;
		border : 0px;
		color:white;
		background:#5FC5FF;
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

</style>
</head>
<body>
	<content class="content">
		<div id="resetInfoArea">
			<form id="resetPwdForm" action="<%= request.getContextPath() %>/resetPwd"
			method="post" onsubmit="return validate();">
				<a href="/Do_IT"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo"></a><br>
				<div class="pwd_box">
					<h4>새로운 비밀번호</h4>
					<span id="pwdResult"></span>
				</div>
				<span class="input_area"><input type="password" minlength="8" maxlength="15" name="newPwd" required></span><br>
				
				<div class="pwd2_box">
					<h4>새로운 비밀번호 확인</h4>
					<span id="pwd2Result"></span>
				</div>
				<span class="input_area"><input type="password" maxlength="15" name="newPwd2" required></span><br>
			
				<span class="input_area joinBtnArea"><button id="joinBtn">비밀번호 재설정</button></span>
			</form>
		</div>
	</content>
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	<script>
		let pwdResult = document.getElementById('pwdResult');
		let pwd2Result = document.getElementById('pwd2Result');
	
		pwdResult.innerHTML = 'pwdResult';
		pwd2Result.innerHTML = 'pwd2Result';
		
	
		// 사용자 입력 값 유효성 검사
		function vaildate(){
			return true;	
		}
	</script>
</body>
</html>