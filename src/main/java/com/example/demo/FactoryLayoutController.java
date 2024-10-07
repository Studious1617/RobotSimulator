package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FactoryLayoutController {
    //test
    public void toBeDecided(ActionEvent event) throws IOException {
        Parent popUp = FXMLLoader.load(getClass().getResource("FactoryLayout.fxml"));
        Stage stageFour = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneFour = new Scene(popUp);
        stageFour.setScene(sceneFour);
        stageFour.show();
    }
}