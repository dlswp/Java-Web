package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/set")
public class SetCookieValue extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Date d = new Date();
		
		Cookie c = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍입니다.", "utf-8"));
		// Cookie 객체를 생성한 후 cookieTest이름으로 한글 정보를 인코딩해서 쿠키에 저장한다.
		
		// c.setMaxAge(24 * 60 * 60);
		// 유효 기간을 설정한다.
		
		c.setMaxAge(-1);  // 유효 시간을 음수로 지정하여 Session쿠키를 만든다.
		
		response.addCookie(c);
		// 생성된 쿠키를 브라우저로 전송한다.
		
		out.println("현재시간 : " + d);
		out.println("<br> 문자열을 Cookie에 저장합니다.");
	}

	
}
