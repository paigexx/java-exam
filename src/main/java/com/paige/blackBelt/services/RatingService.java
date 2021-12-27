package com.paige.blackBelt.services;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.paige.blackBelt.models.Rating;
import com.paige.blackBelt.models.Show;
import com.paige.blackBelt.repositories.RatingRepository;
import com.paige.blackBelt.repositories.ShowRepository;
import com.paige.blackBelt.repositories.UserRepository;

@Service
public class RatingService {
	private final RatingRepository ratingRepository;
	private final UserRepository userRepository;
	private final ShowRepository showRepository;
	
	public RatingService(RatingRepository ratingRepository, UserRepository userRepository, ShowRepository showRepository) {
		this.ratingRepository = ratingRepository;
		this.showRepository = showRepository;
		this.userRepository = userRepository;
	}
	
	public List<Rating> findRatings(){
		return ratingRepository.findAll();
	}
	
	public Rating addRating(Rating rating) {
		return ratingRepository.save(rating);
	}
	
	public Rating findRatingById(Long id) {
    	Optional<Rating> rating = ratingRepository.findById(id);
    	
    	if(rating.isPresent()) {
            return rating.get();
    	} else {
    	    return null;
    	}
	}
//	
//	public Double ratingAverage(Long id) {
//		
//		List<Integer> ratings = ratingRepository.findAllShowRatings(id);
//		int sum = 0;
//		for (int i=0; i < ratings.size(); i++) {
//			sum += i;
//		}
//		double average = sum/ratings.size();
//		return average;
//	}


}
