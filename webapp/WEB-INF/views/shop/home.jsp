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
<script src="<%= request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<style>

.product_area{
	width: 780px;
	margin-right: 80px -10px 80px 80px;

}

.coin_charge{
	border: 0px;
	color: white;
	background: #5FC5FF;
	width: 100px;
	height: 35px;
	margin-top: -20px;
	margin-left: -80px;
	border-radius : 5px; 
}

.product_addBtn{
	background: url("/Do_IT//resources/images/plus.png");
	border: none;
	width : 20px; height : 20px;
}
.product_addBtn:hover{
 	background: url("/Do_IT//resources/images/plus-hover.png");
}

.product_deleteBtn{
	background : url("/Do_IT//resources/images/minus.png");
	border: none;
	width : 20px; height : 20px;
}

.product_deleteBtn:hover{
	 background : url("/Do_IT//resources/images/minus-hover.png")
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
	
	<div class="product_area">
	 <button class="coin_charge" onclick="openPopup('<%=request.getContextPath()%>/coin', 'coin_charge', 700, 900);">코인충전</button>
	
	<h3>| 프리미엄 이용권  <button class="product_addBtn" onclick="openPopup('<%=request.getContextPath()%>/productAdd', 'ProductAdd', 900, 1200);"></button>
	<button class="product_deleteBtn" id="checkbox_btn" onclick="checkbox()"></button> </h3>
	
	  <div class="product_premium">
	    <div class="premium_product" id="set1" onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
	    	<span><input type="checkbox" name="product_check"  style="display:none"></span>
            <img class="premium_img" src="/Do_IT//resources/images/shop-premium.png">
            <div class="product_name"> (체험) 프리미엄 7일 이용권</div>
            <div class="product_price">15 point</div>
     
         </div>
         
		 <div class="premium_product" id="set2" onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
		 	<span><input type="checkbox" name="product_check"  style="display:none"></span>
            <img class="premium_img" src="/Do_IT//resources/images/shop-premium.png">
            <div class="product_name"> 프리미엄 30일 이용권</div>
            <div class="product_price">45 point</div>
         </div>
         
         <div class="premium_product" id="set3" onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
        	 <span><input type="checkbox" name="product_check"  style="display:none"></span>
            <img class="premium_img" src="/Do_IT/resources/images/shop-premium.png">
            <div class="product_name">프리미엄 90일 이용권</div>
            <div class="product_price"><s class="lightgray-c">135 point</s>    120 point</div>
         </div>
      </div>
      
      	<h3>| 이용권 단품  <button class="product_addBtn" onclick="openPopup('<%=request.getContextPath()%>/productAdd', 'ProductAdd', 900, 1200);"></button>
	<button class="product_deleteBtn" id="checkbox_btn" onclick="checkbox()"></button></h3>
      <div class="product_single">
    
         <div class="studyroomjoin_product" id="set1" onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
         <span><input type="checkbox" name="product_check"  style="display:none"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 가입 제한 해제 + todo 이용권(7일)</div>
            <div class="product_price">8 point</div>
         </div>
         <div class="studyroomjoin_product" id="set2" onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
         <span><input type="checkbox" name="product_check"  style="display:none"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 가입 제한 해제 + todo 이용권(30일)</div>
            <div class="product_price">32 point</div>
         </div>
         <div class="studyroomjoin_product" id="set3" onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
         <span><input type="checkbox" name="product_check"  style="display:none"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 가입 제한 해제 + todo 이용권(90일)</div>
            <div class="product_price">
               <s class="lightgray-c"> 96 point</s> 90 point
            </div>
         </div>
         <div class="studymember_product" id="set4" onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
         <span><input type="checkbox" name="product_check"  style="display:none"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 인원 수 해제 + todo 이용권(7일)</div>
            <div class="product_price">8 point</div>
         </div>
         <div class="studymember_product" id="set5" onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
         <span><input type="checkbox" name="product_check" " style="display:none"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 인원 수 해제 + todo 이용권(30일)</div>
            <div class="product_price">32 point</div>
         </div>
         <div class="studymember_product" id="set6" onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
         <span><input type="checkbox" name="product_check"  style="display:none"></span>
            <img class="single_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">스터디 인원 수 해제 + todo 이용권(90일)</div>
            <div class="product_price">
               <s class="lightgray-c"> 96 point</s> 90 point
          </div>
         </div>
      </div>
   </div>
   </div>

<script>

		function openPopup(url, title, width, height){			
			let left = (document.body.clientWidth/2) - (width/2);
			left += window.screenLeft;
			let top = (screen.availHeight/2) - (height/2);
			let options = "width=" + width+",height="+height + ",left="+left + ", top=" + top;
			  
			window.open(url, title, options);
		}
		
	
		function checkbox(){ //-버튼을 누르면 체크박스 활성화. 단, 클릭시 기능구현 아직 덜 했음.
            if($('input[name=product_check]').css('display') == 'none'){
            $('input[name=product_check]').show();
            
        }else{
            $('input[name=product_check]').hide();
        }
      }

</script>



	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>

</body>
</html>