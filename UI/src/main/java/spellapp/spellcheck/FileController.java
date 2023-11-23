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
//import from backend
import Backend.Document;
import Backend.Word_Object;



public class FileController {

    @FXML
    private Label label;
    private FileChooser fileChooser = new FileChooser();
    private Stage primaryStage;
    private TextArea textArea;
    //Create a document object
    Document document;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        //call document
        //convert text area to string
        
        //convert to string array
        
        //send document to MainSceneController

        }

    public void setTextAreaContent(String content) {
        textArea.setText(content);
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
                //add text to text area variable
                textArea = (TextArea) mainSceneRoot.lookup("#textArea");
                

                

                // Get the controller of the main scene - and send it action to set text area of main text area
                MainSceneController mainSceneController = loader.getController();
                mainSceneController.setTextAreaContent(content.toString());
                mainSceneController.init_document(textArea);
                System.out.println("text area content: " + textArea.getText());
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
