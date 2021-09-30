<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>	
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

button{
cursor: pointer;
}
#top {
	display: flex;
    align-items: center;
    justify-content: center;
	width: 100%;
	height: 85px;
	background-color: black;
	opacity: 0.8;
}
#top-content{
    display: flex;
    width: 100%;
}


#title {
    color: white;
    font-size: 2.0em;
    text-align: center;
    margin-left: 70px;
}

#currentTO{
    color: white;
    font-size: 2em;
   
}

#img1{
	width: 25px;
    height: 25px;
    margin-left: 55px;
}

#top1-content-wrapper{
	position : absolute;
	right : 20px;
}

#member-management {
    background-color: white;
 	text-align: center;
    border-radius: 15px;
    font-size: 1.01em;
    width: 100px;
    height: 40px;
    border: 0;
}

#out {
	display: inline-block;
    background-color: red;
    margin-left: 15px;
    width: 100px;
    height: 40px;
    text-align: center;
    border-radius: 15px;
    font-size: 1.05em;
    color: white;
    border : 0;
}

#mid-wrapper {
	display:flex;
    position: relative;
    width: 100%;
    height: calc(100vh - 85px);
}

#mid1{
	position:relative;
	border: 1px solid black;
	width: calc(100% - 335px);
	height: 99.9%;
	background-color: #474747;
}

#mid2{
    width: 335px;
    height: 99.9%;
    box-sizing: border-box;
	
}
#joinMember-title, #joinMember-TO{
	display : inline-block;
	font-size: 1.35em;
    text-align: center;
    margin : 10px;
}

#study-limit{
	font-size : 18px;
}
#joinMember-TO{
	margin-left : 85px;
}

#joinMember-wrapper{
	border-bottom: 1px solid gray;
	height:50px;
	background-color: black;
    opacity: 0.8;
    color: white;
}

#currentJoinMember-wrapper{
    height: calc(100% - 400px);
    border-bottom: 1px solid gray;
    word-wrap: break-word;
    word-break: keep-all;
    overflow: auto;
}
.currentJoinMember-content{
	display: flex;
    height: 70px;
    border-bottom: 1px solid #e5e5e5;
}

#chat-title-content{
    font-size: 1.5em;
    background-color: black;
    opacity: 0.8;
    color: white;
    padding: 10px;
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
	border: 1px solid #c4c4c4;
    border-radius: 5px;
}

#mid1-gridContainer{
	position:relative;
	display:grid;
	grid-template-columns: repeat(5, 1fr);

}

.studyGrid{
	position:relative;
	border : 1px solid black;
}

.user-header{
	 display: flex;
    justify-content: space-between;
}

#gridImg1{
    width: 90%;
    height: 25vh;
    margin: 10px auto;
    left: 6%;
    position: absolute;
    top: 15%;
}

#gridImg2{
	    padding: 5px;
}

#currentJoinMember-content-nickName{
    width: calc(100% - 80px);
    align-self: center;
    font-size: 15px;
}

#gridNickname{
/*	position:absolute;
	top:10px;
	left:15px;*/
	text-align:center;
	font-size:1.2em;
	color: white;
    padding-left: 10px;
    padding-top: 5px;
}

#stopWatch{
	/*position: absolute;
    right: 0;*/
    text-align: center;
    height: 33px;
    font-size: 1.2em;
    background-color: black;
    border-bottom-left-radius: 7px;
    color: white;
    /* padding-left: 20px; */
    width: 105px;
}
#stopWatch div{
	margin-bottom : 5px;
}
#reportImg{
    width: 35px;
    height: 35px;
}

#timer-wrapper {
	position: absolute;
	bottom:0;
	background-color: black;
}


.timer {
	background: none;
	border: 0;
	color: white;
	text-align: center;
	padding: 13px;
}
/* 광고 css */
.ad img{
	width : 334px;
	height : 110px ;
}

</style>
<body>
	<div id="top">
		<div id="top-content">
			<div id="title">${ study.s_name }</div>
			<div id="currentTO">
				<div class="user-profile">
				<img src="/Do_IT/resources/images/studyicon2.jpg" id="img1">
				${ study.s_to }
				</div>
			</div>
			<div id="top1-content-wrapper">
				<c:if test="${ loginUserNo == CreateRoomUser.userNo }">
					<button id="member-management" onclick="memberManagement(${ s_no },${ loginUserNo })">회원 관리</button>
				</c:if>
				<button id="out" onclick="outBtn()">나가기</button>
			</div>
		</div>
	</div>

	<div id="mid-wrapper">
		<div id="mid1">
			<div id="mid1-gridContainer">
					<div class="studyGrid" id="studyGrid">
						<div class="user-header">
						<div id="gridNickname">${ CreateRoomUser.nickName } (방장)</div>
						<%-- <div id="stopWatch">hh:mm:ss</div>--%>
						</div>
						<div class="user-profile"><img src="<%= request.getContextPath() %>${ CreateRoomUser.profileImg }" id="gridImg1"></div>
						<c:if test="${ CreateRoomUser.userNo == loginUserNo }">
							<div id="timer-wrapper">
								<button class="timer start" onclick="startBtn()">▷</button>
								<button class="timer stop" onclick="stopBtn()">||</button>
								<button class="timer end" onclick="endBtn()">ㅁ</button>
							</div>
						</c:if>
					</div>
					
				<c:forEach items="${ MemberList }" var="m" begin="0" end="${ MemberList.size() }">
					<div class="studyGrid" id="studyGrid">
						<div class="user-header">
						<div id="gridNickname">${ m.nickName }</div>
						<%--<div id="stopWatch">hh:mm:ss</div> --%>
						</div>
						<div class="user-profile"><img src="<%= request.getContextPath() %>${ m.profileImg }" id="gridImg1"></div>
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
				<div id="joinMember-TO">${ StudyMemberCount } / <span id="study-limit">${study.s_to }</span></div>
			</div>
			<div id="currentJoinMember-wrapper">
			
				<div class="currentJoinMember-content">
						<img src="<%= request.getContextPath() %>${ CreateRoomUser.profileImg }" id="gridImg2">
						<span id="currentJoinMember-content-nickName">${ CreateRoomUser.nickName } (방장)</span>
				<%--	<span id="reportMember"><img src="/Do_IT/resources/images/reporticon4.jpg" id="reportImg"></span>  --%>
					</div>
			
				<c:forEach items="${ MemberList }" var="m" begin="0" end="${ MemberList.size() }">
					<div class="currentJoinMember-content">
						<img src="<%= request.getContextPath() %>${ m.profileImg }" id="gridImg2">
						<span id="currentJoinMember-content-nickName">${ m.nickName }
							<c:if test="${ m.userNo == studyCreater.user_no }">
								 (방장)
							</c:if>
						</span>
				<%--	<span id="reportMember"><img src="/Do_IT/resources/images/reporticon4.jpg" id="reportImg"></span>  --%>
					</div>
				</c:forEach>	
			</div>
			<div class="ad">
			<img src="<%= request.getContextPath() %>/resources/images/banner_memberjoin.png" alt="광고1">
			<img src="<%= request.getContextPath() %>/resources/images/banner_qna.png" alt="광고1">
			<img src="<%= request.getContextPath() %>/resources/images/banner_ranking.png" alt="광고1">			
			</div>
		</div>
			
		<%-- 채팅 
				<div id="chat-title">
				<div id="chat-title-content">채팅</div>
			</div>
			<div id="chat-content">채팅구간</div>
			<div id="chat-input">
				<input type="text" id="chat" placeholder="대화를 입력하세요">
			</div>
		</div> --%>
	</div>
	
	<c:if test="${ m.userNo == loginUserNo }">
		<script>
			var stopWatchTime = document.getElementById('stopWatch');
			<%--
			setInterval(function(){
				
				
				
				
				stopWatchTime.innerHTML = 
			},1000);
			--%>
		</script>
	</c:if>
	
	<script>
	var gridInput = document.querySelector('#mid1-gridContainer');
	var gridNickname = document.querySelector('#gridNickname');
	
	
	
	if( ${study.s_to} != null ){
		
		if( ${ study.s_to } == 5){
			// height 2 
			gridInput.style.gridTemplateColumns = 'repeat(3,1fr)';
			gridInput.style.gridAutoRows = document.getElementById("mid1").getBoundingClientRect().height / 2 + "px";
		}
		if( ${ study.s_to } == 10){
			// height 3
			gridInput.style.gridTemplateColumns = 'repeat(4,1fr)';
			// gridInput.style.gridTemplateRows = '217px 217px 217px';
			gridInput.style.gridAutoRows = document.getElementById("mid1").getBoundingClientRect().height / 3 + "px";
		}
		if( ${ study.s_to } == 15){
			// height 4
			gridInput.style.gridTemplateColumns = 'repeat(4,1fr)';
			// gridInput.style.gridTemplateRows = '162.75px 162.75px 162.75px 162.75px';
			gridInput.style.gridAutoRows = document.getElementById("mid1").getBoundingClientRect().height / 4 + "px";
		}
		if( ${ study.s_to } == 20){
			// height 4
			gridInput.style.gridTemplateColumns = 'repeat(5,1fr)';
			// gridInput.style.gridTemplateRows = '162.75px 162.75px 162.75px 162.75px';
			gridInput.style.gridAutoRows = document.getElementById("mid1").getBoundingClientRect().height / 4 + "px";
		}
	}
		function outBtn(){
			var out = confirm("정말 나가시겠습니까?");
			if(out == true){
				history.back();
			}
		}
	
		
		
		function openPopup(url, title, width, height){
			
			let left = (document.body.clientWidth/2) - (width/2);
			//듀얼 모니터를 위한 계산
			left += window.screenLeft;
			//작업표시줄 제외하고. 
			let top = (screen.availHeight/2) - (height/2);
			
			let options = "width=" + width+",height="+height + ",left="+left + ", top=" + top;
			
			
			window.open(url, title, options);
		}
		
	function memberManagement(s_no,user_no){
			openPopup('<%= request.getContextPath() %>/study/memberManagement?s_no='+s_no+'&user_no='+user_no, 'studyInfo', 700, 700);
			
		}	
	
	// 타이머
	var start;
	var stop;
	var allTime = 0;
	var startParam = false;
	var s_no = ${s_no};
	
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
						data : { dbSaveTime : dbSaveTime , loginUserNo : ${loginUserNo} , s_no : ${ s_no }},
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
				allTime = 0;
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