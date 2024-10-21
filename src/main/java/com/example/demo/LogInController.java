package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LogInController {
    @FXML
    public Button LogInButton;
    public Button CreateAnAccountButton;

    public TextField enterEmailTF;
    public TextField enterPasswordTF;

    SQLConfiguration sqlConfiguration = new SQLConfiguration();
    DashboardController dashboardController = new DashboardController();

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
        dashboardController.layoutNameLabel1.setVisible(true);
        dashboardController.editLayoutButton1.setVisible(true);
        dashboardController.viewLayoutButton1.setVisible(true);
        dashboardController.deleteLayoutButton1.setVisible(true);
    }
    private void layoutTwoVisibility() {
        dashboardController.layoutNameLabel2.setVisible(true);
        dashboardController.editLayoutButton2.setVisible(true);
        dashboardController.viewLayoutButton2.setVisible(true);
        dashboardController.deleteLayoutButton2.setVisible(true);
    }
    private void layoutThreeVisibility() {
        dashboardController.layoutNameLabel3.setVisible(true);
        dashboardController.editLayoutButton3.setVisible(true);
        dashboardController.viewLayoutButton3.setVisible(true);
        dashboardController.deleteLayoutButton3.setVisible(true);
    }
    private void layoutFourVisibility() {
        dashboardController.layoutNameLabel4.setVisible(true);
        dashboardController.editLayoutButton4.setVisible(true);
        dashboardController.viewLayoutButton4.setVisible(true);
        dashboardController.deleteLayoutButton4.setVisible(true);
    }
    private void layoutFiveVisibility() {
        dashboardController.layoutNameLabel5.setVisible(true);
        dashboardController.editLayoutButton5.setVisible(true);
        dashboardController.viewLayoutButton5.setVisible(true);
        dashboardController.deleteLayoutButton5.setVisible(true);
    }

    public void onLogInButtonClick(ActionEvent e) throws IOException {
        // get text from the textfields
        String email = enterEmailTF.getText();
        String password = enterPasswordTF.getText();

        if (sqlConfiguration.checkUserLogIn(email, password)) {
            // cross-reference them in the database
            if (sqlConfiguration.userLogIn(email, password)) {
                makeUserLayoutVisible();
                // add layouts to dashboard
                sqlConfiguration.getUserLayoutData();

                // sets user email instance into Dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                Parent dashboardPopUp = loader.load();
                DashboardController dashboardController = loader.getController();
                dashboardController.setEmailAddress(email);

                // switches to Dashboard
                Stage stageThree = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene sceneThree = new Scene(dashboardPopUp);
                stageThree.setScene(sceneThree);
                stageThree.show();
            }
        } else {
            System.out.println("Invalid credentials or user doesn't exist. Please try again or make an account.");
        }
    }

    public void onCreateAnAccountButtonClick(ActionEvent event) throws IOException {
        Parent popUp = FXMLLoader.load(getClass().getResource("CreateUserAccount.fxml"));
        Stage stageTwo = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneTwo = new Scene(popUp);
        stageTwo.setScene(sceneTwo);
        stageTwo.show();
    }

}
