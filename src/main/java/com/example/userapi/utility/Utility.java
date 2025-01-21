package com.example.userapi.utility;

import org.apache.commons.validator.routines.EmailValidator;

public class Utility {

    public boolean validateEmail(String email){
        boolean validEmail = EmailValidator.getInstance().isValid(email);

        System.out.println("Validating email " + validEmail);
        if(!validEmail){
            return false;
        }else{
            return true;
        }
    }


}
