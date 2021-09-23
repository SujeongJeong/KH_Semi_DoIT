<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/qna-boardDetail.css' rel='stylesheet'>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
    <h1 style = 'color : #5FC5FF; text-align:center; margin : 50px 0 '>DO IT - 공지사항</h1>
	<div class="content">
		<div class="board_wrap">
        <div class="board_info">
            <div class="writer_info">
                <img class="user_img" src='<%= request.getContextPath() %>/resources/images/user.png' alt="게시글 유저">
                <div class="avatar_info">
                    <div class="nickname">관리자</div>
                    <div class="write_time"> 
                        <fmt:formatDate value="${ notice.create_date }" pattern="yyy.MM.dd HH:mm:ss"/> 작성
                   		<c:if test= "${ notice.create_date != notice.modify_date}">
                        <fmt:formatDate value="${ notice.modify_date }" pattern="yyy.MM.dd HH:mm:ss"/> 수정됨
    					</c:if>     
                                       
                    </div>
                </div>
            </div>
            <span>조회수 : ${ notice.count }</span>
        </div>
        <div class="board_title"><h1>${ notice.notice_title }</h1></div>
        
        <div class="board_text">${ notice.notice_content }</div>
        
        <c:if test="${ !empty loginUser && loginUser.userType == 'A' }">
	    <div class="board_btn btn">
	        <button class="del_btn" onclick="noticeDelete()">삭제</button>
	        <button class="modify_btn" onclick="updateNoticeView();">수정</button>
	    </div>
        <!--  form 태그를 post 방식으로 submit -->
		<form name ="noticeForm" method="post">
			<input type="hidden" name="notice_no" value="${ notice.notice_no }">
		</form>
        </c:if>
</div>
    
        <!-- 댓글등록 -->
        <div class="comment_header">Comment</div>
        <div class="new_comment comment">
            <img class="user_img" src='<%= request.getContextPath() %>/resources/images/user.png' alt="게시글 유저">
			<textarea class="reply_content"></textarea>
            <button onclick="addReply(${ notice.notice_no });">등록</button>
        </div>
    
        
        
        <div class="reply_list">
			<c:forEach items="${ board.replyList }" var="r">
				<ul class="reply_ul">
					<li class="rwriter">${ r.userName }</li>
					<li class="rcontent">${ r.rcontent }</li>
					<li class="rdate"><fmt:formatDate value="${ r.createDate }"
					type="both" pattern="yyyy.MM.dd HH:mm:ss"/></li>
				</ul>
			</c:forEach>
		</div>
        
        <!-- 댓글 -->
        <div class="comment_wrapper">
            <div class="writer_info">
                <img class="user_img" src='<%= request.getContextPath() %>/resources/images/user.png' alt="게시글 유저">
                <div class="avatar_info">
                    <div class="nickname">nickname</div>
                    <div class="write_time">
                        2021-07-22 15:19:04 작성  
                        <fmt:formatDate value="${ board.createDate }" pattern="yyy.MM.dd HH:mm:ss"/>
                        2021-07-22 15:23:14 수정됨
                        <fmt:formatDate value="${ board.modifyDate }" pattern="yyy.MM.dd HH:mm:ss"/>
                    </div>
                </div>
            </div>
            <pre class="comment">MYSQL 이면 group_concat 함수 사용해보세요.</pre>
            <div class="comment_btn btn">
                <button class="report_btn"
                onclick="openPopup('<%= request.getContextPath() %>/qnaReport', 'qnaReport', 500, 360);">신고</button>
                <button class="del_btn" onclick="commentDelete()">삭제</button>
                <button class="modify_btn">수정</button>
            </div>
        </div>

        <div class="comment_wrapper">
            <div class="writer_info">
                <img class="user_img" src='<%= request.getContextPath() %>/resources/images/user.png' alt="게시글 유저">
                <div class="avatar_info">
                    <div class="nickname">nickname</div>
                    <div class="write_time">
                        2021-07-22 15:19:04 작성  
                        <fmt:formatDate value="${ board.createDate }" pattern="yyy.MM.dd HH:mm:ss"/>
                        2021-07-22 15:23:14 수정됨
                        <fmt:formatDate value="${ board.modifyDate }" pattern="yyy.MM.dd HH:mm:ss"/>
                    </div>
                </div>
            </div>
            <div class="comment_btn btn">
                <button class="report_btn">신고</button>
                <button class="del_btn">삭제</button>
                <button class="modify_btn">수정</button>
            </div>
            <pre class="comment">group by 자체가 같은걸 묶는거에요</pre>
        </div>
        <div class="comment_wrapper">
            <div class="writer_info">
                <img class="user_img" src='<%= request.getContextPath() %>/resources/images/user.png' alt="게시글 유저">
                <div class="avatar_info">
                    <div class="nickname">nickname</div>
                    <div class="write_time">
                        2021-07-22 15:19:04 작성  
                        <fmt:formatDate value="${ board.createDate }" pattern="yyy.MM.dd HH:mm:ss"/>
                        2021-07-22 15:23:14 수정됨
                        <fmt:formatDate value="${ board.modifyDate }" pattern="yyy.MM.dd HH:mm:ss"/>
                    </div>
                </div>
            </div>
            <div class="comment_btn btn">
                <button class="report_btn">신고</button>
                <button class="del_btn">삭제</button>
                <button class="modify_btn">수정</button>
            </div>
            <pre class="comment report_comment">신고 처리된 글입니다.</pre>
        </div>
	</div>	
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
	

	<script>
		function updateNoticeView(){
			document.forms.noticeForm.action = "${contextPath}/notice/updateView";
			document.forms.noticeForm.submit();
		}
		
		function noticeDelete(){
			if(confirm("이 공지사항을 삭제하시겠습니까?")){
				document.forms.noticeForm.action = "${contextPath}/notice/delete";
				document.forms.noticeForm.submit();
			}
		}

		function commentDelete(){
			if(confirm("정말로 삭제하시겠습니까?"))
				location.href = '<%= request.getContextPath() %>/commentDelete';
		}
		
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
	 	
		// 댓글 달기
		function addReply(notice_no){
			$.ajax({
				url : "${contextPath}/board/insertReply",
				type : "post",
				data : { notice_no : notice_no, content : $(".reply_content").val() },
				dataType : "json",
				success : function(data){
					if(data != null){
						console.log(data);
						
						var html = '';
						
						// 새로 받아온 갱신 된 댓글 목록을 for문을 통해 html에 저장
						for(var key in data){
							html += '<ul class="reply_ul"><li class="rwriter">'
								  + data[key].userName + '</li><li class="rcontent">'
								  + data[key].rcontent + '</li><li class="rdate">'
								  + data[key].createDate + '</li></ul>';
						}
						
						// 갱신 된 댓글 목록을 다시 적용
						$(".reply_list").html(html);
						// 댓글 작성 부분 리셋
						$(".reply_content").val("");
						
					} else{
						alert('댓글 입력 실패!');
					}
				},
				error : function(e){
					console.log(e);
				}
			});
		}
		
		
	</script>
	
	
<%-- 	<script src="<%= request.getContextPath() %>/resources/js/imagePreview.js"></script> --%>
</body>
</html>