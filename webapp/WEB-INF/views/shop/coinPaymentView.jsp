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

.paymentArea h2, h4 {
	text-align: center;
	margin-bottom: 30px;
}

.cerrent_coin{
	text-align: center;
}

#charge_input{
	display: inline;
	text-align: center;
}

#result{
	text-align: center;
	border: none;
	font-size: 25px;
	color: #5FC5FF

}


.logo {
	margin-bottom: 20px;
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
				
				<div id="charge_area">충전 할 코인 : <input type="text" id="charge_input"  id="charge_input" onkeyup="calc(this.value)"  
						min= 1 placeholder="충전 할 코인 수를 입력하세요.">
						<input type="text" id="result" disabled/></div>
				
				
				<hr>
				 <div class="payment_btn"><button id="phone_payment">핸드폰 결제</button></div>
				 <div class="payment_btn"><button id="card_payment">카드 결제</button></div>
				 <div class="payment_btn"><button id="kakao_payment">카카오 결제</button></div>
				<hr>
				 <textarea class="textarea" rows="10" cols="60" name="content" placeholder="환불 주의사항 채우기" readonly></textarea>
				 <div class="btn_area">
				 <button class=chargebtn type="submit" onclick="charge();">충전</button>
				 <button class=canclebtn type="button" onclick="window.close();">취소</button>
			    </div>
				
			</form>
		</div>
	</div>
	<script>
		function charge(){
			if(confirm("충전이 완료되었습니다. "))
				window.close();
		}
		
		function calc(val){ //수수료포함 코인가격 현금화.
		    var charge = parseInt(val);
		    var price = 110*charge;
		    
		    if(val == ""){
		        document.getElementById('result').value = "0";
		    }else{
		        document.getElementById('result').value = price + "원";
		    }
		}	
		
	</script>

	
</body>
</html>