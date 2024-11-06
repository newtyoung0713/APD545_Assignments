package com.grocerystore;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {

  private final StringProperty name;
  private StringProperty unit;
  private DoubleProperty unitQuantity;
  private DoubleProperty unitPrice;

  public Item() {
    this.name = new SimpleStringProperty();
    this.unit = new SimpleStringProperty();
    this.unitQuantity = new SimpleDoubleProperty();
    this.unitPrice = new SimpleDoubleProperty();
  }

  public Item(String name, String unit, Double unitQty, Double unitPrice) {
    this.name = new SimpleStringProperty(name);
    this.unit = new SimpleStringProperty(unit);
    this.unitQuantity = new SimpleDoubleProperty(unitQty);
    this.unitPrice = new SimpleDoubleProperty(unitPrice);
  }

  @Override
  public String toString() { return name.get(); }

  public String getName() { return name.get(); }
  public void setName(String name) { this.name.set(name); }
  public StringProperty nameProperty() { return name;}

  public String getUnit() { return unit.get(); }
  public void setUnit(String unit) { this.unit.set(unit); }
  public StringProperty unitProperty() { return unit;}

  public Double getUnitQuantity() { return unitQuantity.get(); }
  public void setUnitQuantity(Double unitQuantity) { this.unitQuantity.set(unitQuantity); }
  public DoubleProperty unitQuantityProperty() { return unitQuantity;}

  public Double getUnitPrice() { return unitPrice.get(); }
  public void setUnitPrice(Double unitPrice) { this.unitPrice.set(unitPrice); }
  public DoubleProperty unitPriceProperty() { return unitPrice;}
}
