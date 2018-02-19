package ru.nikartm.image_support;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.nikartm.support.ImageBadgeView;

public class MainActivity extends AppCompatActivity {

    private ImageBadgeView imageBadgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageBadgeView = findViewById(R.id.ibv_icon4);
        initIconWithBadges();
    }

    private void initIconWithBadges() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "exo_regular.ttf");
        imageBadgeView.setBadgeValue(10)
                .setBadgeTextSize(16)
                .setBadgeColor(getResources().getColor(R.color.colorRed400))
                .setMaxBadgeValue(999)
                .setBadgeTextFont(tf)
                .setBadgePadding(5);
    }
}
