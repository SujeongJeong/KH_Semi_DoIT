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
	grid-template-columns: 300px 300px 300px;
	gap : 30px;
}

.product_single {
	margin:50px 15px;
	display : grid;
	grid-template-columns: 300px 300px 300px;
	gap : 30px;
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
	   <c:forEach var="p" items="${ productList }">
	   <c:if test="${ p.product_category == '세트' }">
		<div class="premium_product"  onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
            <input type="checkbox" name="product_check"  style="display:none">
            <img class="premium_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">${p.product_name}</div>
            <div class="product_price">${p.product_price} coin</div>
     
         </div>
         </c:if>
		</c:forEach>
      </div>
     
      	<h3>| 이용권 단품  <button class="product_addBtn" onclick="openPopup('<%=request.getContextPath()%>/productAdd', 'ProductAdd', 900, 1200);"></button>
	<button class="product_deleteBtn" id="checkbox_btn" onclick="checkbox()"></button></h3>
      <div class="product_single">
    
        <c:forEach var="p" items="${ productList }">
	   <c:if test="${ p.product_category == '단품' }">
		<div class="premium_product"  onclick="openPopup('<%=request.getContextPath()%>/productDetail', 'productDetail', 900, 1200);">
            <input type="checkbox" name="product_check"  style="display:none">
            <img class="premium_img" src="/Do_IT/resources/images/shop-study.png">
            <div class="product_name">${p.product_name}</div>
            <div class="product_price">${p.product_price} coin</div>
     
         </div>
         </c:if>
		</c:forEach>
     
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