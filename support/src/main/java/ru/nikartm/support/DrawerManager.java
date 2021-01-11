package ru.nikartm.support;

import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import ru.nikartm.support.model.Badge;

/**
 * Badge drawer manager
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

    /**
     * Method to provide the badge drawer
     * @param canvas {@link Canvas} to drawing on {@link AppCompatImageView}
     */
    public void drawBadge(Canvas canvas) {
        drawer.draw(canvas);
    }

    /**
     * Provide badge entity after attrs init
     * @return badge entity
     */
    public Badge getBadge() {
        return attrController.getBadge();
    }

}
