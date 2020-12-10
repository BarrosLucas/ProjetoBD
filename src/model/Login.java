package model;

public class Login {
    private String email_username;
    private String password;

    public Login(String email_username, String password){
        this.email_username = email_username;
        this.password = password;
    }

    public String getEmail_username() {
        return email_username;
    }

    public void setEmail_username(String email_username) {
        this.email_username = email_username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
