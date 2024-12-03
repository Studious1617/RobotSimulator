package com.example.demo;

import javafx.css.Rule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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

    public String rulesetName;
    public String getRulesetName() {
        return rulesetName;
    }
    public void setRulesetName(String rulesetName) {
        this.rulesetName = rulesetName;
    }

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

    @FXML
    public void onViewButtonClick () {
        // variables to hold chosen rule checkboxes
        String when, is1, then, and1, is2, and2, is3, and3, is4;

        // gets ruleset name
        rulesetName = rulesetNameLabel.getText();
        System.out.println("Ruleset name: " + rulesetName);

        // gets ruleset id
        int rulesetId = sqlConfiguration.getRulesetId(rulesetName, getUserEmail());
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

        // puts rule info into labels
            // first rule
            if (!rule1.isEmpty()) {
                when = rule1.getFirst();
                if (when == null) {
                    when = "XXXXXXXX";
                }

                is1 = rule1.get(1);
                if (is1 == null) {
                    is1 = "XXXXXXXX";
                }

                then = rule1.get(2);
                if (then == null) {
                    then = "XXXXXXXX";
                }

                and1 = rule1.get(3);
                if (and1 == null) {
                    and1 = "XXXXXXXX";
                }

                is2 = rule1.get(4);
                if (is2 == null) {
                    is2 = "XXXXXXXX";
                }

                and2 = rule1.get(5);
                if (and2 == null) {
                    and2 = "XXXXXXXX";
                }

                is3 = rule1.get(6);
                if (is3 == null) {
                    is3 = "XXXXXXXX";
                }

                and3 = rule1.get(7);
                if (and3 == null) {
                    and3 = "XXXXXXXX";
                }

                is4 = rule1.get(8);
                if (is4 == null) {
                    is4 = "XXXXXXXX";
                }

                // puts the values into the labels
                whenLabel_Rule1.setText(when);
                is1Label_Rule1.setText(is1);
                thenLabel_Rule1.setText(then);

                and1Label_Rule1.setText(and1);
                is2Label_Rule1.setText(is2);
                and2Label_Rule1.setText(and2);
                is3Label_Rule1.setText(is3);
                and3Label_Rule1.setText(and3);
                is4Label_Rule1.setText(is4);
            }
            // second rule
            if (!rule2.isEmpty()) {
                when = rule2.getFirst();
                if (when == null) {
                    when = "XXXXXXXX";
                }

                is1 = rule2.get(1);
                if (is1 == null) {
                    is1 = "XXXXXXXX";
                }

                then = rule2.get(2);
                if (then == null) {
                    then = "XXXXXXXX";
                }

                and1 = rule2.get(3);
                if (and1 == null) {
                    and1 = "XXXXXXXX";
                }

                is2 = rule2.get(4);
                if (is2 == null) {
                    is2 = "XXXXXXXX";
                }

                and2 = rule2.get(5);
                if (and2 == null) {
                    and2 = "XXXXXXXX";
                }

                is3 = rule2.get(6);
                if (is3 == null) {
                    is3 = "XXXXXXXX";
                }

                and3 = rule2.get(7);
                if (and3 == null) {
                    and3 = "XXXXXXXX";
                }

                is4 = rule2.get(8);
                if (is4 == null) {
                    is4 = "XXXXXXXX";
                }

                // puts the values into the labels
                whenLabel_Rule2.setText(when);
                is1Label_Rule2.setText(is1);
                thenLabel_Rule2.setText(then);

                and1Label_Rule2.setText(and1);
                is2Label_Rule2.setText(is2);
                and2Label_Rule2.setText(and2);
                is3Label_Rule2.setText(is3);
                and3Label_Rule2.setText(and3);
                is4Label_Rule2.setText(is4);
            }
            // third rule
            if (!rule3.isEmpty()) {
                when = rule3.getFirst();
                if (when == null) {
                    when = "XXXXXXXX";
                }

                is1 = rule3.get(1);
                if (is1 == null) {
                    is1 = "XXXXXXXX";
                }

                then = rule3.get(2);
                if (then == null) {
                    then = "XXXXXXXX";
                }

                and1 = rule3.get(3);
                if (and1 == null) {
                    and1 = "XXXXXXXX";
                }

                is2 = rule3.get(4);
                if (is2 == null) {
                    is2 = "XXXXXXXX";
                }

                and2 = rule3.get(5);
                if (and2 == null) {
                    and2 = "XXXXXXXX";
                }

                is3 = rule3.get(6);
                if (is3 == null) {
                    is3 = "XXXXXXXX";
                }

                and3 = rule3.get(7);
                if (and3 == null) {
                    and3 = "XXXXXXXX";
                }

                is4 = rule3.get(8);
                if (is4 == null) {
                    is4 = "XXXXXXXX";
                }

                // puts the values into the labels
                whenLabel_Rule3.setText(when);
                is1Label_Rule3.setText(is1);
                thenLabel_Rule3.setText(then);

                and1Label_Rule3.setText(and1);
                is2Label_Rule3.setText(is2);
                and2Label_Rule3.setText(and2);
                is3Label_Rule3.setText(is3);
                and3Label_Rule3.setText(and3);
                is4Label_Rule3.setText(is4);
            }
            // fourth rule
            if (!rule4.isEmpty()) {
                when = rule4.getFirst();
                if (when == null) {
                    when = "XXXXXXXX";
                }

                is1 = rule4.get(1);
                if (is1 == null) {
                    is1 = "XXXXXXXX";
                }

                then = rule4.get(2);
                if (then == null) {
                    then = "XXXXXXXX";
                }

                and1 = rule4.get(3);
                if (and1 == null) {
                    and1 = "XXXXXXXX";
                }

                is2 = rule4.get(4);
                if (is2 == null) {
                    is2 = "XXXXXXXX";
                }

                and2 = rule4.get(5);
                if (and2 == null) {
                    and2 = "XXXXXXXX";
                }

                is3 = rule4.get(6);
                if (is3 == null) {
                    is3 = "XXXXXXXX";
                }

                and3 = rule4.get(7);
                if (and3 == null) {
                    and3 = "XXXXXXXX";
                }

                is4 = rule4.get(8);
                if (is4 == null) {
                    is4 = "XXXXXXXX";
                }

                // puts the values into the labels
                whenLabel_Rule4.setText(when);
                is1Label_Rule4.setText(is1);
                thenLabel_Rule4.setText(then);

                and1Label_Rule4.setText(and1);
                is2Label_Rule4.setText(is2);
                and2Label_Rule4.setText(and2);
                is3Label_Rule4.setText(is3);
                and3Label_Rule4.setText(and3);
                is4Label_Rule4.setText(is4);
            }
            // fifth rule
            if (!rule5.isEmpty()) {
                when = rule5.getFirst();
                if (when == null) {
                    when = "XXXXXXXX";
                }

                is1 = rule5.get(1);
                if (is1 == null) {
                    is1 = "XXXXXXXX";
                }

                then = rule5.get(2);
                if (then == null) {
                    then = "XXXXXXXX";
                }

                and1 = rule5.get(3);
                if (and1 == null) {
                    and1 = "XXXXXXXX";
                }

                is2 = rule5.get(4);
                if (is2 == null) {
                    is2 = "XXXXXXXX";
                }

                and2 = rule5.get(5);
                if (and2 == null) {
                    and2 = "XXXXXXXX";
                }

                is3 = rule5.get(6);
                if (is3 == null) {
                    is3 = "XXXXXXXX";
                }

                and3 = rule5.get(7);
                if (and3 == null) {
                    and3 = "XXXXXXXX";
                }

                is4 = rule5.get(8);
                if (is4 == null) {
                    is4 = "XXXXXXXX";
                }

                // puts the values into the labels
                whenLabel_Rule5.setText(when);
                is1Label_Rule5.setText(is1);
                thenLabel_Rule5.setText(then);

                and1Label_Rule5.setText(and1);
                is2Label_Rule5.setText(is2);
                and2Label_Rule5.setText(and2);
                is3Label_Rule5.setText(is3);
                and3Label_Rule5.setText(and3);
                is4Label_Rule5.setText(is4);
            }
            // sixth rule
            if (!rule6.isEmpty()) {
                when = rule6.getFirst();
                if (when == null) {
                    when = "XXXXXXXX";
                }

                is1 = rule6.get(1);
                if (is1 == null) {
                    is1 = "XXXXXXXX";
                }

                then = rule6.get(2);
                if (then == null) {
                    then = "XXXXXXXX";
                }

                and1 = rule6.get(3);
                if (and1 == null) {
                    and1 = "XXXXXXXX";
                }

                is2 = rule6.get(4);
                if (is2 == null) {
                    is2 = "XXXXXXXX";
                }

                and2 = rule6.get(5);
                if (and2 == null) {
                    and2 = "XXXXXXXX";
                }

                is3 = rule6.get(6);
                if (is3 == null) {
                    is3 = "XXXXXXXX";
                }

                and3 = rule6.get(7);
                if (and3 == null) {
                    and3 = "XXXXXXXX";
                }

                is4 = rule6.get(8);
                if (is4 == null) {
                    is4 = "XXXXXXXX";
                }

                // puts the values into the labels
                whenLabel_Rule6.setText(when);
                is1Label_Rule6.setText(is1);
                thenLabel_Rule6.setText(then);

                and1Label_Rule6.setText(and1);
                is2Label_Rule6.setText(is2);
                and2Label_Rule6.setText(and2);
                is3Label_Rule6.setText(is3);
                and3Label_Rule6.setText(and3);
                is4Label_Rule6.setText(is4);
            }
    }

    @FXML
    public void onBackButtonClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsDashboard.fxml"));
        Parent rulesetsDashboardPopUp = loader.load();
        RulesetsDashboard rulesetsDashboardV = loader.getController();
        // so the user's layout info doesn't get lost
        rulesetsDashboardV.setUserEmail(getUserEmail());
        rulesetsDashboardV.setListOfLayouts(getListOfLayouts());
        rulesetsDashboardV.makeUserRulesetsVisible();

        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(rulesetsDashboardPopUp, 1920, 1080);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }
}
