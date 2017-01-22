package es.styleapps.madridguide.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.styleapps.madridguide.R;
import es.styleapps.madridguide.interactors.CacheAllShopsInteractor;
import es.styleapps.madridguide.interactors.GetAllShopsInteractor;
import es.styleapps.madridguide.model.Shops;
import es.styleapps.madridguide.navigator.Navigator;
import es.styleapps.madridguide.util.TypeEntity;

public class MainActivity extends AppCompatActivity {

    //Usamos butterKnife
    @BindView(R.id.activity_main_shops_button)
    Button shopsButton;
    @BindView(R.id.activity_main_activities_button)
    Button activitiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos butterKnife que lo que hace es añadir los Binds de arriba como si fueran findView. Injectamos
        ButterKnife.bind(this);

        setupShopsButton();
        setupActivitiesButton();


    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork.isConnectedOrConnecting();
    }


    private void setupShopsButton() {

        View.OnClickListener listener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

               TypeEntity.setType("shop");

                getDataNowAndShowMeTheInfo();

            }
        };
        shopsButton.setOnClickListener(listener);
    }

    private void setupActivitiesButton() {
        View.OnClickListener listener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                TypeEntity.setType("activity");

                getDataNowAndShowMeTheInfo();

            }
        };
        activitiesButton.setOnClickListener(listener);
    }

    private AlertDialog network(){

       AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("UPSS NO NETWORK").setMessage(R.string.network_alert)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String PREFS_NAME = "dateSaved";
                        SharedPreferences dateSaved = getApplicationContext().getSharedPreferences(PREFS_NAME,0);
                        long actualDate = new Date().getTime();
                        long dateInPref = dateSaved.getLong(PREFS_NAME,actualDate);
                        if(dateInPref != actualDate){
                            navigateTime();
                        }
                    }
                });

        return builder.create();

    }

    private void getDataNowAndShowMeTheInfo() {

        boolean connected = isConnected();

        if (!connected) {
            network().show();
        } else {
            navigateTime();
        }
    }

    private void navigateTime(){

        new GetAllShopsInteractor().execute(getApplicationContext(),
                new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
                    @Override
                    public void response(Shops shops) {
                        new CacheAllShopsInteractor().execute(getApplicationContext(), shops,
                                new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {
                                    @Override
                                    public void response(boolean success) {
                                        Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
                                    }
                                });
                    }
                });//Si justo despues antes de este ; ponemos .var nos genera el shopsButton.setOnClickListener(listener), nos permite
        //poner una variable para poder llamar a ese listener desde otro boton si es necesario

    }
}
