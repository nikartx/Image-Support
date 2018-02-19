package ru.nikartm.support;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import ru.nikartm.support.util.DensityUtils;

/**
 * @author Ivan V on 18.02.2018.
 * @version 1.0
 */
public class ImageBadgeView extends android.support.v7.widget.AppCompatImageView {

    public static final int MAX_VALUE = 99;
    public static final int DEFAULT_BADGE_COLOR = Color.parseColor("maroon");
    public static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    public static final int DEFAULT_BADGE_PADDING = 0;
    public static final int DEFAULT_TEXT_SIZE = 12;
    public static final Typeface DEFAULT_FONT = Typeface.DEFAULT;
    public static final int DEFAULT_FONT_STYLE = Typeface.NORMAL;
    public static final int DEFAULT_STYLE = View.NO_ID;
    public static final boolean DEFAULT_VISIBLE = true;
    public static final boolean DEFAULT_LIMIT = true;

    private int badgeValue = 0;
    private int maxBadgeValue = MAX_VALUE;
    private float badgeRadius;
    private int badgeColor = DEFAULT_BADGE_COLOR;
    private int badgeTextColor = DEFAULT_TEXT_COLOR;
    private float badgeTextSize = DEFAULT_TEXT_SIZE;
    private float badgePadding = DEFAULT_BADGE_PADDING;
    private Typeface badgeTextFont = DEFAULT_FONT;
    private int badgeTextStyle = DEFAULT_FONT_STYLE;
    private int badgeStyle = DEFAULT_STYLE;
    private boolean visibleBadge = DEFAULT_VISIBLE;
    private boolean limitValue = DEFAULT_LIMIT;

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
        badgeTextStyle = typedArray.getInt(R.styleable.ImageBadgeView_ibv_badgeTextStyle, DEFAULT_FONT_STYLE);
        String fontPath = typedArray.getString(R.styleable.ImageBadgeView_ibv_badgeTextFont);
        badgeTextFont = fontPath != null ? Typeface.createFromFile(fontPath) : DEFAULT_FONT;
        badgeStyle = typedArray.getResourceId(R.styleable.ImageBadgeView_ibv_badgeStyle, DEFAULT_STYLE);
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

            float textWidth;
            if (limitValue && badgeValue > maxBadgeValue) {
                textWidth = paint.measureText(maxBadgeValue + "+");
            } else {
                textWidth = paint.measureText(badgeValue + "");
            }

            badgeRadius = (textWidth + badgePadding * 2) / 2;
            float x = pivotX + viewWidth / 2 - badgeRadius;
            float y = pivotY - viewHeight / 2 + badgeRadius;
            paint.setColor(badgeColor);
            canvas.drawCircle(x, y, badgeRadius, paint);

            paint.setColor(badgeTextColor);
            if (limitValue && badgeValue > maxBadgeValue) {
                canvas.drawText(maxBadgeValue + "+", x - textWidth / 2,
                        y + badgeTextSize / scale, paint);
            } else {
                canvas.drawText(badgeValue + "", x - textWidth / 2,
                        y + badgeTextSize / scale, paint);
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

    public float getBadgeTextSize() {
        return badgeTextSize;
    }

    public ImageBadgeView setBadgeTextSize(int badgeTextSize) {
        this.badgeTextSize = DensityUtils.txtPxToSp(badgeTextSize);
        invalidate();
        return this;
    }

    public float getBadgePadding() {
        return badgePadding;
    }

    public ImageBadgeView setBadgePadding(int badgePadding) {
        this.badgePadding = DensityUtils.dpToPx(badgePadding);
        return this;
    }

    public float getBadgeRadius() {
        return badgeRadius;
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

    public int getBadgeStyle() {
        return badgeStyle;
    }

    public ImageBadgeView setBadgeStyle(int badgeStyle) {
        this.badgeStyle = badgeStyle;
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
        badgeTextStyle = DEFAULT_FONT_STYLE;
        badgeColor = DEFAULT_BADGE_COLOR;
        badgeTextSize = DEFAULT_TEXT_SIZE;
        badgeTextColor = DEFAULT_TEXT_COLOR;
        limitValue = true;
        visibleBadge = true;
        invalidate();
    }
}
