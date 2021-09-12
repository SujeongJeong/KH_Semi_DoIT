<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 회원탈퇴</title>
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
	.text_area {
		width: 400px;
		height: 200px;
		background: white;
		overflow-x: scroll; 
 		overflow-y: scroll;
	}
	#withdrawalForm {
		padding: 20px 50px;
	}
	.text_area {
		border: 1px solid black;
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
	#withdrawal_btn {
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
</style>
<body>
	<div class="content">
		<h1>회원 탈퇴</h1>
		<form id="withdrawalForm" action="<%= request.getContextPath() %>/my/withdrawal"
		method="post" onsubmit="return validate();">
			<h3>탈퇴 안내</h3>
			<pre class="text_area">
Do IT에 가입된 계정의 탈퇴를 원하시나요?
탈퇴하게되면 그 동안 Do IT에 쌓여있던 공부시간과 생성되어있는 스터디방 등 모든 정보가 삭제됩니다.
한번 탈퇴가 진행되면 복구는 절대 불가능하기 때문에 신중하게 진행해주셔야합니다.
			</pre>
			<input type="checkbox" name="agreement" id="agreement">
			<label for="agreement">안내 사항을 모두 확인하였으며, 이에 동의합니다.</label>
			<p>현재 비밀번호 입력</p>
			<span class="input_area">
				<input type="password" name="userPwd" required>
			</span>
			<div class="btn_area">
				<button id="withdrawal_btn">탈퇴</button>
				<button id="close_btn" onclick="window.close()">취소</button>
			</div>
		</form>
	</div>
	
</body>
</html>