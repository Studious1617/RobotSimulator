package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello");

        VBox root = new VBox();

        Text text = new Text("Enter sentence: ");
        root.getChildren().add(text);

        TextField textField = new TextField();
        root.getChildren().add(textField);

        Button button = new Button("Enter");
        root.getChildren().add(button);

        Scene scene = new Scene(root);

        root.setMinSize(350, 250);
        stage.setX(100);
        stage.setY(200);
        stage.setMinHeight(300);
        stage.setMinWidth(400);

        stage.setScene(scene);
        stage.setTitle("Hello!");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}