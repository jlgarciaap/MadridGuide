package es.styleapps.madridguide.interactors;

import android.content.Context;

import java.util.List;

import es.styleapps.madridguide.manager.db.ShopDAO;
import es.styleapps.madridguide.manager.net.NetworkManager;
import es.styleapps.madridguide.manager.net.ShopEntity;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.model.Shops;
import es.styleapps.madridguide.model.mappers.ShopEntityShopMapper;

public class GetAllShopsInteractor {

    public interface GetAllShopsInteractorResponse {

        public void response (Shops shops);
    }

    public void execute(Context context, final GetAllShopsInteractorResponse response) {

        Shops shops = null;

        //COn volley ya se ejecuta en segundo plano
        //Testing
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getShopsFromServer(new NetworkManager.GetShopListener() {
            @Override
            public void getShopEntitiesSuccess(List<ShopEntity> result) {
                List<Shop> shops = new ShopEntityShopMapper().map(result);

                if(response != null){
                    response.response(Shops.build(shops));

                }
//
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
    }
}
