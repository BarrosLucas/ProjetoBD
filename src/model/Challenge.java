package model;

public class Challenge {
    private long id;
    private String description;
    private String link;
    private String title;

    public Challenge(long id, String description, String link, String title){
        this.id = id;
        this.description = description;
        this.link = link;
        this.title = title;
    }
    public Challenge(){

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
