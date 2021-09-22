package member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.mail.MailSend;
import member.model.service.MemberService;

/**
 * Servlet implementation class findPwdServlet
 */
@WebServlet("/findPwd")
public class findPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view= request.getRequestDispatcher("WEB-INF/views/member/form/findPwdForm.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEmail = request.getParameter("userEmail");
		String msg = null;
		
		int result = new MemberService().emailCheck(userEmail);
		int result1 = 0; // db에 임시 비밀번호로 변경
		
		String tempPwd = "";
		for (int i = 0; i < 10; i++) {
			tempPwd += (char) ((Math.random() * 26) + 97); // 임시 비밀번호
		}
		
		System.out.println("임시 비밀번호 : " + tempPwd);
		
	
		String encPwd = getSha512(tempPwd); 
		
		if(result > 0) {
			result1 = new MemberService().rsetPwd(userEmail, encPwd); // 임시 비밀번호 암호화하여 db 변경
			
			MailSend.gmailSend(userEmail, tempPwd);
			
			if(result1 > 0) {
				msg = "입력하신 이메일로 임시 비밀번호를 전송했습니다. 로그인 후 비밀번호를 변경해주세요.";
			} else {
				msg = "임시 비밀번호 전송 실패."; 
			}
		} else {
			msg = "가입 정보가 없는 이메일입니다.";
		}
		
		request.setAttribute("userEmail", userEmail);
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/WEB-INF/views/member/form/findPwdForm.jsp").forward(request, response);
	}

	private static String getSha512(String tempPwd) {
        String encPwd = "";
       
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = tempPwd.getBytes(Charset.forName("UTF-8"));
            md.update(bytes);
           
            encPwd = Base64.getEncoder().encodeToString(md.digest());
           
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
       
       
        return encPwd;
    }

}
