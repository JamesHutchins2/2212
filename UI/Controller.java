package com.example.firstjavafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controller {

    @FXML
    private Label label;
    private FileChooser fileChooser = new FileChooser();
    private AnchorPane rootPane;
    @FXML
    protected void handleOpenButton(ActionEvent event) {
        File file = fileChooser.showOpenDialog(label.getScene().getWindow());

        if (file != null) {
            try {
                StringBuilder content = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                reader.close();

                label.setText("File Content:\n" + content.toString());
            } catch (IOException e) {
                e.printStackTrace();
                label.setText("Error reading the file");
            }


        }
    }
}
