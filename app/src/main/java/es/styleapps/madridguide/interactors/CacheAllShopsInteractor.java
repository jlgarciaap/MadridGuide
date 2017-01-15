package es.styleapps.madridguide.interactors;

import android.content.Context;
import android.os.Looper;

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

                boolean success = true;
                for (Shop shop: shops.allShops()) {
                    success = dao.insert(shop) > 0;
                    if (!success) {
                        break;
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
