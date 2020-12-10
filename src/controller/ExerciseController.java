package controller;

import dao.ExerciseDAO;
import model.Exercise;
import utils.Constants;

import java.util.ArrayList;

public class ExerciseController extends Constants {
    public ArrayList<Exercise> getExercises(){
        return ((new ExerciseDAO()).getExercises());
    }
}
