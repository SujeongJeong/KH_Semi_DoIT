<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<style>
* {
	margin: 0px;
	padding : 0px;
}

#top {
	width: 100%;
	height: 85px;
	background-color: black;
}

#top-content{
	padding-top : 17px;
}

#title {
	display: inline-block;
    color: white;
    font-size: 2.0em;
    text-align: center;
    margin-left: 70px;
}

#currentTO{
	display: inline-block;
    color: white;
    font-size: 2em;
   
}

#img1{
	width: 25px;
    height: 25px;
    margin-left: 55px;
}

#top1-content-wrapper{
	display : inline-block;
	position : absolute;
	left : 1225px;
}

#member-management {
	
    display: inline-block;
    background-color: white;
    width: 120px;
    height: 50px;
    text-align: center;
    border-radius: 30px;
    font-size: 1.05em;
}

#out {
	display: inline-block;
    background-color: red;
    margin-left: 15px;
    width: 120px;
    height: 50px;
    text-align: center;
    border-radius: 30px;
    font-size: 1.05em;
    color: white;
}

#mid-wrapper {
    display: inline-block;
    position: relative;
    width: 99.9%;
    height: 651px;
}

#mid1 {
	display: inline-block;
	position:relative;
	border: 1px solid black;
	width: 78%;
	height: 99.9%;
}

#mid2 {
	display: inline-block;
	position : absolute;
	border: 1px solid black;
	width: 22%;
	height: 99.9%;
	
}
#joinMember-title, #joinMember-TO{
	display : inline-block;
	font-size: 1.35em;
    text-align: center;
    margin : 10px;
}

#joinMember-TO{
	margin-left : 85px;
}

#joinMember-wrapper{
	border-bottom: 1px solid gray;
}

#currentJoinMember-wrapper{
	height:250px;
	border-bottom: 1px solid gray;

	word-wrap: break-word;
	word-break: keep-all;
	overflow: auto;
}
.currentJoinMember-content{
	position:relative;
	height:40px;
	border : 1px solid black;
}

#chat-title-content{
	margin:10px;
	font-size: 1.5em;
}

#chat-title{
	border-bottom: 1px solid gray;
}

#chat-content{
	height:250px;
	border-bottom: 1px solid gray;
	
	word-wrap: break-word;
	word-break: keep-all;
	overflow: auto;
	
}

#chat-input{
	height:42px;
}

#chat{
	margin:5px;
	width:95%;
	height:30px;
}

#mid1-gridContainer{
	position:relative;
	display:grid;
	grid-template-columns: repeat(5, 1fr);
	grid-template-rows: 162.75px 162.75px 162.75px 162.75px;
}

.studyGrid{
	position:relative;
	border : 1px solid black;
}

#gridImg1{
	position: absolute; 
	top:0; 
	left: 0;
	width: 100%;
	height: 100%;
}

#gridImg2{
	position:absolute;
	top:5px;
	left:5px;
}

#currentJoinMember-content-nickName{
	position:absolute;
	text-align:center;
	top:7px;
	left:45px;
}

#gridNickname{
	position:absolute;
	text-align:center;
	top:10px;
	left:15px;
	font-size:1.2em;
}

#stopWatch{
	position: absolute;
    text-align: center;
    left: 186px;
    font-size: 1.2em;
    background-color: black;
    border-bottom-left-radius: 7px;
    color: white;
    padding: 8px;
    padding-left: 20px;
}

#reportImg{
	position:absolute;
	width:35px;
	height:35px;
	left:270px;
	top:2px;
}

#timer-wrapper {
	position: absolute;
	top: 80%;
	background-color: black;
}


.timer {
	background: none;
	border: 0;
	color: white;
	text-align: center;
	padding: 13px;
}


</style>
<body>
	<div id="top">
		<div id="top-content">
			<div id="title">${ study.s_name }</div>
			<div id="currentTO">
				<img src="/Do_IT/resources/images/studyicon2.jpg" id="img1">
				${ study.s_to }
			</div>
			<div id="top1-content-wrapper">
				<button id="member-management" onclick="">회원 관리</button>
				<button id="out" onclick="outBtn()">나가기</button>
			</div>
		</div>
	</div>

	<div id="mid-wrapper">
		<div id="mid1">
			<div id="mid1-gridContainer">
				
				<c:forEach items="${ MemberList }" var="m" begin="0" end="${ MemberList.size() }">
					<div class="studyGrid" id="studyGrid">
						<img src="<%= request.getContextPath() %>${ m.profileImg }" id="gridImg1">
						<div id="gridNickname">${ m.nickName }</div>
						<div id="stopWatch">hh:mm:ss</div>
						<c:if test="${ m.userNo == loginUserNo }">
							<div id="timer-wrapper">
								<button class="timer start" onclick="startBtn()">▷</button>
								<button class="timer stop" onclick="stopBtn()">||</button>
								<button class="timer end" onclick="endBtn()">ㅁ</button>
								
							</div>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>

		<div id="mid2">
			<div id="joinMember-wrapper">
				<div id="joinMember-title">참가자 목록</div>
				<div id="joinMember-TO">${ StudyMemberCount } / ${study.s_to }</div>
			</div>
			<div id="currentJoinMember-wrapper">
				<c:forEach items="${ MemberList }" var="m" begin="0" end="${ MemberList.size() }">
					<div class="currentJoinMember-content">
						<img src="<%= request.getContextPath() %>${ m.profileImg }" id="gridImg2">
						<span id="currentJoinMember-content-nickName">${ m.nickName }</span>
						<span id="reportMember"><img src="/Do_IT/resources/images/reporticon4.jpg" id="reportImg"></span>
					</div>
				</c:forEach>	
			</div>
			<div id="chat-title">
				<div id="chat-title-content">채팅</div>
			</div>
			<div id="chat-content">채팅구간</div>
			<div id="chat-input">
				<input type="text" id="chat" placeholder="대화를 입력하세요">
			</div>
		</div>
	</div>
	
	<script>
	var gridInput = document.querySelector('#mid1-gridContainer');
	var gridNickname = document.querySelector('#gridNickname');
	
	
	
	if( ${study.s_to} != null ){
		if( ${ study.s_to } == 5){
			gridInput.style.gridTemplateColumns = 'repeat(3,1fr)';
			gridInput.style.gridTemplateRows = '325.5px 325.5px';
		}
		if( ${ study.s_to } == 10){
			gridInput.style.gridTemplateColumns = 'repeat(4,1fr)';
			gridInput.style.gridTemplateRows = '217px 217px 217px';
		}
		if( ${ study.s_to } == 15){
			gridInput.style.gridTemplateColumns = 'repeat(4,1fr)';
			gridInput.style.gridTemplateRows = '162.75px 162.75px 162.75px 162.75px';
		}
		if( ${ study.s_to } == 20){
			gridInput.style.gridTemplateColumns = 'repeat(5,1fr)';
			gridInput.style.gridTemplateRows = '162.75px 162.75px 162.75px 162.75px';
		}
	}
		function outBtn(){
			var out = confirm("정말 나가시겠습니까?");
			if(out == true){
				history.back();
			}
		}
		
		
		
	
	// 타이머
	var start;
	var stop;
	var allTime = 0;
	var startParam = false;
	
	
	//시작버튼
	function startBtn(){
		start = new Date();
		startParam = true;
		console.log("starttime : "+ start);
	}
	// 정지버튼
	function stopBtn(){
		if(startParam == false){
			
		} else {
			stop = new Date();
			var time = stop-start;
			allTime += time;
			startParam = false;
			
			console.log("time : "+ time);
			console.log("alltime : "+allTime);
		}
	}
	// 종료버튼
	function endBtn(){
	
		if(allTime != 0){
			var save = confirm("공부 시간을 저장하시겠습니까?");
			
			if(save){
				
				if(startParam == true){
					
					stop = new Date();
					var time = stop-start;
					allTime += time;
					var dbSaveTime = Math.round(allTime/1000);
					
					console.log("dbSaveTime : " + dbSaveTime);
					
				} else {
					var dbSaveTime = Math.round(allTime/1000);
				}
				$(function(){
					$.ajax({
						url : "${ pageContext.servletContext.contextPath }/study/enterStudy/timer",
						data : { dbSaveTime : dbSaveTime , loginUserNo : ${loginUserNo}
							     },
						type : "post",
						success : function(result){
							console.log("시간 저장 성공");
						},
						error : function(e){
							console.log("시간 저장 실패");
							console.log(e);
						}
					});
				});
			}
		} else{
			alert("저장할 공부 시간이 없습니다.");
		}
	
	}
	
	
		
		
	</script>
	<%--
	#mid1-gridContainer
	
	5인
	
	grid-template-columns: repeat(3, 1fr);
	grid-template-rows: 325.5px 325.5px;
	
	10인
	grid-template-columns: repeat(4, 1fr);
	grid-template-rows: 217px 217px 217px;
	
	15인
	grid-template-columns: repeat(4, 1fr);
	grid-template-rows: 162.75px 162.75px 162.75px 162.75px;
	
	20인
	grid-template-columns: repeat(5, 1fr);
	grid-template-rows: 162.75px 162.75px 162.75px 162.75px;
	 --%>
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>







</html>