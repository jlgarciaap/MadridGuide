package es.styleapps.madridguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.styleapps.madridguide.R;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.util.Constants;

public class ShopDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Shop shop;

    @BindView(R.id.activity_shop_detail_logo_image)
    ImageView logoImage;
    @BindView(R.id.activity_shop_detail_name_text)
    TextView nameText;
    @BindView(R.id.activity_shop_detail_address_text)
    TextView addressText;
    @BindView(R.id.activity_shop_detail_description_text)
    TextView descriptionText;

    private MapFragment mapFragment;
    private GoogleMap googleMapobject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        getDetailShopFromCallingIntent(intent);
        updateUI();
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void updateUI() {
        nameText.setText(shop.getName());
        Picasso.with(this).load(shop.getLogoImgUrl()).into(logoImage);
        addressText.setText(shop.getAddress());
        descriptionText.setText(shop.getDescription());
        descriptionText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void getDetailShopFromCallingIntent(Intent intent) {
        if(intent != null) {
            shop = (Shop) intent.getSerializableExtra(Constants.INTENT_KEY_DETAIL_SHOP);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMapobject = googleMap;

        LatLng coords = new LatLng(shop.getLatitude(),shop.getLongitude());
        CameraUpdate center = CameraUpdateFactory.newLatLng(coords);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(18);

        googleMapobject.moveCamera(center);
        googleMapobject.animateCamera(zoom);

    }
}
