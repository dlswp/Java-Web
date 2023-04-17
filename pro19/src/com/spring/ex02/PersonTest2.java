package com.spring.ex02;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest2 {
	public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
		// 실행 시 person.xml을 읽어 들여 빈을 생성한다.
		
		PersonService person1 = (PersonService) factory.getBean("personService1");
		// id가 personService1인 빈을 가져온다.
	
		
		person1.sayHello();
		// 생성된 빈을 이용해 name 값을 출력한다.
		System.out.println();
		
		PersonService person2 = (PersonService) factory.getBean("personService2");
		person2.sayHello();
	}
}
