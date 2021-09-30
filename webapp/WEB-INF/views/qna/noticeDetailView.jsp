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
	<link href='<%= request.getContextPath() %>/resources/css/qna-boardDetail.css?after' rel='stylesheet'>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" 
integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
    <h1 style = 'color : #5FC5FF; text-align:center; margin : 50px 0 '>DO IT - 공지사항</h1>
	<div class="content">
		<div class="board_wrap">
        <div class="board_info">
            <div class="writer_info">
                <img class="user_img" src="${ contextPath }/resources/images/admin.png"  alt="게시글 유저">
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
	        <div class="board_title">
	            <h1>${ notice.notice_title }</h1>
	        </div>
	        <div class="board_text">
	        	${ notice.notice_content }
	        </div>
	        
		        
		    <div class="board_btn btn">
		        <c:if test="${ notice.user_no == loginUser.userNo || loginUser.userType == 'A' }">
		        <button class="del_btn" onclick="noticeDelete()">삭제</button>
		        <button class="modify_btn" onclick="updateNoticeView()">수정</button>
		        <form name ="noticeForm" method="post">
					<input type="hidden" name="notice_no" value="${ notice.notice_no }">
				</form>
		        </c:if>
		    </div>
		</div>
    
    
         <!-- 댓글등록 -->
        <div class="comment_header">Comment</div>
        <div class="new_comment comment">
            <img class="user_img" src="${ contextPath }${ loginUser.profileImg }" alt="게시글 유저">
            <textarea class="reply_content"></textarea>
            <button onclick="addReply(${ notice.notice_no });">등록</button>
        </div>
       
         
        <!-- 댓글 -->
        <div class="comment_list">
	        <c:forEach items="${ notice.replyList }" var="r">
	        <c:choose>
	        	<c:when test="${ flag == 'u' && rid == r.reply_no}">
	        		<!-- 댓글수정 -->
					<div class="comment_wrapper" style="border: 5px solid #5FC5FF">
			            <div class="writer_info">
			                <img class="user_img" src="${ contextPath }${ loginUser.profileImg }" alt="게시글 유저">
			                <div class="avatar_info">
			                    <div class="nickname">${ r.nickName }</div>
			                    <div class="write_time">
			                        <fmt:formatDate value="${ r.create_date }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/> 작성
			                        <c:if test= "${ r.create_date != r.modify_date}">
			                        <fmt:formatDate value="${ r.modify_date }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/> 수정됨
			                    	</c:if>
			                    </div>
			                </div>
			            </div>
			            <textarea class="modifyReply_content">${r.reply_content }</textarea>
			            <div class="comment_btn btn">
			                <button class="confirm_btn" onclick="updateReply(${ r.notice_no }, ${ r.reply_no });">등록</button>
			            </div>
		  		    </div>
				</c:when>
	        	<c:otherwise>
		        	<div class="comment_wrapper">
			            <div class="writer_info">
			                <img class="user_img" src='${ contextPath }${ r.profile_img }' alt="게시글 유저">
			                <div class="avatar_info">
			                    <div class="nickname">${ r.nickName }</div>
			                    <div class="write_time">
			                        <fmt:formatDate value="${ r.create_date }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/> 작성
			                        <c:if test= "${ r.create_date != r.modify_date}">
			                        <fmt:formatDate value="${ r.modify_date }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/> 수정됨
			                    	</c:if>
			                    </div>
			                </div>
			            </div>
			            <pre class="comment">${ r.reply_content }</pre>
			            <div class="comment_btn btn">
			            	<c:if test="${ r.user_no != loginUser.userNo}">
					                <button class="report_btn"
				                onclick="openPopup('<%= request.getContextPath() %>/replyReport?reply_no='+${ r.reply_no }, 'qnaReport', 500, 360);">신고</button>
			            	</c:if>
			                <c:if test="${ r.user_no == loginUser.userNo || loginUser.userType == 'A' }">
			                <button class="del_btn" onclick="replyDelete(${ r.notice_no },${ r.reply_no })">삭제</button>
			                <button class="modify_btn" onclick="modifyReply(${ r.notice_no },${ r.reply_no })">수정</button>

			                </c:if>
			            </div>
		  		    </div>
	        	</c:otherwise>
	        </c:choose>
            </c:forEach>
         </div>
	</div>	
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
	

	<script>
	// 페이지 reload
	function refreshMemList(){
		  location.reload();
		 }


 	// 팝업창 호출
	function openPopup(url, title, width, height){
		let left = (document.body.clientWidth/2) - (width/2);
		left += window.screenLeft;
		let top = (screen.availHeight/2) - (height/2);
		
		let options = "width="+width+",height="+height+",left="+left+",top="+top;
		
		// 새창 열기
		window.open(url, title, options);
	}
 	
 	// 게시글 삭제
	function noticeDelete(){
		if(confirm("공지사항을 삭제하시겠습니까?")){
			document.forms.noticeForm.action = "${contextPath}/notice/delete";
			document.forms.noticeForm.submit();
		}
	}
	
 	// 게시글 update
	function updateNoticeView(){
		document.forms.noticeForm.action = "${contextPath}/notice/updateView";
		document.forms.noticeForm.submit();
	}
	
	// 댓글 삭제
	function replyDelete(notice_no, reply_no){
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href = '<%= request.getContextPath() %>/noticeReply/delete?notice_no=' + notice_no + '&reply_no=' + reply_no;
		}
	}
	
	// 댓글 수정버튼 눌렀을 때
	function modifyReply(notice_no, reply_no){
		location.href = '<%= request.getContextPath() %>/noticeReply/update?notice_no=' + notice_no + '&reply_no=' + reply_no;
	}
	
	// 댓글 수정 후 등록버튼 눌렀을 때
	function updateReply(notice_no, reply_no){
		location.href = '<%= request.getContextPath() %>/noticeReply/updateview?notice_no=' + notice_no + '&reply_no=' + reply_no + '&reply_content=' + $(".modifyReply_content").val();
	}
	
	// 댓글 달기
	function addReply(notice_no){
		$.ajax({
			url : "${contextPath}/notice/insertReply",
			type : "post",
			data : { notice_no : notice_no, reply_content : $(".reply_content").val() },
			dataType : "json",
			success : function(data){
				if(data != null){
					
					refreshMemList();
					console.log(data);
					
					var html = '';
												
					// 새로 받아온 갱신 된 댓글 목록을 for문을 통해 html에 저장
					for(var key in data){
						html += '<div class="comment_wrapper"><div class="writer_info"><img class="user_img" src="${ contextPath }${ r.profile_img }" alt="게시글 유저">'
					           +'<div class="avatar_info"><div class="nickname">'
					           + data[key].nickName + '</div><div class="write_time">'
					           + data[key].create_date + ' 작성 ';

					           if(data[key].create_date !=  data[key].modify_date){
					        	html+= ' ' + data[key].modify_date +  '수정됨';
					        	}

					           html += '</div></div></div><pre class="comment">' + data[key].reply_content + '</pre><div class="comment_btn btn">';
					   
					           if(data[key].user_no != ${loginUser.userNo} ){
				       			html+= '<button class="report_btn" onclick="openPopup(\'<%= request.getContextPath() %>/replyReport?reply_no=\'+${ r.reply_no }, \'qnaReport\', 500, 360);">신고</button>';
				       			}
	
					       	   if(data[key].user_no == ${loginUser.userNo} || ${loginUser.userType == 'A' }){
			               	    html+='<button class="del_btn" onclick="replyDelete(${ r.notice_no },${ r.reply_no })">삭제</button><button class="modify_btn" onclick="modifyReply(${ r.notice_no },${ r.reply_no })">수정</button>';
			               	    }
			               		
					       	   html +='</div></div>';
			               	   
		               		}
					// 갱신 된 댓글 목록을 다시 적용
					$(".comment_list").html(html);
					// 댓글 작성 부분 리셋
					$(".reply_content").val("");
					
				} else{
					alert('내용을 입력해주세요!');
				}
			},
			error : function(e){
				console.log(e);
			}
		});
		 refreshMemList();
	}
		
	</script>
	
</body>
</html>