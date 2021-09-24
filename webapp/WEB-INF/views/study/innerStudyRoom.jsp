<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방</title>
</head>
<style>
	#top{
		width:100%;
		height:100px;
		background-color:black;
	}
</style>
<body>
<div id="top"></div>

<div id="mid1">
	<button onclick="sys1()">시작</button>
	<button onclick="sys2()">정지</button>
	<button onclick="sys3()">계산</button>
</div>

<script>
	function sys1(){
		 let startDate = new Date(1000);
		 let endDate = new Date(60*1000);
		 let cal = endDate - startDate;
		 console.log(startDate);
		 console.log(endDate);
		 console.log(cal/1000);
	}
	function sys2(){
		let end = new Date();
		console.log(end);
	}
	function sys3(){
		if(start != null && start2 != null){
			let cal = start - end;
			console.log(cal);
		}
	}
</script>
</body>
</html>