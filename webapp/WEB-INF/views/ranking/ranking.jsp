<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>랭킹 - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css?after' rel='stylesheet'>
<style>
ul{	list-style: none;	}
.ranking-wrapper li{ font-size : 20px; padding: 10px; }
.ranking-wrapper li span{ margin-right : 10px; margin-left : 10px; }
.ranking-wrapper { margin-top : 35px;}
.title{ font-weight : bold;  font-size : 18px;  }
.standard-wrapper {  height: 20px; }
.standard-wrapper span { float : right; height : 20px; font-size : 15px; color : #E5E5E5; padding : 10px;}
.ranking { border-bottom: 1px solid #E5E5E5; }
.me { background-color : #E5E5E5; border-radius : 5px; margin-top : 10px;}
.group-wrapper, .period-wrapper { margin-left : 10px; margin-bottom : 15px;}
.group-wrapper *:not([type=radio],select), .period-wrapper *:not([type=radio]) { margin-right : 30px; }
.outer { border : 1px solid #E5E5E5; border-radius : 5px; height : 80px; margin-left :25px; padding-left : 20px; padding-top:5px;  }
select { padding : 3px;}
</style>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
	<div class="content">
		<div class="outer">
			<div class="group-wrapper">
			<span class="title">범위  </span>
			<input type="radio" name="group" value="all" checked><label for="all">전체</label>
			<input type="radio" id="study" name="group" value="study"><label for="study">스터디방</label>
			<select id="studyList">
				<option>스터디목록</option>
				<option>스터디방1</option>
				<option>스터디방2</option>
			</select>
			</div>
			<div class="period-wrapper">
			<span class="title">기간  </span>
			<input type="radio" class="period" name="period" value="yesterday" checked><label for="yesterday">어제</label>
			<input type="radio" class="period" name="period" value="week"><label for="week">이번주</label>
			<input type="radio" class="period" name="period" value="month"><label for="month">이번달</label>		
			</div>
		</div>	
			
		<div class="standard-wrapper">
			<span id="standard">나나</span>			
		</div>	
		
		<ul class="ranking-wrapper" >
			<li class="ranking"><img src="resources/images/flag-first.png" alt="1위"><span>1위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag-second.png" alt="2위"><span>2위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag-third.png" alt="3위"><span>3위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="4위"><span>4위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="5위"><span>5위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="6위"><span>6위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="7위"><span>7위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="8위"><span>8위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="9위"><span>9위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="last"><img src="resources/images/flag.png" alt="10위"><span>10위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="me"><img src="resources/images/flag-me.png" alt="me"><span>?위</span><span class="nickname">${ loginUser.nickName }</span><span class='hours'>00:00:00</span></li>			
		</ul>

	</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	<%-- 로그인 안했을 때 --%>
	<c:if test="${ loginUser == null }">
	<script>
		$("#study").click(function(){
			if(confirm("로그인 하시겠습니까?")){
				 location.href="${ contextPath}/login";
			 }
		});
	</script>
	</c:if>
	<%-- 기간 라디오 버튼 누를때마다 기준 날짜 다르게 출력 --%>
	<script>
	$(document).on('change', '.period', function(){
		var period = $(".period:checked").val();
		var span = document.getElementById("standard");
		var today = new Date();
		var standard = null;
	
		// 월요일 구하는 함수
		function getMondayDate(d) {
		    var paramDate = d;
		    var day = paramDate.getDay();
		    var diff = paramDate.getDate() - day + (day == 0 ? -6 : 1);
		    return new Date(paramDate.setDate(diff));
		}
		
		if(period == "yesterday"){
			standard = new Date(today.setDate(today.getDate() -1 ));
		}else if(period == "week"){
			standard = getMondayDate(today);
		}else if(period == "month"){
			standard = new Date(today.getFullYear(), today.getMonth(),1);
		}
		// 날짜 포맷 변경
		var days = ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'];
		span.innerHTML = standard.getFullYear()+"년 "+ (standard.getMonth()+1)+"월 " + standard.getDate()+"일 "+ days[standard.getDay()]+" 0시 기준";
		

	});
	
	// standard 기본 값 함수
	$(function(){
		var period = $(".period:checked").val();
		var today = new Date();
		var span = document.getElementById("standard");
		if(period == "yesterday"){
			standard = new Date(today.setDate(today.getDate() -1 ));
		}
		var days = ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'];
		span.innerHTML = standard.getFullYear()+"년 "+ (standard.getMonth()+1)+"월 " + standard.getDate()+"일 "+ days[standard.getDay()]+" 0시 기준";
	});
	</script>
	
</body>
</html>