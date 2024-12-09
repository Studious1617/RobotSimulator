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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.example.demo.CreateUserAccountController.userEmail;
import static com.example.demo.DashboardController.*;
import static com.example.demo.LogInController.layouts;

public class FactoryLayoutEditController implements Initializable {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public Button saveButton, FL_BackButton;
    public Label messageLabel;
    public TextField factoryLayoutName;
    public ChoiceBox<String> robotDirectionCB;

    @FXML
    public ChoiceBox<String>
            CB_00, CB_10, CB_20, CB_30, CB_40,
            CB_01, CB_11, CB_21, CB_31, CB_41,
            CB_02, CB_12, CB_22, CB_32, CB_42,
            CB_03, CB_13, CB_23, CB_33, CB_43,
            CB_04, CB_14, CB_24, CB_34, CB_44;

    // changed to arraylist
    public ArrayList<ChoiceBox<String>> arrayListForChoiceBoxes = new ArrayList<>();

    // arrayList for the chosen choiceBox options
    static ArrayList<String> choiceBoxChosenValueArrayList = new ArrayList<>();

    // returns the value from the choiceboxes
    public ArrayList<String> chosenChoiceBoxValueArrayList() {
        // integer variable to be the arraylist's index
        int cbIndex = 0;

        // adds the choiceBox value to the empty ArrayList<String>
        for (ChoiceBox<String> choiceBox: arrayListForChoiceBoxes) {
            choiceBoxChosenValueArrayList.add(choiceBox.getValue());
        }

        // loops through the arraylist of choiceboxes
        for (String value: choiceBoxChosenValueArrayList) {
            // adds the "Open" value to the list if the user left the space blank
            if (value == null) {
                choiceBoxChosenValueArrayList.set(cbIndex, "Open");

            } else {
                // adds the value the user entered into the space to the list
                choiceBoxChosenValueArrayList.set(cbIndex, value);
            }
            // increases the index by 1
            cbIndex++;
        }
        // returns the arraylist of values
        return choiceBoxChosenValueArrayList;
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        // sets the options for all the choiceboxes
        ObservableList<String> choiceBoxOptions = FXCollections.observableArrayList("Start", "Open", "Wall", "Exit");
        ObservableList<String> robotDirections = FXCollections.observableArrayList("Front", "Left", "Right", "Back");
        // enters all the choiceboxes into the arrayList
        arrayListForChoiceBoxes.addAll(Arrays.asList(
                CB_00, CB_10, CB_20, CB_30, CB_40,
                CB_01, CB_11, CB_21, CB_31, CB_41,
                CB_02, CB_12, CB_22, CB_32, CB_42,
                CB_03, CB_13, CB_23, CB_33, CB_43,
                CB_04, CB_14, CB_24, CB_34, CB_44));

        // gets the data from the database and stores it in variables for readability
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        layoutData = (ArrayList<String>) sqlConfiguration.getUserLayouts(userEmail).get(layoutId);
        robotDirection = sqlConfiguration.getRobotDirection(layoutId);

        // variable to keep track of the index
        int i = 0;

        // adds the layout options to the choiceboxes
        for (ChoiceBox<String> cb: arrayListForChoiceBoxes) {
            cb.getItems().addAll(choiceBoxOptions);
            // makes the saved grid data appear on the choiceboxes
            cb.setValue(layoutData.get(i));
            // increases the index value by 1
            i++;
        }

        robotDirectionCB.getItems().addAll(robotDirections);
        // makes the saved direction appear on the choiceBox
        robotDirectionCB.setValue(robotDirection);
    }

    public boolean startEndBoxExist (ArrayList<String> cbValueArrayList) {
        int startBox = 0;
        int endBox = 0;

        for (String value : cbValueArrayList) {
            if (value != null) {
                if (value.equals("Start")) startBox++;
                if (value.equals("Exit")) endBox++;
            }
        }
        return startBox == 1 && endBox == 1;
    }

    @FXML
    public void onSaveLayoutButton() {
        // makes sure the static values are updated
        System.out.println("Static name (edit): " + layoutName);
        layoutName = factoryLayoutName.getText();
        System.out.println("Updated name (edit): " + layoutName);

        System.out.println("Static id (edit): " + layoutId);
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        System.out.println("Updated id (edit): " + layoutId);

        System.out.println("\nStatic grid data (edit): " + layoutData);
        layoutData = chosenChoiceBoxValueArrayList();
        System.out.println("Updated grid data (edit): " + layoutData);

        System.out.println("\nStatic direction (edit): " + robotDirection);
        robotDirection = robotDirectionCB.getValue();
        System.out.println("Updated direction (edit): " + robotDirection);

        // enter the data into the layouts table
        if (startEndBoxExist(layoutData) && !layoutName.isEmpty()) {
            // enters the layout into the table
            sqlConfiguration.editLayout(layoutId, layoutName, layoutData, robotDirection, userEmail);
            messageLabel.setVisible(true);
        } else if (layoutName.isEmpty()) {
            System.out.println("Enter a name for the layout.");
        } else if (robotDirection.isEmpty()) {
            System.out.println("Enter a direction for the robot.");
        } else {
            System.out.println("You need one Start box and one Exit box selected to save the layout.");
        }

        // checks the info to of the layout
        System.out.println("\nLayout ID (edit): " + layoutId);
        System.out.println("Layout name (edit): " + layoutName);
        System.out.println("Layout data (edit): " + layoutData);
        System.out.println("Layout direction (edit): " + robotDirection);
        System.out.println("Email address (edit): " + userEmail);
    }

    @FXML
    public void onBackButton(ActionEvent e) throws Exception {
        // sets user email instance into Dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController dashboardController = loader.getController();

        // makes sure layouts is updated
        layouts = sqlConfiguration.getUserLayouts(userEmail);
        // makes user's layouts appear
        if (!layouts.isEmpty()) {
            dashboardController.makeUserLayoutVisible();
        }
        Stage stageThree = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(dashboardPopUp,1920,1080);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }
}