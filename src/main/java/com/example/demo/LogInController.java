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
import java.util.List;

public class LogInController {
    @FXML
    public Button LogInButton;
    public Button CreateAnAccountButton;
    @FXML
    public TextField enterEmailTF;
    public TextField enterPasswordTF;

    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    // user's layouts
    public List<Layout> listOfLayouts;

    public void onLogInButtonClick(ActionEvent e) throws IOException {
        String emailAddress = enterEmailTF.getText();
        String password = enterPasswordTF.getText();
        // check if the user entered valid data
        if (sqlConfiguration.checkUserLogIn(emailAddress, password)) {
            if (sqlConfiguration.checkEmailFormat(emailAddress)) {
                // cross-reference it in the database
                if (sqlConfiguration.userLogIn(emailAddress, password)) {
                    // sets user email instance into Dashboard
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                    Parent dashboardPopUp = loader.load();
                    DashboardController dashboardController = loader.getController();

                    dashboardController.setEmailAddress(emailAddress);
                    listOfLayouts = sqlConfiguration.getUserLayoutList(emailAddress);
                    dashboardController.setListOfLayouts(listOfLayouts);
                    // reveals the user's layouts
                    if (!listOfLayouts.isEmpty()) {
                        dashboardController.makeUserLayoutVisible();
                    }
                    // switches to Dashboard
                    Stage stageThree = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene sceneThree = new Scene(dashboardPopUp,1540,800);
                    stageThree.setScene(sceneThree);
                    stageThree.show();
                } else {
                    System.out.println("User account does not exist. Try again.");
                }
            } else {
                System.out.println("Invalid email address. Try again.");
            }
        } else {
            System.out.println("Invalid credentials or user doesn't exist. Please try again or make an account.");
        }
    }

    public void onCreateAnAccountButtonClick(ActionEvent event) throws IOException {
        Parent popUp = FXMLLoader.load(getClass().getResource("CreateUserAccount.fxml"));
        Stage stageTwo = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneTwo = new Scene(popUp,1540,800);
        stageTwo.setScene(sceneTwo);
        stageTwo.show();
    }
}