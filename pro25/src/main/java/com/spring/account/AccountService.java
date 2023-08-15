package com.spring.account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public class AccountService {
	private AccountDAO accDAO;

	public void setAccDAO(AccountDAO accDAO) {
		this.accDAO = accDAO;
	}
	// 속성 accDAO에 빈을 주입하기 위해 setter를 구현한다.
	
	public void sendMoney() throws Exception {
		accDAO.updateBalance1();
		accDAO.updateBalance2();
	}
	// sendMoney() 메서드 호출 시 accDAO의 두 개의 SQL문을 실행한다.
	
}


