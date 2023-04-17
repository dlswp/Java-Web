package sec03.ex01;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncoderFilter
 */

//@WebFilter("/*")
// WebFilter �ֳ����̼��� �̿��� ��� ��û�� ���͸� ��ġ�� �Ѵ�.
public class EncoderFilter implements Filter {
// ����� ���� ���ʹ� �ݵ�� Filter�������̽��� �����ؾ� �Ѵ�.
    
	ServletContext context;
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("utf-8 ���ڵ� .......");
		context = fConfig.getServletContext();
	}
	

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	// doFilter() �ȿ��� ���� ���� ����� �����Ѵ�.
		
		System.out.println("doFilter ȣ��");
		request.setCharacterEncoding("utf-8");
		// �ѱ� ���ڵ� ���� �۾��� �Ѵ�. 
		
		String context = ((HttpServletRequest) request).getContextPath();
		// .getContextPath()�� �� ���ø����̼��� ���ؽ�Ʈ �̸��� �����´�.
		
		String pathinfo = ((HttpServletRequest) request).getRequestURI();
		// .getRequestURI()�� �� ���������� ��û�� ��û URI�� �����´�.
		
		String realPath = request.getRealPath(pathinfo);
		// .getRealPath()�� ��û URI�� ���� ��θ� �����´�.
		
		String mesg = " Context  ����:" + context + "\n URI ���� : " + pathinfo + "\n ������ ���:  " + realPath;
		System.out.println(mesg);

		long begin = System.currentTimeMillis();
		// ��û ���Ϳ��� ��û ó�� ���� �ð��� ���մϴ�.
		
		chain.doFilter(request, response);
		// ���� ���ͷ� �ѱ�� �۾��� �����Ѵ�.
		
		long end = System.currentTimeMillis();
		// ���� ���Ϳ��� ��û ó�� ���� �ð��� ���մϴ�.
		
		System.out.println("�۾� �ð�:" + (end - begin) + "ms");
		// �۾� ��û ���� ���� �ð����� ���� �۾� ���� �ð��� ���մϴ�.
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("destroy ȣ��");
	}

	public EncoderFilter() {
		
	}
	
	
}
