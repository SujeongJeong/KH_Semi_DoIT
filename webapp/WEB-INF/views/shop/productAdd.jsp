<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


</style>
</head>
<body>
<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
 <div class="content">
	<!-- 제목조건, 폼 입력 조건 나중에 설정하기 -->
	<form class="productAddForm">
		<img class="product_img"></img>
           <input type="text" name="product_name" value="" size="35" placeholder="상품명을 입력하세요.">
           <br>
           <br>
           <input type="text" name="product_price" value="" size="15" placeholder="Coin수를 입력하세요."> Coin
           <br>
           <br>
           <input type="text" name="product_detail" value="" size="35" placeholder="상품설명을 입력하세요." height="150px">        
	</form>


 


	</div>
	
<footer>
<%@ include file='/WEB-INF/views/common/footer.jsp' %>
</footer>

</body>
</html>
