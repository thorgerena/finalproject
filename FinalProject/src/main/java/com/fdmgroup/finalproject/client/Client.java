package com.fdmgroup.finalproject.client;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fdmgroup.finalproject.dao.CouponDAO;
import com.fdmgroup.finalproject.dao.UserDAO;
import com.fdmgroup.finalproject.exceptions.JPAException;
import com.fdmgroup.finalproject.model.Coupon;
import com.fdmgroup.finalproject.model.User;

public class Client {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		
		CouponDAO couponDAO = (CouponDAO) ctx.getBean("CouponDAO");
		Coupon c1 = (Coupon) ctx.getBean("newCoupon");
		c1.setDescription("Awesome Deal");
		c1.setValue(new BigDecimal(1000.0));
		c1.setUses(3);
		c1.setCouponID(1);
		Coupon c2 = (Coupon) ctx.getBean("newCoupon");
		c2.setDescription("25% savings");
		c2.setValue(new BigDecimal(1000.0));
		c2.setUses(2);
		c2.setCouponID(2);
		
		try {
			couponDAO.addCoupon(c1);
			couponDAO.addCoupon(c2);
		} catch (JPAException e) {
			e.printStackTrace();
		}
		System.out.println("Done adding coupon");
		
//		try {
//			couponDAO.removeCoupon(1);
//		} catch (JPAException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Done removing Coupon");
		System.out.println("Size: " +couponDAO.listCoupons().size());
		
		UserDAO userDAO = (UserDAO) ctx.getBean("UserDAO");
		User trainee = (User) ctx.getBean("newUser");
		trainee.setEmail("haha@haha1");
		trainee.setUsername("admin123");
		trainee.setPassword("adminpw");
		trainee.setFirstName("a");
		trainee.setLastName("a");
		
		List<Coupon> coupons = Arrays.asList(c1,c2);
		
		trainee.setCoupons(coupons);
		
		try {
			userDAO.addUser(trainee);
		} catch (JPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done adding User");
//		try {
//			userDAO.removeUser(userDAO.getUserByUsername("admin123").getUserID());
//		} catch (JPAException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Done removing User");
		System.out.println("Size: "+userDAO.listUsers().size());
		
		((ConfigurableApplicationContext)ctx).close();
	}

}