<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 추가 – Do IT</title>
<!-- 외부 스타일 시트 -->
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
 
 .wrapper{
	margin : 50px auto;
	
} 

.productAddForm {
	width: 600px;
	height: auto;
	margin: 50px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}


.logo_area {
	margin-bottom: 10px;
	text-align : center;
	justify-content: center;
}


.product_content {
	padding: 0px 20px;
}


.temporary_img {
	width: 100%;
	height: 150px;
	margin-top: 30px;
	margin-bottom: 20px;
	justify-content : center;
}

.title {
	width: 400px;
	height: 30px;
	justify-content : center;
}

.coin_count{
	width: 100px;
	height: 30px;
	justify-content : center;
	
}

.textarea{
	margin-bottom: 30px;
	resize: none;
}

.btn_area {
	text-align: center;
	border-top: 1px solid #282A35;
	padding: 30px;
}

.btn_area .enrollbtn {
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
	<div class="logo_area"><img class="logo" src="/Do_IT/resources/images/logo.png" onclick="window.close();" alt="logo"></div>
	<div>
	<form class="productAddForm" action="" method="post">
		<div class="product_content">		
			<img class="temporary_img" src="/Do_IT/resources/images/study-background6.jpg">
			<h3>상품 이미지</h3>
			<input type="file" name="thumbnail" accept="image/gif,image/jpeg,image/png" required>
			
			
				<div class="inputarea"> 
					<h3>상품명</h3>
					<input type="text" class="title" required placeholder="상품명을 입력하세요.">
				</div>
		
				<div class="inputarea"> 
				<h3>상품 코인</h3>
					<input type="number" class="coin_count" min= 1 required placeholder="Coin수를 입력하세요."> Coin 
					
				</div>
		
			<h3>상품 설명</h3>
			<textarea class="textarea" rows="15" cols="80" name="content" placeholder="상품설명을 입력하세요." readonly></textarea>
			<div class="btn_area">
			<button class=enrollbtn type="submit" onclick="enroll();">등록</button>
			<button class=canclebtn type="button" onclick="window.close();">취소</button>
		    </div>
		
		
			</form>
			</div>
		</div>	
	<script>
		function enroll(){
			if(confirm("등록하시겠습니까? "))
				window.close();
			//코인금액이 가격보다 작으면 코인창으로 가기.
		}
	</script>


</body>
</html>
