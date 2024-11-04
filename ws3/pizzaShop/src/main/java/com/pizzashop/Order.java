package com.pizzashop;

// Using record to make the code easier and easy to maintain
// record is a keyword only for final variables, and automatically generate getters, but no setters
public record Order(Customer customer, Pizza pizza, int quantity) {

  private double calculatePrice(String size) {
    return switch (size) {
      case "Small" -> 5.00;
      case "Medium" -> 10.00;
      case "Large" -> 15.00;
      default -> 0.00;
    };
  }

  public double getTotalBeforeTax() {
    return calculatePrice(pizza.size()) * quantity;
  }

  public double getTotalWithTax() {
    double totalBeforeTax = getTotalBeforeTax();
    return totalBeforeTax + (totalBeforeTax * 0.13);
  }
}
