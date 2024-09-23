package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This will be the main class
 */

public class RobotSimulatorFX extends Application {
    // main class. runs the program
    public static void main(String[] args) {
        launch(args);
    }
    //
    @Override
    public void start(Stage stageOne) throws Exception {
        //
        Parent popUp = FXMLLoader.load(getClass().getResource("CreateUserAccount.fxml"));

        stageOne.setTitle("Welcome to the Robot Simulator");
        Scene sceneOne = new Scene(popUp, 300, 400);
        stageOne.setScene(sceneOne);
        stageOne.show();
    }

}