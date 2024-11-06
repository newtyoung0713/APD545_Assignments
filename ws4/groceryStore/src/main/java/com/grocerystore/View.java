package com.grocerystore;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class View {
  @FXML private ComboBox<Item> itemsComboBox = new ComboBox<>();
  @FXML private Label unitValueLabel;
  @FXML private Label unitPriceValueLabel;
  @FXML private Slider quantitySlider;
  @FXML private Label purchaseQuantityValueLabel;
  @FXML private Label purchasePriceValueLabel;
  @FXML private Button addButton;
  @FXML private Button removeButton;
  @FXML private TableView<ItemInCart> cartTableView;
  @FXML private TableColumn<ItemInCart, String> itemNameColumn;
  @FXML private TableColumn<ItemInCart, Double> purchaseQuantityColumn;
  @FXML private TableColumn<ItemInCart, Double> purchasePriceColumn;
  @FXML private Label totalAmountLabel;
  @FXML private Label detailsLabel;

  @FXML
  public void initialize() {
    // TableColumn<ItemInCart, String> nameColumn = new TableColumn<>("Item name");
    // TableColumn<ItemInCart, Double> quantityColumn = new TableColumn<>("Purchased units");
    // TableColumn<ItemInCart, Double> priceColumn = new TableColumn<>("Purchase price");

    itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    purchaseQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));

    cartTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemInCart>() {
      @Override
      public void changed(ObservableValue<? extends ItemInCart> observable, ItemInCart oldValue, ItemInCart newValue) {
        if (newValue != null) detailsLabel.setText(getItemDetails(newValue));
        else detailsLabel.setText("Use this portion to show the details when an item is being selected from the cart.");
      }
    });
    // cartTableView.getColumns().addAll(nameColumn, quantityColumn, priceColumn);
  }

  private String getItemDetails(ItemInCart itemInCart) {
    Item selectedItem = findItemByName(itemInCart.getName());
    if (selectedItem != null) {
      return String.format("Item Name: %s\nPrice: %.2f\nQty: %.2f\nUnit: %s",
                            selectedItem.getName(),
                            selectedItem.getUnitPrice(),
                            selectedItem.getUnitQuantity(),
                            selectedItem.getUnit());
    }
    return "Details not available.";
  }

  private Item findItemByName(String name) {
    for (Item item : itemsComboBox.getItems()) {
      if (item.getName().equals(name)) return item;
    }
    return null;
  }
  
  public ComboBox<Item> getItemsComboBox() {
    return itemsComboBox;
  }

  public Label getUnitValueLabel() {
    return unitValueLabel;
  }

  public Label getUnitPriceValueLabel() {
    return unitPriceValueLabel;
  }

  public Label getPurchasePriceValueLabel() {
    return purchasePriceValueLabel;
  }

  public Label getPurchaseQuantityLabel() {
    return purchaseQuantityValueLabel;
  }

  public Slider getQuantitySlider() {
    return quantitySlider;
  }

  public Button getAddButton() {
    return addButton;
  }

  public Button getRemoveButton() {
    return removeButton;
  }

  public TableView<ItemInCart> getCartTableView() {
    return cartTableView;
  }

  public Label getTotalAmountLabel() {
    return totalAmountLabel;
  }

  public void setItems(ObservableList<Item> items) {
    itemsComboBox.setItems(items);
  }

  public void setCartTableViewData(ObservableList<ItemInCart> cartItems) {
    cartTableView.setItems(cartItems);
  }

  // public BorderPane setupScene() {
  //   BorderPane root = new BorderPane();
    
  //   root.setTop(itemsComboBox);
    
  //   return root;
  // }
}