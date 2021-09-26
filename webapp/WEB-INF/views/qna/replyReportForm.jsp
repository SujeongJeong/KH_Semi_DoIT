<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" 
integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<style>
   .outer{
		width:90%;
		min-width : 450px;
		margin: 0 auto;
        text-align: center;
	}
	
    .report_radio{
        margin: 30px 0;
    }
	
    #report_comment{
        display: none;
        margin : 0 auto;
    }
    
    .btnArea {
		text-align:center;
		padding : 30px;

	}
	
	button {
		width : 100px;
		height : 35px;
		border : 0px;
		color:white;
		background:#282A35;
		margin : 15px;
		cursor:pointer
	}
</style>

<%
	if(request.getAttribute("result") != null){
		if(request.getAttribute("result").equals("success")){
%>
<script>
	alert('신고처리가 완료되었습니다.');
	window.close();
</script>
<%
		} else{
%>
<script>
	alert('신고처리가 실패되었습니다.');
</script>
<%
		}
	}
%>
</head>
<body>
<%    
	request.setCharacterEncoding("UTF-8");
    int rid = Integer.parseInt(request.getParameter("reply_no"));
 
%>
    <div class="outer">
	    <form id="reportForm" action="<%= request.getContextPath() %>/replyReport"
	    method="post" onsubmit="return checkReport();">
	        <h1>신고 사유</h1>
	        <div class="report_radio">
	            <input type="radio" id="porno" name="report" value="음란 " onclick="div_OnOff(this.value,'report_comment');">
	            <label for="porno">음란</label>
	            <input type="radio" id="ad" name="report" value="광고" onclick="div_OnOff(this.value,'report_comment');">
	            <label for="ad">광고</label>
	            <input type="radio" id="abuse" name="report" value="욕설 " onclick="div_OnOff(this.value,'report_comment');">
	            <label for="abuse">욕설</label>
	            <input type="radio" id="etc" name="report" value="기타 : " onclick="div_OnOff(this.value,'report_comment');">
	            <label for="etc">기타</label>
				
	        </div>
	        <textarea name="etc_comment" id="report_comment" cols="30" rows="10" placeholder="신고사유를 입력해주세요."></textarea>
	        <div class="btn_area">
				<button id="updatePwdBtn">등록</button>
			</div>
			 <input type="hidden" name="reply_no" value=<%= rid %> >
	    </form>
    </div>
	
    <script>
        function div_OnOff(v,id){
         // 라디오 버튼 value 값 조건 비교
            if(v != "기타 : "){
            document.getElementById(id).style.display = "none"; // 숨김
            }else{
            document.getElementById(id).style.display = "block"; // 보여줌
            }
        }
        
        $('#report_comment').on('keyup', function() {
            if($(this).val().length > 600) {
                alert("글자수는 600자로 이내로 제한됩니다.");
                $(this).val($(this).val().substring(0, 600));
            }
        });
        
        function checkReport(){
	/* 		const porno = document.getElementById('porno');
			const ad = document.getElementById('ad');
			const abuse = document.getElementById('abuse');
			const etc = document.getElementById('etc'); */
			
			if( !$('input:radio[id=porno]').is(':checked') && !$('input:radio[id=ad]').is(':checked') && !$('input:radio[id=abuse]').is(':checked') && !$('input:radio[id=etc]').is(':checked')){
				alert('신고사유를 선택해주세요');
				return false;
			}
			
			if($('input:radio[id=etc]').is(':checked') && $("#report_comment").val() == ''){
				alert('내용을 입력해주세요.');
				return false;
			}
			
			return true;
		}
        
    </script>
	
</body>
</html>