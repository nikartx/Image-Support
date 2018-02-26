package ru.nikartm.support;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import ru.nikartm.support.constant.Constants;
import ru.nikartm.support.model.Badge;
import ru.nikartm.support.util.DensityUtils;

/**
 * Define and init attribute values
 * @author Ivan V on 21.02.2018.
 * @version 1.0
 */
public class AttributeController {

    private View view;
    private AttributeSet attrs;
    private Badge badge;

    public AttributeController(View view, AttributeSet attrs) {
        this.view = view;
        this.attrs = attrs;
        badge = new Badge();
        initAttr();
    }

    private void initAttr() {
        TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs, R.styleable.ImageBadgeView);
        int value = typedArray.getInt(R.styleable.ImageBadgeView_ibv_badgeValue,0);
        int maxBadgeValue = typedArray.getInt(R.styleable.ImageBadgeView_ibv_maxBadgeValue, Constants.MAX_VALUE);
        float textSize = typedArray.getDimension(R.styleable.ImageBadgeView_ibv_badgeTextSize, DensityUtils.txtPxToSp(Constants.DEFAULT_TEXT_SIZE));
        float padding = typedArray.getDimension(R.styleable.ImageBadgeView_ibv_badgePadding, DensityUtils.pxToDp(Constants.DEFAULT_BADGE_PADDING));
        float fixedBadgeRadius = typedArray.getDimension(R.styleable.ImageBadgeView_ibv_fixedBadgeRadius, DensityUtils.pxToDp(Constants.NO_INIT));
        int badgeTextStyle = typedArray.getInt(R.styleable.ImageBadgeView_ibv_badgeTextStyle, Constants.DEFAULT_FONT_STYLE);
        String fontPath = typedArray.getString(R.styleable.ImageBadgeView_ibv_badgeTextFont);
        Typeface badgeTextFont = fontPath != null ? Typeface.createFromFile(fontPath) : Constants.DEFAULT_FONT;
        Drawable badgeDrawable = typedArray.getDrawable(R.styleable.ImageBadgeView_ibv_badgeBackground);
        boolean visible = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_visibleBadge, Constants.DEFAULT_VISIBLE);
        boolean limitValue = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_badgeLimitValue, Constants.DEFAULT_LIMIT);
        boolean roundBadge = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_roundBadge, Constants.DEFAULT_ROUND);
        boolean fixedRadius = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_fixedRadius, Constants.DEFAULT_FIXED_RADIUS);
        boolean ovalAfterFirst = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_badgeOvalAfterFirst, Constants.DEFAULT_BADGE_OVAL);
        boolean showCounter = typedArray.getBoolean(R.styleable.ImageBadgeView_ibv_showCounter, Constants.DEFAULT_SHOW_COUNTER);
        int badgeColor = typedArray.getColor(R.styleable.ImageBadgeView_ibv_badgeColor, Constants.DEFAULT_BADGE_COLOR);
        int badgeTextColor = typedArray.getColor(R.styleable.ImageBadgeView_ibv_badgeTextColor, Constants.DEFAULT_TEXT_COLOR);
        int badgePosition = typedArray.getInt(R.styleable.ImageBadgeView_ibv_badgePosition, BadgePosition.TOP_RIGHT);

        badge.setValue(value)
                .setMaxValue(maxBadgeValue)
                .setBadgeTextSize(textSize)
                .setPadding(padding)
                .setFixedRadiusSize(fixedBadgeRadius)
                .setTextStyle(badgeTextStyle)
                .setBadgeTextFont(badgeTextFont)
                .setBackgroundDrawable(badgeDrawable)
                .setVisible(visible)
                .setLimitValue(limitValue)
                .setRoundBadge(roundBadge)
                .setFixedRadius(fixedRadius)
                .setOvalAfterFirst(ovalAfterFirst)
                .setShowCounter(showCounter)
                .setBadgeColor(badgeColor)
                .setBadgeTextColor(badgeTextColor)
                .setPosition(badgePosition);

        typedArray.recycle();
    }

    /**
     * @return initialized badge and counter
     */
    public Badge getBadge() {
        return badge;
    }

}
