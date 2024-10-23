package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class RulesetsController {
    String email;
    List<Layout> layouts;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Layout> getLayouts() {
        return layouts;
    }
    public void setLayouts(List<Layout> layouts) {
        this.layouts = layouts;
    }

    @FXML
    public void onCreateNewRulesetClick(ActionEvent e) throws Exception {
        System.out.println("Not finished yet.");
    }
    @FXML
    public void onLayoutPageClick(ActionEvent e) throws Exception {
        // sets user email instance into Dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent dashboardPopUp = loader.load();
        DashboardController dashboardController = loader.getController();

        dashboardController.setEmailAddress(email);
        dashboardController.setLayouts(layouts);
        // reveals the user's layouts
        dashboardController.makeUserLayoutVisible();

        Stage stageFour = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFour = new Scene(dashboardPopUp);
        stageFour.setScene(sceneFour);
        stageFour.show();
    }
}
