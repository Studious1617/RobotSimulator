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
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RulesetsCreate implements Initializable {
    @FXML
    public TextField rulesetName_TF;
    public Button backButton, rulesetSaveButton;
    public Tab
            rule1Tab, rule2Tab, rule3Tab,
            rule4Tab, rule5Tab, rule6Tab;

    public ChoiceBox
            rule1_When_CB, rule1_Is_CB1,
            rule1_And_CB1, rule1_Is_CB2,
            rule1_And_CB2, rule1_Is_CB3,
            rule1_And_CB3, rule1_Is_CB4,
            rule1_Then_CB,

            rule2_When_CB, rule2_Is_CB1,
            rule2_And_CB1, rule2_Is_CB2,
            rule2_And_CB2, rule2_Is_CB3,
            rule2_And_CB3, rule2_Is_CB4,
            rule2_Then_CB,

            rule3_When_CB, rule3_Is_CB1,
            rule3_And_CB1, rule3_Is_CB2,
            rule3_And_CB2, rule3_Is_CB3,
            rule3_And_CB3, rule3_Is_CB4,
            rule3_Then_CB,

            rule4_When_CB, rule4_Is_CB1,
            rule4_And_CB1, rule4_Is_CB2,
            rule4_And_CB2, rule4_Is_CB3,
            rule4_And_CB3, rule4_Is_CB4,
            rule4_Then_CB,

            rule5_When_CB, rule5_Is_CB1,
            rule5_And_CB1, rule5_Is_CB2,
            rule5_And_CB2, rule5_Is_CB3,
            rule5_And_CB3, rule5_Is_CB4,
            rule5_Then_CB,

            rule6_When_CB, rule6_Is_CB1,
            rule6_And_CB1, rule6_Is_CB2,
            rule6_And_CB2, rule6_Is_CB3,
            rule6_And_CB3, rule6_Is_CB4,
            rule6_Then_CB;

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


        // Rule 1
        rule1_When_CB.getItems().addAll(rulesDirectionOptions);
        rule1_And_CB1.getItems().addAll(rulesDirectionOptions);

        rule1_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule1_Is_CB2.getItems().addAll(rulesSpaceOptions);

        rule1_Then_CB.getItems().addAll(rulesActionOptions);

        /* To be put into code once choiceboxes are renamed
        // Rule 2
        rule2_When_CB.getItems().addAll(rulesDirectionOptions);
        rule2_And_CB1.getItems().addAll(rulesDirectionOptions);

        rule2_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule2_Is_CB2.getItems().addAll(rulesSpaceOptions);

        rule2_Then_CB.getItems().addAll(rulesActionOptions);

        // Rule 3
        rule3_When_CB.getItems().addAll(rulesDirectionOptions);
        rule3_And_CB1.getItems().addAll(rulesDirectionOptions);

        rule3_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule3_Is_CB2.getItems().addAll(rulesSpaceOptions);

        rule3_Then_CB.getItems().addAll(rulesActionOptions);

        // Rule 4
        rule4_When_CB.getItems().addAll(rulesDirectionOptions);
        rule4_And_CB1.getItems().addAll(rulesDirectionOptions);

        rule4_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule4_Is_CB2.getItems().addAll(rulesSpaceOptions);

        rule4_Then_CB.getItems().addAll(rulesActionOptions);

        // Rule 5
        rule5_When_CB.getItems().addAll(rulesDirectionOptions);
        rule5_And_CB1.getItems().addAll(rulesDirectionOptions);

        rule5_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule5_Is_CB2.getItems().addAll(rulesSpaceOptions);

        rule5_Then_CB.getItems().addAll(rulesActionOptions);

        // Rule 6
        rule6_When_CB.getItems().addAll(rulesDirectionOptions);
        rule6_And_CB1.getItems().addAll(rulesDirectionOptions);

        rule6_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule6_Is_CB2.getItems().addAll(rulesSpaceOptions);

        rule6_Then_CB.getItems().addAll(rulesActionOptions);
        */

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
        //Getting values of the sentences
        //Rule 1
        if (!rule1Tab.isDisable()){
            rule1_When_CB.getValue();
            rule1_Is_CB1.getValue();
            rule1_Then_CB.getValue();

            /*To be put into code once initialized
            if (!rule2Tab.isDisable()){
                rule2_When_CB.getValue();
                rule2_Is_CB1.getValue();
                rule2_Then_CB.getValue();

                if (!rule3Tab.isDisable()){
                    rule3_When_CB.getValue();
                    rule3_Is_CB1.getValue();
                    rule3_Then_CB.getValue();

                    if (!rule4Tab.isDisable()){
                        rule4_When_CB.getValue();
                        rule4_Is_CB1.getValue();
                        rule4_Then_CB.getValue();

                        if (!rule5Tab.isDisable()){
                            rule5_When_CB.getValue();
                            rule5_Is_CB1.getValue();
                            rule5_Then_CB.getValue();

                            if (!rule6Tab.isDisable()){
                                rule6_When_CB.getValue();
                                rule6_Is_CB1.getValue();
                                rule6_Then_CB.getValue();

                            }
                        }

                    }
                }

            }
            */
        }

        //Make a method that checks that all avaliable choiceboxes have been filled
        //Make sure it checks for visibility and value

        //Make an arraylist of the String values of the choiceboxes
        ArrayList rule1_And_ArrayList = new ArrayList<>();
        rule1_And_CB1.getValue();
        rule1_And_CB2.getValue();

        ArrayList rule2_And_ArrayList = new ArrayList<>();
        ArrayList rule3_And_ArrayList = new ArrayList<>();
        ArrayList rule4_And_ArrayList = new ArrayList<>();
        ArrayList rule5_And_ArrayList = new ArrayList<>();
        ArrayList rule6_And_ArrayList = new ArrayList<>();

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
