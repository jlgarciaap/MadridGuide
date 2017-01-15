package es.styleapps.madridguide.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import es.styleapps.madridguide.R;
import es.styleapps.madridguide.adapter.ShopsAdapter;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;
import es.styleapps.madridguide.views.OnElementClick;


public class ShopsFragment extends Fragment {


    private RecyclerView shopsRecyclerView; //El tableView
    private ShopsAdapter adapter; //Este seria el dataSource
    private Shops shops;

    private OnElementClick<Shop> listener;


    //Todos los fragmentos requieren un metodo publico y sin parametros
    public ShopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        shopsRecyclerView = (RecyclerView) view.findViewById(R.id.shops_recycler_view);
        //Toda actividad es un contexto(por eso el getActivity)
        shopsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }

    private void updateUI() {

        Shops shops = getShops();

        adapter = new ShopsAdapter(shops,getActivity());
        shopsRecyclerView.setAdapter(adapter);

        adapter.setOnElementClikListener(new OnElementClick<Shop>(){

            @Override
            public void clickedOn(Shop element, int position) {
                if(listener != null) {

                    listener.clickedOn(element,position);
                }
            }
        });

    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
        updateUI();
    }

    public OnElementClick<Shop> getListener(){
        return listener;
    }

    public void setListener(OnElementClick<Shop> listener){

        this.listener = listener;
    }

}
