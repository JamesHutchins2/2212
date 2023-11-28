package spellapp.spellcheck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//ADDED FOR BACKEND CONNECTION
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
//import from backend
import Backend.Document;
import Backend.Word_Object;
import org.fxmisc.richtext.StyleClassedTextArea;




public class FileController {

    @FXML
    private Label label;
    private FileChooser fileChooser = new FileChooser();
    private Stage primaryStage;
    private StyleClassedTextArea textArea = new StyleClassedTextArea();
    //Create a document object
    Document document;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        

        }

        public void setTextAreaContent(String content) {
            textArea.replaceText(content); // Set the text content
    
            
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
                    //set the style sheet to the scene
                    mainSceneRoot.getStylesheets().add(getClass().getResource("/spellapp/spellcheck/stylesheet.css").toExternalForm());
                    //add text to text area variable
                    textArea = (StyleClassedTextArea) mainSceneRoot.lookup("#textArea");
                    
    
                    
    
                    // Get the controller of the main scene - and send it action to set text area of main text area
                    MainSceneController mainSceneController = loader.getController();
                    mainSceneController.setTextAreaContent(content.toString());
                    mainSceneController.init_document(textArea);
                    mainSceneController.startRepeatedTask();
                    
                    // Create Scene object for the main Scene - pass in initial window size
                    Scene mainScene = new Scene(mainSceneRoot, 800, 500);
                    // Make Scene visible
                    primaryStage.setScene(mainScene);
    
                
    
                    //now populate the linked list
                    
    
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
            System.out.println("got loader");
            Parent mainSceneRoot = loader.load();
            System.out.println("loaded");

            // Get the controller of the main scene - and send it action to set text area of main text area
            MainSceneController mainSceneController = loader.getController();
            mainSceneController.setTextAreaContent("");
            System.out.println("set text area content");
            if (getClass().getResourceAsStream("/spellapp/spellcheck/stylesheet.css") == null) {
                System.out.println("Stylesheet not found");
            } else {
                System.out.println("Stylesheet found");
            }

            // Create Scene object for the main Scene - pass in initial window size
            Scene mainScene = new Scene(mainSceneRoot, 800, 500);
            mainScene.getStylesheets().add(getClass().getResource("/spellapp/spellcheck/stylesheet.css").toExternalForm());
            System.out.println("created scene");
            // Make Scene visible
            primaryStage.setScene(mainScene);
            System.out.println("set scene");

        } catch (IOException e) {
            e.printStackTrace();
            label.setText("Error opening with blank file");
        }
    }

    



}