package spellapp.spellcheck;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Label;

public class ExitController {
    private Stage primaryStage;
    public String content = "";

    // Injected via FXML if defined in your FXML file
    @FXML
    private Label label;

    @FXML
    void exitExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void exitSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");

        // Assuming primaryStage is the primary stage of your application
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            saveToFile(file);
            Platform.exit();
        }
    }

    private void saveToFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, maybe show an error dialog to the user
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
