package es.styleapps.madridguide.interactors;

import android.content.Context;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.styleapps.madridguide.manager.db.ShopDAO;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;

public class CacheAllShopsInteractor {
    public interface CacheAllShopsInteractorResponse {
        public void response(boolean success);
    }

    public void execute(final Context context, final Shops shops, final CacheAllShopsInteractorResponse response) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);

                List<Shop> shopDatabase = dao.query();

                boolean success = true;

                for (Shop shop: shops.allShops()) {

                    boolean inDB = false;
                    if (shopDatabase != null && !shopDatabase.isEmpty()){
                        for (Shop shopDB:shopDatabase) {
                            // comprobamos si la tienda ya esta en BD
                           // if (shop.getName().equalsIgnoreCase(shopDB.getName())){
                            if(shop.getId() == shopDB.getId() ||
                                    (System.currentTimeMillis() - shopDB.getDateInsert() < 7)){

                                //Realmente tendria que ser un update. Convertir en integer

                                inDB = true;
                                break;
                            }
                        }
                    }

                    if(!inDB){
                        success = dao.insert(shop) > 0;

                        if (!success) {
                            break;
                        }
                    }
                }

                Looper main = Looper.getMainLooper();
                //main.getThread().

                if (response != null) {
                    response.response(success);
                }
            }
        }).start();
    }
}
