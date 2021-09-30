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

.chargeArea h2, h4 {
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
			<form class="chargeArea" action="<%= request.getContextPath() %>/login" method="post">
				<div class="logo_area"><a href="/Do_IT"><img class="logo" src="/Do_IT/resources/images/logo.png" alt="logo" onclick="window.close();"></a></div><br>
				<div class="cerrent_coin">
					<span id="cerrent_result"><h4>현재 보유 중인 코인 : ${ loginUser.userCoin } coin </h4></span>
				<input type="text" name="result" id="result" disabled>원
				<div id="charge_area">충전 할 코인 : <input type="text" id="charge_input"  onkeyup="calc(this.value)" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"   
					min= 1 placeholder="충전 할 코인 수를 입력하세요." required></div>
				 <textarea class="textarea" rows="10" cols="60" name="content" readonly>다양한 상품을 구매하고 공부의 재미를 더하세요!&#10;&#10;포인트를 구매해서 최대 6개 스터디를 개설하고,&#10;입장하여 좋은 경쟁을 해보세요.&#10;&#10;다양한 아이템을 통해 공부의 재미도 느낄 수 있습니다.&#10; 
상품 가격에 부가가치세가 포함되어 있으며&#10; 사용하지 않은 코인은 마이페이지에서 환불 가능합니다.&#10;</textarea>
				 <div class="btn_area">
				 <button class=chargebtn type="button" >충전</button>
				 <button class=canclebtn type="button" onclick="window.close();">취소</button>
			    </div>
				</div>	
			</form>
		</div>
	</div>
	<script>
		
		
		function calc(val){ //수수료포함 코인가격 현금화 계산 함수.
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
				
			var IMP = window.IMP; 
			IMP.init('imp78099653');
		    var money = $('#result').val();
		        console.log(money);
			var email = '${ loginUser.userEmail }';
			var nickname = '${ loginUser.nickName }';
			var userCoin = '${ loginUser.userCoin }';
			IMP.request_pay({
			pg: 'inicis', 
			pay_method: 'card',
			merchant_uid: 'merchant_' + new Date().getTime(),
			
			//결제창에서 보여질 이름
			name: 'Do_IT 코인 결제',
			//가격
			amount: money,
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
			msg += "(주)두잇 Do_IT  "
			msg += '결제 금액 : ' + rsp.paid_amount + " 입니다. 감사합니다.";
			
		
			$.ajax({
	              type: "POST", 
	              url: "${ contextPath }/shop/chargeSucess", //충전 금액값을 보낼 url 설정
	              data: {
	                  "amount" : money,
	                  "user_coin": userCoin
	         
	      			    },
	      		
			 });
	        window.close();
		     } else {
		         var msg = '결제에 실패하였습니다. ';
		         msg += ' error : ' + rsp.error_msg;
		     }
		     alert(msg);
		     window.close();
		    
		 });
		})
		
</script>




	
</body>
</html>