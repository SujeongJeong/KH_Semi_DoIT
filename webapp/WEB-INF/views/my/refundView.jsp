<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 환불 팝업</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
.wrapper{
	margin-top : 150px;
}
.content {
	width: 40%;
	min-width: 400px;
	height: 600px;
	margin: 70px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

#refundForm h2, h3 {
	text-align: center;
	margin-bottom: 30px;
}

.input_area {
	border: solid 1px #dadada;
	padding: 10px 10px 14px 10px;
	background: white;
}

.input_area input {
	width: 300px;
	height: 30px;
	border: 0px;
}

.input_area input[type=submit] {
	color:white;
	background:#C8EBFF;
}

.login_btn {
	background:#C8EBFF; 
	border: 0px;
	margin-bottom: 10px;
}

.logo {
	margin-bottom: 20px;
}
button[id$=Btn]:hover {
	text-decoration: underline;
}

.textarea{
	margin-bottom: 30px;
}
.btn_area {
	text-align: center;
	border-top: 1px solid #282A35;
	padding: 30px;
}

.btn_area .refundbtn {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #5FC5FF;
}
.btn_area .canclebtn {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #E5E5E5;
	margin: 5px;
}

</style>
</head>
<body>
	<div class="wrapper">
		<div class="content">
			<form id="refundForm" action="<%= request.getContextPath() %>/my/refund" method="post" onsubmit="return validate();">
				<div class="logo_area"><a href="/Do_IT"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo" onclick="window.close();"></a></div><br>
				<div class="current_coin">
					<span id="current_result"><h2>현재 보유 중인 코인 : <span>00 코인</span></h2></span>
				</div>
				<h3>환불 신청 코인 : <input type="number" name="refund_coin" id="refund_coin" min= 1 required></h3>
				<h4>계좌번호 : <input type="text" name="bank_account" id="bank_account" required></h4>
				<h4>은행명 : 
					<select id="bank_name" name="bank_name">
						<option value="kakao">카카오뱅크</option>
						<option value="kookmin">국민은행</option>
						<option value="gieob">기업은행</option>
						<option value="nonghyeob">농협은행</option>
						<option value="shinhan">신한은행</option>
						<option value="saneob">산업은행</option>
						<option value="woori">우리은행</option>
						<option value="city">한국씨티은행</option>
						<option value="hana">하나은행</option>
						<option value="sc">SC제일은행</option>
						<option value="gyeongnam">경남은행</option>
						<option value="gwangju">광주은행</option>
						<option value="daegu">대구은행</option>
						<option value="busan">부산은행</option>
						<option value="saemaeul">새마을금고</option>
						<option value="jeonbug">전북은행</option>
						<option value="jeju">제주은행</option>
					</select>
				</h4>
				<h4>예금주 : <input type="text" name="account_name" id="account_name" min=1 required></h4>
				<textarea class="textarea" rows="10" cols="60" name="content" placeholder="환불 주의사항 채우기" readonly></textarea>
				<div class="btn_area">
				<button class=refundbtn type="submit">환불</button>
				<button class=canclebtn type="button" onclick="window.close();">취소</button>
			    </div>
				
			</form>
		</div>
	</div>
	
</body>
</html>