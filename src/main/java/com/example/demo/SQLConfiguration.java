package com.example.demo;

import java.sql.*;

public class SQLConfiguration {
    // url to access the database
    static String databaseURL = "jdbc:postgresql://robotsimulatordatabase.cdimocs062ok.us-east-2.rds.amazonaws.com:5433/robot_simulator_db?user=AccessPoint&password=AccessPoint9876";

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
        String addUserSQL = """
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
                        RAISE NOTICE 'Email already in use. Please select a different one.', ?;
                    ELSE
                        INSERT INTO useraccounts VALUES (?, ?, ?)
                    END IF;
                END $$;""";

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
                        RAISE NOTICE 'Welcome, %.', ?;
                    ELSE
                        RAISE NOTICE 'Invalid credentials. Please try again or make an account.';
                    END IF;
                END $$;""";

        try (Connection connection = DriverManager.getConnection(databaseURL);
        PreparedStatement preparedStatement = connection.prepareStatement(logInSQL)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);

            addNewUser(name, email, password);

        } catch (SQLException e) {
            System.out.println("Error checking user account: " + e);
        }

    }
}
