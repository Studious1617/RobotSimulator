package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.example.demo.CreateUserAccountController.password;
import static com.example.demo.CreateUserAccountController.userEmail;

public class LogInController {
    public Label logInErrorLabel;
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public Button LogInButton, CreateAnAccountButton;
    @FXML
    public TextField enterEmailTF, enterPasswordTF;

    // static/global variable for the layouts to use throughout the project
    static Map<Integer, List<String>> layouts;

    public void onLogInButtonClick(ActionEvent e) throws IOException {
        userEmail = enterEmailTF.getText();
        password = enterPasswordTF.getText();
        // check if the user entered valid data
        if (sqlConfiguration.checkUserLogIn(userEmail, password)) {
            if (sqlConfiguration.checkEmailFormat(userEmail)) {
                // cross-reference it in the database
                if (sqlConfiguration.userLogIn(userEmail, password)) {
                    // sets user email instance into Dashboard
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                    Parent dashboardPopUp = loader.load();
                    DashboardController dashboardController = loader.getController();

                    // sets layouts map to current info from database
                    layouts = sqlConfiguration.getUserLayouts(userEmail);
                    // reveals the user's layouts
                    if (!layouts.isEmpty()) {
                        dashboardController.makeUserLayoutVisible();
                    }
                    // switches to Dashboard
                    Stage stageThree = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene sceneThree = new Scene(dashboardPopUp,1540,800);
                    stageThree.setScene(sceneThree);
                    stageThree.show();
                } else {
                    logInErrorLabel.setText("User account does not exist. Try again.");
                }
            } else {
                logInErrorLabel.setText("Invalid email address. Try again.");
            }
        } else {
            logInErrorLabel.setText("Invalid credentials or user doesn't exist, try again or make an account");
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