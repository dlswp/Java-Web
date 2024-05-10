package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.member.vo.MemberVO;

@Repository("memberDAO")		// id가 memberDAO인 빈을 자동 주입한다.
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	// XML 설정 파일에서 생성한 id가 sqlSession인 빈을 자동 주입한다.

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		// 주입된 sqlSession 빈으로 selectList() 메서드를 호출하면서 SQL문의 id를 전달한다.
		return membersList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		// 주입된 sqlSession 빈으로 insert() 메서드를 호출하면서 SQL문의 id와 memberVO를 전달한다.
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result =  sqlSession.delete("mapper.member.deleteMember", id);
		// 주입된 sqlSession 빈으로 delete() 메서드를 호출하면서 SQL문의 id와 회원 ID를 전달한다.
		return result;
	}
}
