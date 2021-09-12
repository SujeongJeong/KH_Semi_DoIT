<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 내 정보</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
	/* 전체 감싸는 div */
	.my_wrap {
		display: flex;
	}
	/* 사이드메뉴 영역 */
	.side_menu{
	    width: 160px;
	}
	.side_menu ul {
		padding-left: 40px;
		list-style-type:none;
	}
	.side_menu li {
		 margin: 60px 0;
	}
	.side_menu a{
		text-decoration:none;
		display : block;
		margin : 10px;
		color : #C4C4C4;
		width : 100px;
		text-align : left;
		font-size : 15pt;
	}
	.side_menu a:hover:not(.current){
		color : #5FC5FF;
	}
	
	.side_menu .current a{
		color : #5FC5FF;
		font-weight : bold;
	}
	/* 콘텐츠 영역 */
	.areas {
		display: flex;
		border-bottom: 1px dotted lightgray;
	}
	.profile_area {
		border-right: 1px dotted lightgray;
	}
	/* 내 정보 area */
	.profile_area {
		width: 500px;
		height: 300px;
	}
	.change_area {
		display: flex;
	 	justify-content: space-between;
	 	padding: 0 10px;
	}
	div[class$=box] {
		height: 150px;
	}
	/* left 박스 */
	.left_box {
		width: 150px;
	}
	.profile_area h1 {
		text-align: right;
		padding-right: 10px;
	}
	.img_area {
		position: relative;
	}	
	#profile_img {
		 position: absolute;
	     width: 150px;
	     height: 150px;
	     border: 1px solid black;
	     border-radius: 50%;
	}
	#camera_btn {
		 position: absolute;
	     width: 50px;
	     height: 50px;
	     border: 1px solid black;
	     border-radius: 50%;
         top: 100px;
         left: 100px;
         padding: 0;
	}
	#camera_btn:hover {
		cursor: pointer;
	}
	#camera_img {
		width: 50px;
	    height: 50px;
	    border-radius: 50%;
	}
	.changeBtn {
		background: #5FC5FF;
		color: white;
	}
	
	/* right 박스 */
	.right_box {
		width: 300px;
	}
	.nick_label {
		display: flex;
		justify-content: space-between;
		margin-bottom: 5px;
	}
	.nick_label > * {
		font-size: 1.3em;
	}
	#nickResult {
		color: red;
	}
	input[name=nickName] {
		width: 80%;
		height: 30px;
	}
	.right_box > div[class$=area] {
		margin-bottom: 20px;
	}
	#setPwd_btn {
		width: 50%
	}
	#delUser_btn {
		width: 40%;
	}
	.right_box button {
		height: 30px;
	}
	
	
	/* 목표 공부 시간 area */
	.target_area {
		width: 400px;
		height: 300px;
	}
	.target_area h1 {
		padding-left: 10px;
	}
	.target_area h3 {
		text-align: center;
	}
	.setHour_area{
		text-align: center;
	}
	.setHour_area button {
		height: 30px;
	}
	.set_area {
		margin-top: 40px;
	}
	input[type=number] {
		height: 30px;
	}
	
	
	
	/* 공부 기록 list */
	.recode_area {
		padding-top: 10px;
	}
	#nick > h1 {
		display: inline;
		padding-left: 10px;
		color : #5FC5FF;
	}
	.recode_box {
		display: flex;
		justify-content: space-around;
		margin: 10px 0;
		height: 100px;
		padding-top: 20px;
	}
	.recode_box > div[class^=box] {
		border: 1px solid black;
		width: 200px;
		height: 100px;
		text-align: center;
	}
	.time_box {
		padding-bottom: 10px;
		margin: 0;
	}
	.recode_box1 {
		display: flex;
		justify-content: space-between;
		padding: 0 10px;
		margin-bottom: 10px;
	}
	.recode_btn > button {
		border: 0;
		background: white;
		font-size: 1.3em;
		padding: 0;
	}
	.recode_btn > button:hover {
		cursor: pointer;
	}
	.recode_btn > div {
		font-size: 1.2em;
	}
	.recode_btn > div > button {
		border: 0;
		background: white;
		padding: 0;
	}
	.recode_btn > div > button:hover {
		cursor: pointer;
	}
	.recode_span > span {
		font-size: 1.2em;
	}
	.list_header {
		background: #5FC5FF;
		color: white;
		font-weight: bold;
		margin: 0;
		padding: 0;
		display: flex;
		justify-content: space-around;
		list-style: none;
		height: 50px;
	}
	.list_header li {
		font-size: 18px;
		padding: 10px 0;
	}
	.recode_list {
		border: 1px solid black;
		width: 100%;
		height: 350px;
	}
	.recode_list p {
		color: lightgray;
		margin-top: 15%;
		text-align: center;
	}
</style>
</head>
<body>
<%@ include file='/WEB-INF/views/common/menubar.jsp' %>

	<div class="my_wrap">
		<nav class="side_menu">
			<ul>
				<li class="current"><a href="#">내 정보</a></li>
				<li><a href="<%= request.getContextPath() %>/my/study">내 스터디</a></li>
				<li><a href="<%= request.getContextPath() %>/my/q&a">내 Q&A</a></li>
				<li><a href="<%= request.getContextPath() %>/my/details">결제 내역</a></li>
			</ul>
		</nav>
		<div class="content">
			<div class="areas">
				<div class="profile_area">
					<h1>abc123@naver.com</h1>
					<div class="change_area">
						<div class="left_box">
							<div class="img_area">
								<img id="profile_img" src="<%= request.getContextPath() %>/resources/images/user.png" alt="프로필 이미지">
								<button id="camera_btn" type="button"><img id="camera_img" src="<%= request.getContextPath() %>/resources/images/camera.png" alt="카메라 이미지"></button>
							</div>
						</div>
						<div class="right_box">
							<div class="nick_area">
								<div class="nick_label">
									<label>닉네임</label>
									<span id="nickResult">중복입니다</span>
								</div>
								<input type="text" name="nickName">
	               				<button class="changeBtn" type="button">변경</button>
	               			</div>
	               			<div class="btn_area">
	               				<button id="setPwd_btn" onclick="openPopup('<%= request.getContextPath() %>/my/changePwdView', 'changePwd', 500, 500);">비밀번호 변경</button>
	               				<button id="delUser_btn" onclick="openPopup('<%= request.getContextPath() %>/my/withdrawalView', 'withdrawal', 500, 500);">회원 탈퇴</button>
	               			</div>
						</div>
					</div>
				</div>
				<div class="target_area">
					<h1>하루 목표 공부 시간</h1>
					<div class="set_area">
						<div class="setHour_area">
						<input type="number" min="0" max="24" value="0"> 시간
						<input type="number" min="0" max="59" value="0"> 분 
						<button class="changeBtn" type="button">변경</button>
						<button>취소</button>
						</div>
						<h3><i>"하기 싫은 걸 해야 하고 싶은걸 한다"</i></h3>
					</div>
					
				</div>
			</div>
			<div class="recode_area">
				<div id="nick"><h1>닉네임</h1> <span>님의 공부 기록</span></div>
				<div class="recode_box">
					<div class="box1">
						<p>오늘 공부 시간</p>
						<div><h1  class="time_box">00:00:00</h1></div>
					</div>
					<div class="box2">
						<p>일 평균 공부 시간</p>
						<div><h1  class="time_box">00:00:00</h1></div>
					</div>
					<div class="box3">
						<p>전체 공부 시간</p>
						<div><h1  class="time_box">00:00:00</h1></div>
					</div>
				</div>
			</div>
			<div class="recode_box1">
				<div class="recode_btn">
					<button>일별</button> | 
					<button>주별</button> |
					<button>월별</button> 
					<div><span>2021/09</span> <button><</button> <button>></button></div>
				</div>
				<div class="recode_span">
					<span>월 평균 공부시간 : </span><span>00:00:00</span>
				</div>
			</div>
				<div class="recode_list">
	               <ul class="list_header">
	                  <li class="date">날짜</li>
	                  <li class="study_hour">공부 시간</li>
	                  <li class="target_hour">목표 시간</li>
	               </ul>
	               <p>공부 기록이 없습니다</p>
	            </div>
			</div>
		</div>
	</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	<script>
	function openPopup(url, title, width, height) {
		let left = (document.body.clientWidth/2) - (width/2);
		// 듀얼모니터를 위한 계산
		left += window.screenLeft;
		let top = (screen.availHeight/2) - (height/2);
		
		let options = "width="+width+",height="+height+",left="+left+",top="+top;
		
		window.open(url, title, options);
	}
	</script>
</body>
</html>