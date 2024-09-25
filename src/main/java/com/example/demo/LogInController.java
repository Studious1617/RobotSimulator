package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class LogInController {
    @FXML
    public Button LogInButton;
    public Button CreateAnAccountButton;

    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    public void onLogInButtonClick(ActionEvent e) throws IOException{
        // get text from the textfields
        String name = CreateUserAccountController.userName;
        String email = CreateUserAccountController.email;
        String password = CreateUserAccountController.pass;

        // cross-reference them in the database
        sqlConfiguration.userLogIn(name, email, password);
        // if there's a match go to dashboard
        Parent dashboardPopUp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stageThree = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneThree = new Scene(dashboardPopUp);
        stageThree.setScene(sceneThree);
        stageThree.show();
    }

    public void onCreateAnAccountButtonClick(ActionEvent event) throws IOException {
        Parent popUp = FXMLLoader.load(getClass().getResource("CreateUserAccount.fxml"));
        Stage stageTwo = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneTwo = new Scene(popUp);
        stageTwo.setScene(sceneTwo);
        stageTwo.show();
    }

}
