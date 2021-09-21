<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	border:1px solid black;
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

.onmouseover {
	cursor: pointer;
	transform:scale(1.05);
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
				onclick="location.href='<%=request.getContextPath()%>/study/createStudy'">스터디
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
		
		<%-- 스터디방 리스트 조회 --%>
		<%-- begin="1" end="${ StudyListSize }" --%>
		<c:forEach var="g" items="${ StudyList }" begin="0" end="${ StudyList.size() }">


				<div class="studyRoom">
					<img class="studyImage"
						src="${ contextPath }${ g.sImgList.get(0).file_path }${ g.sImgList.get(0).change_name }"
						onclick="studyInfo(${ g.s_no })"> <span class="sName">${ g.s_name }
					</span><br> <span class="sCategory">#${ g.cname }</span>
					<h4 class="studyTO">n/ ${ g.s_to }</h4>
				</div>

			</c:forEach>	
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
		//게시글 목록에 mouseover/mouseout 시 onmouseover클래스 추가/제거 처리
		const studyList = document.querySelector(".studyList");
		
		studyList.addEventListener('mouseover', function(){
			
			if(event.target.classList.contains('studyImage'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('studyImage'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		studyList.addEventListener('mouseout', function(){
			
			if(event.target.classList.contains('studyImage'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('studyImage'))
				event.target.parentNode.classList.add('onmouseover');
		})
		
	
	</script>


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

function studyInfo(s_no){
	
	openPopup('${contextPath}/study/studyInfo?s_no='+s_no, 'studyInfo', 700, 1000);
	
	
}



</script>


	<footer>
		<%@ include file='/WEB-INF/views/common/footer.jsp'%>
	</footer>

</body>
</html>