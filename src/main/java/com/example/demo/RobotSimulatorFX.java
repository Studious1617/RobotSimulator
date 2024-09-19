package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RobotSimulatorFX extends Application {
    // main class
    public static void main(String[] args) {
        launch(args);
    }
    // creates the
    @Override
    public void start(Stage stageOne) {
        // setup
        stageOne.setTitle("Create account or sign in");

        VBox pop_up = new VBox();
        pop_up.setSpacing(10);

        // username label and textfield
        Label usernameLabel = new Label("Enter username here:");
        TextField usernameTextfield = new TextField();

        // password label and textfield
        Label passwordLabel = new Label("Enter password here:");
        TextField passwordTextfield = new TextField();

        // button to make the accounts and save them to the database
        Button createAccountButton = new Button("Create account");
        pop_up.setAlignment(Pos.CENTER);

        // adds all the elements
        pop_up.getChildren().addAll(
                usernameLabel, usernameTextfield,
                passwordLabel, passwordTextfield,
                createAccountButton);

        // window stuff
        Scene sceneOne = new Scene(pop_up);
        stageOne.setMinWidth(500);
        stageOne.setMinHeight(400);
        stageOne.setScene(sceneOne);
        stageOne.show();
    }
}