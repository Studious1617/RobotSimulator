package com.example.demo;

import java.sql.*;

public class SQLConfiguration {
    // url to access the database
    static String databaseURL = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String upass = "Hard2Guess";
    // makes table
    public SQLConfiguration () {
        // possible change
        String createUserTable = """
                CREATE TABLE ? || '_' || ? (
                fullname VARCHAR(50) NOT NULL, +
                emailAddress VARCHAR(50) NOT NULL UNIQUE, +
                password VARCHAR(30) NOT NULL))""";
        String accountsTable = "CREATE TABLE IF NOT EXISTS userAccounts (" +
                "fullname VARCHAR(50) NOT NULL," +
                "emailAddress VARCHAR(50) NOT NULL UNIQUE," +
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
        String redoneAddUserSQL = "SELECT * FROM useraccounts WHERE emailaddress LIKE ?";
        String insertUserSQL = "INSERT INTO useraccounts VALUES (?, ?, ?)";
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
                    /* makes a table for the user
                    PreparedStatement createPreparedStatement = connection.prepareStatement(createUserTable);
                    createPreparedStatement.setString(1, name.trim());
                    createPreparedStatement.setString(2, email);*/
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
        String redoneLogInSQL = "SELECT * FROM useraccounts WHERE emailaddress LIKE ?;";
        boolean userAlreadyExists = false;

        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(redoneLogInSQL)) {
            if (checkUserLogIn(email, password)) {
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    userAlreadyExists = true;
                    System.out.println("Welcome, " + rs.getString(1));
                }
            } else {
                System.out.println("Invalid credentials. Please try again or make an account.");
            }
        } catch (SQLException e) {
            System.out.println("Error checking user account: " + e);
        }
        return userAlreadyExists;
    }

    public void insertLayout (String userEmail, String layoutName, String[] cbData, String direction) {
        String layoutSQL = "INSERT INTO layouts (layout_name, layoutcells, facingdirection)" +
                " VALUES (?, ?, ?) WHERE user_email = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(layoutSQL)) {
            preparedStatement.setString(1, layoutName);
            preparedStatement.setArray(2, connection.createArrayOf("VARCHAR", cbData));
            preparedStatement.setString(3, direction);
            preparedStatement.setString(4, userEmail);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving layout: " + e);
        }
    }

    public void deleteLayout (String userEmail, String layoutName, String[] cbData, String direction) {
        String deleteLayoutSQL = "DELETE FROM useraccounts WHERE userEmail = ? AND layoutName IN (?)" +
                " AND cbData IN (?) AND direction IN = (?)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(deleteLayoutSQL)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, layoutName);
            preparedStatement.setArray(3, connection.createArrayOf("VARCHAR", cbData));
            preparedStatement.setString(4, direction);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting layout: " + e);
        }
    }

    public void editLayout (String userEmail, String layoutName, String[] cbData, String direction) {
        String editLayoutSQL = "UPDATE useraccounts SET layout_name = ?, cbData = ?, direction = ?" +
                " WHERE userEmail = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(editLayoutSQL)) {
            preparedStatement.setString(1, layoutName);
            preparedStatement.setArray(2, connection.createArrayOf("VARCHAR", cbData));
            preparedStatement.setString(3, direction);
            preparedStatement.setString(4, userEmail);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error editing layout: " + e);
        }
    }

    public void viewLayout (String userEmail, String layoutName, String[] cbData, String direction) {
        String viewLayoutSQL = "SELECT FROM useraccounts WHERE userEmail = ? AND layoutName IN (?)" +
                " AND cbData IN (?) AND direction IN = (?)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(viewLayoutSQL)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, layoutName);
            preparedStatement.setArray(3, connection.createArrayOf("VARCHAR", cbData));
            preparedStatement.setString(4, direction);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error viewing layout: " + e);
        }
    }

}