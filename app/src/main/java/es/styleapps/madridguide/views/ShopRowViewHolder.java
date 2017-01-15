package es.styleapps.madridguide.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.styleapps.madridguide.R;
import es.styleapps.madridguide.model.Shop;

/**
 * Created by jlgarciaap on 12/1/17.
 */

public class ShopRowViewHolder extends RecyclerView.ViewHolder {

    //Usamos butterKnife
//    @BindView(R.id.row_shop_name)
    TextView nameTextView;

//    @BindView(R.id.row_shop_logo)
    ImageView logoImageView;

    private WeakReference<Context> context;

    public ShopRowViewHolder(View itemView) {
        super(itemView);


        //Inicializamos butterKnife que lo que hace es añadir los Binds de arriba como si fueran findView. Injectamos
//        ButterKnife.bind(this,itemView);

        context = new WeakReference<Context>(itemView.getContext());
        nameTextView = (TextView) itemView.findViewById(R.id.row_shop_name);
      logoImageView = (ImageView) itemView.findViewById(R.id.row_shop_logo);



    }

    public void setShop (Shop shop){

        if(shop == null){

            return;

        }
        nameTextView.setText(shop.getName());

        Picasso.with(context.get()).load(shop.getLogoImgUrl())
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(logoImageView);

    }

}
