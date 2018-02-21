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
    public static final boolean DEFAULT_ROUND = true;
    public static final boolean DEFAULT_FIXED_RADIUS = false;
    public static final boolean DEFAULT_BADGE_OVAL = false;
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
    private boolean roundBadge = DEFAULT_ROUND;
    private boolean fixedRadius = DEFAULT_FIXED_RADIUS;
    private boolean badgeOvalAfterFirst = DEFAULT_BADGE_OVAL;
    private Drawable badgeDrawable;
    private float textWidth;

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
        initAttr(attrs);
    }

    public ImageBadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
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
        roundBadge = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_roundBadge, DEFAULT_ROUND);
        fixedRadius = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_fixedRadius, DEFAULT_FIXED_RADIUS);
        badgeOvalAfterFirst = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_badgeOvalAfterFirst, DEFAULT_BADGE_OVAL);
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
                paint.setAntiAlias(true);
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

            textWidth = getTextWidth();
            computeRadius();

            float dx = pivotX + viewWidth / 2 - badgeRadius;
            float dy = pivotY - viewHeight / 2 + badgeRadius;

            // Draw a badge
            paint.setColor(badgeColor);
            if (badgeDrawable != null) {
                dy = pivotY - viewHeight / 2 + getBadgeHeight() / 2;
                int valueHeight = (int) getBadgeHeight();
                int valueWidth = (int) getBadgeWidth();
                if (fixedRadius) {
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
                } else if (roundBadge && valueWidth < valueHeight) {
                    dx = pivotX + viewWidth / 2 - getBadgeHeight() / 2;
                    valueWidth = valueHeight;
                }
                badgeDrawable.setBounds(0, 0, valueWidth, valueHeight);
                canvas.save();
                canvas.translate(dx - valueWidth / 2, dy - valueHeight / 2);
                badgeDrawable.draw(canvas);
                canvas.restore();
            } else {
                canvas.drawCircle(dx, dy, badgeRadius, paint);
            }

            // Draw text
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

    private float getBadgeHeight() {
        return badgeTextSize + badgePadding * 2f;
    }

    private float getBadgeWidth() {
        float width;
        if (badgeDrawable != null && badgeOvalAfterFirst && !fixedRadius) {
            width = textWidth + badgePadding * 4f;
        } else {
            width = textWidth + badgePadding * 2f;
        }
        return width;
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

    private void computeRadius() {
        if (fixedBadgeRadius != NO_INIT) {
            badgeRadius = fixedBadgeRadius;
        } else {
            badgeRadius = getBadgeWidth() / 2;
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

    public boolean isVisibleBadge() {
        return visibleBadge;
    }

    public ImageBadgeView visibleBadge(boolean visible) {
        visibleBadge = visible;
        invalidate();
        return this;
    }

    public boolean isRoundBadge() {
        return roundBadge;
    }

    public ImageBadgeView setRoundBadge(boolean roundBadge) {
        this.roundBadge = roundBadge;
        invalidate();
        return this;
    }

    public boolean isFixedRadius() {
        return fixedRadius;
    }

    public ImageBadgeView setFixedRadius(boolean fixedRadius) {
        this.fixedRadius = fixedRadius;
        invalidate();
        return this;
    }

    public boolean isBadgeOvalAfterFirst() {
        return badgeOvalAfterFirst;
    }

    public ImageBadgeView setBadgeOvalAfterFirst(boolean badgeOvalAfterFirst) {
        this.badgeOvalAfterFirst = badgeOvalAfterFirst;
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
        invalidate();
        return this;
    }

    public float getBadgeRadius() {
        return badgeRadius;
    }

    public ImageBadgeView setFixedBadgeRadius(float fixedBadgeRadius) {
        this.fixedBadgeRadius = fixedBadgeRadius;
        invalidate();
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

    public ImageBadgeView setBadgeTextStyle(int badgeTextStyle) {
        this.badgeTextStyle = badgeTextStyle;
        invalidate();
        return this;
    }

    public int getBadgeBackground() {
        return badgeBackground;
    }

    public Drawable getBadgeBackgroundDrawable() {
        return badgeDrawable;
    }

    public ImageBadgeView setBadgeBackground(Drawable badgeBackground) {
        this.badgeDrawable = badgeBackground;
        invalidate();
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

    public boolean isLimitValue() {
        return limitValue;
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
        limitValue = DEFAULT_LIMIT;
        visibleBadge = DEFAULT_VISIBLE;
        roundBadge = DEFAULT_ROUND;
        fixedRadius = DEFAULT_FIXED_RADIUS;
        badgeOvalAfterFirst = DEFAULT_BADGE_OVAL;
        invalidate();
    }
}
