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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FactoryLayoutController implements Initializable {
    @FXML
    ChoiceBox<String>
            CB_00, CB_10, CB_20, CB_30, CB_40,
            CB_01, CB_11, CB_21, CB_31, CB_41,
            CB_02, CB_12, CB_22, CB_32, CB_42,
            CB_03, CB_13, CB_23, CB_33, CB_43,
            CB_04, CB_14, CB_24, CB_34, CB_44;
    @FXML
    ChoiceBox<String> robotDirectionCB;

    private String emailAddress;
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    @FXML
    public TextField factoryLayoutName;

    public ChoiceBox<String>[] choiceBoxes = new ChoiceBox[]{
            CB_00, CB_10, CB_20, CB_30, CB_40,
            CB_01, CB_11, CB_21, CB_31, CB_41,
            CB_02, CB_12, CB_22, CB_32, CB_42,
            CB_03, CB_13, CB_23, CB_33, CB_43,
            CB_04, CB_14, CB_24, CB_34, CB_44};

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {

        // sets the options for all the choiceboxes
        ObservableList<String> choiceBoxOptions = FXCollections.observableArrayList("Start", "Open", "Wall", "Exit");
        ObservableList<String> robotDirection = FXCollections.observableArrayList("Front", "Left", "Right", "Back");

        // adds the options
        for (ChoiceBox<String> box : choiceBoxes){
            box.setItems(choiceBoxOptions);
        }
        robotDirectionCB.setItems(robotDirection);
    }

    @FXML
    public void onBackButton(ActionEvent event) throws Exception {
        Parent popUp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stageThree = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(popUp);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }

    @FXML
    public void onSaveLayoutButton(ActionEvent e) throws Exception {
        // gets the info to save the layout to the database/table
        String userEmail = getEmailAddress();
        String layoutName = factoryLayoutName.getText();
        // stream to get the choice boxes' value into a list
        List<String> choiceboxStream = Arrays.stream(choiceBoxes)
                .map(choiceBoxes -> choiceBoxes.getValue())
                .toList();
        // get the value from the robotDirection choicebox
        String directionValue = robotDirectionCB.getValue();
        // enter the data into the layouts table

    }
}