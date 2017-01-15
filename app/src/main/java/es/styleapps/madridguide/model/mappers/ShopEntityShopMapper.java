package es.styleapps.madridguide.model.mappers;

import java.util.LinkedList;
import java.util.List;

import es.styleapps.madridguide.manager.net.ShopEntity;
import es.styleapps.madridguide.model.Shop;

/**
 * Created by jlgarciaap on 14/1/17.
 */

public class ShopEntityShopMapper {
    //Mapeamos desde una entidad a un modelo
    public List<Shop> map(List<ShopEntity> shopEntities) {

        List<Shop> result = new LinkedList<>();

        for (ShopEntity entity : shopEntities) {
            Shop shop = new Shop(entity.getId(), entity.getName());
            //detect current lang y segun como este uno u otro. Esto se hace en el mapper
            shop.setDescription(entity.getDescriptionEs());
            shop.setLogoImgUrl(entity.getLogoImg());
            shop.setLatitude(entity.getLatitude());
            shop.setLongitude(entity.getLongitude());
            shop.setImageUrl(entity.getImg());
            shop.setAddress(entity.getAddress());
            //..

            result.add(shop);
        }

        return result;

    }


}
