package com.fdmgroup.finalproject.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fdmgroup.finalproject.dao.UserDAO;
import com.fdmgroup.finalproject.exceptions.JPAException;
import com.fdmgroup.finalproject.model.User;

public class Client {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		UserDAO userDAO = (UserDAO) ctx.getBean("UserDAO");
		User trainee = (User) ctx.getBean("newUser");
		trainee.setEmail("haha@haha1");
		trainee.setUsername("admin12");
		trainee.setPassword("adminpw");
		trainee.setFirstName("a");
		trainee.setLastName("a");
		
		try {
			userDAO.addUser(trainee);
		} catch (JPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done adding User");
		try {
			userDAO.removeUser("admin12");
		} catch (JPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done removing User");
		System.out.println("Size: "+userDAO.listUsers().size());
		((ConfigurableApplicationContext)ctx).close();
	}

}