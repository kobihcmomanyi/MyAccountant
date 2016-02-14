package com.myaccountant.myaccountant.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myaccountant.myaccountant.R;
import com.myaccountant.myaccountant.adapters.CurrentAssetsRowLayoutOneAdapter;
import com.myaccountant.myaccountant.data.CurrentAssets;
import com.rey.material.app.Dialog;
import com.rey.material.widget.SnackBar;

import net.redwarp.library.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/10/2015.
 */
public class CurrentAssetsListFragment extends Fragment {
    SnackBar snackBar;
    Dialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.current_assets_list_layout,container,false);
        ListView listView=(ListView)rootView.findViewById(R.id.current_assets_list);
        RelativeLayout parent=(RelativeLayout)rootView.findViewById(R.id.current_assets_list_parent);
        snackBar=SnackBar.make(getActivity());

        final DatabaseHelper helper=new DatabaseHelper(getActivity());
        final List<CurrentAssets> items=helper.getAll(CurrentAssets.class);
        listView.setAdapter(new CurrentAssetsRowLayoutOneAdapter(getActivity(),items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final CurrentAssets currentAssets=items.get(position);

                dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.row_layout_one_dialog);
                dialog.layoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.setTitle("CURRENT ASSETS");
                dialog.positiveAction("DELETE");
                dialog.negativeAction("CANCEL");
                dialog.neutralAction("EDIT");
                dialog.positiveActionClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        helper.delete(currentAssets);
                        dialog.dismissImmediately();
                    }
                });
                dialog.neutralActionClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismissImmediately();
                    }
                });
                dialog.negativeActionClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismissImmediately();
                    }
                });

                TextView name,description,amount,date,time;
                name=(TextView)dialog.findViewById(R.id.row_one_dialog_name_text_view);
                description=(TextView)dialog.findViewById(R.id.row_one_dialog_description_text_view);
                amount=(TextView)dialog.findViewById(R.id.row_one_dialog_amount_text_view);
                date=(TextView)dialog.findViewById(R.id.row_one_dialog_date);
                time=(TextView)dialog.findViewById(R.id.row_one_dialog_time);

                name.setText(currentAssets.getName());
                description.setText(currentAssets.getDescription());
                amount.setText("KSH."+currentAssets.getWorth());
                date.setText(currentAssets.getDate());
                time.setText(currentAssets.getTime());

                dialog.show();
            }
        });

        snackBar.text("TOTAL KSH." + totalCurrentAssets(items));
        snackBar.textColor(Color.WHITE);
        snackBar.actionText("DISMISS");
        snackBar.actionTextColor(getResources().getColor(R.color.blue));
        snackBar.actionClickListener(new SnackBar.OnActionClickListener() {
            @Override
            public void onActionClick(SnackBar snackBar, int i) {
                snackBar.dismiss();
            }
        });
        //snackBar.duration(10000);
        snackBar.show(parent);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public double totalCurrentAssets(List<CurrentAssets> items){
        double total=0;
        ArrayList<CurrentAssets> listItems=(ArrayList)items;
        for(CurrentAssets list:listItems){
            total+=list.getWorth();
        }
        return total;
    }
}
