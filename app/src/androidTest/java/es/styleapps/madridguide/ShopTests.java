package es.styleapps.madridguide;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.runner.RunWith;

import es.styleapps.madridguide.model.Shop;

//@RunWith(AndroidJUnit4.class)
public class ShopTests extends AndroidTestCase {

    public static final String SHOP = "Shop";
    public static final String ADDRESS = "ADDRESS";

    public void testCanCreateAShop() {

        Shop sut = new Shop(0, SHOP); //System Under Test

        assertNotNull(sut);

    }

    public void testANewShopStoresDataCorrectly() {

        Shop sut = new Shop(10, SHOP);

        assertEquals(10, sut.getId());
        assertEquals(SHOP,sut.getName());

    }

    public void testANewShopStoresDataInPropertiesCorrectly() {

        Shop sut = new Shop(11, SHOP)
                .setAddress(ADDRESS)
                .setDescription("DESC")
                .setImageUrl("URL");

        assertEquals(sut.getAddress(), ADDRESS);
        assertEquals(sut.getDescription(),"DESC");



    }


}
