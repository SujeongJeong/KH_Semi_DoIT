<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/qna-boardInsert.css?aftessr' rel='stylesheet'>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
	<div class="content">
		<div class="wrap">
			<div class="board_area">
				<div class="board_title">
					<h1>새 글 쓰기</h1>
				</div>
				<div class="board_content">
					<form method="post" action="${ contextPath }/board/insert">
						<div class="content">
							<span class="title_span">
                                <h4>제목</h4>
                            </span>
							<span class="input_area"> <input type="text" name="title"
								placeholder="제목을 입력해주세요." required>
							</span> <br><br>
							<textarea class="textarea" rows="20" cols="121" name="content"
								required></textarea>
						</div>
						<div class="btn_area">
							<button type="button">목록으로</button>
							<button type="submit">작성하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>	
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>