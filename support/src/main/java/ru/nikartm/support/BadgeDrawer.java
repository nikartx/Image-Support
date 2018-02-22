package ru.nikartm.support;

import android.content.Context;
import android.graphics.Canvas;

import ru.nikartm.support.model.Badge;

/**
 * @author Ivan V on 21.02.2018.
 * @version 1.0
 */
public class BadgeDrawer {

    private Context context;
    private Badge badge;

    public BadgeDrawer(Context context, Badge badge) {
        this.context = context;
        this.badge = badge;
    }

    public void draw(Canvas canvas) {
        // Draw a badge
    }
}
