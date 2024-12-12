package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.demo.CreateUserAccountController.userEmail;
import static com.example.demo.DashboardController.*;

public class FactoryLayoutViewController {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public Label layoutNameLabel, robotDirectionLabel;
    public Button FLV_BackButton, viewButton;

    @FXML
    public Pane
            P_00, P_10, P_20, P_30, P_40,
            P_01, P_11, P_21, P_31, P_41,
            P_02, P_12, P_22, P_32, P_42,
            P_03, P_13, P_23, P_33, P_43,
            P_04, P_14, P_24, P_34, P_44;

    // arraylist to chang Pane[] array to arraylist<Pane>
    public ArrayList<Pane> arrayListForPanes = new ArrayList<>();

    public void onViewButtonClick () {
        layoutName = layoutNameLabel.getText();
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        // gets layout grid data
        layoutData = (ArrayList<String>) sqlConfiguration.getUserLayouts(userEmail).get(layoutId);

        // adds the panes to an arraylist
        arrayListForPanes.addAll(Arrays.asList(
                P_00, P_10, P_20, P_30, P_40,
                P_01, P_11, P_21, P_31, P_41,
                P_02, P_12, P_22, P_32, P_42,
                P_03, P_13, P_23, P_33, P_43,
                P_04, P_14, P_24, P_34, P_44));

        // loops through the arraylist
        for (int i = 0; i < arrayListForPanes.size(); i++){
            // gets the index of both lists
            String typeOfBox = layoutData.get(i);
            Pane pane = arrayListForPanes.get(i);

            // sets the color of the pane based on the value of the choiceBox
            if (typeOfBox.equals("Start")) {
                pane.setStyle("-fx-background-color: darkgreen;");
            } else if (typeOfBox.equals("Exit")) {
                pane.setStyle("-fx-background-color: red");
            } else if (typeOfBox.equals("Wall")) {
                pane.setStyle("-fx-background-color: grey");
            } else {
                pane.setStyle("-fx-background-color: blue");
            }
        }
    }

    @FXML
    public void onBackButton(ActionEvent event) throws Exception {
        // sets user email instance into Dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController dashboardController = loader.getController();

        // reveals the user's layouts
        dashboardController.makeUserLayoutVisible();

        Stage stageThree = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(dashboardPopUp,1540,800);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }
}