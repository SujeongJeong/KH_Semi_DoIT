package my.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class NickModifyServlet
 */
@WebServlet("/my/modifyNick")
public class NickModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NickModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("loginUser") == null) {
			request.setAttribute("msg", "올바르지 않은 요청입니다.");
			String view = "WEB-INF/views/common/errorpage.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		String nickName = request.getParameter("nickName");
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		// 2. 비지니스 로직 수행
		Member updateMember = new MemberService().updateNickName(userNo, nickName);
		
		if(updateMember != null) {
			// 비밀번호 수정이 잘 되었음을 result success로 표시
			request.setAttribute("result", "success");
			// 수정 된 객체 다시 loginUser에 저장
			request.getSession().setAttribute("loginUser", updateMember);
		} else {
			// 비밀번호 수정이 실패 했음을 result fail로 표시
			request.setAttribute("result", "fail");
		}
		response.sendRedirect(request.getContextPath()+"/my/home");
	}
	

}
