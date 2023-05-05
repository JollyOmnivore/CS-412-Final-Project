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



    public static String LeaderBoard() {
        StringBuilder leaderboardData = new StringBuilder();
        Connection connection = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query to select the top 4 balances
            ResultSet resultSet = statement.executeQuery("SELECT username, balance FROM GameData ORDER BY balance DESC LIMIT 3");

            // Loop through the result set and add each username and balance to the leaderboardData string
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int balance = resultSet.getInt("balance");
                leaderboardData.append(username).append(": $").append(balance).append(",");
            }

            // Remove the trailing comma and space from the string
            if (leaderboardData.length() > 0) {
                leaderboardData.setLength(leaderboardData.length() - 2);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();

        }
         catch (SQLException e) {
            e.printStackTrace();
        }

        return leaderboardData.toString();
    }











}
