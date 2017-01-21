package es.styleapps.madridguide.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.List;

import es.styleapps.madridguide.R;
import es.styleapps.madridguide.adapter.calloutAdapter;
import es.styleapps.madridguide.fragments.ShopsFragment;
import es.styleapps.madridguide.interactors.GetAllShopsFromLocalCacheInteractor;
import es.styleapps.madridguide.interactors.MainThread;
import es.styleapps.madridguide.manager.db.ShopDAO;
import es.styleapps.madridguide.manager.net.NetworkManager;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;
import es.styleapps.madridguide.navigator.Navigator;
import es.styleapps.madridguide.views.OnElementClick;

public class ShopsActivity extends AppCompatActivity implements OnMapReadyCallback {


    private ShopsFragment shopsFragment;
    private MapFragment mapFragment;
    private GoogleMap googleMapobject;
    private List<Shop> shopList;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);


        final String[] INITIAL_PERMS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions(this, INITIAL_PERMS, 1);


        //Siempre usamos las libreria de soporte
        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);



        shopsFragment.setListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(Shop element, int position) {
                Navigator.navigateFromShopsActivityToDetailShopsActivity(element, ShopsActivity.this);

            }
        });

        GetAllShopsFromLocalCacheInteractor interactor = new GetAllShopsFromLocalCacheInteractor();

        interactor.execute(this, new GetAllShopsFromLocalCacheInteractor.OnGetAllShopsFromLocalCacheInteractorCompletion() {
            @Override
            public void completion(final Shops shops) {
                shopList = shops.allShops();
                shopsFragment.setShops(shops);

                addShopsInMap(shops);

            }
        });


        mapFragment.getMapAsync(this);


    }

    private void addShopsInMap(Shops shops) {
        shopList = shops.allShops();

        for (final Shop shop : shopList) {

            final LatLng position = new LatLng(shop.getLatitude(), shop.getLongitude());

            googleMapobject.addMarker(new MarkerOptions().
                            position(position).snippet(shop.getLogoImgUrl())).setTitle(shop.getName());

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMapobject = googleMap;

        LatLng madrid = new LatLng(40.4260397, -3.694);

        CameraUpdate center = CameraUpdateFactory.newLatLng(madrid);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);

        googleMapobject.moveCamera(center);
        googleMapobject.animateCamera(zoom);

        googleMapobject.setInfoWindowAdapter(new calloutAdapter(getApplicationContext()));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
            googleMapobject.setMyLocationEnabled(true);
            googleMapobject.getUiSettings().setMyLocationButtonEnabled(true);


        googleMapobject.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                ShopDAO dao = new ShopDAO(getApplicationContext());
                  Shop shop =  dao.query(marker.getTitle());

                Navigator.navigateFromShopsActivityToDetailShopsActivity(shop,ShopsActivity.this);
            }
        });

    }


}