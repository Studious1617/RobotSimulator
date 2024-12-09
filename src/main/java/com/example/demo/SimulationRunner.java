package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.example.demo.CreateUserAccountController.userEmail;
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

    public void selectLayout() {}

    public void selectRuleset() {

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
