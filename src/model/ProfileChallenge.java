package model;

import java.util.ArrayList;

public class ProfileChallenge {
    private String status;
    private String dateStart;
    private String dateLast;
    private Challenge challenge;
    private long id;

    public ProfileChallenge(long id, String status, String dateStart, String dateLast, Challenge challenge){
        this.status = status;
        this.dateStart = dateStart;
        this.dateLast = dateLast;
        this.challenge = challenge;
    }
    public ProfileChallenge(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateLast() {
        return dateLast;
    }

    public void setDateLast(String dateLast) {
        this.dateLast = dateLast;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
