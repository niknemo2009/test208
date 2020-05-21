package Model.Actors;

import java.util.regex.Pattern;

public class User {
    protected long id;
    protected String login;
    protected String password;
    private UserStatus status;
    private int login_try;
    private Role role;

    public User(){
        this.status = UserStatus.UNAUTHORIZED;
        this.login_try = 0;
    }

    public boolean createUses(Long id, String login, String password, Role role){
        if(!correctLogin(login) || password.length() < 8){
            return false;
        }
        else {
            this.id = id;
            this.role = role;
            this.login = login;
            this.password = password;
        }
        return true;
    }

    //get
    public long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getLogin(){
        return login;
    }

    public UserStatus getStatus(){
        return status;
    }

    public int getLoginTry(){
        return login_try;
    }

    //set
    public void setStatus(UserStatus new_status){
        this.status = new_status;
    }

    public void setUser(String login, String password){
        this.login = login;
        this.password = password;
    }

    //method
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public void increaseLoginTry(){
        ++login_try;
    }

    protected boolean correctLogin(String login){
        return Pattern.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}",login);
    }
}
