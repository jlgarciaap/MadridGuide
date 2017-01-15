package es.styleapps.madridguide.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import es.styleapps.madridguide.R;
import es.styleapps.madridguide.ShopsFragment;
import es.styleapps.madridguide.interactors.GetAllShopsFromLocalCacheInteractor;
import es.styleapps.madridguide.manager.db.DBConstants;
import es.styleapps.madridguide.manager.db.ShopDAO;
import es.styleapps.madridguide.manager.db.provider.MadridGuideProvider;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;
import es.styleapps.madridguide.navigator.Navigator;
import es.styleapps.madridguide.views.OnElementClick;

public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, OnMapReadyCallback {

    private ShopsFragment shopsFragment;
    private MapFragment mapFragment;
    private GoogleMap googleMapobject;

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

        mapFragment.getMapAsync(this);

        GetAllShopsFromLocalCacheInteractor interactor = new GetAllShopsFromLocalCacheInteractor();

        interactor.execute(this, new GetAllShopsFromLocalCacheInteractor.OnGetAllShopsFromLocalCacheInteractorCompletion() {
            @Override
            public void completion(Shops shops) {
                shopsFragment.setShops(shops);
            }
        });

        // loadShops();


        LoaderManager loaderManager = getSupportLoaderManager();
        //loaderManager.initLoader(0, null, this); //Ya no se llaman a los creator

    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        //Contexto, URI, Que queremos, where,camposdelWhere,OrderBy
        CursorLoader loader = new CursorLoader(this, MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //Cuando un codigo no haga nada de perteneciente a la actividad lo suyo es extraerlo fuera
        final Shops shops = ShopDAO.getShops(data);

        shopsFragment.setListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(Shop element, int position) {
                Navigator.navigateFromShopsActivityToDetailShopsActivity(element, ShopsActivity.this);

            }
        });

        shopsFragment.setShops(shops);
    }


    @Override
    public void onLoaderReset(Loader loader) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMapobject = googleMap;
        LatLng sydney = new LatLng(37, -5);
        googleMapobject.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        CameraUpdate center = CameraUpdateFactory.newLatLng(sydney);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(6);

        googleMapobject.moveCamera(center);
        googleMapobject.animateCamera(zoom);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
            googleMapobject.setMyLocationEnabled(true);
            googleMapobject.getUiSettings().setMyLocationButtonEnabled(true);




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {


        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



            googleMapobject.setMyLocationEnabled(true);
            googleMapobject.getUiSettings().setMyLocationButtonEnabled(true);

        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            googleMapobject.setMyLocationEnabled(true);
            googleMapobject.getUiSettings().setMyLocationButtonEnabled(true);


        }


    }

}