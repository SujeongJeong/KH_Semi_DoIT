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
		max-width:100%; 
		max-height:100%;
		margin: 0 auto;
		padding: 50px 0;
	}
	.logo_area {
		display: block;
		text-align: center;
	}
	.logo {
		width: 200px;
	}
	#findPwdForm {
		width:300px;
		height: 460px;
		padding: 20px 100px;
		margin: 0 auto;
	}
	h4 {
		margin: 10px 0;
	}
	h3 {
		text-align: center;
	}
	.input_area {
	    border: solid 1px #dadada;
	    padding : 10px;
	    background : white;
	     border-radius : 5px;
	}
	
	.input_area input{
		width : 200px;
		height : 20px;
		border: 0px;
	}
	#find_btn {
		width: 60px;
		background: #5FC5FF;
		color: white;
		border: 0;
		border-radius : 5px;
	}
	textarea {
		width: 100%;
		height: 100px;
		resize: none;
		margin-bottom: 10px;
		color: red;
	}
	#close_btn {
		width: 100px;
		height: 30px;
		background: lightgray;
		border: 0;
		border-radius : 5px;
		padding: 5px 0;
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
		<h3>비밀번호 찾기</h3>
		<form id="findPwdForm" action="<%= request.getContextPath() %>/findPwd"
		method="post" onsubmit="return checkEmail();">
			<h4>이메일<h4>
			<div class="find_area">
				<span class="input_area">
					<input type="email" name="userEmail" id="userEmail" value="${ userEmail }"> 
				</span>
				<button id="find_btn">조회</button>
			</div>
			<textarea rows="5" cols="5" readonly>${ msg }</textarea>
			<div class="close"><button id="close_btn" type="button" onclick="window.close()">닫기</button></div>
		</form>
	</div>
	<script>
		document.getElementById("find_btn").onclick = function checkEmail() {
			const userEmail = document.getElementById("userEmail");
			
			if(!userEmail.value.trim().length){
				alert("이메일을 입력해주세요.");
				userEmail.focus();
				return false;
			}
			return true;
		};
	</script>
</body>
</html>