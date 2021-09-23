<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Do IT</title>
<!-- 외부 스타일 시트 -->
<link href='<%= request.getContextPath() %>/resources/css/all.css?after' rel='stylesheet'>
<link href='<%= request.getContextPath() %>/resources/css/main.css?after' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<style>
.outer{
		width : 1000px;
		display : grid;
		margin : 20px auto;	
		grid-template-rows : 400px 400px;
		grid-template-columns: 500px 500px;
		gap : 30px 40px ;
		 
	}
	.outer:nth-child(1){ 	grid-area : 1/1/2/2;	}
	.outer:nth-child(2){	grid-area : 1/2/2/3;    }
	.outer:nth-child(3){	grid-area : 2/1/3/2;	}
	.outer:nth-child(4){	grid-area : 2/2/3/3;	}
</style>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<jsp:include page='/WEB-INF/views/common/menubar.jsp'/>
	
	<div class="content">
		<div class="outer">
			<div class="study-area">
				<span class="title">내 스터디</span>
				<span class = "icon">
				<img src="resources/images/left-arrow-nolist.png" alt="넘김"><img src="resources/images/right-arrow-nolist.png" alt="넘김">
				</span>
				<div class="study-list">
					<c:choose>
					<c:when test="${ loginUser == null || Study == null}">
					 <label class="nolist">스터디를 만들거나 추가해보세요.</label>
					</c:when>
					<c:otherwise>
					<div class="study-info">
					<c:forEach var="s" items="${ study }">
					
					<img src="resources/images/study-background1.jpg" alt="스터디배경사진"><br>
					<label class="study-name">${ s.s_name }</label><br>
					<label class="study-category darkgray-c">#${ s.cname }</label>
					</c:forEach>
					</div>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="goal-area">
				<label class="title">오늘의 목표</label>
				<div class="goal-list">
					<ul class="goalUl">
						<li class="semiTitle">오늘 공부시간/목표 시간</li>
						<c:choose>
						<c:when test="${ loginUser == null }">
						<li><span class="todayhours point-c">0시간 00분</span><span class="goalhours lightgray-c">/ 0시간 00분</span></li>
						</c:when>
						<c:otherwise>
						<li><span class="todayhours point-c">3시간 00분</span><span class="goalhours lightgray-c">/ 
						<c:set var="targetHour" value="${ loginUser.targetHour }"/><c:set var ="hours" value="${fn:split(targetHour, '/') }"/>
						 ${ hours[0] }시간 ${ hours[1] }분</span></li>
						</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<div class="ranking-area">
				<span class="title">누적 공부 시간 랭킹 </span><span class="point-c yesterday">하루 전</span>
				<span class="lightgray-c date">yyyy.MM.dd(E요일) 오전 0시 기준</span>
				<ul class="rankikgUl">
				<li class="first"><img src="resources/images/flag-first.png" alt="1위"><span class="nickname">1위 user01</span><span class="hours">22:59:59</span></li>
				<li class="second"><img src="resources/images/flag-second.png" alt="2위"><span class="nickname">2위 user02</span><span class="hours">18:33:33</span></li>
				<li class="third"><img src="resources/images/flag-third.png" alt="3위"><span class="nickname">3위 user03</span><span class="hours">10:00:00</span></li>
				<c:choose>
				<c:when test="${ loginUser == null }">
				<li class="myranking"><img src="resources/images/flag-me.png" alt="내랭킹">로그인하여 나의 랭킹을 확인해보세요.</li>
				</c:when>
				<c:otherwise>
				<li class="myranking"><img src="resources/images/flag-me.png" alt="내랭킹"><span class="nickname">?위 ${ loginUser.nickName }</span><span class="hours">5:03:00</span></li>
				</c:otherwise>
				</c:choose>
				</ul>
			</div>
			<div class="todo-area">
				<div class="todo-title">
					<label class="title">오늘의 할일</label><button class="add"></button>
				</div>
				<div class="todo-list">
					<div class="hiddenScroll">
					<div class="scrollBlind">
						<c:choose>
						<c:when test="${ loginUser == null || Todolist == null }">
							 <label class="nolist">오늘의 할일을 추가하세요.</label>
						</c:when>
						<c:otherwise>
							<ul class="list">							
							<c:forEach var="todo" items="${ Todolist }">
								<li><span>${ todo.todo_content }</span><button class="edit"></button><button class="delete"></button></li>
							</c:forEach>
							</ul>
						</c:otherwise>
						</c:choose>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	<%-- 로그인 하지 않았을 시 --%>
	<c:if test="${ loginUser == null }">
	<script>
		// 로그아웃 후 뒤로가기로 로그인된 페이지 접속 못하게 막기 
		history.pushState(null, null, location.href); 
		window.onpopstate = function(event) { history.go(1); };	
		// 로그인 안했을 때 리스트 추가 시 로그인 페이지로 이동여부묻기 
		$(".add").click(function(){
			 if(confirm("로그인 하시겠습니까?")){
				 location.href="${ contextPath}/login";
			 }
		  });
	</script>
	</c:if>
	<c:if test="${ loginUser != null }">
	<script>
	
	 // todolist 추가 
		$(".add").click(function(){
			if( $(".scrollBlind ul").hasClass("list")){
				$(".list").append("<li><textarea maxlength='48'></textarea></li>");
			}else{
				$(".scrollBlind label").replaceWith("<ul class='list'><li><textarea maxlength='48'></textarea></li></ul>");
			}
			
			$(".list:last-child textarea").focus();
			
			$(".list:last-child textarea").keydown(function(event){
				if(event.key == "Enter"){
					this.blur();
				}
			});
			
			$(".list:last-child textarea").blur(function(){
				var text = this.value;
				$(".list:last-child").innerHTML = text;

				$.ajax({
					url : "${ contextPath }/main/todolistAdd",
					type : "post",
					data : { addList : text },
					dataType : "json",
					success : function(data){
						if( data != null ){
							var html ='';
							for(var key in data){
								html += '<li><span>'+ data[key].todo_content + '</span><button class="edit"></button><button class="delete"></button></li>';
							}
							$(".list").html(html);
						}else{
							alert("오늘의 할일 추가에 실패하셨습니다.");
						}
					},
					error : function(e){
						console.log(e);
					}
				});
				
			});
		}); 
	
	// todolist 수정
	 	$(document).on('click', '.edit', function(){
	 		var current = this.previousSibling;
	 		var old = $(current).text();
			$(current).replaceWith('<textarea>'+old+'</textarea>');
			var textarea = document.getElementsByTagName("textarea");
			
			$(textarea).keydown(function(event){
				if(event.key == "Enter"){
					this.blur();
				}
			});
			
			$(textarea).blur(function(){
				var update = textarea[0].value;
			$.ajax({
				url : "${contextPath}/main/todolistUpdate",
				type : "post",
				data : { old : old, update : update}, 
				dataType : "json",
				success : function(data){
					if( data != null ){
						var html ='';
						for(var key in data){
							html += '<li><span>'+ data[key].todo_content + '</span><button class="edit"></button><button class="delete"></button></li>';
						}
						$(".list").html(html);
					}else{
						alert("오늘의 할일 수정에 실패하셨습니다.");
					}
				},
				error : function(e){
					console.log(e);
				}
				
			});
		
			 });
	 	});
		
	 // todolist 삭제
	 	$(document).on('click', '.delete', function(){
	 		var current = this.previousSibling.previousSibling;
	 		var content = $(current).text();
			$.ajax({
				url : "${contextPath}/main/todolistDelete",
				type : "post",
				data : { content : content}, 
				dataType : "json",
				success : function(data){
					if( data != null ){
						var html ='';
						for(var key in data){
							html += '<li><span>'+ data[key].todo_content + '</span><button class="edit"></button><button class="delete"></button></li>';
						}
						$(".list").html(html);
					}else{
						alert("오늘의 할일 삭제에 실패하셨습니다.");
					}
				},
				error : function(e){
					console.log(e);
				}
				
			});
			
	 	});	

			
	</script>
	</c:if>

</body>
</html>






















