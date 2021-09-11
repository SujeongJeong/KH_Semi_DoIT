<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>coinPaymentView</title>

<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
.wrapper{
	margin-top : 100px;
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

.paymentArea h2, h4 {
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


#charge_input{
	text-align: center;
}


button[id$=Btn]:hover {
	text-decoration: underline;
}

button[id^=easy] {
	display: block; 
}

.payment_btn {
	border: solid 1px #dadada;
	text-align: center;
	margin: 15px 0;
	height: 45px;
}

button[id$=payment] {
	width: 100%;
	height: 100%;
	border: 0px;
}


#phone_payment {
	background: skyblue;
	color: black;
}

#card_payment {
	background: lightgray;
	color: white;
}

#kakao_payment {
	background: #F7E600;
	color: #3A1D1D;
}

.textarea{
	margin-bottom: 30px;
}
.btn_area {
	text-align: center;
	border-top: 1px solid #282A35;
	padding: 30px;
}

.btn_area .chargebtn {
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
			<form class="paymentArea" action="<%= request.getContextPath() %>/login" method="post" onsubmit="return validate();">
				<div class="logo_area"><a href="/Do_IT"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo" onclick="window.close();"></a></div><br>
				<div class="cerrent_coin">
					<span id="cerrent_result"><h2>현재 보유 중인 코인 : 00 코인</h2></span>
				</div>
				<h4>충전 할 코인 : <input type="number" id="charge_input" placeholder="충전 할 코인 수를 입력하세요."></h4>
				<span class="charge_coin"></span>
				
				
				<hr>
				<div class="payment_btn"><button id="phone_payment">핸드폰 결제</button></div>
				<div class="payment_btn"><button id="card_payment">카드 결제</button></div>
				<div class="payment_btn"><button id="kakao_payment">카카오 결제</button></div>
				<hr>
				<textarea class="textarea" rows="10" cols="60" name="content" placeholder="환불 주의사항 채우기" required></textarea>
				<div class="btn_area">
				<button class=chargebtn type="submit">충전</button>
				<button class=canclebtn type="button" onclick="window.close();">취소</button>
			    </div>
				
			</form>
		</div>
	</div>
	
</body>
</html>