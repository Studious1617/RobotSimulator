package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Rule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.demo.CreateUserAccountController.userEmail;
import static com.example.demo.DashboardController.layoutData;
import static com.example.demo.DashboardController.layoutId;
import static com.example.demo.DashboardController.layoutName;
import static com.example.demo.DashboardController.robotDirection;
import static com.example.demo.RulesetsDashboard.rulesetId;
import static com.example.demo.RulesetsDashboard.rulesetName;

public class SimulationRunner {
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

    // arraylist to chang Hbox[] array to arraylist<Hbox>
    public ArrayList<HBox> arrayListForHboxes = new ArrayList<>();

    public void initialize (URL url, ResourceBundle resourceBundle) {
        String layout1Name = String.valueOf(DashboardController.layoutNameLabel1);
        System.out.println("Layout 1 name: " + layout1Name);
        String layout2Name = String.valueOf(DashboardController.layoutNameLabel2);
        System.out.println("Layout 2 name: " + layout2Name);
        String layout3Name = String.valueOf(DashboardController.layoutNameLabel3);
        System.out.println("Layout 3 name: " + layout3Name);
        String layout4Name = String.valueOf(DashboardController.layoutNameLabel4);
        System.out.println("Layout 4 name: " + layout4Name);
        String layout5Name = String.valueOf(DashboardController.layoutNameLabel5);
        System.out.println("Layout 5 name: " + layout5Name);

        String ruleset1Name = String.valueOf(RulesetsDashboard.rulesetNameLabel1);
        System.out.println("Ruleset 1 name: " + ruleset1Name);
        String ruleset2Name = String.valueOf(RulesetsDashboard.rulesetNameLabel2);
        System.out.println("Ruleset 2 name: " + ruleset2Name);
        String ruleset3Name = String.valueOf(RulesetsDashboard.rulesetNameLabel3);
        System.out.println("Ruleset 3 name: " + ruleset3Name);
        String ruleset4Name = String.valueOf(RulesetsDashboard.rulesetNameLabel4);
        System.out.println("Ruleset 4 name: " + ruleset4Name);
        String ruleset5Name = String.valueOf(RulesetsDashboard.rulesetNameLabel5);
        System.out.println("Ruleset 5 name: " + ruleset5Name);


        //TODO add functional variables that carry the names of previously made layouts
        ObservableList<String> layoutOptions = FXCollections.observableArrayList(layout1Name,
                layout2Name, layout3Name, layout4Name, layout5Name);

        //TODO add functional variables that carry the names of previously made rulesets
        ObservableList<String> rulesetOptions = FXCollections.observableArrayList(ruleset1Name,
                ruleset2Name, ruleset3Name, ruleset4Name, ruleset5Name);

        ObservableList<String> maxAttempts =FXCollections.observableArrayList(
                "10", "20", "30", "40", "50", "100", "150", "200");



        //layoutName = layoutNameLabel.getText();
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        // gets layout grid data
        layoutData = (ArrayList<String>) sqlConfiguration.getUserLayouts(userEmail).get(layoutId);

        // adds the panes to an arraylist
        arrayListForHboxes.addAll(Arrays.asList(
                hbox_00, hbox_01, hbox_02, hbox_03, hbox_04,
                hbox_10, hbox_11, hbox_12, hbox_13, hbox_14,
                hbox_20, hbox_21, hbox_22, hbox_23, hbox_24,
                hbox_30, hbox_31, hbox_32, hbox_33, hbox_34,
                hbox_40, hbox_41, hbox_42, hbox_43, hbox_44));

        // loops through the arraylist
        for (int i = 0; i < arrayListForHboxes.size(); i++){
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


        Map<Integer, ArrayList<String>> selectedRulesetRules = getRulesetRules(ruleset1Name, ruleset2Name, ruleset3Name, ruleset4Name, ruleset5Name);
        Map<Integer, List<String>> layoutData = getLayoutData(layout1Name, layout2Name, layout3Name, layout4Name, layout5Name);


        rulesetName_CB.getItems().addAll(rulesetOptions);
        layoutName_CB.getItems().addAll(layoutOptions);
        // makes the saved rulesetName appear on the choiceBox
        rulesetName_CB.setValue(rulesetName);
        layoutName_CB.setValue(layoutName);
    }

    public Map<Integer, List<String>> getLayoutData(String layout1Name, String layout2Name, String layout3Name, String layout4Name, String layout5Name) {
        Map<Integer, List<String>> selectedLayoutData = new HashMap<>();

        if (layout1Name != null) {
            selectedLayoutData = SQLConfiguration.getUserLayouts(userEmail);
        } else if (layout2Name != null) {
            selectedLayoutData = SQLConfiguration.getUserLayouts(userEmail);
        } else if (layout3Name != null){
            selectedLayoutData = SQLConfiguration.getUserLayouts(userEmail);
        } else if (layout4Name != null){
            selectedLayoutData = SQLConfiguration.getUserLayouts(userEmail);
        } else if (layout5Name != null){
            selectedLayoutData = SQLConfiguration.getUserLayouts(userEmail);
        }

        return selectedLayoutData;
    }

    public Map<Integer, ArrayList<String>> getRulesetRules(String ruleset1Name, String ruleset2Name, String ruleset3Name, String ruleset4Name, String ruleset5Name) {

        Map<Integer, ArrayList<String>> selectedRulesetRules = new HashMap<>();

        if (ruleset1Name != null) {
            int rulesetId1 = SQLConfiguration.getRulesetId(ruleset1Name, userEmail);
            selectedRulesetRules = SQLConfiguration.getRules(rulesetId1);
        } else if (ruleset2Name != null) {
            int rulesetId2 = SQLConfiguration.getRulesetId(ruleset2Name, userEmail);
            selectedRulesetRules = SQLConfiguration.getRules(rulesetId2);
        } else if (ruleset3Name != null){
            int rulesetId3 = SQLConfiguration.getRulesetId(ruleset3Name, userEmail);
            selectedRulesetRules = SQLConfiguration.getRules(rulesetId3);
        } else if (ruleset4Name != null){
            int rulesetId4 = SQLConfiguration.getRulesetId(ruleset4Name, userEmail);
            selectedRulesetRules = SQLConfiguration.getRules(rulesetId4);
        } else if (ruleset5Name != null){
            int rulesetId5 = SQLConfiguration.getRulesetId(ruleset5Name, userEmail);
            selectedRulesetRules = SQLConfiguration.getRules(rulesetId5);
        }

        return selectedRulesetRules;
    }

    public void maxAttempts() {}

    public void simRunnerExecution() {}

    public void stepLog() {
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
        for (String action: ruleActions) {
            // formats the actions into a numbered list
            items.add((i + 1) + ". " + action);
//            if (!didRobotCompleteLayout) {
//                // somehow update it
//            } else {
//                System.out.println("Layout completed.");
//            }
        }
        System.out.println(items);
        // adds the numbered steps/actions to the listView
        listView.setItems(items);
    }

    public void onRunClick() {}

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
