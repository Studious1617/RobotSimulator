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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

    public ArrayList<HBox> arrayListForHBoxes = new ArrayList<>();
    // for the HBoxes into an array-array to make it easier to locate
    public HBox[][] arrayArrayForHboxes;

    // stuff for robot
    public Image robotIcon = new Image("file:\\C:\\Users\\Access Point\\Downloads\\bee_icon.png");
    public ImageView imageView;
    public int robotRow = 0;
    public int robotCol = 0;

    public String actionPart;

    // array-array for the coordinates
    public String[][] arrayOfLayoutRows;
    // to hold the rules as strings
    public ArrayList<String> rules;
    // arrayList for the robot's steps
    public ArrayList<String> stepsLog = new ArrayList<>();

    public void initialize (URL url, ResourceBundle resourceBundle) {
        // puts the layout names into an arrayList
        ArrayList<String> layoutNames = sqlConfiguration.getLayoutNamesFromTable(userEmail);

        // rulesets are put into this arrayList
        ArrayList<String> rulesetNames = sqlConfiguration.getRulesetNamesFromTable(userEmail);

        //Functional variables that carry the names of previously made layouts
        ObservableList<String> layoutNameOptions = FXCollections.observableArrayList(layoutNames);

        //Functional variables that carry the names of previously made rulesets
        ObservableList<String> rulesetNameOptions = FXCollections.observableArrayList(rulesetNames);

        // list of max moves the robot can take
        ObservableList<String> maxAttempts =FXCollections.observableArrayList(
                "5", "10", "20", "30", "40", "50", "100", "150", "200");

        // adds options to comboBoxes
        layoutName_CB.getItems().addAll(layoutNameOptions);
        rulesetName_CB.getItems().addAll(rulesetNameOptions);
        maxAttempts_CB.getItems().addAll(maxAttempts);

        // adds the panes to an arraylist
        arrayListForHBoxes.addAll(Arrays.asList(
                hbox_00, hbox_10, hbox_20, hbox_30, hbox_40,
                hbox_01, hbox_11, hbox_21, hbox_31, hbox_41,
                hbox_02, hbox_12, hbox_22, hbox_32, hbox_42,
                hbox_03, hbox_13, hbox_23, hbox_33, hbox_43,
                hbox_04, hbox_14, hbox_24, hbox_34, hbox_44));

        // initializes the HBox[][]  with elements from the ArrayList
        arrayArrayForHboxes = new HBox[][] {
                // first row
                {arrayListForHBoxes.get(0), arrayListForHBoxes.get(1), arrayListForHBoxes.get(2), arrayListForHBoxes.get(3), arrayListForHBoxes.get(4)},
                // second row
                {arrayListForHBoxes.get(5), arrayListForHBoxes.get(6), arrayListForHBoxes.get(7), arrayListForHBoxes.get(8), arrayListForHBoxes.get(9)},
                // third row
                {arrayListForHBoxes.get(10), arrayListForHBoxes.get(11), arrayListForHBoxes.get(12), arrayListForHBoxes.get(13), arrayListForHBoxes.get(14)},
                // fourth row
                {arrayListForHBoxes.get(15), arrayListForHBoxes.get(16), arrayListForHBoxes.get(17), arrayListForHBoxes.get(18), arrayListForHBoxes.get(19)},
                // fifth row
                {arrayListForHBoxes.get(20), arrayListForHBoxes.get(21), arrayListForHBoxes.get(22), arrayListForHBoxes.get(23), arrayListForHBoxes.get(24)}
        };
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
//        System.out.println("\n\tSPLIT METHOD\nAll pairs and their action: " + allRuleConditionsAndAction);
        return allRuleConditionsAndAction;
    }

    public void simRunnerExecution() {
        useLayout(layoutName_CB.getValue());
        // variable to hold the index of the arrayList
        int index = 0;

        // loops through the rows
        for (int row = 0; row < arrayOfLayoutRows.length; row++) {
            // loops through the columns
            for (int col = 0; col < arrayOfLayoutRows[row].length; col++) {
                // gets the index of both lists
                String typeOfBox = layoutData.get(index);
                HBox hBox = arrayArrayForHboxes[row][col];


                // sets the color of the pane based on the value of the choiceBox
                if (typeOfBox.equals("Start")) {
                    hBox.setStyle("-fx-background-color: darkSeaGreen;");
                    // sets the robots coordinates to the Start space
                    robotRow = row;
                    robotCol = col;
                    // deletes the robot if was already created
                    if (imageView != null) {
                        gridForRunner.getChildren().remove(imageView);
                    }
                    // something to hold the image
                    imageView = new ImageView();
                    // adds the icon to the imageView
                    imageView.setImage(robotIcon);
                    imageView.setFitWidth(80);
                    imageView.setFitHeight(80);
                    // adds the robot to the grid at the starting space
                    gridForRunner.add(imageView, robotCol, robotRow);
                } else if (typeOfBox.equals("Exit")) {
                    hBox.setStyle("-fx-background-color: IndianRed");
                } else if (typeOfBox.equals("Wall")) {
                    hBox.setStyle("-fx-background-color: Gray");
                } else {
                    hBox.setStyle("-fx-background-color: White");
                }
                // increases the index by 1
                index++;
            }
        }
    }

    public boolean applyRule(String ruleToTest) {
        // split whole thing by commas, so it splits the rules into separate lists
        String[] rules = ruleToTest.split(",");

        // loops through the split rule list
        for (String part : rules) {
            // if there's only 1 pair  (d:s|a)
            // splits the rule into a list with 2 values (d, b|a)
            String[] twoParts = part.split(":");
            // first one is the direction
            String direction = twoParts[0];
            System.out.println("Direction: " + direction);

            // splits the second value as well      (b, a)
            String[] secondParts = twoParts[1].split("\\|");
            // first value of the split value is the block
            String typeOfSpace = secondParts[0];
            System.out.println("Type of space: " + typeOfSpace);

            // the action is the second value of the split value
            actionPart = secondParts[1];
            System.out.println("Action: " + actionPart);

            // TODO more pairs
            Map<String, Object> result = checkCondition(direction, actionPart);

            if (result.get("action") != null) {
                // returns true if an action was taken
                return true;
            }
        }
        // returns false if no move was made
        return false;
    }

    public void moveRobotAutomaticallyNewVersion() {
        System.out.println("\n\tMOVE ROBOT");
        // checks if the comboBox has a value
        if (maxAttempts_CB.getValue() != null) {
            // sets the variable to the selected max number
            int maxTries = Integer.parseInt(maxAttempts_CB.getValue());
            System.out.println("Max tries: " + maxTries);
            // to add actions to the ListView
            ObservableList<String> items = FXCollections.observableArrayList();

            // variable to hold the index
            int stepsIndex = 0;
            int loopIndex = 0;
            while (!arrayOfLayoutRows[robotRow][robotCol].equals("Exit") && loopIndex < maxTries) {
                boolean moved = false;

                for (String rule : separateRuleConditions()) {
                    if (applyRule(rule)) {
                        moved = true;
                        break; // Exit the loop if a move was made
                    }
                }
                if (moved) {
                    // logs the move
                    stepsLog.add(actionPart);
                    System.out.println("Move: " + actionPart + " | Current Position: (" + robotRow + ", " + robotCol + ")\n");
                }

                loopIndex++;
                // checks if the robot completed the layout
                if (arrayOfLayoutRows[robotRow][robotCol].equals("Exit")) {
                    System.out.println("Reached the exit!");
                    // prints the steps of the robot
                    for (String step : stepsLog) {
                        // formats the steps into a numbered list
                        items.add((stepsIndex + 1) + ". " + step);
                        stepsIndex++;
                    }
                    // adds the final step: reaching the Exit
                    items.add(stepsIndex + ". Exit reached.");
                    // adds the numbered steps/actions to the listView
                    listView.setItems(items);
                    // sets pass or fail label text as PASS
                    passOrFailLabel.setText("PASS");
                    passOrFailLabel.setStyle("-fx-text-fill: Green");
                    passOrFailLabel.setVisible(true);
                    System.out.println("Layout completed in " + loopIndex + " attempts/moves.");
                    // stop the program
                    break;
                }

                if (loopIndex == maxTries) {
                    System.out.println("\tBottom of WHILE loop");
                    System.out.println("Loop index: " + loopIndex + "\tMax tries: " + maxTries);
                    // sets pass or fail label text as FAIL
                    passOrFailLabel.setText("FAIL");
                    passOrFailLabel.setStyle("-fx-text-fill: red");
                    passOrFailLabel.setVisible(true);

                    listView.setItems(items);
                    break;
                }
            }
        }
    }
// check each command. if all are true, do action
    public Map<String, Object> checkCondition(String direction, String whatToDo) {
        if (direction.equals("In-front")) {
            whatToDo = checkCommand(whatToDo);
        }
        else if (direction.equals("To the left")) {
            whatToDo = checkCommand(whatToDo);
        }
        else if (direction.equals("To the right")) {
            whatToDo = checkCommand(whatToDo);
        }
        else if (direction.equals("Backwards")) {
            whatToDo = checkCommand(whatToDo);
        }

        // checks if the coordinates aren't out of bounds
        if (isValidCell(robotRow, robotCol)) {
            // map to return the type of space and updated coordinates of the robot
            Map<String, Object> result = new HashMap<>();
            result.put("spaceType", arrayOfLayoutRows[robotRow][robotCol]);
            result.put("action", whatToDo);
            result.put("row", robotRow);
            result.put("col", robotCol);

            // returns the map
            return result;
        } else {
            // returns null if the space didn't match or is out of bounds
            return null;
        }
    }
//
    public String checkCommand(String whatToDo) {
        // needs to check the block
        if (robotDirection.equals("Front")) {
            // potential WALL commands
            if (arrayOfLayoutRows[robotRow - 1][robotCol].equals("Wall")) {
                if (whatToDo.equals("Turn left")) {
                    robotDirection = "Left";
                    moveRobot();
                }
                else if (whatToDo.equals("Turn right")) {
                    robotDirection = "Right";
                    moveRobot();
                } else if (whatToDo.equals("Turn back")) {
                    robotDirection = "Back";
                    moveRobot();
                } else if (whatToDo.equals("Move forward")) {
                    whatToDo = null;
                }
            }
            else {
                if (whatToDo.equals("Turn left")) {
                    robotDirection = "Left";
                    moveRobot();
                } else if (whatToDo.equals("Turn right")) {
                    robotDirection = "Right";
                    moveRobot();
                } else if (whatToDo.equals("Turn back")) {
                    robotDirection = "Back";
                    moveRobot();
                } else if (whatToDo.equals("Move forward")) {
                    // checks if the block to the front is within the grid
                    if (isValidCell(robotRow - 1, robotCol)) {
                        moveRobot();
                    } else {
                        whatToDo = null;
                    }
                }
            }
            // potential OPEN commands
//            else if (arrayOfLayoutRows[robotRow - 1][robotCol].equals("Open")) {
//                if (whatToDo.equals("Turn left")) {
//                    robotDirection = "Left";
//                    moveRobot();
//                } else if (whatToDo.equals("Turn right")) {
//                    robotDirection = "Right";
//                    moveRobot();
//                } else if (whatToDo.equals("Turn back")) {
//                    robotDirection = "Back";
//                    moveRobot();
//                } else if (whatToDo.equals("Move forward")) {
//                    // checks if the block in front/up is valid
//                    if (isValidCell(robotRow - 1, robotCol)) {
//                        moveRobot();
//                    } else {
//                        whatToDo = null;
//                    }
//                }
//            }
//            // potential START commands
//            else if (arrayOfLayoutRows[robotRow - 1][robotCol].equals("Start")) {
//                if (whatToDo.equals("Turn left")) {
//                    robotDirection = "Left";
//                    moveRobot();
//                } else if (whatToDo.equals("Turn right")) {
//                    robotDirection = "Right";
//                    moveRobot();
//                } else if (whatToDo.equals("Turn back")) {
//                    robotDirection = "Back";
//                    moveRobot();
//                } else if (whatToDo.equals("Move forward")) {
//                    moveRobot();
//                }
//            }
//            // potential EXIT commands
//            else if (arrayOfLayoutRows[robotRow - 1][robotCol].equals("Exit")) {
        }
        else if (robotDirection.equals("Back")) {
            // potential WALL commands
            if (arrayOfLayoutRows[robotRow + 1][robotCol].equals("Wall")) {
                if (whatToDo.equals("Turn left")) {
                    robotDirection = "Left";
                    moveRobot();
                } else if (whatToDo.equals("Turn right")) {
                    robotDirection = "Right";
                    moveRobot();
                } else if (whatToDo.equals("Turn back")) {
                    robotDirection = "Back";
                    moveRobot();
                } else if (whatToDo.equals("Move forward")) {
                        whatToDo = null;
                }
            }
            else {
                if (whatToDo.equals("Turn left")) {
                    robotDirection = "Left";
                    moveRobot();
                } else if (whatToDo.equals("Turn right")) {
                    robotDirection = "Right";
                    moveRobot();
                } else if (whatToDo.equals("Turn back")) {
                    robotDirection = "Back";
                    moveRobot();
                } else if (whatToDo.equals("Move forward")) {
                    // checks if the block to the back is within the grid
                    if (isValidCell(robotRow + 1, robotCol)) {
                        moveRobot();
                    } else {
                        whatToDo = null;
                    }
                }
            }
        }
        else if (robotDirection.equals("Left")) {
            // potential WALL commands
            if (arrayOfLayoutRows[robotRow][robotCol - 1].equals("Wall")) {
                if (whatToDo.equals("Turn left")) {
                    robotDirection = "Left";
                    moveRobot();
                } else if (whatToDo.equals("Turn right")) {
                    robotDirection = "Right";
                    moveRobot();
                } else if (whatToDo.equals("Turn back")) {
                    robotDirection = "Back";
                    moveRobot();
                } else if (whatToDo.equals("Move forward")) {
                    whatToDo = null;

                }
            }
            else {
                if (whatToDo.equals("Turn left")) {
                    robotDirection = "Left";
                    moveRobot();
                } else if (whatToDo.equals("Turn right")) {
                    robotDirection = "Right";
                    moveRobot();
                } else if (whatToDo.equals("Turn back")) {
                    robotDirection = "Back";
                    moveRobot();
                } else if (whatToDo.equals("Move forward")) {
                    // checks if the block to the left is within the grid
                    if (isValidCell(robotRow, robotCol - 1)) {
                        moveRobot();
                    } else {
                        whatToDo = null;
                    }
                }

            }
        }
        else if (robotDirection.equals("Right")) {
            // potential WALL commands
            if (arrayOfLayoutRows[robotRow][robotCol + 1].equals("Wall")) {
                if (whatToDo.equals("Turn left")) {
                    robotDirection = "Left";
                    moveRobot();
                } else if (whatToDo.equals("Turn right")) {
                    robotDirection = "Right";
                    moveRobot();
                } else if (whatToDo.equals("Turn back")) {
                    robotDirection = "Back";
                    moveRobot();
                } else if (whatToDo.equals("Move forward")) {
                    whatToDo = null;
                }
            }
            else {
                if (whatToDo.equals("Turn left")) {
                    robotDirection = "Left";
                    moveRobot();
                } else if (whatToDo.equals("Turn right")) {
                    robotDirection = "Right";
                    moveRobot();
                } else if (whatToDo.equals("Turn back")) {
                    robotDirection = "Back";
                    moveRobot();
                } else if (whatToDo.equals("Move forward")) {
                    // checks if the block to the right is within the grid
                    if (isValidCell(robotRow, robotCol + 1)) {
                        moveRobot();
                    } else {
                        whatToDo = null;
                    }
                }
            }
        }
        return whatToDo;
    }

    public void moveRobot () {
        // updates the robot's coordinates
        if (robotDirection.equals("Front")) {
            robotRow--;
        } else if (robotDirection.equals("Back")) {
            robotRow++;
        } else if (robotDirection.equals("Left")) {
            robotCol--;
        } else if (robotDirection.equals("Right")) {
            robotCol++;
        }

        // moves robot to new coordinates
        GridPane.setRowIndex(imageView, robotRow);
        GridPane.setColumnIndex(imageView, robotCol);

        // checks if the space is Open to color it
        if (arrayOfLayoutRows[robotRow][robotCol].equals("Open")) {
            // changes the color of the hBox where the robot moved to
            HBox newRobotSpace = arrayArrayForHboxes[robotRow][robotCol];
            newRobotSpace.setStyle("-fx-background-color: skyBlue");
        }
//        // pauses to make the robot seem to move
//        try {
//            // Adjust the delay as needed
//            Thread.sleep(500);
//
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
    }

    // rotates the robot icon, but it does all the steps at once so kind of pointless
    public void turnLeft () {
        // turns the robot to the left
        imageView.setRotate(270);
    }
    public void turnRight () {
        // turns the robot to the right
        imageView.setRotate(90);
    }
    public void turnBack () {
        // turns the robot 180 degrees
        imageView.setRotate(180);
    }

    private boolean isValidCell ( int row, int col) {
        return row >= 0 && row < arrayOfLayoutRows.length &&
                col >= 0 && col < arrayOfLayoutRows[row].length;
    }

    public void stepLog (ArrayList<String> ruleActions) {
        // checks if the comboBox has a value
        if (maxAttempts_CB.getValue() != null) {
            // variable to hold the index
            int i = 0;

            // to add actions to the ListView
            ObservableList<String> items = FXCollections.observableArrayList();

            // loops through the arrayList
            for (String action : ruleActions) {
                // formats the actions into a numbered list
                items.add((i + 1) + ". " + action);
                i++;
            }
            System.out.println(items);
            // adds the numbered steps/actions to the listView
            listView.setItems(items);
        } else {
            System.out.println("Make sure to choose a maximum amount of tries.");
        }
    }

    public void onRunClick () {
        useRuleset(rulesetName_CB.getValue());
        moveRobotAutomaticallyNewVersion();
        stepLog(stepsLog);
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
