package com.example.demo;

import com.sun.javafx.charts.Legend;
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
import java.util.ResourceBundle;

public class FactoryLayoutView1Controller implements Initializable {
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
    private Legend.LegendItem layoutTextArea;  //Might be cause of issues in the future? Make sure to check

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    @FXML
    public TextField FactoryLayoutName;
    public Button FLV_BackButton;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        ChoiceBox<String>[] choiceBoxes = new ChoiceBox[]{
                CB_00, CB_10, CB_20, CB_30, CB_40,
                CB_01, CB_11, CB_21, CB_31, CB_41,
                CB_02, CB_12, CB_22, CB_32, CB_42,
                CB_03, CB_13, CB_23, CB_33, CB_43,
                CB_04, CB_14, CB_24, CB_34, CB_44};

        // sets the options for all the choiceboxes
        ObservableList<String> choiceBoxOptions = FXCollections.observableArrayList("Start", "Open", "Wall", "Exit");
        ObservableList<String> robotDirection = FXCollections.observableArrayList("Front", "Left", "Right", "Back");
        // adds the options
        for (ChoiceBox<String> cell : choiceBoxes){
            cell.getItems().addAll(choiceBoxOptions);
        }
        robotDirectionCB.getItems().addAll(robotDirection);
    }

    // Method to view the current selections
    public void viewLayout() {
        ChoiceBox<String>[] choiceBoxes = new ChoiceBox[]{
                CB_00, CB_10, CB_20, CB_30, CB_40,
                CB_01, CB_11, CB_21, CB_31, CB_41,
                CB_02, CB_12, CB_22, CB_32, CB_42,
                CB_03, CB_13, CB_23, CB_33, CB_43,
                CB_04, CB_14, CB_24, CB_34, CB_44};

        StringBuilder layoutInfo = new StringBuilder("Current Layout:\n");
        for (int i = 0; i < choiceBoxes.length; i++) {
            ChoiceBox<String> cell = choiceBoxes[i];
            String selection = cell.getValue();
            layoutInfo.append("Cell ").append(i).append(": ").append(selection != null ? selection : "Not set").append("\n");
        }
        layoutInfo.append("Robot Direction: ").append(robotDirectionCB.getValue() != null ? robotDirectionCB.getValue() : "Not set");

        layoutTextArea.setText(layoutInfo.toString());
    }

    @FXML
    public void onBackButton(ActionEvent event) throws Exception {
        Parent popUp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stageThree = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(popUp,1920,1080);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }

}