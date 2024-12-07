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
    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    @FXML
    public TextField
            CreateAccount_EnterNameTF,
            CreateAccount_EnterEmailTF,
            CreateAccount_EnterPasswordTF;
    public PasswordField CreateAccount_ConfirmPasswordTF;

    public Button CreateAccountButton, CreateAccount_LogInButton;

    // global/static variables to use during log in
    static String userEmail;
    static String password;

    // event for create account button
    public void onCreateAccountButtonClick(ActionEvent e) throws IOException {
        // get text from textfields
        String fullName = CreateAccount_EnterNameTF.getText();
        userEmail = CreateAccount_EnterEmailTF.getText();
        password = CreateAccount_EnterPasswordTF.getText();
        String confirmPassword = CreateAccount_ConfirmPasswordTF.getText();

        if (sqlConfiguration.checkUserInfo(fullName, userEmail, password)
                && password.equals(confirmPassword)) {

            if (sqlConfiguration.checkEmailFormat(userEmail)) {
                // adds the user to the database
                if (sqlConfiguration.addNewUser(fullName, userEmail, password)) {
                    Parent dashPopUp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                    // switch to Dashboard
                    Stage stageThree = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene sceneThree = new Scene(dashPopUp,1540,800);
                    stageThree.setScene(sceneThree);
                    stageThree.show();
                }
                System.out.println("Account created.");
            } else {
                System.out.println("Invalid email address. Try again.");
            }
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    // event for log in button
    public void onCreateAccount_LogInButtonClick(ActionEvent event) throws IOException {
        // go to log in page
        Parent logInPopUp = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Stage stageTwo = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneTwo = new Scene(logInPopUp,1920,1080);
        stageTwo.setScene(sceneTwo);
        stageTwo.show();
    }
}