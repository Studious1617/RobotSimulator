package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RulesetsController {
    @FXML
    public void onCreateNewRulesetClick(ActionEvent e) throws Exception {
        System.out.println("Not finished yet.");
    }
    @FXML
    public void onLayoutPageClick(ActionEvent e) throws Exception {
        Parent popUp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stageFour = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFour = new Scene(popUp);
        stageFour.setScene(sceneFour);
        stageFour.show();
    }
}
