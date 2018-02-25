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
        computeBadgeWidth();
        computeBadgeHeight();
        computeRadius();
        init();
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

    private void computeTopLeft() {
        deltaX = pivotX - viewWidth / 2 + badge.getRadius();
        deltaY = pivotY - viewHeight / 2 + badge.getRadius();
        if (isDrawBackgroundAdded) {
            if (badge.isFixedRadius()) {
                if (badgeWidth > badgeHeight) {
                    deltaX = pivotX - viewWidth / 2 + getBadgeWidth() / 2;
                    deltaY = pivotY - viewHeight / 2 + getBadgeWidth() / 2;
                } else {
                    deltaX = pivotX - viewWidth / 2 + getBadgeHeight() / 2;
                    deltaY = pivotY - viewHeight / 2 + getBadgeHeight() / 2;
                }
                int maxRadius = Math.max(badgeWidth, badgeHeight);
                badgeWidth = maxRadius;
                badgeHeight = maxRadius;
            } else if (badge.isRoundBadge() && badgeWidth < badgeHeight) {
                deltaX = pivotX - viewWidth / 2 + getBadgeHeight() / 2;
                deltaY = pivotY - viewHeight / 2 + getBadgeHeight() / 2;
                badgeWidth = badgeHeight;
            }
        }
    }

    private void computeTopRight() {
        deltaX = pivotX + viewWidth / 2 - badge.getRadius();
        deltaY = pivotY - viewHeight / 2 + badge.getRadius();
        if (isDrawBackgroundAdded) {
            if (badge.isFixedRadius()) {
                if (badgeWidth > badgeHeight) {
                    deltaX = pivotX + viewWidth / 2 - getBadgeWidth() / 2;
                    deltaY = pivotY - viewHeight / 2 + getBadgeWidth() / 2;
                } else {
                    deltaX = pivotX + viewWidth / 2 - getBadgeHeight() / 2;
                    deltaY = pivotY - viewHeight / 2 + getBadgeHeight() / 2;
                }
                int maxRadius = Math.max(badgeWidth, badgeHeight);
                badgeWidth = maxRadius;
                badgeHeight = maxRadius;
            } else if (badge.isRoundBadge() && badgeWidth < badgeHeight) {
                deltaX = pivotX + viewWidth / 2 - getBadgeHeight() / 2;
                deltaY = pivotY - viewHeight / 2 + getBadgeHeight() / 2;
                badgeWidth = badgeHeight;
            }
        }
    }

    private void computeBottomLeft() {
        deltaX = pivotX - viewWidth / 2 + badge.getRadius();
        deltaY = pivotY + viewHeight / 2 - badge.getRadius();
        if (isDrawBackgroundAdded) {
            if (badge.isFixedRadius()) {
                if (badgeWidth > badgeHeight) {
                    deltaX = pivotX - viewWidth / 2 + getBadgeWidth() / 2;
                    deltaY = pivotY + viewHeight / 2 - getBadgeWidth() / 2;
                } else {
                    deltaX = pivotX - viewWidth / 2 + getBadgeHeight() / 2;
                    deltaY = pivotY + viewHeight / 2 - getBadgeHeight() / 2;
                }
                int maxRadius = Math.max(badgeWidth, badgeHeight);
                badgeWidth = maxRadius;
                badgeHeight = maxRadius;
            } else if (badge.isRoundBadge() && badgeWidth < badgeHeight) {
                deltaX = pivotX - viewWidth / 2 + getBadgeHeight() / 2;
                deltaY = pivotY + viewHeight / 2 - getBadgeHeight() / 2;
                badgeWidth = badgeHeight;
            }
        }
    }

    private void computeBottomRight() {
        deltaX = pivotX + viewWidth / 2 - badge.getRadius();
        deltaY = pivotY + viewHeight / 2 - badge.getRadius();
        if (isDrawBackgroundAdded) {
            if (badge.isFixedRadius()) {
                if (badgeWidth > badgeHeight) {
                    deltaX = pivotX + viewWidth / 2 - getBadgeWidth() / 2;
                    deltaY = pivotY + viewHeight / 2 - getBadgeWidth() / 2;
                } else {
                    deltaX = pivotX + viewWidth / 2 - getBadgeHeight() / 2;
                    deltaY = pivotY + viewHeight / 2 - getBadgeHeight() / 2;
                }
                int maxRadius = Math.max(badgeWidth, badgeHeight);
                badgeWidth = maxRadius;
                badgeHeight = maxRadius;
            } else if (badge.isRoundBadge() && badgeWidth < badgeHeight) {
                deltaX = pivotX + viewWidth / 2 - getBadgeHeight() / 2;
                deltaY = pivotY + viewHeight / 2 - getBadgeHeight() / 2;
                badgeWidth = badgeHeight;
            }
        }
    }

    private void computeCenter() {
        deltaX = viewWidth / 2;
        deltaY = viewHeight / 2;
        if (isDrawBackgroundAdded) {
            if (badge.isFixedRadius()) {
                int fixedDiameter = (int) badge.getFixedRadiusSize() * 2;
                badgeWidth = fixedDiameter;
                badgeHeight = fixedDiameter;
            } else if (badge.isRoundBadge() && badgeWidth < badgeHeight) {
                badgeWidth = badgeHeight;
            }
        }
    }

    private void computeBadgeWidth() {
        if (badge.getBackgroundDrawable() != null
                && badge.getValue() > Constants.MAX_CIRCLE_NUMBER
                && badge.isOvalAfterFirst()
                && !badge.isFixedRadius()) {
            badgeWidth = (int) (badge.getTextWidth() + badge.getPadding() * 4f);
        } else {
            badgeWidth = (int) (badge.getTextWidth() + badge.getPadding() * 2f);
        }
    }

    private void computeBadgeHeight() {
        badgeHeight = (int) (badge.getBadgeTextSize() + badge.getPadding() * 2f);
    }

    private void computeRadius() {
        if (badge.getFixedRadiusSize() != Constants.NO_INIT) {
            badge.setRadius(badge.getFixedRadiusSize());
        } else {
            badge.setRadius(badgeWidth / 2);
        }
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