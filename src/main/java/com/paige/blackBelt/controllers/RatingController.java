package com.paige.blackBelt.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paige.blackBelt.models.Rating;
import com.paige.blackBelt.models.Show;
import com.paige.blackBelt.models.User;
import com.paige.blackBelt.services.RatingService;
import com.paige.blackBelt.services.ShowService;
import com.paige.blackBelt.services.UserService;

@Controller
public class RatingController {
	
	private final RatingService ratingService;
	private final UserService userService;
	private final ShowService showService;
	
	public RatingController(RatingService ratingService, UserService userService,  ShowService showService) {
		this.ratingService = ratingService;
		this.userService = userService;
		this.showService = showService;
	}
	
	@PostMapping("/add/rating")
	public String addRating(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute(rating.getShow());
			
			return "show.jsp";
		}
		
		else {
			ratingService.addRating(rating);
			user.getRatings().add(rating);
			Show show = rating.getShow();
			show.getRatings().add(rating);
			return "redirect:/dashboard";
		}
	}
}
