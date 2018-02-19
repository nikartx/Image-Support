package ru.nikartm.support;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * @author Ivan V on 18.02.2018.
 * @version 1.0
 */
public class ImageBadgeView extends android.support.v7.widget.AppCompatImageView {

    public static final int MAX_VALUE = 99;
    public static final int DEFAULT_BADGE_COLOR = Color.parseColor("maroon");
    public static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    public static final int DEFAULT_BADGE_PADDING = 1;
    public static final int DEFAULT_TEXT_SIZE = 12;
    public static final Typeface DEFAULT_FONT = Typeface.DEFAULT;
    public static final int DEFAULT_FONT_STYLE = Typeface.NORMAL;

    private int badgeValue = 0;
    private int maxBadgeValue = MAX_VALUE;
    private float badgeRadius;
    private int badgeColor = DEFAULT_BADGE_COLOR;
    private int badgeTextColor = DEFAULT_TEXT_COLOR;
    private int badgeTextSize = DEFAULT_TEXT_SIZE;
    private int badgePadding = DEFAULT_BADGE_PADDING;
    private Typeface typefaceFont = DEFAULT_FONT;
    private int typefaceStyle = DEFAULT_FONT_STYLE;
    private boolean visibleBadge = true;
    private boolean limitValue = true;

    private Context context;
    private float scale;
    private Paint paint;

    public ImageBadgeView(Context context) {
        super(context);
        this.context = context;
    }

    public ImageBadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public ImageBadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (visibleBadge && badgeValue > 0 ) {
            if(paint == null){
                paint = new Paint();
                Resources resources = context.getResources();
                scale = resources.getDisplayMetrics().density;
                Typeface typeface = Typeface.create(typefaceFont, typefaceStyle);
                paint.setTypeface(typeface);
                paint.setTextSize(badgeTextSize * scale);
            }
            float pivotX = getPivotX();
            float pivotY = getPivotY();
            int viewHeight = getMeasuredHeight();
            int viewWidth = getMeasuredWidth();
            float padding = badgePadding * scale;

            float textWidth;
            if (limitValue && badgeValue > maxBadgeValue) {
                badgeRadius = badgeTextSize + padding + scale;
                textWidth = paint.measureText(maxBadgeValue + "+");
            } else {
                badgeRadius = badgeTextSize + padding - scale;
                textWidth = paint.measureText(badgeValue + "");
            }

            float x = pivotX + viewWidth / 2 - badgeRadius * scale;
            float y = pivotY - viewHeight / 2 + badgeRadius * scale;
            paint.setColor(badgeColor);
            canvas.drawCircle(x, y, badgeRadius * 2, paint);

            paint.setColor(badgeTextColor);
            if (limitValue && badgeValue > maxBadgeValue) {
                canvas.drawText(maxBadgeValue + "+", x - textWidth / 2,
                        y + badgeTextSize * scale / 3, paint);
            } else {
                canvas.drawText(badgeValue + "", x - textWidth / 2,
                        y + badgeTextSize * scale / 3, paint);
            }
        }
    }

    public int getBadgeValue() {
        return badgeValue;
    }

    public ImageBadgeView setBadgeValue(int badgeValue) {
        this.badgeValue = badgeValue;
        invalidate();
        return this;
    }

    public ImageBadgeView visibleBadge(boolean visible) {
        visibleBadge = visible;
        invalidate();
        return this;
    }

    public int getBadgeColor() {
        return badgeColor;
    }

    public ImageBadgeView setBadgeColor(int badgeColor) {
        this.badgeColor = badgeColor;
        invalidate();
        return this;
    }

    public int getBadgeTextColor() {
        return badgeTextColor;
    }

    public ImageBadgeView setBadgeTextColor(int badgeTextColor) {
        this.badgeTextColor = badgeTextColor;
        invalidate();
        return this;
    }

    public int getBadgeTextSize() {
        return badgeTextSize;
    }

    public ImageBadgeView setBadgeTextSize(int badgeTextSize) {
        this.badgeTextSize = badgeTextSize;
        invalidate();
        return this;
    }

    public int getBadgePadding() {
        return badgePadding;
    }

    public ImageBadgeView setBadgePadding(int badgePadding) {
        this.badgePadding = badgePadding;
        return this;
    }

    public float getBadgeRadius() {
        return badgeRadius;
    }

    public Typeface getTypefaceFont() {
        return typefaceFont;
    }

    public ImageBadgeView setTypefaceFont(Typeface font) {
        this.typefaceFont = font;
        invalidate();
        return this;
    }

    public int getTypefaceStyle() {
        return typefaceStyle;
    }

    public void setTypefaceStyle(int typefaceStyle) {
        this.typefaceStyle = typefaceStyle;
        invalidate();
    }

    public ImageBadgeView setMaxBadgeValue(int maxBadgeValue) {
        this.maxBadgeValue = maxBadgeValue;
        invalidate();
        return this;
    }

    public int getMaxBadgeValue() {
        return maxBadgeValue;
    }

    public ImageBadgeView setLimitBadgeValue(boolean badgeValueLimit) {
        limitValue = badgeValueLimit;
        invalidate();
        return this;
    }

    public void clearBadge() {
        badgeValue = 0;
        invalidate();
    }

    public void resetBadge() {
        badgeValue = 0;
        maxBadgeValue = MAX_VALUE;
        typefaceStyle = DEFAULT_FONT_STYLE;
        badgeColor = DEFAULT_BADGE_COLOR;
        badgeTextSize = DEFAULT_TEXT_SIZE;
        badgeTextColor = DEFAULT_TEXT_COLOR;
        limitValue = true;
        visibleBadge = true;
        invalidate();
    }
}
