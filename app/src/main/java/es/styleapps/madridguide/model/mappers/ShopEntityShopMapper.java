package es.styleapps.madridguide.model.mappers;

import java.util.Date;
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
            shop.setDescription(entity.getDescriptionEs());
            shop.setLogoImgUrl(entity.getLogoImg());
            shop.setLatitude(entity.getLatitude());
            shop.setLongitude(entity.getLongitude());
            shop.setImageUrl(entity.getImg());
            shop.setAddress(entity.getAddress());
            shop.setDescriptionEn(entity.getDescriptionEn());

            result.add(shop);
        }

        return result;

    }


}
