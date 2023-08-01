package com.spring.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/mem4.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		
		String action = request.getParameter("action");
		String nextPage = "";
		
		if(action == null || action.equals("listMembers")) {
			List<MemberVO> membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
		}else if(action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "test03/memberInfo.jsp";
			/* 검색 조건이 selectMemberById이면 전송된 값을 getParameter()로 가져온  후 SQL문의 조건식에서 id의 조건값으로 전달한다. */
			
		}else if(action.equals("selectMemberByPwd")) {
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
			/* 검색 조건이 selectMemberByPwd이면 전송된 값을 getParameter()로 가져온후 SQL문의 조건식 pwd의 조건값으로 전달한다. */
		}else if(action.equals("insertMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			dao.insertMember(memberVO);
			// 회원 가입창에서 전송된 회원 정보를 MemberVO에 설정한 후 insertMember() 메서드로 전달한다.
			
			nextPage = "/mem4.do?action=listMembers";
		}else if(action.equals("insertMember2")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			Map memberMap = new HashMap();
			memberMap.put("id", id);
			memberMap.put("pwd", pwd);
			memberMap.put("name", name);
			memberMap.put("email", email);
			dao.insertMember2(memberMap);
			nextPage = "/mem4.do?action=listMembers";
		}else if(action.equals("updateMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			dao.updateMember(memberVO);
			nextPage="/mem4.do?action=listMembers";
		}else if(action.equals("deleteMember")) {
			String id = request.getParameter("id");
			dao.deleteMember(id);
			nextPage="/mem4.do?action=listMembers";
		}else if(action.equals("searchMember")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			// 검색창에 입력한 검색 조건을 가져온다
			
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			List membersList = dao.searchMember(memberVO);
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
		}else if(action.equals("foreachSelect")) {
			  List<String> nameList = new ArrayList<String>();
			  nameList.add("홍길동");
			  nameList.add("차범근");
			  nameList.add("이순신");
			  List<MemberVO> membersList=dao.foreachSelect(nameList);
			  // ArrayList에 검색할 이름을 저장한 후 SQL문으로 ArrayList를 전달한다.
			  
			  request.setAttribute("membersList",membersList);
			  nextPage="test03/listMembers.jsp";
		}else if(action.equals("foreachInsert")) {
	          List<MemberVO> memList = new ArrayList<MemberVO>();
	          memList.add(new MemberVO("m1", "1234", "박길동", "m1@test.com"));
	          memList.add(new MemberVO("m2", "1234", "이길동", "m2@test.com"));
	          memList.add(new MemberVO("m3", "1234", "김길동", "m3@test.com"));
	          // 테이블에 추가할 회원 정보를 memList에 저장한다.
	          
	          int result=dao.foreachInsert(memList);
	          // SQL문으로 memList에 전달한다.
	          
	          nextPage="/mem4.do?action=listMembers";
		    }else if(action.equals("selectLike")) {
		      String name="길동";
			  List<MemberVO> membersList=dao.selectLike(name);
			  request.setAttribute("membersList",membersList);
			  nextPage="test03/listMembers.jsp";
		   }
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
		
	}

}
