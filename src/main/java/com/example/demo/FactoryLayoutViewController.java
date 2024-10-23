package com.example.demo;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FactoryLayoutViewController {//} implements Initializable {
    @FXML
    Pane
            P_00, P_10, P_20, P_30, P_40,
            P_01, P_11, P_21, P_31, P_41,
            P_02, P_12, P_22, P_32, P_42,
            P_03, P_13, P_23, P_33, P_43,
            P_04, P_14, P_24, P_34, P_44;

    List<Pane> paneList = Arrays.asList(
            P_00, P_10, P_20, P_30, P_40,
            P_01, P_11, P_21, P_31, P_41,
            P_02, P_12, P_22, P_32, P_42,
            P_03, P_13, P_23, P_33, P_43,
            P_04, P_14, P_24, P_34, P_44);
    @FXML
    private Label layoutNameLabel;
    @FXML
    private Label robotDirectionCB;
    private String emailAddress;
    //private Legend.LegendItem layoutTextArea;  //Might be the cause of issues in the future? Make sure to check

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Layout> layouts;

    public List<Layout> getLayouts() {
        return layouts;
    }
    public void setLayouts(List<Layout> layouts) {
        this.layouts = layouts;
    }

    public int index;

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    @FXML
    public Button FLV_BackButton;

//    @Override
//    public void initialize (URL url, ResourceBundle resourceBundle) {
//        String layoutName = layouts.get(index).getLayoutName();
//        String direction = layouts.get(index).getDirectionValue();
//        layoutNameLabel.setText(layoutName);
//        robotDirectionCB.setText(direction);
//        // adds the options
//        String[] choiceBoxes = layouts.get(index).getLayoutData();
//        for (String cell : choiceBoxes){
//            if (cell.equals("Start")) {
//                cell.setStyle("-fx-background-color: dark green;");
//            } else if (cell.getValue().equals("Open")) {
//                cell.setStyle("-fx-background-color: blue");
//            } else if (cell.getValue().equals("Wall")) {
//                cell.setStyle("-fx-background-color: grey");
//            } else {
//                cell.setStyle("-fx-background-color: red");
//            }
//        }
//    }

    // Method to view the current selections
//    public void viewLayout() {
//        ChoiceBox<String>[] choiceBoxes = new ChoiceBox[]{
//                CB_00, CB_10, CB_20, CB_30, CB_40,
//                CB_01, CB_11, CB_21, CB_31, CB_41,
//                CB_02, CB_12, CB_22, CB_32, CB_42,
//                CB_03, CB_13, CB_23, CB_33, CB_43,
//                CB_04, CB_14, CB_24, CB_34, CB_44};
//
//        StringBuilder layoutInfo = new StringBuilder("Current Layout:\n");
//        for (int i = 0; i < choiceBoxes.length; i++) {
//            ChoiceBox<String> cell = choiceBoxes[i];
//            String selection = cell.getValue();
//            layoutInfo.append("Cell ").append(i + 1).append(": ").append(selection != null ? selection : "Not set").append("\n");
//        }
//        layoutInfo.append("\nRobot Direction: ").append(robotDirectionCB.getValue() != null ? robotDirectionCB.getValue() : "Not set");
//
//        layoutTextArea.setText(layoutInfo.toString());
//    }

    @FXML
    public void onBackButton(ActionEvent event) throws Exception {
        // sets user email instance into Dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController dashboardController = loader.getController();
        String email = getEmailAddress();

        dashboardController.setEmailAddress(email);
        dashboardController.setLayouts(layouts);
        // reveals the user's layouts
        dashboardController.makeUserLayoutVisible();

        Stage stageThree = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(dashboardPopUp);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }

}