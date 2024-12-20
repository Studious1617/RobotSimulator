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
import java.util.*;

import static com.example.demo.CreateUserAccountController.userEmail;
import static com.example.demo.DashboardController.*;
import static com.example.demo.LogInController.layouts;

public class FactoryLayoutController implements Initializable {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public Button saveButton, FL_BackButton;
    public Label messageLabel;

    @FXML
    public ChoiceBox<String>
            CB_00, CB_10, CB_20, CB_30, CB_40,
            CB_01, CB_11, CB_21, CB_31, CB_41,
            CB_02, CB_12, CB_22, CB_32, CB_42,
            CB_03, CB_13, CB_23, CB_33, CB_43,
            CB_04, CB_14, CB_24, CB_34, CB_44;

    @FXML
    public TextField factoryLayoutName;
    public ChoiceBox<String> robotDirectionCB;

    // changed to arraylist
    public ArrayList<ChoiceBox<String>> arrayListForChoiceBoxes = new ArrayList<>();

    // arrayList for the chosen choiceBox options
    public ArrayList<String> choiceBoxChosenValueArrayList = new ArrayList<>();

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
        ObservableList<String> robotDirection = FXCollections.observableArrayList("Front", "Left", "Right", "Back");

        // adds the initialized choiceboxes to the arrayList
        arrayListForChoiceBoxes.addAll(Arrays.asList(
                CB_00, CB_10, CB_20, CB_30, CB_40,
                CB_01, CB_11, CB_21, CB_31, CB_41,
                CB_02, CB_12, CB_22, CB_32, CB_42,
                CB_03, CB_13, CB_23, CB_33, CB_43,
                CB_04, CB_14, CB_24, CB_34, CB_44));

        // adds the options to the choiceboxes
        for (ChoiceBox<String> choiceBox: arrayListForChoiceBoxes) {
            choiceBox.getItems().addAll(choiceBoxOptions);
        }
        // adds the directions to the choiceBox
        robotDirectionCB.getItems().addAll(robotDirection);
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

    public void onSaveLayoutButton() {
        // makes sure the static values are updated
        layoutName = factoryLayoutName.getText();
        System.out.println("Updated name (create): " + layoutName);

        layoutData = chosenChoiceBoxValueArrayList();
        System.out.println("\nUpdated grid data (create): " + layoutData);

        robotDirection = robotDirectionCB.getValue();
        System.out.println("\nUpdated direction (create): " + robotDirection);

        // enter the data into the layouts table
        if (startEndBoxExist(layoutData) && !layoutName.isEmpty()) {
            // enters the layout into the table
            sqlConfiguration.insertLayout(layoutName, layoutData, robotDirection, userEmail);
            messageLabel.setVisible(true);
        } else if (layoutName.isEmpty()) {
            messageLabel.setText("Enter a name for the layout.");

        } else if (robotDirection.isEmpty()) {
            messageLabel.setText("Enter a direction for the robot.");
        } else {
            messageLabel.setText("You need one Start box and one Exit box selected to save the layout.");
        }

        // checks the info to of the layout
        System.out.println("Layout ID (create): " + layoutId);
        System.out.println("Layout name (create): " + layoutName);
        System.out.println("Layout data (create): " + layoutData);
        System.out.println("Layout direction (create): " + robotDirection);
        System.out.println("Email address (create): " + userEmail);
    }

    public void onBackButton(ActionEvent event) throws Exception {
        // sets user email instance into Dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController dashboardController = loader.getController();

        // sets layouts map to current info from database
        layouts = sqlConfiguration.getUserLayouts(userEmail);
        // reveals the user's layouts
        if (!layouts.isEmpty()) {
            dashboardController.makeUserLayoutVisible();
        }

        Stage stageThree = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(dashboardPopUp,1920,1080);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }
}