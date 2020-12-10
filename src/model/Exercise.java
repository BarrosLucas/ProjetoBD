package model;

public class Exercise {
    private long id;
    private String title;
    private int repetition;
    private double caloriesPerSecond;
    private String link;

    public Exercise(long id, String title, int repetition, double caloriesPerSecond, String link){
        this.id = id;
        this.title = title;
        this.repetition = repetition;
        this.caloriesPerSecond = caloriesPerSecond;
        this.link = link;
    }
    public Exercise(){

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

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public double getCaloriesPerSecond() {
        return caloriesPerSecond;
    }

    public void setCaloriesPerSecond(double caloriesPerSecond) {
        this.caloriesPerSecond = caloriesPerSecond;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
