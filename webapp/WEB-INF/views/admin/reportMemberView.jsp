<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리 - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/admin-ReportMember.css?afters' rel='stylesheet'>
</head>
<body>
<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	<!-- 관리자 nav -->
		<div class="admin-wrap">
			<nav id="admin-nav">
				<ul>
					<li>
						<a href="<%= request.getContextPath() %>/admin/home">회원관리</a>
					</li>
					<li>
						<a href="<%= request.getContextPath() %>/admin/study">스터디관리</a>
					</li>
					<li>
						<a href="<%= request.getContextPath() %>/admin/refund">환불내역</a>
					</li>
					<li class="report current">
						<a href="<%= request.getContextPath() %>/admin/reportMember">신고내역</a>
						<ul class="report-ul">
							<li><a href="<%= request.getContextPath() %>/admin/reportMember">-&ensp;회원</a></li>
							<li><a href="<%= request.getContextPath() %>/admin/reportStudy">-&ensp;스터디</a></li>
						</ul>
					</li>
				</ul>
			</nav>


        <!-- 관리자 content -->    
			<content class="content">
				<div id="board-list-wrap">
					<h1>신고내역 - 회원</h1>
					<div class="board-header">
						<div class="search">
							구분 : 
							<select name="category">
								<option value="10">전체</option>
								<option value="20">게시물</option>
								<option value="30">댓글</option>
								<option value="40">스터디 회원</option>
							</select>&emsp;
							정렬 : 
							<select name="range">
								<option value="10">최신순</option>
								<option value="20">신고 횟수</option>
								<option value="30">작성자 누적 신고</option>
							</select>&emsp;
							<button><img src="../resources/images/search_btn.png"></button>
						</div>
					</div>
					<table class="board-list">
						<caption>게시판 목록</caption>
						<thead>
							<tr>
                                <th>번호</th>
								<th>날짜</th>
								<th>구분</th>
								<!-- <th>제목/내용</th> -->
								<th>신고당한 ID</th>
								<th>신고 횟수</th>
								<th>회원 누적신고</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>2021-08-28</td>
								<td class="reportPopup">게시글</td>
								<!-- <td>학원 홍보</td> -->
								<td>닉네임1</td>
								<td class="reportPopup" onclick="openPopup('<%= request.getContextPath() %>/reportList', 'reportList', 800, 500);">1</td>
								<td class="reportPopup" onclick="openPopup('<%= request.getContextPath() %>/reportAllList', 'reportAllList', 800, 500);">6</td>
							</tr>

						</tbody>
					</table>
					<div class="paging">
						<a href="#" class="bt">&lt;&lt;</a>
						<a href="#" class="bt">&lt;</a>
						<a href="#" class="num on">1</a>
						<a href="#" class="num">2</a>
						<a href="#" class="num">3</a>
						<a href="#" class="num">4</a>
						<a href="#" class="num">5</a>
						<a href="#" class="num">6</a>
						<a href="#" class="num">7</a>
						<a href="#" class="num">8</a>
						<a href="#" class="num">9</a>
						<a href="#" class="num">10</a>
						<a href="#" class="bt">&gt;</a>
						<a href="#" class="bt">&gt;&gt;</a>
					</div>
				</div>
			</content>



		</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
	<script>
	// 팝업창 호출
	function openPopup(url, title, width, height){
		// 왼쪽으로 부터 거리(가운데 맞추기)
		let left = (document.body.clientWidth/2) - (width/2);
		// 듀얼모니터를 위한 계산 (듀얼모니터를 쓰고 있을때 추가로 )
		left += window.screenLeft;
		let top = (screen.availHeight/2) - (height/2);
		
		let options = "width="+width+",height="+height+",left="+left+",top="+top;
		
		
		// 새창 열기
		window.open(url, title, options);
	}
	
	</script>
</body>
</html>