package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardController {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public Button reportsButton;
    public Button createLayoutButton;
    public Button LayPage_RulesetsPageButton;

    public HBox
            layoutOne_Left,
            layoutTwo_Left,
            layoutThree_Left,
            layoutFour_Left,
            layoutFive_Left,

            layoutOne_Right,
            layoutTwo_Right,
            layoutThree_Right,
            layoutFour_Right,
            layoutFive_Right;

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

    public List<Button> buttonList;
    // added buttons to list
    @FXML
    public void initialize () {
        buttonList = new ArrayList<>();
        buttonList.add(editLayoutButton1);
        buttonList.add(editLayoutButton2);
        buttonList.add(editLayoutButton3);
        buttonList.add(editLayoutButton4);
        buttonList.add(editLayoutButton5);

        buttonList.add(viewLayoutButton1);
        buttonList.add(viewLayoutButton2);
        buttonList.add(viewLayoutButton3);
        buttonList.add(viewLayoutButton4);
        buttonList.add(viewLayoutButton5);

        buttonList.add(deleteLayoutButton1);
        buttonList.add(deleteLayoutButton2);
        buttonList.add(deleteLayoutButton3);
        buttonList.add(deleteLayoutButton4);
        buttonList.add(deleteLayoutButton5);
    }


    public Label
            layoutNameLabel1,
            layoutNameLabel2,
            layoutNameLabel3,
            layoutNameLabel4,
            layoutNameLabel5;

    private String emailAddress;
    private String layoutName;
    private String[] layoutData;
    private String directionValue;
    private int layoutID;

    public int index;

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Layout> layouts;

    public void setLayouts(List<Layout> layouts) {
        this.layouts = layouts;
    }

    @FXML
    public void onCreateNewLayoutClick(ActionEvent e) throws Exception {
        String email = getEmailAddress();
        // sets user's email into Factory Layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayout.fxml"));
        Parent popUp = loader.load();
        FactoryLayoutController factoryLayoutController = loader.getController();
        factoryLayoutController.setEmailAddress(email);

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
    @FXML
    public void onDeleteLayoutClick (ActionEvent e) throws Exception {
        index = buttonDifferentiation(e);
        emailAddress = layouts.get(index).getEmailAddress();
        layoutID = layouts.get(index).getLayoutID();

        sqlConfiguration.deleteLayout(emailAddress, layoutID);
    }
    @FXML
    public void onEditLayoutClick (ActionEvent e) throws Exception {
        index = buttonDifferentiation(e);
        emailAddress = layouts.get(index).getEmailAddress();
        layoutName = layouts.get(index).getLayoutName();
        layoutData = layouts.get(index).getLayoutData();
        directionValue = layouts.get(index).getDirectionValue();
        layoutID = layouts.get(index).getLayoutID();

        sqlConfiguration.editLayout(emailAddress, layoutName, layoutData, directionValue, layoutID);
        // goes to edit page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutEdit.fxml"));
        Parent editPopUp = loader.load();
        FactoryLayoutEditController layoutEditController = loader.getController();
        layouts = sqlConfiguration.getUserLayoutData(emailAddress);
        layoutEditController.setLayouts(layouts);
        layoutEditController.setIndex(index);

        // switches to Factory Layout
        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(editPopUp);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    //Work in progress
    public void onViewLayoutClick(ActionEvent e) throws Exception {
        index = buttonDifferentiation(e);
        emailAddress = getEmailAddress();
        System.out.println("This is the email address: " + emailAddress);  //For debugging purposes

        layoutName = layouts.get(index).getLayoutName();
        System.out.println("This is the layout name: " + layoutName);  //For debugging purposes

        layoutData = layouts.get(index).getLayoutData();
        System.out.println("This is the choicebox stream: " + Arrays.toString(layoutData));  //For debugging purposes

        directionValue = layouts.get(index).getDirectionValue();
        System.out.println("This is the direction: " + directionValue);  //For debugging purposes

        layoutID = layouts.get(index).getLayoutID();
        System.out.println("ID: " + layoutID);

        sqlConfiguration.viewLayout(emailAddress, layoutID);

        // goes to view page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutView.fxml"));
        Parent viewPopUp = loader.load();
        FactoryLayoutViewController layoutViewController = loader.getController();
        layoutViewController.setLayouts(layouts);
        layoutViewController.setIndex(index);

        // switches to Factory View
        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(viewPopUp);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    public void makeUserLayoutVisible() {
        int layoutAmount = sqlConfiguration.getUserLayoutsAmount();
        if (layoutAmount == 1) {
            layoutOneVisibility();
        } else if (layoutAmount == 2) {
            layoutOneVisibility();
            layoutTwoVisibility();
        } else if (layoutAmount == 3) {
            layoutOneVisibility();
            layoutTwoVisibility();
            layoutThreeVisibility();
        } else if (layoutAmount == 4) {
            layoutOneVisibility();
            layoutTwoVisibility();
            layoutThreeVisibility();
            layoutFourVisibility();
        } else if (layoutAmount == 5) {
            layoutOneVisibility();
            layoutTwoVisibility();
            layoutThreeVisibility();
            layoutFourVisibility();
            layoutFiveVisibility();
        }

    }

    private void layoutOneVisibility() {
        layoutOne_Left.setVisible(true);
        layoutOne_Right.setVisible(true);

        layoutName = layouts.getFirst().getLayoutName();
        layoutNameLabel1.setText(layoutName);

        layoutNameLabel1.setVisible(true);
        editLayoutButton1.setVisible(true);
        viewLayoutButton1.setVisible(true);
        deleteLayoutButton1.setVisible(true);
    }
    private void layoutTwoVisibility() {
        layoutTwo_Left.setVisible(true);
        layoutTwo_Right.setVisible(true);

        layoutName = layouts.get(1).getLayoutName();
        layoutNameLabel2.setText(layoutName);

        layoutNameLabel2.setVisible(true);
        editLayoutButton2.setVisible(true);
        viewLayoutButton2.setVisible(true);
        deleteLayoutButton2.setVisible(true);
    }
    private void layoutThreeVisibility() {
        layoutThree_Left.setVisible(true);
        layoutThree_Right.setVisible(true);

        layoutName = layouts.get(2).getLayoutName();
        layoutNameLabel3.setText(layoutName);

        layoutNameLabel3.setVisible(true);
        editLayoutButton3.setVisible(true);
        viewLayoutButton3.setVisible(true);
        deleteLayoutButton3.setVisible(true);
    }
    private void layoutFourVisibility() {
        layoutFour_Left.setVisible(true);
        layoutFour_Right.setVisible(true);

        layoutName = layouts.get(3).getLayoutName();
        layoutNameLabel4.setText(layoutName);

        layoutNameLabel4.setVisible(true);
        editLayoutButton4.setVisible(true);
        viewLayoutButton4.setVisible(true);
        deleteLayoutButton4.setVisible(true);
    }
    private void layoutFiveVisibility() {
        layoutFive_Left.setVisible(true);
        layoutFive_Right.setVisible(true);

        layoutName = layouts.get(4).getLayoutName();
        layoutNameLabel5.setText(layoutName);

        layoutNameLabel5.setVisible(true);
        editLayoutButton5.setVisible(true);
        viewLayoutButton5.setVisible(true);
        deleteLayoutButton5.setVisible(true);
    }
    @FXML
    public int buttonDifferentiation (ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        int buttonNumber = 0;
        if (clickedButton.getText().contains("1")) {
            buttonNumber = 1;
        } else if (clickedButton.getText().contains("2")) {
            buttonNumber = 2;
        } else if (clickedButton.getText().contains("3")) {
            buttonNumber = 3;
        } else if (clickedButton.getText().contains("4")) {
            buttonNumber = 4;
        } else if (clickedButton.getText().contains("5")){
            buttonNumber = 5;
        }
        return buttonNumber;
    }
}
