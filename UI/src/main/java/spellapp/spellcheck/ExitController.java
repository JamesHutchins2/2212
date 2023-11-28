package spellapp.spellcheck;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ExitController {
    @FXML
    void exitExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void exitSave(ActionEvent event) {
        // Basically do same as if the save button was clicked
            // Bring up the Save popup
            // Have user set the filename and path
            // Do the save

        // Then exit
        Platform.exit();
    }


}