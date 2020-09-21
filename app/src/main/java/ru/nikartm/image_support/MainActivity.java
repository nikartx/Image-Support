package ru.nikartm.image_support;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import ru.nikartm.support.BadgePosition;
import ru.nikartm.support.ImageBadgeView;

/**
 * Example of using ImageBadgeView
 */
public class MainActivity extends AppCompatActivity {

    private ImageBadgeView imageBadgeView;
    private ImageBadgeView ibvCart;

    private int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageBadgeView = findViewById(R.id.ibv_icon4);
        ibvCart = findViewById(R.id.ibv_icon2);

        initIconWithBadges();
        initCart();
    }

    // Example of counter on the badge
    private void initCart() {
        ibvCart.setBadgeValue(value)
                .setMaxBadgeValue(20)
                .setLimitBadgeValue(true);
        ibvCart.setOnClickListener(v -> ibvCart.setBadgeValue(++value));
    }

    // Initialize a badge programmatically
    private void initIconWithBadges() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "exo_regular.ttf");
        imageBadgeView.setBadgeValue(27)
                .setBadgeOvalAfterFirst(true)
                .setBadgeTextSize(16)
                .setMaxBadgeValue(999)
                .setBadgeTextFont(typeface)
                .setBadgeBackground(getResources().getDrawable(R.drawable.rectangle_rounded))
                .setBadgePosition(BadgePosition.BOTTOM_RIGHT)
                .setBadgeTextStyle(Typeface.NORMAL)
                .setShowCounter(true)
                .setBadgePadding(4);
    }

}
