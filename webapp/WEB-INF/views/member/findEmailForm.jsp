<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 찾기</title>
<style>
	.outer{
		width:90%;
		min-width : 450px;
		margin:auto;
	}
	
	.outer h1 {
		text-align:center;
	}
	
	#findEmailForm {
		width : 300px;
		margin:auto;
	}
	
	.input_area {
	    border: solid 1px #dadada;
	    padding : 10px 10px 14px 10px;
	    background : white;
	}
	
	.input_area input{
		width : 250px;
		height : 30px;
		border: 0px;
	}
	
	.btnArea {
		width : 250px;
		height : 30px;
		padding : 0px 10px 14px 10px;
		margin: 10px 0 10px 0;
	}
	
	button:hover {
		cursor:pointer
	}
	
	button {
		width : 250px;
		height : 35px;
		border : 0px;
		color:white;
		background:#C8EBFF;
		margin : 10px;
		padding : 10px 10px 14px 10px;
		text-align: center;
	}
	
	textarea {
		resize: none;
		width : 270px;
		height : 200px;
	}
</style>
</head>
<body>
	<div class="outer">
	<h1>이메일 찾기</h1>
	
	<form id="findEmailForm" action="<%= request.getContextPath() %>/findEmail"
	method="post" onsubmit="return checkEmail();">
				<h4>이메일 입력</h4>
				<span class="input_area">
					<input type="email" name="userEmail" id="userEmail">
				</span>
				<div class="btnArea">
					<button id="findEmailBtn">이메일  찾기</button>
				</div>
				<textarea rows="5" cols="5" readonly></textarea>
				<div class="btnArea">
					<button id="close">확인</button>
				</div>
			
	</form>
	</div>
	
	<script>
		document.getElementById("close").onclick = function(){
			window.close();
		}
		
		document.getElementById("findEmailBtn").onclick = function checkEmail() {
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