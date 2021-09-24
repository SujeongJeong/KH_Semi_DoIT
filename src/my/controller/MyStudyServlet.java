package my.controller;

import java.io.IOException;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import my.model.service.MyService;

/**
 * Servlet implementation class MyStudyServlet
 */
@WebServlet("/my/study")
public class MyStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyStudyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav1", "my");
		
		int page = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Map<String, Object> map = new MyService().selectMyOpenStudyList(page, userNo);
		
		Map<String, Object> map2 = new MyService().selectMyJoinStudyList(userNo);
		
		// 응답 페이지 구성 시 사용할 데이터 설정
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("MyOpenStudyList", map.get("MyOpenStudyList"));
	
		request.setAttribute("MyJoinStudyList", map2.get("MyJoinStudyList"));
		
		request.getRequestDispatcher("/WEB-INF/views/my/MyStudy.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteSNo = request.getParameter("deleteSNo");
		String exitSNo = request.getParameter("exitSNo");
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		int page = 1;
		
		if(deleteSNo != null && exitSNo == null) {
			int result = new MyService().deleteOpenStudy(Integer.parseInt(deleteSNo), userNo);
			
			if(result > 0) {
				Map<String, Object> map = new MyService().selectMyOpenStudyList(page, userNo);
				request.setAttribute("msg", "개설 스터디 삭제 성공");
				request.setAttribute("pi", map.get("pi"));
				request.setAttribute("MyOpenStudyList", map.get("MyOpenStudyList"));
			} else {
				request.setAttribute("msg", "개설 스터디 삭제 실패");
			}
			
			Map<String, Object> map2 = new MyService().selectMyJoinStudyList(userNo);
			request.setAttribute("MyJoinStudyList", map2.get("MyJoinStudyList"));
			
		} else if(deleteSNo == null && exitSNo != null) {
			
			int result = new MyService().exitJoinStudy(Integer.parseInt(exitSNo), userNo);
			if(result > 0) {
				request.setAttribute("msg", "참여 스터디 삭제 성공");
				Map<String, Object> map2 = new MyService().selectMyJoinStudyList(userNo);
				
				request.setAttribute("MyJoinStudyList", map2.get("MyJoinStudyList"));
			} else {
				request.setAttribute("msg", "개설 스터디 삭제 실패");
			}
			
			Map<String, Object> map = new MyService().selectMyOpenStudyList(page, userNo);
	
			request.setAttribute("pi", map.get("pi"));
			request.setAttribute("MyOpenStudyList", map.get("MyOpenStudyList"));
		}
		
		request.getRequestDispatcher("/WEB-INF/views/my/MyStudy.jsp").forward(request, response);
	}

}
