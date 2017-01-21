package es.styleapps.madridguide.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import es.styleapps.madridguide.R;
import es.styleapps.madridguide.interactors.MainThread;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.views.calloutViewHolder;

public class calloutAdapter implements GoogleMap.InfoWindowAdapter {

    private LayoutInflater layout;
    private Context context;
    private Shop shop;
    private View view;
    public calloutAdapter(Context context){

        this.layout = LayoutInflater.from(context);
        this.context = context;
        view = layout.inflate(R.layout.custom_callout, null);

    }




    @Override
    public View getInfoWindow(Marker marker) {

//        if (marker.isInfoWindowShown()) {
//
//            marker.hideInfoWindow();
//            marker.showInfoWindow();
//        }
        return null;
    }

    @Override
    public View getInfoContents(final Marker marker) {

        TextView nameShop = (TextView) view.findViewById(R.id.custom_callout_text);
        ImageView imageShop = (ImageView) view.findViewById(R.id.custom_callout_logo);

        nameShop.setText(marker.getTitle());

        Picasso.with(context).load(marker.getSnippet()).into(imageShop);

        return view;
    }

}


