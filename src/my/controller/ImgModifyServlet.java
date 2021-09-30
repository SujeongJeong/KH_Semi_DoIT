package my.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class ImgModifyServlet
 */
@WebServlet("/my/modifyImg")
public class ImgModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int maxSize = 1024*1024*10;
		
		// 2. 웹  서버 컨테이너 경로 추출
		String root = request.getSession().getServletContext().getRealPath("/");
	
		// 3. 파일 실제 저장 경로 
		String savePath = root + "resources\\uploadFiles\\my\\";
		
		// HttpServletRequest => MultipartRequest 변경
		// MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		String[] current_img = ((Member)request.getSession().getAttribute("loginUser")).getProfileImg().split("/");
		
		// 만약 유저 이미지가 기본이미지면 삭제 안하기!
		if(current_img.length != 4) {
			File deleteFile = new File(savePath+current_img[4]);
			deleteFile.delete();			
		}
		
		String profile_img = "/resources/uploadFiles/my/" + multiRequest.getFilesystemName("modify_img");

		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		// 2. 비지니스 로직 수행		
		Member updateMember = new MemberService().modifyImg(userNo, profile_img);
		
		if(updateMember != null) {
			request.getSession().setAttribute("loginUser", updateMember);
		} else {
			// 비밀번호 수정이 실패 했음을 result fail로 표시
			request.setAttribute("msg", "프로필 이미지 변경 실패");
		}
		response.sendRedirect(request.getContextPath()+"/my/home");
	}

}
