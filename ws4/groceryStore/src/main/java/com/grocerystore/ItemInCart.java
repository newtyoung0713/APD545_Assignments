package com.grocerystore;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ItemInCart {

  private StringProperty name;
  private DoubleProperty quantity;
  private DoubleProperty purchasePrice;

  public ItemInCart(String name, Double qty, Double price) {
    this.name = new SimpleStringProperty(name);
    this.quantity = new SimpleDoubleProperty(qty);
    this.purchasePrice = new SimpleDoubleProperty(price);
  }

  public String getName() { return name.get(); }

  public StringProperty nameProperty() { return name; }

  public Double getQuantity() { return quantity.get(); }

  public DoubleProperty quantityProperty() { return quantity; }

  public Double getPurchasePrice() { return purchasePrice.get(); }

  public DoubleProperty purchasePriceProperty() { return purchasePrice; }
}
