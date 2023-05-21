package com.spring.ex03;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	private static SqlSessionFactory sqlMapper = null;
	public static SqlSessionFactory getInstance() {
		if(sqlMapper == null) {
			try {
				String resource = "mybatis/SqlMapConfig.xml";
				// MemberDAO의 각 메서드 호출 시 src/mybatis/SqlMapConfig.xml에서 설정 정보를 읽은 후 데이터베이스와의 연동 준비를 합니다.
				
				Reader reader = Resources.getResourceAsReader(resource);
				
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				// 마이바티스를 이용하는 sqlMapper 객체를 가져온다.
				
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	
	public List<MemberVO> selectAllMemberList() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> membersList = null;
		
		membersList = session.selectList("mapper.member.selectAllMemberList");
		// 모든 회원 정보를 조회한다.
		return membersList;
	}
	
	public String selectName() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		String name = session.selectOne("mapper.member.selectName");
		// selectOne() 메서드로 인자로 지정한 SQL문을 실행한 후 한 개의 데이터(문자열)를 반환한다.
		
		return name;
	}
	
	public int selectPwd() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		int pwd = session.selectOne("mapper.member.selectPwd");
		// selectOne() 메서드로 지정한 SQL문을 실행한 후 한 개의 데이터(정수)를 반환한다.
		
		return pwd;
	}
	
	public MemberVO selectMemberById(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		MemberVO memberVO = session.selectOne("mapper.member.selectMemberById", id);
		/* selectOne은 레코드 한 개만 조회할 때 사용한다. */
		/* id는 서블릿에서 넘어온 id의 값을 selectOne() 메서드 호출 시 해당 SQL문의 조건값으로 전달한다. */
		return memberVO;
	}
	
	public List<MemberVO> selectMemberByPwd(int pwd){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> membersList = null;
		membersList = session.selectList("mapper.member.selectMemberByPwd",pwd);
		/* selectList는 비밀번호가 같은 회원은 여러 명이 있을 수 있으므로 selectList() 메서드로 조회한다. */
		/* pwd는 정수 데이터인 pwd를 SQL문의 조건 값으로 전달한다. */
		return membersList;
	}


	
	
}
