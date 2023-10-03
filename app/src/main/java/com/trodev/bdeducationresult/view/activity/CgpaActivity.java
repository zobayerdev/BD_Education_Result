package com.trodev.bdeducationresult.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.trodev.bdeducationresult.R;

import java.util.Objects;

public class CgpaActivity extends AppCompatActivity {

    private EditText first, second, third, four;
    private TextView total, avge, grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CGPA Calculator");

        first = findViewById(R.id.first_ET);
        second = findViewById(R.id.two_tET);
        third = findViewById(R.id.three_ET);
        four = findViewById(R.id.four_ET);
        total = findViewById(R.id.total_ET);
        avge = findViewById(R.id.avg_ET);
        grad = findViewById(R.id.grade_ET);

        Button calculateBTN = findViewById(R.id.btn);

        calculateBTN.setOnClickListener(view -> {

            if(first.getText().toString().equals("")){
                Toast.makeText(CgpaActivity.this, "Enter first subject number", Toast.LENGTH_SHORT).show();
            }else if(second.getText().toString().equals("")){
                Toast.makeText(CgpaActivity.this, "Enter second subject number", Toast.LENGTH_SHORT).show();
            }else if(third.getText().toString().equals("")){
                Toast.makeText(CgpaActivity.this, "Enter third subject number", Toast.LENGTH_SHORT).show();
            }else if(four.getText().toString().equals("")){
                Toast.makeText(CgpaActivity.this, "Enter fourth subject number", Toast.LENGTH_SHORT).show();
            } else{
                calculateResult();
            }


        });


    }

    private void calculateResult() {
        int m1, m2, m3, m4, tot;
        double avg;

        m1 = Integer.parseInt(first.getText().toString());
        m2 = Integer.parseInt(second.getText().toString());
        m3 = Integer.parseInt(third.getText().toString());
        m4 = Integer.parseInt(four.getText().toString());

        // total calculator
        tot = m1+m2+m3+m4;
        total.setText("Total: "+tot);

        /* average calculator */
        avg = (double) tot / 4;
        avge.setText("Average: "+avg);


        if(avg >= 80){
            grad.setText("CGPA: 4.00");
        }

        else if(avg >= 79){
            grad.setText("CGPA: 3.75");
        }

        else if(avg >= 74){
            grad.setText("CGPA: 3.50");
        }

        else if(avg >= 69){
            grad.setText("CGPA: 3.25");
        }

        else if(avg >= 64){
            grad.setText("CGPA: 3.00");
        }

        else if(avg >= 59){
            grad.setText("CGPA: 2.75");
        }

        else if(avg >= 54){
            grad.setText("CGPA: 2.50");
        }

        else if(avg >= 49){
            grad.setText("CGPA: 2.25");
        }

        else if(avg >= 44){
            grad.setText("CGPA: 2.00");
        }

        else {
            grad.setText("CGPA: Fail");
        }

    }
}