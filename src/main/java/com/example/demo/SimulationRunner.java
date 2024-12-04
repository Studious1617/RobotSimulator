package com.example.demo;

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

public class SimulationRunner {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public Button backButton, runButton;

    public ComboBox<String> layoutName_CB, rulesetName_CB, maxAttempts_CB;

    public ListView<Object> listView;

    public String userEmail;
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

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

    public void stepLog() {}

    public void onRunClick() {}

    @FXML
    public void onBackButtonClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsDashboard.fxml"));
        Parent rulesetsDashboardPopUp = loader.load();
        RulesetsDashboard rulesetsDashboardSR = loader.getController();
        // so the user's layout info doesn't get lost
//        rulesetsDashboardSR.setUserEmail(userEmail);
//        rulesetsDashboardSR.setListOfLayouts(getListOfLayouts());
        rulesetsDashboardSR.makeUserRulesetsVisible();

        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(rulesetsDashboardPopUp, 1920, 1080);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

}
