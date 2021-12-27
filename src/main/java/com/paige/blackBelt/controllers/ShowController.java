package com.paige.blackBelt.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paige.blackBelt.models.Rating;
import com.paige.blackBelt.models.Show;
import com.paige.blackBelt.models.User;
import com.paige.blackBelt.services.RatingService;
import com.paige.blackBelt.services.ShowService;
import com.paige.blackBelt.services.UserService;

@Controller
public class ShowController {

	private final ShowService showService;
	private final UserService userService;
	private final RatingService ratingService;
	
	public ShowController(ShowService showService, UserService userService, RatingService ratingService) {
		this.showService = showService;
		this.userService = userService;
		this.ratingService = ratingService;
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("shows", showService.findShows());
		return "dashboard.jsp";
	}
	
	@RequestMapping("/show/new")
	public String newShow(@ModelAttribute("show") Show show, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		return "create.jsp";
	}
	
	@RequestMapping(value="/show/create", method=RequestMethod.POST)
	public String createShow(@Valid @ModelAttribute("show") Show show, BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		if(showService.showNames().contains(show.getName())) {
			model.addAttribute("user", user);
			model.addAttribute("error", "That show name is taken.");
			return "create.jsp";
		}
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "create.jsp";
		}
		
		else {
			showService.saveShow(show);
			user.getShows().add(show);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/edit/{id}")
	public String editShow(@PathVariable("id") Long id, @ModelAttribute("show") Show show, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		Show thisShow = showService.findShowById(id);
		model.addAttribute("show", thisShow);
		return "edit.jsp";
	}
	
	@PostMapping("/show/update/{id}")
	public String updateShow(@Valid @ModelAttribute("show") Show show, BindingResult result, @PathVariable("id") Long id, Model model, HttpSession session) {
		if(result.hasErrors()) {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findUserById(userId);
			model.addAttribute("user", user);
			return "edit.jsp";
		}
		else {
			showService.saveShow(show);
			return "redirect:/dashboard";
			
		}
	}
	
	@RequestMapping("/show/{id}")
	public String showShow(@PathVariable("id") Long id, Model model, HttpSession session, @ModelAttribute("rating") Rating rating) {
		Show show = showService.findShowById(id);
		model.addAttribute(show);
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("ratings", showService.showRatings(id));
//		System.out.print(ratingService.ratingAverage(id));
		return "show.jsp";
	}
	
	@RequestMapping("/show/delete/{id}")
	public String deleteShow(@PathVariable("id") Long id, HttpSession session) {
		Show show = showService.findShowById(id);
		showService.deleteShow(id);
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		user.getShows().remove(show);
		return "redirect:/dashboard";
		
	}

}
