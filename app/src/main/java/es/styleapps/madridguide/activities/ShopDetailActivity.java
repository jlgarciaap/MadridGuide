package es.styleapps.madridguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.styleapps.madridguide.R;
import es.styleapps.madridguide.model.Shop;
import es.styleapps.madridguide.util.Constants;

public class ShopDetailActivity extends AppCompatActivity {

    private Shop shop;

    @BindView(R.id.activity_shop_detail_logo_image)
    ImageView logoImage;
    @BindView(R.id.activity_shop_detail_name_text)
    TextView nameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        getDetailShopFromCallingIntent(intent);
        updateUI();

    }

    private void updateUI() {
        nameText.setText(shop.getName());
        Picasso.with(this).load(shop.getLogoImgUrl()).into(logoImage);
    }

    private void getDetailShopFromCallingIntent(Intent intent) {
        if(intent != null) {
            shop = (Shop) intent.getSerializableExtra(Constants.INTENT_KEY_DETAIL_SHOP);
        }
    }
}
