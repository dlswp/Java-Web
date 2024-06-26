package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

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

	public int insertMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		
		result = session.insert("mapper.member.insertMember", memberVO);
		// 지정한 id의 SQL문에 memberVO의 값을 전달하여 회원 정보를 테이블에 추가한다.
		
		session.commit();
		// 수동 커밋이므로 반드시 commit() 메서드를 호출하여 영구 반영한다.
		
		return result;
	}
	
	public int insertMember2(Map<String,String> memberMap) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		int result = session.insert("mapper.member.insertMember2", memberMap);
		// 지정한 id의 SQL문에 memberVO의 값을 전달하여 회원 정보를 테이블에 추가한다.
		
		session.commit();
		// 수동 커밋이므로 반드시 commit() 메서드를 호출하여 영구 반영한다.
		
		return result;
	}
	
	public int updateMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		int result = session.update("mapper.member.updateMember", memberVO);
		// 지정한 id의 SQL문에 memberVO의 값을 전달하여 회원 정보를 테이블에 추가한다.
		
		session.commit();
		// 수동 커밋이므로 반드시 commit() 메서드를 호출하여 영구 반영한다.
		
		return result;
	}
	
	public int deleteMember(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		int result = 0;
		result = session.delete("mapper.member.deleteMember", id);
		// 지정한 id의 SQL문에 memberVO의 값을 전달하여 회원 정보를 테이블에 추가한다.
		
		session.commit();
		// 수동 커밋이므로 반드시 commit() 메서드를 호출하여 영구 반영한다.
		
		return result;
	}
	
	public List searchMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		List list = session.selectList("mapper.member.searchMember", memberVO);
		// 회원 검색창에서 전달된 이름과 나이 값을 memberVO에 설정하여 SQL문으로 전달한다.
		
		return list;
	}
	
	public List<MemberVO>  foreachSelect(List nameList){
        sqlMapper=getInstance();
        SqlSession session=sqlMapper.openSession();
        
        List list=session.selectList("mapper.member.foreachSelect",nameList);
        // 검색 이름이 저장된 nameList를 SQL문으로 전달한다.
        
        return list;		
    }
	
	public int  foreachInsert(List memList){
        sqlMapper=getInstance();
        SqlSession session=sqlMapper.openSession();
        
        int result = session.insert("mapper.member.foreachInsert",memList);
        // 회원 정보가 저장된 memList를 SQL문으로 전달한다.
        
        session.commit();
        return result ;		
     }
	
	public List<MemberVO>  selectLike(String name){
        sqlMapper=getInstance();
        SqlSession session=sqlMapper.openSession();
        List list=session.selectList("mapper.member.selectLike",name);
        return list;		
    }
}
