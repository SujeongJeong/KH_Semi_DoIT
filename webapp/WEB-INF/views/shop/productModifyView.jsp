<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정 페이지</title>
<!-- 외부 스타일 시트 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
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

.allcontents {
	width: 600px;
	height: auto;
	margin: 50px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.product_content {
	padding: 0px 20px;
}


.image_area {
	width: 100%;
	height: 300px;
	margin-top: 30px;
	margin-bottom: 20px;
	justify-content : center;
	border:1px solid #ddd;
	border-radius: 10px;
}


.image_area img{
	width: 100%;
	height: auto;	
}


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

#studyroomLimit, #studyroomEntryLimit, #todoListLimit{
	width: 40px;
	height: 25px;
	justify-content : center;
	margin-left: 10px;
}

.textarea{
	width: 500px;
	margin-bottom: 30px;
	resize: none;
}

.btn_area {
	text-align: center;
	border-top: 1px solid #282A35;
	padding: 30px;
}

.btn_area .modifybtn {
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
	<div class="allcontents">

	<form class="productModifyForm"  method="post" action="${ contextPath }/productModify"  enctype="multipart/form-data" onsubmit="return confirm('이대로 수정하시겠습니까?');">
		<div class="product_content">		
			<input type="hidden" name="product_no" value="${p.product_no }">
			
			<h3>상품 이미지</h3>
			<img class="image_area" name="fileimg" src="${ contextPath }${ p.product_img}">
			수정 파일 <input type="file" name="modify_file" id="productimg" accept="image/gif,image/jpeg,image/png"  required>
			
				<div class="inputarea" id="category">
					<div><h3>상품명</h3> 	
					<select name="category">
						<option value="세트" <c:if test="${ p.product_category == '세트'}" </c:if>>세트</option>
						<option value="단품" <c:if test="${ p.product_category == '단품'}" </c:if>>단품</option>
					</select></div>
					<input type="text" class="title" name="title" value="${ p.product_name }">	
				</div>
		
				<div class="inputarea"> 
				<h3>상품 코인 | 기간</h3>
					<input type="number" class="coin_count" name="price" min= 1  value="${ p.product_price }"> Coin / 
					<input type="number" class="expirtion" name="expirtion" min= 1  value="${ p.expiration_date }"> 일
				</div>
				<div class="limit-wrapper">
				<h3>이용 기능 제한</h3>
					
					<input type=radio id="limit1" name="limit"  style="display:none"> 스터디방 입장/생성 가능 개수 : 
					<input type="number" class="limit_number"  id="studyroomLimit" name="studyRoomLimit" min=3 required value="${ p.s_limit}"  maxlength="6"> 개 <br>
					<input type=radio id="limit2" name="limit"  style="display:none">스터디방 입장 인원 수 : 
					<input type="number" class="limit_number"  id="studyroomEntryLimit"  name="studyroomEntryLimit" min=5 required value="${ p.s_to_limit}"  maxlength="20"> 명 <br>
					<input type=radio id="limit3" name="limit"  style="display:none"> 오늘의 할일 수 : 
					<input type="number" class="limit_number"  id="todoListLimit" name="todoListLimit" min=5 required value="${ p.todo_limit}" > 개 <br>
					<input type=radio id="limit4" name="limit" style="display:none"> 스터디방 기간연장 상품 :
					<input type="number" class="limit_number" id="limitProduct" name="limitProduct" min=5 required value="${ p.s_limit}" > 일
									
				
				
				</div><hr>
		
			<h3>상품 설명</h3>
			<textarea class="textarea" rows="15" cols="60" name="content">${ p.product_detail }</textarea>
			<div class="btn_area">
			<button class=modifybtn type="submit" >수정</button>
			<button class=canclebtn type="button" onclick="window.close();">취소</button>
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
				 $(this).prev().click();
				 }
			});
		</script>
		<script>
		//파일변경
		function readUpload(input) {
			 if (input.files && input.files[0]) {
			  var reader = new FileReader();
			  
			  reader.onload = function (e) {
			   $('.image_area').attr('src', e.target.result);  
			  }
			  
			  reader.readAsDataURL(input.files[0]);
			  }
			}
			 
			//함수
			$("#productimg").change(function(){
			   readUpload(this);
			});
			
		</script>
		
	


</body>
</html>
