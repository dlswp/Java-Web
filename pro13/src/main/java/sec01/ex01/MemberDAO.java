package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			// 현재 환경의 naming context 획득하기
			
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			// DataSource 찾기
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List listMembers() {
		List list = new ArrayList();
		try {
			con = dataFactory.getConnection();
			// DataSource를 이용해 데이터베이스에 연결
			
			String query = "select * from t_member order by joinDate desc ";
			// 회원 정보를 최근 가입일 순으로 조회합니다.
			
			System.out.println("prepareStatememt: " + query);
			
			pstmt = con.prepareStatement(query);
			// prepareStatement() 메서드에 SQL문을 전달해 PrepareStatement 객체를 생성
			ResultSet rs = pstmt.executeQuery();
			// executeQuery() 메서드를 호출해 미리 설정한 SQL문을 실행
			
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				// 조회한 레코드의 각 컬럼 값을 받아옴.
				
				MemberBean vo = new MemberBean();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				// 각 컬럼 값을 다시 MemberVO객체의 속성에 설정
				
				list.add(vo);
				// 조회한 회원 정보를 MemberBean객체의 속성에 저장한 후 다시 ArrayList에 저장한다.
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		// 조회한 레코드의 개수만큼 MemberVO객체를 저장한 ArrayList를 반환.
	}

	public void addMember(MemberBean memberBean) {
		try {
			Connection con = dataFactory.getConnection();
			// DataSource를 이용해 데이터 베이스와 연결
			
			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			
			String query = "insert into t_member";
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			
			System.out.println("prepareStatememt: " + query);	
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			// insert문의 각 ?에 순서대로 회원정보를 세팅
			
			pstmt.executeUpdate();
			// 조회문을 제외한 create,drop,insert,delete,update등등 문을 처리할 때 사용
			
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
