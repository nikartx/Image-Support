package ru.nikartm.support;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import ru.nikartm.support.constant.Constants;
import ru.nikartm.support.model.Badge;

/**
 * Draw a badge and the count on ImageView
 * @author Ivan V on 21.02.2018.
 * @version 1.0
 */
public class BadgeDrawer {

    private View view;
    private Badge badge;

    private Paint paint;
    private float scale;
    private float dx;
    private float dy;

    public BadgeDrawer(View view, Badge badge) {
        this.view = view;
        this.badge = badge;
    }

    /**
     * Draw badge and counter on {@link Canvas}
     * If a badge counter <= 0 it will be hided on {@link AppCompatImageView}
     */
    public void draw(Canvas canvas) {
        if (badge.isVisible() && badge.getValue() > 0) {
            initPaint();
            float pivotX = view.getPivotX();
            float pivotY = view.getPivotY();
            int viewHeight = view.getMeasuredHeight();
            int viewWidth = view.getMeasuredWidth();

            badge.setTextWidth(getTextWidth());
            computeRadius();

            dx = pivotX + viewWidth / 2 - badge.getRadius();
            dy = pivotY - viewHeight / 2 + badge.getRadius();

            drawBadge(canvas, pivotX, pivotY, viewHeight, viewWidth);
            drawText(canvas);
        }
    }

    private void initPaint() {
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
            scale = view.getResources().getDisplayMetrics().density;
            Typeface typeface = Typeface.create(badge.getBadgeTextFont(), badge.getTextStyle());
            paint.setTypeface(typeface);
            paint.setTextSize(badge.getBadgeTextSize());
        }
    }

    // Draw a badge
    private void drawBadge(Canvas canvas, float pivotX, float pivotY, int viewHeight, int viewWidth) {
        paint.setColor(badge.getBadgeColor());
        if (badge.getBackgroundDrawable() != null) {
            drawCustomBadgeBackground(canvas, pivotX, pivotY, viewHeight, viewWidth);
        } else {
            canvas.drawCircle(dx, dy, badge.getRadius(), paint);
        }
    }

    private void drawCustomBadgeBackground(Canvas canvas, float pivotX, float pivotY, int viewHeight, int viewWidth) {
        dy = pivotY - viewHeight / 2 + getBadgeHeight() / 2;
        int valueHeight = (int) getBadgeHeight();
        int valueWidth = (int) getBadgeWidth();
        if (badge.isFixedRadius()) {
            if (valueWidth > valueHeight) {
                dy = pivotY - viewHeight / 2 + getBadgeWidth() / 2;
                dx = pivotX + viewWidth / 2 - getBadgeWidth() / 2;
            } else {
                dy = pivotY - viewHeight / 2 + getBadgeHeight() / 2;
                dx = pivotX + viewWidth / 2 - getBadgeHeight() / 2;
            }
            int maxRadius = Math.max(valueWidth, valueHeight);
            valueWidth = maxRadius;
            valueHeight = maxRadius;
        } else if (badge.isRoundBadge() && valueWidth < valueHeight) {
            dx = pivotX + viewWidth / 2 - getBadgeHeight() / 2;
            valueWidth = valueHeight;
        }
        badge.getBackgroundDrawable().setBounds(0, 0, valueWidth, valueHeight);
        canvas.save();
        canvas.translate(dx - valueWidth / 2, dy - valueHeight / 2);
        badge.getBackgroundDrawable().draw(canvas);
        canvas.restore();
    }

    // Draw text
    private void drawText(Canvas canvas) {
        paint.setColor(badge.getBadgeTextColor());
        if (badge.isLimitValue() && badge.getValue() > badge.getMaxValue()) {
            canvas.drawText(String.valueOf(badge.getMaxValue()).concat("+"), dx - badge.getTextWidth() / 2,
                    dy + badge.getBadgeTextSize() / scale, paint);
        } else {
            canvas.drawText(String.valueOf(badge.getValue()), dx - badge.getTextWidth() / 2,
                    dy + badge.getBadgeTextSize() / scale, paint);
        }
    }

    private float getBadgeHeight() {
        return badge.getBadgeTextSize() + badge.getPadding() * 2f;
    }

    private float getBadgeWidth() {
        float width;
        if (badge.getBackgroundDrawable() != null
                && badge.getValue() > Constants.MAX_CIRCLE_NUMBER
                && badge.isOvalAfterFirst()
                && !badge.isFixedRadius()) {
            width = badge.getTextWidth() + badge.getPadding() * 4f;
        } else {
            width = badge.getTextWidth() + badge.getPadding() * 2f;
        }
        return width;
    }

    private float getTextWidth() {
        float textWidth;
        if (badge.isLimitValue() && badge.getValue() > badge.getMaxValue()) {
            textWidth = paint.measureText(String.valueOf(badge.getMaxValue()).concat("+"));
        } else {
            textWidth = paint.measureText(String.valueOf(badge.getValue()));
        }
        return textWidth;
    }

    private void computeRadius() {
        if (badge.getFixedRadiusSize() != Constants.NO_INIT) {
            badge.setRadius(badge.getFixedRadiusSize());
        } else {
            badge.setRadius(getBadgeWidth() / 2);
        }
    }
}
