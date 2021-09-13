<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Refund</title>
</head>
<style>
   .outer{
		width:90%;
		min-width : 450px;
		margin: 0 auto;
        text-align: center;
	}

    .have_coin{
        margin: 20px;
    }

    table {
         border-collapse: collapse;
         width:100%;
    }

	
	.refund_table{
        margin: 0 auto;
        border-top: 2px solid gray;
    }


    .refund_table thead {
        background: #f9f9f9;
    }

    .refund_table th,
    .refund_table td {
        padding: 10px;
        font-size: 14px;
    }

    .refund_table td {
        height : 30px;
        text-align: center;
    }
	

</style>
<body>
    <div class="outer">
	    
	    <%-- <form id="updatePwdForm" action="<%= request.getContextPath() %>/pwdModify"
	method="post" onsubmit="return checkPwd();"> --%>
	        <h1>nickname1님</h1>
	        <div class="refund_list">
	            <div class="have_coin">
	            	<h3>보유 코인 : 32</h3> 
	            </div>
	            
	            	<table class="refund_table">
						<thead>
							<tr>
                                <th>날짜</th>
								<th>구매/충전/환불</th>
								<th>코인</th>
								<th>총 코인</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>21/08/03</td>
								<td>코인 충전</td>
								<td>+300</td>
								<td>500코인</td>
							</tr>
						</tbody>
					</table>
	        </div>
    </div>

	
</body>
</html>