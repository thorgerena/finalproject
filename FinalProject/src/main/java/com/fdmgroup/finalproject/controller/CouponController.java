package com.fdmgroup.finalproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.finalproject.dao.CouponDAO;
import com.fdmgroup.finalproject.dao.UserDAO;
import com.fdmgroup.finalproject.exceptions.JPAException;
import com.fdmgroup.finalproject.exceptions.LoginException;
import com.fdmgroup.finalproject.model.Coupon;
import com.fdmgroup.finalproject.model.User;

/**
 * LoginController class for login and related operations
 * @author Knight
 *
 */
@Controller
public class CouponController {
	

	/**
	 * Method that looks up coupons owned by user
	 * @param request
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/getCoupons", method = RequestMethod.POST)
	public String getCoupons(HttpServletRequest request, Model model,
			@RequestParam String username) {
		ApplicationContext ac = (ApplicationContext) request.getSession()
				.getServletContext().getAttribute("context");
		UserDAO dao = (UserDAO) ac.getBean("UserDAO");

		User user = null;

		try {
			user = dao.getUserByUsername(username);

		} catch (JPAException e) {
			e.printStackTrace();
		}

		if (user == null) {
			model.addAttribute("wrongusername", true);
			return "home";

		} else {
			model.addAttribute("coupons", user.getCoupons());
			return "coupons";
		}
	}
	
	@RequestMapping(value = "/redeem", method = RequestMethod.POST)
	public String redeem(HttpServletRequest request, Model model, @RequestParam String couponID, @RequestParam String username) {
		ApplicationContext ac = (ApplicationContext) request.getSession().getServletContext().getAttribute("context");
		CouponDAO dao = (CouponDAO) ac.getBean("CouponDAO");
		
		try {
			if (dao.useCoupon(Integer.parseInt(couponID))) {
				model.addAttribute("redeemed", true);
			} else {
				model.addAttribute("redeemed", false);
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (JPAException e1) {
			e1.printStackTrace();
		}
		
		UserDAO userDAO = (UserDAO) ac.getBean("UserDAO");

		User user = null;

		try {
			user = userDAO.getUserByUsername(username);

		} catch (JPAException e) {
			e.printStackTrace();
			throw new LoginException(e.getMessage());
		}

		if (user == null) {
			model.addAttribute("wrongusername", true);
			return "home";

		} else {
			model.addAttribute("coupons", user.getCoupons());
			return "coupons";
		}
	}
}
