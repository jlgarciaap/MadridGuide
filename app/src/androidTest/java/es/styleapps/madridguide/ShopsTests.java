package es.styleapps.madridguide;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;


import java.util.ArrayList;
import java.util.List;

import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;

/**
 * Created by jlgarciaap on 12/1/17.
 */

public class ShopsTests extends AndroidTestCase {

    public void testCreatingAShopsWithNullListReturnsNonNullShops(){

        Shops sut = Shops.build(null);
        assertNotNull(sut);
        assertNotNull(sut.allShops());

    }

    public void testCreatingAShopsWithAListReturnsNonNullShops(){

        List<Shop> data = getShops();


        Shops sut = Shops.build(data);
        assertNotNull(sut);
        assertNotNull(sut.allShops());
        assertEquals(sut.allShops(), data);
        assertEquals(sut.allShops().size(), data.size());

    }

    @NonNull
    private List<Shop> getShops() {
        List<Shop> data = new ArrayList<>();
        data.add(new Shop(1,"1").setAddress("AD 1"));
        data.add(new Shop(2,"2").setAddress("AD 2"));
        return data;
    }

}
