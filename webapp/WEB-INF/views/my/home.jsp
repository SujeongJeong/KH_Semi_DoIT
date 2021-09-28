<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 내 정보</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<link href='<%= request.getContextPath() %>/resources/css/qna-main.css?after' rel='stylesheet'>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<%
	if(request.getAttribute("result") != null) {
		if(request.getAttribute("result").equals("success")) {
%>
<script>
	alert('회원 탈퇴가 완료 되었습니다. 복구 관련 사항은 관리자에게 문의하세요.');
	opener.location.href='http://localhost:8800/Do_IT/';
	window.close();
</script>
<%
	} else {
%>
<script>
	alert('회원 탈퇴에 실패했습니다');
	window.close();
</script>
<%
		}
	}
%>
<style>
	/* 전체 감싸는 div */
	.my_wrap {
		display: flex;
	}
	button {
		border : 0; 
		border-radius : 5px;
	}
	/* 사이드메뉴 영역 */
	.side_menu{
	    width: 160px;
	    padding-left : 5%;
	}
	.side_menu ul {
		padding-left: 40px;
		list-style-type:none;
	}
	.side_menu li {
		 margin: 60px 0;
	}
	.side_menu a{
		text-decoration:none;
		display : block;
		margin : 10px;
		color : #C4C4C4;
		width : 100px;
		text-align : left;
		font-size : 15pt;
	}
	.side_menu a:hover:not(.current){
		color : #5FC5FF;
	}
	
	.side_menu .current a{
		color : #5FC5FF;
		font-weight : bold;
	}
	/* 콘텐츠 영역 */
	.areas {
		display: flex;
		border-bottom: 1px dotted lightgray;
	}
	.profile_area {
		border-right: 1px dotted lightgray;
	}
	/* 내 정보 area */
	.profile_area {
		width: 500px;
		height: 300px;
	}
	.change_area {
		display: flex;
	 	justify-content: space-between;
	 	padding: 0 10px;
	}
	div[class$=box] {
		height: 150px;
	}
	/* left 박스 */
	.left_box {
		width: 150px;
	}
	.profile_area h2 {
		text-align: right;
		padding-right: 10px;
	}
	.img_area {
		position: relative;
		border : 0; 
		border-radius : 5px;
		
	}	
	#profile_img {
		 position: absolute;
	     width: 150px;
	     height: 150px;
	     border: 1px solid black;
	     border-radius: 50%;
	}
	#camera_btn {
		 position: absolute;
	     width: 50px;
	     height: 50px;
	     border: 1px solid black;
	     border-radius: 50%;
         top: 100px;
         left: 100px;
         padding: 0;
	}
	#camera_btn:hover {
		cursor: pointer;
	}
	#camera_img {
		width: 50px;
	    height: 50px;
	    border-radius: 50%;
	}
	.changeBtn {
	    maring-left:6px;
		background: #5FC5FF;
		color: white;
	}
	
	/* right 박스 */
	.right_box {
		width: 300px;
	}
	#nickForm label{
		display: block;
		margin-bottom: 5px;
	}
	input[name=nickName] {
		width: 160px;
		height: 30px;
	}
	#nickForm {
		margin-bottom: 20px;
	}
	#setPwd_btn {
		width: 50%;
	}
	#delUser_btn {
		width: 40%;	
	}
	.right_box button {
		height: 30px;
	}
	
	
	/* 목표 공부 시간 area */
	.target_area {
		width: 400px;
		height: 300px;
	}
	.target_area h2 {
		padding-left: 10px;
	}
	.target_area h4 {
		text-align: center;
	}
	.setHour_area{
		text-align: center;
	}
	.setHour_area button {
		height: 30px;
	}
	.set_area {
		margin-top: 40px;
	}
	input[type=number] {
		height: 30px;
	}
	
	
	
	/* 공부 기록 list */
	.recode_area {
		padding-top: 10px;
	}
	#nick > h2 {
		display: inline;
		padding-left: 10px;
		color : #5FC5FF;
	}
	.recode_box {
		display: flex;
		justify-content: space-around;
		margin: 10px 0;
		height: 100px;
		padding-top: 20px;
	}
	.recode_box > div[class^=rec] {
		width: 200px;
		height: 100px;
		text-align: center;
		border: 1px solid #E5E5E5; 
		border-radius: 5px;
	}
	.time_box {
		padding-bottom: 10px;
		margin: 0;
	}
	.recode_box1 {
		display: flex;
		justify-content: space-between;
		padding: 0 10px;
		margin-bottom: 10px;
	}
	.recode_btn > button {
		border: 0;
		background: white;
		font-size: 1.3em;
		padding: 0;
	}
	.recode_btn > button:hover {
		cursor: pointer;
	}
	.recode_btn > div {
		font-size: 1.2em;
	}
	.recode_btn > div > button {
		border: 0;
		background: white;
		padding: 0;
	}
	.recode_btn > div > button:hover {
		cursor: pointer;
	}
	.recode_span > span {
		font-size: 1.2em;
	}
	#modify_img {
		display: none;
	}
</style>
</head>
<body>
<%@ include file='/WEB-INF/views/common/menubar.jsp' %>

	<div class="my_wrap">
		<nav class="side_menu">
			<ul>
				<li class="current"><a href="#">내 정보</a></li>
				<li><a href="<%= request.getContextPath() %>/my/study">내 스터디</a></li>
				<li><a href="<%= request.getContextPath() %>/my/q&a">내 Q&A</a></li>
				<li><a href="<%= request.getContextPath() %>/my/details">결제 내역</a></li>
			</ul>
		</nav>
		<div class="content">
			<div class="areas">
				<div class="profile_area">
					<h2>${ loginUser.userEmail }</h2>
					<div class="change_area">
						<div class="left_box">
							<form class="img_area" name="imgForm" method="post" enctype="multipart/form-data">
								<img id="profile_img" src="${ contextPath }${ loginUser.profileImg }" alt="프로필 이미지">
								<input type="file" id="modify_img" name="modify_img" accept="image/gif, image/jpeg, image/png">
								<button id="camera_btn" type="button"><img id="camera_img" src="<%= request.getContextPath() %>/resources/images/camera.png" alt="카메라 이미지"></button>
							</form>
						</div>
						<div class="right_box">
							<form id="nickForm" action="<%= request.getContextPath() %>/my/modifyNick" method="post" onsubmit="return validate();">
								<label>닉네임</label>
								<input type="text" name="nickName" value="${ loginUser.nickName }">
								<button class="changeBtn" id="nickCheck" type="button">중복확인</button>
		               			<button class="changeBtn" id="nick_change">변경</button>
							</form>
	               			<div class="btn_area">
	               				<button id="setPwd_btn" onclick="openPopup('<%= request.getContextPath() %>/my/modifyPwd', 'modifyPwd', 500, 500);">비밀번호 변경</button>
	               				<button id="delUser_btn" onclick="openPopup('<%= request.getContextPath() %>/my/withdrawal', 'withdrawal', 500, 500);">회원 탈퇴</button>
	               			</div>
						</div>
					</div>
				</div> 
				<div class="target_area">
					<h2>하루 목표 공부 시간</h2>
					<div class="set_area">
						<form id="setHourForm" class="setHour_area" action="<%= request.getContextPath() %>/my/setHour" method="post">
							<c:set var="TargetHour" value="${ fn:split(loginUser.targetHour, '/') }"/>
							<input id="hour" name="hour" type="number" min="0" max="24" value="${TargetHour[0]}" > 시간
							<input id="min" name="min" type="number" min="0" max="59" value="${TargetHour[1]}"> 분 
							<button class="changeBtn" id="hour_change">변경</button>
							<button type="button" onclick="resetSetHour();">취소</button>
						</form>
						<h4><i>"하기 싫은 걸 해야 하고 싶은걸 한다"</i></h4>
					</div>
					
				</div>
			</div>
			<div class="recode_area">
				<div id="nick"><h2>${ loginUser.nickName }</h2> <span>님의 공부 기록</span></div>
				<div class="recode_box">
					<div class="rec_box1">
						<p>오늘 공부 시간</p>
						<div><h1 class="time_box">00:00:00</h1></div>
					</div>
					<div class="rec_box1">
						<p>일 평균 공부 시간</p>
						<div><h1 class="time_box">00:00:00</h1></div>
					</div>
					<div class="rec_box1">
						<p>전체 공부 시간</p>
						<div><h1 class="time_box">00:00:00</h1></div>
					</div>
				</div>
			</div>
			<div>
				<div class="recode_box1">
					<div><span id="standard"></span></div>
					<div>평균 공부시간</div>
				</div>		
				<table class="board_list">
					<thead>
			        	<tr>
			            <th>날짜</th>
			            <th>공부 시간</th>
			            </tr>          
			         </thead>
			         <tbody>
			           	<c:forEach var="sl" items="${ StudyRecodeList }">
							<tr>
								<td><fmt:formatDate value="${ sl.studyDate }" type="both" pattern="yyyy.MM.dd"/></td>
								<td class="formatTime">${ sl.studyTime }</td>
							</tr>
						</c:forEach>
			          </tbody>
				</table>
				<ul class="board_paging">
                        <li><a href="${ contextPath }/my/home?page=1${ searchParam }">&lt;&lt;</a></li>
                        
                        <!-- 이전 페이지로(<) -->
                        <li>
                        <c:choose>
                           <c:when test="${ pi.page > 1 }">
                           <a href="${ contextPath }/my/home?page=${ pi.page - 1}${ searchParam }">&lt;</a>
                           </c:when>
                           <c:otherwise>
                           <a href="#">&lt;</a>
                           </c:otherwise>
                        </c:choose>
                        </li>
                        
                        <!-- 페이지 목록(최대 10개) -->
                        <c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
                        <li>
                           <c:choose>
                              <c:when test="${ p eq pi.page }">
                                 <a href="#" class="current_page">${ p }</a>
                              </c:when>
                              <c:otherwise>
                                 <a href="${ contextPath }/my/home?page=${ p }${ searchParam }">${ p }</a>
                              </c:otherwise>
                           </c:choose>
                        </li>
                        </c:forEach>
                        
                        <!-- 다음 페이지로(>) -->
                        <li>
                        <c:choose>
                           <c:when test="${ pi.page < pi.maxPage }">
                           <a href="${ contextPath }/my/home?page=${ pi.page + 1 }${ searchParam }">&gt;</a>
                           </c:when>
                           <c:otherwise>
                           <a href="#">&gt;</a>
                           </c:otherwise>
                        </c:choose>
                        </li>
                        
                        <!-- 맨  끝으로(>>) -->
                        <li><a href="${ contextPath }/my/home?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
                     </ul>  
			</div>
		</div>
	</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	<script>
	$("#camera_btn").click(function() {
		$('#modify_img').click();
	});
	
	var isUsable = false;
	
	function validate() {
		if(!isUsable) {
			alert("중복체크를 진행해주세요");
			nickname.focus();
			return false;
		}
		
		return true;
	}
	
	$("#nickCheck").click(function nickCheck(){
		// input userId 변수
		var nickname = $("[name=nickName]");
		// 아이디 중복 시 false, 아이디 사용 가능 시 true
		
		var regExp = /^[가-힣a-zA-Z\d]{2,10}$/; 
		
		if(!nickname || !regExp.test(nickname.val()) ) {
			alert('닉네임은 2~10자 한글, 숫자, 영어 대 소문자를 입력하세요.');
			nickname.focus();
		} else {
			// 4자리 이상의 userId 값을 입력 했을 경우 중복 확인을 위해 ajax 통신 요청
			$.ajax({
				url : "${ contextPath }/nickCheck",
				type : "post",
				data : { nickname : nickname.val() },
				success : function(result){
					console.log(result);
					if(result == "fail") {
						alert("이미 사용중인 닉네임입니다.");
						nickname.focus();
					} else {
						if(confirm('사용 가능한 닉네임입니다. 사용하시겠습니까?')) {
							// 더 이상 id 입력 공간을 수정할 수 없도록 readonly 처리
							nickname.attr('readonly', true);
							isUsable = true; // 사용 가능한 아이디라는 flag 값
						} else {
							// confirm 창에서 취소를 누를 경우(처음 , 또는 반복해서) 다시 id 수정 가능하도록
							nickname.attr('readonly', false);
							nickname.focus();
							isUsable = false; // 사용 불가능한 아이디라는 flag 값
						}
					}
					// 아이디 중복 체크 후 중복이 아니며 사용하겠다고 선택한 경우에만
					// joinBtn disable 제거
					if(isUsable) {
						$("#nick_change").removeAttr("disabled");
					} else {
						$("#nick_change").attr("disabled", true);
					}
				},
				error : function(e){
					console.log(e);
				}
			});
		}
	}); 
	
	function openPopup(url, title, width, height) {
		let left = (document.body.clientWidth/2) - (width/2);
		// 듀얼모니터를 위한 계산
		left += window.screenLeft;
		let top = (screen.availHeight/2) - (height/2);
		
		let options = "width="+width+",height="+height+",left="+left+",top="+top;
		
		window.open(url, title, options);
	};
	
	
	$("#hour").change(function() {
		if($('#hour').val() == "24") {
			$('#min').val("0");
			$('#min').prop("max", "0");
		} else {
			$('#min').prop("max", "59");
		}
	});
	
	function resetSetHour() {
		$('input[name=hour]').val(${TargetHour[0]});
		$('input[name=min]').val(${TargetHour[1]});
	};
	
	$('#modify_img').change(function(){
		document.forms.imgForm.action = '${contextPath}/my/modifyImg';
		document.forms.imgForm.submit();
	});

	$('input:radio[name=study_recode]').change(function(){
	      document.forms.recodeForm.action = '${contextPath}/my/home';
	      document.forms.recodeForm.submit();
	   });
	
	  $(document).ready(function() {
	    var totalTime = $('.formatTime').html();

	    var hour = Math.floor(totalTime / 3600) <  10  ? '0'+ Math.floor(totalTime / 3600) : Math.floor(totalTime / 3600);
		var minute = Math.floor((totalTime % 3600) / 60) <  10 ? '0'+ Math.floor((totalTime % 3600) / 60) : Math.floor((totalTime % 3600) / 60);
		var second = (totalTime % 3600) % 60 < 10 ? '0'+ (totalTime % 3600) % 60 : (totalTime % 3600) % 60;
	    
		var str = hour + ":" + minute + ":" + second;
	    $('.formatTime').html(str); 
	    console.log(str);
	});  
	
	/*  function formatTime(time) {
		var totalTime = time;

	    var hour = Math.floor(totalTime / 3600) <  10  ? '0'+ Math.floor(totalTime / 3600) : Math.floor(totalTime / 3600);
		var minute = Math.floor((totalTime % 3600) / 60) <  10 ? '0'+ Math.floor((totalTime % 3600) / 60) : Math.floor((totalTime % 3600) / 60);
		var second = (totalTime % 3600) % 60 < 10 ? '0'+ (totalTime % 3600) % 60 : (totalTime % 3600) % 60;
	    
		return hour + ":" + minute + ":" + second;
	} 
	
	var today = new Date();
	var standard = new Date(today.setDate(today.getDate() - 30 ));
	document.getElementById("standard").innerHTML = standard.getFullYear()+"년 "+ (standard.getMonth()+1)+"월 " + standard.getDate()+"일 "+ " 0시 ~ 오늘 기준";
		
	$('.formatTime').ready(function(){
		var totalTime = $('.formatTime').html();

	    var hour = Math.floor(totalTime / 3600) <  10  ? '0'+ Math.floor(totalTime / 3600) : Math.floor(totalTime / 3600);
		var minute = Math.floor((totalTime % 3600) / 60) <  10 ? '0'+ Math.floor((totalTime % 3600) / 60) : Math.floor((totalTime % 3600) / 60);
		var second = (totalTime % 3600) % 60 < 10 ? '0'+ (totalTime % 3600) % 60 : (totalTime % 3600) % 60;
	    
		$('.formatTime').html(hour + ":" + minute + ":" + second);
		console.log(hour + ":" + minute + ":" + second);
	});
	 */

	</script>
</body>
</html>