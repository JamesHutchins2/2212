module spellapp.spellcheck {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens spellapp.spellcheck to javafx.fxml;
    exports spellapp.spellcheck;
}