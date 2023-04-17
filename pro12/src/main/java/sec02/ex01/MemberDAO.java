package sec02.ex01;

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
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List listMembers(MemberVO memberVO) {
		List membersList = new ArrayList();
		String _name=memberVO.getName(); // 조회할 이름을 가져온다.
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member ";
			
			if((_name!=null && _name.length()!=0)){ // _name 값이 존재하면 SQL문에 where절을 추가하여 해당 이름으로 조회한다.
				 query+=" where name=?";
				 pstmt = con.prepareStatement(query);
				 pstmt.setString(1, _name); // 첫 번째 "?"에 전달된 이름을 지정한다.
			}else {
				pstmt = con.prepareStatement(query);	// _name 값이 없으면 모든 회원 정보를 조회합니다.
			}
			
			
			System.out.println("prepareStatememt: " + query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				membersList.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membersList;
	}

}
