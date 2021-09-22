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
    width: calc(100% - 140px);
    min-width: 800px;
    max-width: 1000px;
    position: relative;
}

.search {
	display: inline-block;
}

.search1{
	display: relative;
}

.searchText{
    width: 200px;
    height: 25px;
    border-radius:10px;
}

.searchTextSubmit{
	height: 30px;
    width: 75px;
    border-radius:10px;
}

.deleteStudyRoom{
	position: absolute;
    left: 860px;
    top: 17px;
}

.createStudy {
	margin-left: 388px;
	margin-right: 20px;
}
.deleteStudyRoomBtn{
    background-color: #E5E5E5;
    outline: 0;
    border-radius: 5px;
    color: red;
    border: 1px solid red;
    width: 100px;
    height: 33px;
    
}
.studyCreateBtn, .plusBtn{
	background-color: #5FC5FF;
    border: 0;
    outline: 0;
    border-radius: 5px;
    width: 140px;
    height: 30px;
    font-size: 1.05em;
    font-weight: bold;
}

.plusBtn{
	border: 1px solid black;
}

.category {
	margin-left: 5px;
}

.studyCategory {
	margin-left: 15px;
    margin-right: 15px;
    background-color: white;
    width: 110px;
    height: 30px;
    border-radius: 10px;
}

.studyList {
	display: grid;
	grid-template-columns: repeat(5, 1fr);
	grid-auto-rows: minmax(225px, auto);
	gap: 20px 15px;
}

.sCategory{
    border: 1px solid #5886fb;
    border-radius: 10px;
    padding: 1px 10px;
    background: #5886fb;
    color: white;
}

.canJoinStudy{
	position: absolute;
    top: 70px;
}


.canJoinStudyBtn{
	width: 22px;
    height: 16px;
}

.canJoinStudyText{
	display: inline-block;
    font-size: 1.1em;
}


.studyRoom {
	position: relative;
}

.sub_wrapper {
	width: 100%;
    margin-bottom: 5px;
}

#deleteStudy{
	position: absolute;
    top: 125px;
    left: 123px;
    width: 22px;
    height: 19px;
}

.img_wrapper {
	position: relative;
	transition: 0.3s all;
}

.img_wrapper:hover > .studyImage{
	border-color: #f5ff10;
}

.studyTO {
	position: absolute;
	top:125px;
	left:10px;
    border: 1px solid #eee;
    border-radius: 5px;
    padding: 0px 8px;
    background: #fff;
    font-size: 14px;
    font-weight: bold;
}

.sName {
    font-weight: bold;
    margin-left: 2px;
}

.studyImage {
	width: 150px;
	height: 150px;
	border:1px solid #ddd;
	border-radius: 10px;
	padding: 2px;
}

.plusBtn {
	display: inline-block;
	left: 43.5%;
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
		<span><h1>오픈 스터디</h1></span>

		<div class="search search1">
			<form>
				<input type="text" class="searchText" size="15px" name="search"
					placeholder="검색할 내용을 입력하세요"> 
					<input type="submit" class="searchTextSubmit" value="검색하기">
			</form>
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
			<input type="checkbox" id="canJoinStudyBtn" class="canJoinStudyBtn" name="canJoinStudyBtn">
			<label for="canJoinStudyBtn" class="canJoinStudyText">바로 참여 가능한 방</label>
		</div>
		
		<div class="search createStudy">
			<button class="studyCreateBtn"
				onclick="createBtnEvent()">스터디	만들기</button>
			<c:if test="${ loginUser != null }">
				<script>
					function createBtnEvent(){
						location.href='<%=request.getContextPath()%>/study/createStudy';
					}
				</script>
			</c:if>
			<c:if test="${ loginUser == null }">
				<script>
					function createBtnEvent(){
						location.href='<%=request.getContextPath()%>/login';
					}
				</script>
			</c:if>
		</div>
		
		<br> <br>

		<form id="deleteStudyForm" method="get" action="<%=request.getContextPath()%>/study/deleteStudy">
			
		<div class="studyList">
		
		<%-- 스터디방 리스트 조회 --%>

		<c:forEach var="g" items="${ StudyList }" begin="0" end="${ StudyList.size() }">

				<div class="studyRoom">
					
					<div class="img_wrapper">
						<img class="studyImage"
							src="${ contextPath }${ g.sImgList.get(0).file_path }${ g.sImgList.get(0).change_name }"
							onclick="studyInfo(${ g.s_no })">

						<c:set var="MemberCount" value="0"/>
						<c:forEach var="i" items="${ StudyMemberList }" begin="0" end="${ StudyMemberList.size() }">
							
						</c:forEach>
						
						<div class="studyTO">
						${ MemberCount } / ${ g.s_to }</div>
					</div>
					<div class="sub_wrapper">
						<div class="sName">${ g.s_name }</div>
					</div>
					<div class="sub_wrapper">
						<span class="sCategory">#${ g.cname }</span>
					</div>
					<c:if test="${ loginUser.userType == 'A' }">
					<input type="checkbox" name="deleteStudy" id="deleteStudy" value="${ g.s_no }">
					</c:if>
				</div>
	
			</c:forEach>
			<c:if test="${ loginUser.userType == 'A' }">
			<div class="deleteStudyRoom">
				<button type="submit" class="deleteStudyRoomBtn"
					onclick="location.href='<%=request.getContextPath()%>/study/createStudy'">
					스터디 삭제</button>
			</div>
			</c:if>
		</div>
		</form>

		<br>
		<div class="studyRoomPlus">
			<hr class="plusLine">
			<button class="plusBtn" onclick="plusBtnEvent(     )">더보기</button>
		</div>
	</div>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

<script>
		//게시글 목록에 mouseover/mouseout 시 onmouseover클래스 추가/제거 처리
		const studyList = document.querySelector(".studyList");
		
		studyList.addEventListener('mouseover', function(){
			
			if(event.target.classList.contains('img_wrapper'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('img_wrapper'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		studyList.addEventListener('mouseout', function(){
			
			if(event.target.classList.contains('img_wrapper'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('img_wrapper'))
				event.target.parentNode.classList.remove('onmouseover');
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

function plusBtnEvent(){

}


</script>


	<footer>
		<%@ include file='/WEB-INF/views/common/footer.jsp'%>
	</footer>

</body>
</html>