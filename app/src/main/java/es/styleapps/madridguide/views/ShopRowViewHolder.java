package es.styleapps.madridguide.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

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
    ImageView backgroundImageView;

    private WeakReference<Context> context;

    public ShopRowViewHolder(View itemView) {
        super(itemView);


        //Inicializamos butterKnife que lo que hace es añadir los Binds de arriba como si fueran findView. Injectamos
//        ButterKnife.bind(this,itemView);

        context = new WeakReference<Context>(itemView.getContext());
        nameTextView = (TextView) itemView.findViewById(R.id.row_shop_name);
      logoImageView = (ImageView) itemView.findViewById(R.id.row_shop_logo);
      backgroundImageView = (ImageView) itemView.findViewById(R.id.row_shop_background);



    }

    public void setShop (Shop shop){

        if(shop == null){

            return;

        }
        nameTextView.setText(shop.getName());

        Picasso.with(context.get()).load(shop.getLogoImgUrl())
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(logoImageView);

        Picasso.with(context.get()).load(shop.getImageUrl())
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(new Target() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        backgroundImageView.setBackground(new BitmapDrawable(bitmap));

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });



    }

}
