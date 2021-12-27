package com.paige.blackBelt.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paige.blackBelt.models.User;
import com.paige.blackBelt.services.UserService;
import com.paige.blackBelt.validators.UserValidator;
@Controller
public class UserController {
	private final UserService userService;
	private final UserValidator userValidator;
	
	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	@RequestMapping("/")
	public String home(@ModelAttribute("user") User user) {
		System.out.println(userService.userEmails());
		return "home.jsp";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
			if(userService.userEmails().contains(user.getEmail())) {
				model.addAttribute("error", "That email already exisits");
				return "home.jsp";
			}
    		userValidator.validate(user, result);
    		if(result.hasErrors()) {
    			return "home.jsp";
    		}
    		else {
    			userService.registerUser(user);	
    			session.setAttribute("userId", user.getId());
    			return "redirect:/dashboard";
    		}
    }
	
	 @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, @ModelAttribute("user") User user) {
	    		if (userService.authenticateUser(email, password)) {
	    			User userSession = userService.findByEmail(email);
	    			session.setAttribute("userId", userSession.getId());
	    			return "redirect:/dashboard";
	    		}

	    		else {
	    			model.addAttribute("error", "Invalid credentials please try again");
	    			return "home.jsp";
	    		}
	    }
	 
	 @RequestMapping("/logout")
	    public String logout(HttpSession session) {
	        // invalidate session
	        // redirect to login page
	    		session.invalidate();
	    		return "redirect:/";
	    }

}
