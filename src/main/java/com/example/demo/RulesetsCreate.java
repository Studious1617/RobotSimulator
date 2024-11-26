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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RulesetsCreate implements Initializable {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public TextField rulesetName_TF;
    public Button backButton, rulesetSaveButton, addRuleButton,
            rule1_addButton1, rule1_addButton2,
            rule2_addButton1, rule2_addButton2,
            rule3_addButton1, rule3_addButton2,
            rule4_addButton1, rule4_addButton2,
            rule5_addButton1, rule5_addButton2,
            rule6_addButton1, rule6_addButton2;
    public Tab
            rule1Tab, rule2Tab, rule3Tab,
            rule4Tab, rule5Tab, rule6Tab;

    public ChoiceBox<String>
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
        rule1_And_CB2.getItems().addAll(rulesDirectionOptions);
        rule1_And_CB3.getItems().addAll(rulesDirectionOptions);

        rule1_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule1_Is_CB2.getItems().addAll(rulesSpaceOptions);
        rule1_Is_CB3.getItems().addAll(rulesSpaceOptions);
        rule1_Is_CB4.getItems().addAll(rulesSpaceOptions);

        rule1_Then_CB.getItems().addAll(rulesActionOptions);


        // Rule 2
        rule2_When_CB.getItems().addAll(rulesDirectionOptions);
        rule2_And_CB1.getItems().addAll(rulesDirectionOptions);
        rule2_And_CB2.getItems().addAll(rulesDirectionOptions);
        rule2_And_CB3.getItems().addAll(rulesDirectionOptions);

        rule2_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule2_Is_CB2.getItems().addAll(rulesSpaceOptions);
        rule2_Is_CB3.getItems().addAll(rulesSpaceOptions);
        rule2_Is_CB4.getItems().addAll(rulesSpaceOptions);

        rule2_Then_CB.getItems().addAll(rulesActionOptions);

        // Rule 3
        rule3_When_CB.getItems().addAll(rulesDirectionOptions);
        rule3_And_CB1.getItems().addAll(rulesDirectionOptions);
        rule3_And_CB2.getItems().addAll(rulesDirectionOptions);
        rule3_And_CB3.getItems().addAll(rulesDirectionOptions);

        rule3_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule3_Is_CB2.getItems().addAll(rulesSpaceOptions);
        rule3_Is_CB3.getItems().addAll(rulesSpaceOptions);
        rule3_Is_CB4.getItems().addAll(rulesSpaceOptions);

        rule3_Then_CB.getItems().addAll(rulesActionOptions);

        // Rule 4
        rule4_When_CB.getItems().addAll(rulesDirectionOptions);
        rule4_And_CB1.getItems().addAll(rulesDirectionOptions);
        rule4_And_CB2.getItems().addAll(rulesDirectionOptions);
        rule4_And_CB3.getItems().addAll(rulesDirectionOptions);

        rule4_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule4_Is_CB2.getItems().addAll(rulesSpaceOptions);
        rule4_Is_CB3.getItems().addAll(rulesSpaceOptions);
        rule4_Is_CB4.getItems().addAll(rulesSpaceOptions);

        rule4_Then_CB.getItems().addAll(rulesActionOptions);

        // Rule 5
        rule5_When_CB.getItems().addAll(rulesDirectionOptions);
        rule5_And_CB1.getItems().addAll(rulesDirectionOptions);
        rule5_And_CB2.getItems().addAll(rulesDirectionOptions);
        rule5_And_CB3.getItems().addAll(rulesDirectionOptions);

        rule5_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule5_Is_CB2.getItems().addAll(rulesSpaceOptions);
        rule5_Is_CB3.getItems().addAll(rulesSpaceOptions);
        rule5_Is_CB4.getItems().addAll(rulesSpaceOptions);

        rule5_Then_CB.getItems().addAll(rulesActionOptions);

        // Rule 6
        rule6_When_CB.getItems().addAll(rulesDirectionOptions);
        rule6_And_CB1.getItems().addAll(rulesDirectionOptions);
        rule6_And_CB2.getItems().addAll(rulesDirectionOptions);
        rule6_And_CB3.getItems().addAll(rulesDirectionOptions);

        rule6_Is_CB1.getItems().addAll(rulesSpaceOptions);
        rule6_Is_CB2.getItems().addAll(rulesSpaceOptions);
        rule6_Is_CB3.getItems().addAll(rulesSpaceOptions);
        rule6_Is_CB4.getItems().addAll(rulesSpaceOptions);

        rule6_Then_CB.getItems().addAll(rulesActionOptions);
    }

    public boolean doesTextFieldHaveText(String textFieldValue){
        return textFieldValue != null && !textFieldValue.isEmpty();
    }

    public boolean areAllRulesSaved(){
        return true;
    }

    public void onSaveButtonClick(ActionEvent e) {
        //Getting values of the sentences
        String when, is1, then, and1, is2, and2, is3, and3, is4;

        if (rule1Tab.isDisabled()) {
            System.out.println("The rule #1 tab is disabled.");
        }
        else {
            //Rule 1
            when = rule1_When_CB.getValue();
            is1 = rule1_Is_CB1.getValue();
            then = rule1_Then_CB.getValue();
            and1 = rule1_And_CB1.getValue();
            is2 = rule1_Is_CB2.getValue();
            and2 = rule1_And_CB2.getValue();
            is3 = rule1_Is_CB3.getValue();
            and3 = rule1_And_CB3.getValue();
            is4 = rule1_Is_CB4.getValue();

            String email = getUserEmail();
            System.out.println("This is the user email: " + email);

            rulesetName = rulesetName_TF.getText();

            ArrayList<String> rule1CheckList = new ArrayList<>(Arrays.asList(when, is1, then, and1, is2, and2, is3, and3, is4));
            System.out.println("Before inserting the rules...");

            // Make sure to create ruleset in rulesets table
            // foreign key (rulesetID) needs to reference the rulesets table
            System.out.println("Checking if the text field has text...");
            if (doesTextFieldHaveText(rulesetName)) {
                System.out.println("Check complete. The text field has text.\n");
                System.out.println("Making a new ruleset...");
                System.out.println("Inserting ruleset name and rule count into database...");
                // makes a new ruleset
                sqlConfiguration.insertRuleset(rulesetName, 0, userEmail);
                System.out.println("Insertion complete. Your ruleset is named: " + rulesetName);
                System.out.println("Checking that row 1 and 2 as well as the last are not null...");
                // checks the required checkboxes
                if (when != null && is1 != null && then != null) { // necessary conditions
                    System.out.println("Check complete.\n");
                    System.out.println("Inserting rules into database...");
                    //RulesetID orginally had a 2 here but I changed it

                    sqlConfiguration.insertRules(1, when, is1, then, and1, is2, and2, is3, and3, is4);
                    System.out.println("Rules inserted.\n");

                    //TODO Write a method that gets the rules info from the database instead of this so that
                    // we can really see where and when things are breaking down
//                    sqlConfiguration.getUserRulesetList();
                    System.out.println("rule1CheckList: ");
                    for (String s : rule1CheckList) {
                        System.out.println(s);
                    }

                }
            } else {
                //Make this a popup later
                System.out.println("Couldn't save ruleset. Make sure that you give the ruleset a title!");
            }
        }

        if (rule2Tab.isDisabled()) {
            System.out.println("The rule #2 tab is disabled.");
        }
        else {
            // no validation for checkboxes yet
            when = rule2_When_CB.getValue();
            is1 = rule2_Is_CB1.getValue();
            and1 = rule2_And_CB1.getValue();
            is2 = rule2_Is_CB2.getValue();
            and2 = rule2_And_CB2.getValue();
            is3 = rule2_Is_CB3.getValue();
            and3 = rule2_And_CB3.getValue();
            is4 = rule2_Is_CB4.getValue();
            then = rule2_Then_CB.getValue();

            ArrayList<String> rule2CheckList = new ArrayList<>(Arrays.asList(when, is1, then, and1, is2, and2, is3, and3, is4));
            for (String s2 : rule2CheckList) {
                System.out.println("rule2CheckList1: ");
                System.out.println(s2);
            }

            if (when != null && is1 != null && then != null) { // necessary conditions
                sqlConfiguration.insertRules(3, when, is1, then, and1, is2, and2, is3, and3, is4);

                for (String s2 : rule2CheckList) {
                    System.out.println("rule2CheckList2: ");
                    System.out.println(s2);
                }
            }
        }
/*
            if (!rule3Tab.isDisable()) {
                // no validation for checkboxes yet
                when = rule3_When_CB.getValue();
                is1 = rule3_Is_CB1.getValue();
                and1 = rule3_And_CB1.getValue();
                is2 = rule3_Is_CB2.getValue();
                and2 = rule3_And_CB2.getValue();
                is3 = rule3_Is_CB3.getValue();
                and3 = rule3_And_CB3.getValue();
                is4 = rule3_Is_CB4.getValue();
                then = rule3_Then_CB.getValue();

                ArrayList<String> rule3CheckList = new ArrayList<>(Arrays.asList(when, is1, then, and1, is2, and2, is3, and3, is4));
                for (String s3: rule2CheckList) {
                    System.out.println(s3);
                }

                sqlConfiguration.insertRules(1, when, is1, then, and1, is2, and2, is3, and3, is4);

                if (!rule4Tab.isDisable()) {
                    // no validation for checkboxes yet
                    when = rule4_When_CB.getValue();
                    is1 = rule4_Is_CB1.getValue();
                    and1 = rule4_And_CB1.getValue();
                    is2 = rule4_Is_CB2.getValue();
                    and2 = rule4_And_CB2.getValue();
                    is3 = rule4_Is_CB3.getValue();
                    and3 = rule4_And_CB3.getValue();
                    is4 = rule4_Is_CB4.getValue();
                    then = rule4_Then_CB.getValue();

                    ArrayList<String> rule4CheckList = new ArrayList<>(Arrays.asList(when, is1, then, and1, is2, and2, is3, and3, is4));
                    for (String s4: rule2CheckList) {
                        System.out.println(s4);
                    }

                    sqlConfiguration.insertRules(1, when, is1, then, and1, is2, and2, is3, and3, is4);

                    if (!rule5Tab.isDisable()) {
                        rule5_When_CB.getValue();
                        rule5_Is_CB1.getValue();
                        rule5_Then_CB.getValue();

                        if (!rule6Tab.isDisable()) {
                            rule6_When_CB.getValue();
                            rule6_Is_CB1.getValue();
                            rule6_Then_CB.getValue();

                        }
                    }

                }
            } */

        //Make a method that checks that all avaliable choiceboxes have been filled
        //Make sure it checks for visibility and value
        System.out.println("Rule saved.\n");
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
