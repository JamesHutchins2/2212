package spellapp.spellcheck;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javax.swing.*;
import javax.swing.text.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.stage.FileChooser;
import javafx.event.EventHandler;

import javafx.scene.text.Text;
import java.util.LinkedList;
import javafx.scene.paint.Color;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;

public class SpellSceneController {
    private Scene preScene;

    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    public void setSpellAreaContent(String mainContent) {
        // Example code here - Read current text from textArea into StringBuilder and
        StringBuilder content = new StringBuilder();                // Create StringBuilder to hold text
        content.append( mainContent );                              // Fill it with current textArea text
        String[] words = content.toString().split("\\s+");    // Parse into words (in array)
        LinkedList<String> wordList = new LinkedList<>();           // Create sample linkedList
        for (String word : words) {  wordList.add(word); }          // Add words to LinkedList

        // Add LinkedLIst items to the TextFlow (spellArea)
        for (String word : wordList) {
            Text textNode = new Text(word + " ");
            spellArea.getChildren().add(textNode);

            // Sample - color a few words red  
            if ( word.equals("version**") ) textNode.setFill(Color.RED);
        }
    }

    @FXML
    void exitSpell(ActionEvent event) {
        Stage stage = (Stage) exitSpellBtn.getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }


    @FXML
    private Button exitFromSpelllButton;
    @FXML
    private Button exitSpellBtn;
    @FXML
    private Button saveSpellButton;
    @FXML
    private TextFlow spellArea;
    @FXML
    private BorderPane spellID;
    @FXML
    private Label spellLabel;

    @FXML
    void exitfromspell(ActionEvent event) {
    }

    @FXML
    void handleSaveSpellButton(ActionEvent event) {
    }
}
