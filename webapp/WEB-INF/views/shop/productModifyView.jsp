<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정 페이지</title>
<!-- 외부 스타일 시트 -->
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
 
 .wrapper{
	margin : 50px auto;
	
} 

.productModifyForm {
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
}

.coin_count{
	width: 80px;
	height: 25px;
	justify-content : center;
	
}

.expirtion{
	width: 50px;
	height: 25px;
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

	<form class="productModifyForm"  method="post" action="${ contextPath }/productModify"  enctype="multipart/form-data">
		<div class="product_content">		
			<input type="hidden" name="product_no" value="${p.product_no }">
			
			<h3>상품 이미지</h3>
			<img class="image_area" name="fileimg" src="${ contextPath }${ p.product_img}">
			수정 파일 <input type="file" name="file" id="productimg" accept="image/gif,image/jpeg,image/png">
			
				<div class="inputarea"> 
					<div><h3>상품명</h3> 	
					<select name="category">
						<option value="세트" <c:if test="${ p.product_category == '세트'}" selected</c:if>>세트</option>
						<option value="단품" <c:if test="${ p.product_category == '단품'}" selected</c:if>>단품</option>
					</select></div>
					<input type="text" class="title" name="title" value="${ p.product_name }">	
				</div>
		
				<div class="inputarea"> 
				<h3>상품 코인 | 기간</h3>
					<input type="number" class="coin_count" name="price" min= 1  value="${ p.product_price }"> Coin / 
					<input type="number" class="expirtion" name="expirtion" min= 1  value="${ p.expiration_date }"> 일
				</div>
		
			<h3>상품 설명</h3>
			<textarea class="textarea" rows="15" cols="80" name="content">${ p.product_detail }</textarea>
			<div class="btn_area">
			<button class=enrollbtn type="submit" onclick="detailView();">수정</button>
			<button class=canclebtn type="button" onclick="window.close();">취소</button>
		    </div>
			</div>
			</form>
			</div>
		</div>	
		
		<script src="${ contextPath }/resources/js/imagePreview.js"></script>
		
	
		<script>
			function detailView(product_no){
				alert('수정이 완료되었습니다.');
				location.href = '${contextPath}/ProductModifyView?product_no='+product_no;
			}
		</script>
		
		
	


</body>
</html>
