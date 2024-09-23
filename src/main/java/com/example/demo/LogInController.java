package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController {
    @FXML
    public TextField LogIn_EnterEmailTF;
    public PasswordField LogIn_EnterPasswordTF;

    public Button LogInButton;

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
}
