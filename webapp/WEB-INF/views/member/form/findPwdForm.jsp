<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
	.content {
		width: 500px;
		height: 500px;
		margin: 0 auto;
		padding: 50px 0;
	}
	.logo_area {
		display: block;
		text-align: center;
	}
	.logo {
		width: 300px;
	}
	#findPwdForm {
		padding: 20px 100px;
	}
	h1 {
		text-align: center;
	}
	.input_area {
	    border: solid 1px #dadada;
	    padding : 10px;
	    background : white;
	}
	
	.input_area input{
		width : 200px;
		height : 20px;
		border: 0px;
	}
	.find_btn {
		width: 50px;
		background: #5FC5FF;
		color: white;
		border: 0;
	}
	textarea {
		width: 100%;
		height: 100px;
		resize: none;
		margin-bottom: 10px;
	}
	#close_btn {
		width: 100px;
		height: 30px;
		background: lightgray;
		border: 0;
	}
	.close {
		text-align: center;
	}
	.find_area {
		display: flex;
		justify-content: space-between;
		margin-bottom: 10px;
	}
</style>
</head>
<body>
	<div class="content">
		<div class="logo_area" onclick="window.close();"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo"></div>
		<h1>비밀번호 찾기</h1>
		<form id="findPwdForm" action="<%= request.getContextPath() %>/findPwd"
		method="post" onsubmit="return checkEmail();">
			<p>이메일</p>
			<div class="find_area">
				<span class="input_area">
					<input type="email" name="userEmail" id="userEmail">
				</span>
				<button class="find_btn">조회</button>
			</div>
			<textarea rows="5" cols="5" readonly></textarea>
			<div class="btn_area close"><button id="close_btn" onclick="window.close()">확인</button></div>
		</form>
	</div>
	<script>
		document.getElementById("close").onclick = function(){
			window.close();
		}
		
		document.getElementById("findPwdBtn").onclick = function checkEmail() {
			const userEmail = document.getElementById("userEmail");
			
			if(!userEmail.value.trim().length){
				userEmail.focus();
				return false;
			}
			return true;
		};
	</script>
</body>
</html>