module com.example.carloan {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.example.carloan to javafx.fxml;
  exports com.example.carloan;
}