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
 <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
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
					<form method="post" action="<%=request.getContextPath()%>/qna/insert">
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
							<textarea class="textarea" rows="20" cols="136" name="content" id="textAreaContent" required></textarea>
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

		<!-- Smart Editor -->
<script type="text/javascript" src="<%=request.getContextPath()%>/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
 
 
<!-- Smart Editor -->
<script type="text/javascript">
 
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "textAreaContent",
    sSkinURI: "<%=request.getContextPath()%>/se2/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});
 
/* //‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["textAreaContent"].exec("UPDATE_CONTENTS_FIELD", [ ]);
 
    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("textAreaContent").value를 이용해서 처리한다.
  
    try {
        elClickedObj.form.submit();
    } catch(e) {
     
    }
} */

//저장버튼 클릭시 form 전송
$("#save").click(function(){
    oEditors.getById["textAreaContent"].exec("UPDATE_CONTENTS_FIELD", []);
    $("#frm").submit();
});    

 
// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/resources/uploadFiles/'+filepath+'">';
    //["기존 DB에 저장된 내용을 에디터에 적용할 문구"]
    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]);
}
 </script>
</body>
</html>