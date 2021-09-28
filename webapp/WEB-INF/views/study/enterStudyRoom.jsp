<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방</title>
</head>
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

#member-management {
	margin-left: 880px;
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
	font-size: 1.5em;
    text-align: center;
    margin : 10px;
}

#joinMember-TO{
	margin-left : 100px;
}

#joinMember-wrapper{
	border-bottom: 1px solid gray;
}

#currentJoinMember-wrapper{
	height:250px;
	border-bottom: 1px solid gray;
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
	border : 1px solid black;
}

#sImage {
	
}

#timer-wrapper {
	position: absolute;
	top: 90%;
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
			<button id="member-management" onclick="">회원 관리</button>
			<button id="out" onclick="outBtn()">나가기</button>
		</div>
	</div>

	<div id="mid-wrapper">
		<div id="mid1">
			<div id="mid1-gridContainer">
				
				<c:forEach items=${  } var="">
				<div class="studyGrid" id="studyGrid"></div>
				</c:forEach>
				<%--<img id="sImage" alt="기본이미지" src="/Do_IT/resources/images/camera.jpg"> --%>
				<%-- <button class="timer start">▷</button>
							<button class="timer stop">||</button>
							<button class="timer end">ㅁ</button>--%>
			</div>
		</div>

		<div id="mid2">
			<div id="joinMember-wrapper">
				<div id="joinMember-title">참가자 목록</div>
				<div id="joinMember-TO">${ StudyMemberCount } / ${study.s_to }</div>
			</div>
			<div id="currentJoinMember-wrapper">
				현재 참가자 목록 출력
				<%-- 현재 입장인원 반복문 출력 --%>
				<div class="currentJoinMember-content"></div>
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
	
	if(${ study.s_to } != null){
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