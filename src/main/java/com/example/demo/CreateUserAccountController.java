package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateUserAccountController {
    @FXML
    public TextField CreateAccount_EnterNameTF;
    public TextField CreateAccount_EnterEmailTF;
    public PasswordField CreateAccount_EnterPasswordTF;
    public PasswordField CreateAccount_ConfirmPasswordTF;

    public Button CreateAccountButton;
    public Button CreateAccount_LogInButton;

    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    // event for create account button
    @FXML
    protected void onCreateAccountButtonClick() {
        // get text from textfields
        String fullName = CreateAccount_EnterNameTF.getText();
        String userEmail = CreateAccount_EnterEmailTF.getText();
        String password = CreateAccount_EnterPasswordTF.getText();
        String confirmPassword = CreateAccount_ConfirmPasswordTF.getText();

        // check password
        // check if there's no match in database
        // add them to the database
        if (confirmPassword.equals(password)) {
            sqlConfiguration.addNewUser(fullName, userEmail, password);
        }
        // switch to dashboard

    }

    // event for log in button
    @FXML
    protected void onCreateAccount_LogInButtonClick(Stage stageTwo) throws IOException {
        // go to log in page
        Parent popUp2 = FXMLLoader.load(getClass().getResource("LogIn.fxml"));

        stageTwo.setTitle("Welcome to the Robot Simulator");
        Scene sceneOne = new Scene(popUp2, 300, 400);
        stageTwo.setScene(sceneOne);
        stageTwo.show();
        //
    }
}