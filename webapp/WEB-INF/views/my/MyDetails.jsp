<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 구매내역</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<link href='<%= request.getContextPath() %>/resources/css/qna-main.css?after' rel='stylesheet'>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
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
	/* 보유 코인 박스 */
	.coin_area {
		height: 100px;
		margin-bottom: 10px;
		border: 1px solid black;
	}
	.coin_box {
		display: flex;
		justify-content: space-between;
		padding: 10px 20px;
	}
	.btn_area {
		padding: 10px 0;
	}
	button[id$=btn] {
		width: 100px;
		height: 50px;
		margin-left: 20px;
	}
	#charge_btn {
		background: #5FC5FF;
		color: white;
	}
	#coin h1{
		margin-top: 12px;
	
	}
	/* 리스트 */
	div[class$=list] {
		border: 1px solid black;
		width: 100%;
		height: 350px;
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
	div[class$=list] p {
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
				<li><a href="<%= request.getContextPath() %>/my/home">내 정보</a></li>
				<li><a href="<%= request.getContextPath() %>/my/study">내 스터디</a></li>
				<li><a href="<%= request.getContextPath() %>/my/q&a">내 Q&A</a></li>
				<li class="current"><a href="#">결제 내역</a></li>
			</ul>
		</nav>
		<div class="content">
			<div class="coin_area">
				<div class="coin_box">
					<h2>보유 코인</h2>
					<span id="coin"><h1>${ loginUser.userCoin } 코인</h1></span>
					<span class="btn_area">
						<button id="charge_btn" onclick="openPopup('<%=request.getContextPath()%>/coin', 'coin_charge', 700, 900);">충전</button>
						<button id="refund_btn" onclick="openPopup('<%=request.getContextPath()%>/my/refundView', 'refund', 700, 900);">환불</button>
					</span>
				</div>
			</div>
			<div class="details_area">
				<h2>구매/충전/환불 내역</h2>
				<table class="board_list">
	                <thead>
	                    <tr>
	                        <th>날짜</th>
	                        <th>구매/충전/환불</th>
	                        <th>코인</th>
	                        <th>총코인</th>
	                    </tr>
	                </thead>
	                <tbody>
						<c:forEach var="mb" items="${ MyboardList }">
							<tr>
								<td>${ mb.board_no }</td>
								<td>${ mb.cname }</td>
								<td class="tit" onclick="boardDetailView(${mb.board_no})">${ mb.board_title }</td>
								<td>${ mb.nickname }</td>
								<td>${ mb.create_date }</td>
								<td>${ mb.count }</td>
							</tr>
						</c:forEach>
					</tbody>
	            </table>	
			</div>
			<div class="my_item">
				<h2>내 아이템</h2>
				<table class="board_list">
	                <thead>
	                    <tr>
	                        <th>아이템명</th>
	                        <th>코인</th>
	                        <th>사용 시작일자</th>
	                        <th>유효기간</th>
	                    </tr>
	                </thead>
	                <tbody>
						<c:forEach var="il" items="${ ItemList }">
							<tr>
								<td>${ il.productName }</td>
								<td>${ il.productPrice }</td>
								<td>${ il.startDate }</td>
								<td>${ il.expirationDate }</td>
							</tr>
						</c:forEach>
					</tbody>
	            </table>
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