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
	        <h1>nickname1님</h1>
	        <div class="refund_list">
	            <div class="have_coin">
	            	<h3>보유 코인 : </h3> 
	            </div>
	            <div class="details_area">
				<h2>구매/충전/환불 내역</h2>
				<form name="detailsForm" method="post">
					<input type="radio" name="details_history" id="purchase" value="purchase" checked><label for="purchase">구매</label>
					<input type="radio" name="details_history" id="charge" value="charge" <c:if test="${ radioValue eq 'charge' }">checked</c:if>><label for="charge">충전</label>
					<input type="radio" name="details_history" id="refund" value="refund" <c:if test="${ radioValue eq 'refund' }">checked</c:if>><label for="refund">환불</label>
				</form>
				<table class="board_list">
	                <c:choose> 
	                	<c:when test="${ radioValue eq 'purchase' }">
	                		<thead>
	                			<tr>
				                	<th>구매일</th>
				                    <th>상품명</th>
				                    <th>구매 개수</th>
				                    <th>상품 코인</th>        
				               </tr>
	                		</thead>
	                		<tbody>
	                			<c:forEach var="pl" items="${ PurchaseList }">
									<tr>
										<td>${ pl.purchaseDate }</td>
										<td>${ pl.productName }</td>
										<td>${ pl.productCount }</td>
										<td>${ pl.productPrice * pl.productCount }</td>
									</tr>
								</c:forEach>
	                		</tbody>
	                	</c:when>
	                	<c:when test="${ radioValue eq 'charge' }">
	                		<thead>
	                			<tr>
				            		<th>충전일</th>
				            		<th>충전 코인</th>
				            	</tr>
	                		</thead>
	                		<tbody>
	                			<c:forEach var="cl" items="${ ChargeList }">
									<tr>
										<td>${ cl.chargeDate }</td>
										<td>${ cl.chargeCoin }</td>
									</tr>
								</c:forEach>
	                		</tbody>
	                	</c:when>
	                	<c:otherwise>
	                		<thead>
	                			<tr>
				            		<th>환불 신청일</th>
				            		<th>환불 신청 코인</th>
				            		<th>환불 완료일</th>
				            	</tr>
	                		</thead>
	                		<tbody>
	                			<c:forEach var="rl" items="${ RefundList }">
									<tr>
										<td>${ rl.refundDate }</td>
										<td>${ rl.refundCoin }</td>
										<td>${ rl.completeDate }</td>
									</tr>
								</c:forEach>
	                		</tbody>
	                	</c:otherwise>
	                </c:choose>
	            </table>
	            <ul class="board_paging">
								<li><a href="${ contextPath }/my/details?page=1${ searchParam }">&lt;&lt;</a></li>
								
								<!-- 이전 페이지로(<) -->
								<li>
								<c:choose>
									<c:when test="${ pi.page > 1 }">
									<a href="${ contextPath }/my/details?page=${ pi.page - 1}${ searchParam }">&lt;</a>
									</c:when>
									<c:otherwise>
									<a href="#">&lt;</a>
									</c:otherwise>
								</c:choose>
								</li>
								
								<!-- 페이지 목록(최대 10개) -->
								<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
								<li>
									<c:choose>
										<c:when test="${ p eq pi.page }">
											<a href="#" class="current_page">${ p }</a>
										</c:when>
										<c:otherwise>
											<a href="${ contextPath }/my/details?page=${ p }${ searchParam }">${ p }</a>
										</c:otherwise>
									</c:choose>
								</li>
								</c:forEach>
								
								<!-- 다음 페이지로(>) -->
								<li>
								<c:choose>
									<c:when test="${ pi.page < pi.maxPage }">
									<a href="${ contextPath }/my/details?page=${ pi.page + 1 }${ searchParam }">&gt;</a>
									</c:when>
									<c:otherwise>
									<a href="#">&gt;</a>
									</c:otherwise>
								</c:choose>
								</li>
								
								<!-- 맨  끝으로(>>) -->
								<li><a href="${ contextPath }/my/details?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
							</ul>	
			</div>
	        </div>
    </div>

	
</body>
</html>