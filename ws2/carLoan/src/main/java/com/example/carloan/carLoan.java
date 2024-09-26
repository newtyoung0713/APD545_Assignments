/**********************************************
Workshop #2
Course:<APD545> - Semester
Last Name:<Yang>
First Name:<Sheng-Lin>
ID:<160443222>
Section:<NBB>
This assignment represents my own work in accordance with Seneca Academic Policy. Signature
Date:<Sep-26 2024>
**********************************************/

package com.example.carloan;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class carLoan extends Application {

  // UI Elements
  private TextField carPriceField, downPaymentField, interestRateField, monthlyPaymentField;
  private Label carPriceLabel, loanDurationLabel, downPaymentLabel,
                interestRateLabel, monthlyPaymentLabel;
  private Slider loanDurationSlider;
  private Button calculateButton, clearButton;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Car Payment");

    // Initialize UI elements
    carPriceField = new TextField();
    downPaymentField = new TextField();
    interestRateField = new TextField();
    monthlyPaymentField = new TextField();
    monthlyPaymentField.setEditable(false);

    carPriceLabel = new Label("Car Price $");
    loanDurationLabel = new Label("Years :");
    loanDurationSlider = new Slider(2, 5, 1);  // Slider with min 2, max 5, initial 1
    loanDurationSlider.setMajorTickUnit(1);      // Set tick marks every 1 year
    loanDurationSlider.setShowTickMarks(true);
    loanDurationSlider.setShowTickLabels(true);
    downPaymentLabel = new Label("Down Payment $");
    interestRateLabel = new Label("Interest Rate %");
    monthlyPaymentLabel = new Label("Monthly Payment $");
    monthlyPaymentLabel.setWrapText(true);
    monthlyPaymentLabel.setMaxWidth(150);

    clearButton = new Button("Clear");
    calculateButton = new Button("Calculate");

    // Set up layout
    GridPane gridPane = new GridPane();
    GridPane.setHalignment(carPriceLabel, HPos.LEFT);
    GridPane.setHalignment(downPaymentLabel, HPos.LEFT);
    GridPane.setHalignment(interestRateLabel, HPos.LEFT);
    GridPane.setHalignment(loanDurationLabel, HPos.LEFT);
    GridPane.setHalignment(monthlyPaymentLabel, HPos.LEFT);
    gridPane.setPadding(new Insets(20, 10, 10, 10));
    gridPane.setHgap(50);
    gridPane.setVgap(10);

    // Add components to GridPane
    gridPane.add(carPriceLabel, 0, 0);
    gridPane.add(carPriceField, 1, 0);

    gridPane.add(loanDurationLabel, 0, 1);
    gridPane.add(loanDurationSlider, 1, 1);

    gridPane.add(downPaymentLabel, 0, 2);
    gridPane.add(downPaymentField, 1, 2);

    gridPane.add(interestRateLabel, 0, 3);
    gridPane.add(interestRateField, 1, 3);

    gridPane.add(monthlyPaymentLabel, 0, 4);
    gridPane.add(monthlyPaymentField, 1, 4);

    HBox buttonBox = new HBox(10, clearButton, calculateButton);
    gridPane.add(buttonBox, 1, 5);

    // Create the scene and set the stage (w: 400, h: 300)
    Scene scene = new Scene(gridPane, 400, 300);
    primaryStage.setScene(scene);
    primaryStage.show();

    // Event handlers
    loanDurationSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      loanDurationLabel.setText("Years :");
    });

    addNumericValidation(carPriceField);
    addNumericValidation(downPaymentField);
    addNumericValidation(interestRateField);

    calculateButton.setOnAction(e -> calculateMonthlyPayment());
    clearButton.setOnAction(e -> clearForm());
  }

  private void calculateMonthlyPayment() {
    // Check if all fields are filled
    if (carPriceField.getText().isEmpty() ||
        downPaymentField.getText().isEmpty() ||
        interestRateField.getText().isEmpty()) {
      // Show an error message if any field is empty
      monthlyPaymentField.setText("All fields are required.");
      return;
    }

    try {
      // Get user input
      double carPrice = Double.parseDouble(carPriceField.getText());
      double downPayment = Double.parseDouble(downPaymentField.getText());
      double loanAmount = carPrice - downPayment;
      double interestRate = Double.parseDouble(interestRateField.getText()) / 100;
      int loanDuration = (int) loanDurationSlider.getValue();

      // Check for any invalid input (e.g., negative loanAmount)
      if (loanAmount <= 0 || interestRate <= 0 || loanDuration <= 0) {
        monthlyPaymentField.setText("Invalid Input. Please check the numbers.");
        return;
      }

      // Monthly interest rate
      double monthlyInterestRate = interestRate / 12;
      // Total number of months
      int months = loanDuration * 12;
      // Calculate payment per month
      double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -months));
      // Display the result formatted as currency
      monthlyPaymentField.setText(String.format("$%.2f", monthlyPayment));
    } catch (NumberFormatException ex) {
      // Handle invalid input
      monthlyPaymentField.setText("Invalid input");
    }
  }

  private void clearForm() {
    // Clear all input fields and reset slider
    carPriceField.clear();
    downPaymentField.clear();
    interestRateField.clear();
    loanDurationSlider.setValue(2);
    monthlyPaymentField.clear();
  }

  private void addNumericValidation(TextField textField) {
   textField.textProperty().addListener((observable, oldValue, newValue) -> {
     if (!newValue.matches("\\d*(\\.\\d*)?")) textField.setText(oldValue);
   });
  }
}