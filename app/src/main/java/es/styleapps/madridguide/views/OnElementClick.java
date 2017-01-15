package es.styleapps.madridguide.views;

/**
 * Created by jlgarciaap on 14/1/17.
 */

public interface OnElementClick<T> {

    public abstract void clickedOn(T element, int position);
}
