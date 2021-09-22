<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 비밀번호 변경</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<%
	if(request.getAttribute("result") != null) {
		if(request.getAttribute("result").equals("success")) {
%>
<script>
	alert('성공적으로 비밀번호를 변경하였습니다.');
	window.close();
</script>
<%
	} else {
%>
<script>
	alert('비밀번호 변경에 실패하셨습니다. 비밀번호를 확인해주세요.');
</script>
<%
		}
	}
%>
<style>
	.content {
		width: 500px;
		height: 500px;
		margin: 0 auto;
		padding: 50px 0;
	}
	button {
		border : 0; 
		border-radius : 5px;
	}
	h1 {
		text-align: center;
	}
	#ModifyPwdForm {
		padding: 20px 100px;
	}
	.input_area {
		width : 300px;
		height : 30px;
	    border: solid 1px #dadada;
	    padding : 10px;
	    background : white;
	}
	
	.input_area input{
		width: 280px;
		height : 30px;
		border: 0px;
	}
	.btn_area {
		width: 100%;
		height: 50px;
		margin: 20px 0;
		text-align: center;
	}
	#change_btn {
		width: 100px;
		height: 40px;
		background: #5FC5FF;
		color: white;
	}
	#close_btn {
		width: 100px;
		height: 40px;
		background: lightgray;
	}
	.pwd_box {
		display: flex;
		justify-content: space-between;
	}
	.pwdresult {
		padding-top: 10px;
		color: red;
	}
	.logo_area {
		display: block;
		text-align: center;
	}
</style>
<body>
	<div class="content">
		<div class="logo_area" onclick="window.close();"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo"></div>
		<h1>비밀번호 변경</h1>
		<form id="ModifyPwdForm" action="<%= request.getContextPath() %>/my/modifyPwd"
		method="post" onsubmit="return validate();">
			<div class="input_pwd">
				<p>현재 비밀번호</p>
				<span class="input_area">
					<input type="password" name="userPwd" id="userPwd" required>
				</span>
			</div>
			<div class="input_pwd">
				<p>새로운 비밀번호</p>
				<span class="input_area">
					<input type="password" name="newPwd" id="newPwd" required>
				</span>
			</div>
			<div class="input_pwd">
				<p>새로운 비밀번호 확인</p>	
				<span class="input_area">
					<input type="password" name="newPwd2" id="newPwd2" required>
				</span>
			</div>
			<div class="btn_area">
				<button id="change_btn">변경</button>
				<button id="close_btn" onclick="window.close()">취소</button>
			</div>
		</form>
	</div>
</body>
<script>
	function validate() {
		let regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
		
		// 새로운 비밀번호가 형식에 맞지않을때
		if(!regExp.test($('#newPwd').val())) {
			alert("비밀번호는 8~16 자 영문 대 소문자,숫자,특수문자를 모두 포함하여 입력하세요.");
			return false;
		}
		
		// 새로운 비밀번호와 새로운 비밀번호 확인이 일치하지 않으면
		if($("#newPwd").val() != $("#newPwd2").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#newPwd2").focus();
			return false;
		}
		
		return true;
	}
</script>
</html>