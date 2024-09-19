package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateUserAccountController {

    @FXML
    public TextField CreateAccount_EnterNameTF;
    public TextField CreateAccount_EnterEmailTF;
    public TextField CreateAccount_EnterPasswordTF;
    public TextField CreateAccount_ConfirmPasswordTF;

    public Button CreateAccountButton;
    public Button CreateAccount_LogInButton;

    @FXML
    protected void onCreateAccountButtonClick() {
        // get text from textfields
        // check password
        // check if there's no match in database
        // add them to the database
        // switch to dashboard
    }
    @FXML
    protected void onCreateAccount_LogInButtonClick() {
        // go to log in page
    }
}