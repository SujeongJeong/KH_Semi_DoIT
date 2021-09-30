<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>

<style>
* {
	margin : 0px;
}

#top{
	border : 1px solid black;
	width:100%;
	height:75px;
	background-color:#959393;
	align-items: center;
	
}

#top_content{
    text-align: center;
    font-size: 2em;
    align-self: center;
    margin: 16px;
    font-weight:700;
}

#top2{
	border-bottom:1px solid black;
	width:100%;
	height:55px;
	display:flex;
	justify-content:space-around;
	background-color:#ddd;
}

.top2_content{
	text-align:center;
	margin:16px;
	font-size:1.15em;
	font-weight:550;
}

#mid{
	display:grid;
	height:calc(100vh - 150px);
	word-wrap: break-word;
    word-break: keep-all;
    overflow: auto;
}

.mid_content{
	display:flex;
	border-bottom : 1px solid #ddd;
	height:50px;
	text-align:center;
	align-items:center;
	justify-content:space-between;
	padding-left:15px;
	padding-right:38px;
	
}

.study_member{
	display:flex;
	align-items:center;
}

.grid_img{
	width:25px;
	height:25px;
}

.grid_nickName{
	margin-left:5px;
	text-align:center;
}

.time_today{
	margin-right:53px;
}

.time_weekend{
	margin-right:26px;
}

#mid_content{
	padding-right:189px;
}

#time_today{
	padding-right:6px;
}

</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
</head>
<body>
	<div id="content-wrapper">
		<div id="top">
			<div id="top_content">회원 관리</div>
		</div>
		<div id="top2">
			<div id="top2_content1" class="top2_content">스터디 회원</div>
			<div id="top2_content2" class="top2_content">오늘 공부시간</div>
			<div id="top2_content3" class="top2_content">이번주 공부시간</div>
			<div id="top2_content4" class="top2_content">내보내기</div>
		</div>	
		<div id="mid">
		
			<div class="mid_content" id="mid_content">
				<div class="study_member">
					<img class="grid_img" alt="#" src="<%= request.getContextPath() %>${ managerAccount.profileImg }">
					<div class="grid_nickName">${ managerAccount.nickName } (방장)</div>
				</div>
				<div class="time_today" id="time_today">hh:mm:ss</div>
				<div class="time_weekend" id="time_weekend">hh:mm:ss</div>
			</div>
			
			<c:forEach items="${ MemberList }" var="m" begin="0" end="${ MemberList.size() }">
				<div class="mid_content">
					<div class="study_member">
						<img class="grid_img" alt="#" src="<%= request.getContextPath() %>${ m.profileImg }">
						<div class="grid_nickName">${ m.nickName }</div>
					</div>
					<div class="time_today">hh:mm:ss</div>
					<div class="time_weekend">hh:mm:ss</div>
					<div class="member_out"><button class="member_out_btn" onclick="member_outEvent(${m.userNo},${ s_no });">내보내기</button></div>
				</div>
			</c:forEach>
		</div>
	</div>

	<script>
		function member_outEvent(user_no,s_no){
			
			if(confirm("해당 회원을 내보내시겠습니까?")){
				$(function(){
					$.ajax({
						url : "${ pageContext.servletContext.contextPath }/study/enterStudy/memberOutFuncion",
						data : { user_no : user_no, s_no : s_no },
						type : "post",
						success : function(result){
							console.log("회원 탈퇴시키기 성공");
							alert("해당 회원을 내보냈습니다.")
						},
						error : function(e){
							console.log("회원 탈퇴시키기 실패(알 수 없는 오류)");
							console.log(e);
						}
					});
				});
			}
		}
	
	</script>




















</body>
</html>