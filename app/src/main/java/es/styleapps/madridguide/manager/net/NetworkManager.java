package es.styleapps.madridguide.manager.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import es.styleapps.madridguide.R;
import es.styleapps.madridguide.util.TypeEntity;

/**
 * Created by jlgarciaap on 14/1/17.
 */

public class NetworkManager {

    //Interfaz que usamos para devolver los datos y usarlos fuera del networkmanager
    public interface GetShopListener {
        public void getShopEntitiesSuccess(List<ShopEntity> result);

        public void getShopEntitiesDidFail();

    }

    public interface GetLogoShopListener {
        public void getLogoShopSuccess(Bitmap result);

        public void getLogoShopDidFail();


    }

    public NetworkManager(Context context) {
        this.context = new WeakReference<>(context);
    }

    static WeakReference<Context> context;

    //Le pasamos el listener para devolver los datos
    public void getShopsFromServer(final GetShopListener listener) {

        RequestQueue queue = Volley.newRequestQueue(context.get());
        String url = "";
        if (TypeEntity.getType() == "shop") {

            url = context.get().getString(R.string.shops_url);

        } else if (TypeEntity.getType() == "activity") {

            url = context.get().getString(R.string.shopsUrl);

        }


        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { //Si va bien sale esto

                Log.d("JSON", response);
                List<ShopEntity> shopResponse = parseResponse(response);
                //Usamos esto para devolver, ya que se produce en otro hilo o tarde mucho y si devolvemos
                //en el mismo metodo devolveria siempre null antes de terminar
                if (listener != null) {
                    listener.getShopEntitiesSuccess(shopResponse);
                }
            }
        }, new Response.ErrorListener() {//Si va mal esto
            @Override
            public void onErrorResponse(VolleyError error) {
                //La respuesta con el error
                if (listener != null) {
                    listener.getShopEntitiesDidFail();
                }
            }
        });

        queue.add(request);


    }

    //Devolvemos una lista con lo descargado parseado
    private List<ShopEntity> parseResponse(String response) {
        List<ShopEntity> result = null;
        try {
            //Pasamos un reader a GsonBuilder y lo aplique a la entidad que hemos creado
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();
            ShopsResponse shopsResponse = gson.fromJson(reader, ShopsResponse.class);
            result = shopsResponse.result;

        } catch (Exception e) {

            e.printStackTrace();
        }
        ;

        return result;
    }

    public void getBitmapFromShop(final String urlLogo, final GetLogoShopListener listener) {

        RequestQueue queue = Volley.newRequestQueue(context.get());
        String url = urlLogo;

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                if (listener != null) {

                    listener.getLogoShopSuccess(response);
                }

            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listener != null) {
                    listener.getLogoShopDidFail();
                }
            }
        });

        queue.add(imageRequest);

    }


}
