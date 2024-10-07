package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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

    @FXML
    public void onCreateNewLayoutClick(ActionEvent e) throws Exception {
        //
        RulesetsPageButton.setStyle("-fx-background-color: null");
    }
}
