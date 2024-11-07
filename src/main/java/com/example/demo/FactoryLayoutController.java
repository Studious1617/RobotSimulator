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

public class FactoryLayoutController implements Initializable {
    @FXML
    public Button saveButton;
    public Button FL_BackButton;
    public Label messageLabel;

    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    private ChoiceBox<String>
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

    @FXML
    private TextField factoryLayoutName;
    public String getFactoryLayoutName() {
        return factoryLayoutName.getText();
    }
    @FXML
    private ChoiceBox<String> robotDirectionCB;
    public String getRobotDirectionCB() {
        return robotDirectionCB.getValue();
    }

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


/////
    public List<Layout> listOfLayouts;
    public List<Layout> getListOfLayouts() {
        return listOfLayouts;
    }
    public void setListOfLayouts(List<Layout> listOfLayouts) {
        this.listOfLayouts = listOfLayouts;
    }
/////
    public String[] choiceBoxList;
    public String[] getChoiceBox() {
        // makes a new list of strings
        choiceBoxList = new String[choiceBoxes.length];
        // integer variable to be the list's index
        int cbIndex = 0;
        for (ChoiceBox<String> choiceBox: choiceBoxes) {
            // adds the "Open" value to the list if the user left the space blank
            if (choiceBox.getValue() == null) {
                choiceBoxList[cbIndex++] = "Open";
            } else {
            // adds the value the user entered into the space to the list
                choiceBoxList[cbIndex++] = choiceBox.getValue();
            }
        }
        return choiceBoxList;
    }
    public void setChoiceBox(String[] choiceBoxList) {
        if (choiceBoxList.length == choiceBoxes.length) {
            int cbIndex = 0;
            for (ChoiceBox<String> choiceBox : choiceBoxes) {
                choiceBox.setValue(choiceBoxList[cbIndex++]);
            }
        }
    }

    public int index;
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        choiceBoxes = new ChoiceBox[]{
                CB_00, CB_10, CB_20, CB_30, CB_40,
                CB_01, CB_11, CB_21, CB_31, CB_41,
                CB_02, CB_12, CB_22, CB_32, CB_42,
                CB_03, CB_13, CB_23, CB_33, CB_43,
                CB_04, CB_14, CB_24, CB_34, CB_44};

        // sets the options for all the choiceboxes
        ObservableList<String> choiceBoxOptions = FXCollections.observableArrayList("Start", "Open", "Wall", "Exit");
        ObservableList<String> robotDirection = FXCollections.observableArrayList("Front", "Left", "Right", "Back");

        // adds the options
        for (ChoiceBox<String> box : choiceBoxes){
            box.getItems().addAll(choiceBoxOptions);
        }
        robotDirectionCB.getItems().addAll(robotDirection);
    }

    public void onBackButton(ActionEvent event) throws Exception {
        // sets user email instance into Dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController dashboardController = loader.getController();
        dashboardController.setListOfLayouts(getListOfLayouts());
        dashboardController.setLayoutEmail(getLayoutEmail());

        // makes user's layouts appear
        if (!getListOfLayouts().isEmpty()) {
            dashboardController.makeUserLayoutVisible();
        }

        Stage stageThree = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(dashboardPopUp,1920,1080);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }

    public boolean startEndBoxExist (String[] choiceBox) {
        int startBox = 0;
        int endBox = 0;

        for (String box : choiceBox) {
            if (box != null) {
                if (box.equals("Start")) startBox++;
                if (box.equals("Exit")) endBox++;
            }
        }
        return startBox == 1 && endBox == 1;
    }

    public void onSaveLayoutButton(ActionEvent e) throws Exception {
        // gets the info to save the layout to the database/table
        System.out.println(getLayoutId());
        System.out.println("getFactoryLayoutName(): " + getFactoryLayoutName());
        System.out.println("getLayoutName(): " + getLayoutName());
        System.out.println("getChoiceBox(): " + Arrays.toString(getChoiceBox()));
        System.out.println("getListOfLayouts(): " + getListOfLayouts());
        System.out.println("getLayoutData(): " + Arrays.toString(getLayoutData()));
        System.out.println("getRobotDirectionCB(): " + getRobotDirectionCB());
        System.out.println("getLayoutDirection(): " + getLayoutDirection());
        System.out.println(getLayoutEmail());

        // enter the data into the layouts table
        if (startEndBoxExist(getChoiceBox()) && !getFactoryLayoutName().isEmpty()) {
            sqlConfiguration.insertLayout(getFactoryLayoutName(), getChoiceBox(), getRobotDirectionCB(), getLayoutEmail());
            messageLabel.setVisible(true);
        } else if (getFactoryLayoutName().isEmpty()) {
            System.out.println("Enter a name for the layout.");
        } else if (getRobotDirectionCB().isEmpty()) {
            System.out.println("Enter a direction for the robot.");
        } else {
            System.out.println("You need one Start box and one Exit box selected to save the layout.");
        }
    }
}