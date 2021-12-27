package com.paige.blackBelt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paige.blackBelt.models.Rating;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long>{
	List<Rating> findAll();
	List<Rating> findAllByUser_id(Long id);
	List<Rating> findAllByShow_id(Long id);
	@Query("SELECT d FROM Rating d WHERE show_id = ?1 ORDER BY d.score ASC")
	 List<Rating> findAllRatingsByShow(Long id);
	@Query("SELECT d.score FROM Rating d WHERE show_id = ?1")
	 List<Integer> findAllShowRatings(Long id);
	

	    
}
