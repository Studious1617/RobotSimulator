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
    // variable to hold the ruleset ids
    int rulesetId;

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
                "In-front", "To the left", "To the right", "Backwards");
        ObservableList<String> rulesSpaceOptions = FXCollections.observableArrayList(
                "A wall", "Empty", "The Start", "The Exit");
        ObservableList<String> rulesActionOptions =FXCollections.observableArrayList(
                "Move forward", "Turn right", "Turn left", "Turn back");

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

        rulesetName = rulesetName_TF.getText();
        String email = getUserEmail();
        System.out.println("This is the user email: " + email);
        int rId = sqlConfiguration.getRulesetId(rulesetName, email);
        System.out.println("Ruleset ID: " + rId);

        System.out.println("Before inserting the rules...");
        // Make sure to create ruleset in rulesets table
        // foreign key (rulesetID) needs to reference the rulesets table
        System.out.println("Checking if the text field has text...");
        if (doesTextFieldHaveText(rulesetName)) {
            System.out.println("Check complete. The text field has text.\n");
            System.out.println("Making a new ruleset...");
            System.out.println("Inserting ruleset name into database...");

            // makes a new ruleset
            sqlConfiguration.insertRuleset(rulesetName, 0, userEmail);

            System.out.println("Insertion complete. Your ruleset is named: " + rulesetName);

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


            ArrayList<String> rule1CheckList = new ArrayList<>(Arrays.asList(when, is1, then, and1, is2, and2, is3, and3, is4));

            System.out.println("Checking that row 1 and 2 as well as the last for Rule #1 are not null...");
            // checks the required checkboxes
            if (when != null && is1 != null && then != null) {
                System.out.println("Check complete.\n");
                // sets the variable to the new ruleset's ruleset id
                rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
                // checks the ANDs are filled
                if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                    System.out.println("Inserting all conditions into database...");

                    sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                    System.out.println("Rule 1 inserted with all ANDs.\n");
                    // checks if 2 AND conditions are filled
                } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                    System.out.println("Inserting all but 1 AND condition into database...");

                    sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                    System.out.println("Rule 1 inserted with 2 ANDs.\n");
                    // checks if only one AND condition is filled
                } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                    System.out.println("Inserting all but 2 AND conditions into database...");

                    sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                    System.out.println("Rule 1 inserted with 1 AND.\n");
                    // checks if all ANDs are empty
                } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                    System.out.println("Inserting only required conditions (no ANDs) into database...");

                    sqlConfiguration.insertRules(rulesetId, when, is1, then, null, null, null, null, null, null);
                    System.out.println("Rule 1 inserted with no ANDs.\n");
                }

                //TODO Write a method that gets the rules info from the database instead of this so that
                // we can really see where and when things are breaking down
//                    sqlConfiguration.getUserRulesetList();
                System.out.println("rule1CheckList: ");
                for (String s : rule1CheckList) {
                    System.out.println(s);
                }

            }

            if (rule2Tab.isDisabled()) {
                System.out.println("Rule #2 was not added.");
            } else {
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

                // list to check the values of the choiceboxes
                ArrayList<String> rule2CheckList = new ArrayList<>(Arrays.asList(when, is1, then, and1, is2, and2, is3, and3, is4));

                System.out.println("Checking that row 1 and 2 as well as the last for Rule #2 are not null...");
                // checks the required checkboxes
                if (when != null && is1 != null && then != null) {
                    System.out.println("Check complete.\n");
                    // sets the variable to the new ruleset's ruleset id
                    rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 2 inserted with all ANDs.\n");
                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 2 inserted with 2 ANDs.\n");
                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 2 inserted with 1 AND.\n");
                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 2 inserted with no ANDs.\n");
                    }

                    for (String s2 : rule2CheckList) {
                        System.out.println("rule2CheckList: ");
                        System.out.println(s2);
                    }
                }
            }

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

                System.out.println("Checking that row 1 and 2 as well as the last for Rule #3 are not null...");
                // checks the required checkboxes
                if (when != null && is1 != null && then != null) {
                    // sets the variable to the new ruleset's ruleset id
                    rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 3 inserted with all ANDs.\n");
                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 3 inserted with 2 ANDs.\n");
                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 3 inserted with 1 AND.\n");
                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 3 inserted with no ANDs.\n");
                    }
                    for (String s3 : rule3CheckList) {
                        System.out.println("rule3CheckList: ");
                        System.out.println(s3);
                    }
                }
            } else {
                System.out.println("Rule #3 was not added.");
            }

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

                System.out.println("Checking that row 1 and 2 as well as the last for Rule #4 are not null...");
                // checks the required checkboxes
                if (when != null && is1 != null && then != null) {
                    // sets the variable to the new ruleset's ruleset id
                    rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 4 inserted with all ANDs.\n");
                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 4 inserted with 2 ANDs.\n");
                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 4 inserted with 1 AND.\n");
                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 4 inserted with no ANDs.\n");
                    }
                    for (String s4 : rule4CheckList) {
                        System.out.println("rule4CheckList: ");
                        System.out.println(s4);
                    }
                }
            } else {
                System.out.println("Rule #4 was not added.");
            }

            if (!rule5Tab.isDisable()) {
                when = rule5_When_CB.getValue();
                is1 = rule5_Is_CB1.getValue();
                and1 = rule5_And_CB1.getValue();
                is2 = rule5_Is_CB2.getValue();
                and2 = rule5_And_CB2.getValue();
                is3 = rule5_Is_CB3.getValue();
                and3 = rule6_And_CB3.getValue();
                is4 = rule5_Is_CB4.getValue();
                then = rule5_Then_CB.getValue();

                ArrayList<String> rule5CheckList = new ArrayList<>(Arrays.asList(when, is1, then, and1, is2, and2, is3, and3, is4));

                System.out.println("Checking that row 1 and 2 as well as the last for Rule #5 are not null...");
                // checks the required checkboxes
                if (when != null && is1 != null && then != null) {
                    // sets the variable to the new ruleset's ruleset id
                    rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 5 inserted with all ANDs.\n");
                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 5 inserted with 2 ANDs.\n");
                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 5 inserted with 1 AND.\n");
                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 5 inserted with no ANDs.\n");
                    }
                    for (String s5 : rule5CheckList) {
                        System.out.println("rule5CheckList: ");
                        System.out.println(s5);
                    }
                }
            } else {
                System.out.println("Rule #5 was not created.");
            }
            if (!rule6Tab.isDisable()) {
                when = rule6_When_CB.getValue();
                is1 = rule6_Is_CB1.getValue();
                and1 = rule6_And_CB1.getValue();
                is2 = rule6_Is_CB2.getValue();
                and2 = rule6_And_CB2.getValue();
                is3 = rule6_Is_CB3.getValue();
                and3 = rule6_And_CB3.getValue();
                is4 = rule6_Is_CB4.getValue();
                then = rule6_Then_CB.getValue();

                ArrayList<String> rule6CheckList = new ArrayList<>(Arrays.asList(when, is1, then, and1, is2, and2, is3, and3, is4));

                System.out.println("Checking that row 1 and 2 as well as the last for Rule #6 are not null...");
                // checks the required checkboxes
                if (when != null && is1 != null && then != null) {
                    // sets the variable to the new ruleset's ruleset id
                    rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 6 inserted with all ANDs.\n");
                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 6 inserted with 2 ANDs.\n");
                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 6 inserted with 1 AND.\n");
                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRules(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 6 inserted with no ANDs.\n");
                    }
                    for (String s6 : rule6CheckList) {
                        System.out.println("rule6CheckList: ");
                        System.out.println(s6);
                    }
                }
            } else {
                System.out.println("Rule #6 was not created.");
            }
        } else {
            //Make this a popup later
            System.out.println("Couldn't save ruleset. Make sure that you give the ruleset a title!");
        }
        // updates rule_count column
        sqlConfiguration.updateRuleCount(rulesetName, email);
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

    public void onAddRuleButtonClick(ActionEvent e) throws Exception {
        if (rule1Tab.isDisabled()){
            rule1Tab.setDisable(false);
        } else if (rule2Tab.isDisabled()) {
            rule2Tab.setDisable(false);
        } else if (rule3Tab.isDisabled()) {
            rule3Tab.setDisable(false);
        } else if (rule4Tab.isDisabled()) {
            rule4Tab.setDisable(false);
        } else if (rule5Tab.isDisabled()) {
            rule5Tab.setDisable(false);
        } else if (rule6Tab.isDisabled()) {
            rule6Tab.setDisable(false);
        }
    }
}
