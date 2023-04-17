package sec03.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sess2")
public class SessionTest2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		// getSession()을 호출하여 최초 요청 시 세션 객체를 새로 생성하거나 기존 세션을 반환합니다.
		
		out.println("세션 아이디 : " + session.getId() + "<br>");
		// 생성된 세션 객체의 id를 가져온다.
		
		out.println("최초 세션 생성 시각 : " + new Date(session.getCreationTime()) + "<br>");
		// 최초 세션 객체 생성 시간을 가져온다.
		
		out.println("최근 세션 접근 시각 : " + new Date(session.getLastAccessedTime()) + "<br>");
		// 세션 객체에 가장 최근에 접근한 시간을 가져온다.
		
		out.println("기본 세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>");
		// 톰캣의 기본 세션 유효시간을 출력한다.
		
		session.setMaxInactiveInterval(5);
		// 세션의 유효 시간을 5초로 설정합니다.
		
		out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>");
		// 세션 객체의 유효 시간을 가져온다.
		
		if (session.isNew()) {
			out.print("새 세션이 만들어졌습니다.");
		}
		// 최초 생성된 세션인지 판별합니다.
		
	}

	
}
