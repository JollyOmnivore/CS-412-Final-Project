import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
    public Model(){

    }



public static int Auth(String Username) {
    System.out.println(Username);
    Connection connection;
    try {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement stmt = connection.createStatement();

        String cmd = "SELECT username FROM GameData;";
        ResultSet resultSet = stmt.executeQuery(cmd);
        while (resultSet.next()) {
            String name = resultSet.getString("username");
            if (name.equals(Username)) {
                System.out.println("user exists");
                String getbal = "SELECT balance" +
                        "FROM GameData" +
                        "WHERE username = '" + Username + "';";
                ResultSet balance = stmt.executeQuery(getbal);
                int userBalance = balance.getInt("balance");
                System.out.println(userBalance);

                connection.close();
                return userBalance;

            }
            else {
                System.out.println("not name match");
            }


            System.out.println("creating new user");
            String newuser = "INSERT INTO GameData (username,balance) VALUES ('" + Username + "',1000)";
            System.out.println(newuser);
            stmt.execute(newuser);
            connection.close();
            return 1000;

        }


    } catch (SQLException var10) {
        System.out.println("unable to connect to database.db");
        var10.printStackTrace();
    }
    return 0;
}

public static void Bet(String Username,int Balance){
    System.out.println(Username + " made a bet their balance is now " + Balance);
}


public static void Createdb() {

    Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement stmt = connection.createStatement();

            String cmd = "CREATE TABLE IF NOT EXISTS GameData ("
                + "userid INTEGER PRIMARY KEY,"
                + "username STRING,"
                + "balance INT"
                + ");";

            stmt.execute(cmd);
            connection.close();
            }
        catch (SQLException ex)
        {
        System.out.println("unable to connect to database.db");
        ex.printStackTrace();
        }
}

}
