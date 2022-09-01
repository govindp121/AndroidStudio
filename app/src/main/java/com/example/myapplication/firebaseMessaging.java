package com.example.myapplication;
import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.netcore.android.smartechpush.SmartPush;

public class firebaseMessaging extends FirebaseMessagingService {
    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        
    }
}
