<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/qna-boardInsert.css?after' rel='stylesheet'>
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
						<div class="contents">
							<span class="title_span">
								<h4>분류</h4>
							</span>
							<span class="input_area"> 
							<select name="category">
								<option value="10">language</option>
								<option value="20">embedded</option>
								<option value="30">ai</option>
								<option value="40">backend</option>
								<option value="50">project</option>
								<option value="60">game</option>
								<option value="70">android</option>
								<option value="80">bigdata</option>
								<option value="90">blockchain</option>
								<option value="100">devops</option>
							</select>
							</span>
							<span class="title_span">
                                <h4>제목</h4>
                            </span>
							<span class="input_area"> <input type="text" name="title"
								placeholder="제목을 입력해주세요." required>
							</span> <br><br>
							
							
							<div class="image_area"></div>
							<div class="image_area"></div>

							<input type="file" name="contentImg1" accept="image/gif,image/jpeg,image/png"> 
							<input type="file" name="contentImg2" accept="image/gif,image/jpeg,image/png">
							
							
							
							
							<textarea class="textarea" rows="20" cols="137" name="content" required>
								
								
								</textarea>
						</div>
						<div class="btn_area">
							<button type="button" onclick="location.href='<%= request.getContextPath() %>/qna/home'">목록으로</button>
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
	
	<script src="<%= request.getContextPath() %>/resources/js/imagePreview.js"></script>
</body>
</html>