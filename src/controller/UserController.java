package controller;

import dao.UserDAO;
import model.Login;
import model.Training;
import model.User;
import utils.Constants;
import utils.Validate;

import java.util.ArrayList;

public class UserController extends Constants {
    public int createUser(User user){
        if(user == null){
            return ERROR_EMPTY;
        }else if(user.getName()==null){
            return ERROR_EMPTY;
        }else if(user.getName().isEmpty()){
            return ERROR_EMPTY;
        }

        
        if(!Validate.validateName(user.getName())){
            return ERROR_NAME;
        }
        

        user.setCalories(0);
        user.setTrainings(new ArrayList<Training>());
        user.setFullChallenges(0);
        user.setDaysOfTraining(0);

        if((new UserDAO()).createUser(user)){
            return OK;
        }
        return ERROR_INTERNAL;
    }

    public User getUser(long idUser){
        return (new UserDAO()).getUserById(idUser);
    }
}
