package com.fdmgroup.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController takes care of home related operations, like goHome.
 * @author Knight
 *
 */
@Controller
public class HomeController {

	/**
	 * Method that navigates to home page.
	 * @return "home" (home.jsp)
	 */
	@RequestMapping(value="/")
	public String goHome(){
		return "home";
	}	
}
