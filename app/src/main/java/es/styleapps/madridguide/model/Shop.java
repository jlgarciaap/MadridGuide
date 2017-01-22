package es.styleapps.madridguide.model;

import java.io.Serializable;

/**
 * Created by jlgarciaap on 10/1/17.
 */

public class Shop implements Serializable{
    private long id;
    private String name;
    private String imageUrl;
    private String logoImgUrl;
    private String address;
    private String url;
    private String description;



    private String descriptionEn;
    private float latitude;
    private float longitude;


    public Shop(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Shop() { // Con esto ocultamos el constructor por defecto
    }

    public long getId() {
        return id;
    }

    public Shop setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Shop setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Shop setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public Shop setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shop setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Shop setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Shop setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public Shop setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
        return  this;
    }

    public float getLatitude() {
        return latitude;
    }

    public Shop setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public Shop setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }
}
