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

/**
 * @author      James Hutchins
 * @author      Michelle Bourdon
 * @author      Jessica Kerr
 * @author      Laila El attar
 * @author      Nouran Sakr
 * @version     1.0
 * @since       0.0
 * The ExitController class handles actions related to exiting the application.
 */
public class ExitController {
    /**
     * Handles the "Exit" button click event to exit the application.
     * @param event The ActionEvent triggered by the "Exit" button.
     */
    @FXML
    void exitExit(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Handles the "Save" button click event to save data and exit the application.
     * This method performs the following steps:
     *     Brings up the Save popup.
     *     Allows the user to set the filename and path.
     *     Performs the save operation.
     *     Exits the application.
     * @param event The ActionEvent triggered by the "Save" button.
     */

    @FXML
    private void exitSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            saveSystem(file, textArea.getText());
        }
        Platform.exit();
    }

    /**
     * saveSystem saves the file
     * @param file is the file to be saved
     * @param text is the string to be saved in the file
     */
    private void saveSystem(File file, String text) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.write(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
