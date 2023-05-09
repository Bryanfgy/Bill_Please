package com.example.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    private boolean buttonpress;
     EditText amountCash;
     EditText noOfPeople;
     ToggleButton serviceChg;
     ToggleButton gst;
     EditText discount;
     RadioGroup paymentMthd;
     Button split;
     Button reset;
     TextView totalpay;
     TextView splitpay;
    private double test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountCash = findViewById(R.id.moneyAmt);
        noOfPeople = findViewById(R.id.numPayers);
        serviceChg = findViewById(R.id.ServiceChg);
        gst = findViewById(R.id.Gst);
        discount = findViewById(R.id.discount);
        paymentMthd = findViewById(R.id.paymentMethod);
        split = findViewById(R.id.Split);
        reset = findViewById(R.id.Reset);
        totalpay = findViewById(R.id.totalAmt);
        splitpay = findViewById(R.id.splitPay);

        paymentMthd.check(R.id.Cash);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your code for the action


                float totalamt = 0;
                double indiviualamt = 0;
                double totalCash = 0;
                double disCash =0;
                double serviceChargeDisc =0;
                double gstDiscount =0;
                String paymtd ="";
                String value = amountCash.getText().toString();
                float cashAmt = Integer.parseInt(value);
                String pax = noOfPeople.getText().toString();
                float people = Integer.parseInt(pax);


                if(serviceChg.isChecked()){
                    serviceChargeDisc = 0.1;
                };
                if(gst.isChecked()){
                    gstDiscount = 0.07;
                }

                String dis = discount.getText().toString();
                float Disc = Integer.parseInt(dis);



                int radioId = paymentMthd.getCheckedRadioButtonId();
                if(radioId == R.id.Cash){
                    paymtd =" in cash";
                }else{
                    paymtd =" via PayNow to 91234567";
                }

                disCash = cashAmt-((cashAmt/100)*Disc);
                totalCash = disCash*(1+serviceChargeDisc + gstDiscount);
                indiviualamt = (totalCash/people);
                String round = String.format("%.2f",indiviualamt);
                String cashAmtRounded = String.format("%.2f",totalCash);

                totalpay.setText("Total Bill : $"+cashAmtRounded);

                splitpay.setText("Each pays : $"+round+ paymtd);


            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountCash.setText("0");
                noOfPeople.setText("1");
                discount.setText("0");
                serviceChg.setChecked(false);
                gst.setChecked(false);
                paymentMthd.check(R.id.Cash);
                totalpay.setText("Total Bill: $");
                splitpay.setText("Each pays : $");
            }
        });




    }
}