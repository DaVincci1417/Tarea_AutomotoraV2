module com.example.automotorav2 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.automotorav2 to javafx.fxml;
    exports com.example.automotorav2;
}