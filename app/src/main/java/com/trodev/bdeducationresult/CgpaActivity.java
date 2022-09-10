package com.trodev.bdeducationresult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CgpaActivity extends AppCompatActivity {

    private EditText first, second, third, four, total, avge, grad;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);

        // Back activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CGPA Calculator");

        first = findViewById(R.id.first_ET);
        second = findViewById(R.id.two_tET);
        third = findViewById(R.id.three_ET);
        four = findViewById(R.id.four_ET);
        total = findViewById(R.id.total_ET);
        avge = findViewById(R.id.avg_ET);
        grad = findViewById(R.id.grade_ET);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marktot();
            }
        });


    }

    private void marktot() {
        int m1, m2, m3, m4, tot;
        double avg;
        String grade;

        m1 = Integer.parseInt(first.getText().toString());
        m2 = Integer.parseInt(second.getText().toString());
        m3 = Integer.parseInt(third.getText().toString());
        m4 = Integer.parseInt(four.getText().toString());

        // total calculator
        tot = m1+m2+m3+m4;
        total.setText(String.valueOf(tot));

        // average calculator
        avg = tot/4;
        avge.setText(String.valueOf(avg));


        if(avg >= 80){
            grad.setText("4.00");
        }

        else if(avg >= 79){
            grad.setText("3.75");
        }

        else if(avg >= 74){
            grad.setText("3.50");
        }

        else if(avg >= 69){
            grad.setText("3.25");
        }

        else if(avg >= 64){
            grad.setText("3.00");
        }

        else if(avg >= 59){
            grad.setText("2.75");
        }

        else if(avg >= 54){
            grad.setText("2.50");
        }

        else if(avg >= 49){
            grad.setText("2.25");
        }

        else if(avg >= 44){
            grad.setText("2.00");
        }

        else {
            grad.setText("Fail");
        }

    }
}