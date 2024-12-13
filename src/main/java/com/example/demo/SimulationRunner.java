package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

import static com.example.demo.CreateUserAccountController.userEmail;
import static com.example.demo.DashboardController.*;
import static com.example.demo.RulesetsDashboard.*;

@SuppressWarnings("IfCanBeSwitch")
public class SimulationRunner implements Initializable {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public Button backButton, runButton;

    public Label passOrFailLabel;

    public ComboBox<String> layoutName_CB, rulesetName_CB, maxAttempts_CB;

    public ListView<String> listView;

    public GridPane gridForRunner;

    public HBox
            hbox_00, hbox_10, hbox_20, hbox_30, hbox_40,
            hbox_01, hbox_11, hbox_21, hbox_31, hbox_41,
            hbox_02, hbox_12, hbox_22, hbox_32, hbox_42,
            hbox_03, hbox_13, hbox_23, hbox_33, hbox_43,
            hbox_04, hbox_14, hbox_24, hbox_34, hbox_44;

    // arrayList to put the HBoxes into an arrayList<Hbox>
    public ArrayList<HBox> arrayListForHboxes = new ArrayList<>();

    // stuff for robot
    public Rectangle robot;
    public int robotRow = 0;
    public int robotCol = 0;

    // array-array for the coordinates
    public String[][] arrayOfLayoutRows;
    // to hold the rules as strings
    public ArrayList<String> rules;
    // arrayList for the robot's steps
    public ArrayList<String> stepsLog = new ArrayList<>();

    public void initialize (URL url, ResourceBundle resourceBundle) {
        // variable for the layouts' index
        int l = 0;
        // puts the layout names into an arrayList
        ArrayList<String> layoutNames = sqlConfiguration.getLayoutNamesFromTable(userEmail);
        for (String lName: layoutNames) {
            System.out.println("Layout " + (l + 1) + " name: " + lName);
            l++;
        }

        // variable for the rulesets' index
        int r = 0;
        // rulesets are put into this arrayList
        ArrayList<String> rulesetNames = sqlConfiguration.getRulesetNamesFromTable(userEmail);
        for (String rName: rulesetNames) {
            System.out.println("Ruleset " + (r + 1)+ " name: " + rName);
            r++;
        }

        //TODO add functional variables that carry the names of previously made layouts
        ObservableList<String> layoutNameOptions = FXCollections.observableArrayList(layoutNames);

        //TODO add functional variables that carry the names of previously made rulesets
        ObservableList<String> rulesetNameOptions = FXCollections.observableArrayList(rulesetNames);

        // list of max moves the robot can take
        ObservableList<String> maxAttempts =FXCollections.observableArrayList(
                "10", "20", "30", "40", "50", "100", "150", "200");

        // adds options to comboBoxes
        layoutName_CB.getItems().addAll(layoutNameOptions);
        rulesetName_CB.getItems().addAll(rulesetNameOptions);
        maxAttempts_CB.getItems().addAll(maxAttempts);

        // adds the panes to an arraylist
        arrayListForHboxes.addAll(Arrays.asList(
                hbox_00, hbox_10, hbox_20, hbox_30, hbox_40,
                hbox_01, hbox_11, hbox_21, hbox_31, hbox_41,
                hbox_02, hbox_12, hbox_22, hbox_32, hbox_42,
                hbox_03, hbox_13, hbox_23, hbox_33, hbox_43,
                hbox_04, hbox_14, hbox_24, hbox_34, hbox_44));
    }

    public void useLayout(String layoutName) {
        System.out.println("\n\tUSE LAYOUT");
        // checks if comboBox isn't null
        if (layoutName != null) {
            // gets layout id
            layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
            System.out.println("Layout ID: " + layoutId);
            System.out.println("Layout name: " + layoutName);

            // gets robot starting direction
            robotDirection = sqlConfiguration.getRobotDirection(layoutId);
            System.out.println("Robot direction: " + robotDirection);

            // gets layout grid data
            layoutData = (ArrayList<String>) sqlConfiguration.getUserLayouts(userEmail).get(layoutId);
            System.out.println("Grid data: " + layoutData);

            // initializes the String[][]  with elements from the ArrayList
            arrayOfLayoutRows = new String[][] {
                    // first row
                    {layoutData.getFirst(), layoutData.get(1), layoutData.get(2), layoutData.get(3), layoutData.get(4)},
                    // second row
                    {layoutData.get(5), layoutData.get(6), layoutData.get(7), layoutData.get(8), layoutData.get(9)},
                    // third row
                    {layoutData.get(10), layoutData.get(11), layoutData.get(12), layoutData.get(13), layoutData.get(14)},
                    // fourth row
                    {layoutData.get(15), layoutData.get(16), layoutData.get(17), layoutData.get(18), layoutData.get(19)},
                    // fifth row
                    {layoutData.get(20), layoutData.get(21), layoutData.get(22), layoutData.get(23), layoutData.get(24)}
            };

            System.out.println("Layout in grid format:\n");
            // prints the array-array
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    System.out.print(arrayOfLayoutRows[r][c] + ", ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Make sure to choose a layout to use.");
        }
    }

    public void useRuleset(String rulesetNamePara) {
        System.out.println("\n\tUSE RULESET");
        // checks if the comboBox has a value
        if (rulesetNamePara != null) {
            // updates the ruleset name static variable
            rulesetName = rulesetNamePara;
            // gets ruleset id
            rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
            System.out.println("Ruleset ID: " + rulesetId);
            System.out.println("Ruleset name: " + rulesetName);
        } else {
            System.out.println("Make sure to choose a ruleset to run.");
        }
    }

    public ArrayList<String> separateRuleConditions() {
        // will have a length of 24 pairs (direction:space) at most (4 pair conditions for 6 rules)
        ArrayList<String> allRuleConditionsAndAction = new ArrayList<>();

        // gets the rule ids of the ruleset
        ArrayList<Integer> rulesOfRuleset = sqlConfiguration.getRuleId(rulesetId);
        ArrayList<String> ruleActions = sqlConfiguration.getRuleActions(rulesetId);
        // loops through the amount rule ids
        for (int i = 0; i < rulesOfRuleset.size(); i++) {
            //
            int ruleId = rulesOfRuleset.get(i);
            // gets the arrayList value from the map using the rule id key
            ArrayList<String> condition = sqlConfiguration.getRules(rulesetId).get(ruleId);
            // gets the rule's THEN action to add later
            String action = ruleActions.get(i);

            // checks if the condition is null
            if (condition.getFirst() != null & condition.get(1) != null & condition.get(2) != null & condition.get(3) != null
                    & condition.get(4) != null & condition.get(5) != null & condition.get(6) != null & condition.get(7) != null) {
                // adds all pairs to the arrayList
                allRuleConditionsAndAction.add(
                        condition.getFirst() + ":" + condition.get(1) + "," + condition.get(2) + ":" + condition.get(3) + "," +
                        condition.get(4) + ":" + condition.get(5) + "," + condition.get(6) + ":" + condition.get(7)
                                + "|" + action);

            } else if (condition.getFirst() != null & condition.get(1) != null & condition.get(2) != null & condition.get(3) != null
                    & condition.get(4) != null & condition.get(5) != null & condition.get(6) == null & condition.get(7) == null) {
                // adds 3 pairs to the arrayList
                allRuleConditionsAndAction.add(
                        condition.getFirst() + ":" + condition.get(1) + "," + condition.get(2) + ":" + condition.get(3) + "," +
                        condition.get(4) + ":" + condition.get(5) + "|" + action);

            } else if (condition.getFirst() != null & condition.get(1) != null & condition.get(2) != null & condition.get(3) != null
                    & condition.get(4) == null & condition.get(5) == null & condition.get(6) == null & condition.get(7) == null) {
                // adds 2 pairs to the arrayList
                allRuleConditionsAndAction.add(
                        condition.getFirst() + ":" + condition.get(1) + "," + condition.get(2) + ":" + condition.get(3)
                                + "|" + action);

            } else if (condition.getFirst() != null & condition.get(1) != null & condition.get(2) == null & condition.get(3) == null
                    & condition.get(4) == null & condition.get(5) == null & condition.get(6) == null & condition.get(7) == null) {
                // adds only 1 pair to the arrayList
                allRuleConditionsAndAction.add(condition.getFirst() + ":" + condition.get(1) + "|" + action);
            }
        }
        System.out.println("\n\tSPLIT METHOD\nAll pairs and their action: " + allRuleConditionsAndAction);
        return allRuleConditionsAndAction;
    }

    public void simRunnerExecution() {
        System.out.println("\n\tSIMULATION RUNNER");
        // variable to hold the index of the arrayList
        int index = 0;
        // loops through the rows
        for (int row = 0; row < arrayOfLayoutRows.length; row++) {
            // loops through the columns
            for (int col = 0; col < arrayOfLayoutRows[row].length; col++) {
                // gets the index of both lists
                String typeOfBox = layoutData.get(index);
                HBox hBox = arrayListForHboxes.get(index);

                // sets the color of the pane based on the value of the choiceBox
                if (typeOfBox.equals("Start")) {
                    hBox.setStyle("-fx-background-color: darkSeaGreen;");
                    // sets the robots coordinates to the Start space
                    robotRow = row;
                    robotCol = col;
                    // makes the robot
                    robot = new Rectangle(50, 50, Color.VIOLET);
                    // adds the robot to the grid at the starting space
                    gridForRunner.add(robot, robotCol, robotRow);
                    System.out.println("Robot coordinates: (" + robotRow + ", " + robotCol + ")");
                } else if (typeOfBox.equals("Exit")) {
                    hBox.setStyle("-fx-background-color: IndianRed");
                } else if (typeOfBox.equals("Wall")) {
                    hBox.setStyle("-fx-background-color: Gray");
                } else {
                    hBox.setStyle("-fx-background-color: CadetBlue");
                }
                System.out.print("Normal coordinates: (" + row + ", " + col + ")\t");
                System.out.println("Index: " + index);
                // increases the index by 1
                index++;
            }
        }
    }

    public void moveRobotAutomatically() {
        // checks if the comboBox has a value
        if (maxAttempts_CB.getValue() != null) {
            // sets the variable to the selected max number
            int maxTries = Integer.parseInt(maxAttempts_CB.getValue());
            // to add actions to the ListView
            ObservableList<String> items = FXCollections.observableArrayList();

            // variable to hold the index
            int stepsIndex = 0;
            int loopIndex = 0;
            while (loopIndex < maxTries) {
                if (arrayOfLayoutRows[robotRow][robotCol].equals("Exit")) {
                    System.out.println("Reached the exit!");
                    // prints the steps of the robot
                    for (String step : stepsLog) {
                        // formats the steps into a numbered list
                        items.add((stepsIndex + 1) + ". " + step);
                        stepsIndex++;
                    }
                    // adds the numbered steps/actions to the listView
                    listView.setItems(items);
                    // sets pass or fail label text as PASS
                    passOrFailLabel.setText("PASS");
                    passOrFailLabel.setStyle("--body-text-color: green");
                    passOrFailLabel.setVisible(true);
                    // stop the program
                    return;
                }
                for (String rule : separateRuleConditions()) {
                    System.out.println("Full thing: " + rule);
                    // split whole thing by commas, so it splits the rules into separate lists
                    String[] rules = rule.split(",");

                    // loops through the split rule list
                    for (String part : rules) {
                        // if there's only 1 pair
                        String[] twoParts = part.split(":");

                        String space = twoParts[0];
                        System.out.println("Space: " + space);

                        // if there's 2 pairs
                        // do later

                        // calls checkCondition() to check the space on the grid
                        Map<String, Object> result = checkCondition(space);
                        if (result != null && result.get("spaceType").equals(space)) {
                            String[] secondParts = twoParts[1].split("\\|");

                            String typeOfSpace = secondParts[0];
                            System.out.println("Type of space: " + typeOfSpace);

                            String actionPart = secondParts[1];
                            System.out.println("Action: " + actionPart);

                            // Log the step
                            stepsLog.add("Move: " + actionPart + " | Current Position: (" + robotRow + ", " + robotCol + ")");

                            // moves according to the action
                            executeAction(actionPart);
                            break;
                        }
                    }
                }
                loopIndex++;
            }
            if (!arrayOfLayoutRows[robotRow][robotCol].equals("Exit")) {
                // sets pass or fail label text as FAIL
                passOrFailLabel.setText("PASS");
                passOrFailLabel.setStyle("--body-text-color: red");
                passOrFailLabel.setVisible(true);
            }
        } else {
            System.out.println("Make sure to choose a maximum amount of tries.");
        }
    }

    public Map<String, Object> checkCondition(String space) {
        int row = robotRow;
        int col = robotCol;

        if (isValidCell(row+1, col+1) || isValidCell(row-1, col-1)) {
            if (space.equals("In-front")) {
                if (robotDirection.equals("Front") && isValidCell(row - 1, col)) {
                    row--;
                } else if (robotDirection.equals("Back") && isValidCell(row + 1, col)) {
                    row++;
                } else if (robotDirection.equals("Left") && isValidCell(row, col - 1)) {
                    col--;
                } else if (robotDirection.equals("Right") && isValidCell(row, col + 1)) {
                    col++;
                }
            } else if (space.equals("To the left")) {
                if (robotDirection.equals("Front") && isValidCell(row, col - 1)) {
                    col--;
                } else if (robotDirection.equals("Back") && isValidCell(row, col + 1)) {
                    col++;
                } else if (robotDirection.equals("Left") && isValidCell(row + 1, col)) {
                    row++;
                } else if (robotDirection.equals("Right") && isValidCell(row - 1, col)) {
                    row--;
                }
            } else if (space.equals("To the right")) {
                if (robotDirection.equals("Front") && isValidCell(row, col + 1)) {
                    col++;
                } else if (robotDirection.equals("Back") && isValidCell(row, col - 1)) {
                    col--;
                } else if (robotDirection.equals("Left") && isValidCell(row - 1, col)) {
                    row--;
                } else if (robotDirection.equals("Right") && isValidCell(row + 1, col)) {
                    row++;
                }
            } else if (space.equals("Backwards")) {
                if (robotDirection.equals("Front") && isValidCell(row + 1, col)) {
                    row++;
                } else if (robotDirection.equals("Back") && isValidCell(row - 1, col)) {
                    row--;
                } else if (robotDirection.equals("Left") && isValidCell(row, col + 1)) {
                    col++;
                } else if (robotDirection.equals("Right") && isValidCell(row, col - 1)) {
                    col--;
                }
            }
        }
        // checks if the coordinates aren't out of bounds
        if (isValidCell(row, col)) {
            // map to return the type of space and updated coordinates of the robot
            Map<String, Object> result = new HashMap<>();
            result.put("spaceType", arrayOfLayoutRows[row][col]);
            result.put("row", row);
            result.put("col", col);
            // returns the map
            return result;
        } else {
            // returns null if the space didn't match or is out of bounds
            return null;
        }
    }

    private void executeAction(String action) {
        if (action.equals("Move forward")) {
            moveRobot();
        } else if (action.equals("Turn left")) {
            turnLeft();
        } else if (action.equals("Turn right")) {
            turnRight();
        } else if (action.equals("Turn back")) {
            turnBack();
        }
    }

//    public void myMoveRobotAutomatically () {
//        // checks if the robot is on the Exit space
//        if (arrayOfLayoutRows[robotRow][robotCol].equals("Exit")) {
//            System.out.println("Reached the exit!");
//
//        }
//
//        // checks the space in front of the robot
//        if (robotDirection.equals("Front")) {
//            // if there is a wall to the left
//            if (isWall(robotRow, robotCol + 1)) {
//                turnLeft();
//                // if there is a wall to the right
//            } else if (isWall(robotRow, robotCol - 1)) {
//                turnRight();
//                // if in front is a wall
//            } else if (isWall(robotRow - 1, robotCol)) {
//                moveForward();
//                // if in front is open
//            } else if (isOpen(robotRow - 1, robotCol)) {
//                moveForward();
//                // if in front is the Exit
//            } else if (isExit(robotRow - 1, robotCol)) {
//                moveForward();
//            }
//            // checks space behind the robot
//        } else if (robotDirection.equals("Back")) {
//            // if there's a wall to the right
//            if (isWall(robotRow + 1, robotCol)) {
//                turnLeft();
//                // if there's a wall to the left
//            } else if (isWall(robotRow - 1, robotCol)) {
//                turnRight();
//                // if it's open in front
//            } else if (isOpen(robotRow - 1, robotCol)) {
//                moveForward();
//                // if it's open behind
//            } else if (isOpen(robotRow + 1, robotCol)) {
//                turnBack();
//            }
//            // checks the space to the left of the robot
//        } else if (robotDirection.equals("Left")) {
//            if (isWall(robotRow, robotCol - 1)) {
//                turnLeft();
//            } else if (isOpen(robotRow, robotCol - 1)) {
//                moveForward();
//            } else if (isExit(robotRow, robotCol - 1)) {
//                moveForward();
//            }
//            // checks space to the right of the robot
//        } else if (robotDirection.equals("Right")) {
//            if (isWall(robotRow, robotCol + 1)) {
//                turnLeft();
//            } else if (isOpen(robotRow, robotCol + 1)) {
//                moveForward();
//            } else if (isExit(robotRow, robotCol + 1)) {
//                moveForward();
//            }
//        }
//        // pauses to make the robot seem to move
//        try {
//            // Adjust the delay as needed
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }

    public void moveRobot () {
        // moves the robot one space in the direction it's facing
        if (robotDirection.equals("Front")) {
            robotRow--;
        } else if (robotDirection.equals("Back")) {
            robotRow++;
        } else if (robotDirection.equals("Left")) {
            robotCol--;
        } else if (robotDirection.equals("Right")) {
            robotCol++;
        }
        // Update robot position
        GridPane.setRowIndex(robot, robotRow);
        GridPane.setColumnIndex(robot, robotCol);
    }

    public void turnLeft () {
        // turns the robot to the left from any direction
        if (robotDirection.equals("Front")) {
            robotDirection = "Left";
        } else if (robotDirection.equals("Back")) {
            robotDirection = "Right";
        } else if (robotDirection.equals("Left")) {
            robotDirection = "Back";
        } else if (robotDirection.equals("Right")) {
            robotDirection = "Front";
        }
    }
    public void turnRight () {
        // turns the robot to the right from any direction
        if (robotDirection.equals("Front")) {
            robotDirection = "Right";
        } else if (robotDirection.equals("Back")) {
            robotDirection = "Left";
        } else if (robotDirection.equals("Left")) {
            robotDirection = "Front";
        } else if (robotDirection.equals("Right")) {
            robotDirection = "Back";
        }
    }
    public void turnBack () {
        // turns the robot 180 degrees
        if (robotDirection.equals("Front")) {
            robotDirection = "Back";
        } else if (robotDirection.equals("Back")) {
            robotDirection = "Front";
        } else if (robotDirection.equals("Left")) {
            robotDirection = "Right";
        } else if (robotDirection.equals("Right")) {
            robotDirection = "Left";
        }
    }

    private boolean isValidCell ( int row, int col){
        return row >= 0 && row < arrayOfLayoutRows.length &&
                col >= 0 && col < arrayOfLayoutRows[row].length;
    }

//    public void stepLog () {
//        // checks if the comboBox has a value
//        if (maxAttempts_CB.getValue() != null) {
//            // for readability
//            rulesetName = rulesetName_CB.getValue();
//            // updates the ruleset id
//            rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
//
//            // gets the rule actions from database
//            ArrayList<String> ruleActions = sqlConfiguration.getRuleActions(rulesetId);
//
//            // variable to hold the index
//            int i = 0;
//
//            // to add actions to the ListView
//            ObservableList<String> items = FXCollections.observableArrayList();
//
//            // loops through the arrayList
//            for (String action : ruleActions) {
//                // formats the actions into a numbered list
//                items.add((i + 1) + ". " + action);
//                i++;
//            }
//            System.out.println(items);
//            // adds the numbered steps/actions to the listView
//            listView.setItems(items);
//        } else {
//            System.out.println("Make sure to choose a maximum amount of tries.");
//        }
//    }

    public void onRunClick () {
        // if the robot was created
        if (robot != null) {
            robot.setVisible(false);
        }
        useLayout(layoutName_CB.getValue());
        useRuleset(rulesetName_CB.getValue());
        //separateRuleConditions();
        simRunnerExecution();
        moveRobotAutomatically();
        // stepLog();
    }

    public void onBackButtonClick (ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent layoutsDashboardPopUp = loader.load();
        DashboardController layoutDashboard = loader.getController();

        layoutDashboard.makeUserLayoutVisible();

        Stage stageThree = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(layoutsDashboardPopUp, 1920, 1080);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }
}
