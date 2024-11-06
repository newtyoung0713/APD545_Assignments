package com.grocerystore;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Cart extends Application {

  private Model model;
  private View view;

  // @Override
  // public void init() {
    //   model.loadData();
    //   view = new View(model.getItemsList());
    // }
    
  @Override
  public void start(Stage primaryStage) throws IOException {
    model = new Model();
    model.loadData();
    
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/grocerystore/view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    view = fxmlLoader.getController();
    setupAction();
    primaryStage.setTitle("Shopping Cart");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void setupAction() {
    view.setItems(model.getItemsObservableList());

    view.getPurchaseQuantityLabel().textProperty().bind(
      view.getQuantitySlider().valueProperty().asString("%.0f")
    );

    view.getItemsComboBox().valueProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        view.getUnitValueLabel().textProperty().bind(Bindings.format("%.2f %s", newValue.unitQuantityProperty(), newValue.unitProperty()));
        view.getUnitPriceValueLabel().textProperty().bind(newValue.unitPriceProperty().asString("%.2f"));
      } else {
        view.getUnitValueLabel().textProperty().unbind();
        view.getUnitPriceValueLabel().textProperty().unbind();
        view.getUnitValueLabel().setText("0.00");
        view.getUnitPriceValueLabel().setText("0.00");
      }
    });

    view.getPurchasePriceValueLabel().textProperty().bind(Bindings.createStringBinding(() -> {
      Item selectedItem = view.getItemsComboBox().getSelectionModel().getSelectedItem();
      if (selectedItem != null) {
        double totalPrice = view.getQuantitySlider().getValue() * selectedItem.getUnitPrice();
        return String.format("%.2f", totalPrice);
      }
      return "0.00";
    }, view.getQuantitySlider().valueProperty(), view.getItemsComboBox().valueProperty()));

    view.getItemsComboBox().getSelectionModel().clearSelection();

    view.setCartTableViewData(model.getCartObservableList());

    view.getAddButton().setOnAction(event -> {
      Item selectedItem = view.getItemsComboBox().getSelectionModel().getSelectedItem();
      if (selectedItem != null) {
        double quantity = view.getQuantitySlider().getValue();
        double purchasePrice = quantity * selectedItem.getUnitPrice();
        ItemInCart itemInCart = new ItemInCart(selectedItem.getName(), quantity, purchasePrice);
        model.getCartObservableList().add(itemInCart);
      }
    });

    view.getRemoveButton().setOnAction(event -> {
      int selectedIndex = view.getCartTableView().getSelectionModel().getSelectedIndex();
      if (selectedIndex >= 0) {
        model.getCartObservableList().remove(selectedIndex);
      }
    });

    DoubleBinding totalBinding = Bindings.createDoubleBinding(() ->
            model.getCartObservableList().stream().mapToDouble(ItemInCart::getPurchasePrice).sum(),
            model.getCartObservableList()
    );

    view.getTotalAmountLabel().textProperty().bind(totalBinding.asString("%.2f"));

    view.getCartTableView().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (newSelection != null) {
        model.getItemsObservableList().stream().filter(item -> item.getName().equals(newSelection.getName())).findFirst().ifPresent(item -> {
          view.getItemsComboBox().getSelectionModel().select(item);
          view.getQuantitySlider().setValue(newSelection.getQuantity());
        });
      }
    });
  }

  public static void main(String[] args) {
    launch(args);
  }
}
