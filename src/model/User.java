package model;

import java.util.ArrayList;

public class User {
    private long id;
    private String name;
    private double calories;
    private long daysOfTraining;
    private long fullChallenges;
    private ArrayList<Training> trainings;

    public User(long id, String username, String email, String name, String password, double calories, long daysOfTraining, long fullChallenges, ArrayList<Training> trainings){
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.daysOfTraining = daysOfTraining;
        this.fullChallenges = fullChallenges;
        this.trainings = trainings;
    }

    public User(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public long getDaysOfTraining() {
        return daysOfTraining;
    }

    public void setDaysOfTraining(long daysOfTraining) {
        this.daysOfTraining = daysOfTraining;
    }

    public long getFullChallenges() {
        return fullChallenges;
    }

    public void setFullChallenges(long fullChallenges) {
        this.fullChallenges = fullChallenges;
    }

    public ArrayList<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
    }
}
