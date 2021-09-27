<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Q&A - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/qna-boardInsert.css?after' rel='stylesheet'>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
   <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

</head>
<body>
  
  
  <%-- 	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	 --%>
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
  <div class="content">
		<div class="wrap">
			<div class="board_area">
				<div class="board_title">
					<h1>새 글 쓰기</h1>
				</div>
				<div class="board_content">
					<form method="post" action="<%=request.getContextPath()%>/qna/insert">
						<div class="contents">
							<span class="title_span">
								<h4>분류</h4>
							</span>
							<span class="input_area"> 
							<select name="category">
								<option value="1">language</option>
								<option value="2">embedded</option>
								<option value="3">ai</option>
								<option value="4">back-end</option>
								<option value="5">front-end</option>
								<option value="6">game</option>
								<option value="7">app</option>
								<option value="8">bigdata</option>
								<option value="9">blockchain</option>
								<option value="10">devops</option>
								<option value="11">project</option>
							</select>
							</span>
							<span class="title_span">
                                <h4>제목</h4>
                            </span>
							<span class="input_area"> <input type="text" name="title"
								placeholder="제목을 입력해주세요." required>
							</span> <br><br>
						<textarea  rows="20" cols="136" name="content" id="summernote" required></textarea>
	
						</div>
						<div class="btn_area">
							<button type="button" onclick="location.href='<%= request.getContextPath() %>/qna/home'">목록으로</button>
							<button type="submit" id="save" value="save">작성하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>	
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
  <script>
	// 작성하기 버튼 눌렀을때 content 내용 비어있을때
	$('#save').click(function(){
		if($('#summernote').val() == ''){
			alert('내용을 입력해주세요.');
		} 
	});

// 글쓰기 api
  $('#summernote').summernote({
          placeholder: '내용을 입력해주세요.',
          tabsize: 2,
          height: 300
  });
  </script>
</body>
</html>