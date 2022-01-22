module com.example.transportcegiel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.transportcegiel to javafx.fxml;
    exports com.example.transportcegiel;
}