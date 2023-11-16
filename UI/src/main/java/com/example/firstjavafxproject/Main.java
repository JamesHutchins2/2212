package com.example.firstjavafxproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FileChooserLayout.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setPrimaryStage(stage);

        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Spellchecker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
