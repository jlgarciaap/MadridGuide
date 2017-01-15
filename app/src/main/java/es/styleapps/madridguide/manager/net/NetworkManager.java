package es.styleapps.madridguide.manager.net;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.util.List;

import es.styleapps.madridguide.R;

/**
 * Created by jlgarciaap on 14/1/17.
 */

public class NetworkManager {

    //Interfaz que usamos para devolver los datos y usarlos fuera del networkmanager
    public interface GetShopListener {
        public void getShopEntitiesSuccess(List<ShopEntity> result);
        public void getShopEntitiesDidFail();

    }

    public NetworkManager( Context context) {
        this.context = new WeakReference<>(context);
    }

    WeakReference<Context> context;

    //Le pasamos el listener para devolver los datos
    public void getShopsFromServer(final GetShopListener listener){

        RequestQueue queue = Volley.newRequestQueue(context.get());
        String url = context.get().getString(R.string.shopsUrl);

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
    private  List<ShopEntity> parseResponse(String response) {
        List<ShopEntity> result = null;
        try {
            //Pasamos un reader a GsonBuilder y lo aplique a la entidad que hemos creado
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();
            ShopsResponse shopsResponse = gson.fromJson(reader, ShopsResponse.class);
            result = shopsResponse.result;

        }catch(Exception e){

            e.printStackTrace();
        };

        return result;
    }

}
