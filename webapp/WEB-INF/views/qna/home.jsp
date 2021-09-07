<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/qna-main.css?afters' rel='stylesheet'>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
	<div class="content">
		<div id="board_list_wrap">
         <div class="board-header">
            <div class="del_btn">
                <button type="button">삭제</button>
            </div>
            <div class="search">
                <button><img src="../resources/images/search_btn.png"></button><input type="text">
            </div>
            <div class="newpost_btn">
                    <button type="button" onClick="location.href='<%= request.getContextPath() %>/qna/insert'">새 글 쓰기</button>
            </div>
        </div>
            <table class="board_list">
                <caption>게시판 목록</caption>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>구분</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="manager">
                        <td>
                            <input type="checkbox">
                        </td>
                        <td></td>
                        <td class="tit">
                            <a href="#">Q_A 게시판 사용시 주의사항!! [12]</a>
                        </td>
                        <td>관리자</td>
                        <td>2021-08-29</td>
                        <td>211</td>
                    </tr>
                    <tr class="manager">
                        <td>
                            <input type="checkbox">
                        </td>
                        <td></td>
                        <td class="tit">
                            <a href="#">[태그 잊지말고 달기] Q&A 좋은 답글 달리는 꿀팁! [24]</a>
                        </td>
                        <td>관리자</td>
                        <td>2021-08-29</td>
                        <td>321</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">18</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-29</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">17</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-29</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">16</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-29</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">15</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">14</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-27</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">13</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-27</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">12</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-27</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">11</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-26</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">10</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-26</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">9</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">8</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">7</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">6</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">5</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">4</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">3</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">2</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox">1</td>
                        <td>Language</td>
                        <td class="tit">
                            <a href="#">web server와 was의 차이점이 뭔가요?</a>
                        </td>
                        <td>닉네임</td>
                        <td>2021-08-28</td>
                        <td>111</td>
                    </tr>
                    
                </tbody>
            </table>
            <div class="paging">
                <a href="#" class="bt">&lt;&lt;</a>
                <a href="#" class="bt">&lt;</a>
                <a href="#" class="num on">1</a>
                <a href="#" class="num">2</a>
                <a href="#" class="num">3</a>
                <a href="#" class="num">4</a>
                <a href="#" class="num">5</a>
                <a href="#" class="num">6</a>
                <a href="#" class="num">7</a>
                <a href="#" class="num">8</a>
                <a href="#" class="num">9</a>
                <a href="#" class="num">10</a>
                <a href="#" class="bt">&gt;</a>
                <a href="#" class="bt">&gt;&gt;</a>
            </div>
        </div>

	</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>

</body>
</html>