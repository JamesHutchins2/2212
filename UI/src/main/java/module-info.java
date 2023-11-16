module com.example.firstjavafxproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.firstjavafxproject to javafx.fxml;
    exports com.example.firstjavafxproject;
}