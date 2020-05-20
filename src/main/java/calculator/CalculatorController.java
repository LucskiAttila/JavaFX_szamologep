package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";
    private boolean dot = false;

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (display.getText().equals("0")) {
            if (dot) {
                display.setText(display.getText() + digitPressed);
                return;
            } else {
                display.setText(digitPressed);
                return;
            }
        }
        if (startNumber) {
            display.setText(display.getText() + digitPressed);
        } else {
            display.setText(digitPressed);
            startNumber = true;
            dot = false;
        }
    }


    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("AC")) {
            display.setText("0");
            operator = "";
            startNumber = true;
            number1 = 0;
            dot = false;
            return;
        }
        if (operatorPressed.equals(".")) {
            if (!dot) {
                display.setText(display.getText() + operatorPressed);
                dot = true;
                startNumber = true;
                return;
            } else {
                return;
            }
        }
        if (operatorPressed.equals("\u00B1")){
            if (Double.parseDouble(display.getText()) == 0.0) {
                return;
            }
            display.setText(String.valueOf(calculator.calculate(Double.parseDouble(display.getText()), -1, "*")));
            dot = true;
            return;
        }
        if (operatorPressed.equals("=")) {
            if (operator.isEmpty()) {
                return;
            }
            double number2 = Double.parseDouble(display.getText());
            double result = calculator.calculate(number1, number2, operator);
            display.setText(String.valueOf(result));
            startNumber = false;
            dot = true;
            operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = false;
            dot = false;
        }
    }

}
