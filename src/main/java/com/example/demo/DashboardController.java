package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DashboardController {
    SQLConfiguration sqlConfiguration = new SQLConfiguration();
    FactoryLayoutController flc = new FactoryLayoutController();

    @FXML
    public GridPane DashboardGridPane;

    public Button reportsButton;
    public Button createLayoutButton;

    public Button editLayoutButton1,
            editLayoutButton2,
            editLayoutButton3,
            editLayoutButton4,
            editLayoutButton5,

            viewLayoutButton1,
            viewLayoutButton2,
            viewLayoutButton3,
            viewLayoutButton4,
            viewLayoutButton5,

            deleteLayoutButton1,
            deleteLayoutButton2,
            deleteLayoutButton3,
            deleteLayoutButton4,
            deleteLayoutButton5;

    private String emailAddress;
    private String layoutName;
    private String directionValue;

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLayoutName() {
        return layoutName;
    }
    public String getDirectionValue() {
        return directionValue;
    }

    @FXML
    public void onCreateNewLayoutClick(ActionEvent e) throws Exception {
        // sets user's email into Factory Layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FactoryLayout.fxml"));
        Parent popUp = loader.load();
        FactoryLayoutController factoryLayoutController = loader.getController();
        factoryLayoutController.setEmailAddress(emailAddress);

        // switches to Factory Layout
        Stage stageFive = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneFive = new Scene(popUp);
        stageFive.setScene(sceneFive);
        stageFive.show();
    }

    public void onRulesetsPageClick(ActionEvent e) throws Exception {
        Parent popUp = FXMLLoader.load(getClass().getResource("Rulesets.fxml"));
        Stage stageSix = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneSix = new Scene(popUp);
        stageSix.setScene(sceneSix);
        stageSix.show();
    }

    public void onDeleteLayoutClick (ActionEvent e) throws Exception {
        emailAddress = getEmailAddress();
        layoutName = getLayoutName();
        String[] cbData = flc.getChoiceboxStream();
        directionValue = getDirectionValue();

        sqlConfiguration.deleteLayout(emailAddress, layoutName, cbData, directionValue);
    }

    public void onEditLayoutClick (ActionEvent e) throws Exception {
        //
    }
}
