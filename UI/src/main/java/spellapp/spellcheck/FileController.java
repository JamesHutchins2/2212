package spellapp.spellcheck;

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
//import from backend
import Backend.*;



public class FileController {

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

        // Read the contents of the file into "content" (StringBuilder class)
        if (file != null) {
            try {
                StringBuilder content = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();

                // Get object hierarchy for MainScene into a loader object and load into a Parent class
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
                Parent mainSceneRoot = loader.load();

                // Get the controller of the main scene - and send it action to set text area of main text area
                MainSceneController mainSceneController = loader.getController();
                mainSceneController.setTextAreaContent(content.toString());

                // Create Scene object for the main Scene - pass in initial window size
                Scene mainScene = new Scene(mainSceneRoot, 800, 500);
                // Make Scene visible
                primaryStage.setScene(mainScene);

            } catch (IOException e) {
                e.printStackTrace();
                label.setText("Error reading the file");
            }
        }
    }

    @FXML
    void handleNewFileButton(ActionEvent event) {
        try {
            // Get object hierarchy for MainScene into a loader object and load into a Parent class
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
            Parent mainSceneRoot = loader.load();

            // Get the controller of the main scene - and send it action to set text area of main text area
            MainSceneController mainSceneController = loader.getController();

            // Create Scene object for the main Scene - pass in initial window size
            Scene mainScene = new Scene(mainSceneRoot, 800, 500);
            // Make Scene visible
            primaryStage.setScene(mainScene);

        } catch (IOException e) {
            e.printStackTrace();
            label.setText("Error opening with blank file");
        }
    }

}
