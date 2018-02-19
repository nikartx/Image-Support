package ru.nikartm.image_support;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.nikartm.support.ImageBadgeView;

public class MainActivity extends AppCompatActivity {

    private ImageBadgeView imageBadgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageBadgeView = findViewById(R.id.ibv_icon);
        initIconsWithBadges();
    }

    private void initIconsWithBadges() {
        imageBadgeView.setBadgeValue(10)
                .setBadgeTextSize(16);
    }
}
