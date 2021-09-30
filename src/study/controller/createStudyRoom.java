package study.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.Attachment;
import common.MyFileRenamePolicy;
import member.model.vo.Member;
import shop.model.vo.Purchase;
import study.model.service.StudyService;
import study.model.vo.MemberJoinStudy;
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
		int userNo = ((Member)(request.getSession().getAttribute("loginUser"))).getUserNo();
	
		// 스터디방 개설 기간 설정
		Purchase prLimit = new StudyService().purchaseLimit(userNo);
		
//		System.out.println("prLimit1 : " + prLimit);
		
		
		if(prLimit.gets_limit_date() == 0) {
			prLimit.sets_limit_date(4);
		}
		if(prLimit.getS_to_limit() == 0) {
			prLimit.setS_to_limit(5);
		}
		
		request.setAttribute("prLimit", prLimit);
		
//		System.out.println("prLimit2 : " + prLimit);
		
		
		request.setAttribute("nav1", "study");
		
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/study/CreateStudy.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파일 첨부 
		int maxSize = 1024*1024*10;	// 파일 10mbyte 제한
		String root = request.getSession().getServletContext().getRealPath("/");	// 웹서버 컨테이너 경로
		String savePath = root + "resources\\uploadFiles\\study";		// 저장될 경로
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
//		System.out.println(savePath);
		
		// DB의 Study,Attachment 데이터 저장
		// Attachment 테이블에 값 삽입
		List<Attachment> photo = new ArrayList<>();
		String fileName = "s_image"; 
		
		if (multiRequest.getFilesystemName(fileName) != null) {
			Attachment at = new Attachment();

			at.setFile_path("/resources/uploadFiles/study/"); // 경로 저장
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
		int cid = Integer.parseInt(multiRequest.getParameter("cid"));
		String s_explain = multiRequest.getParameter("s_explain");
		String s_notice = multiRequest.getParameter("s_notice");
		int user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();		

		//날짜시간
		String ssp = multiRequest.getParameter("s_startPeriod");
		String sep = multiRequest.getParameter("s_endPeriod");
		String sst = multiRequest.getParameter("s_startTime");
		String set = multiRequest.getParameter("s_endTime");
		
//		System.out.println("sep3 : " + sep3);
//		System.out.println("dateString : "+ ssp);
//		System.out.println("dateString : "+ sep);
//		System.out.println("dateString : "+ sst);
//		System.out.println("dateString : "+ set);
		
		Date s_startPeriod = null;
		Date s_endPeriod = null;
		Date s_startTime = null;
		Date s_endTime = null;
		
		try {
				
				s_startPeriod = new SimpleDateFormat("yyyy-MM-dd").parse(ssp);
//				System.out.println("s_startPeriod : " + s_startPeriod);
				s_endPeriod = new SimpleDateFormat("yyyy-MM-dd").parse(sep);
//				System.out.println("s_endPeriod : " + s_endPeriod);
				s_startTime = new SimpleDateFormat("HH:mm").parse(sst);
				s_endTime = new SimpleDateFormat("HH:mm").parse(set);
				  
//				System.out.println("s_startPeriod : "+ s_startPeriod);
//				System.out.println("s_endPeriod : "+ s_endPeriod);
//				System.out.println("s_startTime : "+ s_startTime);
//				System.out.println("s_endTime : "+ s_endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int result = 0;
			
			Study s = new Study(s_name, s_to, s_day, s_startPeriod, s_endPeriod, s_startTime, s_endTime, s_explain,
					s_notice, user_no, cid, photo);
//			System.out.println(s);
			result = new StudyService().insertStudyRoom(s);
			
			
			
			// 스터디방 생성 성공시, 방장도 정원수에 포함되므로 db에 저장하기 위해 회원이 방금 만든 스터디방의 s_no 가져오는 처리
			Study s2 = new StudyService().selectStudyRoomOnlySNo(user_no);
			int s_no = s2.getS_no();
				
			 
			
			
			
		if (result > 0) {
			//스터디방 생성 성공시, 방장도 정원수에 포함되므로 db에 저장처리
			MemberJoinStudy mjs = new MemberJoinStudy(user_no,s_no);
			int result2 = new StudyService().insertMemberJoinStudy(mjs);
			
			if(result2 > 0) {
				response.sendRedirect(request.getContextPath() + "/study/home");
			} else {
//				request.setAttribute("msg", "스터디방 생성을 실패하였습니다.");
//				request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			}
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
