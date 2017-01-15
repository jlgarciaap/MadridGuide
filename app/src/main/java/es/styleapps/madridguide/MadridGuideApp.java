package es.styleapps.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import es.styleapps.madridguide.interactors.CacheAllShopsInteractor;
import es.styleapps.madridguide.interactors.GetAllShopsInteractor;
import es.styleapps.madridguide.manager.db.ShopDAO;
import es.styleapps.madridguide.manager.net.NetworkManager;
import es.styleapps.madridguide.manager.net.ShopEntity;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;
import es.styleapps.madridguide.model.mappers.ShopEntityShopMapper;

/**
 * Created by jlgarciaap on 9/1/17.
 */

public class MadridGuideApp extends Application {

    //Esto se arrancaria con la aplicacion no con los activities
    //usamo esto del contexto para poder acceder al contexto desde cualquier aplicacion

    //La creamos como weak para el mismo concepto que en IOS, punteros a objetos light para poder usarlo desde varios sitios

    private static WeakReference<Context> appContext;


    @Override
    public void onCreate() {
        super.onCreate();

        appContext = new WeakReference<Context>(getApplicationContext());

       // insertTestData();
       Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

        new GetAllShopsInteractor().execute(getApplicationContext(),
                new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
            @Override
            public void response(Shops shops) {
                new CacheAllShopsInteractor().execute(getApplicationContext(), shops,
                        new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {
                    @Override
                    public void response(boolean success) {
                        //TODO lo suyo es bajar todo lo de la aplicacion o segun necesidad?Las imagenes?
                    }
                });
            }
        });


    }

    private void insertTestData() {

        ShopDAO dao = new ShopDAO(getApplicationContext());

        for (int i = 0; i < 100; i++) {
            Shop shop = new Shop(i,"La tienda " +i);
            shop.setLogoImgUrl("https://s-media-cache-ak0.pinimg.com/564x/73/db/97/73db97c0c4a9c9b009d69f21ea48ecdc.jpg");
            dao.insert(shop);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContext() {
        return appContext.get();
    }
}
