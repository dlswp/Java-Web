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

@WebServlet("/json2")
public class JsonServlet2 extends HttpServlet {

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
		
		JSONObject totalObject = new JSONObject();
		// 배열을 저장할 totalObject를 선언한다.
		
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
		
		totalObject.put("members", membersArray);
		// totalObject에 members라는 name으로 membersArray를 value로 저장한다.

		String jsonInfo = totalObject.toJSONString();
		// JSONObject를 문자열로 변환한다.
		
		System.out.print(jsonInfo);
		writer.print(jsonInfo);
		// JSON 데이터를 브라우저로 전송한다.
	}

	
	
}
