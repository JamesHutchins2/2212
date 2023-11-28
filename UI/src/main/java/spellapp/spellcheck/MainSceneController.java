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
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import Backend.Document;
import Backend.Word_Object;
import javafx.stage.FileChooser;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fxmisc.richtext.StyleClassedTextArea;

import Backend.Doc_Error;
public class MainSceneController {

    @FXML
    private Pane textAreaContainer;
    private StyleClassedTextArea textArea = new StyleClassedTextArea();
    
    @FXML
    private Label mainLabel;

    //Create a document object
    Document document;
    private ContextMenu contextMenu = new ContextMenu();
    private ContextMenu spellingContextMenu = new ContextMenu();
    private ContextMenu undoContextMenu = new ContextMenu();
    private int currentCaretPosition = 0;

    

    public void setTextAreaContent(String content) {
        textArea.replaceText(content); // Replaces the entire text content

        textArea.setOnMouseClicked(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
            // Additional logic for handling text click, if any
        });
        System.out.println("setting text area content" + content);
        System.out.println("text area content: " + textArea.getText());
        
        // Initialize and populate the document as before
        init_document(textArea); // Ensure init_document is compatible with InlineCssTextArea
        document.populateLinkedList(content);
        document.run_spell_check();

        
    }

 
    public void startRepeatedTask() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            Platform.runLater(() -> {
                try {
                    String textContent = textArea.getText();
                    document.populateLinkedList(textContent);
                    System.out.println("Running spell check");
                    document.run_spell_check();
    
                    int[] docAnalysisVals = get_doc_analysis();
                    update_doc_analysis_values(docAnalysisVals);
    
                    int[] docErrorVals = get_doc_error();
                    //run the highlight misspelled words function
                    highlightWords();
                    update_doc_error_values(docErrorVals);
                    
                } catch (Exception e) {
                    System.err.println("Error during repeated task: " + e.getMessage());
                    // Handle or log the exception appropriately
                }
            });
        }));
    
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    

    
     
    public void init_document(StyleClassedTextArea textArea_in){
        // Set the text area
        System.out.println("initializing document");
        
    
        // Create a new document object
        this.document = new Document(textArea.getText());
        
    }
    

    public void setDocument(Document document) {
        // Populate the document object
        document.populateLinkedList(textArea.getText());
        // Uncomment if spell check should be run here
        // document.run_spell_check();
    
        // Set the document object
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
            if (getClass().getResourceAsStream("/stylesheet.css") == null) {
                System.out.println("Stylesheet not found");
            } else {
                System.out.println("Stylesheet found");
            }

            Scene mainScene = new Scene(root, 800, 500);
            mainScene.getStylesheets().add(getClass().getResource("/spellapp/spellcheck/stylesheet.css").toExternalForm());


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
        //int num_lines = textArea.getParagraphs().size();
        String content = textArea.getText();
        int lines = (int) content.chars().filter(ch -> ch == '\n').count() + 1;
        int[] doc_err_vals = document.get_doc_analysis(lines);

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
    



    @FXML
    public void initialize() {
        // Initialize StyleClassedTextArea
        textArea = new StyleClassedTextArea();
        textAreaContainer.getChildren().add(textArea);
        textArea.prefWidthProperty().bind(textAreaContainer.widthProperty());
        textArea.prefHeightProperty().bind(textAreaContainer.heightProperty());
        startRepeatedTask();
        // Configure the StyleClassedTextArea
        textAreaContainer.setOnMouseClicked(event -> {
            System.out.println("Mouse clicked in textArea"); // For debugging purposes
            if (event.getButton() == MouseButton.PRIMARY) {
                handleTextClick(event);
            } else {
                contextMenu.hide();
            }

            // Additional mouse click logic, if needed
        });
    }

    private void handleTextClick(MouseEvent event) {
        System.out.println("Mouse clicked at: " + event.getX() + ", " + event.getY());
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
        if(this_word.isIs_double_word_after() || this_word.isIs_double_word_before()){
            //hide all other context menus
            contextMenu.hide();
            System.out.println("word is double word: " + this_word.getWord());
            showPopupAtTextPosition_double_word(this_word, event.getScreenX(), event.getScreenY());
        }
    }
    private void showPopupAtTextPosition_double_word(Word_Object word, double x, double y){

        contextMenu.getItems().clear();

        if (word.isIs_double_word_after()){
            contextMenu.getItems().addAll(
                createDoubleWordItem_replace(0, word)
                
            );

            contextMenu.show(textArea, x, y);
        }
    }
    private CustomMenuItem createDoubleWordItem_replace(int index, Word_Object word){
        String text = "Delete Double Word";
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: red;");
        label.setPrefWidth(150);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setWrapText(true);


        label.setOnMouseClicked(event -> {
            //delete the text, and the word object in the linked list

            implement_drop_double_word(word);
            document.decrease_current_double_words();
        });

        return new CustomMenuItem(label,true);
    }


    //adding a function to display the popup
    private CustomMenuItem addTouserDictItem(Word_Object word){
        //create a new menu item
        
        //create a new custom menu item
        
        //add a label
        Label label = new Label("Add to user dictionary");
        //add an event listener to the menu item
        label.setPrefWidth(150);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setWrapText(true);
        label.setOnMouseClicked(event -> {
            System.out.println("adding to user dict");
            //get the word
            String word_text = word.getWord();
            //add the word to the user dictionary
            document.add_to_user_dict(word_text);
            System.out.println("added to user dict: " + word_text); 
            //set the word object is real word flag
            word.setIs_real_word(true);
            //set the word object modified flag
            word.setModified(true);
            //set the word object suggestion to null
            word.setSuggestions(null);
           

            //hide the context menu
            contextMenu.hide();
        });
        //return the custom menu item
        return new CustomMenuItem(label, true);
    }

    private void showPopupAtTextPosition_spelling(Word_Object word, double x, double y) {
        // Clear existing items and prepare new ones
        contextMenu.getItems().clear();
        String[] suggestions = word.getSuggestions();
        //get the word start and stop indicies
        


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
            implement_suggestion(word, suggestion);

            //update fix and missplet couts
    
            // Hide the context menu
            contextMenu.hide();
    
            // Additional functionality like re-running spell check can be added here
        });
    
        return new CustomMenuItem(label, false);
    }

    private void implement_suggestion(Word_Object word, String suggestion) {
        // Get indices from adjacent nodes
        int startIndex = (word.getPrev_node() != null) ? word.getPrev_node().getEnd_index() + 1 : 0;
        int endIndex = (word.getNext_node() != null) ? word.getNext_node().getStart_index() : textArea.getLength();
    
        // Debugging print statements
        System.out.println("Replacing '" + textArea.getText(startIndex, endIndex) + "' with '" + suggestion + "'");
    
        // Replace the text with new word and surrounding spaces
        textArea.replaceText(startIndex, endIndex, " " + suggestion + " ");
    
        // Update the Word_Object and document stats
        word.setWord(suggestion);
        word.setIs_real_word(true);
        word.setModified(false);
        document.decrease_current_misspelt_words();
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

    public void highlightWords(){
    String text = textArea.getText();
        String[] words = text.split("\\s+"); // Split text into words
    
        int startIndex = 0;
        for (String word : words) {
            int endIndex = startIndex + word.length();
    
            // Get the word object from the linked list
            Word_Object this_word = document.wordBuffer.get_word_at_index(startIndex+1);

            if(this_word == null){
                System.out.println("word is null");
                return;
            }
    
            if (!this_word.isIs_real_word()) {
                textArea.setStyleClass(startIndex, endIndex, "misspelled-word");
            } 
            else if (this_word.isIs_double_word_after() || this_word.isIs_double_word_before()) {
                textArea.setStyleClass(startIndex, endIndex, "double-word");
            
            }else if(this_word.isNeeds_capital() || this_word.isNeeds_period()){
                textArea.setStyleClass(startIndex, endIndex, "capital-word");
            } else {
                textArea.setStyleClass(startIndex, endIndex, "normal-word");
            }
    
            startIndex = endIndex + 1; // +1 for the space after the word
        }
    }
   
    

    
}