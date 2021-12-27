package com.paige.blackBelt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paige.blackBelt.models.Rating;
import com.paige.blackBelt.models.Show;
import com.paige.blackBelt.repositories.RatingRepository;
import com.paige.blackBelt.repositories.ShowRepository;
import com.paige.blackBelt.repositories.UserRepository;

@Service
public class ShowService {
	private final RatingRepository ratingRepository;
	private final UserRepository userRepository;
	private final ShowRepository showRepository;
	
	public ShowService(RatingRepository ratingRepository, UserRepository userRepository, ShowRepository showRepository) {
		this.ratingRepository = ratingRepository;
		this.showRepository = showRepository;
		this.userRepository = userRepository;
	}
	
	public List<Show> findShows(){
		return showRepository.findAll();
	}
	
	public Show saveShow(Show show) {
		return showRepository.save(show);
	}
	
	public Show findShowById(Long id) {
	    	Optional<Show> show = showRepository.findById(id);
	    	
	    	if(show.isPresent()) {
	            return show.get();
	    	} else {
	    	    return null;
	    	}
	 }
	
	public void deleteShow(Long id) {
		showRepository.deleteById(id);
	}
	
	public List<Rating> findRatings(Long id){
		return ratingRepository.findAllByShow_id(id);
	}
	
	public List<Rating> showRatings(Long id){
		return ratingRepository.findAllRatingsByShow(id);
	}
	
	public List<String> showNames(){
		return showRepository.findShowNames();
	}


}
