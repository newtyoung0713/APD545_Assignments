module com.grocerystore {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.grocerystore to javafx.fxml;
  exports com.grocerystore;
}