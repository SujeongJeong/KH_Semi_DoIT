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
		height: 800px;
		margin:auto;
	}
	
	#joinForm {
		width : 400px;
		margin: 70px auto;
	}
	.logo {
		display: block;
		text-align: center;
	}
	
	#joinForm h4 {
		text-align:left;
	}
	.input_area {
	    border: solid 1px #dadada;
	    padding : 10px 10px 14px 10px;
	    background : white;
	    margin-bottom: 30px;
	    margin-right: 10px;
	}
	
	.input_area input {
		width : 255px;
		height : 30px;
		border: 0px;
		margin-bottom: 15px;
		margin-left: 0;
	}
	
	.btnArea {
		text-align:center;
	}
	
	.joinBtnArea  {
		width : 400px;
		border : 0px;
		background:#5FC5FF;
	}
	
	#joinBtn {
		width : 400px;
		height : 50px;
		border : 0px;
		color:white;
		background:#5FC5FF;
		margin-top : 20px;
		font-size: 1.5em;
	}
	div[class$=box] {
		display: flex;
		justify-content: space-between;
		margin-bottom: 20px;
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
	#emailCheck, #nickCheck {
		width : 100px;
		height: 30px;
		border : 0px;
		color:white;
		background:#5FC5FF;
	}

</style>
</head>
<body>
	<div class="content">
		<div id="joinInfoArea">
			<form id="joinForm" action="<%= request.getContextPath() %>/memberJoin"
			method="post" onsubmit="return validate();">
				<a href="/Do_IT" class="logo"><img src="/Do_IT/resources/images/logo.png" alt="logo"></a><br>
				<div class="email_box">
					<h4>이메일</h4>
					<span id="emailResult"></span>
				</div>
				<span class="input_area"><input type="email" name="userEmail" required></span>
				<button id="emailCheck" type="button">중복확인</button>
				
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
				<span class="input_area"><input type="text" minlength="2" maxlength="10" name="nickName" required></span>
				<button id="nickCheck" type="button">중복확인</button>
				
				<div class="terms_all"><input type="checkbox" id="termsAll" name="terms"><label for="termsAll">모두 동의</label></div>
				<div class="terms_tos"><input type="checkbox" name="terms"><a href="#" onclick="openPopup('<%= request.getContextPath() %>/tos', 'tos', 500, 500); return false">서비스 이용 약관 동의(필수)</a></div>
				<div class="terms_pp"><input type="checkbox" name="terms"><a href="#" onclick="openPopup('<%= request.getContextPath() %>/pp', 'pp', 500, 500); return false">개인 정보 정책 동의(필수)</a></div>
				
				<span class="joinBtnArea"><button id="joinBtn">회원가입</button></span>
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
		
		function validate() {
			// 비밀번호가 8 ~ 16 자리 사이이고 특수문자, 숫자, 영어만 가능
			
			
			// 비밀번호와 비밀번호 확인이 일치하지 않으면
			if($("[name=userPwd]").val() != $("[name=userPwd2]").val()) {
				pwd2Result.innerHtml = "비밀번호가 일치하지 않습니다.";
				$("[name=userPwd2]").focus();
				return false;
			}
	
			return true;
		}
		
		$("#emailCheck").click(function(){
			// input userId 변수
			var userEmail = $("[name=userEmail]");
			// 아이디 중복 시 false, 아이디 사용 가능 시 true
			var isUsable = false;
			
			if(!userEmail || !userEmail.val().contains("@") ) {
				emailResult.innerHtml = "올바른 이메일 형식을 입력해주세요."
				userEmail.focus();
			} else {
				// 4자리 이상의 userId 값을 입력 했을 경우 중복 확인을 위해 ajax 통신 요청
				$.ajax({
					url : "${ contextPath }/emailCheck",
					type : "post",
					data : { userEmail : userEmail.val() },
					success : function(result){
						console.log(result);
						if(result == "fail") {
							emailResult.innerHtml = "이미 사용중이거나 탈퇴한 이메일입니다."
							userEmail.focus();
						} else {
							if(confirm('사용 가능한 이메일입니다. 사용하시겠습니까?')) {
								// 더 이상 id 입력 공간을 수정할 수 없도록 readonly 처리
								userEmail.attr('readonly', true);
								isUsable = true; // 사용 가능한 아이디라는 flag 값
							} else {
								// confirm 창에서 취소를 누를 경우(처음 , 또는 반복해서) 다시 id 수정 가능하도록
								userEmail.attr('readonly', false);
								userEmail.focus();
								isUsable = false; // 사용 불가능한 아이디라는 flag 값
							}
						}
						// 아이디 중복 체크 후 중복이 아니며 사용하겠다고 선택한 경우에만
						// joinBtn disable 제거
						if(isUsable) {
							$("#joinBtn").removeAttr("disabled");
						} else {
							$("#joinBtn").attr("disabled", true);
						}
					},
					error : function(e){
						console.log(e);
					}
				});
			}
		});
		
		$("#nickCheck").click(function(){
			// input userId 변수
			var nickName = $("[name=nickName]");
			// 아이디 중복 시 false, 아이디 사용 가능 시 true
			var isUsable = false;
			
			if(!nickName || userId.val().length < 4 ) {
				alert('아이디는 최소 4자리 이상이어야 합니다.');
				userId.focus();
			} else {
				// 4자리 이상의 userId 값을 입력 했을 경우 중복 확인을 위해 ajax 통신 요청
				$.ajax({
					url : "${ contextPath }/nickCheck",
					type : "post",
					data : { nickName : nickName.val() },
					success : function(result){
						console.log(result);
						if(result == "fail") {
							alert("사용할 수 없는 아이디 입니다.");
							nickName.focus();
						} else {
							if(confirm('사용 가능한 아이디입니다. 사용하시겠습니까?')) {
								// 더 이상 id 입력 공간을 수정할 수 없도록 readonly 처리
								nickName.attr('readonly', true);
								isUsable = true; // 사용 가능한 아이디라는 flag 값
							} else {
								// confirm 창에서 취소를 누를 경우(처음 , 또는 반복해서) 다시 id 수정 가능하도록
								nickName.attr('readonly', false);
								nickName.focus();
								isUsable = false; // 사용 불가능한 아이디라는 flag 값
							}
						}
						// 아이디 중복 체크 후 중복이 아니며 사용하겠다고 선택한 경우에만
						// joinBtn disable 제거
						if(isUsable) {
							$("#joinBtn").removeAttr("disabled");
						} else {
							$("#joinBtn").attr("disabled", true);
						}
					},
					error : function(e){
						console.log(e);
					}
				});
			}
		});
		
		function openPopup(url, title, width, height) {
			let left = (document.body.clientWidth/2) - (width/2);
			// 듀얼모니터를 위한 계산
			left += window.screenLeft;
			let top = (screen.availHeight/2) - (height/2);
			
			let options = "width="+width+",height="+height+",left="+left+",top="+top;
			
			window.open(url, title, options);
		}
	</script>

	<!-- 쿠키 사용하여 아이디 기억하기 기능 외부 파일로 작성하여 하단에 넣기 -->
	<script src="<%= request.getContextPath() %>/resources/js/rememberUserId.js"></script>
</body>
</html>










