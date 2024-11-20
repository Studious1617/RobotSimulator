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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RulesetsCreate implements Initializable {
    @FXML
    public TextField rulesetName_TF;
    public Button backButton, rulesetSaveButton;

    public ChoiceBox
            rule1_When_CB, rule1_Is_CB1,
            rule1_Then_CB,
            rule1_And_CB1, rule1_Is_CB2;

    public String rulesetName;

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

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        ObservableList<String> rulesDirectionOptions = FXCollections.observableArrayList(
                "In-front", "To the left", "To the right");
        ObservableList<String> rulesSpaceOptions = FXCollections.observableArrayList(
                "A wall", "Empty", "The Start", "The Exit");
        ObservableList<String> rulesActionOptions =FXCollections.observableArrayList(
                "Move forward", "Turn right", "Turn left");
        // rule 1
        rule1_When_CB.getItems().addAll(rulesDirectionOptions);
        rule1_And_CB1.getItems().addAll(rulesDirectionOptions);

        rule1_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule1_Is_CB2.getItems().addAll(rulesSpaceOptions);

        rule1_Then_CB.getItems().addAll(rulesActionOptions);
    }

    public boolean doesTextFieldHaveText(String textFieldValue){
        if (textFieldValue == null || textFieldValue.isEmpty()){
            return false;
        }
        return true;
    }

    public void onSaveButtonClick(ActionEvent e) {
        rulesetName = rulesetName_TF.getText();
        if (!doesTextFieldHaveText(rulesetName)){
            //Make this a popup later
            System.out.println("Make sure that you give the ruleset a title!");
        }
    }

    public void onBackButtonClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsDashboard.fxml"));
        Parent rulesetsDashboardPopUp = loader.load();
        RulesetsDashboard rulesetsDashboard = loader.getController();
        // so the user's info doesn't get lost
        rulesetsDashboard.setUserEmail(getUserEmail());
        rulesetsDashboard.setListOfLayouts(getListOfLayouts());

        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(rulesetsDashboardPopUp,1920,1080);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }
}
