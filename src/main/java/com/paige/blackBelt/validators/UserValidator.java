package com.paige.blackBelt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.paige.blackBelt.models.User;



@Component
public class UserValidator implements Validator {
    
//  specifies that the instance of the User class can be validated with this validation 
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
//  first argument is the member variable we are "targeting" to validate. 
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }
}