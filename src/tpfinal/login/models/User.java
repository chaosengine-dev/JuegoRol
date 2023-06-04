package tpfinal.login.models;

import java.io.Serializable;
public class User implements Serializable {
    private static int lastId = 0;
    private int id;
    private String username;
    private String password;
    private String secondPassword;
    private String email;
    private boolean isAdmin = false;

    public User(){}
    public User(String username, String password, String secondPassword, String email) {
        this.id = generateUniqueId();
        this.username = username;
        this.password = password;
        this.secondPassword = secondPassword;
        this.email = email;
    }

    private int generateUniqueId() {
        return lastId++;
    }

    //region GETTERS AND SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getSecondPassword() { return secondPassword; }
    public void setSecondPassword(String secondPassword) { this.secondPassword = secondPassword; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean getisAdmin() {return isAdmin;}
    public void setAdmin(boolean admin) {isAdmin = admin;}
    //endregion

    @Override
    public String toString() {
        return "- UserName: " + username +
                "- Password: " + password +
                "- SecondPassword: " + secondPassword +
                "- email: " + email;
    }
}