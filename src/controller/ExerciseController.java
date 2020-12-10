package controller;

import dao.ExerciseDAO;
import model.Exercise;
import utils.Constants;

import java.util.ArrayList;

public class ExerciseController extends Constants {
    public ArrayList<Exercise> getExercises(){
        return ((new ExerciseDAO()).getExercises());
    }
    
    public int createExercise(Exercise exercise) {
    	if(exercise == null){
            return ERROR_EMPTY;
        }else if(exercise.getTitle()==null){
            return ERROR_EMPTY;
        }else if(exercise.getLink()==null){
            return ERROR_EMPTY;
        }
    	
    	if(exercise.getTitle().isEmpty()) {
    		return ERROR_EMPTY;
    	}else if(exercise.getLink().isEmpty()) {
    		return ERROR_EMPTY;
    	}
    	
    	if(exercise.getCaloriesPerSecond()<=0) {
    		return ERROR_EMPTY;
    	}
    	
    	if((new ExerciseDAO()).createExercise(exercise)) {
    		return OK;
    	}
    	
    	return ERROR_INTERNAL;
    	
    }
}
