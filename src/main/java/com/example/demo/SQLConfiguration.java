package com.example.demo;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLConfiguration {
    // environment variables
    static String databaseURL = System.getenv("DATABASE_URL");
    static String user = System.getenv("DATAUSER");
    static String upass = System.getenv("USERPASS");

    // makes tables
    public SQLConfiguration () {
        String accountsTable = """
                CREATE TABLE IF NOT EXISTS userAccounts (
                user_id SERIAL PRIMARY KEY,
                full_name VARCHAR(50) NOT NULL,
                email_Address VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(30) NOT NULL)""";
        String layoutsTable = """
                CREATE TABLE IF NOT EXISTS layouts (
                layout_id SERIAL PRIMARY KEY,
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
                and1_condition VARCHAR(50),
                is2_condition VARCHAR(50),
                and2_condition VARCHAR(50),
                is3_condition VARCHAR(50),
                and3_condition VARCHAR(50),
                is4_condition VARCHAR(50),
                then_action VARCHAR(50) NOT NULL,
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

    // ACCOUNT METHODS
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

    // LAYOUT METHODS
    public void insertLayout (String layoutName, ArrayList<String> layoutData, String direction, String userEmail) {
        String insertLayout = "INSERT INTO layouts (layout_name, layout_data, direction, email_address)" +
                " VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement layoutStmt = connection.prepareStatement(insertLayout)) {

            // Convert ArrayList to SQL Array
            Array sqlArray = connection.createArrayOf("VARCHAR", layoutData.toArray());

            // inserts the values into the question marks
            layoutStmt.setString(1, layoutName);
            layoutStmt.setArray(2, sqlArray);
            layoutStmt.setString(3, direction);
            layoutStmt.setString(4, userEmail);
            System.out.println("layoutData() from SQL: " + sqlArray);
            layoutStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving layout: " + e);
        }
    }

    public void deleteLayout (int lId) {
        String deleteLayoutSQL = "DELETE FROM layouts WHERE layout_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteLayoutSQL)) {
            preparedStatement.setInt(1, lId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting layout: " + e);
        }
    }

    public void editLayout (int layoutId, String layoutName, ArrayList<String> layoutData, String direction, String userEmail) {
        String editLayoutSQL = "UPDATE layouts SET layout_name = ?, layout_data = ?, direction = ?" +
                " WHERE email_address = ? AND layout_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(editLayoutSQL)) {

            // Convert ArrayList to SQL Array
            Array sqlArray = connection.createArrayOf("VARCHAR", layoutData.toArray());

            // inserts the parameters into the question marks
            preparedStatement.setString(1, layoutName);
            preparedStatement.setArray(2, sqlArray);
            preparedStatement.setString(3, direction);
            preparedStatement.setString(4, userEmail);
            preparedStatement.setInt(5, layoutId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error editing layout: " + e);
        }
    }

    public ArrayList<String> getLayoutNamesFromTable (String email) {
        // list to hold the names
        ArrayList<String> layoutNames = new ArrayList<>();

        // SQL to get the layout names, in order
        String getLayoutNamesSQL = "SELECT layout_name FROM layouts " +
                "WHERE email_address = ? ORDER BY layout_id";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(getLayoutNamesSQL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                layoutNames.add(rs.getString("layout_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting layout names: " + e);
        }
        // returns the layout names in the table
        return layoutNames;
    }

    public int getLayoutId (String lName, String email) {
        String getLayoutIdSQL = "SELECT layout_id FROM layouts WHERE " +
                "layout_name = ? AND email_address = ?";
        // variable to hold the layout id
        int layoutId = 0;
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(getLayoutIdSQL)) {
            preparedStatement.setString(1, lName);
            preparedStatement.setString(2, email);
            // gets results from running the SQL string
            ResultSet rs = preparedStatement.executeQuery();
            // loops through the rows
            while (rs.next()) {
                // sets the variable to the result
                layoutId = rs.getInt("layout_id");
            }
        } catch (SQLException e) {
            System.out.println("Error getting layout id: " + e);
        }
        // returns the result, or zero if there wasn't a match
        return layoutId;
    }

    public String getRobotDirection (int lId) {
        // initialized variable to hold the robot's direction
        String startingDirection = "";
        // SQL to get the robot's direction from table
        String getDirectionSQL = "SELECT direction FROM layouts WHERE layout_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(getDirectionSQL)) {
            // sets the question mark in the SQL to the method's parameter
            preparedStatement.setInt(1, lId);
            ResultSet rs = preparedStatement.executeQuery();
            // loops through the rows
            while (rs.next()) {
                startingDirection = rs.getString("direction");
            }
        } catch (SQLException e) {
            System.out.println("Error getting robot direction: " + e);
        }
        // returns the result, or a blank string if not
        return startingDirection;
    }

    public Map<Integer, List<String>> getUserLayouts (String email) {
        int layoutId;
        Array sqlArray;
        List<String> layoutData;

        // map for layout_id and layout
        Map<Integer, List<String>> idAndData = new HashMap<>();

        String getLayoutsSQL = "SELECT * FROM layouts WHERE email_address = ? ORDER BY layout_id";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(getLayoutsSQL)){
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // adds the id to the variable
                layoutId = rs.getInt("layout_id");
                // adds the layout_data to the map
                sqlArray = rs.getArray("layout_data");
                // Converts SQL array to Java array
                String[] javaArray = (String[]) sqlArray.getArray();
                // Converts Java array to ArrayList
                layoutData = new ArrayList<>(List.of(javaArray));
                // adds the id and the array to the map
                idAndData.put(layoutId, layoutData);
            }
        } catch (SQLException e) {
            System.out.println("Error getting rules: " + e);
        }
        return idAndData;
    }

    public int getUserLayoutsAmount (String userEmail) {
        // gets layout info by matching the emails
        String getLayoutAmountSQL = "SELECT * FROM layouts WHERE email_address = ?";
        int numberOfLayouts = 0;
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(getLayoutAmountSQL)) {
            preparedStatement.setString(1, userEmail);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                numberOfLayouts++;
            }
        } catch (SQLException e) {
            System.out.println("Error getting layout data: " + e);
        }
        return numberOfLayouts;
    }

    // RULESET METHODS
    public void insertRuleset (String rulesetName, String userEmail) {
        // when create button is clicked, ruleset is created in table
        String insertRuleset = "INSERT INTO rulesets (ruleset_name, email_address) " +
                "VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(insertRuleset)) {
            preparedStatement.setString(1, rulesetName);
            preparedStatement.setString(2, userEmail);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating ruleset: " + e);
        }
    }

    public void insertRule (int rulesetID, String when, String is1,
                String and1, String is2, String and2, String is3, String and3, String is4, String then) {
        String insertRule = "INSERT INTO rules (ruleset_id, when_condition, is1_condition, " +
                "and1_condition, is2_condition, and2_condition, is3_condition, and3_condition, is4_condition, " +
                "then_action) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(insertRule)) {
            // ruleset id and first condition
            preparedStatement.setInt(1, rulesetID);
            preparedStatement.setString(2, when);
            preparedStatement.setString(3, is1);
            // AND conditions
            preparedStatement.setString(4, and1);
            preparedStatement.setString(5, is2);
            preparedStatement.setString(6, and2);
            preparedStatement.setString(7, is3);
            preparedStatement.setString(8, and3);
            preparedStatement.setString(9, is4);
            // THEN
            preparedStatement.setString(10, then);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving rule: " + e);
        }
    }

    public void editRuleset (int ruleset_id, int rule_id, String when, String is1,
                 String and1, String is2, String and2, String is3, String and3, String is4, String then) {
        String editRulesSQL = "UPDATE rules SET when_condition = ?, is1_condition = ?, and1_condition = ?," +
                " is2_condition = ?, and2_condition = ?, is3_condition = ?, and3_condition = ?, is4_condition = ?," +
                " then_action = ? WHERE ruleset_id = ? AND rule_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(editRulesSQL)) {
            // first condition
            preparedStatement.setString(1, when);
            preparedStatement.setString(2, is1);
            // AND conditions
            preparedStatement.setString(3, and1);
            preparedStatement.setString(4, is2);
            preparedStatement.setString(5, and2);
            preparedStatement.setString(6, is3);
            preparedStatement.setString(7, and3);
            preparedStatement.setString(8, is4);
            // THEN
            preparedStatement.setString(9, then);
            // ids
            preparedStatement.setInt(10, ruleset_id);
            preparedStatement.setInt(11, rule_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving rule: " + e);
        }
    }

    public void deleteOldRule (int ruleId) {
        String deleteOldRuleSQL = "DELETE FROM rules WHERE rule_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(deleteOldRuleSQL)) {
            // sets the SQL string's question mark to the passed ruleId parameter
            preparedStatement.setInt(1, ruleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting old rule: " + e);
        }
    }

    public void deleteRuleset (int rulesetId) {
        String deleteRulesFirstSQL = "DELETE FROM rules WHERE ruleset_id = ?";
        String deleteRulesetSQL = "DELETE FROM rulesets WHERE ruleset_id = ?";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass)) {
            // deletes rules with ruleset id because they're dependent on it
            PreparedStatement deleteRulesFirstStatement = connection.prepareStatement(deleteRulesFirstSQL);
            deleteRulesFirstStatement.setInt(1, rulesetId);
            deleteRulesFirstStatement.executeUpdate();

            // now deletes ruleset from rulesets table
            PreparedStatement deleteRulesetsStatement = connection.prepareStatement(deleteRulesetSQL);
            deleteRulesetsStatement.setInt(1, rulesetId);
            deleteRulesetsStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting ruleset: " + e);
        }
    }

    public ArrayList<String> getRulesetNamesFromTable (String email) {
        // list to hold the names
        ArrayList<String> rulesetNames = new ArrayList<>();

        // SQL to get the ruleset names in order
        String getRulesetNamesSQL = "SELECT ruleset_name FROM rulesets " +
                "WHERE email_address = ? ORDER BY ruleset_id";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(getRulesetNamesSQL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rulesetNames.add(rs.getString("ruleset_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting ruleset names: " + e);
        }
        // returns the ruleset names in the table
        return rulesetNames;
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
        } catch (SQLException e) {
            System.out.println("Error getting ruleset id: " + e);
        }
        // returns the result, or zero if there wasn't a match
        return rulesetId;
    }

    public Map<Integer, ArrayList<String>> getRules (int rulesetId) {
        int ruleId;
        // map for rule_id and conditions
        Map<Integer, ArrayList<String>> idAndConditions = new HashMap<>();

        String getRulesSQL = "SELECT * FROM rules WHERE ruleset_id = ? ORDER BY rule_id";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(getRulesSQL)){
            preparedStatement.setInt(1, rulesetId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // adds the id to the variable
                ruleId = rs.getInt("rule_id");
                // new list for every rule's conditions
                ArrayList<String> conditions = new ArrayList<>();
                // adds the row to the list
                conditions.add(rs.getString("when_condition"));
                conditions.add(rs.getString("is1_condition"));
                conditions.add(rs.getString("and1_condition"));
                conditions.add(rs.getString("is2_condition"));
                conditions.add(rs.getString("and2_condition"));
                conditions.add(rs.getString("is3_condition"));
                conditions.add(rs.getString("and3_condition"));
                conditions.add(rs.getString("is4_condition"));

                conditions.add(rs.getString("then_action"));
                // adds the id and the list to the map
                idAndConditions.put(ruleId, conditions);
            }
        } catch (SQLException e) {
            System.out.println("Error getting rules: " + e);
        }
        return idAndConditions;
    }

    public ArrayList<Integer> getRuleId (int rulesetId) {
        // list to hold rule_ids
        ArrayList<Integer> ruleIdList = new ArrayList<>();

        String getRulesSQL = "SELECT rule_id FROM rules WHERE ruleset_id = ? ORDER BY rule_id";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(getRulesSQL)){
            preparedStatement.setInt(1, rulesetId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // adds the rule_id to the list
                ruleIdList.add(rs.getInt("rule_id"));

            }
        } catch (SQLException e) {
            System.out.println("Error getting rule_ids: " + e);
        }
        return ruleIdList;
    }

    public ArrayList<String> getRuleActions (int rulesetId) {
        ArrayList<String> thenActions = new ArrayList<>();
        // gets the actions from a ruleset
        String getRuleActionsSQL = "SELECT then_action FROM rules WHERE ruleset_id = ? ORDER BY rule_id";
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
        PreparedStatement preparedStatement = connection.prepareStatement(getRuleActionsSQL)) {
            // sets the question mark as the parameter
             preparedStatement.setInt(1, rulesetId);
             ResultSet rs = preparedStatement.executeQuery();
             // loops through the rows
             while (rs.next()) {
                 // gets the action from the table into the String variable
                 String then = rs.getString("then_action");
                 // adds the variable to the arrayList
                 thenActions.add(then);
             }
        } catch (SQLException e) {
            System.out.println("Error getting rule actions: " + e);
        }
        // returns the arrayList
        return thenActions;
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
                // gets the rule id from the row
                int rule = rs.getInt("rule_id");
                // adds the rule_id to the list (just for counting below)
                rulesForCountList.add(rule);
            }
            // increases the howManyRules variable by 1 for every rule in the list
            int checkLength = 0;
            while (checkLength < rulesForCountList.size()) {
                howManyRules++;
                checkLength++;
            }
            // changes the rule_count column value to the counted number above
            PreparedStatement updateStatement = connection.prepareStatement(updateRuleCountSQL);
            updateStatement.setInt(1, howManyRules);
            updateStatement.setInt(2, rId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error counting rules: " + e);
        }
    }

    public int getUserRulesetAmount (String userEmail) {
        // gets ruleset info by matching the emails
        String getRulesetAmountSQL = "SELECT * FROM rulesets WHERE email_address = ?";
        int numberOfRulesets = 0;
        try (Connection connection = DriverManager.getConnection(databaseURL, user, upass);
             PreparedStatement preparedStatement = connection.prepareStatement(getRulesetAmountSQL)) {
            preparedStatement.setString(1, userEmail);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                numberOfRulesets++;
            }
        } catch (SQLException e) {
            System.out.println("Error counting rulesets: " + e);
        }
        return numberOfRulesets;
    }
}