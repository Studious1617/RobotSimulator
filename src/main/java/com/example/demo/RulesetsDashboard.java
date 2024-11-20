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

import java.util.ArrayList;
import java.util.List;

public class RulesetsDashboard {
    public int buttonNumber;
    @FXML
    public Button
            RulePage_LayoutsPageButton,
            createLayoutButton;
    
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

    public Button
            deleteRulesetButton1,
            deleteRulesetButton2,
            deleteRulesetButton3,
            deleteRulesetButton4,
            deleteRulesetButton5;

    public List<Button> rulesetButtonList;
    public Label rulesetDeleteLabel;

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
    public void initialize () {
        rulesetButtonList = new ArrayList<>();
        rulesetButtonList.add(deleteRulesetButton1);
        rulesetButtonList.add(deleteRulesetButton2);
        rulesetButtonList.add(deleteRulesetButton3);
        rulesetButtonList.add(deleteRulesetButton4);
        rulesetButtonList.add(deleteRulesetButton5);
    }

    public int index;
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    @FXML
    public void onCreateNewRulesetClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsCreate.fxml"));
        Parent rulesetsCreatePopUp = loader.load();
        RulesetsCreate rulesetsCreate = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsCreate.setUserEmail(getUserEmail());
        rulesetsCreate.setListOfLayouts(getListOfLayouts());

        Stage stageSixC = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixC = new Scene(rulesetsCreatePopUp,1920,1080);
        stageSixC.setScene(sceneSixC);
        stageSixC.show();
    }

    @FXML
    public void onEditRulesetClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsEdit.fxml"));
        Parent rulesetsEditPopUp = loader.load();
        RulesetsEdit rulesetsEdit = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsEdit.setUserEmail(getUserEmail());
        rulesetsEdit.setListOfLayouts(getListOfLayouts());

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(rulesetsEditPopUp,1920,1080);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }

    @FXML
    public void onViewRulesetClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsView.fxml"));
        Parent rulesetsViewPopUp = loader.load();
        RulesetsView rulesetsView = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsView.setUserEmail(getUserEmail());
        rulesetsView.setListOfLayouts(getListOfLayouts());

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

    public void onDeleteRulesetClick(ActionEvent e) throws Exception {

        deleteRulesetButton1.onMouseClickedProperty();
        if (deleteRulesetButton1.isVisible()) {
                ruleset1_right.setVisible(false);
                ruleset1_left.setVisible(false);
                rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
            }

    }

    public void onDeleteRulesetClick2(ActionEvent e) throws Exception {
        if (deleteRulesetButton2.isVisible()) {
            ruleset2_right.setVisible(false);
            ruleset2_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
    }

    public void onDeleteRulesetClick3(ActionEvent e) throws Exception {
        if (deleteRulesetButton3.isVisible()) {
            ruleset3_right.setVisible(false);
            ruleset3_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
    }

    public void onDeleteRulesetClick4(ActionEvent e) throws Exception {
        if (deleteRulesetButton4.isVisible()) {
            ruleset4_right.setVisible(false);
            ruleset4_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
    }

    public void onDeleteRulesetClick5(ActionEvent e) throws Exception {
        if (deleteRulesetButton5.isVisible()) {
            ruleset5_right.setVisible(false);
            ruleset5_left.setVisible(false);
            rulesetDeleteLabel.setText("Ruleset Deleted");
            rulesetDeleteLabel.setVisible(true);
        }
    }
    }

