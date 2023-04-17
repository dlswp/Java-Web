package sec01.ex02;

public class Address {

	private String city;
	private String zipcode;
	// 회원이 거주 도시와 우편번호를 저장한다.
	
	public Address() {
		
	}
	// 속성에 대한 getter/setter

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
