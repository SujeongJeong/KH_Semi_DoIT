<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방 생성</title>
<!-- 외부 스타일 시트 -->
<link href='<%=request.getContextPath()%>/resources/css/all.css'
	rel='stylesheet'>
<style>
#imgArea{
	border: 1px solid black;
	width:200px;
    height: 200px;
}	
.content{
	display: flex;
	justify-content: between;
}
label{
	display:inline-block;
	width:130px;
}


</style>	
</head>
<body>
<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
<%@ include file='/WEB-INF/views/common/menubar.jsp'%>

<div class="content">
	<form method="POST">
	<label name="title">스터디 배경화면</label>
	<input type="file" id="studyImage" name="studyImage" value="studyImage">
	<div id="imgArea"></div>
	
	<hr>
	<label name="title">스터디 이름</label>
	<input type="text" name="studyName" size="20" placeholder="스터디 이름을 입력하세요" maxlength="20">
	<hr>
	<label name="title">스터디 정원수</label>
	<input type="radio" name="studyTO" id="TO_5" value="TO_5">
    <label for = "TO_5">5인(기본)</label>
    <input type="radio" name="studyTO" id="TO_10" value="TO_10">
    <label for = "TO_10">10인(유료)</label>
    <input type="radio" name="studyTO" id="TO_15" value="TO_15">
    <label for = "TO_15">15인(유료)</label>
    <input type="radio" name="studyTO" id="TO_20" value="TO_20">
    <label for = "TO_20">20인(유료)</label>
    <hr>
    <label name="title">요일 설정</label>
    <input type="checkbox" name="studyDay" id="monday" value="monday">
    <label for="monday">월</label>
    <input type="checkbox" name="studyDay" id="tuesday" value="tuesday">
    <label for="tuesday">화</label>
    <input type="checkbox" name="studyDay" id="wednesday" value="monday">
    <label for="wednesday">수</label>
    <input type="checkbox" name="studyDay" id="thursday" value="monday">
    <label for="thursday">목</label>
    <br>
    <input type="checkbox" name="studyDay" id="friday" value="monday">
    <label for="friday">금</label>
    <input type="checkbox" name="studyDay" id="saturday" value="monday">
    <label for="saturday">토</label>
    <input type="checkbox" name="studyDay" id="sunday" value="monday">
    <label for="sunday">일</label>
    <hr>
    <label name="title">기간 설정</label>
    	
    <!-- 작성 예정 -->
    <hr>
    <label name="title">시간 설정</label>
	시작 시간 : 
	<input type="text" id="studyStartTime" name="studyStartTime" size="20" placeholder="00:00(시간:분, 24시간 기준)" maxlength="5">
	종료 시간 : 
	<input type="text" id="studyEndTime" name="studyName" size="20" placeholder="00:00(시간:분, 24시간 기준)" maxlength="5">
	<hr>
	<label name="title">카테고리</label>
	<input type="radio" name="cid" id="language" value="language">
    <label for = "language">language</label>
    <input type="radio" name="cid" id="embeded" value="embeded">
    <label for = "embeded">embeded</label>
    <input type="radio" name="cid" id="ai" value="ai">
    <label for = "ai">ai</label>
    <input type="radio" name="cid" id="back-end" value="back-end">
    <label for = "back-end">back-end</label>
    <br>
    <input type="radio" name="cid" id="front-end" value="front-end">
    <label for = "front-end">front-end</label>
    <input type="radio" name="cid" id="game" value="game">
    <label for = "game">game</label>
    <input type="radio" name="cid" id="app" value="app">
    <label for = "app">app</label>
    <input type="radio" name="cid" id="bigdata" value="bigdata">
    <label for = "bigdata">bigdata</label>
    <br>
    <input type="radio" name="cid" id="blockchain" value="blockchain">
    <label for = "blockchain">blockchain</label>
    <input type="radio" name="cid" id="devops" value="devops">
    <label for = "devops">devops</label>
    <input type="radio" name="cid" id="project" value="project">
    <label for = "project">project</label>
    <hr>
    <label name="title">스터디 소개<br>(설명)</label>
    <textarea name="studyExplain" id="studyExplain" value="studyExplain"
     cols="80" rows="5" placeholder="스터디 소개 및 설명을 입력하세요"></textarea>
    <hr>
    <label name="title">스터디 공지사항<br>(주의사항/규칙 등)</label>
    <textarea name="studyNotice" id="studyNotice" value="studyNotice"
     cols="80" rows="5" placeholder="스터디 공지사항(주의사항/규칙 등)을 입력하세요"></textarea>
     <br><br>
	<button type="submit">스터디방 만들기</button>
	</form>


</div>
<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp'%>
</footer>
</body>
</html>