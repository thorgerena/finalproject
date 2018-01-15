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
import com.fdmgroup.finalproject.exceptions.RegisterException;
import com.fdmgroup.finalproject.model.User;

/**
 * RegisterController for registering and related operations
 * 
 * @author Knight
 * 
 */
@Controller
public class RegisterController {

	/**
	 * Method that directs to register page and adds a user bean to the model
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register")
	public String goToRegister(HttpServletRequest request, Model model) {
		return "register";
	}

	/**
	 * Method that register a user by adding them to the database. It also
	 * checks first if the username or email already exists and if so then
	 * throws RegisterException
	 * 
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public String register(HttpServletRequest request, Model model, 
				@RequestParam String username, @RequestParam String password, 
				@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName) {

		ApplicationContext ac = (ApplicationContext) request.getSession()
				.getServletContext().getAttribute("context");
		UserDAO dao = (UserDAO) ac.getBean("UserDAO");

		if (username.trim().equals("")
				|| password.trim().equals("")
				|| email.trim().equals("")
				|| firstName.trim().equals("")
				|| lastName.trim().equals("")) {
			model.addAttribute("emptyField", true);
			return "register";
		}

		User existingUser = null;

		try {
			existingUser = dao.getUser(username);

			if (existingUser != null) {
				model.addAttribute("usernameexists", true);
				return "register";
			}
		} catch (JPAException e) {
			e.printStackTrace();
			throw new RegisterException(e.getMessage());
		}

		try {
			existingUser = dao.getUserByEmail(email);

			if (existingUser != null) {
				model.addAttribute("emailexists", true);
				return "register";
			}
		} catch (JPAException e) {
			e.printStackTrace();
			throw new RegisterException(e.getMessage());
		}
		
		User user = (User) ac.getBean("newUser");	
		//User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			dao.addUser(user);
			model.addAttribute("registered", true);
			return ("login");
		} catch (JPAException e) {
			e.printStackTrace();
			model.addAttribute("registered", false);
			return "register";
		}
	}
}