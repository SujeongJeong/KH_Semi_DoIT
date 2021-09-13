<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report</title>
</head>
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
<body>
    <div class="outer">
	    <form>
	    <%-- <form id="updatePwdForm" action="<%= request.getContextPath() %>/pwdModify"
	method="post" onsubmit="return checkPwd();"> --%>
	        <h1>신고 사유</h1>
	        <div class="report_radio">
	            <input type="radio" id="porno" name="report" value="porno" onclick="div_OnOff(this.value,'report_comment');">
	            <label for="porno">음란</label>
	            <input type="radio" id="ad" name="report" value="ad" onclick="div_OnOff(this.value,'report_comment');">
	            <label for="ad">광고</label>
	            <input type="radio" id="abuse" name="report" value="abuse" onclick="div_OnOff(this.value,'report_comment');">
	            <label for="abuse">욕설</label>
	            <input type="radio" id="etc" name="report" value="etc" onclick="div_OnOff(this.value,'report_comment');">
	            <label for="etc">기타</label>
	
	        </div>
	        <textarea name="" id="report_comment" cols="30" rows="10" placeholder="신고사유를 입력해주세요."></textarea>
	        <div class="btn_area">
				<button id="updatePwdBtn">등록</button>
			</div>
	    </form>
    </div>
	
    <script>
        function div_OnOff(v,id){
         // 라디오 버튼 value 값 조건 비교
            if(v != "etc"){
            document.getElementById(id).style.display = "none"; // 보여줌
            }else{
            document.getElementById(id).style.display = "block"; // 숨김
            }
        }
        
        $('#report_comment').on('keyup', function() {
            if($(this).val().length > 600) {
                alert("글자수는 600자로 이내로 제한됩니다.");
                $(this).val($(this).val().substring(0, 600));
            }
        });
    </script>
	
</body>
</html>