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

    

    public void setTextAreaContent(String content) {
        textArea.setText(content);
        //also set the file manager's text area content
        //initialize the document object
        init_document(textArea);
        //call the backend to load the document
        document.populateLinkedList(content.toString().split(" "));
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
    // Create a service to run the task
    Service<Void> backgroundService = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    // Background work
                    String text = textArea.getText();
                    String[] words = text.split("\\s+");
                    document.populateLinkedList(words);
                    document.run_spell_check();
                    return null;
                }

                @Override
                protected void succeeded() {
                    super.succeeded();
                    // Update UI if necessary, run on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        // Update UI here if necessary
                    });
                }

                @Override
                protected void failed() {
                    super.failed();
                    // Handle errors, run on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        Throwable error = getException();
                        System.err.println("An error occurred in the background task:");
                        error.printStackTrace();
                        // Update UI to reflect error state if necessary
                    });
                }
            };
        }
    };

    // Set up the service to restart every 5 seconds
    backgroundService.setOnSucceeded(e -> {
        backgroundService.reset();
        backgroundService.start();
    });
    backgroundService.setOnCancelled(e -> backgroundService.reset());
    backgroundService.setOnFailed(e -> backgroundService.reset());

    // Start the service
    backgroundService.start();

    // If you need to stop and start the service manually, you can use:
    // backgroundService.cancel();
    // backgroundService.start();
}
    
     
    public void init_document(TextArea textArea){
        //set the text area
        this.textArea = textArea;

        //create a new document object
        document = new Document(textArea.getText().split(" "));
        
    }

    public void setDocument(Document document) {
        //populate the document object
        document.populateLinkedList(textArea.getText().split(" "));
        document.run_spell_check();
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
        document.populateLinkedList(content.toString().split(" "));
        document.run_spell_check();

        // Start Spell check mode
        // Pass the "content" String into Backend methods to load linked Lists, etc before starting spell check mode
        // Once done - Parse linked list of incorrect words and somehow show them differently on UI
        // THen we want to create a series of popup UIs that are able to popup when certain words are clicked on
    }
    // ---> james adding stuff.
    public void initialize() {
        textArea.setOnMouseClicked(event -> {
            handleTextClick(event);
        });
    }
    private void handleTextClick(MouseEvent event) {

        //check to see if this.document is null
        if(this.document == null){
            //initialize the document object
            init_document(textArea);
            startRepeatedTask();
        }else{
            //populate the document object
            //document.populateLinkedList(textArea.getText().split(" "));
            //document.run_spell_check();
            
        }
        
        
        

        //
        // E.g., get click position, call backend function, etc.
        int position = getClickPosition(event);
        
       

        //call the spell check function
        //document.run_spell_check();

        //now we will call to the backend to get the word object
        Word_Object this_word = document.get_word_in_linked_list(position);
        System.out.println("Got Word: " + this_word.getWord());
        //lets see if the word object is null
        

        //now we will call a function with the word_object to render a popup with the word object

        //only display if the word is misspelled, otherwise do nothing
        if(this_word.isIs_real_word() == false){
            int position_x = (int) event.getScreenX();
            int position_y = (int) event.getScreenY();
            //call the function to display the popup
            showPopupAtTextPosition_spelling(this_word, position_x, position_y);
        }


    }

    //adding a function to display the popup
    

    private void showPopupAtTextPosition_spelling(Word_Object word, int x, int y) {
    System.out.println("Showing popup at: " + x + ", " + y);
    // Create a context menu or popup
    String[] suggestions = word.getSuggestions();
    //if suggesitons[0] is null, then do nothing
    if(suggestions[0] == null){
        return;
    }
    ContextMenu contextMenu = new ContextMenu();

    // Create CustomMenuItems with Labels
    CustomMenuItem item1 = createCustomMenuItem(suggestions[0]);
    CustomMenuItem item2 = createCustomMenuItem(suggestions[1]);
    CustomMenuItem item3 = createCustomMenuItem(suggestions[2]);

    // Add items to context menu
    contextMenu.getItems().addAll(item1, item2, item3);

    // Show context menu at specified screen coordinates
    contextMenu.show(textArea, x, y);
    System.out.println("Showing popup done");
    }

    private CustomMenuItem createCustomMenuItem(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: red;"); // Set text color to red
        label.setPrefWidth(150); // Set preferred width
        label.setMaxWidth(Double.MAX_VALUE); // Allow label to expand
        label.setWrapText(true); // Allow text wrapping

        CustomMenuItem item = new CustomMenuItem(label, false);
        return item;
    }

    //adding a get click position function
    private int getClickPosition(MouseEvent event) {
        System.out.println("getting click position");
        // First, get the text area content
        //String text = textArea.getText();
        // Next, get the click position
        int position = textArea.getCaretPosition();
        System.out.println("Click position: " + position);

        // Finally, return the position
        System.out.println("got click position");

        return position;
    }

}




