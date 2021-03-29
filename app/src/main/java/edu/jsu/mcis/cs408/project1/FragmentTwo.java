package edu.jsu.mcis.cs408.project1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTwo extends Fragment implements TabFragment {

    private final String title = "Tips";
    private TextView outputField;
    private TextView output;
    private Button calculate;
    private EditText total_bill;
    private EditText tip_percentage;
    private EditText number_of_people;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        outputField = (TextView) view.findViewById(R.id.output);
        calculate = (Button) view.findViewById(R.id.calculate);
        output = (TextView) view.findViewById(R.id.output);
        total_bill = (EditText) view.findViewById(R.id.total_bill);
        tip_percentage = (EditText) view.findViewById(R.id.tip_percentage);
        number_of_people = (EditText) view.findViewById(R.id.number_of_people);
        view.findViewById(R.id.calculate).setOnClickListener(this::onClick);
    }

    public void onClick(View v) {

        if (total_bill.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please do not leave any field blank or zero.", Toast.LENGTH_LONG).show();
        }
        if (tip_percentage.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please do not leave any field blank or zero.", Toast.LENGTH_LONG).show();
        }
        if (number_of_people.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please do not leave any field blank or zero.", Toast.LENGTH_LONG).show();
        } else {

            double final_amount = 0;
            double money = Double.parseDouble(total_bill.getText().toString());
            double tip_percent = Double.parseDouble(tip_percentage.getText().toString());
            int people = Integer.parseInt(number_of_people.getText().toString());
            if (money != 0 && tip_percent != 0 && people != 0) {
                if (v.getId() == calculate.getId()) {
                    tip_percent = tip_percent / 100;
                    final_amount = ((money * tip_percent) + money) / people;
                    output.setText("Amount Per Person: $" + String.format("%.2f", final_amount));
                }
            }
            else {
                Toast.makeText(getContext(), "Please do not leave any field blank or zero.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getTabTitle() { return title; }

}