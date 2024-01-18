module com.mycompany.neni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.neni to javafx.fxml;
    exports com.mycompany.neni;
}
