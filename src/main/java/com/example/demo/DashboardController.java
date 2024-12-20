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

import java.lang.*;
import java.util.ArrayList;

import static com.example.demo.CreateUserAccountController.userEmail;

public class DashboardController {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
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

    public Button reportsButton, createLayoutButton, LayPage_RulesetsPageButton,
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
            deleteLayoutButton5,

            runSimulationButton1,
            runSimulationButton2,
            runSimulationButton3,
            runSimulationButton4,
            runSimulationButton5;

    public Label layoutDeleteLabel,
            layoutNameLabel1,
            layoutNameLabel2,
            layoutNameLabel3,
            layoutNameLabel4,
            layoutNameLabel5;

    static int layoutId;
    static String layoutName;
    static ArrayList<String> layoutData;
    static String robotDirection;

    public void onRulesetsPageClick(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RulesetsDashboard.fxml"));
        Parent popUp = loader.load();
        RulesetsDashboard rulesetsDashboard = loader.getController();

        if (sqlConfiguration.getUserRulesetAmount(userEmail) != 0) {
            rulesetsDashboard.makeUserRulesetsVisible();
        }

        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(popUp,1540,800);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    public void onCreateNewLayoutClick(ActionEvent e) throws Exception {
        // loads the page to create layouts
        Parent createLayoutPopUp = FXMLLoader.load(getClass().getResource("FactoryLayout.fxml"));

        // switches to Factory Layout
        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(createLayoutPopUp,1540,800);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    public void onDeleteLayoutClick1() {
        deleteLayoutButton1.onMouseClickedProperty();
        if (deleteLayoutButton1.isVisible()) {
            layoutOne_Left.setVisible(false);
            layoutOne_Right.setVisible(false);
            layoutDeleteLabel.setText("Layout Deleted");
            layoutDeleteLabel.setVisible(true);
        }
        layoutName = layoutNameLabel1.getText();
        // gets layoutId for get method
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        // deletes from table
        sqlConfiguration.deleteLayout(layoutId);
    }
    public void onDeleteLayoutClick2() {
        deleteLayoutButton2.onMouseClickedProperty();
        if (deleteLayoutButton2.isVisible()) {
            layoutTwo_Left.setVisible(false);
            layoutTwo_Right.setVisible(false);
            layoutDeleteLabel.setText("Layout Deleted");
            layoutDeleteLabel.setVisible(true);
        }
        layoutName = layoutNameLabel2.getText();
        // gets layoutId for get method
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        // deletes from table
        sqlConfiguration.deleteLayout(layoutId);
        // refreshes the dashboard
        makeUserLayoutVisible();
    }
    public void onDeleteLayoutClick3() {
        deleteLayoutButton3.onMouseClickedProperty();
        if (deleteLayoutButton3.isVisible()) {
            layoutThree_Left.setVisible(false);
            layoutThree_Right.setVisible(false);
            layoutDeleteLabel.setText("Layout Deleted");
            layoutDeleteLabel.setVisible(true);
        }
        layoutName = layoutNameLabel2.getText();
        // gets layoutId for get method
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        // deletes from table
        sqlConfiguration.deleteLayout(layoutId);
        // refreshes the dashboard
        makeUserLayoutVisible();
    }
    public void onDeleteLayoutClick4() {
        deleteLayoutButton4.onMouseClickedProperty();
        if (deleteLayoutButton4.isVisible()) {
            layoutFour_Left.setVisible(false);
            layoutFour_Right.setVisible(false);
            layoutDeleteLabel.setText("Layout Deleted");
            layoutDeleteLabel.setVisible(true);
        }
        layoutName = layoutNameLabel2.getText();
        // gets layoutId for get method
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        // deletes from table
        sqlConfiguration.deleteLayout(layoutId);
        // refreshes the dashboard
        makeUserLayoutVisible();
    }
    public void onDeleteLayoutClick5() {
        deleteLayoutButton5.onMouseClickedProperty();
        if (deleteLayoutButton5.isVisible()) {
            layoutFive_Left.setVisible(false);
            layoutFive_Right.setVisible(false);
            layoutDeleteLabel.setText("Layout Deleted");
            layoutDeleteLabel.setVisible(true);
        }
        layoutName = layoutNameLabel5.getText();
        // gets layoutId for get method
        layoutId = sqlConfiguration.getLayoutId(layoutName, userEmail);
        // deletes from table
        sqlConfiguration.deleteLayout(layoutId);
    }

    public void onEditLayoutClick1(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutEdit.fxml"));
        Parent layoutEditPopUp = loader.load();
        FactoryLayoutEditController layoutsEdit = loader.getController();
        // sets the ruleset name to the edit page
        layoutName = layoutNameLabel1.getText();
        layoutsEdit.factoryLayoutName.setText(layoutName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(layoutEditPopUp,1540,800);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditLayoutClick2(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutEdit.fxml"));
        Parent layoutEditPopUp = loader.load();
        FactoryLayoutEditController layoutsEdit = loader.getController();
        // sets the ruleset name to the edit page
        layoutName = layoutNameLabel2.getText();
        layoutsEdit.factoryLayoutName.setText(layoutName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(layoutEditPopUp,1540,800);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditLayoutClick3(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutEdit.fxml"));
        Parent layoutEditPopUp = loader.load();
        FactoryLayoutEditController layoutsEdit = loader.getController();
        // sets the ruleset name to the edit page
        layoutName = layoutNameLabel3.getText();
        layoutsEdit.factoryLayoutName.setText(layoutName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(layoutEditPopUp,1540,800);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditLayoutClick4(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutEdit.fxml"));
        Parent layoutEditPopUp = loader.load();
        FactoryLayoutEditController layoutsEdit = loader.getController();
        // sets the ruleset name to the edit page
        layoutName = layoutNameLabel4.getText();
        layoutsEdit.factoryLayoutName.setText(layoutName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(layoutEditPopUp,1540,800);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }
    public void onEditLayoutClick5(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutEdit.fxml"));
        Parent layoutEditPopUp = loader.load();
        FactoryLayoutEditController layoutsEdit = loader.getController();
        // sets the ruleset name to the edit page
        layoutName = layoutNameLabel5.getText();
        layoutsEdit.factoryLayoutName.setText(layoutName);

        Stage stageSixE = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSixE = new Scene(layoutEditPopUp,1540,800);
        stageSixE.setScene(sceneSixE);
        stageSixE.show();
    }

    public void onViewLayoutClick1(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutView.fxml"));
        Parent layoutsViewPopUp = loader.load();
        FactoryLayoutViewController layoutView = loader.getController();
        // sets the ruleset name to the label
        layoutName = layoutNameLabel1.getText();
        layoutView.layoutNameLabel.setText(layoutName);

        Stage stageThreeV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneThreeV = new Scene(layoutsViewPopUp,1540,800);
        stageThreeV.setScene(sceneThreeV);
        stageThreeV.show();
    }
    public void onViewLayoutClick2(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutView.fxml"));
        Parent layoutsViewPopUp = loader.load();
        FactoryLayoutViewController layoutView = loader.getController();
        // sets the ruleset name to the label
        layoutName = layoutNameLabel2.getText();
        layoutView.layoutNameLabel.setText(layoutName);

        Stage stageThreeV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneThreeV = new Scene(layoutsViewPopUp,1540,800);
        stageThreeV.setScene(sceneThreeV);
        stageThreeV.show();
    }
    public void onViewLayoutClick3(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutView.fxml"));
        Parent layoutsViewPopUp = loader.load();
        FactoryLayoutViewController layoutView = loader.getController();
        // sets the ruleset name to the label
        layoutName = layoutNameLabel3.getText();
        layoutView.layoutNameLabel.setText(layoutName);

        Stage stageThreeV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneThreeV = new Scene(layoutsViewPopUp,1540,800);
        stageThreeV.setScene(sceneThreeV);
        stageThreeV.show();
    }
    public void onViewLayoutClick4(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutView.fxml"));
        Parent layoutsViewPopUp = loader.load();
        FactoryLayoutViewController layoutView = loader.getController();
        // sets the ruleset name to the label
        layoutName = layoutNameLabel4.getText();
        layoutView.layoutNameLabel.setText(layoutName);

        Stage stageThreeV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneThreeV = new Scene(layoutsViewPopUp,1540,800);
        stageThreeV.setScene(sceneThreeV);
        stageThreeV.show();
    }
    public void onViewLayoutClick5(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayoutView.fxml"));
        Parent layoutsViewPopUp = loader.load();
        FactoryLayoutViewController layoutView = loader.getController();
        // sets the ruleset name to the label
        layoutName = layoutNameLabel5.getText();
        layoutView.layoutNameLabel.setText(layoutName);

        Stage stageThreeV = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneThreeV = new Scene(layoutsViewPopUp,1540,800);
        stageThreeV.setScene(sceneThreeV);
        stageThreeV.show();
    }

    public void onSimulationRunnerClick(ActionEvent e) throws Exception {
        // goes to Simulation Runner page
        Parent simRunnerPopUp = FXMLLoader.load(getClass().getResource("SimulationRunner.fxml"));
        // switches to Sim Runner
        Stage stageSeven = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSeven = new Scene(simRunnerPopUp,1920,1080);
        stageSeven.setScene(sceneSeven);
        stageSeven.show();
    }

    public void makeUserLayoutVisible() {
        int layoutAmount = sqlConfiguration.getUserLayoutsAmount(userEmail);
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
            System.out.println("No layouts made.");
        }
    }

    private void layoutOneVisibility() {
        layoutOne_Left.setVisible(true);
        layoutOne_Right.setVisible(true);

        layoutName = sqlConfiguration.getLayoutNamesFromTable(userEmail).getFirst();
        layoutNameLabel1.setText(layoutName);

        layoutNameLabel1.setVisible(true);
        editLayoutButton1.setVisible(true);
        viewLayoutButton1.setVisible(true);
        deleteLayoutButton1.setVisible(true);
    }
    private void layoutTwoVisibility() {
        layoutTwo_Left.setVisible(true);
        layoutTwo_Right.setVisible(true);

        layoutName = sqlConfiguration.getLayoutNamesFromTable(userEmail).get(1);
        layoutNameLabel2.setText(layoutName);

        layoutNameLabel2.setVisible(true);
        editLayoutButton2.setVisible(true);
        viewLayoutButton2.setVisible(true);
        deleteLayoutButton2.setVisible(true);
    }
    private void layoutThreeVisibility() {
        layoutThree_Left.setVisible(true);
        layoutThree_Right.setVisible(true);

        layoutName = sqlConfiguration.getLayoutNamesFromTable(userEmail).get(2);
        layoutNameLabel3.setText(layoutName);

        layoutNameLabel3.setVisible(true);
        editLayoutButton3.setVisible(true);
        viewLayoutButton3.setVisible(true);
        deleteLayoutButton3.setVisible(true);
    }
    private void layoutFourVisibility() {
        layoutFour_Left.setVisible(true);
        layoutFour_Right.setVisible(true);

        layoutName = sqlConfiguration.getLayoutNamesFromTable(userEmail).get(3);
        layoutNameLabel4.setText(layoutName);

        layoutNameLabel4.setVisible(true);
        editLayoutButton4.setVisible(true);
        viewLayoutButton4.setVisible(true);
        deleteLayoutButton4.setVisible(true);
    }
    private void layoutFiveVisibility() {
        layoutFive_Left.setVisible(true);
        layoutFive_Right.setVisible(true);

        layoutName = sqlConfiguration.getLayoutNamesFromTable(userEmail).get(4);
        layoutNameLabel5.setText(layoutName);

        layoutNameLabel5.setVisible(true);
        editLayoutButton5.setVisible(true);
        viewLayoutButton5.setVisible(true);
        deleteLayoutButton5.setVisible(true);
    }
}
