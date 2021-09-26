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
	margin-left: -150px;
	border-radius : 5px; 
}

.product_addBtn{
	background: url("/Do_IT//resources/images/plus.png");
	border: none;
	width : 20px; 
	height : 20px;
	margin-left: 15px;
}

.product_addBtn:hover{
 	background: url("/Do_IT//resources/images/plus-hover.png");
}

.product_deleteBtn{
	background : url("/Do_IT//resources/images/minus.png");
	border: none;
	width : 20px; 
	height : 20px;
	margin-left: 10px;
}

.product_deleteBtn:hover{
	 background : url("/Do_IT//resources/images/minus-hover.png")
} 

.deleteProductBtn{
	width: 100px;
	height: 35px;
	border-radius : 5px; border: 0px; 
	color: white;
	background: #E4352E;
	margin: 5px;
	margin-left: 840px;
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
	margin-left: 35px;
}

.product_price{
	width:200px;
	text-align:center;
	margin-left: 35px;
	color : #5FC5FF;
}

.premium_img{
	width: 300px;
	height: 300px;
}

.premium_img img{
	width: 270px;
	height: 270px;
}


</style>

</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
  
 <div class="content">	
	<div class="product_area">
	 <button class="coin_charge" onclick="openPopup('<%=request.getContextPath()%>/coin', 'coin_charge', 700, 900);">코인충전</button>
	
	
	<h3>| 프리미엄 이용권  
	<c:if test="${ loginUser.userType == 'A' }">
	<!-- 추가, 삭제버튼 -->
	<button class="product_addBtn" onclick="openPopup('<%=request.getContextPath()%>/productAdd', 'ProductAdd', 900, 1200);"></button>
	<button class="product_deleteBtn" id="checkbox_btn" onclick="checkbox()"></button> 
	</c:if>
	<form id="deleteProductForm" name="deleteProductForm" method="get" onsubmit="return confirm('정말 삭제하시겠습니까?');" action="<%=request.getContextPath()%>/productDelete">
		 <button  class="deleteProductBtn"  style="display:none" onclick="deleteBtn()">삭제</button></h3>
		 
		  <!-- 프리미엄 상품 리스트  -->	
		  <div class="product_premium">
		   <c:forEach var="p" items="${ productList }">
		   <c:if test="${ p.product_category == '세트' }">
			<div class="premium_product">
	            <input type="checkbox"  id="product_check" name="product_check"  style="display:none" value="${p.product_no}">
	            <div  onclick="pnoSubmit(${p.product_no})">
	        		<div class="premium_img"><img src="${ p.product_img}"></div>
	           	 	<div class="product_name">${p.product_name}</div>
	            	<div class="product_price">${p.product_price} coin</div>
	    		</div>
	         </div>
	         </c:if>
			</c:forEach>
	      </div>
	     
	      	<h3>| 이용권 단품  </h3>
	   	<!-- 단품 상품 리스트  -->
	      <div class="product_single">
	        <c:forEach var="p" items="${ productList }">
		    <c:if test="${ p.product_category == '단품' }">
				<div class="premium_product">
		          	<input type="checkbox" id="product_check" name="product_check" value="${p.product_no}" style="display:none">
			          	<div  onclick="pnoSubmit(${p.product_no})">
			          		<div class="premium_img"><img src="${ contextPath }${ p.product_img}"></div>
			            	<div class="product_name">${p.product_name}</div>
			            	<div class="product_price">${p.product_price} coin</div>
		     			</div>
	        	</div>
	         </c:if>
			</c:forEach>
	      </div>
    </form>
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
		
		//deleteBtn 클릭시 체크박스와 삭제버튼활성화 함수.
		function checkbox(){ 
            if($('input[name=product_check]').css('display') == 'none'){
            $('input[name=product_check]').show();
            $('.deleteProductBtn').show();
            
   		     }else{
            $('input[name=product_check]').hide();
            $('.deleteProductBtn').hide();
      	    }
    	  }
		//상세페이지로 product_no 값 넘기기
	 	function pnoSubmit(product_no){
		 openPopup('${contextPath}/productDetail?product_no='+product_no, 'productDetail', 900, 1200);
	 	}
	

</script>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>

</body>
</html>