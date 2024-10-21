package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class DashboardController {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();
    FactoryLayoutController flc = new FactoryLayoutController();

    @FXML
    public GridPane DashboardGridPane;

    public Button reportsButton;
    public Button createLayoutButton;
    public Button LayPage_RulesetsPageButton;

    public Button
            editLayoutButton1,
            editLayoutButton2,
            editLayoutButton3,
            editLayoutButton4,
            editLayoutButton5,

            viewLayoutButton1,
            viewLayoutButton2,
            viewLayoutButton3,
            viewLayoutButton4,
            viewLayoutButton5,

            deleteLayoutButton1,
            deleteLayoutButton2,
            deleteLayoutButton3,
            deleteLayoutButton4,
            deleteLayoutButton5;

    public Label
            layoutNameLabel1,
            layoutNameLabel2,
            layoutNameLabel3,
            layoutNameLabel4,
            layoutNameLabel5;

    private String emailAddress;
    private String layoutName;
    private String directionValue;
    private int layoutID;

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLayoutName() {
        return layoutName;
    }
    public String getDirectionValue() {
        return directionValue;
    }
    public int getLayoutID(String layoutName) {
        return sqlConfiguration.getLayoutID(layoutName);
    }

    @FXML
    public void onCreateNewLayoutClick(ActionEvent e) throws Exception {
        // sets user's email into Factory Layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayout.fxml"));
        Parent popUp = loader.load();
        FactoryLayoutController factoryLayoutController = loader.getController();
        factoryLayoutController.setEmailAddress(emailAddress);

        // switches to Factory Layout
        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(popUp);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    public void onRulesetsPageClick(ActionEvent e) throws Exception {
        Parent popUp = FXMLLoader.load(getClass().getResource("Rulesets.fxml"));
        Stage stageSix = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSix = new Scene(popUp);
        stageSix.setScene(sceneSix);
        stageSix.show();
    }

    public void onDeleteLayoutClick (ActionEvent e) throws Exception {
        emailAddress = getEmailAddress();
        layoutID = getLayoutID(getLayoutName());

        sqlConfiguration.deleteLayout(emailAddress, layoutID);
    }

    public void onEditLayoutClick (ActionEvent e) throws Exception {
        emailAddress = getEmailAddress();
        layoutName = getLayoutName();
        String[] cbData = flc.getChoiceBox();
        directionValue = getDirectionValue();
        layoutID = getLayoutID(layoutName);

        sqlConfiguration.editLayout(emailAddress, layoutName, cbData, directionValue, layoutID);
    }

    //Lets the user view previously made layouts
    //Work in progress
    public void onViewLayoutClick(ActionEvent e) throws Exception {
        emailAddress = getEmailAddress();
        System.out.println("This is the email address: " + emailAddress);  //For debugging purposes

        layoutName = getLayoutName();
        System.out.println("This is the layout name: " + layoutName);  //For debugging purposes

        String[] cbData = flc.getChoiceBox();
        System.out.println("This is the choicebox stream: " + Arrays.toString(cbData));  //For debugging purposes

        directionValue = getDirectionValue();
        System.out.println("This is the direction: " + directionValue);  //For debugging purposes

        layoutID = getLayoutID(getLayoutName());
        System.out.println("ID: " + layoutID);

        sqlConfiguration.viewLayout(emailAddress, layoutID);
    }
}
