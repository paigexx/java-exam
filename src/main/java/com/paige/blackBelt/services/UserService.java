package com.paige.blackBelt.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.paige.blackBelt.models.User;
import com.paige.blackBelt.repositories.RatingRepository;
import com.paige.blackBelt.repositories.ShowRepository;
import com.paige.blackBelt.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
	private final RatingRepository ratingRepository;
	private final ShowRepository showRepository;
	
	public UserService(RatingRepository ratingRepository, UserRepository userRepository, ShowRepository showRepository) {
		this.ratingRepository = ratingRepository;
		this.showRepository = showRepository;
		this.userRepository = userRepository;
	}

    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> optUser = userRepository.findById(id);
    	
    	if(optUser.isPresent()) {
            return optUser.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public User save(User user) {
    	return userRepository.save(user);
    }
    
    public List<String> userEmails(){
    	return userRepository.findAllEmails();
    }
    
}