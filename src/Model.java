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
    int balance = 0;
    boolean exists = false;
    try {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement stmt = connection.createStatement();

        String cmd = String.format("SELECT * FROM GameData WHERE username = '%s';", Username);
        ResultSet resultSet = stmt.executeQuery(cmd);
        while (resultSet.next()) {
            exists = true;
            balance = resultSet.getInt("balance");
        }
        if(exists == false){
            System.out.println("creating new user");
            String newuser = "INSERT INTO GameData (username,balance) VALUES ('" + Username + "',1000)";
            System.out.println(newuser);
            stmt.execute(newuser);
            balance = 1000;
            connection.close();
        }
        return (balance);


    } catch (SQLException var10) {
        System.out.println("unable to connect to database.db");
        var10.printStackTrace();
    }
    return 0;
}

public static void Bet(String Username,int Balance){
    System.out.println(Username + " made a bet their balance is now " + Balance);
    Connection connection;
    try {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement stmt = connection.createStatement();
        String cmd = String.format("UPDATE GameData SET balance = %d WHERE username = '%s';",Balance, Username);
        stmt.execute(cmd);
    }
    catch (SQLException var10)   {
        System.out.println("unable to connect to database.db");
        var10.printStackTrace();
    }


}


public static void Createdb() {

    Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement stmt = connection.createStatement();

            String cmd = "CREATE TABLE IF NOT EXISTS GameData ("
                + "userid INTEGER PRIMARY KEY,"
                + "username STRING NOT NULL UNIQUE,"
                + "balance INT NOT NULL"
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
