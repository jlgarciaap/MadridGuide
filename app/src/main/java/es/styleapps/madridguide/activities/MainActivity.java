package es.styleapps.madridguide.activities;

import android.Manifest;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.styleapps.madridguide.R;
import es.styleapps.madridguide.navigator.Navigator;

public class MainActivity extends AppCompatActivity {

    //Usamos butterKnife
    @BindView(R.id.activity_main_shops_button)
    Button shopsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos butterKnife que lo que hace es añadir los Binds de arriba como si fueran findView. Injectamos
        ButterKnife.bind(this);

        setupShopsButton();


    }

    private void setupShopsButton() {
        View.OnClickListener listener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
            }
        }; //Si justo despues antes de este ; ponemos .var nos genera el shopsButton.setOnClickListener(listener), nos permite
        //poner una variable para poder llamar a ese listener desde otro boton si es necesario
        shopsButton.setOnClickListener(listener);
    }
}
