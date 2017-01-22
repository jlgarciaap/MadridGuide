package es.styleapps.madridguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import es.styleapps.madridguide.R;
import es.styleapps.madridguide.model.Shop;

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


