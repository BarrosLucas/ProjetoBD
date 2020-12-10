package utils;

import dao.UserDAO;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Validate {
    public static boolean validateEmail(String email){
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    public static boolean validatePassword(String password){
        if(password == null){
            return false;
        }
        if(password.isEmpty()){
            return false;
        }
        if(password.contains(" ") || password.contains("-") || password.contains("*") || password.contains("/") || password.contains("_") || password.contains("-") || password.contains("=") ){
            return false;
        }
        if(!password.contains("0") && !password.contains("1") && !password.contains("2") && !password.contains("3") && !password.contains("4") && !password.contains("5") && !password.contains("6") && !password.contains("7") && !password.contains("8") && !password.contains("9")){
            return false;
        }
        if(password.length()<7){
            return false;
        }
        return true;
    }
    public static boolean validateUsername(String username){
        if(username.contains(" ")){
            return false;
        }
        return true;
    }
    public static boolean validateName(String name){
        if(name == null){
            return false;
        }
        if(name.isEmpty()){
            return false;
        }
        if(!name.contains(" ")){
            return false;
        }
        if(name.contains("0") || name.contains("1") || name.contains("2") || name.contains("3") || name.contains("4") || name.contains("5") || name.contains("6") || name.contains("7") || name.contains("8") || name.contains("9")){
            return false;
        }
        return true;
    }
    
}
