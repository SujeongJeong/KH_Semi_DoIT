<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shop - Do IT</title>
<!-- 외부 스타일 시트 -->
<link href='<%= request.getContextPath() %>/resources/css/all.css?after' rel='stylesheet'>
<style>

.product_area{
	width: 780px;
	margin: 100px auto;

}

.coincharge{
	float: left;
}

.product_premium {
	margin:50px 15px;
	display : grid;
	grid-template-columns: 80px 80px 80px;
	gap : 20px;
		   
}

.product_single {
	margin:50px 15px;
	display : grid;
	grid-template-columns: 80px 80px 80px;
	gap : 20px;
}

#set1 {
	float:left;
	margin-right:50px;
	margin-bottom:50px;
}
#set2 {
	margin-left:200px;
	margin-right:50px;
	margin-bottom:50px;
}

#set3 {
	margin-left:400px;
	margin-right:150px;
	margin-bottom:50px;;
}

#set4 {
	float:left;
	margin-right:50px;
	margin-:50px;

}
#set5 {
	margin-left:200px;
	margin-right:150px;
	
}

#set6 {
	margin-left:400px;
	margin-right:150px;
	
}


.product_name{
	width:200px;
	text-align:center;
}

.product_price{
	width:200px;
	text-align:center;
	margin-lfet:20px;
	color : #5FC5FF;
}



</style>

</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
  <div class="content">
	<!-- 스크롤바 오른쪽에 하나 만드는거 잊지말기. -->
	<!--<div class="allProduct" style="overflow-y:scroll; height:100%; padding:4px; border:1 solid #000000;">-->	
	
	<div class="product_area">
	<button class="coin_charge" onclick="location.href ='<%= request.getContextPath() %>/shop/coin';">코인충전</button>
	
	<h3>| 프리미엄 이용권  <button class="product_addBtn" onclick="location.href ='<%= request.getContextPath() %>/productAdd';"> + </button>
     <button class="product_deleteBtn"> - </button> </h3>
	
	  <div class="product_premium">
	    <div class="premium_product" id="set1">
	    	<span><input type="checkbox" name="product_check"></span>
            <img class="premium_img" src="/Do_IT//resources/images/shop-premium.png">
            <div class="product_name"> (체험) 프리미엄 7일 이용권</div>
            <div class="product_price">15 point</div>
         </div>
		 <div class="premium_product" id="set2">
		 <span><input type="checkbox" name="product_check"></span>
            <img class="premium_img" src="/Do_IT//resources/images/shop-premium.png">
            <div class="product_name"> 프리미엄 30일 이용권</div>
            <div class="product_price">45 point</div>
         </div>
         <div class="premium_product" id="set3">
         <span><input type="checkbox" name="product_check"></span>
            <img class="premium_img" src="/Do_IT/resources/images/shop-premium.png">
            <div class="product_name">프리미엄 90일 이용권</div>
            <div class="product_price"><s class="lightgray-c">135 point</s>    120 point</div>
         </div>
      </div>
      
      	<h3>| 이용권 단품  <button class="product_addBtn" onclick="location.href ='<%= request.getContextPath() %>/productAdd';"> + </button>
     <button class="product_deleteBtn"> - </button> </h3>
      <div class="product_single">
    
         <div class="studyroomjoin_product" id="set1">
         <span><input type="checkbox" name="product_check"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 가입 제한 해제 + todo 이용권(7일)</div>
            <div class="product_price">8 point</div>
         </div>
         <div class="studyroomjoin_product" id="set2">
         <span><input type="checkbox" name="product_check"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 가입 제한 해제 + todo 이용권(30일)</div>
            <div class="product_price">32 point</div>
         </div>
         <div class="studyroomjoin_product" id="set3">
         <span><input type="checkbox" name="product_check"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 가입 제한 해제 + todo 이용권(90일)</div>
            <div class="product_price">
               <s class="lightgray-c"> 96 point</s> 90 point
            </div>
         </div>
         <div class="studymember_product" id="set4">
         <span><input type="checkbox" name="product_check"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 인원 수 해제 + todo 이용권(7일)</div>
            <div class="product_price">8 point</div>
         </div>
         <div class="studymember_product" id="set5">
         <span><input type="checkbox" name="product_check"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 인원 수 해제 + todo 이용권(30일)</div>
            <div class="product_price">32 point</div>
         </div>
         <div class="studymember_product" id="set6">
         <span><input type="checkbox" name="product_check"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 인원 수 해제 + todo 이용권(90일)</div>
            <div class="product_price">
               <s class="lightgray-c"> 96 point</s> 90 point
            </div>
         </div>
      </div>
   </div>
   </div>




	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>

</body>
</html>