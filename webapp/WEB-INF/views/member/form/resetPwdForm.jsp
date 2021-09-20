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
	}
	
	#resetPwdForm h1 {
		text-align: center;
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
</style>
</head>
<body>
	<div class="content">
		<div id="resetInfoArea">
			<form id="resetPwdForm" action="<%= request.getContextPath() %>/resetPwd" method="post" onsubmit="return validate();">
				<a href="/Do_IT"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo"></a><br>
					<h1>비밀번호 재설정</h1>
					<h4>새로운 비밀번호</h4>
					<span class="input_area"><input type="password" minlength="8" maxlength="15" name="newPwd" required></span><br>
				
					<h4>새로운 비밀번호 확인</h4>
					<span class="input_area"><input type="password" maxlength="15" name="newPwd2" required></span><br>
			
					<span class="input_area joinBtnArea"><button id="joinBtn">비밀번호 재설정</button></span>
			</form>
		</div>
	</div>
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	<script>
		// 사용자 입력 값 유효성 검사
		function vaildate(){
			return true;	
		}
	</script>
</body>
</html>