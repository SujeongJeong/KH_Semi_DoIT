package my.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import my.model.service.MyService;
import shop.model.vo.Refund;

/**
 * Servlet implementation class RefundServlet
 */
@WebServlet("/my/refund")
public class RefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefundServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/my/refundView.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int refund_coin = Integer.parseInt(request.getParameter("refund_coin")); // 환불 신청 코인
		String bank_account = request.getParameter("bank_account"); // 계좌번호
		String bank_name = request.getParameter("bank_name");
		String account_name = request.getParameter("account_name");
		
		int userNo = ((Member)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		if(((Member)(request.getSession().getAttribute("loginUser"))).getUserCoin() >= refund_coin) {
			Refund r = new Refund(refund_coin, bank_account, bank_name, account_name, userNo);
			int result = new MyService().insertRefundCoin(r);
			if(result > 0) {
				int refundNo = new MyService().selectRefundNo(userNo);
				int result2 = new MyService().modifyUserCoin(refundNo);
				if(result2 > 0) {
					Member m = new MemberService().selectMember(userNo);
					request.getSession().setAttribute("loginUser", m);
					request.setAttribute("result", "success");	
				}
				
			}
		} else {
			request.setAttribute("result", "fail");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/my/MyDetails.jsp").forward(request, response);
		
	}

}
