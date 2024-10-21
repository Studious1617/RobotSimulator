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
import java.util.Optional;
import java.util.ResourceBundle;

public class FactoryLayoutController implements Initializable {
    @FXML
    public Button saveButton;
    public Button FL_BackButton;
    public Label messageLabel;

    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    ChoiceBox<String>
            CB_00, CB_10, CB_20, CB_30, CB_40,
            CB_01, CB_11, CB_21, CB_31, CB_41,
            CB_02, CB_12, CB_22, CB_32, CB_42,
            CB_03, CB_13, CB_23, CB_33, CB_43,
            CB_04, CB_14, CB_24, CB_34, CB_44;

    public ChoiceBox<String>[] choiceBoxes = new ChoiceBox[]{
            CB_00, CB_10, CB_20, CB_30, CB_40,
            CB_01, CB_11, CB_21, CB_31, CB_41,
            CB_02, CB_12, CB_22, CB_32, CB_42,
            CB_03, CB_13, CB_23, CB_33, CB_43,
            CB_04, CB_14, CB_24, CB_34, CB_44};

    public void getBoxValue() {
        for (int i = 0; i < choiceBoxes.length; i++) {

        }
    }

    private String emailAddress;
    @FXML
    public TextField factoryLayoutName;
    @FXML
    ChoiceBox<String> robotDirectionCB;

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFactoryLayoutName() {
        return factoryLayoutName.getText();
    }
    @FXML
    public String[] getChoiceBox() {
        // makes a new list of strings
        String[] choiceBoxList = new String[choiceBoxes.length];
        // integer variable to be the list's index
        int cbIndex = 0;
        for (ChoiceBox<String> choiceBox: choiceBoxes) {
            // adds the "Open" value to the list if the user left the space blank
            if (choiceBox == null) {
                choiceBoxList[cbIndex++] = "Open";
            } else {
            // adds the value the user entered into the space to the list
                choiceBoxList[cbIndex++] = choiceBox.getValue();
            }
        }
        return choiceBoxList;
    }
    public String getRobotDirectionCB() {
        return robotDirectionCB.getValue();
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        ChoiceBox<String>[] initializeChoiceBoxes = new ChoiceBox[]{
                CB_00, CB_10, CB_20, CB_30, CB_40,
                CB_01, CB_11, CB_21, CB_31, CB_41,
                CB_02, CB_12, CB_22, CB_32, CB_42,
                CB_03, CB_13, CB_23, CB_33, CB_43,
                CB_04, CB_14, CB_24, CB_34, CB_44};

        // sets the options for all the choiceboxes
        ObservableList<String> choiceBoxOptions = FXCollections.observableArrayList("Start", "Open", "Wall", "Exit");
        ObservableList<String> robotDirection = FXCollections.observableArrayList("Front", "Left", "Right", "Back");

        // adds the options
        for (ChoiceBox<String> box : initializeChoiceBoxes){
            box.getItems().addAll(choiceBoxOptions);
        }
        robotDirectionCB.getItems().addAll(robotDirection);
    }

    @FXML
    public void onBackButton(ActionEvent event) throws Exception {
        // set layout info to dashboard
        Parent popup = FXMLLoader.load(getClass().getResource("Dashboard"));
        Stage stageThree = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(popup);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }

    public boolean startEndBoxExist (ChoiceBox<String>[] choiceBox) {
        int startBox = 0;
        int endBox = 0;

        for (ChoiceBox<String> box : choiceBox) {
            if (box != null) {
                if (box.getValue().equals("Start")) startBox++;
                if (box.getValue().equals("Exit")) endBox++;
            }
        }
        return startBox == 1 && endBox == 1;
    }

    @FXML
    public void onSaveLayoutButton(ActionEvent e) throws Exception {
        // gets the info to save the layout to the database/table
        String userEmail = getEmailAddress();
        String layoutName = getFactoryLayoutName();
        String[] cbData = getChoiceBox();
        String directionValue = getRobotDirectionCB();
        int layoutID = sqlConfiguration.getLayoutID(layoutName);

        // enter the data into the layouts table
        if (startEndBoxExist(choiceBoxes) && !layoutName.trim().isEmpty()) {
            sqlConfiguration.insertLayout(userEmail, layoutName, cbData, directionValue, layoutID);
            messageLabel.setText("Layout saved");
        } else if (layoutName == null) {
            System.out.println("Enter a name for the layout.");
        } else {
            System.out.println("You need one Start box and one Exit box selected to save the layout.");
        }

    }


}