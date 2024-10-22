package com.example.demo;

import java.sql.*;

public class SQLConfiguration {
    // url to access the database
    static String databaseURL = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String upass = "Hard2Guess";
    // makes table
    public SQLConfiguration () {
        String accountsTable = "CREATE TABLE IF NOT EXISTS userAccounts (" +
                "user_id SERIAL PRIMARY KEY, " +
                "full_name VARCHAR(50) NOT NULL," +
                "email_Address VARCHAR(50) NOT NULL UNIQUE," +
                "password VARCHAR(30) NOT NULL)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             Statement statement = connection.createStatement()) {
            statement.execute(accountsTable);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e);
        }
    }

    public boolean checkUserInfo (String checkName, String checkEmail, String checkPassword) {
        return checkName != null && !checkName.trim().isEmpty() &&
                checkEmail != null && !checkEmail.trim().isEmpty() &&
                checkPassword != null && !checkPassword.trim().isEmpty();
    }
    public boolean checkUserLogIn (String checkEmail, String checkPassword) {
        return checkEmail != null && !checkEmail.trim().isEmpty() &&
                checkPassword != null && !checkPassword.trim().isEmpty();
    }

    public boolean addNewUser (String name, String email, String password) {
        String redoneAddUserSQL = "SELECT * FROM useraccounts WHERE email_address LIKE ?";

        String insertUserSQL = "INSERT INTO useraccounts (full_name, email_address, password) VALUES (?, ?, ?)";
        boolean emailAlreadyInUse = false;

        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(redoneAddUserSQL)) {
            if (checkUserInfo(name, email, password)) {
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    emailAlreadyInUse = true;
                }

                if (emailAlreadyInUse) {
                    System.out.println("Email is already in use. Please choose a different one.");
                    return false;
                } else {
                    // adds the user's info to their table
                    PreparedStatement addPreparedStatement = connection.prepareStatement(insertUserSQL);
                    addPreparedStatement.setString(1, name);
                    addPreparedStatement.setString(2, email);
                    addPreparedStatement.setString(3, password);
                    addPreparedStatement.executeUpdate();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e);
        }
        return false;
    }

    public boolean userLogIn (String email, String password) {
        String redoneLogInSQL = "SELECT * FROM useraccounts WHERE email_address LIKE ?;";
        boolean userAlreadyExists = false;

        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(redoneLogInSQL)) {
            if (checkUserLogIn(email, password)) {
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    userAlreadyExists = true;
                    System.out.println("Welcome, " + rs.getString("full_name"));
                }
            } else {
                System.out.println("Invalid credentials. Please try again or make an account.");
            }
        } catch (SQLException e) {
            System.out.println("Error checking user account: " + e);
        }
        return userAlreadyExists;
    }

    public void insertLayout (int layoutID, String layoutName, String[] cbData, String direction, String userEmail) {
        String layoutSQL = "INSERT INTO layouts (layout_id, layout_name, layout_data, direction, email_address)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(layoutSQL)) {
            preparedStatement.setInt(1, layoutID);
            preparedStatement.setString(2, layoutName);
            preparedStatement.setArray(3, connection.createArrayOf("VARCHAR", cbData));
            preparedStatement.setString(4, direction);
            preparedStatement.setString(5, userEmail);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving layout: " + e);
        }
    }

    public void deleteLayout (String userEmail, int layoutID) {
        String deleteLayoutSQL = "DELETE FROM layouts WHERE email_address = ? AND layout_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(deleteLayoutSQL)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setInt(2, layoutID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting layout: " + e);
        }
    }

    public void editLayout (String userEmail, String layoutName, String[] cbData, String direction, int layoutID) {
        String editLayoutSQL = "UPDATE layouts SET layout_name = ?, layout_data = ?, direction = ?" +
                " WHERE email_address = ? AND layout_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(editLayoutSQL)) {
            preparedStatement.setString(1, layoutName);
            preparedStatement.setArray(2, connection.createArrayOf("VARCHAR", cbData));
            preparedStatement.setString(3, direction);
            preparedStatement.setString(4, userEmail);
            preparedStatement.setInt(5, layoutID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error editing layout: " + e);
        }
    }

    public void viewLayout (String userEmail, int layoutID) {
        String viewLayoutSQL = "SELECT * FROM layouts WHERE email_address = ? AND layout_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(viewLayoutSQL)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setInt(2, layoutID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error viewing layout: " + e);
        }
    }

    public int getUserLayoutsAmount () {
        // gets layout info by matching the emails
        String getLayoutSQL = "SELECT * FROM useraccounts u RIGHT JOIN layouts l " +
                "ON u.email_address = l.email_address";
        int numberOfLayouts = 0;
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(getLayoutSQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                numberOfLayouts++;
            }
        } catch (SQLException e) {
            System.out.println("Error getting layout data: " + e);
        }
        return numberOfLayouts;
    }

    public int getLayoutID (String layout_name) {
        String userIDSQL = "SELECT l.layout_id FROM layouts l LEFT JOIN useraccounts u" +
                " ON l.email_address = u.email_address AND l.layout_name = ?";
        int layout_id = 1;
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(userIDSQL)) {
            preparedStatement.setString(1, layout_name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                layout_id = rs.getInt("layout_id");
            }
        } catch (SQLException e) {
            System.out.println("Error getting layout ID: " + e);
        }
        return layout_id;
    }

    // populate the factory layout with the saved layouts
    public void getUserLayoutData () {
        
    }
}