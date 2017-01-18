package es.styleapps.madridguide.interactors;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.List;

import es.styleapps.madridguide.manager.db.ShopDAO;
import es.styleapps.madridguide.manager.net.NetworkManager;
import es.styleapps.madridguide.manager.net.ShopEntity;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;
import es.styleapps.madridguide.model.mappers.ShopEntityShopMapper;
import es.styleapps.madridguide.util.Constants;

public class GetAllShopsInteractor {

    public interface GetAllShopsInteractorResponse {

        public void response (Shops shops);
    }

    public void execute(final Context context, final GetAllShopsInteractorResponse response) {

        final String PREFS_NAME = "dateSaved";
        SharedPreferences dateSaved = context.getSharedPreferences(PREFS_NAME,0);
        long actualDate = new Date().getTime();
        long dateInPref = dateSaved.getLong(PREFS_NAME,actualDate);
        ShopDAO dao = new ShopDAO(context);
        boolean daoData = false;

            if (dao.query() != null){
                if (!dao.query().isEmpty()){

                    daoData = true;
                }
            }

            if (actualDate - dateInPref > Constants.SEVENDAYS || !daoData  || dateInPref == actualDate) {

                dao.deleteAll();
                NetworkManager networkManager = new NetworkManager(context);
                networkManager.getShopsFromServer(new NetworkManager.GetShopListener() {
                    @Override
                    public void getShopEntitiesSuccess(List<ShopEntity> result) {
                        List<Shop> shops = new ShopEntityShopMapper().map(result);

                        if (response != null) {
                            response.response(Shops.build(shops));

                        }
//
                        SharedPreferences dateSaved = context.getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = dateSaved.edit();
                        editor.putLong(PREFS_NAME, new Date().getTime());
                        editor.commit();

//                //TODO actualizar cache cada 7 dias para que no descargue y grabe siempre que descargue
//                ShopDAO dao = new ShopDAO(getApplicationContext());
//                for (Shop shop : shops) {
//                    dao.insert(shop);
//                }
                    }

                    @Override
                    public void getShopEntitiesDidFail() {

                    }
                });

            } else {
                //Si es mayor de siete dias borramos  y descagamos de nuevo

                List<Shop> shopsList = dao.query();
                if (response != null) {
                    response.response(Shops.build(shopsList));
                }

            }

    }
}
