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
		// ServletContext ��ü�� �����´� 
		// => ServletContext�� ���ε��� �����ʹ� ��� ����ڰ� ������ �� �ִ�.
		// => ��� ����ڰ� �������� ����ϴ� �����ʹ� ServletContext�� ���ε� �س��� ����ϸ� ���ϴ�
		
		List member = (ArrayList)context.getAttribute("member");
		// member�� ������ ���ε��� ȸ�������� �����´�.
		
		String name = (String)member.get(0);
		int age = (Integer)member.get(1);
		
		out.println("<html><body>");
		out.println(name + "<br>");
		out.println(age + "<br>");
		out.println("</body></html>");
	}

	
}
