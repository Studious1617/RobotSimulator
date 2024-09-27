package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This will be the main class
 */

public class FactoryLayoutController extends Application {

    // main class. runs the program
    public static void main(String[] args) {
        launch(args);
    }
    //
    @Override
    public void start(Stage createAccountStage) throws Exception {
        //
        Parent popUp = FXMLLoader.load(getClass().getResource("FactoryLayout.fxml"));

        Scene FacAccountScene = new Scene(popUp);
        createAccountStage.setScene(FacAccountScene);
        createAccountStage.show();
    }
}