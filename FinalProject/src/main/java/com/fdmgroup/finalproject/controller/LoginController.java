package com.fdmgroup.finalproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.finalproject.dao.UserDAO;
import com.fdmgroup.finalproject.exceptions.JPAException;
import com.fdmgroup.finalproject.exceptions.LoginException;
import com.fdmgroup.finalproject.model.User;

/**
 * LoginController class for login and related operations
 * @author Knight
 *
 */
@Controller
public class LoginController {
	
	/**
	 * Method that directs to login page and adds a user bean to the model
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String goToLogin(HttpServletRequest request, Model model) {
		return "login";
	}

	/**
	 * Method that logs in a user by checking the username and password against the database
	 * @param request
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/loginuser", method = RequestMethod.POST)
	public String loginUser(HttpServletRequest request, Model model,
			@RequestParam String username, @RequestParam String password) {
		ApplicationContext ac = (ApplicationContext) request.getSession()
				.getServletContext().getAttribute("context");
		UserDAO dao = (UserDAO) ac.getBean("UserDAO");

		User user = null;

		try {
			user = dao.getUser(username);

		} catch (JPAException e) {
			e.printStackTrace();
			throw new LoginException(e.getMessage());
		}

		if (user == null) {
			model.addAttribute("wrongusername", true);
			return "login";

		} else if (!user.getPassword().equals(password)) {
			model.addAttribute("wrongpassword", true);
			return "login";
		} else {
			request.getSession().setAttribute("loggeduser", user);
			return "home";
		}
	}

	/**
	 * Method that logs out a user and returns the home page
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logoutUser(HttpServletRequest request) {
		request.getSession().removeAttribute("loggeduser");
		return "home";
	}

}
