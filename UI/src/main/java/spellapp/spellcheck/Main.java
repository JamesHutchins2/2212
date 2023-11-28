package spellapp.spellcheck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FileChooser.fxml"));
        Parent root = loader.load();

        FileController fileController = loader.getController();
        fileController.setPrimaryStage(stage);
        
        
        

        

        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/spellapp/spellcheck/stylesheet.css").toExternalForm());
        stage.setTitle("Spellchecker");
        stage.setScene(scene);
        stage.show();
        //call a function in main scne controller to populate the text area
        
    }

    public static void main(String[] args) {
        launch();
    }
}