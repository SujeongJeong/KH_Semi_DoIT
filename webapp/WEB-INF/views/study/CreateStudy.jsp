<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방 만들기 – Do IT</title>
<!-- 외부 스타일 시트 -->
<link href='<%=request.getContextPath()%>/resources/css/all.css'
	rel='stylesheet'>
<style>
.span1 {
	display: inline-block;
	width: 200px;
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

.s_startPeriod {
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
</style>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp'%>

	<div class="content">
		<form id="createForm" method="POST"
			action="<%=request.getContextPath()%>/study/createStudy">
			<span class="span1"><label class="title">스터디 배경화면</label></span> <input
				type="file" id="studyImage" name="s_Image" value="studyImage">
			<div id="imgArea"></div>

			<hr>
			<span class="span1"><label class="title">스터디 이름</label></span> 
			<input type="text" name="s_name" size="20" placeholder="스터디 이름을 입력하세요"
				maxlength="20">
			<hr>
			<span class="span1"><label class="title">스터디 정원수</label></span> 
			<span class="studyTO"> 
				<input type="radio" name="s_to" id="TO_5" value="5"> 
				<label for="TO_5">5인(기본)</label> 
				<input type="radio" name="s_to" id="TO_10" value="10"> 
				<label for="TO_10">10인(유료)</label> 
				<input type="radio" name="s_to"	id="TO_15" value="15"> 
				<label for="TO_15">15인(유료)</label>
				<input type="radio" name="s_to" id="TO_20" value="20"> 
				<label for="TO_20">20인(유료)</label>
			</span>
			<hr>
			<span class="span1"><label class="title">요일 설정</label></span> 
			<span class="studyDay"> 
				<input type="checkbox" name="s_day"	id="monday" value="monday"> 
				<label for="monday">월</label> 
				<input type="checkbox" name="s_day" id="tuesday" value="tuesday"> 
				<label for="tuesday">화</label> 
				<input type="checkbox" name="s_day"	id="wednesday" value="monday"> 
				<label for="wednesday">수</label>
				<input type="checkbox" name="s_day" id="thursday" value="monday">
				<label for="thursday">목</label> 
				<input type="checkbox" name="s_day"	id="friday" value="monday"> 
				<label for="friday">금</label> 
				<input type="checkbox" name="s_day" id="saturday" value="monday"> 
				<label for="saturday">토</label> 
				<input type="checkbox" name="s_day"	id="sunday" value="monday"> 
				<label for="sunday">일</label>
			</span>
			<hr>
			<span class="span1"><label class="title">기간 설정</label>
			</span> 시작일 : <input type="date" name="s_startPeriod" class="s_startPeriod"> 
			종료일: <input type="date" name="s_endPeriod">
			<hr>
			<span class="span1"><label class="title">시간 설정</label></span> 
			시작 시간 :	<input type="time" id="studyStartTime" name="s_startTime"> 
			<label class="endTime"> 종료 시간 :</label> 
			<input type="time" id="studyEndTime" name="s_endTime">
			<hr>
			<span class="span1"><label class="title">카테고리</label></span> <span
				class="studyCategory"> 
				<input type="radio" name="cid" id="language" value="language"> 
				<label for="language">language</label>
				<input type="radio" name="cid" id="embeded" value="embeded">
				<label for="embeded">embeded</label> 
				<input type="radio" name="cid" id="ai" value="ai"> 
				<label for="ai">ai</label> 
				<input type="radio" name="cid" id="back-end" value="back-end"> 
				<label for="back-end">back-end</label></span> <br>
			 <span class="span1"></span> <span class="studyCategory">
				<input type="radio" name="cid" id="front-end" value="front-end">
				<label for="front-end">front-end</label> 
				<input type="radio"	name="cid" id="game" value="game"> 
				<label for="game">game</label>
				<input type="radio" name="cid" id="app" value="app"> 
				<label for="app">app</label> 
				<input type="radio" name="cid" id="bigdata" value="bigdata"> 
				<label for="bigdata">bigdata</label></span> <br>
			 <span class="span1"></span> <span class="studyCategory">
				<input type="radio" name="cid" id="blockchain" value="blockchain">
				<label for="blockchain">blockchain</label> 
				<input type="radio"	name="cid" id="devops" value="devops"> 
				<label for="devops">devops</label>
				<input type="radio" name="cid" id="project" value="project">
				<label for="project">project</label>
			</span>
			<hr>
			<span class="span1 span2"><label class="title">스터디 소개<br>(설명)
			</label></span>
			<textarea name="s_explain" id="studyExplain" cols="80" rows="5"
				placeholder="스터디 소개 및 설명을 입력하세요(최대800자)"></textarea>
			<hr>
			<span class="span1 span2"><label class="title">스터디
					공지사항<br>(주의사항,규칙)
			</label></span>

			<textarea name="s_notice" id="studyNotice" cols="80" rows="5"
				placeholder="스터디 공지사항(주의사항/규칙 등)을 입력하세요(최대800자)"></textarea>
			<br> <br>
			<div class="submitBtn">
				<button type="submit" id="createStudyBtn">스터디방 만들기</button>
			</div>
		</form>


	</div>
	<footer>
		<%@ include file='/WEB-INF/views/common/footer.jsp'%>
	</footer>
</body>
</html>