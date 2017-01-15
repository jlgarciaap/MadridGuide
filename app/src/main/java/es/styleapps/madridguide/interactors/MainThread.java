package es.styleapps.madridguide.interactors;

import android.os.Handler;
import android.os.Looper;

public class MainThread {

    public static void run (final Runnable runnable){
        Handler handler = new Handler(Looper.getMainLooper());//Lo pasamos al looper main

        handler.obtainMessage();
        handler.post(new Runnable() {
            @Override
            public void run() {
               runnable.run();
            }
        });

    }


}
