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
	
   h1{
   	color : red;
   }
   
   .error_img{
   		width : 300px;
   		height : 300px;
   }
</style>
</head>
<body>
<%    
//	request.setCharacterEncoding("UTF-8");
  //  int rid = Integer.parseInt(request.getParameter("reply_no"));
 
%>
    <div class="outer">
	  
	        <h1>신고 처리중입니다.</h1>
	        <div class="reporting">
	          <img class="error_img" src='<%= request.getContextPath() %>/resources/images/error.png' alt="에러">
				
	        </div>
	     

    </div>
	
</body>
</html>