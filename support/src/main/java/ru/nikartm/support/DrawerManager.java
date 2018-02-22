package ru.nikartm.support;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import ru.nikartm.support.model.Badge;

/**
 * @author Ivan V on 21.02.2018.
 * @version 1.0
 */
public class DrawerManager {

    private AttributeController attrController;
    private BadgeDrawer drawer;

    public DrawerManager(Context context, AttributeSet attrs) {
        initManager(context, attrs);
    }

    private void initManager(Context context, AttributeSet attrs) {
        attrController = new AttributeController(context, attrs);
        drawer = new BadgeDrawer(context, attrController.getBadge());
    }

    public void drawBadge(Canvas canvas) {
        drawer.draw(canvas);
    }

    public AttributeController getAttrController() {
        return attrController;
    }

    public BadgeDrawer getDrawer() {
        return drawer;
    }

    public Badge getBadge() {
        return attrController.getBadge();
    }

}
