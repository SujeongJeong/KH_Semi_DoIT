<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 구매내역</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<link href='<%= request.getContextPath() %>/resources/css/qna-main.css?after' rel='stylesheet'>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<%
   if(request.getAttribute("result") != null) {
      if(request.getAttribute("result").equals("success")) {
%>
<script>
   alert('코인 환불 신청을 완료하였습니다.');
   opener.parent.location.reload();
   window.close();
</script>
<%
   } else {
%>
<script>
   alert(' 코인 환불 신청에 실패하였습니다.');
   window.close();
</script>
<%
      }
   }
%>
<style>
   /* 전체 감싸는 div */
   .my_wrap {
      display: flex;
   }   
   /* 사이드메뉴 영역 */
   .side_menu{
       width: 160px;
       padding-left : 5%;
   }
   .side_menu ul {
      padding-left: 40px;
      list-style-type:none;
   }
   .side_menu li {
       margin: 60px 0;
   }
   .side_menu a{
      text-decoration:none;
      display : block;
      margin : 10px;
      color : #C4C4C4;
      width : 100px;
      text-align : left;
      font-size : 15pt;
   }
   .side_menu a:hover:not(.current){
      color : #5FC5FF;
   }
   
   .side_menu .current a{
      color : #5FC5FF;
      font-weight : bold;
   }
   /* 콘텐츠 영역 */
   /* 보유 코인 박스 */
   .coin_area {
      height: 100px;
      margin-bottom: 10px;
      border-bottom: solid 1px #dadada;
   }
   .coin_box {
      display: flex;
      justify-content: space-between;
      padding: 10px 20px;
   }
   .btn_area {
      padding: 10px 0;
   }
   button[id$=btn] {
      width: 100px;
      height: 50px;
      margin-left: 10px;
      border: 0px;
      border-radius : 5px; 
   }
   #charge_btn {
      margin-left: 0px;
      background: #5FC5FF;
      color: white;
   }
   #coin h1{
      margin-top: 12px;
   
   }
   /* 리스트 */
   div[class$=list] {
      border: 1px solid black;
      width: 100%;
      height: 350px;
   }
   .list_header {
      background: #5FC5FF;
      color: white;
      font-weight: bold;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: space-around;
      list-style: none;
      height: 50px;
   }
   .list_header li {
      font-size: 18px;
      padding: 10px 0;
   }
   div[class$=list] p {
      color: lightgray;
      margin-top: 15%;
      text-align: center;
   }
   form[name=detailsForm] {
      margin-bottom: 10px;
   }
</style>
</head>
<body>
<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
   <div class="my_wrap">
      <nav class="side_menu">
         <ul>
            <li><a href="<%= request.getContextPath() %>/my/home">내 정보</a></li>
            <li><a href="<%= request.getContextPath() %>/my/study">내 스터디</a></li>
            <li><a href="<%= request.getContextPath() %>/my/q&a">내 Q&A</a></li>
            <li class="current"><a href="#">결제 내역</a></li>
         </ul>
      </nav>
      <div class="content">
         <div class="coin_area">
            <div class="coin_box">
               <h2>보유 코인</h2>
               <span id="coin"><h1>${ loginUser.userCoin } 코인</h1></span>
               <span class="btn_area">
                  <button id="charge_btn" onclick="openPopup('<%=request.getContextPath()%>/coin', 'coin_charge', 900, 1200);">충전</button>
                  <button id="refund_btn" onclick="openPopup('<%=request.getContextPath()%>/my/refund', 'refund', 700, 900);">환불</button>
               </span>
            </div>
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
                                <th>상품 가격</th>        
                           </tr>
                         </thead>
                         <tbody>
                            <c:forEach var="pl" items="${ PurchaseList }">
                           <tr>
                              <td><fmt:formatDate value="${ pl.purchaseDate }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/></td>
                              <td>${ pl.productName }</td>
                              <td>${ pl.productPrice }</td>
                           </tr>
                        </c:forEach>
                         </tbody>
                         
                      </c:when>
                      <c:when test="${ radioValue eq 'charge' }">
                         <thead>
                            <tr>
                              <th>충전일</th>
                              <th>충전 코인</th>
                              <th>결제 금액</th>
                           </tr>
                         </thead>
                         <tbody>
                            <c:forEach var="cl" items="${ ChargeList }">
                           <tr>
                              <td><fmt:formatDate value="${ cl.chargeDate }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/></td>
                              <td>${ cl.chargeCoin }</td>
                              <td>${ cl.chargeCoin * 110 }</td> 
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
                              <td><fmt:formatDate value="${ rl.refundDate }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/></td>
                              <td>${ rl.refundCoin }</td>
                              <td>
                              	<c:choose>
									<c:when test="${ rl.completeDate eq NULL }">
								    	환불 처리중
								    </c:when>
								    <c:otherwise>
										<fmt:formatDate value="${ rl.completeDate }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/>
								    </c:otherwise>
								</c:choose>
                              </td>
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
         <div class="my_item">
            <h2>내 아이템</h2>
            <table class="board_list">
                   <thead>
                       <tr>
                           <th>아이템명</th>
                           <th>코인</th>
                           <th>사용 시작일자</th>
                           <th>유효기간</th>
                       </tr>
                   </thead>
                   <tbody>
                  <c:forEach var="il" items="${ ItemList }">
                     <tr>
                        <td>${ il.productName }</td>
                        <td>${ il.productPrice }</td>
                        <td>${ il.startDate }</td>
                        <td>${ il.expirationDate }</td>
                     </tr>
                  </c:forEach>
               </tbody>
               </table>
         </div>
      </div>
   </div>
   
   <footer>
   <%@ include file='/WEB-INF/views/common/footer.jsp' %>
   </footer>
   <script>
   $('input[type=radio]').change(function(){
      document.forms.detailsForm.action = '${contextPath}/my/details';
      document.forms.detailsForm.submit();
   });
   
   function openPopup(url, title, width, height) {
		let left = (document.body.clientWidth/2) - (width/2);
		// 듀얼모니터를 위한 계산
		left += window.screenLeft;
		let top = (screen.availHeight/2) - (height/2);
		
		let options = "width="+width+",height="+height+",left="+left+",top="+top;
		
		window.open(url, title, options);
	};
   </script>
</body>
</html>