package com.example.myapplication;


import static android.content.ContentValues.TAG;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.netcore.android.SMTBundleKeys;
import com.netcore.android.Smartech;
import com.netcore.*;
import com.netcore.android.logger.SMTDebugLevel;
import com.netcore.android.smartechpush.SmartPush;
import com.netcore.android.smartechpush.notification.SMTNotificationOptions;

import java.lang.ref.WeakReference;


public class Demo_Netcore extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Smartech smartech = Smartech.getInstance(new WeakReference<>(this.getApplicationContext()));
        smartech.initializeSdk(this);
        smartech.setDebugLevel(SMTDebugLevel.Level.VERBOSE);
        smartech.trackAppInstallUpdateBySmartech();
       // smartech.getSmartechAttributionURL("this", getAttributionTag());

        try {

        } catch (Exception e) {
            Log.e(TAG, "Fetching FCM token failed.");
        }

    }
    public class DeeplinkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle bundleExtra = intent.getExtras();
            if (bundleExtra != null) {

                if (bundleExtra.containsKey(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_DEEPLINK)) {
                    String deepLinkvalue = bundleExtra.getString(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_DEEPLINK);
                } else {
                    Log.v("Activity", "does not have deeplink path.");
                }

                if (bundleExtra.containsKey(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_CUSTOM_PAYLOAD)) {
                    String customPayloadvalue = bundleExtra.getString(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_CUSTOM_PAYLOAD);
                } else {
                    Log.v("Activity", "does not have custom payload.");
                }

            }

        }
    }

}


