package model;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;

public class Modules {
    private long id;
    private String description;
    private String title;
    private ArrayList<ModuleExercise> moduleExercises;

    public Modules(long id, String description, String title, ArrayList<ModuleExercise> moduleExercises){
        this.id = id;
        this.description = description;
        this.title = title;
        this.moduleExercises = moduleExercises;
    }
    public Modules(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ModuleExercise> getExercises() {
        return moduleExercises;
    }

    public void setExercises(ArrayList<ModuleExercise> moduleExercises) {
        this.moduleExercises = moduleExercises;
    }
}
