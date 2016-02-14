package com.myaccountant.myaccountant.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.astuetz.PagerSlidingTabStrip;
import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.adapters.AssetsViewPagerAdapter;
import com.myaccountant.myaccountant.fragments.CurrentAssetsFragment;
import com.myaccountant.myaccountant.fragments.NonCurrentAssetsFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.rey.material.app.Dialog;

public class AssetsActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerSlidingTabStrip pagerSlidingTabStrip;
    AssetsViewPagerAdapter assetsViewPagerAdapter;
    String selectedTab="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);

        selectedTab="currentAssets";

        getSupportActionBar().setTitle("Assets");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        //status bar and navigation bar styling
        SystemBarTintManager tintManager=new SystemBarTintManager(this);
        //status bar tint enable and styling
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(Color.parseColor("#0390dc"));
        //navigation bar tint enable and styling
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setNavigationBarTintColor(Color.parseColor("#00000000"));

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        pagerSlidingTabStrip=(PagerSlidingTabStrip)findViewById(R.id.pager_title_strip);
        assetsViewPagerAdapter=new AssetsViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(assetsViewPagerAdapter);
        pagerSlidingTabStrip.setViewPager(viewPager);

        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==1){
                    selectedTab="nonCurrentAssets";
                }else if(position==0){
                    selectedTab="currentAssets";
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            if(selectedTab.equalsIgnoreCase("currentAssets")){
                Intent intent=new Intent(getApplicationContext(),FrameActivity.class);
                intent.putExtra("category","currentAssets");
                startActivity(intent);

            }else{
                Intent intent=new Intent(getApplicationContext(),FrameActivity.class);
                intent.putExtra("category", "nonCurrentAssets");
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
