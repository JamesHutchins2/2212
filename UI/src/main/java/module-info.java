module spellapp.spellcheck {
    requires javafx.controls;
    requires javafx.fxml;


    opens spellapp.spellcheck to javafx.fxml;
    exports spellapp.spellcheck;
}