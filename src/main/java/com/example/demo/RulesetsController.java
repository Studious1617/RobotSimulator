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
        dashboardController.setLayoutId(layoutId);
        dashboardController.setLayoutName(layoutName);

        dashboardController.setLayoutEmail(layoutEmail);
        // reveals the user's layouts
        dashboardController.makeUserLayoutVisible();

        Stage stageFour = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFour = new Scene(dashboardPopUp);
        stageFour.setScene(sceneFour);
        stageFour.show();
    }
}
