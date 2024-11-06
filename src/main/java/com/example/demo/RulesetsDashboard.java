package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.List;

public class RulesetsDashboard {
    @FXML
    public Button
            RulePage_LayoutsPageButton,
            createLayoutButton;


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
        // so the user's layout info doesn't get lost
        rulesetsCreate.setUserEmail(getUserEmail());
        rulesetsCreate.setListOfLayouts(getListOfLayouts());

        Stage stageSixC = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixC = new Scene(rulesetsCreatePopUp);
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
        Scene sceneSixE = new Scene(rulesetsEditPopUp);
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
        Scene sceneSixV = new Scene(rulesetsViewPopUp);
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
        if (!getListOfLayouts().isEmpty() && getListOfLayouts() != null) {
            dashboardController.makeUserLayoutVisible();
        }

        Stage stageFour = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFour = new Scene(dashboardPopUp);
        stageFour.setScene(sceneFour);
        stageFour.show();
    }
}
