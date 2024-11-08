package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FactoryLayoutViewController implements Initializable {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();
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

    private int layoutId;
    private String layoutName;
    private String[] layoutData;
    private String layoutDirection;
    private String layoutEmail;

    public int getLayoutId() {
        return layoutId;
    }
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
    public String getLayoutName() {
        return layoutName;
    }
    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }
    public String[] getLayoutData() {
        return layoutData;
    }
    public void setLayoutData(String[] layoutData) {
        this.layoutData = layoutData;
    }
    public String getLayoutDirection() {
        return layoutDirection;
    }
    public void setLayoutDirection(String layoutDirection) {
        this.layoutDirection = layoutDirection;
    }
    public String getLayoutEmail() {
        return layoutEmail;
    }
    public void setLayoutEmail(String layoutEmail) {
        this.layoutEmail = layoutEmail;
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

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        layoutName = getLayoutName();
        layoutDirection = getLayoutDirection();

        layoutNameLabel.setText(layoutName);
        robotDirectionCB.setText(layoutDirection);
        // adds the layout data to the panes
        String[] choiceBoxes = getLayouts().get(index).getLayoutData();
        for (int i = 0; i < choiceBoxes.length; i++){
            // gets the index of both lists
            String typeOfBox = choiceBoxes[i];
            Pane pane = paneList.get(i);
            // sets the color of the pane based on its value
            if (typeOfBox.equals("Start")) {
                pane.setStyle("-fx-background-color: dark green;");
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
        layoutEmail = getLayoutEmail();

        dashboardController.setLayoutName(layoutName);
        dashboardController.setLayoutData(layoutData);
        dashboardController.setLayoutDirection(layoutDirection);
        index = dashboardController.buttonDifferentiation(event);

        layoutId = dashboardController.listOfLayouts.get(index).getLayoutID();
        System.out.println("ID: " + layoutId);

        layoutName = dashboardController.listOfLayouts.get(index).getLayoutName();
        System.out.println("layout name: " + layoutName);

        String[] layoutDataCB = dashboardController.getChoiceBoxList();
        System.out.println("getChoiceBoxList(): " + Arrays.toString(layoutDataCB));

        layoutData = dashboardController.listOfLayouts.get(index).getLayoutData();
        System.out.println("layoutObject.getLayoutData(): " + Arrays.toString(layoutData));

        layoutDirection = dashboardController.listOfLayouts.get(index).getLayoutDirection();
        System.out.println("direction: " + layoutDirection);

        layoutEmail = dashboardController.listOfLayouts.get(index).getLayoutEmail();
        System.out.println("email address: " + layoutEmail);

        sqlConfiguration.viewLayout(layoutEmail);

        // reveals the user's layouts
        dashboardController.makeUserLayoutVisible();

        Stage stageThree = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(dashboardPopUp,1920,1080);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }

}