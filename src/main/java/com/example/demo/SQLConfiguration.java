package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLConfiguration {
    // url to access the database
    static String databaseURL = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String upass = "Hard2Guess";

    public List<Layout> listOfLayoutLists = new ArrayList<>();

    // makes table
    public SQLConfiguration () {
        String accountsTable = """
                CREATE TABLE IF NOT EXISTS userAccounts (
                user_id SERIAL PRIMARY KEY,
                full_name VARCHAR(50) NOT NULL,
                email_Address VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(30) NOT NULL)""";
        String layoutsTable = """
                CREATE TABLE IF NOT EXISTS layouts (
                layout_id INT PRIMARY KEY,
                layout_name VARCHAR(50) NOT NULL UNIQUE,
                layout_data VARCHAR[],
                direction VARCHAR(10) NOT NULL,
                email_address VARCHAR(50) NOT NULL,
                CONSTRAINT fk_email
                    FOREIGN KEY (email_address)
                        REFERENCES useraccounts (email_address)
                )""";
        String rulesetsTable = """
                CREATE TABLE IF NOT EXISTS rulesets (
                ruleset_id SERIAL PRIMARY KEY,
                ruleset_name VARCHAR(50) UNIQUE NOT NULL,
                rule_count INT,
                email_address VARCHAR(50) NOT NULL)""";
        String rulesTable = """
                CREATE TABLE IF NOT EXISTS rules (
                rule_id SERIAL PRIMARY KEY,
                ruleset_id INT NOT NULL,
                when_condition VARCHAR(50) NOT NULL,
                is1_condition VARCHAR(50) NOT NULL,
                then_action VARCHAR(50) NOT NULL,
                and1_condition VARCHAR(50),
                is2_condition VARCHAR(50),
                and2_condition VARCHAR(50),
                is3_condition VARCHAR(50),
                and3_condition VARCHAR(50),
                is4_condition VARCHAR(50),
                CONSTRAINT fk_ruleset
                    FOREIGN KEY (ruleset_id)
                        REFERENCES rulesets (ruleset_id)
                )""";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             Statement statement = connection.createStatement()) {
            statement.execute(accountsTable);
            statement.execute(layoutsTable);
            statement.execute(rulesetsTable);
            statement.execute(rulesTable);
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
    public boolean checkEmailFormat (String email) {
        // email regex pattern
        Pattern emailFormat = Pattern.compile(
                ".+" + // start of email before the @ symbol
                        "@.+" + // the @ and the address like @gmail
                        "[.].+"); // the .com or whatever the ending is
        // matches what the user entered with the pattern
        Matcher matcher = emailFormat.matcher(email);
        return matcher.matches();
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

    public void insertLayout (String layoutName, String[] layoutData, String direction, String userEmail) {
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass)) {
            String layoutGetter = "SELECT l.layout_id FROM layouts l JOIN useraccounts u " +
                    "ON l.email_address = u.email_address WHERE u.email_address = ?";
            String enterLayout = "INSERT INTO layouts (layout_id, layout_name, layout_data, direction, email_address)" +
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement idStmt = connection.prepareStatement(layoutGetter);
            idStmt.setString(1, userEmail);
            ResultSet rs = idStmt.executeQuery();
            int layId = 1;
            while (rs.next()) {
                layId = rs.getInt("layout_id");
            }

            PreparedStatement layoutStmt = connection.prepareStatement(enterLayout);
            layoutStmt.setInt(1, layId);
            layoutStmt.setString(2, layoutName);
            layoutStmt.setArray(3, connection.createArrayOf("VARCHAR", layoutData));
            layoutStmt.setString(4, direction);
            layoutStmt.setString(5, userEmail);
            System.out.println("layoutData(): " + Arrays.toString(layoutData));
            layoutStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving layout: " + e);
        }
    }

    public void deleteLayout (String userEmail, int index) {
        String deleteLayoutSQL = "DELETE FROM layouts WHERE email_address = ? " +
                "AND layout_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteLayoutSQL)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setInt(2, index);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting layout: " + e);
        }
    }

    public void editLayout (String userEmail, String layoutName, String[] layoutData, String direction) {
        String editLayoutSQL = "UPDATE layouts SET layout_name = ?, layout_data = ?, direction = ?" +
                " WHERE email_address = ? AND layout_id IN (SELECT layout_id FROM layouts WHERE email_address = ?)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(editLayoutSQL)) {
            preparedStatement.setString(1, layoutName);
            preparedStatement.setArray(2, connection.createArrayOf("VARCHAR", layoutData));
            preparedStatement.setString(3, direction);
            preparedStatement.setString(4, userEmail);
            preparedStatement.setString(5, userEmail);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error editing layout: " + e);
        }
    }

    public void viewLayout (String userEmail, int layoutID) {
        String viewLayoutSQL = "SELECT * FROM layouts WHERE email_address = ? " +
                "AND layout_id IN ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(viewLayoutSQL)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setInt(2, layoutID);

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error viewing layout: " + e);
        }
    }

    public int getUserLayoutsAmount () {
        // gets layout info by matching the emails
        String getLayoutAmountSQL = "SELECT * FROM useraccounts u RIGHT JOIN layouts l " +
                "ON u.email_address = l.email_address";
        int numberOfLayouts = 0;
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(getLayoutAmountSQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                numberOfLayouts++;
            }
        } catch (SQLException e) {
            System.out.println("Error getting layout data: " + e);
        }
        return numberOfLayouts;
    }

    public List<Layout> getUserLayoutList (String userEmail) {
        String getLayoutSQL = "SELECT * FROM layouts l JOIN useraccounts u " +
                "ON l.email_address = u.email_address WHERE u.email_address = ? " +
                "ORDER BY l.layout_id";

        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(getLayoutSQL)) {
            preparedStatement.setString(1, userEmail);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int layoutId = rs.getInt("layout_id");
                String layoutName = rs.getString("layout_name");
                Array layoutDataArray = rs.getArray("layout_data");
                String[] layoutData = (String[]) layoutDataArray.getArray();
                String layoutDirection = rs.getString("direction");
                String layoutEmail = rs.getString("email_address");
                listOfLayoutLists.add(new Layout(layoutId, layoutName, layoutData, layoutDirection, layoutEmail));
            }
        } catch (SQLException e) {
            System.out.println("Error getting user's layout(s): " + e);
        }
        return listOfLayoutLists;
    }

    public void insertRuleset (String rulesetName, int ruleCount, String userEmail) {
        // when create button is clicked, ruleset is created in table
        String insertRuleset = "INSERT INTO rulesets (ruleset_name, rule_count, email_address) " +
                "VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(insertRuleset)) {
            preparedStatement.setString(1, rulesetName);
            preparedStatement.setInt(2, ruleCount);
            preparedStatement.setString(3, userEmail);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating ruleset: " + e);
        }
    }

    public void insertRules (int rulesetID, String when, String is1, String then,
                             String and1, String is2, String and2, String is3, String and3, String is4) {
        String insertRule = "INSERT INTO rules (ruleset_id, when_condition, is1_condition, then_action, " +
                "and1_condition, is2_condition, and2_condition, is3_condition, and3_condition, is4_condition)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(insertRule)) {
            //preparedStatement.setInt(1, ruleID);
            preparedStatement.setInt(1, rulesetID);
            preparedStatement.setString(2, when);
            preparedStatement.setString(3, is1);
            preparedStatement.setString(4, then);
            // And conditions
            preparedStatement.setString(5, and1);
            preparedStatement.setString(6, is2);
            preparedStatement.setString(7, and2);
            preparedStatement.setString(8, is3);
            preparedStatement.setString(9, and3);
            preparedStatement.setString(10, is4);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving rule: " + e);
        }
    }

    public ArrayList<String> getRules (int rulesetId) {
        String when, is1, then, and1, is2, and2, is3, and3, is4;
        ArrayList<String> conditions = new ArrayList<>();
        String getRulesSQL = "SELECT * FROM rules WHERE ruleset_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(getRulesSQL)){
            preparedStatement.setInt(1, rulesetId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // adds the row to the list
                conditions.add(rs.getString("when_condition"));
                conditions.add(rs.getString("is1_condition"));
                conditions.add(rs.getString("then_action"));
                conditions.add(rs.getString("and1_condition"));
                conditions.add(rs.getString("is2_condition"));
                conditions.add(rs.getString("and2_condition"));
                conditions.add(rs.getString("is3_condition"));
                conditions.add(rs.getString("and3_condition"));
                conditions.add(rs.getString("is4_condition"));
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return conditions;
    }

    public int getRulesetId (String rName, String email) {
        String getRulesetIdSQL = "SELECT ruleset_id FROM rulesets WHERE " +
                "ruleset_name = ? AND email_address = ?";
        // variable to hold the ruleset id
        int rulesetId = 0;
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(getRulesetIdSQL)) {
            preparedStatement.setString(1, rName);
            preparedStatement.setString(2, email);
            // gets results from running the SQL string
            ResultSet rs = preparedStatement.executeQuery();
            // loops through the rows
            while (rs.next()) {
                // sets the variable to the result
                rulesetId = rs.getInt("ruleset_id");
            }
            // returns the result, or zero if there wasn't a match
            return rulesetId;
        } catch (SQLException e) {
            System.out.println("Error getting ruleset id: " + e);
        }
        // returns 0 if connection to database fails
        return rulesetId;
    }

    public void updateRuleCount (String rName, String email) {
        int rId = getRulesetId(rName, email);
        ArrayList<Integer> rulesForCountList = new ArrayList<>();
        int howManyRules = 0;
        String getRuleCountSQL = "SELECT rule_id FROM rules WHERE ruleset_id = ?";
        String updateRuleCountSQL = "UPDATE rulesets SET rule_count = ? WHERE ruleset_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass)) {
            // counts the rules in the ruleset
            PreparedStatement ruleCountStatement = connection.prepareStatement(getRuleCountSQL);
            ruleCountStatement.setInt(1, rId);
            ResultSet rs = ruleCountStatement.executeQuery();
            // loops through the Rules table
            while (rs.next()) {
                //
                int rule = rs.getInt("rule_id");
                // adds the rule_id to the list (just for counting below)
                rulesForCountList.add(rule);
            }
            // increases the howManyRules variable by 1 for every rule in the list
            for (int rule: rulesForCountList) {
                howManyRules++;
            }
            // changes the rule_count column value to the counted number above
            PreparedStatement updateStatement = connection.prepareStatement(updateRuleCountSQL);
            updateStatement.setInt(1, howManyRules);
            updateStatement.setInt(2, rId);
        } catch (SQLException e) {
            System.out.println("Error counting rules: " + e);
        }
    }
}