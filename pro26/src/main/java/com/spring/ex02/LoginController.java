package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController")
public class LoginController {
	@RequestMapping(value = { "/test/loginForm.do", "/test/loginForm2.do" }, method = { RequestMethod.GET })
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	@RequestMapping(value = "/test/login.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("result");
		
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);

		return mav;
	}
	
	
	/*
	@RequestMapping(value = "/test/login2.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login2(@RequestParam("userID") String userID, @RequestParam("userName") String userName,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// @RequestParam을 이용해 매개변수가 userID이면 그 값을 변수 userID에 자동으로 설정한다
		// @RequestParam을 이용해 매개변수가 userName이면 그 값을 변수 userName에 자동으로 설정한다.
		
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");

		
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		
		
		System.out.println("userID: "+userID);
		System.out.println("userName: "+userName);
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		
		return mav;
	}
	*/
	
	
	@RequestMapping(value = "/test/login2.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login2(@RequestParam("userID") String userID, 
			@RequestParam(value = "userName", required = true) String userName, 
			@RequestParam(value = "email", required = false) String email,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// @RequestParam을 이용해 매개변수가 userID이면 그 값을 변수 userID에 자동으로 설정한다
		// @RequestParam을 이용해 매개변수가 userName이면 그 값을 변수 userName에 자동으로 설정한다.
		
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");

		
		System.out.println("userID: "+userID);
		System.out.println("userName: "+userName);
		System.out.println("email: "+email);
		
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/test/login3.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login3(@RequestParam Map<String, String> info,
			                   HttpServletRequest request, HttpServletResponse response) throws Exception {
		// @RequestPram을 이용해 Map에 전송된 매개변수 이름을 key, 값을 value로 저장한다.
		
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		
		String userID = info.get("userID");
		String userName = info.get("userName");
		// Map에 저장된 매개변수의 이름으로 전달된 값을 가져온다.
		
		System.out.println("userID: "+userID);
		System.out.println("userName: "+userName);
		
		mav.addObject("info", info);
		// @RequestParam에서 설정한 Map 이름으로 바인딩한다.
		
		mav.setViewName("result");
		
		return mav;
	}

	@RequestMapping(value = "/test/login4.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO,
			                   HttpServletRequest request, HttpServletResponse response) throws Exception {
		// @ModelAttribute를 이용해 전달되는 매개변수 값을 LoginVO 클래스와 이름이 같은 속성에 자동으로 설정한다.
		// addObject() 를 이용할 필요 없이 info를 이용해 바로 JSP에서 LoginVO 속성에 접근할 수 있다.
		
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		
		
		System.out.println("userID: " + loginVO.getUserID());
		System.out.println("userName: " + loginVO.getUserName());
		
		mav.setViewName("result");
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "/test/login5.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login5(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Model model은 메서드 호출 시 Model클래스 객체를 생성한다.
		
		request.setCharacterEncoding("utf-8");
		
		model.addAttribute("userID", "hong");
		model.addAttribute("userName", "홍길동");
		// JSP에 전달할 데이터를 model에 addAttribute() 메서드를 이용해서 바인딩한다.
		
		return "result";
	}
	
}
