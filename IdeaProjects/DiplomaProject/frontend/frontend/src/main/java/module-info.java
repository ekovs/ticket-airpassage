module com.ekovs.frontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ekovs.frontend to javafx.fxml;
    exports com.ekovs.frontend;
}