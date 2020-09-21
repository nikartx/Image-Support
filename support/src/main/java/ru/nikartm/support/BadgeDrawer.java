package ru.nikartm.support;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatImageView;
import android.view.View;

import ru.nikartm.support.model.Badge;

/**
 * Draw a badge and the count on ImageView
 * @author Ivan V on 21.02.2018.
 * @version 1.0
 */
public class BadgeDrawer {

    private BadgePosition position;
    private Paint paint;

    private View view;
    private Badge badge;

    BadgeDrawer(View view, Badge badge) {
        this.view = view;
        this.badge = badge;
    }

    /**
     * Draw badge and counter on {@link Canvas}
     * If a badge counter <= 0 it will be hided on {@link AppCompatImageView}
     */
    public void draw(Canvas canvas) {
        if (badge.isVisible() || badge.getValue() > 0) {
            initPaint();
            computeTextWidth();
            position = new BadgePosition(view, badge).compute();

            drawBadge(canvas);
            if (badge.isShowCounter()) {
                drawText(canvas);
            }
        }
    }

    private void initPaint() {
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
            Typeface typeface = Typeface.create(badge.getBadgeTextFont(), badge.getTextStyle());
            paint.setTypeface(typeface);
            paint.setTextSize(badge.getBadgeTextSize());
        }
    }

    // Draw a badge
    private void drawBadge(Canvas canvas) {
        paint.setColor(badge.getBadgeColor());
        if (badge.getBackgroundDrawable() != null) {
            badge.getBackgroundDrawable().setBounds(0, 0, position.getBadgeWidth(), position.getBadgeHeight());
            canvas.save();
            canvas.translate(position.getDeltaX() - position.getBadgeWidth() / 2,
                             position.getDeltaY() - position.getBadgeHeight() / 2);
            badge.getBackgroundDrawable().draw(canvas);
            canvas.restore();
        } else {
            canvas.drawCircle(position.getDeltaX(), position.getDeltaY(), badge.getRadius(), paint);
        }
    }

    // Draw text
    private void drawText(Canvas canvas) {
        paint.setColor(badge.getBadgeTextColor());
        if (badge.isLimitValue() && badge.getValue() > badge.getMaxValue()) {
            canvas.drawText(String.valueOf(badge.getMaxValue()).concat("+"), position.getDeltaX() - badge.getTextWidth() / 2,
                    position.getDeltaY() + badge.getBadgeTextSize() / 3f, paint);
        } else {
            canvas.drawText(String.valueOf(badge.getValue()), position.getDeltaX() - badge.getTextWidth() / 2,
                    position.getDeltaY() + badge.getBadgeTextSize() / 3f, paint);
        }
    }

    private void computeTextWidth() {
        float textWidth;
        if (badge.isLimitValue() && badge.getValue() > badge.getMaxValue()) {
            textWidth = paint.measureText(String.valueOf(badge.getMaxValue()).concat("+"));
        } else {
            textWidth = paint.measureText(String.valueOf(badge.getValue()));
        }
        badge.setTextWidth(textWidth);
    }
}
