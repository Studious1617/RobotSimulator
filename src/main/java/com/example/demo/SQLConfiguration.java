package com.example.demo;

import java.sql.*;

public class SQLConfiguration {
    // url to access the database
    static String databaseURL = "robot_simulator_db.public.useraccounts";

    // makes table
    public SQLConfiguration () {
        try (Connection connection = DriverManager.getConnection(databaseURL);
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS userAccounts (" +
                    "fullname VARCHAR(50) NOT NULL," +
                    "emailAddress VARCHAR(50) NOT NULL UNIQUE," +
                    "password VARCHAR(30) NOT NULL UNIQUE)");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e);
        }
    }

    //
    public void addNewUser (String name, String email, String password) {
        String addUserSQL = "INSERT INTO useraccounts (fullname, emailaddress, password)" +
                " VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(databaseURL);
             PreparedStatement preparedStatement = connection.prepareStatement(addUserSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e);
        }
    }

    public void userLogIn (String name, String email, String password) {
        String logInSQL = """
                DO $$
                DECLARE
                    doesExist BOOLEAN;
                BEGIN
                    SELECT EXISTS (
                        SELECT 2
                        FROM useraccount
                        WHERE emailaddress = ?
                    ) INTO doesExist;

                IF doesExist THEN
                        RAISE NOTICE '% already exists. Please choose a different email address.', ?;
                    ELSE
                        RAISE NOTICE 'Account created.';
                    END IF;
                END $$;""";

        try (Connection connection = DriverManager.getConnection(databaseURL);
        PreparedStatement preparedStatement = connection.prepareStatement(logInSQL)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, email);

            addNewUser(name, email, password);

        } catch (SQLException e) {
            System.out.println("Error checking user account: " + e);
        }

    }
}
