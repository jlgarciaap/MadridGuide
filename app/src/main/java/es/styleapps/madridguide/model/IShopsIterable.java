package es.styleapps.madridguide.model;

import java.util.List;

/**
 * Created by jlgarciaap on 10/1/17.
 */
public interface IShopsIterable {
    long size();
    Shop get(long index);
    List<Shop> allShops();

}
