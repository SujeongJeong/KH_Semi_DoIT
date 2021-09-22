package qna.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.BoardService;
import qna.model.vo.Search;
import qna.model.service.NoticeService;
import qna.model.vo.Notice;

/**
 * Servlet implementation class QnaHomeServlet
 */
@WebServlet("/qna/home")
public class QnaHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메뉴바 클릭했을 때 페이지로 이동
		// RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/qna/home.jsp");
		request.setAttribute("nav1", "qna");
		// view.forward(request, response);
		
		
		List<Notice> noticeList = new NoticeService().selectList();
		
		
		// System.out.println("공지사항 목록 : " + noticeList);
		request.setAttribute("noticeList", noticeList);
		
		int page = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 검색 조건과 검색 값 추가하기

		String searchValue = request.getParameter("searchValue");
		
		System.out.println("seachvalue : " + searchValue);
		
		
//		조회를 할때 리턴받아야 할 값이 곱에 대한 리스트값, 페이지에 대해서 페이징바에서
//		몇번부터 몇번까지 있어야 할지에 대한 값 여러개의 값을 가져와야한다.  map안에다가 담아서 가져오려한다.
		// 요청 페이지 값을 매개변수로 넘기고 조회 된 게시글 리스트 + 페이징 처리에 대한 값 Map 타입에 담아 리턴
		Map<String, Object> map = new BoardService().selectList(page, new Search(searchValue));
		
		// 응답 페이지 구성 시 사용할 데이터 설정
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("boardList", map.get("boardList"));
		System.out.println(map.get("boardList"));
		
		request.getRequestDispatcher("/WEB-INF/views/qna/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
