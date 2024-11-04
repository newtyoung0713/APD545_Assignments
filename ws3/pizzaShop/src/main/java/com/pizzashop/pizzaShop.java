/**********************************************
Workshop #3
Course:<APD545> - Semester
Last Name:<Yang>
First Name:<Sheng-Lin>
ID:<160443222>
Section:<NBB>
This assignment represents my own work in accordance with Seneca Academic Policy. Signature
Date:<Oct-11 2024>
**********************************************/

package com.pizzashop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class pizzaShop extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    try {
      // Loading FXML document
      Parent root = FXMLLoader.load(getClass().getResource("/com/pizzashop/pizzashop.fxml"));
      
      // Setting the scene
      stage.setTitle("Pizza Shop");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
