<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shop - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
.product_premium {
		    display: grid;
		    grid-template-columns: 100px 100px 100px;
		    grid-template-rows: 100px 100px 100px;
}

.product_single {
		    display: grid;
		    grid-template-columns: 100px 100px 100px;
		    grid-template-rows: 100px 100px 100px;
}


</style>

</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
	<content class="content">
	<!-- 스크롤바 오른쪽에 하나 만드는거 잊지말기. -->
	
	
	
	<div class="productlist">
	<h3>| 프리미엄 이용권  <button class="product_addBtn" onclick="location.href ='<%= request.getContextPath() %>/productAdd';"> + </button>
     <button class="product_deleteBtn"> - </button> </h3>
	
      <div class="product_premium">
  
         <div class="premium_product" id="set1">
            <img class="premium" src="resources/images/shop-premium.png">
            <p class="product_name">(체험) 프리미엄 7일 이용권</p>
            <p class="product_price">15 point</p>
         </div>
         <div class="premium_product" id="set2">
            <img class="premium" src="/resources/images/shop-premium.png">
            <p class="product_name">프리미엄 30일 이용권</p>
            <p class="product_price">45 point</p>
         </div>
         <div class="premium_product" id="set2">
            <img class="premium" src="/resources/images/shop-premium.png">
            <p class="product_name">프리미엄 90일 이용권</p>
            <p class="product_price">
               <s>135 point</s> 120 point
            </p>
         </div>
      </div>
      
      	<h3>| 이용권 단품  <button class="product_addBtn"> + </button>
     <button class="product_deleteBtn"> - </button> </h3>
      <div class="product_single">
    
         <div class="studyroomjoin_product" id="set1">
            <img class="single" src="resources/images/shop-study.png">
            <p class="product_name">스터디 가입 제한 해제 + todo 이용권(7일)</p>
            <p class="product_price">8 point</p>
         </div>
         <div class="studyroomjoin_product" id="set2">
            <img class="single" src="resources/images/shop-study.png">
            <p class="product_name">스터디 가입 제한 해제 + todo 이용권(30일)</p>
            <p class="product_price">32 point</p>
         </div>
         <div class="studyroomjoin_product" id="set3">
            <img class="single" src="resources/images/shop-study.png">
            <p class="product_name">스터디 가입 제한 해제 + todo 이용권(90일)</p>
            <p class="product_price">
               <s> 96 point</s> 90 point
            </p>
         </div>
         <div class="studymember_product" id="set1">
            <img class="single" src="resources/images/shop-study.png">
            <p class="product_name">스터디 인원 수 해제 + todo 이용권(7일)</p>
            <p class="product_price">8 point</p>
         </div>
         <div class="studymember_product" id="set2">
            <img class="single" src="resources/images/shop-study.png">
            <p class="product_name">스터디 인원 수 해제 + todo 이용권(30일)</p>
            <p class="product_price">32 point</p>
         </div>
         <div class="studymember_product" id="set3">
         	
            <img class="single" src="resources/images/shop-study.png">
            <p class="product_name">스터디 인원 수 해제 + todo 이용권(90일)</p>
            <p class="product_price">
               <s> 96 point</s> 90 point
            </p>
         </div>
      </div>




   </div>

   </content>


	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>

</body>
</html>