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

public class LogInController {
    @FXML
    public TextField LogIn_EnterEmailTF;
    public PasswordField LogIn_EnterPasswordTF;

    public Button LogInButton;
    public Button CreateAnAccountButton;

    SQLConfiguration sqlConfiguration = new SQLConfiguration();
    CreateUserAccountController cuaController = new CreateUserAccountController();

    @FXML
    protected void onLogInButtonClick() {
        // get text from both textfields
        String name = cuaController.CreateAccount_EnterNameTF.getText();
        String email = LogIn_EnterEmailTF.getText();
        String password = LogIn_EnterPasswordTF.getText();

        // cross-reference them in the database
        sqlConfiguration.userLogIn(name, email, password);
        // if there's a match go to dashboard

    }

    @FXML
    protected void onCreateAnAccountButtonClick(Stage stageOne) throws IOException {
        Parent popUp = FXMLLoader.load(getClass().getResource("CreateUserAccount.fxml"));

        stageOne.setTitle("Welcome to the Robot Simulator");
        Scene sceneOne = new Scene(popUp, 300, 400);
        stageOne.setScene(sceneOne);
        stageOne.show();
    }
}
