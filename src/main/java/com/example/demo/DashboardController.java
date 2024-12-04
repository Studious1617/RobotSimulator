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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DashboardController {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();
    FactoryLayoutController factoryLayoutController = new FactoryLayoutController();

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

    public Label
            layoutNameLabel1,
            layoutNameLabel2,
            layoutNameLabel3,
            layoutNameLabel4,
            layoutNameLabel5;

    private String emailAddress;
    private int layoutId;
    private String layoutName;



    private String[] layoutData;
    private String layoutDirection;
    private String layoutEmail;

    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getLayoutId() {
        return layoutId;
    }
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public String getLayoutEmail() {
        return layoutEmail;
    }
    public void setLayoutEmail(String layoutEmail) {
        this.layoutEmail = layoutEmail;
    }


    public int index;
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public String[] choiceBoxList;
    public String[] getChoiceBoxList() {
        // makes a new list of strings
        choiceBoxList = new String[factoryLayoutController.choiceBoxes.length];
        // integer variable to be the list's index
        int cbIndex = 0;
        for (ChoiceBox<String> choiceBox: factoryLayoutController.choiceBoxes) {
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
    public void setChoiceBoxList(String[] choiceBoxList) {
        if (choiceBoxList.length == getChoiceBoxList().length) {
            int cbIndex = 0;
            for (ChoiceBox<String> choiceBox : factoryLayoutController.choiceBoxes) {
                choiceBox.setValue(choiceBoxList[cbIndex++]);
            }
        }
    }

    public List<Layout> listOfLayouts;
    public List<Layout> getListOfLayouts() {
        return listOfLayouts;
    }
    public void setListOfLayouts(List<Layout> listOfLayouts) {
        this.listOfLayouts = listOfLayouts;
    }

    public void onRulesetsPageClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsDashboard.fxml"));
        Parent popUp = loader.load();
        RulesetsDashboard rulesetsDashboard = loader.getController();
        rulesetsDashboard.setUserEmail(getEmailAddress());
        rulesetsDashboard.setListOfLayouts(getListOfLayouts());

        rulesetsDashboard.makeUserRulesetsVisible();

        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(popUp,1540,800);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    @FXML
    public void onCreateNewLayoutClick(ActionEvent e) throws Exception {
        // sets user's email into Factory Layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayout.fxml"));
        Parent popUp = loader.load();
        FactoryLayoutController factoryLayoutController = loader.getController();
        factoryLayoutController.setLayoutEmail(this.emailAddress);
        factoryLayoutController.setListOfLayouts(this.listOfLayouts);

        // switches to Factory Layout
        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(popUp,1540,800);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    public void onDeleteLayoutClick (ActionEvent e) throws Exception {
        if (buttonDifferentiation(e) != -1){
            sqlConfiguration.deleteLayout(getEmailAddress(), buttonDifferentiation(e));
        }
        refreshDashboard(e);
    }

    public void onEditLayoutClick (ActionEvent e) throws Exception {
        // goes to edit page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutEdit.fxml"));
        Parent editPopUp = loader.load();
        FactoryLayoutEditController layoutEditController = loader.getController();
        index = buttonDifferentiation(e);
        layoutEditController.setIndex(index);

        // listOfLayouts = sqlConfiguration.getUserLayoutList(getEmailAddress());
        layoutEditController.setListOfLayouts(getListOfLayouts());
        // switches to Edit Layout
        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(editPopUp,1920,1080);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    //Work in progress
    public void onViewLayoutClick(ActionEvent e) throws Exception {
        // goes to view page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutView.fxml"));
        Parent viewPopUp = loader.load();
        FactoryLayoutViewController layoutViewController = loader.getController();

        layoutViewController.setLayoutId(getLayoutId());
        layoutViewController.setListOfLayouts(getListOfLayouts());
        layoutViewController.setIndex(getIndex());

        // switches to Factory View
        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(viewPopUp,1920,1080);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    public void refreshDashboard(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController layoutRefreshController = loader.getController();

        makeUserLayoutVisible();
        layoutRefreshController.setListOfLayouts(getListOfLayouts());
        makeUserLayoutVisible();

        Stage stageFour = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFour = new Scene(dashboardPopUp,1920,1080);
        stageFour.setScene(sceneFour);
        stageFour.show();
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
        } else {
            System.out.println();
        }
    }

    private void layoutOneVisibility() {
        layoutOne_Left.setVisible(true);
        layoutOne_Right.setVisible(true);

        layoutName = listOfLayouts.getFirst().getLayoutName();
        layoutNameLabel1.setText(layoutName);

        layoutNameLabel1.setVisible(true);
        editLayoutButton1.setVisible(true);
        viewLayoutButton1.setVisible(true);
        deleteLayoutButton1.setVisible(true);
    }
    private void layoutTwoVisibility() {
        layoutTwo_Left.setVisible(true);
        layoutTwo_Right.setVisible(true);

        layoutName = listOfLayouts.get(1).getLayoutName();
        layoutNameLabel2.setText(layoutName);

        layoutNameLabel2.setVisible(true);
        editLayoutButton2.setVisible(true);
        viewLayoutButton2.setVisible(true);
        deleteLayoutButton2.setVisible(true);
    }
    private void layoutThreeVisibility() {
        layoutThree_Left.setVisible(true);
        layoutThree_Right.setVisible(true);

        layoutName = listOfLayouts.get(2).getLayoutName();
        layoutNameLabel3.setText(layoutName);

        layoutNameLabel3.setVisible(true);
        editLayoutButton3.setVisible(true);
        viewLayoutButton3.setVisible(true);
        deleteLayoutButton3.setVisible(true);
    }
    private void layoutFourVisibility() {
        layoutFour_Left.setVisible(true);
        layoutFour_Right.setVisible(true);

        layoutName = listOfLayouts.get(3).getLayoutName();
        layoutNameLabel4.setText(layoutName);

        layoutNameLabel4.setVisible(true);
        editLayoutButton4.setVisible(true);
        viewLayoutButton4.setVisible(true);
        deleteLayoutButton4.setVisible(true);
    }
    private void layoutFiveVisibility() {
        layoutFive_Left.setVisible(true);
        layoutFive_Right.setVisible(true);

        layoutName = listOfLayouts.get(4).getLayoutName();
        layoutNameLabel5.setText(layoutName);

        layoutNameLabel5.setVisible(true);
        editLayoutButton5.setVisible(true);
        viewLayoutButton5.setVisible(true);
        deleteLayoutButton5.setVisible(true);
    }

    public int buttonDifferentiation (ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        int buttonNumber = -1;

        if (clickedButton.getId().contains("1")) {
            buttonNumber = 0;
        } else if (clickedButton.getId().contains("2")) {
            buttonNumber = 1;
        } else if (clickedButton.getId().contains("3")) {
            buttonNumber = 2;
        } else if (clickedButton.getId().contains("4")) {
            buttonNumber = 3;
        } else if (clickedButton.getId().contains("5")){
            buttonNumber = 4;
        }
        return buttonNumber;
    }
}
