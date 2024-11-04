package com.pizzashop;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class pizzaShopController {

  private List<Order> orders;

  public pizzaShopController() {
    this.orders = new ArrayList<>();
  }

  @FXML
  private TextField customerNameField;
  
  @FXML
  private TextField customerPhoneField;
  
  @FXML
  private RadioButton cheeseRadioBtn;
  
  @FXML
  private RadioButton vegetarianRadioBtn;
  
  @FXML
  private RadioButton meatLoverRadioBtn;
  
  @FXML
  private Button placeOrderBtn;
  
  @FXML
  private Button clearBtn;

  @FXML
  private ChoiceBox<String> pizzaSizeChoiceBox;

  @FXML
  private TextField quantityField;
  
  @FXML
  private TextArea orderSummaryTextArea;

  @FXML
  public void initialize() {
    pizzaSizeChoiceBox.getItems().addAll("Small", "Medium", "Large");
    pizzaSizeChoiceBox.setValue("Small");
    ToggleGroup pizzaTypeGroup = new ToggleGroup();
    cheeseRadioBtn.setToggleGroup(pizzaTypeGroup);
    vegetarianRadioBtn.setToggleGroup(pizzaTypeGroup);
    meatLoverRadioBtn.setToggleGroup(pizzaTypeGroup);
    orderSummaryTextArea.setEditable(false);
  }

  @FXML
  public void placeOrder() {
    // Collect customer's information
    String customerName = customerNameField.getText();
    String customerPhone = customerPhoneField.getText();

    // Create an object of customer
    Customer customer = new Customer(customerName, customerPhone);

    // Collect pizza type
    Order order = getOrder(customer);
    // Add order into the list
    orders.add(order);
    // Display the summary of the order
    displayOrderSummary(order);
  }

  @FXML
  public void clearFields() {
    customerNameField.clear();
    customerPhoneField.clear();
    cheeseRadioBtn.setSelected(true);
    vegetarianRadioBtn.setSelected(false);
    meatLoverRadioBtn.setSelected(false);
    pizzaSizeChoiceBox.setValue("Small");
    quantityField.clear();
    orderSummaryTextArea.clear();
  }

  private Order getOrder(Customer customer) {
    String pizzaType;
    if (cheeseRadioBtn.isSelected()) {
      pizzaType = "Cheese";
    } else if (vegetarianRadioBtn.isSelected()) {
      pizzaType = "Vegetarian";
    } else {
      pizzaType = "Meat Lover";
    }

    // Collect pizza size
    String pizzaSize = pizzaSizeChoiceBox.getValue();

    // Create an object of pizza
    Pizza pizza = new Pizza(pizzaType, pizzaSize);

    // Collect quantity
    int quantity = Integer.parseInt(quantityField.getText());

    // Create an object of order
    return new Order(customer, pizza, quantity);
  }

  private void displayOrderSummary(Order order) {
    String orderSummary = String.format(
            "%-20s: %s\n" +
                    "%-20s: %s\n" +
                    "%-20s: %s\n" +
                    "%-20s: %s\n" +
                    "%-20s: %d\n" +
                    "%-20s: $%.2f\n" +
                    "%-20s: $%.2f\n" +
                    "%-20s: $%.2f",
            "Customer Name", order.customer().name(),
            "Customer Phone", order.customer().phone(),
            "Pizza Type                  ", order.pizza().type(),
            "Pizza Size                   ", order.pizza().size(),
            "Quantity                      ", order.quantity(),
            "SubTotal                     ", order.getTotalBeforeTax(),
            "Tax                                ", order.getTotalBeforeTax() * 0.13,
            "Total                             ", order.getTotalWithTax());

    orderSummaryTextArea.setText(orderSummary);
    orderSummaryTextArea.setEditable(false);
  }
}
