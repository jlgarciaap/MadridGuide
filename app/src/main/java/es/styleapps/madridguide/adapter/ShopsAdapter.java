package es.styleapps.madridguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import es.styleapps.madridguide.R;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;
import es.styleapps.madridguide.views.OnElementClick;
import es.styleapps.madridguide.views.ShopRowViewHolder;

/**
 * Created by jlgarciaap on 12/1/17.
 */

public class ShopsAdapter extends RecyclerView.Adapter<ShopRowViewHolder> {
    //Pasa los datos a las filas.
    //Tenemos que decirla dentro del <> cual es el holder que tiene que crear

    private final LayoutInflater layoutInflater;
    private final Shops shops;

    private OnElementClick<Shop> listener;


    //Necesitamos un contexto porque carga cosas de disco(en este caso los binarios de la vista)
    public ShopsAdapter(Shops shops, Context context) {

        this.shops = shops;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ShopRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Aqui se crea la celda

        View view = layoutInflater.inflate(R.layout.row_shop, parent,false);//Lo ponemos colgando del padre
        //Es decir, lo que queremos inflar, donde, si adjuntamos esto al root(false porque tenemos que reciclar)

        return (new ShopRowViewHolder(view));
    }

    @Override
    public void onBindViewHolder(ShopRowViewHolder holder, final int position) {
        //Se dispara al pintar la celda cuando se esta reciclando, rellenamos la celda

        final Shop shop = shops.get(position);


        holder.setShop(shop);

        //Esto es como el contentView de las tablas en IOS
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ShopsAdapter.this.listener != null){
                    listener.clickedOn(shop, position);

                }

            }
        });

    }


    public void setOnElementClikListener(OnElementClick<Shop> listener){

        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return (int)shops.size();
    }

}
