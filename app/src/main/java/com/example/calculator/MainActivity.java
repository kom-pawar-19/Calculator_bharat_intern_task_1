package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInput = findViewById(R.id.tvInput);
    }

    public void onDigit(View view) {
        Button button = (Button) view;
        String digit = button.getText().toString();
        String currentInput = tvInput.getText().toString();
        tvInput.setText(currentInput + digit);
    }

    public void onOperator(View view) {
        Button button = (Button) view;
        String operator = button.getText().toString();
        String currentInput = tvInput.getText().toString();
        tvInput.setText(currentInput + operator);
    }

    public void onDecimalPoint(View view) {
        String currentInput = tvInput.getText().toString();
        if (!currentInput.contains(".")) {
            tvInput.setText(currentInput + ".");
        }
    }

    public void onClear(View view) {
        tvInput.setText("");
    }

    public void onEqual(View view) {
        String currentInput = tvInput.getText().toString();

        // Split the input into individual numbers and operators
        String[] numbers = currentInput.split("[+*/-]");
        String[] operators = currentInput.split("[0-9]+");

        // Remove any empty strings from the arrays
        List<String> numberList = new ArrayList<>(Arrays.asList(numbers));
        numberList.removeIf(String::isEmpty);

        List<String> operatorList = new ArrayList<>(Arrays.asList(operators));
        operatorList.removeIf(String::isEmpty);

        // Perform the calculation
        double result = Double.parseDouble(numberList.get(0));
        int operatorIndex = 0;

        for (int i = 1; i < numberList.size(); i++) {
            double number = Double.parseDouble(numberList.get(i));
            String operator = operatorList.get(operatorIndex);

            switch (operator) {
                case "+":
                    result += number;
                    break;
                case "-":
                    result -= number;
                    break;
                case "*":
                    result *= number;
                    break;
                case "/":
                    result /= number;
                    break;
            }

            operatorIndex++;
        }

        // Update the input text with the result
        tvInput.setText(String.valueOf(result));
    }

}
