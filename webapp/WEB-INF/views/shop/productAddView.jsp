<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 외부 스타일 시트 -->
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>


.productAddForm {
	width: 40%;
	min-width: 400px;
	height: 600px;
	margin: 70px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.productArea {
	width: 800px;
	margin: auto;
}

.productAddForm {
	width: 780px;
	margin: 100px auto;
}
.product_content {
	padding: 0px 20px;
	margin-bottom: 30px;
}

.image_area {
	text-align: center;
}

.image_area img {
	padding: 20px;
	width: 100%;
}

.product_content .inputarea {
	margin-bottom: 30px;
}

.inputarea input {
	width: 400px;
	height: 30px;
}

.inputarea coin_count{
	width: 150px;
	height: 30px;
}


.textarea{
	margin-bottom: 30px;
}

.btn_area {
	text-align: center;
	border-top: 1px solid #282A35;
	padding: 30px;
}

.btn_area .enrollbtn {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #5FC5FF;
	margin: 5px;
}
.btn_area .canclebtn {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #E5E5E5;
	margin: 5px;
}

.temporary_img {
	width: 450px;
	height: 150px;
	margin-top: 30px;
	margin-bottom: 20px;
}


</style>
</head>
<body>
<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
 <div class="content">
 	<div class="productArea">
	<!-- 제목조건, 폼 입력 조건 나중에 설정하기 -->
	<form class="productAddForm" action="" method="post">
		<div class="product_content">		
		<!-- 사진, 제목, coin수, content 입력 폼 만듬-->	
			<!-- <div class="image_area" src="/Do_IT//resources/images/study-background3"></div> -->
			<img class="temporary_img" src="/Do_IT/resources/images/study-background6.jpg">
			<br>
			<input type="file" name="thumbnail" accept="image/gif,image/jpeg,image/png" required>
			
			<h6>
				<span class="inputarea"> 
					<input type="text" name="title" required placeholder="상품명을 입력하세요.">
				</span>
			</h6>
			<h6>
				<span class="inputarea"> 
					<input type="text" name="coin_count" required placeholder="Coin수를 입력하세요."> Coin 
				</span>
			</h6>
			<textarea class="textarea" rows="15" cols="60" name="content" placeholder="상품설명을 입력하세요." required></textarea>
			<div class="btn_area">
			<button class=enrollbtn type="submit">등록</button>
			<button class=canclebtn type="button" onclick="location.href='${ contextPath }/shop/home'">취소</button>
		    </div>
		
		
	</form>
	</div>
		
	</div>


 


	
<footer>
<%@ include file='/WEB-INF/views/common/footer.jsp' %>
</footer>

</body>
</html>
