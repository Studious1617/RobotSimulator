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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.example.demo.CreateUserAccountController.userEmail;
import static com.example.demo.RulesetsDashboard.*;

public class RulesetsEdit implements Initializable {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();
    @FXML
    public Button backButton, editSaveButton, addRuleButton,
            rule1_addButton1, rule1_addButton2,
            rule2_addButton1, rule2_addButton2,
            rule3_addButton1, rule3_addButton2,
            rule4_addButton1, rule4_addButton2,
            rule5_addButton1, rule5_addButton2,
            rule6_addButton1, rule6_addButton2;

    public Tab
            rule1Tab, rule2Tab, rule3Tab,
            rule4Tab, rule5Tab, rule6Tab;

    public Label rulesetNameLabel;

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

    public void onBackButtonClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsDashboard.fxml"));
        Parent rulesetsDashboardPopUp = loader.load();
        RulesetsDashboard rulesetsDashboardE = loader.getController();

        rulesetsDashboardE.makeUserRulesetsVisible();

        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(rulesetsDashboardPopUp, 1920,1080);
        stageFive.setScene(sceneFive);
        stageFive.show();
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

    public void onSaveButtonClick() {
        // for getting values of the sentences
        String when, is1, then, and1, is2, and2, is3, and3, is4;

        System.out.println("This is the user email: " + userEmail);
        // gets first index/name of the list of ruleset names
        System.out.println("Ruleset name: " + rulesetName);
        rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
        System.out.println("Ruleset ID: " + rulesetId);

        // list to hold the rules and check how many there are
        ArrayList<Integer> allCurrentRules = sqlConfiguration.getRuleId(rulesetId);
        System.out.println("Current rule IDs: " + allCurrentRules);

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

        System.out.println("\nChecking that row 1 and 2 as well as the last for Rule #1 are not null...");
        // checks the required checkboxes
        if (when != null && is1 != null && then != null) {
            System.out.println("Check complete.\n");
            // sets the variable to the first rule id
            ruleId = sqlConfiguration.getRuleId(rulesetId).getFirst();
            // checks the ANDs are filled
            if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                System.out.println("Inserting all conditions into database...");

                sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, and3, is4);
                System.out.println("Rule 1 updated with all ANDs.\n");
                // checks if 2 AND conditions are filled
            } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                System.out.println("Updating all but 1 AND condition into database...");

                sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, null, null);
                System.out.println("Rule 1 updated with 2 ANDs.\n");
                // checks if only one AND condition is filled
            } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                System.out.println("Inserting all but 2 AND conditions into database...");

                sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, null, null, null, null);
                System.out.println("Rule 1 updated with 1 AND.\n");
                // checks if all ANDs are empty
            } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                System.out.println("Inserting only required conditions (no ANDs) into database...");

                sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, null, null, null, null, null, null);
                System.out.println("Rule 1 updated with no ANDs.\n");
            }

            //TODO Write a method that gets the rules info from the database instead of this so that
            // we can really see where and when things are breaking down
            System.out.println("rule1CheckList: ");
            for (String s : rule1CheckList) {
                System.out.println(s);
            }
        } else {
            // message if the user saves a blank rule
            System.out.println("Check failed.\nMake sure to enter a some data into the rule.\n");
        }

        if (!rule2Tab.isDisabled()) {
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

            System.out.println("\nChecking that row 1 and 2 as well as the last for Rule #2 are not null...");
            // checks the required checkboxes
            if (when != null && is1 != null && then != null) {
                // checks if the rule exists
                if (allCurrentRules.size() == 2 && allCurrentRules.get(1) != null) {

                    System.out.println("Check complete.\n");
                    // sets the variable to the second rule id
                    ruleId = allCurrentRules.get(1);

                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 2 updated with all ANDs.\n");

                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 2 updated with 2 ANDs.\n");

                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 2 updated with 1 AND.\n");

                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 2 updated with no ANDs.\n");
                    }

                    System.out.println("rule2CheckList: ");
                    for (String s2 : rule2CheckList) {
                        System.out.println(s2);
                    }
                }
                // inserts the rule if it's not in the table
                else {
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 2 updated with all ANDs.\n");

                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 2 updated with 2 ANDs.\n");

                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 2 updated with 1 AND.\n");

                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 2 updated with no ANDs.\n");
                    }

                    System.out.println("rule2CheckList: ");
                    for (String s2 : rule2CheckList) {
                        System.out.println(s2);
                    }
                }
            } else {
                System.out.println("Check failed.\nMake sure to not leave the rule blank.");
            }
        } else {
            // checks if the rule id is null/doesn't exist
            if (allCurrentRules.size() >= 2 && allCurrentRules.get(1) != null) {
                System.out.println("Rule #2 was not created. Deleting rule from table...");
                // holds the rule id in a variable
                int secondRuleId = allCurrentRules.get(1);

                // deletes the rule
                sqlConfiguration.deleteOldRule(secondRuleId);
                System.out.println("Old rule deleted.");
            } else {
                System.out.println("Rule #2 was not created.");
            }
        }

        if (!rule3Tab.isDisabled()) {
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

            System.out.println("\nChecking that row 1 and 2 as well as the last for Rule #3 are not null...");
            // checks the required checkboxes
            if (when != null && is1 != null && then != null) {
                // checks if the rule exists
                if (allCurrentRules.size() == 3 && allCurrentRules.get(2) != null) {

                    // sets the variable to the new ruleset's ruleset id
                    ruleId = allCurrentRules.get(2);

                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 3 updated with all ANDs.\n");

                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 3 updated with 2 ANDs.\n");

                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 3 updated with 1 AND.\n");

                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 3 updated with no ANDs.\n");
                    }

                    System.out.println("rule3CheckList: ");
                    for (String s3 : rule3CheckList) {
                        System.out.println(s3);
                    }
                }
                // inserts the rule if it isn't in the table
                else {
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 3 updated with all ANDs.\n");

                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 3 updated with 2 ANDs.\n");

                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 3 updated with 1 AND.\n");

                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 3 updated with no ANDs.\n");
                    }

                    System.out.println("rule3CheckList: ");
                    for (String s3 : rule3CheckList) {
                        System.out.println(s3);
                    }
                }
            }
        } else {
            // checks if the rule id is null/doesn't exist
            if (allCurrentRules.size() >= 3 && allCurrentRules.get(2) != null) {
                System.out.println("Rule #3 was not created. Deleting rule from table...");
                // saves the second rule id in a variable to make it more readable
                int thirdRuleId = allCurrentRules.get(2);

                // deletes the rule
                sqlConfiguration.deleteOldRule(thirdRuleId);
                System.out.println("Old rule deleted.");
            } else {
                System.out.println("Rule #3 was not created.");
            }
        }

        if (!rule4Tab.isDisabled()) {
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

            System.out.println("\nChecking that row 1 and 2 as well as the last for Rule #4 are not null...");
            // checks the required checkboxes
            if (when != null && is1 != null && then != null) {
                // checks if the rule exists
                if (allCurrentRules.size() == 4 && allCurrentRules.get(3) != null) {
                    // sets the variable to the new ruleset's ruleset id
                    ruleId = allCurrentRules.get(3);
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 4 updated with all ANDs.\n");
                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 4 updated with 2 ANDs.\n");
                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 4 updated with 1 AND.\n");
                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 4 updated with no ANDs.\n");
                    }

                    System.out.println("rule4CheckList: ");
                    for (String s4 : rule4CheckList) {
                        System.out.println(s4);
                    }
                }
                // inserts the rule if it doesn't exist
                else {
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 3 updated with all ANDs.\n");

                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 4 updated with 2 ANDs.\n");

                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 4 updated with 1 AND.\n");

                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 4 updated with no ANDs.\n");
                    }

                    for (String s4 : rule4CheckList) {
                        System.out.println("rule4CheckList: ");
                        System.out.println(s4);
                    }
                }
            }
        } else {
            // checks if the rule id is null/doesn't exist
            if (allCurrentRules.size() >= 4 && allCurrentRules.get(3) != null) {
                System.out.println("Rule #4 was not created. Deleting rule from table...");
                // holds the rule id in a variable
                int fourthRuleId = allCurrentRules.get(3);

                // saves the second rule id in a variable to make it more readable
                sqlConfiguration.deleteOldRule(fourthRuleId);
                System.out.println("Old rule deleted.");
            } else {
                System.out.println("Rule #4 was not created.");
            }
        }

        if (!rule5Tab.isDisabled()) {
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

            System.out.println("\nChecking that row 1 and 2 as well as the last for Rule #5 are not null...");
            // checks the required checkboxes
            if (when != null && is1 != null && then != null) {
                // checks if the rule exists
                if (allCurrentRules.size() == 5 && allCurrentRules.get(4) != null) {

                    // sets the variable to the new ruleset's ruleset id
                    ruleId = allCurrentRules.get(4);

                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 5 updated with all ANDs.\n");

                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 5 updated with 2 ANDs.\n");

                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 5 updated with 1 AND.\n");

                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 5 updated with no ANDs.\n");
                    }

                    System.out.println("rule5CheckList: ");
                    for (String s5 : rule5CheckList) {
                        System.out.println(s5);
                    }
                }
                // inserts the rule if it's not in the table
                else {
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 5 updated with all ANDs.\n");

                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 5 updated with 2 ANDs.\n");

                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 5 updated with 1 AND.\n");

                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 5 updated with no ANDs.\n");
                    }

                    for (String s5 : rule5CheckList) {
                        System.out.println("rule5CheckList: ");
                        System.out.println(s5);
                    }
                }
            }
        } else {
            // checks if the rule id is null/doesn't exist
            if (allCurrentRules.size() >= 5 && allCurrentRules.get(4) != null) {
                System.out.println("Rule #5 was not created. Deleting rule from table...");
                // holds the rule id in a variable
                int fifthRuleId = allCurrentRules.get(4);

                // saves the second rule id in a variable to make it more readable
                sqlConfiguration.deleteOldRule(fifthRuleId);
                System.out.println("Old rule deleted.");
            } else {
                System.out.println("Rule #5 was not created.");
            }
        }

        if (!rule6Tab.isDisabled()) {
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
                // checks if it exists or not
                if (allCurrentRules.size() == 6 && allCurrentRules.get(5) != null) {

                    // sets the variable to the new ruleset's ruleset id
                    ruleId = allCurrentRules.get(5);

                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 6 updated with all ANDs.\n");

                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 6 updated with 2 ANDs.\n");

                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 6 updated with 1 AND.\n");

                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.editRuleset(rulesetId, ruleId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 6 updated with no ANDs.\n");
                    }

                    System.out.println("rule6CheckList: ");
                    for (String s6 : rule6CheckList) {
                        System.out.println(s6);
                    }
                }
                // enters the rule into the table if it wasn't created with the ruleset
                else {
                    // checks the ANDs are filled
                    if (and1 != null && is2 != null && and2 != null && is3 != null && and3 != null && is4 != null) {
                        System.out.println("Inserting all conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, and3, is4);
                        System.out.println("Rule 6 updated with all ANDs.\n");

                        // checks if 2 AND conditions are filled
                    } else if (and1 != null && is2 != null && and2 != null && is3 != null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 1 AND condition into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, and2, is3, null, null);
                        System.out.println("Rule 6 updated with 2 ANDs.\n");

                        // checks if only one AND condition is filled
                    } else if (and1 != null && is2 != null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting all but 2 AND conditions into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, and1, is2, null, null, null, null);
                        System.out.println("Rule 6 updated with 1 AND.\n");

                        // checks if all ANDs are empty
                    } else if (and1 == null && is2 == null && and2 == null && is3 == null && and3 == null && is4 == null) {
                        System.out.println("Inserting only required conditions (no ANDs) into database...");

                        sqlConfiguration.insertRule(rulesetId, when, is1, then, null, null, null, null, null, null);
                        System.out.println("Rule 6 updated with no ANDs.\n");
                    }

                    for (String s6 : rule6CheckList) {
                        System.out.println("rule6CheckList: ");
                        System.out.println(s6);
                    }
                }
            }
        } else {
            // checks if the rule id is null/doesn't exist
            if (allCurrentRules.size() >= 6 && allCurrentRules.get(5) != null) {
                System.out.println("Rule #6 was not created. Deleting rule from table...");
                // holds the rule id in a variable
                int sixthRuleId = allCurrentRules.get(5);

                // saves the second rule id in a variable to make it more readable
                sqlConfiguration.deleteOldRule(sixthRuleId);
                System.out.println("Old rule deleted.");
            } else {
                System.out.println("Rule #6 was not created.");
            }
        }
        // updates rule_count column
        sqlConfiguration.updateRuleCount(rulesetName, userEmail);
    }

    public void onAddRuleButtonClick() {
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