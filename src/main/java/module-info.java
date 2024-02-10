module com.example.uga_hacks_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens com.example.uga_hacks_project;
       // to javafx.fxml;
    exports com.example.uga_hacks_project;
}
