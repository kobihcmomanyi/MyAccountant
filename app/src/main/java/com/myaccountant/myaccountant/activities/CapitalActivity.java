package com.myaccountant.myaccountant.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.myaccountant.myaccountant.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class CapitalActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Capital");
        //status bar and navigation bar styling
        SystemBarTintManager tintManager=new SystemBarTintManager(this);
        //status bar tint enable and styling
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(Color.parseColor("#0390dc"));
        //navigation bar tint enable and styling
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setNavigationBarTintColor(Color.parseColor("#00000000"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_capital, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sole) {
            intent =new Intent(CapitalActivity.this, SoleProprietorshipActivity.class);
            startActivity(intent);
        }else if(id == R.id.action_partnership){
            intent =new Intent(CapitalActivity.this, PartnershipActivity.class);
            startActivity(intent);
        }else if(id == R.id.action_company){
            intent =new Intent(CapitalActivity.this, CompanyActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
