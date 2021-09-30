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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<%
   if(request.getAttribute("result") != null) {
      if(request.getAttribute("result").equals("success")) {
%>
<script>
   alert('상품 등록이 완료되었습니다.');
   opener.parent.location.reload();
   window.close();
</script>
<%
   } else {
%>
<script>
   alert(' 상품 등록에 실패하셨습니다.');
   window.close();
</script>
<%
      }
   }
%>



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
	width: 55px;
	height: 25px;
	justify-content : center;
}


#studyroomLimit, #studyroomEntryLimit, #todoListLimit{
	width: 40px;
	height: 25px;
	justify-content : center;
	margin-left: 10px;
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
	<form class="productAddForm"  method="post" action="${ contextPath }/productAdd"
               enctype="multipart/form-data" onsubmit="return confirm('상품을 등록하시겠습니까?');">
		
		<div class="product_content">		
			
			<h3>상품 이미지</h3>
			<div class="image_area"></div>
			<input type="file" name="file" id="productimg" accept="image/gif,image/jpeg,image/png" required>
			
				<div class="inputarea"> 
					<div><h3>상품명</h3> 	
					<select name="category" id="category">
						<option value="세트">세트</option>
						<option value="단품">단품</option>
					</select></div>
					<input type="text" class="title" name="title" required placeholder="상품명을 입력하세요.">
				</div>
		
				<div class="inputarea"> 
				<h3>상품 코인 | 기간</h3>
					<input type="number" class="coin_count" name="price" min= 1 required placeholder="Coin 입력"> Coin / 
					<input type="number" class="expirtion" name="expirtion" min= 1 required placeholder="숫자만 입력하세요"> 일
				</div>
				
				<div class="limit-wrapper">
					<h3>이용 기능 제한</h3>
					<!-- <span>스터디방 입장/생성 가능 개수 : </span><input type="number" class="studyroomLimit" name="studyroomLimit" min=3 required placeholder="3"> 개<br>
					<span>스터디방 입장 인원 수 : </span><input type="number" class="studyroomEntryLimit"  name="studyroomEntryLimit" min=4 required placeholder="4"> 인<br>
					<span>오늘의 할일 수 : </span><input type="number"  class="todoListLimit" name="todoListLimit" min=5 required placeholder="5"> 개 <br>-->
					
					<input type=radio id="limit1" name="limit"  style="display:none"> 스터디방 입장/생성 가능 개수 : 
					<input type="number" class="limit_number"  id="studyroomLimit" name="studyRoomLimit" min=3 required placeholder="3"  maxlength="6"> 개 <br>
					<input type=radio id="limit2" name="limit"  style="display:none">스터디방 입장 인원 수 : 
					<input type="number" class="limit_number"  id="studyroomEntryLimit"  name="studyroomEntryLimit" min=5 required placeholder="5"  maxlength="20"> 명 <br>
					<input type=radio id="limit3" name="limit"  style="display:none"> 오늘의 할일 수 : 
					<input type="number" class="limit_number"  id="todoListLimit" name="todoListLimit" min=5 required placeholder="5" > 개 <br>
					<input type=radio id="limit4" name="limit" style="display:none"> 스터디방 기간연장 상품 :
					<input type="number" class="limit_number" id="limitProduct" name="limitProduct" min=5 required placeholder="5" > 일
					
					
					
					
					</div><hr>
			
				<h3>상품 설명</h3>
				<textarea class="textarea" rows="15" cols="80" name="content" placeholder="상품설명을 입력하세요."></textarea>
				<div class="btn_area">
				<button class=enrollbtn type="submit">등록</button>
				<button class=canclebtn type="button" onclick="window.close();">취소</button>
			    </div>
			
			  </div>
			</form>
			</div>
		</div>	
		
		
		<script src="${ contextPath }/resources/js/imagePreview.js"></script>
		
		<script type="text/javascript">
		<%-- 단품 클릭 시 라디오 버튼 생성 --%>
			$('#category').change(function(){
				
				 if($('input[name=limit]').css('display') == 'none'){
			            $('input[name=limit]').show();
			            $(".checked").attr('disabled', false);
			                 
			   		     }else{
			            $('input[name=limit]').hide();
			            $(".limit_number").attr('disabled', false);
			            
			      	    }
			});
        <%-- 클릭된 라디오버튼  텍스트 활성화,비활성화--%>
			$("[type='radio']").click(function(){
								
				$(".limit_number").attr('disabled', true);
				 $(this).next().attr('disabled', false); 
				
			});
			
			 <%-- 클릭이 되면 위에 함수로 넘어감.--%>
			 $("[type='number']").click(function(){
				 if($("#category option:selected").val()=="단품"){
				 console.log("되는거니마는거니")
				 $(this).prev().click();
				 }
			});
		
			 
			 if(){
					opener.parent.location.reload();
					  window.close();
				}
				

		</script>


</body>
</html>
