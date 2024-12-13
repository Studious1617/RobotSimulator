package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.example.demo.CreateUserAccountController.userEmail;
import static com.example.demo.RulesetsDashboard.rulesetId;
import static com.example.demo.RulesetsDashboard.rulesetName;

public class RulesetsView {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();
    @FXML
    public Button backButton;

    public Label rulesetNameLabel,
            whenLabel_Rule1, is1Label_Rule1,
            and1Label_Rule1, is2Label_Rule1,
            and2Label_Rule1, is3Label_Rule1,
            and3Label_Rule1, is4Label_Rule1,
            thenLabel_Rule1,

            whenLabel_Rule2, is1Label_Rule2,
            and1Label_Rule2, is2Label_Rule2,
            and2Label_Rule2, is3Label_Rule2,
            and3Label_Rule2, is4Label_Rule2,
            thenLabel_Rule2,

            whenLabel_Rule3, is1Label_Rule3,
            and1Label_Rule3, is2Label_Rule3,
            and2Label_Rule3, is3Label_Rule3,
            and3Label_Rule3, is4Label_Rule3,
            thenLabel_Rule3,

            whenLabel_Rule4, is1Label_Rule4,
            and1Label_Rule4, is2Label_Rule4,
            and2Label_Rule4, is3Label_Rule4,
            and3Label_Rule4, is4Label_Rule4,
            thenLabel_Rule4,

            whenLabel_Rule5, is1Label_Rule5,
            and1Label_Rule5, is2Label_Rule5,
            and2Label_Rule5, is3Label_Rule5,
            and3Label_Rule5, is4Label_Rule5,
            thenLabel_Rule5,

            whenLabel_Rule6, is1Label_Rule6,
            and1Label_Rule6, is2Label_Rule6,
            and2Label_Rule6, is3Label_Rule6,
            and3Label_Rule6, is4Label_Rule6,
            thenLabel_Rule6;

    @FXML
    public void onViewButtonClick () {
        // gets ruleset name
        rulesetName = rulesetNameLabel.getText();
        System.out.println("Ruleset name: " + rulesetName);

        // gets ruleset id
        rulesetId = sqlConfiguration.getRulesetId(rulesetName, userEmail);
        System.out.println("Ruleset ID: " + rulesetId);

        // gets rule ids
        ArrayList<Integer> ruleIds = sqlConfiguration.getRuleId(rulesetId);
            // first rule id
            int ruleId1 = 0;
            if (ruleIds.size() == 1) {
                ruleId1 = ruleIds.getFirst();
            }

            // second rule id
            int ruleId2 = 0;
            if (ruleIds.size() == 2) {
                ruleId1 = ruleIds.getFirst();
                ruleId2 = ruleIds.get(1);
            }

            // third rule id
            int ruleId3 = 0;
            if (ruleIds.size() == 3) {
                ruleId1 = ruleIds.getFirst();
                ruleId2 = ruleIds.get(1);
                ruleId3 = ruleIds.get(2);
            }

            // fourth rule id
            int ruleId4 = 0;
            if (ruleIds.size() == 4) {
                ruleId1 = ruleIds.getFirst();
                ruleId2 = ruleIds.get(1);
                ruleId3 = ruleIds.get(2);
                ruleId4 = ruleIds.get(3);
            }

            // fifth rule id
            int ruleId5 = 0;
            if (ruleIds.size() == 5) {
                ruleId1 = ruleIds.getFirst();
                ruleId2 = ruleIds.get(1);
                ruleId3 = ruleIds.get(2);
                ruleId4 = ruleIds.get(3);
                ruleId5 = ruleIds.get(4);
            }

            // sixth rule id
            int ruleId6 = 0;
            if (ruleIds.size() == 6) {
                ruleId1 = ruleIds.getFirst();
                ruleId2 = ruleIds.get(1);
                ruleId3 = ruleIds.get(2);
                ruleId4 = ruleIds.get(3);
                ruleId5 = ruleIds.get(4);
                ruleId6 = ruleIds.get(5);
            }
            System.out.println("Rule ID 1: " + ruleId1);
            System.out.println("Rule ID 2: " + ruleId2);
            System.out.println("Rule ID 3: " + ruleId3);
            System.out.println("Rule ID 4: " + ruleId4);
            System.out.println("Rule ID 5: " + ruleId5);
            System.out.println("Rule ID 6: " + ruleId6);

        // gets rules from table
            // first rule
            ArrayList<String> rule1 = sqlConfiguration.getRules(rulesetId).get(ruleId1);
            System.out.println("Rule #1: " + rule1);

            // second rule
            ArrayList<String> rule2 = sqlConfiguration.getRules(rulesetId).get(ruleId2);
            System.out.println("Rule #2: " + rule2);

            // third rule
            ArrayList<String> rule3 = sqlConfiguration.getRules(rulesetId).get(ruleId3);
            System.out.println("Rule #3: " + rule3);

            // fourth rule
            ArrayList<String> rule4 = sqlConfiguration.getRules(rulesetId).get(ruleId4);
            System.out.println("Rule #4: " + rule4);

            // fifth rule
            ArrayList<String> rule5 = sqlConfiguration.getRules(rulesetId).get(ruleId5);
            System.out.println("Rule #5: " + rule5);

            // sixth rule
            ArrayList<String> rule6 = sqlConfiguration.getRules(rulesetId).get(ruleId6);
            System.out.println("Rule #6: " + rule6);

        // puts rule info into labels using checkRules method
            // first rule
            checkAndPutRulesIntoLabels(rule1, whenLabel_Rule1, is1Label_Rule1, and1Label_Rule1,
                    is2Label_Rule1, and2Label_Rule1, is3Label_Rule1, and3Label_Rule1, is4Label_Rule1, thenLabel_Rule1);
            // second rule
            checkAndPutRulesIntoLabels(rule2, whenLabel_Rule2, is1Label_Rule2, and1Label_Rule2,
                    is2Label_Rule2, and2Label_Rule2, is3Label_Rule2, and3Label_Rule2, is4Label_Rule2, thenLabel_Rule2);
            // third rule
            checkAndPutRulesIntoLabels(rule3, whenLabel_Rule3, is1Label_Rule3, and1Label_Rule3,
                    is2Label_Rule3, and2Label_Rule3, is3Label_Rule3, and3Label_Rule3, is4Label_Rule3, thenLabel_Rule3);
            // fourth rule
            checkAndPutRulesIntoLabels(rule4, whenLabel_Rule4, is1Label_Rule4, and1Label_Rule4,
                    is2Label_Rule4, and2Label_Rule4, is3Label_Rule4, and3Label_Rule4, is4Label_Rule4, thenLabel_Rule4);
            // fifth rule
            checkAndPutRulesIntoLabels(rule5, whenLabel_Rule5, is1Label_Rule5, and1Label_Rule5,
                    is2Label_Rule5, and2Label_Rule5, is3Label_Rule5, and3Label_Rule5, is4Label_Rule5, thenLabel_Rule5);
            // sixth rule
            checkAndPutRulesIntoLabels(rule6, whenLabel_Rule6, is1Label_Rule6, and1Label_Rule6,
                    is2Label_Rule6, and2Label_Rule6, is3Label_Rule6, and3Label_Rule6, is4Label_Rule6, thenLabel_Rule6);
    }

    public void checkAndPutRulesIntoLabels(ArrayList<String> rule, Label whenLabel, Label is1Label,
           Label and1Label, Label is2Label, Label and2Label, Label is3Label, Label and3Label, Label is4Label, Label thenLabel) {
        // variables to hold the conditions
        String when, is1, and1, is2, and2, is3, and3, is4, then;
        // checks if the rule exists and not blank
        if (rule != null && !rule.isEmpty()) {
            when = rule.getFirst();
            if (when == null) {
                when = "XXXXXXXX";
            }

            is1 = rule.get(1);
            if (is1 == null) {
                is1 = "XXXXXXXX";
            }

            and1 = rule.get(2);
            if (and1 == null) {
                and1 = "XXXXXXXX";
            }

            is2 = rule.get(3);
            if (is2 == null) {
                is2 = "XXXXXXXX";
            }

            and2 = rule.get(4);
            if (and2 == null) {
                and2 = "XXXXXXXX";
            }

            is3 = rule.get(5);
            if (is3 == null) {
                is3 = "XXXXXXXX";
            }

            and3 = rule.get(6);
            if (and3 == null) {
                and3 = "XXXXXXXX";
            }

            is4 = rule.get(7);
            if (is4 == null) {
                is4 = "XXXXXXXX";
            }

            then = rule.get(8);
            if (then == null) {
                then = "XXXXXXXX";
            }

            // puts the values into the labels
            whenLabel.setText(when);
            is1Label.setText(is1);

            and1Label.setText(and1);
            is2Label.setText(is2);
            and2Label.setText(and2);
            is3Label.setText(is3);
            and3Label.setText(and3);
            is4Label.setText(is4);

            thenLabel.setText(then);
        }
    }

    @FXML
    public void onBackButtonClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsDashboard.fxml"));
        Parent rulesetsDashboardPopUp = loader.load();
        RulesetsDashboard rulesetsDashboardV = loader.getController();

        rulesetsDashboardV.makeUserRulesetsVisible();

        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(rulesetsDashboardPopUp, 1540, 1080);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }
}