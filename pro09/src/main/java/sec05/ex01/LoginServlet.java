package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		// �α���â���� ���۵� ID�� ��й�ȣ�������´�.
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pwd);
		// MemberVO ��ü�� �����ϰ� �Ӽ��� ID�� ��й�ȣ�� �����Ѵ�.
		
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberVO);
		// MemberDAO�� isExisted() �޼��带 ȣ���ϸ鼭 memberVO�� �����Ѵ�.

		if (result) {
			HttpSession session = request.getSession();
			
			session.setAttribute("isLogon", true);
			// ��ȸ�� ����� true�̸� isLogOn �Ӽ��� true�� ���ǿ� �����Ѵ�.
			
			session.setAttribute("login.id", user_id);
			session.setAttribute("login.pwd", user_pwd);
			// ��ȸ�� ����� true�̸� ID�� ��й�ȣ�� ���ǿ� �����Ѵ�.

			out.print("<html><body>");
			out.print("�ȳ��ϼ��� " + user_id + "��!!!<br>");
			out.print("<a href='show'>ȸ����������</a>");
			out.print("</body></html>");
		} else {
			out.print("<html><body>ȸ�� ���̵� Ʋ���ϴ�.");
			out.print("<a href='login3.html'> �ٽ� �α����ϱ�</a>");
			out.print("</body></html>");
		}
	}

	
}
