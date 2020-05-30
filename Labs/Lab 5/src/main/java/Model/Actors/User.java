package Model.Actors;

import java.util.regex.Pattern;

public class User {
    protected long id;
    protected String login;
    protected String password;
    private Role role;

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

    public long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getLogin(){
        return login;
    }

    public void setUser(String login, String password){
        this.login = login;
        this.password = password;
    }

    //method
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    protected boolean correctLogin(String login){
        return Pattern.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}",login);
    }
}
