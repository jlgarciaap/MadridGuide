package es.styleapps.madridguide.interactors;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

import es.styleapps.madridguide.manager.db.ShopDAO;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;

/**
 * Created by jlgarciaap on 13/1/17.
 */

public class GetAllShopsFromLocalCacheInteractor {

    public interface OnGetAllShopsFromLocalCacheInteractorCompletion {

        public void completion(Shops shops);

    }


    public void execute(final Context context, final OnGetAllShopsFromLocalCacheInteractorCompletion completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);

                List<Shop> shopList = dao.query();
                final Shops shops = Shops.build(shopList);

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(shops);
                    }
                });


            }


        }).start();

    }

}

class MainThread {

    public static void run (final Runnable runnable){
        Handler handler = new Handler(Looper.getMainLooper());//Lo pasamos al looper main
        handler.post(new Runnable() {
            @Override
            public void run() {
               runnable.run();
            }
        });

    }


}