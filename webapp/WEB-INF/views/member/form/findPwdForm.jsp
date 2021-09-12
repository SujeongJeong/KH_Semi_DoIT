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
		width : 250px;
		height : 30px;
		border: 0px;
	}
	.btn_area {
		width: 100%;
		height: 50px;
		margin: 20px 0;
	}
	.btn_area > button {
		width: 100%;
		height: 100%;
		border: 0;
		background: #5FC5FF;
		color:white;
	}
	textarea {
		width: 100%;
		height: 100px;
		resize: none;
	}
	#close_btn {
		width: 100px;
		height: 30px;
		background: lightgray;
	}
	.close {
		text-align: center;
	}
</style>
</head>
<body>
	<div class="content">
		<h1>비밀번호 찾기</h1>
		<form id="findPwdForm" action="<%= request.getContextPath() %>/findPwd"
		method="post" onsubmit="return checkEmail();">
			<p>이메일</p>
			<span class="input_area">
				<input type="email" name="userEmail" id="userEmail">
			</span>
			<div class="btn_area"><button>비밀번호 찾기</button></div>
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