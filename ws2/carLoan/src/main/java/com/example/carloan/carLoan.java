/**********************************************
Workshop #2
Course:<APD545> - Semester
Last Name:<Yang>
First Name:<Sheng-Lin>
ID:<160443222>
Section:<NBB>
This assignment represents my own work in accordance with Seneca Academic Policy. Signature
Date:<Sep-XX 2024>
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
  private TextField vehicleTypeField, vehicleAgeField, carPriceField,
                    downPaymentField, interestRateField, loanPaymentFrequencyField,
                    monthlyPaymentField;
  private Label vehicleTypeLabel, vehicleAgeLabel, carPriceLabel,
                downPaymentLabel, interestRateLabel, loanPaymentFrequencyLabel,
                loanDurationLabel, estimatedLoanLabel;
  private Slider loanDurationSlider;
  private Button calculateButton, clearButton;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Car Payment");

    // Initialize UI elements
    vehicleTypeField = new TextField();
    vehicleTypeField.setPromptText("Car/ Truck");
    vehicleTypeField.setPrefWidth(270);
    vehicleAgeField = new TextField();
    vehicleAgeField.setPromptText("New/ Used");
    carPriceField = new TextField();
    carPriceField.setPromptText("$0.00");
    downPaymentField = new TextField();
    downPaymentField.setPromptText("$0.00");
    interestRateField = new TextField();
    interestRateField.setPromptText("0.00%");
    loanPaymentFrequencyField = new TextField();
    loanPaymentFrequencyField.setPromptText("Weekly/ Bi-Weekly/ Monthly");
    monthlyPaymentField = new TextField();
    monthlyPaymentField.setPromptText("$0.00");
    monthlyPaymentField.setEditable(false);

    vehicleTypeLabel = new Label("Type of Vehicle:");
    vehicleAgeLabel = new Label("Age of Vehicle:");
    carPriceLabel = new Label("Price of the Vehicle: $");
    downPaymentLabel = new Label("Down Payment: $");
    interestRateLabel = new Label("Interest Rate: %");
    loanDurationLabel = new Label("Loan Payment Period:");
    loanPaymentFrequencyLabel = new Label("Loan Payment Frequency:");
    loanDurationSlider = new Slider(12, 96, 12);  // Slider with min 12, max 96, initial 12
    loanDurationSlider.setMajorTickUnit(12);      // Set tick marks every 12 months
    loanDurationSlider.setShowTickMarks(true);
    loanDurationSlider.setShowTickLabels(true);
    estimatedLoanLabel = new Label("Your Estimated Fixed Rate Loan Payment is");
    estimatedLoanLabel.setWrapText(true);
    estimatedLoanLabel.setMaxWidth(150);

    calculateButton = new Button("Get Results");
    clearButton = new Button("Clear");

    // Set up layout
    GridPane gridPane = new GridPane();
    GridPane.setHalignment(vehicleTypeLabel, HPos.RIGHT);
    GridPane.setHalignment(vehicleAgeLabel, HPos.RIGHT);
    GridPane.setHalignment(carPriceLabel, HPos.RIGHT);
    GridPane.setHalignment(downPaymentLabel, HPos.RIGHT);
    GridPane.setHalignment(interestRateLabel, HPos.RIGHT);
    GridPane.setHalignment(loanDurationLabel, HPos.RIGHT);
    GridPane.setHalignment(loanPaymentFrequencyLabel, HPos.RIGHT);
    gridPane.setPadding(new Insets(20, 10, 10, 10));
    gridPane.setHgap(50);
    gridPane.setVgap(10);

    // Add components to GridPane
    gridPane.add(vehicleTypeLabel, 0, 0);
    gridPane.add(vehicleTypeField, 1, 0);

    gridPane.add(vehicleAgeLabel, 0, 1);
    gridPane.add(vehicleAgeField, 1, 1);

    gridPane.add(carPriceLabel, 0, 2);
    gridPane.add(carPriceField, 1, 2);

    gridPane.add(downPaymentLabel, 0, 3);
    gridPane.add(downPaymentField, 1, 3);

    gridPane.add(interestRateLabel, 0, 4);
    gridPane.add(interestRateField, 1, 4);

    gridPane.add(loanDurationLabel, 0, 5);
    gridPane.add(loanDurationSlider, 1, 5);

    gridPane.add(loanPaymentFrequencyLabel, 0, 6);
    gridPane.add(loanPaymentFrequencyField, 1, 6);

    HBox buttonBox = new HBox(10, clearButton, calculateButton);
    gridPane.add(buttonBox, 1, 7);

    gridPane.add(estimatedLoanLabel, 0, 8);
    gridPane.add(monthlyPaymentField, 1, 8);

    // Create the scene and set the stage (w: 500, h: 400)
    Scene scene = new Scene(gridPane, 500, 400);
    primaryStage.setScene(scene);
    primaryStage.show();

    // Event handlers
    loanDurationSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      // int months = newVal.intValue();
      // loanDurationLabel.setText("Loan Payment Period: " + months + " months");
      loanDurationLabel.setText("Loan Payment Period:");
    });

    addNumericValidation(carPriceField);
    addNumericValidation(downPaymentField);
    addNumericValidation(interestRateField);

    calculateButton.setOnAction(e -> calculateMonthlyPayment());
    clearButton.setOnAction(e -> clearForm());
  }

  private void calculateMonthlyPayment() {
    // Check if all fields are filled
    if (vehicleTypeField.getText().isEmpty() ||
        vehicleAgeField.getText().isEmpty() ||
        carPriceField.getText().isEmpty() ||
        downPaymentField.getText().isEmpty() ||
        interestRateField.getText().isEmpty() ||
        loanPaymentFrequencyField.getText().isEmpty()) {
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

      // Get loan payment frequency
      String paymentFrequency = loanPaymentFrequencyField.getText().toLowerCase();
      double paymentPerPeriod;
      int periods = 0;
      double periodicInterestRate = 0;
      // Determine payment frequency logic
      switch (paymentFrequency) {
        case "weekly":
          periods = loanDuration * 4 * 12 / 52; // Loan duration in weeks
          periodicInterestRate = interestRate / 52; // Weekly interest rate
          break;
        case "biweekly":
        case "bi-weekly":
          periods = loanDuration * 4 * 12 / 26; // Loan duration in bi-weekly periods
          periodicInterestRate = interestRate / 26; // Bi-weekly interest rate
          break;
        case "monthly":
          periods = loanDuration; // Loan duration in months
          periodicInterestRate = interestRate / 12; // Monthly interest rate
          break;
        default:
          monthlyPaymentField.setText("Please enter Weekly, Bi-weekly, or Monthly");
          return;
      }

      // Calculate payment per period
      paymentPerPeriod = (loanAmount * periodicInterestRate) / (1 - Math.pow(1 + periodicInterestRate, -periods));

      // Display the result formatted as currency
      monthlyPaymentField.setText(String.format("$%.2f", paymentPerPeriod));
    } catch (NumberFormatException ex) {
      // Handle invalid input
      monthlyPaymentField.setText("Invalid input");
    }
  }

  private void clearForm() {
    // Clear all input fields and reset slider
    vehicleTypeField.clear();
    vehicleAgeField.clear();
    carPriceField.clear();
    downPaymentField.clear();
    interestRateField.clear();
    loanDurationSlider.setValue(12);
    loanPaymentFrequencyField.clear();
    monthlyPaymentField.clear();
  }

  private void addNumericValidation(TextField textField) {
   textField.textProperty().addListener((observable, oldValue, newValue) -> {
     if (!newValue.matches("\\d*(\\.\\d*)?")) interestRateField.setText(oldValue);
   });
  }
}