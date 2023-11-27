package spellapp.spellcheck;
import org.fxmisc.richtext.InlineCssTextArea;
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

import Backend.Doc_Error;
public class MainSceneController {

    @FXML
    private InlineCssTextArea textArea = new TextArea();
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
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
        // Task to be executed every 5 seconds
        Platform.runLater(() -> {
            
            document.populateLinkedList(textArea.getText());
            System.out.println("running spell check");
            document.run_spell_check();
            //now we will also call doc error and doc analysis update functions
            
            //doc analysis values 
            int[] doc_analysis_vals = get_doc_analysis();
            //update the doc analysis values
            update_doc_analysis_values(doc_analysis_vals);
            //call to get the doc error values
            int[] doc_error_vals = get_doc_error();
            //update the doc error values
            update_doc_error_values(doc_error_vals);
            
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

            Scene mainScene = new Scene(root, 800, 500);

            Stage stage = new Stage();
            stage.setTitle("User Dictionary");
            stage.setScene(mainScene);
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
    
    public int[] get_doc_analysis(){
        //gets the values from the doc analysis object

        int[] doc_err_vals = document.get_doc_analysis();

        return doc_err_vals;

    };

    //define fxml values for the doc analysis counts
    @FXML
    private Label characterCountLabel;
    @FXML
    private Label lineCountLabel;
    @FXML
    private Label wordCountLabel;
    @FXML
    private Label Misspellings;
    @FXML
    private Label MissCaps;
    @FXML
    private Label DoubleWords;
    @FXML
    private Label Corrections;
    @FXML
    private Label DoubleWordCorrections;

    public void update_doc_error_values(int[] values){
        //update the doc error values
        //get the values
        int misspelt_words = values[0];
        int corrected_misspelt_words = values[1];
        int double_words = values[2];
        int corrected_double_words = values[3];
        int capital_errors = values[4];
        int corrected_capital_errors = values[5];

        //set the values
        Misspellings.setText("Misspellings: " + misspelt_words);
        MissCaps.setText("Capital Errors: " + capital_errors);
        DoubleWords.setText("Double Words: " + double_words);
        Corrections.setText("Corrected Misspellings: " + corrected_misspelt_words);
        DoubleWordCorrections.setText("Corrected Double Words: " + corrected_double_words);
    }

    public void update_doc_analysis_values(int[] counts){
        //update the doc analysis values
        //get the values
        int char_count = counts[0];
        int word_count = counts[1];
        int line_count = counts[2];
        System.out.println("scene control doc vals");
        System.out.println("char count: " + char_count);
        System.out.println("word count: " + word_count);
        System.out.println("line count: " + line_count);

        //set the values
        characterCountLabel.setText("Character count: " + char_count);
        lineCountLabel.setText("Line count: " + line_count);
        wordCountLabel.setText("Word count: " + word_count);
    }

    public int[] get_doc_error(){

        Doc_Error error = document.get_doc_error();

        //get the values
        int misspelt_words = error.getCurrent_misspelt_words();
        int corrected_misspelt_words = error.getCorrected_misspelt_words();
        int double_words = error.getCurrent_double_words();
        int corrected_double_words = error.getCorrected_double_words();
        int capital_errors = error.getCurrent_capital_errors();
        int corrected_capital_errors = error.getCorrected_capital_errors();

        //create an array to store the values
        int[] values = {misspelt_words, corrected_misspelt_words, double_words, corrected_double_words, capital_errors, corrected_capital_errors};

        //return the array
        return values;

            
    };
    



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
    private CustomMenuItem addTouserDictItem(Word_Object word){
        //create a new menu item
        MenuItem item = new MenuItem("Add to user dictionary");
        //create a new custom menu item
        
        //add a label
        Label label = new Label("Add to user dictionary");
        //add an event listener to the menu item
        item.setOnAction(event -> {
            //get the word
            String word_text = word.getWord();
            //add the word to the user dictionary
            document.add_to_user_dict(word_text);
            //hide the context menu
            contextMenu.hide();
        });
        //return the custom menu item
        return new CustomMenuItem(label, false);
    }

    private void showPopupAtTextPosition_spelling(Word_Object word, double x, double y) {
        // Clear existing items and prepare new ones
        contextMenu.getItems().clear();
        String[] suggestions = word.getSuggestions();
        //get the word start and stop indicies
        int start_index = word.getStart_index();
        int end_index = word.getEnd_index();


        if (suggestions[0] != null) {
            // Add new items to the context menu
            contextMenu.getItems().addAll(
                createCustomMenuItem(0, word),
                createCustomMenuItem(1, word),
                createCustomMenuItem(2, word),
                addTouserDictItem(word) // Updated method name
            );

            // Show context menu at the mouse cursor's position
            contextMenu.show(textArea, x, y);
        }
    }

    

    private CustomMenuItem createCustomMenuItem(int index, Word_Object word) {
        String suggestion = word.getSuggestions()[index];
        Label label = new Label(suggestion);
        label.setStyle("-fx-text-fill: red;");
        label.setPrefWidth(150);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setWrapText(true);
    
        label.setOnMouseClicked(event -> {
            // Replace the word in the text editor
            int startIndex = word.getStart_index();
            int endIndex = word.getEnd_index();
            textArea.replaceText(startIndex, endIndex, suggestion);
    
            // Update the Word_Object
            word.setWord(suggestion);
            //change all of the flags

            //update fix and missplet couts
    
            // Hide the context menu
            contextMenu.hide();
    
            // Additional functionality like re-running spell check can be added here
        });
    
        return new CustomMenuItem(label, false);
    }

    private int getClickPosition(MouseEvent event) {
        // Return the current caret position in the text area
        return textArea.getCaretPosition();
    }

    

    
}




