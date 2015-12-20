package com.myaccountant.myaccountant.helpers;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by user on 12/1/2015.
 */
public class MyAccountantApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "FHb3jTxKjmhRrNypHc2WLoJFxEvNBtAT5e2giabr", "AcFM7Tbjc8MO51DhduHglicIFf2v4GdaoyVVGWDa");

    }

    public static void updateParseInstallation(ParseUser user){
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("userId", user.getObjectId());
        installation.saveInBackground();
    }
}
