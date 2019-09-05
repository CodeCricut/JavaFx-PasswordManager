package model;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 0L;

    private String service;
    private String username;
    private String password;

    public Account(String username, String password){
        this("", username, password);
    }

    public Account(String service, String username, String password){
        this.service = service;
        this.username = username;
        this.password = password;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o){
        if (! (o instanceof Account))
            return false;
        return (((Account) o).username.equals(username) &&
                ((Account) o).password.equals(password));
    }
}