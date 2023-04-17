package sec05.ex01;

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
			// ctx�� lookup�޼��带 �̿��ؼ� "java:/comp/env"�� �ش��ϴ� ��ü�� ã�Ƽ� envContext�� ����
			
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			// envConbtext�� lookup�޼��带 �̿��ؼ� "jdbc/oracle"�� �ش��ϴ� ��ü�� ã�Ƽ� dataFactory�� ����
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List listMembers() {
		List list = new ArrayList();
		try {
			// connDB();
			con = dataFactory.getConnection();
			// getConnection�޼��带 �̿��ؼ� Ŀ�ؼ� Ǯ�� ���� Ŀ�ؼ� ��ü�� ���� con������ ����
			
			String query = "select * from t_member ";
			System.out.println("prepareStatememt: " + query);
			
			pstmt = con.prepareStatement(query);
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
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addMember(MemberVO memberVO) {
		try {
			Connection con = dataFactory.getConnection();
			
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			String query = "insert into t_member";
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			
			System.out.println("prepareStatememt: " + query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delMember(String id) {
		try {
			Connection con = dataFactory.getConnection();
			Statement stmt = con.createStatement();
			
			String query = "delete from t_member" + " where id=?";
			
			System.out.println("prepareStatememt:" + query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isExisted(MemberVO memberVO) {
		boolean result = false;
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "select decode(count(*),1,'true','false') as result from t_member";
			query += " where id=? and pwd=?";
			// ����Ŭ�� decode()�Լ��� �̿��� ��ȸ�Ͽ� ID�� ��й�ȣ�� ���̺� �����ϸ� true��, �������� ������ false�� ��ȸ�մϴ�.
			
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			// �޼��尡 ���޵� ID�� ��й�ȣ�� �̿��� SQL���� �ۼ��� �� �����ͺ��̽��� ��ȸ�Ѵ�.
			
			rs.next(); //Ŀ���� ù��° ���ڵ�� ��ġ��ŵ�ϴ�.
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
