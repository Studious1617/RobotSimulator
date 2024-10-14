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

public class LogInController {
    @FXML
    public Button LogInButton;
    public Button CreateAnAccountButton;

    public TextField enterEmailTF;
    public TextField enterPasswordTF;

    SQLConfiguration sqlConfiguration = new SQLConfiguration();

    public void onLogInButtonClick(ActionEvent e) throws IOException{
        // get text from the textfields
        // String name = CreateUserAccountController.userName;
        String email = enterEmailTF.getText();
        String password = enterPasswordTF.getText();

        if (sqlConfiguration.checkUserLogIn(email, password)) {
            // cross-reference them in the database
            sqlConfiguration.userLogIn(email, password);

            // sets user email instance into Dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent dashboardPopUp = loader.load();
            DashboardController dashboardController = loader.getController();
            dashboardController.setEmailAddress(email);

            // switches to Dashboard
            Stage stageThree = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene sceneThree = new Scene(dashboardPopUp);
            stageThree.setScene(sceneThree);
            stageThree.show();
        } else {
            System.out.println("Invalid credentials or user doesn't exist. Please try again or make an account.");
        }
    }

    public void onCreateAnAccountButtonClick(ActionEvent event) throws IOException {
        Parent popUp = FXMLLoader.load(getClass().getResource("CreateUserAccount.fxml"));
        Stage stageTwo = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneTwo = new Scene(popUp);
        stageTwo.setScene(sceneTwo);
        stageTwo.show();
    }

}
