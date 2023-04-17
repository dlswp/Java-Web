package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/json3")
public class JsonServlet3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		JSONObject totaObject = new JSONObject();
		// 배열을 최종적으로 저장할 JSONObject 객체를 생성한다.
		
		JSONArray membersArray = new JSONArray();
		// memberInfo JSON 객체를 저장할 membersArray 배열을 선언한다.
		
		JSONObject memberInfo = new JSONObject();
		// 회원 한 명의 정보가 들어갈 memberInfo JSON객체를 선언한다.
		
		memberInfo.put("name", "박지성");
		memberInfo.put("age", "25");
		memberInfo.put("gender", "남자");
		memberInfo.put("nickname", "날센돌이");
        // 회원 정보를 name/value 쌍으로 저장한다.
		
		membersArray.add(memberInfo);
		// 회원 정보를 다시 membersArray배열에 저장한다.
		
		memberInfo = new JSONObject();
		memberInfo.put("name", "김연아");
		memberInfo.put("age", "21");
		memberInfo.put("gender", "여자");
		memberInfo.put("nickname", "칼치");
		membersArray.add(memberInfo);
		// 다른 회원 정보를 name/value 쌍으로 저장한 후 membersArray에 다시 저장한다.
		
		totaObject.put("members", membersArray);
		// totalObject에 members라는 name으로 membersArray를 value로 저장한다.

		JSONArray bookArray = new JSONArray();
		// JSONArray객체를 생성한다.
		JSONObject bookInfo = new JSONObject();
		
		bookInfo.put("title", "초보자를 위한 자바 프로그래밍");
		bookInfo.put("writer", "이병승");
		bookInfo.put("price", "30000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pro16/image/image1.jpg");
		// JSONObject객체를 생성한 후 책 정보를 저장한다.
		
		bookArray.add(bookInfo);
		// bookArray에 객체를 저장한다.

		bookInfo = new JSONObject();
		bookInfo.put("title", "모두의 파이썬");
		bookInfo.put("writer", "이승찬");
		bookInfo.put("price", "12000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pro16/image/image2.jpg");
		// JSONObject객체를 생성한 후 책 정보를 저장한다.
		
		bookArray.add(bookInfo);
		// bookArray에 객체를 저장한다.
		
		totaObject.put("books", bookArray);
		// 도서 정보를 저장한 배열을 배열 이름 books로 totalObject에 저장한다.
		
		String jsonInfo = totaObject.toJSONString();
		// JSONObject를 문자열로 변환한다.
		
		System.out.print(jsonInfo);
		writer.print(jsonInfo);
		// JSON 데이터를 브라우저로 전송한다.
	}

	
	
}
