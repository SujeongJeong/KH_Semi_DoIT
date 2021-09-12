<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 비밀번호 변경</title>
</head>
<style>
	.content {
		width: 500px;
		height: 500px;
		margin: 0 auto;
	}
	h1 {
		text-align: center;
	}
	#changePwdForm {
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
</style>
<body>
	<div class="content">
		<h1>비밀번호 변경</h1>
		<form id="changePwdForm" action="<%= request.getContextPath() %>/my/changePwd"
		method="post" onsubmit="return validate();">
			<div class="input_pwd">
				<div class="pwd_box">
					<p>현재 비밀번호</p>
				<span class="pwdresult">pwdresult</span>
				</div>
				<span class="input_area">
					<input type="password" name="userPwd" required>
				</span>
			</div>
			<div class="input_pwd">
				<div class="pwd_box">
					<p>새로운 비밀번호</p>
				<span class="pwdresult">pwdresult</span>
				</div>
				<span class="input_area">
					<input type="password" name="userPwd" required>
				</span>
			</div>
			<div class="input_pwd">
				<div class="pwd_box">
					<p>새로운 비밀번호 확인</p>
					<span class="pwdresult">pwdresult</span>
				</div>
				<span class="input_area">
					<input type="password" name="userPwd" required>
				</span>
			</div>
			<div class="btn_area">
				<button id="change_btn">변경</button>
				<button id="close_btn" onclick="window.close()">취소</button>
			</div>
		</form>
	</div>
</body>
</html>