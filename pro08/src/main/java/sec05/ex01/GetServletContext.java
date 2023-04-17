package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cget")
public class GetServletContext extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ServletContext context = getServletContext();
		// ServletContext 객체를 가져온다 
		// => ServletContext에 바인딩된 데이터는 모든 사용자가 접근할 수 있다.
		// => 모든 사용자가 공통으로 사용하는 데이터는 ServletContext에 바인딩 해놓고 사용하면 편리하다
		
		List member = (ArrayList)context.getAttribute("member");
		// member로 이전에 바인딩된 회원정보를 가져온다.
		
		String name = (String)member.get(0);
		int age = (Integer)member.get(1);
		
		out.println("<html><body>");
		out.println(name + "<br>");
		out.println(age + "<br>");
		out.println("</body></html>");
	}

	
}
