package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    public GridPane DashboardGridPane;
    public Node leftHBox;
    public Node rightHBox;



    public Button LayoutsPageButton;
    public Button RulesetsPageButton;
    public Button reportsButton;
    public Button createLayoutButton;

    public Button editLayoutButton1;
    public Button editLayoutButton2;
    public Button editLayoutButton3;
    public Button editLayoutButton4;
    public Button editLayoutButton5;

    public Button viewLayoutButton1;
    public Button viewLayoutButton2;
    public Button viewLayoutButton3;
    public Button viewLayoutButton4;
    public Button viewLayoutButton5;

    public Button deleteLayoutButton1;
    public Button deleteLayoutButton2;
    public Button deleteLayoutButton3;
    public Button deleteLayoutButton4;
    public Button deleteLayoutButton5;

    private String emailAddress;
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    @FXML
    public void onRulesetsPageClick(ActionEvent e) throws Exception {
        Parent popUp = FXMLLoader.load(getClass().getResource("Rulesets.fxml"));
        Stage stageSix = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSix = new Scene(popUp);
        stageSix.setScene(sceneSix);
        stageSix.show();
    }
}
