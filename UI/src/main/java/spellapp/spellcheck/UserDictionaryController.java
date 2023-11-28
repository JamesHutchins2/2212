package spellapp.spellcheck;
import org.fxmisc.richtext.StyleClassedTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Popup;
import javafx.stage.Stage;
import Backend.Document;

import java.io.IOException;

public class UserDictionaryController {
    @FXML
    private TextArea textArea;

    @FXML
    private Label mainLabel;

    private Stage primaryStage;

    public void setTextAreaContent(String content) {
        textArea.setText(content);
    }

    @FXML
    private void handleHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FileChooser.fxml"));
            Parent root = loader.load();
            FileController homeController = loader.getController();
            homeController.setPrimaryStage(primaryStage);

            Scene mainScene = new Scene(root, 800, 500);

            Stage stage = new Stage();
            stage.setTitle("SpellChecker");
            stage.setScene(mainScene);
            stage.show();

            Stage currentStage = (Stage) textArea.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FileChooser.fxml: " + e.getMessage());
        }
    }

    @FXML
    void openHelp(ActionEvent event) {
        try {
            // Load the Help.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Help.fxml"));
            Parent root = loader.load();

            // Create a new popup
            javafx.stage.Popup popup = new Popup();
            popup.getContent().add(root);

            // Set the owner stage
            Stage ownerStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            popup.show(ownerStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}