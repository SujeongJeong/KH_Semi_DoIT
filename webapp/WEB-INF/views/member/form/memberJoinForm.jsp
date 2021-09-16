<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<% if(session.getAttribute("msg") != null) { %>
<script>
	 alert('<%= session.getAttribute("msg") %>');
</script>
<% 
		session.removeAttribute("msg");	
	} 
%>
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
		border-radius : 5px; 
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
		border-radius : 5px; 
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
		border-radius : 5px; 
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
		border-radius : 5px; 
	}

</style>
</head>
<body>
<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }" scope="application"/>
	<div class="content">
		<form id="joinForm" action="<%= request.getContextPath() %>/memberJoin" method="post" onsubmit="return validate();">
			<a href="/Do_IT" class="logo"><img src="/Do_IT/resources/images/logo.png" alt="logo"></a>
				
			<h4>이메일</h4>
			<span class="input_area"><input type="email" name="userEmail" id="userEmail" required></span>
			<button id="emailCheck" type="button">중복확인</button>
				
			<h4>비밀번호</h4>
			<span class="input_area"><input type="password" name="userPwd" id="userPwd" required></span><br>
		
			<h4>비밀번호 확인</h4>
			<span class="input_area"><input type="password" name="userPwd2" id="userPwd2" required></span><br>
				
		
			<h4>닉네임</h4>
			<span class="input_area"><input type="text" name="nickname"  id="nickname" required></span>
			<button id="nickCheck" type="button">중복확인</button>
				
			<div class="terms_all"><input type="checkbox" id="termsAll" name="terms"><label for="termsAll">모두 동의</label></div>
			<div class="terms_tos"><input type="checkbox" class="terms" name="terms"><a href="#" onclick="openPopup('<%= request.getContextPath() %>/tos', 'tos', 500, 500); return false">서비스 이용 약관 동의(필수)</a></div>
			<div class="terms_pp"><input type="checkbox" class="terms" name="terms"><a href="#" onclick="openPopup('<%= request.getContextPath() %>/pp', 'pp', 500, 500); return false">개인 정보 정책 동의(필수)</a></div>
				
			<span class="joinBtnArea"><button id="joinBtn">회원가입</button></span>
		</form>
	</div>
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	
	<script>
		// 전체 동의 체크 박스
	    $(document).ready( function() {
	    	if(!$(".terms").is(":checked")) {
	    		$('#termsAll').prop('checked', false);
	    	}
	    	
	    	
	        $('#termsAll').click( function() {
	          $('.terms').prop('checked', this.checked);
	        } );
	      } ); 
	
		function validate() {
			
			// 비밀번호가 8 ~ 16 자리 사이이고 특수문자, 숫자, 영어만 가능 (숫자, 영문, 특수문자 각 1자리 이상 ( 그외 글자 X ))
			let regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
			
			if(!regExp.test($('#userPwd').val())) {
				alert("8~16 자 영문 대 소문자,숫자,특수문자를 모두 포함하여 입력하세요");
				return false;
			}
     
			// 비밀번호와 비밀번호 확인이 일치하지 않으면
			if($("#userPwd").val() != $("#userPwd2").val()) {
				alert("비밀번호가 일치하지 않습니다.");
				$("#userPwd2").focus();
				return false;
			}
	
			// 약관 동의 체크 박스가 체크되어 있지 않으면
			if(!$(".terms").is(":checked")) {
				alert("서비스 이용 약관과 개인정보 정책에 대한 안내 모두 동의해주세요.");
				return false;
			}
			
			return true;
		}
		
		var isUsable_e = false;
		var isUsable_n = false;
		
		$("#emailCheck").click(function(){
			// input userId 변수
			var userEmail = $("[name=userEmail]");
			// 아이디 중복 시 false, 아이디 사용 가능 시 true
			
			if(!userEmail || userEmail.val().indexOf('@') == -1 ) {
				alert('올바른 형식의 이메일을 입력해주세요.');
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
							alert("이미 사용중이거나 탈퇴한 이메일입니다.");
							userEmail.focus();
						} else {
							if(confirm('사용 가능한 이메일입니다. 사용하시겠습니까?')) {
								// 더 이상 id 입력 공간을 수정할 수 없도록 readonly 처리
								userEmail.attr('readonly', true);
								isUsable_e = true; // 사용 가능한 아이디라는 flag 값
							} else {
								// confirm 창에서 취소를 누를 경우(처음 , 또는 반복해서) 다시 id 수정 가능하도록
								userEmail.attr('readonly', false);
								userEmail.focus();
								isUsable_e = false; // 사용 불가능한 아이디라는 flag 값
							}
						}
						// 아이디 중복 체크 후 중복이 아니며 사용하겠다고 선택한 경우에만
						// joinBtn disable 제거
						if(isUsable_e && isUsable_n) {
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
			var nickname = $("[name=nickname]");
			// 아이디 중복 시 false, 아이디 사용 가능 시 true
			
			var regExp = /^[가-힣a-zA-Z\d]{2,10}$/; 
			
			if(!nickname || !regExp.test(nickname.val()) ) {
				alert('2~10자 한글, 숫자, 영어 대 소문자를 입력하세요.');
				nickname.focus();
			} else {
				// 4자리 이상의 userId 값을 입력 했을 경우 중복 확인을 위해 ajax 통신 요청
				$.ajax({
					url : "${ contextPath }/nickCheck",
					type : "post",
					data : { nickname : nickname.val() },
					success : function(result){
						console.log(result);
						if(result == "fail") {
							alert("이미 사용중인 닉네임입니다.");
							nickname.focus();
						} else {
							if(confirm('사용 가능한 닉네임입니다. 사용하시겠습니까?')) {
								// 더 이상 id 입력 공간을 수정할 수 없도록 readonly 처리
								nickname.attr('readonly', true);
								isUsable_n = true; // 사용 가능한 아이디라는 flag 값
							} else {
								// confirm 창에서 취소를 누를 경우(처음 , 또는 반복해서) 다시 id 수정 가능하도록
								nickname.attr('readonly', false);
								nickname.focus();
								isUsable_n = false; // 사용 불가능한 아이디라는 flag 값
							}
						}
						// 아이디 중복 체크 후 중복이 아니며 사용하겠다고 선택한 경우에만
						// joinBtn disable 제거
						if(isUsable_e && isUsable_n) {
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
</body>
</html>










