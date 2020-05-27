package sample;

import java.sql.*;

public class DatabaseHandler extends ConfigDB {
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" +
                dbHost +
                ":" +
                dbPort +
                "/" +
                dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return connection;
    }
    public void addRocket(Rocket rocket) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " +
                Constants.ROCKET_TABLE + "(" +
                Constants.ROCKET_POWER + "," +
                Constants.ROCKET_MASS + "," +
                Constants.ROCKET_MODEL + ")" + "VALUES(?,?,?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
        preparedStatement.setInt(1, rocket.getPower());
        preparedStatement.setInt(2, rocket.getMass());
        preparedStatement.setString(3, rocket.getModel());
        preparedStatement.executeUpdate();
    }

    public void deleteRocket(Rocket rocket) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM rocket WHERE model =?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(delete);
        preparedStatement.setString(1,rocket.getModel());

        preparedStatement.executeUpdate();
    }

    public void sselectRocket() throws SQLException, ClassNotFoundException{

        Statement statement = getConnection().createStatement();

        String select = "SELECT * FROM rocket";

        ResultSet res = statement.executeQuery(select);
        while(res.next()){
            int power = res.getInt(Constants.ROCKET_POWER);
            int mass = res.getInt(Constants.ROCKET_MASS);
            String model = res.getString(Constants.ROCKET_MODEL);

        }

    }
}
