package es.styleapps.madridguide;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import es.styleapps.madridguide.manager.db.ShopDAO;
import es.styleapps.madridguide.model.Shop;


//Los test largos con base de datos que lo que hacemos es crear una base de datos se llaman test de integracion
//Los rapidos testUnitarios
public class ShopDAOTests extends AndroidTestCase {

    public static final String SHOP_TESTING_NAME = "Shop Testing Name";

    public void testCanInsertANewShop(){
        final ShopDAO sut = new ShopDAO(getContext());

        final int count = getCount(sut);

        final long id = insertTestData(sut);
        assertTrue(id > 0); //Si el id es mayor que 0 significa que la base de datos tiene algo
        assertTrue(count + 1 == sut.queryCursor().getCount());


    }

    public void testCanDeleteAShop(){

        final ShopDAO sut = new ShopDAO(getContext());
        final long id = insertTestData(sut);
        final int count = getCount(sut);
        assertEquals(1,sut.delete(id));
        assertTrue(count - 1 == sut.queryCursor().getCount());

    }


    public void testDeleteAll(){

        final ShopDAO sut = new ShopDAO(getContext());
        final long id = insertTestData(sut);

        sut.deleteAll();

        final int count = getCount(sut);

        assertEquals(0,count);

    }

    public void testQueryOneShop() {
        final ShopDAO dao = new ShopDAO(getContext());
        final long id = insertTestData(dao);

        Shop sut = dao.query(id);

        assertNotNull(sut);
        assertEquals(sut.getName(), SHOP_TESTING_NAME);

    }

    public void testQueryAllShops(){
        final ShopDAO dao = new ShopDAO(getContext());
        final long id = insertTestData(dao);

        List<Shop> shopList = dao.query();

        assertNotNull(shopList);
        assertTrue(shopList.size() > 0);

        for (Shop shop : shopList) {
            assertTrue(shop.getName().length() > 0);
        }
    }


    private long insertTestData(ShopDAO sut) {
        final Shop shop = new Shop(1, SHOP_TESTING_NAME).setAddress("AD 1");
        return sut.insert(shop);
    }

    private int getCount(ShopDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }


}
