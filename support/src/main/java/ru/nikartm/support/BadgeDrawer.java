package ru.nikartm.support;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import ru.nikartm.support.constant.Constants;
import ru.nikartm.support.model.Badge;

/**
 * @author Ivan V on 21.02.2018.
 * @version 1.0
 */
public class BadgeDrawer {

    private View view;
    private Badge badge;

    private float scale;
    private Paint paint;

    public BadgeDrawer(View view, Badge badge) {
        this.view = view;
        this.badge = badge;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        scale = view.getContext().getResources().getDisplayMetrics().density;
        Typeface typeface = Typeface.create(badge.getBadgeTextFont(), badge.getTextStyle());
        paint.setTypeface(typeface);
        paint.setTextSize(badge.getBadgeTextSize());
    }

    public void draw(Canvas canvas) {
        if (badge.isVisible() && badge.getValue() > 0) {
            float pivotX = view.getPivotX();
            float pivotY = view.getPivotY();
            int viewHeight = view.getMeasuredHeight();
            int viewWidth = view.getMeasuredWidth();

            badge.setTextWidth(getTextWidth());
            computeRadius();

            float dx = pivotX + viewWidth / 2 - badge.getRadius();
            float dy = pivotY - viewHeight / 2 + badge.getRadius();

            // Draw a badge
            paint.setColor(badge.getBadgeColor());
            if (badge.getBackgroundDrawable() != null) {
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
            } else {
                canvas.drawCircle(dx, dy, badge.getRadius(), paint);
            }

            // Draw text
            paint.setColor(badge.getBadgeTextColor());
            if (badge.isLimitValue() && badge.getValue() > Constants.MAX_VALUE) {
                canvas.drawText(String.valueOf(Constants.MAX_VALUE).concat("+"), dx - badge.getTextWidth() / 2,
                        dy + badge.getBadgeTextSize() / scale, paint);
            } else {
                canvas.drawText(String.valueOf(badge.getValue()), dx - badge.getTextWidth() / 2,
                        dy + badge.getBadgeTextSize() / scale, paint);
            }
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
        if (badge.isLimitValue() && badge.getValue() > Constants.MAX_VALUE) {
            textWidth = paint.measureText(String.valueOf(badge.getValue()).concat("+"));
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
