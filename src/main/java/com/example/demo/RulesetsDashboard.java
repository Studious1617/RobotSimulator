package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static com.example.demo.CreateUserAccountController.userEmail;
import static com.example.demo.LogInController.layouts;

public class RulesetsDashboard {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();
    @FXML
    public Button
            RulePage_LayoutsPageButton, createLayoutButton, reportsButton,
            deleteRulesetButton1,
            deleteRulesetButton2,
            deleteRulesetButton3,
            deleteRulesetButton4,
            deleteRulesetButton5,

            viewRulesetButton1,
            viewRulesetButton2,
            viewRulesetButton3,
            viewRulesetButton4,
            viewRulesetButton5,

            editRulesetButton1,
            editRulesetButton2,
            editRulesetButton3,
            editRulesetButton4,
            editRulesetButton5;

    public Label
            rulesetNameLabel1,
            rulesetNameLabel2,
            rulesetNameLabel3,
            rulesetNameLabel4,
            rulesetNameLabel5;

    public HBox
            ruleset1_left,
            ruleset1_right,
            ruleset2_left,
            ruleset2_right,
            ruleset3_left,
            ruleset3_right,
            ruleset4_left,
            ruleset4_right,
            ruleset5_left,
            ruleset5_right;

    public Label rulesetDeleteLabel;

    static int rulesetId;
    static String rulesetName;
    static int ruleId;

    @FXML
    public void onCreateNewRulesetClick(ActionEvent e) throws Exception {
        Parent rulesetsCreatePopUp = FXMLLoader.load(getClass().getResource("RulesetsCreate.fxml"));

        // switches to the ruleset create page
        Stage stageSixC = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixC = new Scene(rulesetsCreatePopUp,1920,1080);
        stageSixC.setScene(sceneSixC);
        stageSixC.show();
    }

    public void onEditRulesetClick1(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // sets the ruleset name to the edit page
        rulesetName = rulesetNameLabel1.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditRulesetClick2(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // sets the ruleset name to the edit page
        rulesetName = rulesetNameLabel2.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditRulesetClick3(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // sets the ruleset name to the edit page
        rulesetName = rulesetNameLabel3.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditRulesetClick4(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // sets the ruleset name to the edit page
        rulesetName = rulesetNameLabel4.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditRulesetClick5(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // sets the ruleset name to the edit page
        rulesetName = rulesetNameLabel5.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }

    public void onViewRulesetClick1(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // sets the ruleset name to the label
        rulesetName = rulesetNameLabel1.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }
    public void onViewRulesetClick2(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // sets the ruleset name to the label
        rulesetName = rulesetNameLabel2.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }
    public void onViewRulesetClick3(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // sets the ruleset name to the label
        rulesetName = rulesetNameLabel3.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }
    public void onViewRulesetClick4(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // sets the ruleset name to the label
        rulesetName = rulesetNameLabel4.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }
    public void onViewRulesetClick5(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // sets the ruleset name to the label
        rulesetName = rulesetNameLabel5.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }

    public void onLayoutPageClick(ActionEvent e) throws Exception {
        // loads/readies the Layout Dashboard up
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController dashboardController = loader.getController();

        // gets user's layouts
        layouts = sqlConfiguration.getUserLayouts(userEmail);
        // reveals the user's layouts if they have some
        if (!layouts.isEmpty()) {
            dashboardController.makeUserLayoutVisible();
        }
        // switches to the layout dashboard
        Stage stageFour = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFour = new Scene(dashboardPopUp,1540,800);
        stageFour.setScene(sceneFour);
        stageFour.show();
    }

    public void onDeleteRulesetClick1() {
        deleteRulesetButton1.onMouseClickedProperty();
        if (deleteRulesetButton1.isVisible()) {
            ruleset1_right.setVisible(false);
            ruleset1_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel1.getText();
        // gets rulesetId to delete with that id
        rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
        // deletes from table
        sqlConfiguration.deleteRuleset(rulesetId);
    }
    public void onDeleteRulesetClick2() {
        if (deleteRulesetButton2.isVisible()) {
            ruleset2_right.setVisible(false);
            ruleset2_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel2.getText();
        // gets rulesetId to delete the ruleset
        rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
        // deletes from table
        sqlConfiguration.deleteRuleset(rulesetId);
        // refreshes the dashboard so there's no blank space between rulesets
        makeUserRulesetsVisible();
    }
    public void onDeleteRulesetClick3() {
        if (deleteRulesetButton3.isVisible()) {
            ruleset3_right.setVisible(false);
            ruleset3_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel3.getText();
        // gets rulesetId to delete from tables
        rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
        // deletes from table
        sqlConfiguration.deleteRuleset(rulesetId);
        // refreshes the dashboard so there's no blank space between rulesets
        makeUserRulesetsVisible();
    }
    public void onDeleteRulesetClick4() {
        if (deleteRulesetButton4.isVisible()) {
            ruleset4_right.setVisible(false);
            ruleset4_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel4.getText();
        // gets rulesetId to delete ruleset from database
        rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
        // deletes from table
        sqlConfiguration.deleteRuleset(rulesetId);
        // refreshes the dashboard so there's no blank space between rulesets
        makeUserRulesetsVisible();
    }
    public void onDeleteRulesetClick5() {
        if (deleteRulesetButton5.isVisible()) {
            ruleset5_right.setVisible(false);
            ruleset5_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel5.getText();
        // gets rulesetId to delete ruleset
        rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
        // deletes from table
        sqlConfiguration.deleteRuleset(rulesetId);
    }

    public void makeUserRulesetsVisible() {
        int rulesAmount = sqlConfiguration.getUserRulesetAmount();
        if (rulesAmount == 1) {
            rulesetOneVisibility();
        } else if (rulesAmount == 2) {
            rulesetOneVisibility();
            rulesetTwoVisibility();
        } else if (rulesAmount == 3) {
            rulesetOneVisibility();
            rulesetTwoVisibility();
            rulesetThreeVisibility();
        } else if (rulesAmount == 4) {
            rulesetOneVisibility();
            rulesetTwoVisibility();
            rulesetThreeVisibility();
            rulesetFourVisibility();
        } else if (rulesAmount == 5) {
            rulesetOneVisibility();
            rulesetTwoVisibility();
            rulesetThreeVisibility();
            rulesetFourVisibility();
            rulesetFiveVisibility();
        }
    }

    private void rulesetOneVisibility() {
        String rulesetName1 = sqlConfiguration.getRulesetNamesFromTable(userEmail).getFirst();
        rulesetNameLabel1.setText(rulesetName1);

        ruleset1_left.setVisible(true);
        ruleset1_right.setVisible(true);
        rulesetNameLabel1.setVisible(true);
        editRulesetButton1.setVisible(true);
        viewRulesetButton1.setVisible(true);
        deleteRulesetButton1.setVisible(true);
    }
    private void rulesetTwoVisibility() {
        ruleset2_left.setVisible(true);
        ruleset2_right.setVisible(true);

        rulesetName = sqlConfiguration.getRulesetNamesFromTable(userEmail).get(1);
        rulesetNameLabel2.setText(rulesetName);

        rulesetNameLabel2.setVisible(true);
        editRulesetButton2.setVisible(true);
        viewRulesetButton2.setVisible(true);
        deleteRulesetButton2.setVisible(true);
    }
    private void rulesetThreeVisibility() {
        ruleset3_left.setVisible(true);
        ruleset3_right.setVisible(true);

        rulesetName = sqlConfiguration.getRulesetNamesFromTable(userEmail).get(2);
        rulesetNameLabel3.setText(rulesetName);

        rulesetNameLabel2.setVisible(true);
        editRulesetButton3.setVisible(true);
        viewRulesetButton3.setVisible(true);
        deleteRulesetButton3.setVisible(true);
    }
    private void rulesetFourVisibility() {
        ruleset4_left.setVisible(true);
        ruleset4_right.setVisible(true);

        rulesetName = sqlConfiguration.getRulesetNamesFromTable(userEmail).get(3);
        rulesetNameLabel4.setText(rulesetName);

        rulesetNameLabel4.setVisible(true);
        editRulesetButton4.setVisible(true);
        viewRulesetButton4.setVisible(true);
        deleteRulesetButton4.setVisible(true);
    }
    private void rulesetFiveVisibility() {
        ruleset5_left.setVisible(true);
        ruleset5_right.setVisible(true);

        rulesetName = sqlConfiguration.getRulesetNamesFromTable(userEmail).get(4);
        rulesetNameLabel5.setText(rulesetName);

        rulesetNameLabel5.setVisible(true);
        editRulesetButton5.setVisible(true);
        viewRulesetButton5.setVisible(true);
        deleteRulesetButton5.setVisible(true);
    }
}

