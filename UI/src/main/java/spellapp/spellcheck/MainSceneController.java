package spellapp.spellcheck;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
// import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import Backend.Document;
import Backend.Word_Object;
import javafx.stage.FileChooser;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
public class MainSceneController {

    @FXML
    private TextArea textArea = new TextArea();
    @FXML
    private Label mainLabel;

    //Create a document object
    Document document;
    private ContextMenu contextMenu = new ContextMenu();
    private ContextMenu spellingContextMenu = new ContextMenu();
    private ContextMenu undoContextMenu = new ContextMenu();
    private int currentCaretPosition = 0;

    

    public void setTextAreaContent(String content) {
        textArea.setText(content);
        textArea.setOnMouseClicked(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
            // Add logic to handle text click, if any
        });
        //also set the file manager's text area content
        //initialize the document object
        init_document(textArea);
        //call the backend to load the document
        document.populateLinkedList(content.toString());
        document.run_spell_check();

        //re-populate the text area with the new content
        StringBuilder newContent = new StringBuilder();
        //get the head node
        Word_Object curr = (Word_Object) document.wordBuffer.getHead();
        //loop through the linked list until we reach the end
        while(curr != null){
            //add the word to the string builder
          newContent.append(curr.getWord() + " ");
          curr = curr.getNext_node();
        }
        //set the text area content
        textArea.setText(newContent.toString());
        
    }
 
public void startRepeatedTask() {
    // Create a Timeline
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
        // Task to be executed every 5 seconds
        Platform.runLater(() -> {
            
            document.populateLinkedList(textArea.getText());
            System.out.println("running spell check");
            document.run_spell_check();

            // Re-populate the text area with the new content
            //StringBuilder newContent = new StringBuilder();
            //Word_Object curr = document.wordBuffer.getHead(); // Removed the cast
            //while (curr != null) {
            //    newContent.append(curr.getWord()).append(" ");
            //    curr = curr.getNext_node();
            //}
            //textArea.setText(newContent.toString());
        });
    }));

    timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
    timeline.play(); // Start the timeline

    // If you need to stop this timeline manually, you can use:
    // timeline.stop();
}

    
     
    public void init_document(TextArea textArea){
        //set the text area
        this.textArea = textArea;

        //parse the text area
        

        //create a new document object
        document = new Document(textArea.getText());
        
    }

    public void setDocument(Document document) {
        //populate the document object
        document.populateLinkedList(textArea.getText());
        //document.run_spell_check();
        //set the document object
        this.document = document;
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
    public String[] parse_text(){
        //get the text from the text area
        String text = textArea.getText();
        //split the text into an array of words
        String[] words = text.split("\\s+");
        
        //delete double spaces
        
        //return the array of words
        return words;
    
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
    private void handleUserDictionary(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDictionary.fxml"));
            Parent root = loader.load();
            UserDictionaryController userDictionaryController = loader.getController();
            userDictionaryController.setTextAreaContent(textArea.getText());

            // Get the existing stage
            Stage stage = (Stage) textArea.getScene().getWindow();


            stage.setScene(new Scene(root, 800, 500));
            stage.setTitle("User Dictionary");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading UserDictionary.fxml: " + e.getMessage());
        }
    }

    @FXML
    void startSpell(ActionEvent event) {
        StringBuilder content = new StringBuilder();
        content.append( textArea.getText() );
        mainLabel.setText("Spell Check Mode");
        // call spell check mode
        
        
        //document.populateLinkedList(content.toString().split(" "));
        //document.run_spell_check();

        // Start Spell check mode
        // Pass the "content" String into Backend methods to load linked Lists, etc before starting spell check mode
        // Once done - Parse linked list of incorrect words and somehow show them differently on UI
        // THen we want to create a series of popup UIs that are able to popup when certain words are clicked on
    }
    // ---> james adding stuff.
    public void initialize() {
        textArea.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                handleTextClick(event);
            } else {
                contextMenu.hide();
            }
        });
    }
    private void handleTextClick(MouseEvent event) {
        // Check if the document is initialized
        if (this.document == null) {
            System.out.println("document is null, initializing");
            init_document(textArea);
            startRepeatedTask();
        }

        int position = getClickPosition(event);
        Word_Object this_word = document.get_word_in_linked_list(position);

        // Check if the word is misspelled and show suggestions
        if (!this_word.isIs_real_word()) {
            //hide all other context menus
            contextMenu.hide();
            System.out.println("word is misspelled: " + this_word.getWord());
            showPopupAtTextPosition_spelling(this_word, event.getScreenX(), event.getScreenY());
        }
    }

    //adding a function to display the popup
    

    private void showPopupAtTextPosition_spelling(Word_Object word, double x, double y) {
        // Clear existing items and prepare new ones
        contextMenu.getItems().clear();
        String[] suggestions = word.getSuggestions();

        if (suggestions[0] != null) {
            // Add new items to the context menu
            contextMenu.getItems().addAll(
                createCustomMenuItem(0, word),
                createCustomMenuItem(1, word),
                createCustomMenuItem(2, word)
            );

            // Show context menu at the mouse cursor's position
            contextMenu.show(textArea, x, y);
        }
    }

    private CustomMenuItem createCustomMenuItem(int index, Word_Object word) {
        // Create a label with specified text and styling
        String text = word.getSuggestions()[index];
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: red;");
        label.setPrefWidth(150);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setWrapText(true);

        //let's create an event listner for the label

        label.setOnMouseClicked(event -> {
            //get the text of the label
            String label_text = label.getText();
            //get the current word
            String current_word = text;
            //replace the current word with the label text
            textArea.replaceSelection(label_text);
            //hide the context menu
            contextMenu.hide();
            //add the word to the user dictionary
            //re-run the spell check
            //document.run_spell_check();
            //re-populate the text area with the new content
            StringBuilder newContent = new StringBuilder();
            //get the head node
            Word_Object curr = (Word_Object) document.wordBuffer.getHead();
            //loop through the linked list until we reach the end
            while(curr != null){
                //add the word to the string builder
              newContent.append(curr.getWord() + " ");
              curr = curr.getNext_node();
            }
            //set the text area content
            textArea.setText(newContent.toString());
        });

        return new CustomMenuItem(label, false);
    }
    private void implement_caps(Word_Object word){
        //get the word start and stop indicies
        int startIndex = (word.getPrev_node() != null) ? word.getPrev_node().getEnd_index() + 1 : 0;
        int endIndex = (word.getNext_node() != null) ? word.getNext_node().getStart_index() : textArea.getLength();

        //replace that text in that range with the word
    }

    private void implement_suggestion(Word_Object word, String suggestion) {
        // Get indices from adjacent nodes
        int startIndex = (word.getPrev_node() != null) ? word.getPrev_node().getEnd_index() + 1 : 0;
        int endIndex = (word.getNext_node() != null) ? word.getNext_node().getStart_index() : textArea.getLength();
    
        // Debugging print statements
        System.out.println("Replacing '" + textArea.getText(startIndex, endIndex) + "' with '" + suggestion + "'");
    
        // Replace the text with new word and surrounding spaces

        //check for 3 cases:
        int w_len = word.getWord().length();
        int s_len = suggestion.length();
        int worddiff = w_len - s_len;
        //1 the words are the same length
        if(worddiff == 0){
            textArea.replaceText(startIndex, endIndex, " " + suggestion + " ");
        }
        //2 the suggestion is longer
        else if(worddiff < 0){
            textArea.replaceText(startIndex, endIndex, " " + suggestion + " ");
        }
        //3 the suggestion is shorter
        else if(worddiff > 0){
            //means we need to shift the indices back
            
            shift_indices();
        }
        else{
            System.out.println("error in worddiff");
        }
        

        
        
    
        // Update the Word_Object and document stats
        word.setWord(suggestion);
        word.setIs_real_word(true);
        word.setModified(false);
        document.decrease_current_misspelt_words();
    }

    private void shift_indices(){
        //shift the indices back
    }

    private void implement_drop_double_word(Word_Object word){
        //get the word start and stop indicies
        //update the words' indicies


        int startIndex = (word.getPrev_node() != null) ? word.getPrev_node().getEnd_index() + 1 : 0;
        int endIndex = (word.getNext_node() != null) ? word.getNext_node().getStart_index() : textArea.getLength();

        textArea.replaceText(startIndex, endIndex, " ");

        //remove the word

        document.wordBuffer.removeWord(word);

    }
    

    private int getClickPosition(MouseEvent event) {
        // Return the current caret position in the text area
        return textArea.getCaretPosition();
    }

    

    
}




