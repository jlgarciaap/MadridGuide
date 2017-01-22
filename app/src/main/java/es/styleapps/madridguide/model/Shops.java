package es.styleapps.madridguide.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jlgarciaap on 10/1/17.
 */

public class Shops implements IShopsIterable, IShopsUpdatable {

    List<Shop> shops;

    //Nos creamos nuestros metodos al estilo constructor porque estos no se heredan y es mejor asin
    public static
    @NonNull
    Shops build(@NonNull final List<Shop> shopList) {
        Shops shops = new Shops(shopList);

        if (shopList == null) {

            shops.shops = new ArrayList<>();

        }

        return shops;
    }

    public static
    @NonNull
    Shops build() {

        return build(null); //Pasamos null para hacer una lista vacia con el metodo superior

    }

    private Shops() {

        shops = new ArrayList<>();

    }

    private Shops(List<Shop> shops) {
        this.shops = shops;
    }


    @Override
    public long size() {
        return shops.size();
    }

    @Override
    public Shop get(long index) {
        return shops.get((int) index);
    }

    @Override
    public List<Shop> allShops() {
        return shops;
    }

    @Override
    public void add(Shop shop) {

        shops.add(shop);
    }

    @Override
    public void delete(Shop shop) {

        shops.remove(shop);

    }

    @Override
    public void edit(Shop newShop, long index) {

        shops.set((int) index, newShop);

    }
}
