package controller;

import dao.ExerciseDAO;
import dao.TrainingDAO;
import dao.UserDAO;
import model.*;
import utils.Constants;

import java.util.ArrayList;
import java.util.EnumMap;

public class TrainingController extends Constants {
    public int newWorkout(long idUser, Training training){
        if(training == null){
            return ERROR_EMPTY;
        }
        if(training.getTitle() == null){
            return ERROR_EMPTY;
        }
        if(training.getTitle().isEmpty()){
            return ERROR_EMPTY;
        }
        if(training.getDescription() == null){
            return ERROR_EMPTY;
        }
        if(training.getDescription().isEmpty()){
            return ERROR_EMPTY;
        }
        if(training.getModules()==null){
            return ERROR_EMPTY;
        }
        if(training.getModules().size()==0){
            return ERROR_EMPTY;
        }

        for(Modules module: training.getModules()){
            if(module.getDescription() == null){
                return ERROR_EMPTY;
            }
            if(module.getDescription().isEmpty()){
                return ERROR_EMPTY;
            }
            if(module.getTitle() == null){
                return ERROR_EMPTY;
            }
            if(module.getTitle().isEmpty()){
                return ERROR_EMPTY;
            }
            if(module.getExercises() == null){
                return ERROR_EMPTY;
            }
            if(module.getExercises().isEmpty()){
                return ERROR_EMPTY;
            }
            for(ModuleExercise moduleExercise: module.getExercises()){
                if(moduleExercise.getExercise() == null){
                    return ERROR_EMPTY;
                }
                if(!(new TrainingDAO()).createTraining(training,idUser)){
                    return ERROR_INTERNAL;
                }
            }
        }

        return OK;
    }

    public ArrayList<Training> getTrainings(long idUser){
        return (new TrainingDAO()).getTrainings(idUser);
    }

    public int runTraining(long idUser, double calories){
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(idUser);

        if(user == null){
            return ERROR_NOT_EXISTS;
        }

        user.setDaysOfTraining(user.getDaysOfTraining()+1);
        user.setCalories(user.getCalories()+calories);

        if(userDAO.updateUserById(user)){
            return OK;
        }

        return ERROR_INTERNAL;

    }


}
