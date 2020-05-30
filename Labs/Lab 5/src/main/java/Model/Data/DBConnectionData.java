package Model.Data;

public class DBConnectionData {

    public String getLogin() {
        return "root";
    }

    public String getPassword() {
        return "root";
    }

    public String getConnectionURL() {
        return "jdbc:mysql://localhost:3306/internet_shop?autoReconnect=true&useSSL=false";
    }
}
