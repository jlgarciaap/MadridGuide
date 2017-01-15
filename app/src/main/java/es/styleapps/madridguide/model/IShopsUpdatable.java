package es.styleapps.madridguide.model;

/**
 * Created by jlgarciaap on 10/1/17.
 */
public interface IShopsUpdatable {

    void add(Shop shop);
    void delete(Shop shop);
    void edit(Shop newShop, long index);
}
