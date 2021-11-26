package com.car105.yendondeesta;

import com.google.firebase.database.FirebaseDatabase;

public class MyfireBaseApp extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
