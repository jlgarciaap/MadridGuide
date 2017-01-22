package es.styleapps.madridguide.manager.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jlgarciaap on 14/1/17.
 */

public class ShopsResponse {
    //Lo convertimos en una lista de ShopEntitys
    @SerializedName("result")
    List<ShopEntity> result;

}
