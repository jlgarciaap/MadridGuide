package es.styleapps.madridguide.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import es.styleapps.madridguide.R;
import es.styleapps.madridguide.model.Shop;

/**
 * Created by jlgarciaap on 20/1/17.
 */

public class calloutViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    ImageView logoImageView;
    private WeakReference<Context> context;


    public calloutViewHolder(View itemView) {
        super(itemView);


        context = new WeakReference<Context>(itemView.getContext());
        nameTextView = (TextView) itemView.findViewById(R.id.custom_callout_text);
        logoImageView = (ImageView) itemView.findViewById(R.id.custom_callout_logo);

    }

    public void setShop (Shop shop){

        if(shop == null){

            return;

        }
        nameTextView.setText(shop.getName());

        Picasso.with(context.get()).load(shop.getLogoImgUrl()).fetch();
        Picasso.with(context.get()).load(shop.getLogoImgUrl())
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(logoImageView);

    }



}
