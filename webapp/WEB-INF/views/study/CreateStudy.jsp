<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="shop.model.vo.Purchase"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방 만들기 – Do IT</title>
<!-- 외부 스타일 시트 -->
<link href='<%=request.getContextPath()%>/resources/css/all.css'
	rel='stylesheet'>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<style>
.span1 {
	display: inline-block;
	width: 200px;
}

.span2{
	position : relative;
}

.title2{
	position : absolute;
	bottom : 55px;
}

#imgArea img {
	width: 100%;
	height:100%;
}

#studyExplain, #studyNotice {
	display: inline-block;
}

.studyDay label, .studyTO label {
	margin-right: 25px;
}

.studyCategory label {
	margin-right: 50px;
}

#s_startPeriod {
	margin-right: 25px;
}

.endTime {
	margin-left: 25px;
}

.submitBtn {
	display: flex;
	justify-content: center;
}

#createStudyBtn {
	width: 135px;
	height: 50px;
	font-size: 1.10em;
	background-color: #5FC5FF;
	border-radius: 5px;
	border: 0;
	outline: 0;
}
.timepicker, .datepicker {
	width: 120px;
}
</style>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp'%>
	<c:set var="p" value="${ p }"></c:set>
	<div class="content">
		<form id="createForm" method="POST"
			action="<%=request.getContextPath()%>/study/createStudy" enctype="multipart/form-data" onsubmit="return validate();">
			<span class="span1"><label class="title">스터디 배경화면</label></span> 
			<input type="file" id="studyImage" name="s_image" value="studyImage" 
			accept="image/gif.image/jpeg,image/png" required>
			<div id="imgArea"></div>

			<hr>
			<span class="span1"><label class="title" id="studyTitle">스터디 이름</label></span> 
			<input type="text" name="s_name" size="20" placeholder="스터디 이름을 입력하세요"
				maxlength="20" required>
			<hr>
			<span class="span1"><label class="title">스터디 정원수</label></span> 
			<span class="studyTO"> 
				<input type="radio" name="s_to" id="TO_5" value="5" required> 
				<label for="TO_5">5인(기본)</label> 
				<input type="radio" name="s_to" id="TO_10" value="10" class="radio_to"> 
				<label for="TO_10">10인(유료)</label> 
				<input type="radio" name="s_to"	id="TO_15" value="15" class="radio_to"> 
				<label for="TO_15">15인(유료)</label>
				<input type="radio" name="s_to" id="TO_20" value="20" class="radio_to"> 
				<label for="TO_20">20인(유료)</label>
			</span>
			<hr>
			<span class="span1"><label class="title">요일 설정</label></span> 
			<span class="studyDay"> 
				<input type="checkbox" name="s_day"	id="monday" value="월" > 
				<label for="monday">월</label> 
				<input type="checkbox" name="s_day" id="tuesday" value="화"> 
				<label for="tuesday">화</label> 
				<input type="checkbox" name="s_day"	id="wednesday" value="수"> 
				<label for="wednesday">수</label>
				<input type="checkbox" name="s_day" id="thursday" value="목">
				<label for="thursday">목</label> 
				<input type="checkbox" name="s_day"	id="friday" value="금"> 
				<label for="friday">금</label> 
				<input type="checkbox" name="s_day" id="saturday" value="토"> 
				<label for="saturday">토</label> 
				<input type="checkbox" name="s_day"	id="sunday" value="일"> 
				<label for="sunday">일</label>
			</span>
			<hr>
			
			<span class="span1"><label class="title">기간 설정</label></span>
			시작일 : <input type="text" name="s_startPeriod" class="datepicker" id="s_startPeriod" required> 
			종료일 : <input type="text" name="s_endPeriod" class="datepicker" id="s_endPeriod" required>
			<hr>
			<span class="span1"><label class="title">시간 설정</label></span> 
			시작 시간 :	<input type="text" name="s_startTime" class="timepicker" id="studyStartTime" required>
			<label class="endTime"> 종료 시간 :</label> 
			<input type="text" name="s_endTime" class="timepicker" id="studyEndTime" required>
			
			<hr>
			<span class="span1"><label class="title">카테고리</label></span> <span
				class="studyCategory"> 
				<input type="radio" name="cid" id="language" value="1" required> 
				<label for="language">language</label>
				<input type="radio" name="cid" id="embeded" value="2">
				<label for="embeded">embeded</label> 
				<input type="radio" name="cid" id="ai" value="3"> 
				<label for="ai">ai</label> 
				<input type="radio" name="cid" id="back-end" value="4"> 
				<label for="back-end">back-end</label></span> <br>
			 <span class="span1"></span> <span class="studyCategory">
				<input type="radio" name="cid" id="front-end" value="5">
				<label for="front-end">front-end</label> 
				<input type="radio"	name="cid" id="game" value="6"> 
				<label for="game">game</label>
				<input type="radio" name="cid" id="app" value="7"> 
				<label for="app">app</label> 
				<input type="radio" name="cid" id="bigdata" value="8"> 
				<label for="bigdata">bigdata</label></span> <br>
			 <span class="span1"></span> <span class="studyCategory">
				<input type="radio" name="cid" id="blockchain" value="9">
				<label for="blockchain">blockchain</label> 
				<input type="radio"	name="cid" id="devops" value="10"> 
				<label for="devops">devops</label>
				<input type="radio" name="cid" id="project" value="11">
				<label for="project">project</label>
			</span>
			<hr>
			<span class="span1 span2"><label class="title title2">스터디 소개<br>(설명)
			</label></span>
			<textarea name="s_explain" id="studyExplain" cols="80" rows="5"
				placeholder="스터디 소개 및 설명을 입력하세요(최대800자)" required></textarea>
			<hr>
			<span class="span1 span2"><label class="title title2">스터디
					공지사항<br>(주의사항,규칙)
			</label></span>

			<textarea name="s_notice" id="studyNotice" cols="80" rows="5"
				placeholder="스터디 공지사항(주의사항/규칙 등)을 입력하세요(최대800자)" required></textarea>
			<br> <br>
			<input type="hidden" name="userNo" value="${ loginUser.userNo }">
			
			<div class="submitBtn">
				<button type="submit" id="createStudyBtn" onsubmit="return validate()">스터디방 만들기</button>
			</div>
		</form>
<script>
	let studyImage = document.getElementById("studyImage");
	let imgArea = document.getElementById("imgArea");
	
	studyImage.addEventListener('change',preview);
	
	function preview(){
		
		if(this.files && this.files[0]){
			let reader = new FileReader();
			 reader.readAsDataURL(this.files[0]);
			 reader.onload = function(){
				 imgArea.innerHTML = '<img src="' + reader.result + '">';
			 }
		}
		imgArea.style.border = "1px solid black";
		imgArea.style.padding = "20px";
		imgArea.style.width = "200px";
		imgArea.style.height = "200px";
		
	}
	
	// 스터디방 이름 중복체크
	$("input[name=s_name]").change(function(){
		// input userId 변수
		var sname = $("input[name=s_name]");
		// 아이디 중복 시 false, 아이디 사용 가능 시 true
		
		var isUsable = false;
		
		if(sname.val().length <= 0 || sname.val().length > 15 ) {
			alert('이름은 1 ~ 15자 사이로 입력해주세요.');
			sname.focus();
		} else {
			// 4자리 이상의 userId 값을 입력 했을 경우 중복 확인을 위해 ajax 통신 요청
			$.ajax({
				url : "${ contextPath }/snameCheck",
				type : "post",
				data : { sname : sname.val() },
				success : function(result){
					console.log(result);
					if(result == "fail") {
						alert("이미 사용중인 이름입니다.");
						sname.focus();
					} else {
						if(confirm('사용 가능한 이름입니다. 사용하시겠습니까?')) {
							// 더 이상 id 입력 공간을 수정할 수 없도록 readonly 처리
							isUsable = true; // 사용 가능한 아이디라는 flag 값
						} else {
							// confirm 창에서 취소를 누를 경우(처음 , 또는 반복해서) 다시 id 수정 가능하도록
							sname.focus();
							isUsable = false; // 사용 불가능한 아이디라는 flag 값
						}
					}
					// 아이디 중복 체크 후 중복이 아니며 사용하겠다고 선택한 경우에만
					// joinBtn disable 제거
					if(isUsable) {
						$("#createStudyBtn").removeAttr("disabled");
					} else {
						$("#createStudyBtn").attr("disabled", true);
					}
				},
				error : function(e){
					console.log(e);
				}
			});
		}
	});
	 
	let s_to_limit = ${prLimit.s_to_limit}; // 스터디방 입장 가능 인원 수 제한 4
	let s_limitdate = ${prLimit.s_limitdate}; //스터디방연장상품 0
	
	if(s_limitdate == 0) {
		$(".radio_to").attr("disabled", true);
	} else {
		$(".radio_to").removeAttr("disabled");
		
		$('.radio_to').click(function(){
			if(s_limitdate != 0) {
				$( '.datepicker' ).datepicker( "destroy" );
				$('.datepicker').datepicker({ dateFormat: 'yy-mm-dd',minDate : 0, maxDate : "+"+s_limitdate+"d"});
			}
		});
	}
	
	// 
	$('#TO_5').click(function(){
		$( '.datepicker' ).datepicker( "destroy" );
		$('.datepicker').datepicker({ dateFormat: 'yy-mm-dd',minDate : 0, maxDate : '+3m'});
	});
	
	
	$("input[name=s_to]").each(function(index, elem){ 
		if($(elem).val() <= s_to_limit+1) {
			$(elem).removeAttr("disabled");
		} else {
			$(elem).attr("disabled", true);
		}
	});
	
	function validate(){
		if($('#s_startPeriod').val() > $('#s_endPeriod').val()){
			alert('시작일이 종료일 보다 큽니다');
			return false;
		}
		
		if($('#s_startPeriod').val() == $('#s_endPeriod').val()) {
	         if($('#studyStartTime').val() > $('#studyEndTime').val()){
	            alert('시작시간이 종료시간보다 큽니다.');
	            return false;
	         }
	      }
		
		if($("input[name=s_day]").is(":checked")==false) {
			alert("적어도 하나 이상의 요일을 선택해주세요.");
			return false;
		}
		
		if($('#s_startPeriod').val() == new Date()) {
			if($("input[name=s_startTime]") < new Date().getTime()) {
				alert('시작 시간이 현재 시간보다 작습니다.');
				return true;
			}
		}
	}
	
	$('.timepicker').timepicker({
	    timeFormat: 'HH:mm',
	    interval: 5,
	    startTime: '00:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
	
	
</script>
	</div>
	<footer>
		<%@ include file='/WEB-INF/views/common/footer.jsp'%>
	</footer>
</body>
</html>