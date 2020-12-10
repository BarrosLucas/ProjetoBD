package model;

import java.util.ArrayList;

public class Training {
    private long id;
    private String title;
    private String description;
    private ArrayList<Modules> modules;

    public Training(long id, String title, String description, ArrayList<Modules> modules){
        this.id = id;
        this.title = title;
        this.description = description;
        this.modules = modules;
    }
    public Training(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Modules> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Modules> modules) {
        this.modules = modules;
    }
}
