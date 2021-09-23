<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 보기</title>
<!-- 외부 스타일 시트 -->
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
 
 .wrapper{
	margin : 50px auto;
	
} 

.modifybtn {
	width: 88px;
	height: 35px;
	border-radius : 5px; border: 0px; 
	color: white;
	background: #5FC5FF;
	margin: 5px;
	position: fixed;
    right: 10px;
    top: 10px;
}

.allcontents {
	width: 600px;
	height: auto;
	margin: 50px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.product_content {
	padding: 0px 20px;
}


.image_area {
	width: 100%;
	height: 150px;
	margin-top: 30px;
	margin-bottom: 20px;
	justify-content : center;
}


.image_area img{
	width: 100%;
	height: 150px;}


.title {
	width: 400px;
	height: 30px;
	justify-content : center;
	border: 0;
}

#coinarea{
	font-size: 25px;
	color: #5FC5FF;
}

.coin_count{
	width: 45px;
	height: 25px;
	justify-content : center;
	border: 0;
	font-size: 25px;
	color: #5FC5FF;
}


.textarea{
	margin-top: 25px;
	margin-bottom: 30px;
	resize: none;
	border: 0;
}

.btn_area {
	text-align: center;
	border-top: 1px solid #282A35;
	padding: 30px;
}

.btn_area .paymentbtn {
	width: 100px;
	height: 35px;
	border-radius : 5px; border: 0px; 
	color: white;
	background: #5FC5FF;
	margin: 5px;
}
.btn_area .canclebtn {
	width: 100px;
	height: 35px;
	border-radius : 5px; border: 0px;
	color: white;
	background: #E5E5E5;
	margin: 5px;
}

</style>
</head>
<body>

<div class="wrapper">
 
	<!-- 제목조건, 폼 입력 조건 나중에 설정하기 -->
	
	<div class=allcontents>
	<form class="productModifyForm"  method="post" action='<%= request.getContextPath() %>/productModifyView'>
		<input type="hidden" name="product_no" value="${p.product_no}">
		<input type="hidden" name="expirtion" value="${p.expiration_date}">
		<input type="hidden" name="category" value="${p.product_category}">
		<button class="modifybtn" >수정</button>
	
		<div class="product_content">	
		
			<img class="image_area"  src="${ contextPath }${ p.product_img }">
			
				<div class="inputarea"> 
					<h2 class="title" >${ p.product_name }</h2> <hr>
				</div> 
		
				<div class="inputarea" id="coinarea"> 
					<input type="number" class="coin_count" name="price" min= 1 readonly value="${ p.product_price }"> Coin 
				</div>
		
			<textarea class="textarea" rows="15" cols="80" name="content" readonly>${ p.product_detail } </textarea>
			<div class="btn_area">
			<button class=paymentbtn type="button" onclick="parchase();">구매</button>
			<button class=canclebtn type="button" onclick="window.close();">취소</button>
		    </div>
		    </div>
		</form>
			</div>
			</div>
		
	
		
	<script>
			
	
			function detailView(){
				alert('로그인 후 이용 가능 합니다.');
				location.href='${contextPath}/login';
			}
			
	
			function parchase(){
				if(confirm("구매 하시겠습니까?"))
					window.close();
				//코인금액이 가격보다 작으면 코인창으로 가기.
			}
	</script>


</body>
</html>
