package filter;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/*")
public class LoginCheckFilter implements Filter {
	//비로그인 상태에서 가능한 요청 리스트
	private List<String> permitList;
	// css,js,image와 같은 리소스 리스트
	private List<String> resourceList;
    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest hreq = (HttpServletRequest)request;
		String uri = hreq.getRequestURI();
		// System.out.println(uri);

		
		// 로그인 하지 않은 상태에서 허용 되는 요청이 아니라면
		if(!permitList.contains(uri)) {
			// css,js,image와 같이 /resources/ 하위 요청인지 확인
			boolean isResourceFile = false;
			for(String str : resourceList) {
				if(uri.contains(str)) {
					isResourceFile = true;
					break;
				}
			}
			// 리소스 파일도 아니라면 로그인 하지 않은 상태에서 허가 되지 않은 요청을 한 상황이므로
			if(!isResourceFile) {
				Member loginUser = (Member)hreq.getSession().getAttribute("loginUser");
				// 로그인 하지 않은 상태에서 허가되지 않은 요청을 한 상황이므로
				if(loginUser == null) {
					hreq.setAttribute("msg", "올바르지 않은 요청입니다.");
					hreq.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
					return; // -> 아래의 doFilter 요청으로 서블릿으로 넘어가지 않도록
				}
			}
		}
		// 필터 적용 확인을 위해 /memberModify 요청의 동일 기능 주석 후 결과 확인 기능
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 인스턴스 초기화 시 permitList, resourceList 값 설정하기
		permitList = new ArrayList<>();
		permitList.add("/Do_IT/");
		permitList.add("/Do_IT/login");
		permitList.add("/Do_IT/idCheck");
		permitList.add("/Do_IT/nickCheck");
		permitList.add("/Do_IT/memberJoin");
		permitList.add("/Do_IT/shop/home");
		permitList.add("/Do_IT/productAdd");
		
		//스터디
		permitList.add("/Do_IT/study/home"); // 스터디홈
		permitList.add("/Do_IT/study/studyInfo"); // 스터디방 클릭시 나오는 스터디방 정보
		// 나중에 로그인하면 접근가능으로 바꿀거
		permitList.add("/Do_IT/study/createStudy"); // 스터디방 만들기 -> 추후 변경 예정
		permitList.add("/Do_IT/study/enterStudy"); // 스터디방 내부 -> 추후 변경 예정
		
		resourceList = new ArrayList<>();
		resourceList.add("/resources/");
	}

}
