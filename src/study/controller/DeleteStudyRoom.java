package study.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.model.service.StudyService;

/**
 * Servlet implementation class DeleteStudyRoom
 */
@WebServlet("/study/deleteStudy")
public class DeleteStudyRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudyRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// checkbox의 value가 해당 방의 s_no, string으로 리턴받은 값 int배열에 담아 넘기기
		String[] deleteStudyArr = request.getParameterValues("deleteStudy");
		int[] deleteStudyArrInt = new int[deleteStudyArr.length];
		
		for(int i=0; i<deleteStudyArr.length;i++) {
			deleteStudyArrInt[i] = Integer.parseInt(deleteStudyArr[i]);
		}
		
		int result = new StudyService().deleteStudy(deleteStudyArrInt);
		
		if(result>0)
			response.sendRedirect(request.getContextPath() + "/study/home");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
