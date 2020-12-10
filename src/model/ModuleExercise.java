package model;

public class ModuleExercise {
    private long id;
    private Exercise exercise;
    private Modules module;

    public ModuleExercise(long id, Exercise exercise, Modules module){
        this.id = id;
        this.exercise = exercise;
        this.module = module;
    }

    public ModuleExercise(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Modules getModule() {
        return module;
    }

    public void setModule(Modules module) {
        this.module = module;
    }
}
