package com.grocerystore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Model {

  private ObservableList<Item> itemsObservableList;
  private ObservableList<ItemInCart> cartObservableList;

  public Model() {
    itemsObservableList = FXCollections.observableArrayList();
    cartObservableList = FXCollections.observableArrayList();
  }

  public void loadData() {
    // itemsObservableList.clear(); // Clear existing data before loading

    String data;
    InputStream is = getClass().getResourceAsStream("ItemsMaster.csv");
    try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
      while ((data = br.readLine()) != null) {
        String[] values = data.split(",");
        String name = values[0].trim();
        String unit = values[1].trim();
        Double unitQty = Double.parseDouble(values[2].trim());
        Double unitPrice = Double.parseDouble(values[3].trim());
        itemsObservableList.add(new Item(name, unit, unitQty, unitPrice));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


//    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ItemsMaster.csv")) {
//      if (inputStream == null) {
//        throw new IOException("Cannot find ItemsMaster.csv");
//      }
//
//      try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
//        String data;
//        while ((data = br.readLine()) != null) {
//          String[] values = data.split(",");
//          if (values.length >= 4) {
//            try {
//              String name = values[0].trim();
//              String unit = values[1].trim();
//              Double unitQty = Double.parseDouble(values[2].trim());
//              Double unitPrice = Double.parseDouble(values[3].trim());
//
//              itemsObservableList.add(new Item(name, unit, unitQty, unitPrice));
//            } catch (NumberFormatException e) {
//              System.err.println("Invalid number format in line: " + data);
//            }
//          } else {
//            System.err.println("Invalid data format: " + data);
//          }
//        }
//      }
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
  }

  public ObservableList<Item> getItemsObservableList() {
    return itemsObservableList;
  }

  public ObservableList<ItemInCart> getCartObservableList() {
    return cartObservableList;
  }
}
