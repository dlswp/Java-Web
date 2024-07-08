package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/*")
public class TestController {
	
	static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello REST!!";
	} //  /hello로 요청 시 브라우저로 문자열을 전송한다.
	
	@RequestMapping("/member")
	public MemberVO member() {
		MemberVO vo = new MemberVO();
		vo.setId("hong");
		vo.setPwd("1234");
		vo.setName("홍길동");
		vo.setEmail("hong@test.com");
		return vo;
		// MemberVO 객체의 속성 값을 저장한 후 JSON으로 전송한다.
		
	}
	
	@RequestMapping("/membersList")
	public List<MemberVO> listMembers(){
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		// MemberVO 객체를 저장할 ArrayList 객체를 생성한다.
		
		for(int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("123"+i);
			vo.setName("홍길동"+i);
			vo.setEmail("hong"+i+"@test.com");
			list.add(vo);
		} // MemberVO 객체를 10개 생성해 ArrayList에 저장한다.
		
		return list;
		// ArrayList를 JSON으로 브라우저에 전송한다.
	}
	
	@RequestMapping("/membersMap")
	public Map<Integer, MemberVO> membersMap(){
		
		Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();
		// MemberVO 객체를 저장할 HashMap 객체를 생성한다.
		
		for(int i=0; i< 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("123"+i);
			vo.setName("홍길동"+i);
			vo.setEmail("hong"+i+"@test.com");
			
			map.put(i, vo);
		} // MemberVO 객체를 HashMap에 저장한다.
		
		return map;
		// HashMap 객체를 브라우저로 전송한다.
	}
	
	// 브라우저에서 요청 시 {num} 부분의 값이 @PathVariable로 지정된다.
	@RequestMapping(value = "/notice/{num}" , method = RequestMethod.GET)
	public int notice(@PathVariable("num") int num) throws Exception{
		// 요청 URL에서 지정된 값이 num에 저장으로 활당된다.
		
		return num;
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public void modify(@RequestBody MemberVO vo) {
		// JSON으로 전송된 데이터를 MemberVO 객체의 속성에 자동으로 설정해 준다.
		
		logger.info(vo.toString());
	}
	
	
	@RequestMapping("/membersList2")
	public ResponseEntity<List<MemberVO>> listMembers2(){
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		for(int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			
			vo.setId("lee" + i);
			vo.setPwd("123" + i);
			vo.setName("이순신" + i);
			vo.setEmail("lee" + i + "@test.com");
			list.add(vo);
		}
		
		return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
		// 오류 코드 500으로 응답한다.
	}
	
	@RequestMapping(value = "/res3")
	public ResponseEntity res3() {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		// 전송할 데이터의 종류와 인코딩을 설정한다.
		
		String message = "<script>";
		message += " alert('새 회원을 등록합니다.');";
		message += " location.href='/pro29/test/membersList2'; ";
		message += " </script>";
		// 전송할 자바스크립트 코드를 문자열로 작성합니다.
		
		return new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		// ResponseEntity를 이용해 HTML 형식으로 전송한다.
	}
	
}
