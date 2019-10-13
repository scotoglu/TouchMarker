package com.example.touchmarker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

public class DialogWindows {
    Context context;
    AlertDialog.Builder builder;

    public DialogWindows(Activity activity) {

     this.context = activity;
    }
    public void gpsWarning(){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("WhichBus")
                .setMessage("GPS'sinizi açık olduğundan emin olun." +
                        "Açık değilse mevcut konumunuzu bulamazsınız")
                .show();
    }
    public void notFoundNearestStopsWarning(){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("WhichBus")
                .setMessage("1 km içinde herhangi bir durak bulunmamaktadır.")
                .show();
    }
    public void nearestStopsToLocations(){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("WhichBus")
                .setMessage("Yakın durakları görüntülemektasiniz. " +
                        "Yeşil markerlar yakın durakları, " +
                        "kırmızı marker sizi göstermektedir.")
                .show();
    }
}
