package study.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.Attachment;
import common.MyFileRenamePolicy;
import member.model.vo.Member;
import study.model.service.StudyService;
import study.model.vo.Study;

/**
 * Servlet implementation class createStudyRoom
 */
@WebServlet("/study/createStudy")
public class createStudyRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createStudyRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/study/CreateStudy.jsp");
		request.setAttribute("nav1", "study");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파일 첨부 
		int maxSize = 1024*1024*10;	// 파일 10mbyte 제한
		String fileRoot = "";
		String root = request.getSession().getServletContext().getRealPath("/");	// 웹서버 컨테이너 경로
		String test = this.getClass().getResource("/").toString();
		System.out.println("test: " + test);
		System.out.println(root);
		String savePath = root + "resources\\uploadFiles\\";		// 저장될 경로
		System.out.println(savePath);
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		// DB의 Study,Attachment 데이터 저장
		// Attachment 테이블에 값 삽입
		List<Attachment> photo = new ArrayList<>();
		String fileName = "s_image"; 
		
		if (multiRequest.getFilesystemName(fileName) != null) {
			Attachment at = new Attachment();

			at.setFile_path("/resources/uploadFiles/"); // 경로 저장
			at.setOrigin_name(multiRequest.getOriginalFileName(fileName)); // 원본이름 저장
			at.setChange_name(multiRequest.getFilesystemName(fileName)); // 바뀐이름 저장

			photo.add(at);
		}
		
		// 파일 외의 Study 테이블에 삽입할 값들
		String s_name = multiRequest.getParameter("s_name");
		int s_to = Integer.parseInt(multiRequest.getParameter("s_to"));
		String[] s_dayArr = multiRequest.getParameterValues("s_day");
		String s_day="";
		if(!s_dayArr[0].equals(""))
			s_day = String.join(",", s_dayArr);


//		String ssp = multiRequest.getParameter("s_startPeriod");
//		System.out.println("dateString : "+ ssp);
//		try {
//			Date s_startPeriod = new SimpleDateFormat("yyyy/mm/dd").parse(ssp);
//			System.out.println("date : "+ s_startPeriod);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		
//		String s_endPeriod = multiRequest.getParameter("s_endPeriod");
//		String s_startTime = multiRequest.getParameter("s_startTime");
//		String s_endTime = multiRequest.getParameter("s_endTime");
		
		int cid = Integer.parseInt(multiRequest.getParameter("cid"));
		String s_explain = multiRequest.getParameter("s_explain");
		String s_notice = multiRequest.getParameter("s_notice");
		int user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();		
		
//		Study s = new Study(s_name,s_to,s_day,s_startPeriod,s_endPeriod,s_startTime,s_endTime,
//							s_explain,s_notice,user_no,cid,photo);
		Study s = new Study(s_name,s_to,s_day,s_explain,s_notice,user_no,cid,photo);
		
//		System.out.println(s); 		//양호
		
		int result = new StudyService().insertStudyRoom(s);
		
		if(result>0) {
			// 스터디방 생성 후 목록으로 재요청
			response.sendRedirect(request.getContextPath() + "/study/home");
		} else {
			// 로직 실패시 저장된 파일 삭제
			// 서버에 저장된 이름으로 파일 가져와서 삭제
			File failedFile = new File(savePath + photo.get(0).getChange_name());
			failedFile.delete();
			
//			request.setAttribute("msg", "스터디방 생성을 실패하였습니다.");
//			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			
		}
		
	}

}






