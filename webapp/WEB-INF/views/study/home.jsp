<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 - Do IT</title>
<!-- 외부 스타일 시트 -->
<link href='<%=request.getContextPath()%>/resources/css/all.css'
	rel='stylesheet'>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<style>
#content {
	margin: 50px auto;
	height: auto;
	min-height: 100%;
	width: 800px;
}

.search {
	display: inline-block;
}

.createStudy {
	margin-left: 185px;
	margin-right: 20px;
}

.studyCreateBtn, .plusBtn {
	background-color: #5FC5FF;
	border : 0; 
    outline :0;
    border-radius:5px;
}

.category {
	margin-left: 5px;
}

.studyCategory {
	margin-right: 15px;
	background-color: white;
}

.studyList {
	display: grid;
	grid-template-columns: repeat(5, 1fr);
	grid-auto-rows: minmax(225px, auto);
	gap: 20px 15px;
}

.sCategory{
	color:#C4C4C4;
}

.studyRoom {
	position: relative;
}

.studyTO {
	position: absolute;
	top:100px;
	left:10px;
	
}

.studyImage {
	width: 150px;
	height: 150px;
}

.plusBtn {
	display: inline-block;
	left: 48.5%;
	bottom: 22px;
	position: relative;
	
}

.plusLine {
	border-style: dotted;
	position: relative;
}
</style>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp'%>

	<div class="content" id="content">
		<h1>오픈 스터디</h1>
		<div class="search search1">
			<form>
				<input type="text" size="15px" name="search"
					placeholder="검색할 내용을 입력하세요"> <input type="submit"
					value="검색하기">
			</form>
		</div>
		<div class="search createStudy">
			<button class="studyCreateBtn"
				onclick="location.href='<%=request.getContextPath()%>/createStudy'">스터디
				만들기</button>
		</div>
		<div class="search category">
			<form>
				<select class="studyCategory">
					<option value="default" selected>카테고리</option>
					<option value="Language">Language</option>
					<option value="Embeded">Embeded</option>
					<option value="Ai">Ai</option>
					<option value="Front-End">Front-End</option>
					<option value="Back-End">Back-End</option>
					<option value="App">App</option>
					<option value="Bigdata">Bigdata</option>
					<option value="BlockChain">BlockChain</option>
					<option value="Devops">Devops</option>
					<option value="Project">Project</option>
					<option value="Game">Game</option>
				</select>
			</form>


		</div>
		<div class="search canJoinStudy">
			<input type="checkbox" name="canJoinStudy">바로 참여 가능한 방
		</div>
		<br> <br>

		<div class="studyList">
			<div class="studyRoom">
				<img class="studyImage"
					src='/Do_IT/resources/images/study-background1.jpg'
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 1</span><br>
				<span class="sCategory">#Ai</span>
				<h4 class="studyTO">n/m</h4>
			</div>
			<div class="studyRoom">
				<img class="studyImage"
					src="/Do_IT/resources/images/study-background2.jpg"
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 2</span><br>
				<span class="sCategory">#Front-end</span>
				<h4 class="studyTO">n/m</h4>
			</div>
			<div class="studyRoom">
				<img class="studyImage"
					src="/Do_IT/resources/images/study-background3.jpg"
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 3</span><br>
				<span class="sCategory">#Back-end</span>
				<h4 class="studyTO">n/m</h4>
			</div>
			<div class="studyRoom">
				<img class="studyImage"
					src="/Do_IT/resources/images/study-background4.png"
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 4</span><br>
				<span class="sCategory">#Language</span>
				<h4 class="studyTO">n/m</h4>
			</div>
			<div class="studyRoom">
				<img class="studyImage"
					src="/Do_IT/resources/images/study-background5.jpg"
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 5</span><br>
				<span class="sCategory">#Embeded</span>
				<h4 class="studyTO">n/m</h4>
			</div>
			<div class="studyRoom">
				<img class="studyImage"
					src="/Do_IT/resources/images/study-background6.jpg"
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 6</span><br>
				<span class="sCategory">#App</span>
				<h4 class="studyTO">n/m</h4>
			</div>
			<div class="studyRoom">
				<img class="studyImage"
					src="/Do_IT/resources/images/study-background7.jpg"
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 7</span><br>
				<span class="sCategory">#Bigdata</span>
				<h4 class="studyTO">n/m</h4>
			</div>
			<div class="studyRoom">
				<img class="studyImage"
					src="/Do_IT/resources/images/study-background8.png"
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 8</span><br>
				<span class="sCategory">#Blockchain</span>
				<h4 class="studyTO">n/m</h4>
			</div>
			<div class="studyRoom">
				<img class="studyImage"
					src="/Do_IT/resources/images/study-background1.jpg"
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 9</span><br>
				<span class="sCategory">#Game</span>
				<h4 class="studyTO">n/m</h4>
			</div>
			<div class="studyRoom">
				<img class="studyImage" src="/Do_IT/resources/images/user.png"
					onclick="openPopup('<%=request.getContextPath()%>/studyInfo', 'studyInfo', 700, 1000);"><span
					class="sName">스터디방 10</span><br>
				<span class="sCategory">#Project</span>
				<h4 class="studyTO">n/m</h4>
			</div>
		</div>

		<br>
		<div class="studyRoomPlus">
			<hr class="plusLine">
			<button class="plusBtn">더보기</button>
		</div>
	</div>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

	<script>

function openPopup(url, title, width, height){
	
	let left = (document.body.clientWidth/2) - (width/2);
	//듀얼 모니터를 위한 계산
	left += window.screenLeft;
	//작업표시줄 제외하고. 
	let top = (screen.availHeight/2) - (height/2);
	
	let options = "width=" + width+",height="+height + ",left="+left + ", top=" + top;
	
	
	window.open(url, title, options);
}

</script>


	<footer>
		<%@ include file='/WEB-INF/views/common/footer.jsp'%>
	</footer>

</body>
</html>