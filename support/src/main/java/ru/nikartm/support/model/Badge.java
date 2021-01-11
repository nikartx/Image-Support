package ru.nikartm.support.model;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;

/**
 * @author Ivan V on 21.02.2018.
 * @version 1.0
 */
public class Badge {

    private int value;
    private int maxValue;
    private float radius;
    private float fixedRadiusSize;
    private int badgeColor;
    private int badgeTextColor;
    private float badgeTextSize;
    private float padding;
    private Typeface badgeTextFont;
    private int textStyle;
    private int badgeBackground;
    private boolean visible;
    private boolean limitValue;
    private boolean roundBadge;
    private boolean fixedRadius;
    private boolean ovalAfterFirst;
    private boolean showCounter;
    private Drawable backgroundDrawable;
    private float textWidth;
    private int position;

    public Badge() {
    }

    public void clearValue() {
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public Badge setValue(int value) {
        this.value = value;
        return this;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public Badge setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public float getRadius() {
        return radius;
    }

    public Badge setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public float getFixedRadiusSize() {
        return fixedRadiusSize;
    }

    public Badge setFixedRadiusSize(float fixedRadiusSize) {
        this.fixedRadiusSize = fixedRadiusSize;
        return this;
    }

    public int getBadgeColor() {
        return badgeColor;
    }

    public Badge setBadgeColor(int badgeColor) {
        this.badgeColor = badgeColor;
        return this;
    }

    public int getBadgeTextColor() {
        return badgeTextColor;
    }

    public Badge setBadgeTextColor(int badgeTextColor) {
        this.badgeTextColor = badgeTextColor;
        return this;
    }

    public float getBadgeTextSize() {
        return badgeTextSize;
    }

    public Badge setBadgeTextSize(float badgeTextSize) {
        this.badgeTextSize = badgeTextSize;
        return this;
    }

    public float getPadding() {
        return padding;
    }

    public Badge setPadding(float padding) {
        this.padding = padding;
        return this;
    }

    public Typeface getBadgeTextFont() {
        return badgeTextFont;
    }

    public Badge setBadgeTextFont(Typeface badgeTextFont) {
        this.badgeTextFont = badgeTextFont;
        return this;
    }

    public int getTextStyle() {
        return textStyle;
    }

    public Badge setTextStyle(int textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public int getBadgeBackground() {
        return badgeBackground;
    }

    public Badge setBadgeBackground(int badgeBackground) {
        this.badgeBackground = badgeBackground;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public Badge setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isLimitValue() {
        return limitValue;
    }

    public Badge setLimitValue(boolean limitValue) {
        this.limitValue = limitValue;
        return this;
    }

    public boolean isRoundBadge() {
        return roundBadge;
    }

    public Badge setRoundBadge(boolean roundBadge) {
        this.roundBadge = roundBadge;
        return this;
    }

    public boolean isFixedRadius() {
        return fixedRadius;
    }

    public Badge setFixedRadius(boolean fixedRadius) {
        this.fixedRadius = fixedRadius;
        return this;
    }

    public boolean isOvalAfterFirst() {
        return ovalAfterFirst;
    }

    public Badge setOvalAfterFirst(boolean ovalAfterFirst) {
        this.ovalAfterFirst = ovalAfterFirst;
        return this;
    }

    public boolean isShowCounter() {
        return showCounter;
    }

    public Badge setShowCounter(boolean showCounter) {
        this.showCounter = showCounter;
        return this;
    }

    @Nullable
    public Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public Badge setBackgroundDrawable(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
        return this;
    }

    public float getTextWidth() {
        return textWidth;
    }

    public Badge setTextWidth(float textWidth) {
        this.textWidth = textWidth;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public Badge setPosition(int position) {
        this.position = position;
        return this;
    }

}
