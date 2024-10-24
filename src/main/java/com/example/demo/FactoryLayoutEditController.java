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
import java.util.List;
import java.util.ResourceBundle;

public class FactoryLayoutEditController implements Initializable {
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

    private String emailAddress;
    @FXML
    private TextField factoryLayoutName;
    @FXML
    private ChoiceBox<String> robotDirectionCB;

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
            if (choiceBox.getValue() == null) {
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

    private List<Layout> layouts;

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

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        choiceBoxes = new ChoiceBox[]{
                CB_00, CB_10, CB_20, CB_30, CB_40,
                CB_01, CB_11, CB_21, CB_31, CB_41,
                CB_02, CB_12, CB_22, CB_32, CB_42,
                CB_03, CB_13, CB_23, CB_33, CB_43,
                CB_04, CB_14, CB_24, CB_34, CB_44};
        // for the edit function
        if (getLayouts() != null) {
            String[] savedChoiceBoxes = getLayouts().get(index).getLayoutData();
            String direction = getLayouts().get(index).getDirectionValue();
            int index = 0;
            for (ChoiceBox<String> box : choiceBoxes) {
                box.setValue(savedChoiceBoxes[index]);
                index++;
            }
            robotDirectionCB.setValue(direction);
        }
        // sets the options for all the choiceboxes
        ObservableList<String> choiceBoxOptions = FXCollections.observableArrayList("Start", "Open", "Wall", "Exit");
        ObservableList<String> robotDirection = FXCollections.observableArrayList("Front", "Left", "Right", "Back");

        // adds the options
        for (ChoiceBox<String> box : choiceBoxes){
            box.getItems().addAll(choiceBoxOptions);
        }
        robotDirectionCB.getItems().addAll(robotDirection);
    }

    @FXML
    public void onBackButton(ActionEvent event) throws Exception {
        // sets user email instance into Dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController dashboardController = loader.getController();
        // set layout info to dashboard
        layouts = sqlConfiguration.getUserLayoutData(getEmailAddress());
        dashboardController.setLayouts(layouts);

        // makes user's layouts appear
        dashboardController.makeUserLayoutVisible();

        Stage stageThree = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(dashboardPopUp);
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

    @FXML
    public void onSaveLayoutButton(ActionEvent e) throws Exception {
        // gets the info to save the layout to the database/table
        String userEmail = getEmailAddress();
        String layoutName = getFactoryLayoutName();
        String[] layoutData = getChoiceBox();
        String directionValue = getRobotDirectionCB();

        // enter the data into the layouts table
        if (startEndBoxExist(layoutData) && !layoutName.isEmpty()) {
            sqlConfiguration.insertLayout(layoutName, layoutData, directionValue, userEmail);

            messageLabel.setVisible(true);
        } else if (layoutName.isEmpty()) {
            System.out.println("Enter a name for the layout.");
        } else if (robotDirectionCB.getValue().isEmpty()) {
            System.out.println("Enter a direction for the robot.");
        } else {
            System.out.println("You need one Start box and one Exit box selected to save the layout.");
        }
    }
}