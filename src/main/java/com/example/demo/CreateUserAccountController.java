package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateUserAccountController {
    public Stage stageOne;
    public Scene sceneOne;
    @FXML
    public TextField CreateAccount_EnterNameTF;
    public TextField CreateAccount_EnterEmailTF;
    public TextField CreateAccount_EnterPasswordTF;
    public PasswordField CreateAccount_ConfirmPasswordTF;

    public static String userName;
    public static String email;
    public static String pass;

    public Button CreateAccountButton;
    public Button CreateAccount_LogInButton;

    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    // event for create account button
    public void onCreateAccountButtonClick(ActionEvent e) throws IOException {
        // get text from textfields
        String fullName = CreateAccount_EnterNameTF.getText();
        String userEmail = CreateAccount_EnterEmailTF.getText();
        String password = CreateAccount_EnterPasswordTF.getText();
        String confirmPassword = CreateAccount_ConfirmPasswordTF.getText();

        if (sqlConfiguration.checkUserInfo(fullName, userEmail, password)
                && password.equals(confirmPassword)) {
            // sets up loader for the fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            // loads the fxml
            Parent dashboardPopUp = loader.load();
            // gets the controller fx:id from the fxml
            DashboardController dashboardController = loader.getController();
            // makes a dashboard object with the user's email
            dashboardController.setEmailAddress(userEmail);

            // adds the user to the database
            sqlConfiguration.addNewUser(fullName, userEmail, password);

            // switch to Dashboard
            Stage stageThree = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene sceneThree = new Scene(dashboardPopUp);
            stageThree.setScene(sceneThree);
            stageThree.show();
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    // event for log in button
    public void onCreateAccount_LogInButtonClick(ActionEvent event) throws IOException {
        // go to log in page
        Parent logInPopUp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        stageOne = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sceneOne = new Scene(logInPopUp);
        stageOne.setScene(sceneOne);
        stageOne.show();
    }
}