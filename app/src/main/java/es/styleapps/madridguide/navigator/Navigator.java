package es.styleapps.madridguide.navigator;

import android.content.Intent;

import es.styleapps.madridguide.activities.MainActivity;
import es.styleapps.madridguide.activities.ShopDetailActivity;
import es.styleapps.madridguide.activities.ShopsActivity;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.util.Constants;

/**
 * Created by jlgarciaap on 9/1/17.
 */
public class Navigator {
    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {
        //Todas las variables locales son final para no liarla ya que en su mayoria son constantes
        final Intent i = new Intent(mainActivity, ShopsActivity.class);

        mainActivity.startActivity(i);

        //Para poder hacerlo testeable cambiamos esto para que devuelva el intent(pero es solo para test)
        //el metodo seria public static void por defecto, lo hemos cambiado a public static Intent
        return i;

    }

    public static Intent navigateFromShopsActivityToDetailShopsActivity(Shop shop, final ShopsActivity shopsActivity) {

        final Intent i = new Intent(shopsActivity, ShopDetailActivity.class);

        i.putExtra(Constants.INTENT_KEY_DETAIL_SHOP, shop);

        shopsActivity.startActivity(i);

        return i;

    }
}
