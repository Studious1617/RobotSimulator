package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FactoryLayoutController {
    @FXML
    public ChoiceBox CB_00;
    public ChoiceBox CB_10;
    public ChoiceBox CB_20;
    public ChoiceBox CB_30;
    public ChoiceBox CB_40;

    public ChoiceBox CB_01;
    public ChoiceBox CB_11;
    public ChoiceBox CB_21;
    public ChoiceBox CB_31;
    public ChoiceBox CB_41;

    public ChoiceBox CB_02;
    public ChoiceBox CB_12;
    public ChoiceBox CB_22;
    public ChoiceBox CB_32;
    public ChoiceBox CB_42;

    public ChoiceBox CB_03;
    public ChoiceBox CB_13;
    public ChoiceBox CB_23;
    public ChoiceBox CB_33;
    public ChoiceBox CB_43;

    public ChoiceBox CB_04;
    public ChoiceBox CB_14;
    public ChoiceBox CB_24;
    public ChoiceBox CB_34;
    public ChoiceBox CB_44;

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

    }
}