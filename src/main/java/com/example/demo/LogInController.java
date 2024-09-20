package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LogInController {
    @FXML
    public TextField LogIn_EnterEmailTF;
    public TextField LogIn_EnterPasswordTF;

    public Button LogInButton;

    @FXML
    protected void onLogInButtonClick() {
        // get text from both textfields
        // cross-reference them in the database
        // if there's a match go to dashboard
    }
}
