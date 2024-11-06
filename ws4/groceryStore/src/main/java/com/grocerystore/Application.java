package com.grocerystore;

import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Application {

  private Cart cart;

  // public void init() throws FileNotFoundException {
  //   cart = new Cart();
  //   cart.init();
  // }

  public void start(Stage primaryStage) throws IOException {
    cart.start(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }
}