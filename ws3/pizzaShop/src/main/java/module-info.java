module com.pizzashop {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.pizzashop to javafx.fxml;
  exports com.pizzashop;
}