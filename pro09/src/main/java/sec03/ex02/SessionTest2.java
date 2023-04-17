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
		// getSession()�� ȣ���Ͽ� ���� ��û �� ���� ��ü�� ���� �����ϰų� ���� ������ ��ȯ�մϴ�.
		
		out.println("���� ���̵� : " + session.getId() + "<br>");
		// ������ ���� ��ü�� id�� �����´�.
		
		out.println("���� ���� ���� �ð� : " + new Date(session.getCreationTime()) + "<br>");
		// ���� ���� ��ü ���� �ð��� �����´�.
		
		out.println("�ֱ� ���� ���� �ð� : " + new Date(session.getLastAccessedTime()) + "<br>");
		// ���� ��ü�� ���� �ֱٿ� ������ �ð��� �����´�.
		
		out.println("�⺻ ���� ��ȿ �ð� : " + session.getMaxInactiveInterval() + "<br>");
		// ��Ĺ�� �⺻ ���� ��ȿ�ð��� ����Ѵ�.
		
		session.setMaxInactiveInterval(5);
		// ������ ��ȿ �ð��� 5�ʷ� �����մϴ�.
		
		out.println("���� ��ȿ �ð� : " + session.getMaxInactiveInterval() + "<br>");
		// ���� ��ü�� ��ȿ �ð��� �����´�.
		
		if (session.isNew()) {
			out.print("�� ������ ����������ϴ�.");
		}
		// ���� ������ �������� �Ǻ��մϴ�.
		
	}

	
}
