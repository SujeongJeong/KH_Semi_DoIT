<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방</title>
</head>
<style>
body {
	margin: 0px;
}

#top {
	width: 100%;
	height: 120px;
	background-color: black;
}

#mid-container {
	position: relative;
}

#mid1 {
	display: inline-block;
	position: absolute;
	width: 80%;
	height: 847px;
}

#mid1-grid {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	grid-auto-rows: minmax(423.5px, auto);
}

.mid1-grid-content {
	position: relative;
	border: 1px solid black;
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

#mid2 {
	display: inline-block;
	border: 1px solid black;
	position: absolute;
	width: 19.9%;
	height: 847px;
	left: 80%;
}
</style>
<body>
	<div id="top"></div>
	<div id="mid-container">

		<div id="mid1">
			<div id="mid1-grid">
				<div class="mid1-grid-content">
					<div id="timer-wrapper">
						<button class="timer start">▷</button>
						<button class="timer stop">||</button>
						<button class="timer end">ㅁ</button>
					</div>
				</div>
				<div class="mid1-grid-content">
					<div id="timer-wrapper">
						<button class="timer start">▷</button>
						<button class="timer stop">||</button>
						<button class="timer end">ㅁ</button>
					</div>
				</div>
				<div class="mid1-grid-content">
					<div id="timer-wrapper">
						<button class="timer start">▷</button>
						<button class="timer stop">||</button>
						<button class="timer end">ㅁ</button>
					</div>
				</div>
				<div class="mid1-grid-content">
					<div id="timer-wrapper">
						<button class="timer start">▷</button>
						<button class="timer stop">||</button>
						<button class="timer end">ㅁ</button>
					</div>
				</div>
			</div>
			<%--<img id="sImage" alt="기본이미지" src="/Do_IT/resources/images/camera.jpg"> --%>

		</div>

		<div id="mid2"></div>

	</div>
</body>
</html>