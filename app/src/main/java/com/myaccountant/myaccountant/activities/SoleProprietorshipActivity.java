package com.myaccountant.myaccountant.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.SoleProprietorship;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;

import net.redwarp.library.database.DatabaseHelper;

/**
 * Created by user on 11/11/2015.
 */
public class SoleProprietorshipActivity extends AppCompatActivity{

    MaterialEditText name,amount;
    Button saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sole_proprietorship_layout);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Sole proprietorship");

        SystemBarTintManager tintManager=new SystemBarTintManager(this);
        //status bar tint enable and styling
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(Color.parseColor("#0390dc"));
        //navigation bar tint enable and styling
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setNavigationBarTintColor(Color.parseColor("#00000000"));

        name=(MaterialEditText)findViewById(R.id.sole_proprietorship_name);
        amount=(MaterialEditText)findViewById(R.id.sole_proprietorship_amount);
        saveButton=(Button)findViewById(R.id.save_btn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper = new DatabaseHelper(SoleProprietorshipActivity.this);
                SoleProprietorship data = new SoleProprietorship();
                data.setAmount(Double.parseDouble(amount.getText().toString()));
                data.setName(name.getText().toString());
                helper.save(data);
                Toast.makeText(SoleProprietorshipActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
