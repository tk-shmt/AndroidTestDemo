package com.shimotsuji.android.taxcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    private EditText priceEditText;
    private TextView resultTextView;
    private TaxCalculator taxCalculator; // TaxCalculatorのインスタンス

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceEditText = findViewById(R.id.priceEditText);
        Button calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        taxCalculator = new TaxCalculator(); // TaxCalculatorを初期化

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTaxIncludedPrice();
            }
        });
    }

    private void calculateTaxIncludedPrice() {
        String priceString = priceEditText.getText().toString();

        if (priceString.isEmpty()) {
            resultTextView.setText("金額を入力してください。");
            return;
        }

        try {
            double price = Double.parseDouble(priceString);
            double taxRate = 0.1; // 税率10%
            double taxIncludedPrice = taxCalculator.calculateTaxIncludedPrice(price, taxRate);
            resultTextView.setText(MessageFormat.format("税込み価格: {0}", taxIncludedPrice));
        } catch (NumberFormatException e) {
            resultTextView.setText("有効な金額を入力してください。");
        }
    }
}