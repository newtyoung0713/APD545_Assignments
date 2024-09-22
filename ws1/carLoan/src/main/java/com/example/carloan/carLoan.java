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
          loanDurationLabel, monthlyPaymentLabel, estimatedLoanLabel;
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
    vehicleTypeField.setPrefWidth(200);
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
    // monthlyPaymentLabel = new Label("Monthly Payment: $0.00");
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

    // Create the scene and set the stage (w: 450, h: 500)
    Scene scene = new Scene(gridPane, 450, 500);
    primaryStage.setScene(scene);
    primaryStage.show();

    // Event handlers
    loanDurationSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      int months = newVal.intValue();
      // loanDurationLabel.setText("Loan Payment Period: " + months + " months");
      loanDurationLabel.setText("Loan Payment Period:");
    });

    calculateButton.setOnAction(e -> calculateMonthlyPayment());
    clearButton.setOnAction(e -> clearForm());
  }

  private void calculateMonthlyPayment() {
    try {
      // Get user input
      double carPrice = Double.parseDouble(carPriceField.getText());
      double downPayment = Double.parseDouble(downPaymentField.getText());
      double loanAmount = carPrice - downPayment;
      double interestRate = Double.parseDouble(interestRateField.getText()) / 100;
      int loanDuration = (int) loanDurationSlider.getValue();

      // Monthly interest rate
      double monthlyInterestRate = interestRate / 12;

      // Calculate monthly payment
      double monthlyPayment = (loanAmount * monthlyInterestRate) /
              (1 - Math.pow(1 + monthlyInterestRate, -loanDuration));

      // Display the result formatted as currency
      monthlyPaymentLabel.setText(String.format("Monthly Payment: $%.2f", monthlyPayment));
    } catch (NumberFormatException ex) {
      // Handle invalid input
      monthlyPaymentLabel.setText("Please enter valid numbers.");
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
    monthlyPaymentLabel.setText("Monthly Payment: $0.00");
  }
}