package com.myaccountant.myaccountant.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.Spinner;
import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.data.Company;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;

import net.redwarp.library.database.DatabaseHelper;


import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 11/11/2015.
 */
public class CompanyActivity extends AppCompatActivity{

    NiceSpinner category;
    MaterialEditText name,amount,issue_price,current_price;
    Button saveButton;

    LinkedList<String> dataSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_layout);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Company");
        //status bar and navigation bar styling
        SystemBarTintManager tintManager=new SystemBarTintManager(this);
        //status bar tint enable and styling
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(Color.parseColor("#0390dc"));
        //navigation bar tint enable and styling
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setNavigationBarTintColor(Color.parseColor("#00000000"));

        dataSet=new LinkedList<>(Arrays.asList("Ordinary shares","Preference shares","Other"));

        category=(NiceSpinner)findViewById(R.id.company_category_spinner);
        name=(MaterialEditText)findViewById(R.id.company_name);
        amount=(MaterialEditText)findViewById(R.id.company_amount);
        issue_price=(MaterialEditText)findViewById(R.id.company_issue_price);
        current_price=(MaterialEditText)findViewById(R.id.company_current_price);
        saveButton=(Button)findViewById(R.id.save_btn);

        category.attachDataSource(dataSet);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper = new DatabaseHelper(CompanyActivity.this);
                Company data = new Company();
                data.setName(name.getText().toString());
                data.setAmount(Double.parseDouble(amount.getText().toString()));
                data.setCategory(category.getText().toString());
                data.setCurrentPrice(Double.parseDouble(current_price.getText().toString()));
                data.setIssuePrice(Double.parseDouble(issue_price.getText().toString()));
                helper.save(data);
                Toast.makeText(CompanyActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
