package com.pizzashop;

// Using record to make the code easier and easy to maintain
// record is a keyword only for final variables, and automatically generate getters, but no setters
public record Customer(String name, String phone) {
}
