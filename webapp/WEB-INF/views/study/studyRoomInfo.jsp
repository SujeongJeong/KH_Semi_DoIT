<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방 상세 – Do IT</title>
<style>
body{
	margin:0px;
}
*{
	text-align:center;
}
#outer {
	width: 100%;
	height: 100%;
}

.Top {
	position: relative;
}

#studyName {
	position: absolute;
	width: 300px;
	height: 75px;
	top: 10px;
	left: 30%;
	font-size: 1.2em;
}

#nickName {
	position: absolute;
	width: 150px;
	height: 50px;
	top: 135px;
}

#studyTO {
	position: absolute;
	width: 135px;
	height: 100px;
	top: 115px;
	left: 530px;
}

#backImage {
	width: 100%;
	height: 200px;
	opacity: 0.5;
}

.mid1 {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	gap: 30px 10px;
}

.sDay, .sTime, .sPeriod {
	display: flex;
	justify-content: center;
}

.sTime{
	margin-bottom: 40px;
}

.category {
	margin-top: 15px;
	color:#5886fb;
}

.studyExplain, .studyNotice {
	display: inline-block;
	width: 90%;
	height: 195px;
	border: 1px solid black;
	border-color : #E5E5E5; 
	border-radius:5px;
	
	word-wrap: break-word;
	word-break: keep-all;
	overflow: auto;
}


.studyNotice {
	background-color: #E5E5E5;
}

.mid2, .mid3 {
	display: flex;
	justify-content: center;
}

#studyRoomBtn {
	width: 65%;
	height: 45px;
	background-color: #5FC5FF;
	font-size: 1.2rem;
	color: white;
	font-weight: bold;
	border-radius: 5px;
	border: 0;
	outline: 0;
}

#report {
	margin-left: 25px;
	width: 20%;
	height: 45px;
	color: red;
	font-weight: bold;
	background-color:#E5E5E5;
	font-size: 1.2rem;
	border-radius: 5px;
	border: 0;
	outline: 0;
}


h2, h3 {
	text-align: center;
}
</style>
</head>
<body>
	<div class="outer">
		<div class="Top">
			<img id="backImage"
				src="/Do_IT/resources/images/study-background1.jpg">
			<div id="studyName">
				<h2>${ studyRoom.s_name }</h2>
			</div>
			<div id="nickName">
				<h3>${ studyRoom.user_nkname }</h3>
			</div>
			<div id="studyTO">
				<h3>
					스터디 정원<br>${ StudyMemberCount } / ${ studyRoom.s_to } 명
				</h3>
			</div>
			


		</div>
		<div class="mid1">
			<div>
				<h3>공부 요일</h3>
				<div class="sDay">${ studyRoom.s_day }</div>
			</div>

			<div>
				<h3>스터디 기간</h3>
				<div class="sPeriod">
				${ ssp } ~ ${ sep }</div>
			</div>

			<div>
				<h3>공부 시간</h3>
				<div class="sTime">${ sst } ~ ${ set }</div>
			</div>

			<div class="category">
				<h3 class="sCategory"># ${studyRoom.cname }</h3>
			</div>
		</div>
		<hr>
		<h2>소개글</h2>
		<div class="mid2">
			<div class="studyExplain">
				<h3>
					${ studyRoom.s_explain }
				</h3>
			</div>
		</div>
		<h2>공지사항</h2>
		<div class="mid3">
			<div class="studyNotice">
				<h3>
					${ studyRoom.s_notice }
				</h3>
			</div>
		</div>
		<br>
			
			<form name="joinStudyValues" method="post">
				<input type="hidden" name="userNo" value="${loginUser.userNo}"></input>
				<input type="hidden" name="s_no" value="${studyRoom.s_no}"></input>
			</form>

		<button id="studyRoomBtn" onclick="studyBtn()">
			<c:choose>
				<c:when test="${ !empty loginUser }">

					<%-- 스터디방 가입하기 --%>
					<c:if test="${ selectMemberJoinStudy == null }">
						<c:out value="가입하기" />
						
						<c:if test="${ memberJoinStudyNum < 3}">
							<c:if test="${ StudyMemberCount +1 < studyRoom.s_to }">
								<script>
									function studyBtn() {
										let cResult = confirm("가입하시겠습니까?");
										if (cResult == true) {
											document.forms.joinStudyValues.action = "${contextPath}/study/joinStudy";
											document.forms.joinStudyValues.submit();
										}
									}
								</script>
							</c:if>
							<c:if test="${ StudyMemberCount +1 == studyRoom.s_to }">
								<script>
									function studyBtn() {
										alert("정원이 초과되어 가입하실 수 없습니다.");
									}
								</script>
							</c:if>
						</c:if>
						<c:if test="${ memberJoinStudyNum >= 3}">
							<script>
								function studyBtn() {
									alert("가입 한도를 초과하셨습니다.");
								}
							</script>
						</c:if>
					</c:if>
					<%-- 스터디방 입장하기 --%>
					<c:if test="${ selectMemberJoinStudy != null }">
						<c:out value="입장하기" />
						<script>
								function studyBtn(){
									opener.location.href='<%=request.getContextPath()%>/study/enterStudy?s_no=${studyRoom.s_no}&userNo=${loginUser.userNo}';window.close();
								}
							</script>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:out value="로그인" />
					<script>
								function studyBtn(){
									opener.location.href='<%=request.getContextPath()%>/login';
									window.close();

						}
					</script>
				</c:otherwise>
			</c:choose>
		</button>

		<button id="report">신고하기</button>
		</div>



</body>
</html>











