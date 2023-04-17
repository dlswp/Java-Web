package sec02.ex01;

import java.sql.Date;

public class MemberBean {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	// 회원 테이블의 컬럼 이름과 동일하게 이름과 자료형을 선언합니다.
	
	public MemberBean() {
		
	}


	public MemberBean(String id, String pwd, String name, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		// 인자가 네 개인 생성자를 추가합니다.
		// 생성자의 역할은 반환값이 없지만, 반환타입을 void형으로 선언하지 않는다.
		// 생성자는 초기화를 위한 데이터를 인수로 전달 받는다.
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
