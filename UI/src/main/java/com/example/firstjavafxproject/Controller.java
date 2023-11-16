package com.example.firstjavafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controller {

    @FXML
    private Label label;
    private FileChooser fileChooser = new FileChooser();
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    protected void handleOpenButton(ActionEvent event) {
        File file = fileChooser.showOpenDialog(label.getScene().getWindow());

        if (file != null) {
            try {
                StringBuilder content = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                reader.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
                Parent mainSceneRoot = loader.load();

                MainSceneController mainSceneController = loader.getController();

                mainSceneController.setTextAreaContent(content.toString());

                Scene mainScene = new Scene(mainSceneRoot, 800, 500);
                primaryStage.setScene(mainScene);

            } catch (IOException e) {
                e.printStackTrace();
                label.setText("Error reading the file");
            }
        }
    }
}
