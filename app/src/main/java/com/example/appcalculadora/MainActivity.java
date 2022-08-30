package com.example.appcalculadora;


import android.app.Activity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.app.*;
import java.util.regex.*;

public class MainActivity extends Activity {


    EditText txtValue1, txtValue2, txtResult;
    Button btnSum, btnDivision, btnSubtraction, btnMultiplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValue1 = findViewById(R.id.txtValue1);
        txtValue2 = findViewById(R.id.txtValue2);
        txtResult = findViewById(R.id.txtResult);
        btnSum = findViewById(R.id.btnSum);
        btnSubtraction = findViewById(R.id.btnSubtraction);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiplication = findViewById(R.id.btnMultiplication);

        btnSum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(!IsEmptyEntryOrNotNum(txtValue1, txtValue2))
                {
                    txtResult.setText("");
                    double sum = DoSum(Double.parseDouble(txtValue1.getText().toString()), Double.parseDouble(txtValue2.getText().toString()));

                    txtResult.setText(String.valueOf(sum));
                    ShowResult("Soma", sum);
                }
                else
                {
                    ShowEmptyEntry();
                }


            }

        });

        btnSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!IsEmptyEntryOrNotNum(txtValue1, txtValue2)) {
                    txtResult.setText("");
                    double subtraction = DoSubtraction(Double.parseDouble(txtValue1.getText().toString()), Double.parseDouble(txtValue2.getText().toString()));
                    txtResult.setText(String.valueOf(subtraction));
                    ShowResult("Subtração", subtraction);
                }
                else
                {
                    ShowEmptyEntry();
                }
            }

        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!IsEmptyEntryOrNotNum(txtValue1, txtValue2)) {
                    txtResult.setText("");
                    double multiplication = DoMultiplication(Double.parseDouble(txtValue1.getText().toString()), Double.parseDouble(txtValue2.getText().toString()));

                    txtResult.setText(String.valueOf(multiplication));
                    ShowResult("Multiplicação", multiplication);
                }
                else
                {
                    ShowEmptyEntry();
                }
            }

        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!IsEmptyEntryOrNotNum(txtValue1, txtValue2)) {
                    txtResult.setText("");
                    if(IdentifyDivisionByZero(txtValue2)) {

                        double division = DoDivision(Double.parseDouble(txtValue1.getText().toString()), Double.parseDouble(txtValue2.getText().toString()));

                        txtResult.setText(String.valueOf(division));

                        ShowResult("Divisão", division);

                    }
                    else{
                        ShowDivisionByZero();
                    }
                }
                else
                {
                    ShowEmptyEntry();
                }
            }

        });


    }

    private static double DoSum(double value1, double value2) {

        return (value1 + value2);
    }

    private static double DoSubtraction(double value1, double value2) {

        return (value1 - value2);

    }

    private static double DoMultiplication(double value1, double value2) {
        return (value1 * value2);
    }

    private static double DoDivision(double value1, double value2) {
        double division = (value1 / value2);

        if (division > 0)
            return division;
        else return 0.0;
    }

    private static boolean IdentifyDivisionByZero(EditText value2){
        boolean divisionByZero = true;

        if (Double.parseDouble(value2.getText().toString()) == 0)
            divisionByZero = false;

        return divisionByZero;
    }

    private static boolean IsEmptyEntryOrNotNum(EditText value1, EditText value2){
        boolean IsEmptyEntryOrNotNum = true;
        String num1 = value1.getText().toString();
        String num2 = value2.getText().toString();

        if ((num1.matches("[+-]?\\d*(\\.\\d+)?")) & (num2.matches("[+-]?\\d*(\\.\\d+)?") & !num1.isEmpty() & !num2.isEmpty()))
            IsEmptyEntryOrNotNum = false;

        return IsEmptyEntryOrNotNum;

    }

    public void ShowEmptyEntry()
    {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Entrada inválida");
        dialogo.setMessage("Os campos valor 1 e valor 2 não podem ser vazios e não podem conter letras");
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

    public void ShowResult(String operation, double result )
    {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Resultado "+ operation);
        dialogo.setMessage("O Resultado da "+ operation + " é " + result);

        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

    public void ShowDivisionByZero()
    {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Erro Esperado");
        dialogo.setMessage("Não existe divisão por zero!!!");
        txtResult.setText("Não existe divisão por zero");
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}
