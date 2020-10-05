package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.nfc.FormatException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double result;
    boolean flag;
    String compute;
    TextView expression, recent;
    Button all_clear, clear, btn_equal;
    Button[] numButton, opButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = 0;
        flag = false;
        compute = "";
        numButton = new Button[11];
        opButton = new Button[4];

        expression = (TextView)findViewById(R.id._expression);
        recent = (TextView)findViewById(R.id._recent);
        all_clear = (Button)findViewById(R.id.all_clear);
        clear = (Button)findViewById(R.id.clear);
        numButton[0] = (Button)findViewById(R.id._0);
        numButton[1] = (Button)findViewById(R.id._1);
        numButton[2] = (Button)findViewById(R.id._2);
        numButton[3] = (Button)findViewById(R.id._3);
        numButton[4] = (Button)findViewById(R.id._4);
        numButton[5] = (Button)findViewById(R.id._5);
        numButton[6] = (Button)findViewById(R.id._6);
        numButton[7] = (Button)findViewById(R.id._7);
        numButton[8] = (Button)findViewById(R.id._8);
        numButton[9] = (Button)findViewById(R.id._9);
        numButton[10] = (Button)findViewById(R.id._decimal);
        btn_equal = (Button)findViewById(R.id.btn_equal);
        opButton[0] = (Button)findViewById(R.id.btn_add);
        opButton[1] = (Button)findViewById(R.id.btn_sub);
        opButton[2] = (Button)findViewById(R.id.btn_mul);
        opButton[3] = (Button)findViewById(R.id.btn_div);

        View.OnClickListener all_clearFunction = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.setText("");
                recent.setText("");
                result = 0;
            }
        };

        View.OnClickListener clearFunction = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.setText("");
            }
        };

        View.OnClickListener numButtonFunction = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = expression.getText().toString();

                if (flag || exp.contains("Error") || exp.equals("0"))
                {
                    expression.setText("");
                    recent.setText("");
                    exp = "";
                }

                flag = false;
                Button btn = (Button)v;
                String num = btn.getText().toString();

                if (num.equals("."))
                {
                    if(!exp.contains("."))
                    {
                        expression.setText(exp + num);
                    }
                }
                else
                {
                    expression.setText(exp + num);
                }
            }
        };

        View.OnClickListener operatorFunction = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;

                try
                {
                    if (result != 0)
                        btn_equal.performClick();
                    else
                        result = Double.parseDouble(expression.getText().toString());

                    compute = b.getText().toString();
                    recent.setText(result + " " + compute);
                    flag = true;
                }
                catch (NumberFormatException e)
                {
                    expression.setText("Syntax Error");
                }
            }
        };

        View.OnClickListener equalFunction = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    switch(compute)
                    {
                        case "+":
                            expression.setText(Double.toString(result + Double.parseDouble(expression.getText().toString())));
                            break;

                        case "-":
                            expression.setText(Double.toString(result - Double.parseDouble(expression.getText().toString())));
                            break;

                        case "÷":
                            expression.setText(Double.toString(result / Double.parseDouble(expression.getText().toString())));
                            break;

                        case "x":
                            expression.setText(Double.toString(result * Double.parseDouble(expression.getText().toString())));
                            break;

                        default:
                            break;
                    }

                    if (expression.getText().toString().equals("∞"))
                    {
                        result = 0;
                        expression.setText("Math Error");
                    }
                    else
                    {
                        result = Double.parseDouble(expression.getText().toString());
                    }
                    recent.setText("");
                }
                catch (ArithmeticException e)
                {
                    expression.setText("Math Error");
                }
                catch(Exception e)
                {
                    expression.setText("Syntax Error");
                }
            }
        };

        all_clear.setOnClickListener(all_clearFunction);
        clear.setOnClickListener(clearFunction);
        btn_equal.setOnClickListener(equalFunction);

        for (int i = 0; i <= 10; i++)
        {
            numButton[i].setOnClickListener(numButtonFunction);
        }

        for (int i = 0; i < 4; i++)
        {
            opButton[i].setOnClickListener(operatorFunction);
        }
    }
}