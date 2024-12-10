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
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.demo.CreateUserAccountController.userEmail;
import static com.example.demo.DashboardController.*;
import static com.example.demo.RulesetsDashboard.rulesetId;
import static com.example.demo.RulesetsDashboard.rulesetName;

public class SimulationRunner implements Initializable {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public Button backButton, runButton;

    public ComboBox<String> layoutName_CB, rulesetName_CB, maxAttempts_CB;

    public ListView<String> listView;

    public HBox
            hbox_00, hbox_01, hbox_02, hbox_03, hbox_04,
            hbox_10, hbox_11, hbox_12, hbox_13, hbox_14,
            hbox_20, hbox_21, hbox_22, hbox_23, hbox_24,
            hbox_30, hbox_31, hbox_32, hbox_33, hbox_34,
            hbox_40, hbox_41, hbox_42, hbox_43, hbox_44;

    // arrayList to put the HBoxes into an arrayList<Hbox>
    public ArrayList<HBox> arrayListForHboxes = new ArrayList<>();

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
        ArrayList<String> rulesetNames = sqlConfiguration.getLayoutNamesFromTable(userEmail);
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

        //layoutName = layoutNameLabel.getText();
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        System.out.println("Layout ID: " + layoutId);
        System.out.println("Layout name: " + layoutName);

        // gets layout grid data
        layoutData = (ArrayList<String>) sqlConfiguration.getUserLayouts(userEmail).get(layoutId);
        System.out.println("Grid data: " + layoutData);
        System.out.println("Ruleset name: " + rulesetName);

        // adds the panes to an arraylist
        arrayListForHboxes.addAll(Arrays.asList(
                hbox_00, hbox_01, hbox_02, hbox_03, hbox_04,
                hbox_10, hbox_11, hbox_12, hbox_13, hbox_14,
                hbox_20, hbox_21, hbox_22, hbox_23, hbox_24,
                hbox_30, hbox_31, hbox_32, hbox_33, hbox_34,
                hbox_40, hbox_41, hbox_42, hbox_43, hbox_44));

        // loops through the arraylist
        for (int i = 0; i < arrayListForHboxes.size(); i++) {
            // gets the index of both lists
            String typeOfBox = layoutData.get(i);
            HBox hBox = arrayListForHboxes.get(i);

            // sets the color of the pane based on the value of the choiceBox
            if (typeOfBox.equals("Start")) {
                hBox.setStyle("-fx-background-color: darkgreen;");
            } else if (typeOfBox.equals("Exit")) {
                hBox.setStyle("-fx-background-color: red");
            } else if (typeOfBox.equals("Wall")) {
                hBox.setStyle("-fx-background-color: grey");
            } else {
                hBox.setStyle("-fx-background-color: blue");
            }
        }

//        Map<Integer, List<String>> layoutGridData = getLayoutData(layoutName);
//        Map<Integer, ArrayList<String>> selectedRulesetRules = getRulesetRules(rulesetName);

        // makes the saved rulesetName appear on the choiceBox
//        layoutName_CB.setValue(layoutName);
//        rulesetName_CB.setValue(rulesetName);
    }

    public Map<Integer, List<String>> getLayoutData(String layoutName) {

        Map<Integer, List<String>> selectedLayoutData = new HashMap<>();

        if (layoutName != null) {
            selectedLayoutData = sqlConfiguration.getUserLayouts(userEmail);
        } else {
            System.out.println("No layouts created.\nPlease make one before running the simulation.");
        }

        return selectedLayoutData;
    }

    public Map<Integer, ArrayList<String>> getRulesetRules(String rulesetName) {

        Map<Integer, ArrayList<String>> selectedRulesetRules = new HashMap<>();
        int rulesetId;

        if (rulesetName != null) {
            rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
            selectedRulesetRules = sqlConfiguration.getRules(rulesetId);
        } else {
            System.out.println("No rulesets created.\nPlease make one before running the simulation.");
        }

        return selectedRulesetRules;
    }

    public void maxAttempts() {}

    public void simRunnerExecution() {}

    public void stepLog() {
        // checks if the comboBox has a value
        if (rulesetName_CB.getValue() != null) {
            // for readability
            rulesetName = rulesetName_CB.getValue();
            // updates the ruleset id
            rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);

            // gets the rule actions from database
            ArrayList<String> ruleActions = sqlConfiguration.getRuleActions(rulesetId);

            // Adds actions to the ListView
            ObservableList<String> items = FXCollections.observableArrayList();

            // variable to hold the index
            int i = 0;
            // variable to check completion
            boolean didRobotCompleteLayout = true;
            // loops through the arrayList
            for (String action : ruleActions) {
                // formats the actions into a numbered list
                items.add((i + 1) + ". " + action);
                i++;
//            if (!didRobotCompleteLayout) {
//                // somehow update it
//            } else {
//                System.out.println("Layout completed.");
//            }
            }
            System.out.println(items);
            // adds the numbered steps/actions to the listView
            listView.setItems(items);
        } else {
            System.out.println("Make sure to choose a ruleset to run.");
        }
    }

    public void onRunClick() {
        stepLog();
    }

    @FXML
    public void onBackButtonClick(ActionEvent e) throws Exception {
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
