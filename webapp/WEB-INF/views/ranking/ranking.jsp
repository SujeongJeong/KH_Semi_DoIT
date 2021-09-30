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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<style>
ul{	list-style: none;	}
.ranking-wrapper li{ font-size : 20px; padding: 10px; }
.ranking-wrapper li span:not(.nickname){ margin-right : 30px; margin-left : 20px; }
.ranking-wrapper{ margin-top : 35px;}
.title{ font-weight : bold;  font-size : 18px;  }
.standard-wrapper{  height: 20px; }
.standard-wrapper span{ float : right; height : 20px; font-size : 15px; color : #c4c4c4; padding : 10px;}
.ranking { border-bottom: 1px solid #E5E5E5; }
.me{ background-color : #E5E5E5; border-radius : 5px; margin-top : 10px;}
.group-wrapper, .period-wrapper { margin-left : 10px; margin-bottom : 15px;}
.group-wrapper *:not([type=radio],select), .period-wrapper *:not([type=radio]) { margin-right : 30px; }
.outer{ border : 1px solid #E5E5E5; border-radius : 5px; height : 80px; margin-left :25px; padding-left : 20px; padding-top:5px;  }
select{ padding : 3px;}
.img-wrapper{ width:30px; height:30px;}
.img-wrapper img{ width:30px; height:30px;}
.hours{ padding-left : 25px;}
</style>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
	<div class="content">
		<div class="outer">
			<div class="group-wrapper">
			<span class="title">범위  </span>
			<input type="radio" id="all" name="group" value="all" checked><label for="all">전체</label>
			<input type="radio" id="study" name="group" value="study"><label for="study">스터디방</label>
			<select for="study" id="studyList">
				<option value="0" >스터디목록</option>
				<c:choose>
				<c:when test="${ loginUser != null  and Study != null }">
					<c:forEach var="s" items="${ Study }">
					<option value="${ s.s_no }">${ s.s_name }</option>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<option value="0">=========</option>
				</c:otherwise>
				</c:choose>
			</select>
			</div>
			<div class="period-wrapper">
			<span class="title">기간  </span>
			<input type="radio" class="period" name="period" id="yesterday" value="yesterday" checked><label for="yesterday">어제</label>
			<input type="radio" class="period" name="period" id="week" value="week"><label for="week">최근 7일</label>
			<input type="radio" class="period" name="period" id="month" value="month"><label for="month">최근 30일</label>		
			</div>
		</div>	
			
		<div class="standard-wrapper">
			<span id="standard"></span>			
		</div>	
		
		<ul class="ranking-wrapper" >
		 <c:forEach var="r" items="${ Ranking }">
		 	<li class="ranking"><img src="${ contextPath }${ r.rank_img }" alt="1위">
		 	<span>${ r.rank }위</span>
		 	<span class="nickname">${ r.nickName }</span>
		 	<span class="img-wrapper"><img src="${contextPath }${ r.profile_img}" alt="profile"></span>
		 	<span class='hours'>${ r.s_time }</span></li>
		 </c:forEach>
			<c:if test="${ loginUser == null }">
			<li class="me"><img src="resources/images/flag-me.png" alt="me">
			<span class="nickname">로그인하여 나의 랭킹을 확인해보세요.</span></li>			
			</c:if>
			<c:if test="${ loginUser != null }">
			<li class="me"><img src="resources/images/flag-me.png" alt="me"><span>${ myRanking.rank }위</span>
			<span class="nickname">${ myRanking.nickName }</span>
			<span class="img-wrapper"><img src="${ contextPath }${ myRanking.profile_img }" alt="profile"></span>
			<span class='hours'>${ myRanking.s_time }</span></li>			
			</c:if>
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
		$("#studyList").click(function(){
			if(confirm("로그인 하시겠습니까?")){
				 location.href="${ contextPath}/login";
			 }
		});
	</script>
	</c:if>
	
	
	<script>
	<%-- select 클릭 시 스터디방 checked, 기준 yesterday, 스터디방 num 가져오기 --%>
	var sNum = 1;
		$("#studyList").click(function(){
			$("#study").click();
		});
		
    $("#study").click(function(){
    	$("#yesterday").click();
    	$('#studyList option:eq(1)').attr('selected','selected');
    });
	<%-- 기간 라디오 버튼 누를때마다 기준 날짜, 랭킹 다르게 출력 --%>
	var span = document.getElementById("standard");
	var days = ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'];

	// standard 기본 값 함수
	$(function(){
		var today = new Date();
		var period = $(".period:checked").val();
		var standard = null;
		if(period == "yesterday"){
			standard = new Date(today.setDate(today.getDate() -1 ));
		}
		var days = ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'];
		span.innerHTML = standard.getFullYear()+"년 "+ 
						(standard.getMonth()+1)+"월 " + standard.getDate()+"일 "+ 
						 days[standard.getDay()]+" 0시 기준";
	});
	
	// 조건 별 랭킹 출력
	$("input[type=radio]").click(function(){
		var group = $("input[name='group']:checked").val();
		var period = $("input[name='period']:checked").val();
		var standard = null;
		var today = new Date();
		sNum = $("option:selected").val();
		// 기준날짜
		if(period == "yesterday"){
			standard = new Date(today.setDate(today.getDate() -1 ));
			span.innerHTML = standard.getFullYear()+"년 "+ (standard.getMonth()+1)+"월 " 
							 + standard.getDate()+"일 "+ days[standard.getDay()]+" 0시 기준";
			
		}else if(period == "week"){
			standard = new Date(today.setDate(today.getDate() -7 ));
			span.innerHTML = standard.getFullYear()+"년 "+ (standard.getMonth()+1)+"월 " 
				             + standard.getDate()+"일 "+ days[standard.getDay()]+" 0시 ~ 어제 기준";
			
		}else if(period == "month"){
			standard = new Date(today.setDate(today.getDate() -30 ));
			span.innerHTML = standard.getFullYear()+"년 "+ (standard.getMonth()+1)+"월 " + 
			                 standard.getDate()+"일 "+ days[standard.getDay()]+" 0시 ~ 어제 기준";
		}
		
		// 랭킹
		$.ajax({
			url : "${ contextPath }/ranking/selectRanking",
			data : {group : group , period: period, sNum: sNum},
			type : "post",
			dataType : "json",
			success : function(map){
				if( map != null){
					var html ='';
					for(var key in map.list){
						html += '<li class="ranking"><img src=\"/Do_IT'+ map.list[key].rank_img +'\" alt="1위">'+
					 			'<span>'+map.list[key].rank +'위</span>'+
					 			'<span class="nickname">'+ map.list[key].nickName +'</span>'+
					 			'<span class="img-wrapper"><img src=\"/Do_IT'+ map.list[key].profile_img +'\" alt="profile"></span>'+
					 			'<span class="hours">'+ map.list[key].s_time +'</span></li>';
					}
					
					html += '<li class="me"><img src="resources/images/flag-me.png" alt="me"><span>'+map.my.rank+'위</span>'+
						  '<span class="nickname">'+ map.my.nickName +'</span>'+
						  '<span class="img-wrapper"><img src=\"/Do_IT'+ map.my.profile_img +'\" alt="profile"></span>'+
						  '<span class="hours">'+ map.my.s_time +'</span></li>';
					
					$(".ranking-wrapper").html(html);
				}else{
					alert("랭킹을 불러오는데 실패하였습니다.");
				}
			},
			error : function(e){
				console.log(e);
			}
		});
	});
	
	</script>
</body>
</html>