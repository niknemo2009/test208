package com.parking;


public class User {
    private String login;
    private String password;

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public boolean LogIn(String login, String password){
        if(this.login.equals(login) && this.password.equals(password)){
            return true;
        }else{
            return false;
        }
    }
}
