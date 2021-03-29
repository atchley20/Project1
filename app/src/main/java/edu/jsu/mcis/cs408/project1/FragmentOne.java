package edu.jsu.mcis.cs408.project1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class FragmentOne extends Fragment implements TabFragment {

    private final String title = "Calculator";
    public TextView outputCalc;
    public void btn0(View view){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        outputCalc = (TextView) view.findViewById(R.id.outputCalc);
        Field[] fields = R.id.class.getFields();
        for (int i = 0; i < fields.length; ++i) {
            String name = fields[i].getName();
            if (name.length() >= 3 && name.substring(0, 3).equals("btn")) {
                int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
                view.findViewById(id).setOnClickListener(this::numKeyPressed);
            }
            if (name.length() >= 3 && name.contains("Clear")) {
                int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
                view.findViewById(id).setOnClickListener(this::btnClear);
            }
            if (name.length() >= 3 && name.contains("Sqrt")) {
                int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
                view.findViewById(id).setOnClickListener(this::sqrtPressed);
            }
            if (name.length() >= 3 && name.contains("Decimal")) {
                int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
                view.findViewById(id).setOnClickListener(this::decPressed);
            }
            if (name.length() >= 3 && name.contains("Equals")) {
                int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
                view.findViewById(id).setOnClickListener(this::equalPressed);
            }
            if (name.length() >= 3 && name.contains("Plus")) {
                int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
                view.findViewById(id).setOnClickListener(this::flipSign);
            }
            if (name.length() >= 3 && name.contains("Percent")) {
                int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
                view.findViewById(id).setOnClickListener(this::pctPressed);
            }
            //if the id includes sqrt , equals
        }
    }

    public void onClick(View v) {
        String button = ((Button) v).getText().toString();
    }
    public int count = 0;
    public int decCount = 0;
    public String expression = "";
    public void numKeyPressed(View v){
        System.out.println("Test");
        String buttonText = ((Button) v).getText().toString();
        System.out.println("Test 2");

        if(count == 0){
            outputCalc.setText(buttonText);
            expression = buttonText;
            count += 1 ;
        }
        else{
            outputCalc.append(buttonText);
            expression += buttonText;
        }
    }

    public void btnClear(View v){
        outputCalc.setText("0");
        expression = "";
        count = 0;
        decCount = 0;
    }

    public void decPressed(View v){

        if(decCount < 1){
            outputCalc.append(".");
            expression += ".";
            decCount += 1;
        }
    }
    public void equalPressed(View v){
        String operator = "";
        String[] splitExpression = new String[3];

        String result = "";

        if(expression.contains("+")){
            splitExpression = expression.split("\\+");

            BigDecimal left = new BigDecimal(splitExpression[0]);
            BigDecimal right = new BigDecimal(splitExpression[1]);

            result = left.add(right).toString();
            expression = result;
        }

        else if(expression.contains("\u2212")){
            splitExpression = expression.split("\u2212");

            BigDecimal left = new BigDecimal(splitExpression[0]);
            BigDecimal right = new BigDecimal(splitExpression[1]);
            result = left.subtract(right).toString();
            expression = result;
        }

        else if(expression.contains("\u00d7")){
            splitExpression = expression.split("\u00d7");

            BigDecimal left = new BigDecimal(splitExpression[0]);
            BigDecimal right = new BigDecimal(splitExpression[1]);
            result = left.multiply(right).toString();
            expression = result;
        }

        else{
            splitExpression = expression.split("\u00f7");

            BigDecimal left = new BigDecimal(splitExpression[0]);
            BigDecimal right = new BigDecimal(splitExpression[1]);
            result = left.divide(right).toString();
            expression = result;
        }



        outputCalc.setText(result);

    }


    public void operatorBtnPressed(View v){
        String buttonText = ((Button) v).getText().toString();
        expression += buttonText;
        outputCalc.setText("");
    }

    public void flipSign(View v){
        String originalNum = outputCalc.getText().toString();
        int numToCon = Integer.parseInt(outputCalc.getText().toString());

        if(numToCon > 0){
            numToCon = -numToCon;
            expression.replace(originalNum, Integer.toString(numToCon));
            outputCalc.setText(Integer.toString(numToCon));
        }
        else{
            expression.replace(originalNum, Integer.toString(Math.abs(numToCon)));
            outputCalc.setText(Integer.toString(Math.abs(numToCon)));
        }
    }

    public void sqrtPressed(View v){
        double numToSquare = Double.parseDouble(outputCalc.getText().toString());

        outputCalc.setText(Double.toString(Math.sqrt(numToSquare)));
    }


    public void pctPressed(View v){

        BigDecimal x = new BigDecimal(outputCalc.getText().toString());

        x = x.divide(new BigDecimal(100));

        outputCalc.setText(x.toString());

    }
    public String getTabTitle() { return title; }

}