package my.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import my.model.service.MyService;

/**
 * Servlet implementation class MyQnAServlet
 */
@WebServlet("/my/q&a")
public class MyQnAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyQnAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/my/MyQ&A.jsp");
		request.setAttribute("nav1", "my");
		
		int page = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		// 나의 게시글 리스트
		Map<String, Object> map = new MyService().selectMyBoardList(page, userNo);
		
		// 내가 댓글을 쓴 게시글 리스트
		Map<String, Object> map2 = new MyService().selectMyReplyList(page, userNo);
		
		// 응답 페이지 구성 시 사용할 데이터 설정
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("MyboardList", map.get("MyboardList"));
		
		request.setAttribute("pi2", map2.get("pi2"));
		request.setAttribute("MyReplyList", map2.get("MyReplyList"));
		
		request.getRequestDispatcher("/WEB-INF/views/my/MyQ&A.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
