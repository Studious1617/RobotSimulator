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

public class RulesetsEdit {
    @FXML
    public Button backButton;

    public String userEmail;
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Layout> listOfLayouts;
    public List<Layout> getListOfLayouts() {
        return listOfLayouts;
    }
    public void setListOfLayouts(List<Layout> listOfLayouts) {
        this.listOfLayouts = listOfLayouts;
    }

    public void onBackButtonClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsDashboard.fxml"));
        Parent rulesetsDashboardPopUp = loader.load();
        RulesetsDashboard rulesetsDashboardE = loader.getController();
        // so the user's info doesn't get lost
        rulesetsDashboardE.setUserEmail(getUserEmail());
        rulesetsDashboardE.setListOfLayouts(getListOfLayouts());

        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(rulesetsDashboardPopUp, 1920,1080);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    public void onSaveButtonClick(ActionEvent e) throws Exception {
        /* rule1_When_CB.getItems()
           rule1_IS_CB1.getItems()
           rule1_And_CB1.getItems()
           rule1_Then_CB.getItems()
         */
    }
}
