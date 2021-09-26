<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코인 충전 – Do IT</title>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
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

#charge_area{
	margin-top: 10px;
	margin-bottom: 20px;
}

#result{
	width:100px;
	text-align: center;
	border: none;
	font-size: 25px;
	color: #5FC5FF;
	margin-botton: 10px;
}

.logo {
	margin-bottom: 10px;
}


button[id$=Btn]:hover {
	text-decoration: underline;
}

button[id^=easy] {
	display: block; 
}

.payment_btn {
	border: 0;
	text-align: center;
	margin: 15px 0;
	height: 45px;
}

button[id$=payment] {
	width: 100%;
	height: 100%;
	border-radius : 5px; border:0; outline:0;
	
}


#phone_payment {
	background: #C8EBFF;
	color: black;
}

#card_payment {
	background: lightgray;
	color: white;
}

#kakao_payment {
	background: #FFEA00;
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
	border-radius : 5px; border:0; outline:0;
	color: white;
	background: #5FC5FF;
}
.btn_area .canclebtn {
	width: 100px;
	height: 35px;
	border-radius : 5px; border:0; outline:0;
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
					<span id="cerrent_result"><h4>현재 보유 중인 코인 : ${ loginUser.userCoin } coin </h4></span>
				<input type="text" id="result" disabled>원
				<div id="charge_area">충전 할 코인 : <input type="text" id="charge_input"  onkeyup="calc(this.value)" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"   
					min= 1 placeholder="충전 할 코인 수를 입력하세요."></div>
				
			
				 <textarea class="textarea" rows="10" cols="60" name="content" placeholder="환불 주의사항 채우기" readonly></textarea>
				 <div class="btn_area">
				 <button class=chargebtn type="button">충전</button>
				 <button class=canclebtn type="button" onclick="window.close();">취소</button>
			    </div>
				</div>	
			</form>
		</div>
	</div>
	<script>
		function charge(){
			if(confirm("충전이 완료되었습니다."))
				window.close();
		}
		
		function calc(val){ //수수료포함 코인가격 현금화.
		    var charge = parseInt(val);
		    var price = 110*charge;
		    
		    if(val == ""){
		        document.getElementById('result').value = "0";
		    }else{
		        document.getElementById('result').value = price;
		    }
		}	
		
		
		//결제 API
		
		$(".chargebtn").click(function () {
		var IMP = window.IMP; // 생략가능
		IMP.init('imp78099653');
	    var money = $('#result').val();
	        console.log(money);
		var email = '${ loginUser.userEmail }';
		var nickname = '${ loginUser.nickName }';
		
		IMP.request_pay({
		pg: 'inicis', // version 1.1.0부터 지원.

		pay_method: 'card',
		
		merchant_uid: 'merchant_' + new Date().getTime(),
		/*
		merchant_uid에 경우
		https://docs.iamport.kr/implementation/payment
		참고하기. 아직 못함.
		*/
		name: '주문명:Do_IT 코인 결제',
		//결제창에서 보여질 이름
		amount: money,
		//가격
		buyer_email: email,
		buyer_name: nickname,
		buyer_tel: '***-****-*****',
		buyer_addr: '',
		buyer_postcode: '',
		m_redirect_url: 'http://localhost:8800/Do_IT/shop/home'
	}, 
		
		
		function (rsp) {
		console.log(rsp);
		if (rsp.success) {
		var msg = '결제가 완료되었습니다.';
		msg += '고유ID : ' + rsp.imp_uid;
		msg += '상점 거래ID : ' + rsp.merchant_uid;
		msg += '결제 금액 : ' + rsp.paid_amount;
		msg += '카드 승인번호 : ' + rsp.apply_num;
		  
		$.ajax({
              type: "GET", 
              url: "${ contextPath }/shop/charge", //충전 금액값을 보낼 url 설정
              data: {
                  "amount" : money/110
                  //작성자는 세션에서 가져오기. 코인값, 결제수단, 
              },
          });
      } else {
          var msg = '결제에 실패하였습니다. ';
          msg += ' error : ' + rsp.error_msg;
      }
      alert(msg);
      window.close();
     
  });
});
</script>




	
</body>
</html>