package es.styleapps.madridguide.manager.net;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jlgarciaap on 14/1/17.
 */

public class ShopEntity {
    //Una entitie es lo que esta dentro del un josn cada objeto seria una entitie

    @SerializedName("id") private Long id;
    @SerializedName("name") private String name;
    @SerializedName("img") private String img;
    @SerializedName("logo_img") private String logoImg;
    @SerializedName("address") private String address;
    @SerializedName("url") private String url;
    @SerializedName("description_es") private String descriptionEs;
    @SerializedName("description_en") private String descriptionEn;
    @SerializedName("gps_lat") private float latitude;
    @SerializedName("gps_lon") private float longitude;


    public Long getId() {
        return id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public String getDescriptionEs() {
        return descriptionEs;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }
}
