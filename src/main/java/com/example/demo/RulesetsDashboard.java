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

import java.util.List;

public class RulesetsDashboard {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();
    @FXML
    public Button
            RulePage_LayoutsPageButton, createLayoutButton,
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

    public String rulesetName;
    public void setRulesetName(String rulesetName) {
        this.rulesetName = rulesetName;
    }

    private String userEmail;
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private List<Layout> listOfLayouts;
    public List<Layout> getListOfLayouts() {
        return listOfLayouts;
    }
    public void setListOfLayouts(List<Layout> listOfLayouts) {
        this.listOfLayouts = listOfLayouts;
    }

    @FXML
    public void onCreateNewRulesetClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsCreate.fxml"));
        Parent rulesetsCreatePopUp = loader.load();
        RulesetsCreate rulesetsCreate = loader.getController();
        System.out.println("Stabilizing user layout data as ");
        // so the user's layout info doesn't get lost
        rulesetsCreate.setUserEmail(getUserEmail());

        rulesetsCreate.setListOfLayouts(getListOfLayouts());

        Stage stageSixC = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixC = new Scene(rulesetsCreatePopUp,1920,1080);
        stageSixC.setScene(sceneSixC);
        stageSixC.show();
    }

    @FXML
    public void onEditRulesetClick1(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsEdit.setUserEmail(getUserEmail());
        rulesetsEdit.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the edit page
        String rulesetName = rulesetNameLabel1.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);
        rulesetsEdit.setRulesetName(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditRulesetClick2(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsEdit.setUserEmail(getUserEmail());
        rulesetsEdit.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the edit page
        String rulesetName = rulesetNameLabel2.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);
        rulesetsEdit.setRulesetName(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditRulesetClick3(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsEdit.setUserEmail(getUserEmail());
        rulesetsEdit.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the edit page
        String rulesetName = rulesetNameLabel3.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);
        rulesetsEdit.setRulesetName(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditRulesetClick4(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsEdit.setUserEmail(getUserEmail());
        rulesetsEdit.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the edit page
        String rulesetName = rulesetNameLabel4.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);
        rulesetsEdit.setRulesetName(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditRulesetClick5(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsEdit.setUserEmail(getUserEmail());
        rulesetsEdit.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the edit page
        String rulesetName = rulesetNameLabel5.getText();
        rulesetsEdit.rulesetNameLabel.setText(rulesetName);
        rulesetsEdit.setRulesetName(rulesetName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }

    @FXML
    public void onViewRulesetClick1(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsView.setUserEmail(getUserEmail());
        rulesetsView.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the label
        String rulesetName = rulesetNameLabel1.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);
        rulesetsView.setRulesetName(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }
    public void onViewRulesetClick2(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsView.setUserEmail(getUserEmail());
        rulesetsView.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the label
        String rulesetName = rulesetNameLabel2.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);
        rulesetsView.setRulesetName(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }
    public void onViewRulesetClick3(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsView.setUserEmail(getUserEmail());
        rulesetsView.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the label
        String rulesetName = rulesetNameLabel3.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);
        rulesetsView.setRulesetName(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }
    public void onViewRulesetClick4(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsView.setUserEmail(getUserEmail());
        rulesetsView.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the label
        String rulesetName = rulesetNameLabel4.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);
        rulesetsView.setRulesetName(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }
    public void onViewRulesetClick5(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsView.setUserEmail(getUserEmail());
        rulesetsView.setListOfLayouts(getListOfLayouts());
        // sets the ruleset name to the label
        String rulesetName = rulesetNameLabel5.getText();
        rulesetsView.rulesetNameLabel.setText(rulesetName);
        rulesetsView.setRulesetName(rulesetName);

        Stage stageSixV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixV = new Scene(rulesetsViewPopUp,1920,1080);
        stageSixV.setScene(sceneSixV);
        stageSixV.show();
    }

    @FXML
    public void onLayoutPageClick(ActionEvent e) throws Exception {
        // sets user email instance into Dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController dashboardController = loader.getController();
        dashboardController.setListOfLayouts(getListOfLayouts());
        // reveals the user's layouts
        if (!getListOfLayouts().isEmpty()) {
            dashboardController.makeUserLayoutVisible();
        }

        Stage stageFour = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFour = new Scene(dashboardPopUp,1540,800);
        stageFour.setScene(sceneFour);
        stageFour.show();
    }

    public void onDeleteRulesetClick1(ActionEvent e) throws Exception {
        deleteRulesetButton1.onMouseClickedProperty();
        if (deleteRulesetButton1.isVisible()) {
            ruleset1_right.setVisible(false);
            ruleset1_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel1.getText();
        // gets rulesetId for getRules method
        int rulesetId = sqlConfiguration.getRulesetId(rulesetName, getUserEmail());
        // deletes from table
        sqlConfiguration.deleteRuleset(rulesetId);
    }
    public void onDeleteRulesetClick2(ActionEvent e) throws Exception {
        if (deleteRulesetButton2.isVisible()) {
            ruleset2_right.setVisible(false);
            ruleset2_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel2.getText();
        // gets rulesetId for getRules method
        int rulesetId = sqlConfiguration.getRulesetId(rulesetName, getUserEmail());
        // deletes from table
        sqlConfiguration.deleteRuleset(rulesetId);
    }
    public void onDeleteRulesetClick3(ActionEvent e) throws Exception {
        if (deleteRulesetButton3.isVisible()) {
            ruleset3_right.setVisible(false);
            ruleset3_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel3.getText();
        // gets rulesetId for getRules method
        int rulesetId = sqlConfiguration.getRulesetId(rulesetName, getUserEmail());
        // deletes from table
        sqlConfiguration.deleteRuleset(rulesetId);
    }
    public void onDeleteRulesetClick4(ActionEvent e) throws Exception {
        if (deleteRulesetButton4.isVisible()) {
            ruleset4_right.setVisible(false);
            ruleset4_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel4.getText();
        // gets rulesetId for getRules method
        int rulesetId = sqlConfiguration.getRulesetId(rulesetName, getUserEmail());
        // deletes from table
        sqlConfiguration.deleteRuleset(rulesetId);
    }
    public void onDeleteRulesetClick5(ActionEvent e) throws Exception {
        if (deleteRulesetButton5.isVisible()) {
            ruleset5_right.setVisible(false);
            ruleset5_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
        rulesetName = rulesetNameLabel5.getText();
        // gets rulesetId for getRules method
        int rulesetId = sqlConfiguration.getRulesetId(rulesetName, getUserEmail());
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
        String rulesetName1 = sqlConfiguration.getRulesetNamesFromTable(getUserEmail()).getFirst();
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

        rulesetName = sqlConfiguration.getRulesetNamesFromTable(getUserEmail()).get(1);
        rulesetNameLabel2.setText(rulesetName);

        rulesetNameLabel2.setVisible(true);
        editRulesetButton2.setVisible(true);
        viewRulesetButton2.setVisible(true);
        deleteRulesetButton2.setVisible(true);
    }
    private void rulesetThreeVisibility() {
        ruleset3_left.setVisible(true);
        ruleset3_right.setVisible(true);

        rulesetName = sqlConfiguration.getRulesetNamesFromTable(getUserEmail()).get(2);
        rulesetNameLabel3.setText(rulesetName);

        rulesetNameLabel2.setVisible(true);
        editRulesetButton3.setVisible(true);
        viewRulesetButton3.setVisible(true);
        deleteRulesetButton3.setVisible(true);
    }
    private void rulesetFourVisibility() {
        ruleset4_left.setVisible(true);
        ruleset4_right.setVisible(true);

        rulesetName = sqlConfiguration.getRulesetNamesFromTable(getUserEmail()).get(3);
        rulesetNameLabel4.setText(rulesetName);

        rulesetNameLabel4.setVisible(true);
        editRulesetButton4.setVisible(true);
        viewRulesetButton4.setVisible(true);
        deleteRulesetButton4.setVisible(true);
    }
    private void rulesetFiveVisibility() {
        ruleset5_left.setVisible(true);
        ruleset5_right.setVisible(true);

        rulesetName = sqlConfiguration.getRulesetNamesFromTable(getUserEmail()).get(4);
        rulesetNameLabel5.setText(rulesetName);

        rulesetNameLabel5.setVisible(true);
        editRulesetButton5.setVisible(true);
        viewRulesetButton5.setVisible(true);
        deleteRulesetButton5.setVisible(true);
    }
}

