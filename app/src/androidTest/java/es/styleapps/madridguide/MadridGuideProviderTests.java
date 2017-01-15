package es.styleapps.madridguide;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaActionSound;
import android.net.Uri;
import android.test.AndroidTestCase;

import es.styleapps.madridguide.manager.db.DBConstants;
import es.styleapps.madridguide.manager.db.ShopDAO;
import es.styleapps.madridguide.manager.db.provider.MadridGuideProvider;
import es.styleapps.madridguide.model.Shop;

/**
 * Created by jlgarciaap on 13/1/17.
 */

public class MadridGuideProviderTests extends AndroidTestCase {

    public void testQueryAllShops(){
        ContentResolver cr = getContext().getContentResolver();//Eso nos busca el contentProvider que nos cuadre

        Cursor c = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS,null,null,null);

        assertNotNull(c);

    }

    public void testInsertShop() {

        ContentResolver cr = getContext().getContentResolver();//Eso nos busca el contentProvider que nos cuadre

        final Cursor beforeCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS,null,null,null);
        final int beforeCount = beforeCursor.getCount();

        Shop shop = new Shop(1, "Little shop of horrors!");
         Uri insertedUri = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));
        assertNotNull(insertedUri);

        final Cursor afterCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS,null,null,null);
        final int afterCount = afterCursor.getCount();

        assertEquals(beforeCount + 1, afterCount);

    }


}
