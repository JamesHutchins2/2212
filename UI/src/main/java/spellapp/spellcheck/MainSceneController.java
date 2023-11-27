package spellapp.spellcheck;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javax.swing.*;
import javax.swing.text.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javafx.stage.FileChooser;
import javafx.scene.layout.BorderPane;


public class MainSceneController {

    @FXML
    private TextArea textArea;
    @FXML
    private Label mainLabel;
    @FXML
    private BorderPane mainID;

    @FXML
    public void initialize() {
        mainLabel.setText("MainSceneController Initialized");
        textArea.setStyle("-fx-highlight-fill: none; -fx-highlight-text-fill: black; -fx-font-size: 12px;");
    }

    public void setTextAreaContent(String content) {
        textArea.setText(content);
    }

    public String getTextAreaContent() {
        return textArea.getText();
    }


    @FXML
    private void handleSaveButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            saveSystem(file, textArea.getText());
        }
    }

    private void saveSystem(File file, String text) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.write(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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


    @FXML
    public void exit(ActionEvent event) {
        try {
            // Load the Help.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Exit.fxml"));
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

    @FXML
    void startSpell(ActionEvent event) {
        // Start Spell check mode - change window status text
        mainLabel.setText("Spell Check Mode");

        // Several possible methods
        // Pass the "content" String into Backend methods to load linked Lists, etc before starting spell check mode
        // Once done - Parse linked list of incorrect words and somehow show them differently on UI
        // THen we want to create a series of popup UIs that are able to popup when certain words are clicked on

        // Let's create a Spell Scene
        // Create new Scene based on SpellScene
         try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SpellScene.fxml"));
             Scene spellScene = new Scene(fxmlLoader.load());

             // Create a SpellCheck controller, and set the previous Scene to this one
             SpellSceneController controller = fxmlLoader.getController();
             // SpellController controller = (SpellController)loader.getController();
             controller.setPreScene(textArea.getScene());               // some element from current scene

             controller.setSpellAreaContent(textArea.getText()); // Pass current text to SpellCheck

             Stage stage = (Stage) textArea.getScene().getWindow(); // Get current stage
             stage.setScene(spellScene);  // show new scene
             stage.show();

         } catch (IOException e) {
             e.printStackTrace();
         }

    }
    

    @FXML
    private void handleUserDictionary(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserDictionary.fxml"));
            Scene dictScene = new Scene(fxmlLoader.load());

            // Create a Dictionary controller, and set the previous Scene to this one
            UserDictionaryController controller = fxmlLoader.getController();
            // SpellController controller = (SpellController)loader.getController();
            controller.setPreScene(textArea.getScene());               // some element from current scene

            Stage stage = (Stage) textArea.getScene().getWindow(); // Get current stage
            stage.setScene(dictScene);  // show new scene
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading UserDictionary.fxml: " + e.getMessage());
        }
        /*
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDictionary.fxml"));
            Parent root = loader.load();
            UserDictionaryController userDictionaryController = loader.getController();
            userDictionaryController.setTextAreaContent(textArea.getText());

            Scene mainScene = new Scene(root, 800, 500);

            Stage stage = new Stage();
            stage.setTitle("User Dictionary");
            stage.setScene(mainScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading UserDictionary.fxml: " + e.getMessage());
        }
*/

    }
}




