package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import wrapper.EncryptWrapper;

/**
 * Servlet Filter implementation class EncryptFilter
 */
// 이 필터는 암호화가 필요한 서블릿에만 적용이 되기 때문에
// LoginServlet, MemberJoinServlet, PwdModifyServlet에 필터 적용이 되어야 함
@WebFilter(filterName="encrypt", servletNames= {"LoginServlet",
												"MemberJoinServlet", 
												"PwdModifyServlet",
												"rsetPwdServlet"})
public class EncryptFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptFilter() {
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
		// System.out.println("EncryptFilter 통과");

		// wrapper : 필터에게 넘어온 request, response를 변경할 때 사용
		// request나 response를 특정한 형태로 감싸서 변형
		// 매개변수로 받아온 ServletRequest를 HttpServletRequest로 다운 캐스팅하여
		// Wrapper 객체의 생성자로 전달
		EncryptWrapper encWrapper = new EncryptWrapper((HttpServletRequest)request);
		
		
		// pass the request along the filter chain
		// request를 가공한 encWrapper 객체로 변경하여 서블릿 넘김
		// 3개의 요청에 대해서 request.getParameter(key)를 수행하면
		// EncryptWrapper에 오버라이딩 된 getParameter 메소드 오버라이 딩
		chain.doFilter(encWrapper, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
