<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    table {
         border-collapse: collapse;
         width:100%;
    }

	
	.report_table{
        margin: 0 auto;
        border-top: 2px solid gray;
    }


    .report_table thead {
        background: #f9f9f9;
    }

    .report_table th,
    .report_table td {
        padding: 10px;
        font-size: 14px;
    }

    .report_table td {
        height : 30px;

    }
	

</style>
<body>
    <div class="outer">
	    
	    <%-- <form id="updatePwdForm" action="<%= request.getContextPath() %>/pwdModify"
	method="post" onsubmit="return checkPwd();"> --%>
	        <h1>신고 리스트</h1>
	            	<table class="report_table">
						<thead>
							<tr>
                                <th>날짜</th>
								<th>신고자 닉네임</th>
								<th>제목/내용</th>
								<th>신고사유</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach  var="r" items="${ reportList }">
							<tr>
								<td>${ r.report_date }</td>
								<td>${ r.nickname }</td>
								<td>${ r.board_title }</td>
								<td>${ r.report_content }</td>
							</tr>
						</c:forEach>	
						</tbody>
					</table>
    </div>

	
</body>
</html>