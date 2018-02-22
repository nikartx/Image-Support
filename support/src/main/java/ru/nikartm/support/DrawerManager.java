package ru.nikartm.support;

import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import ru.nikartm.support.model.Badge;

/**
 * @author Ivan V on 21.02.2018.
 * @version 1.0
 */
public class DrawerManager {

    private AttributeController attrController;
    private BadgeDrawer drawer;

    public DrawerManager(View view, AttributeSet attrs) {
        initManager(view, attrs);
    }

    private void initManager(View view, AttributeSet attrs) {
        attrController = new AttributeController(view, attrs);
        drawer = new BadgeDrawer(view, attrController.getBadge());
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
