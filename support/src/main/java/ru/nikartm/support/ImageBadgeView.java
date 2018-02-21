package ru.nikartm.support;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import ru.nikartm.support.util.DensityUtils;

/**
 * Image View with a badge like notification count
 * @author Ivan V on 18.02.2018.
 * @version 1.0
 */
public class ImageBadgeView extends android.support.v7.widget.AppCompatImageView {

    public static final int MAX_VALUE = 99;
    public static final int DEFAULT_BADGE_COLOR = Color.parseColor("red");
    public static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    public static final int DEFAULT_BADGE_PADDING = 0;
    public static final int DEFAULT_TEXT_SIZE = 12;
    public static final Typeface DEFAULT_FONT = Typeface.DEFAULT;
    public static final int DEFAULT_FONT_STYLE = Typeface.NORMAL;
    public static final int DEFAULT_STYLE = View.NO_ID;
    public static final boolean DEFAULT_VISIBLE = true;
    public static final boolean DEFAULT_LIMIT = true;
    public static final float NO_INIT = -1f;

    private int badgeValue = 0;
    private int maxBadgeValue = MAX_VALUE;
    private float badgeRadius;
    private float fixedBadgeRadius = NO_INIT;
    private int badgeColor = DEFAULT_BADGE_COLOR;
    private int badgeTextColor = DEFAULT_TEXT_COLOR;
    private float badgeTextSize = DEFAULT_TEXT_SIZE;
    private float badgePadding = DEFAULT_BADGE_PADDING;
    private Typeface badgeTextFont = DEFAULT_FONT;
    private int badgeTextStyle = DEFAULT_FONT_STYLE;
    private int badgeBackground = DEFAULT_STYLE;
    private boolean visibleBadge = DEFAULT_VISIBLE;
    private boolean limitValue = DEFAULT_LIMIT;
    private Drawable badgeDrawable;

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
        init(attrs);
    }

    public ImageBadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageBadgeView);
        badgeValue = typedArray.getInt(R.styleable.ImageBadgeView_ibv_badgeValue,0);
        maxBadgeValue = typedArray.getInt(R.styleable.ImageBadgeView_ibv_maxBadgeValue, MAX_VALUE);
        badgeTextSize = typedArray.getDimension(R.styleable.ImageBadgeView_ibv_badgeTextSize, DensityUtils.txtPxToSp(DEFAULT_TEXT_SIZE));
        badgePadding = typedArray.getDimension(R.styleable.ImageBadgeView_ibv_badgePadding, DensityUtils.pxToDp(DEFAULT_BADGE_PADDING));
        fixedBadgeRadius = typedArray.getDimension(R.styleable.ImageBadgeView_ibv_fixedBadgeRadius, DensityUtils.pxToDp(NO_INIT));
        badgeTextStyle = typedArray.getInt(R.styleable.ImageBadgeView_ibv_badgeTextStyle, DEFAULT_FONT_STYLE);
        String fontPath = typedArray.getString(R.styleable.ImageBadgeView_ibv_badgeTextFont);
        badgeTextFont = fontPath != null ? Typeface.createFromFile(fontPath) : DEFAULT_FONT;
        badgeDrawable = typedArray.getDrawable(R.styleable.ImageBadgeView_ibv_badgeBackground);
        visibleBadge = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_visibleBadge, DEFAULT_VISIBLE);
        limitValue = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_badgeLimitValue, DEFAULT_LIMIT);
        badgeColor = typedArray.getColor(R.styleable.ImageBadgeView_ibv_badgeColor, DEFAULT_BADGE_COLOR);
        badgeTextColor = typedArray.getColor(R.styleable.ImageBadgeView_ibv_badgeTextColor, DEFAULT_TEXT_COLOR);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (visibleBadge && badgeValue > 0) {
            if (paint == null) {
                paint = new Paint();
                Resources resources = context.getResources();
                scale = resources.getDisplayMetrics().density;
                Typeface typeface = Typeface.create(badgeTextFont, badgeTextStyle);
                paint.setTypeface(typeface);
                paint.setTextSize(badgeTextSize);
            }
            float pivotX = getPivotX();
            float pivotY = getPivotY();
            int viewHeight = getMeasuredHeight();
            int viewWidth = getMeasuredWidth();

            float textWidth = getTextWidth();
            computeRadius(textWidth);

            float dx = pivotX + viewWidth / 2 - badgeRadius;
            float dy = pivotY - viewHeight / 2 + badgeRadius;
            paint.setColor(badgeColor);
            if (badgeDrawable != null) {
                dy = pivotY - viewHeight / 2 + (badgeTextSize + badgePadding * 2) / 2;
                int valueWidth = (int) (textWidth + badgePadding * 2);
                int valueHeight = (int) (badgeTextSize + badgePadding * 2);
                badgeDrawable.setBounds(0, 0, valueWidth, valueHeight);
                canvas.save();
                canvas.translate(dx - valueWidth / 2, dy - valueHeight / 2);
                badgeDrawable.draw(canvas);
                canvas.restore();
            } else {
                canvas.drawCircle(dx, dy, badgeRadius, paint);
            }

            paint.setColor(badgeTextColor);
            if (limitValue && badgeValue > maxBadgeValue) {
                canvas.drawText(String.valueOf(maxBadgeValue).concat("+"), dx - textWidth / 2,
                        dy + badgeTextSize / scale, paint);
            } else {
                canvas.drawText(String.valueOf(badgeValue), dx - textWidth / 2,
                        dy + badgeTextSize / scale, paint);
            }
        }
    }

    private float getTextWidth() {
        float textWidth;
        if (limitValue && badgeValue > maxBadgeValue) {
            textWidth = paint.measureText(String.valueOf(maxBadgeValue).concat("+"));
        } else {
            textWidth = paint.measureText(String.valueOf(badgeValue));
        }
        return textWidth;
    }

    private void computeRadius(float textWidth) {
        if (fixedBadgeRadius != NO_INIT) {
            badgeRadius = fixedBadgeRadius;
        } else {
            badgeRadius = (textWidth + badgePadding * 2) / 2;
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

    public float getBadgeTextSize() {
        return badgeTextSize;
    }

    public ImageBadgeView setBadgeTextSize(int badgeTextSize) {
        this.badgeTextSize = DensityUtils.dpToPx(badgeTextSize);
        invalidate();
        return this;
    }

    public float getBadgePadding() {
        return badgePadding;
    }

    public ImageBadgeView setBadgePadding(int badgePadding) {
        this.badgePadding = DensityUtils.txtPxToSp(badgePadding);
        return this;
    }

    public float getBadgeRadius() {
        return badgeRadius;
    }

    public ImageBadgeView setFixedBadgeRadius(float fixedBadgeRadius) {
        this.fixedBadgeRadius = fixedBadgeRadius;
        return this;
    }

    public Typeface getBadgeTextFont() {
        return badgeTextFont;
    }

    public ImageBadgeView setBadgeTextFont(Typeface font) {
        this.badgeTextFont = font;
        invalidate();
        return this;
    }

    public int getBadgeTextStyle() {
        return badgeTextStyle;
    }

    public void setBadgeTextStyle(int badgeTextStyle) {
        this.badgeTextStyle = badgeTextStyle;
        invalidate();
    }

    public int getBadgeBackground() {
        return badgeBackground;
    }

    public ImageBadgeView setBadgeBackground(int badgeBackground) {
        this.badgeBackground = badgeBackground;
        return this;
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
        fixedBadgeRadius = NO_INIT;
        badgeTextStyle = DEFAULT_FONT_STYLE;
        badgeColor = DEFAULT_BADGE_COLOR;
        badgeTextSize = DEFAULT_TEXT_SIZE;
        badgeTextColor = DEFAULT_TEXT_COLOR;
        badgeDrawable = null;
        limitValue = true;
        visibleBadge = true;
        invalidate();
    }
}
