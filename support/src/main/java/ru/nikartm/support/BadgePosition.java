package ru.nikartm.support;

import android.view.View;

import ru.nikartm.support.constant.Constants;
import ru.nikartm.support.model.Badge;

/**
 * Determine the position of a badge on view
 * @author Ivan V on 24.02.2018.
 * @version 1.0
 */
public class BadgePosition {

    public static final int TOP_LEFT = 0;
    public static final int TOP_RIGHT = 1;
    public static final int BOTTOM_LEFT = 2;
    public static final int BOTTOM_RIGHT = 3;
    public static final int CENTER = 4;

    private View view;
    private Badge badge;

    private boolean isDrawBackgroundAdded = false;

    private float pivotX;
    private float pivotY;
    private int viewHeight;
    private int viewWidth;
    private int badgeWidth;
    private int badgeHeight;

    private float deltaX;
    private float deltaY;

    BadgePosition(View view, Badge badge) {
        this.view = view;
        this.badge = badge;
        init();
        computeBadgeWidth();
        computeBadgeHeight();
        computeRadius();
    }

    private void init() {
        isDrawBackgroundAdded = badge.getBackgroundDrawable() != null;
        pivotX = view.getPivotX();
        pivotY = view.getPivotY();
        viewHeight = view.getMeasuredHeight();
        viewWidth = view.getMeasuredWidth();
    }

    /**
     * Compute position of a badge on view
     */
    public BadgePosition compute() {
        switch (badge.getPosition()) {
            case TOP_LEFT:
                computeTopLeft();
                break;
            case TOP_RIGHT:
                computeTopRight();
                break;
            case BOTTOM_LEFT:
                computeBottomLeft();
                break;
            case BOTTOM_RIGHT:
                computeBottomRight();
                break;
            case CENTER:
                computeCenter();
                break;
        }
        return this;
    }

    private void defineMeasurement() {
        if (checkFixedRadius()) {
            int maxMeasurement = Math.max(badgeWidth, badgeHeight);
            badgeWidth = maxMeasurement;
            badgeHeight = maxMeasurement;
        } else if ((badge.isOvalAfterFirst() && badge.getValue() <= Constants.MAX_CIRCLE_NUMBER)
                || (badge.isRoundBadge() && badgeWidth < badgeHeight)) {
            badgeWidth = badgeHeight;
        }
    }

    private void computeTopLeft() {
        if (isDrawBackgroundAdded) {
            defineMeasurement();
            deltaX = pivotX - viewWidth / 2 + badgeWidth / 2;
            deltaY = pivotY - viewHeight / 2 + badgeHeight / 2;
        } else {
            deltaX = pivotX - viewWidth / 2 + badge.getRadius();
            deltaY = pivotY - viewHeight / 2 + badge.getRadius();
        }
    }

    private void computeTopRight() {
        if (isDrawBackgroundAdded) {
            defineMeasurement();
            deltaX = pivotX + viewWidth / 2 - badgeWidth / 2;
            deltaY = pivotY - viewHeight / 2 + badgeHeight / 2;
        } else {
            deltaX = pivotX + viewWidth / 2 - badge.getRadius();
            deltaY = pivotY - viewHeight / 2 + badge.getRadius();
        }
    }

    private void computeBottomLeft() {
        if (isDrawBackgroundAdded) {
            defineMeasurement();
            deltaX = pivotX - viewWidth / 2 + badgeWidth / 2;
            deltaY = pivotY + viewHeight / 2 - badgeHeight / 2;
        } else {
            deltaX = pivotX - viewWidth / 2 + badge.getRadius();
            deltaY = pivotY + viewHeight / 2 - badge.getRadius();
        }
    }

    private void computeBottomRight() {
        if (isDrawBackgroundAdded) {
            defineMeasurement();
            deltaX = pivotX + viewWidth / 2 - badgeWidth / 2;
            deltaY = pivotY + viewHeight / 2 - badgeHeight / 2;
        } else {
            deltaX = pivotX + viewWidth / 2 - badge.getRadius();
            deltaY = pivotY + viewHeight / 2 - badge.getRadius();
        }
    }

    private void computeCenter() {
        deltaX = viewWidth / 2;
        deltaY = viewHeight / 2;
        if (isDrawBackgroundAdded) {
            defineMeasurement();
        }
    }

    private void computeBadgeWidth() {
        if (badge.getFixedRadiusSize() != Constants.NO_INIT) {
            badgeWidth = (int) (badge.getFixedRadiusSize() * 2f);
        } else if (isDrawBackgroundAdded
                && badge.getValue() > Constants.MAX_CIRCLE_NUMBER
                && badge.isOvalAfterFirst()
                && !badge.isFixedRadius()) {
            badgeWidth = (int) (badge.getTextWidth() + badge.getPadding() * 4f);
        } else {
            badgeWidth = (int) (badge.getTextWidth() + badge.getPadding() * 2f);
        }
    }

    private void computeBadgeHeight() {
        if (badge.getFixedRadiusSize() != Constants.NO_INIT) {
            badgeHeight = (int) (badge.getFixedRadiusSize() * 2f);
        } else {
            badgeHeight = (int) (badge.getBadgeTextSize() + badge.getPadding() * 2f);
        }
    }

    private void computeRadius() {
        if (badge.getFixedRadiusSize() != Constants.NO_INIT) {
            badge.setRadius(badge.getFixedRadiusSize());
        } else {
            badge.setRadius(badgeWidth / 2f);
        }
    }

    private boolean checkFixedRadius() {
        return badge.getFixedRadiusSize() != Constants.NO_INIT || badge.isFixedRadius();
    }

    public int getBadgeHeight() {
        return badgeHeight;
    }

    public int getBadgeWidth() {
        return badgeWidth;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

}