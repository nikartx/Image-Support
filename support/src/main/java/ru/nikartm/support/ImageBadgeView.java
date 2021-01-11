package ru.nikartm.support;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;
import ru.nikartm.support.listener.OnBadgeCountChangeListener;
import ru.nikartm.support.util.DensityUtils;

/**
 * Image View with a badge like notification count
 * @author Ivan V on 18.02.2018.
 * @version 1.0
 */
public class ImageBadgeView extends AppCompatImageView {

    private DrawerManager manager;
    private OnBadgeCountChangeListener onBadgeCountChangeListener;

    public ImageBadgeView(Context context) {
        super(context);
        initAttr(null);
    }

    public ImageBadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    public ImageBadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        manager = new DrawerManager(ImageBadgeView.this, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        manager.drawBadge(canvas);
    }

    /**
     * Register a callback to be invoked when counter of a badge changed.
     * @param listener the callback that will run.
     */
    public void setOnBadgeCountChangeListener(OnBadgeCountChangeListener listener) {
        onBadgeCountChangeListener = listener;
    }

    /**
     * @return the badge counter value
     */
    public int getBadgeValue() {
        return manager.getBadge().getValue();
    }

    /**
     * Set a badge counter value and add change listener if it registered
     * @param badgeValue new counter value
     */
    public ImageBadgeView setBadgeValue(int badgeValue) {
        manager.getBadge().setValue(badgeValue);
        if (onBadgeCountChangeListener != null) {
            onBadgeCountChangeListener.onCountChange(badgeValue);
        }
        invalidate();
        return this;
    }

    /**
     * Get the maximum value of a badge (by default setting 99)
     */
    public int getMaxBadgeValue() {
        return manager.getBadge().getMaxValue();
    }

    /**
     * Set the maximum value of a badge (by default setting 99)
     * @param maxBadgeValue new maximum value for counter
     */
    public ImageBadgeView setMaxBadgeValue(int maxBadgeValue) {
        manager.getBadge().setMaxValue(maxBadgeValue);
        invalidate();
        return this;
    }

    /**
     * @return state of a badge visible
     */
    public boolean isVisibleBadge() {
        return manager.getBadge().isVisible();
    }

    /**
     * Set a badge visible state
     * @param  visible If state true a badge will be visible
     */
    public ImageBadgeView visibleBadge(boolean visible) {
        manager.getBadge().setVisible(visible);
        invalidate();
        return this;
    }

    /**
     * @return state of a badge vertical stretched
     */
    public boolean isRoundBadge() {
        return manager.getBadge().isRoundBadge();
    }

    /**
     * Define whether a badge can be stretched vertically
     * @param roundBadge If param is true, a badge can be stretched vertically
     */
    public ImageBadgeView setRoundBadge(boolean roundBadge) {
        manager.getBadge().setRoundBadge(roundBadge);
        invalidate();
        return this;
    }

    /**
     * @return state of a badge fixed radius by width
     */
    public boolean isFixedRadius() {
        return manager.getBadge().isFixedRadius();
    }

    /**
     * Define whether a badge can be with fixed radius by width.
     * Badge can have only circle or square form.
     * @param fixedRadius If param is true, a badge radius fixed
     */
    public ImageBadgeView setFixedRadius(boolean fixedRadius) {
        manager.getBadge().setFixedRadius(fixedRadius);
        invalidate();
        return this;
    }

    /**
     * State of a badge whether a badge can be oval form after first value number of counter
     * @return state of a badge
     */
    public boolean isBadgeOvalAfterFirst() {
        return manager.getBadge().isOvalAfterFirst();
    }

    /**
     * Define whether a badge can be oval form after first value number of counter
     * Please use this method only for custom drawable badge background. See {@link #setBadgeBackground(Drawable)}.
     * @param badgeOvalAfterFirst If param is true, a badge can be oval form after first value
     */
    public ImageBadgeView setBadgeOvalAfterFirst(boolean badgeOvalAfterFirst) {
        manager.getBadge().setOvalAfterFirst(badgeOvalAfterFirst);
        invalidate();
        return this;
    }

    /**
     * @return State whether the counter is showing
     */
    public boolean isShowCounter() {
        return manager.getBadge().isShowCounter();
    }

    /**
     * Specify whether the counter can be showing on a badge
     * @param showCounter Specify whether the counter is shown
     */
    public ImageBadgeView setShowCounter(boolean showCounter) {
        manager.getBadge().setShowCounter(showCounter);
        invalidate();
        return this;
    }

    /**
     * State of a badge has limit counter
     * @return state of a badge has limit
     */
    public boolean isLimitValue() {
        return manager.getBadge().isLimitValue();
    }

    /**
     * Define whether a badge counter can have limit
     * @param badgeValueLimit If param is true, after max value (default 99) a badge will have counter 99+
     * Otherwise a badge will show the current value, e.g 101
     */
    public ImageBadgeView setLimitBadgeValue(boolean badgeValueLimit) {
        manager.getBadge().setLimitValue(badgeValueLimit);
        invalidate();
        return this;
    }

    /**
     * Get the current badge background color
     */
    public int getBadgeColor() {
        return manager.getBadge().getBadgeColor();
    }

    /**
     * Set the background color for a badge
     * @param badgeColor the color of the background
     */
    public ImageBadgeView setBadgeColor(int badgeColor) {
        manager.getBadge().setBadgeColor(badgeColor);
        invalidate();
        return this;
    }

    /**
     * Get the current text color of a badge
     */
    public int getBadgeTextColor() {
        return manager.getBadge().getBadgeTextColor();
    }

    /**
     * Set the text color for a badge
     * @param badgeTextColor the color of a badge text
     */
    public ImageBadgeView setBadgeTextColor(int badgeTextColor) {
        manager.getBadge().setBadgeTextColor(badgeTextColor);
        invalidate();
        return this;
    }

    /**
     * Get the current text size of a badge
     */
    public float getBadgeTextSize() {
        return manager.getBadge().getBadgeTextSize();
    }

    /**
     * Set the text size for a badge
     * @param badgeTextSize the size of a badge text
     */
    public ImageBadgeView setBadgeTextSize(float badgeTextSize) {
        manager.getBadge().setBadgeTextSize(DensityUtils.dpToPx(badgeTextSize));
        invalidate();
        return this;
    }

    /**
     * Get padding of a badge
     */
    public float getBadgePadding() {
        return manager.getBadge().getPadding();
    }

    /**
     * Set a badge padding
     * @param badgePadding the badge padding
     */
    public ImageBadgeView setBadgePadding(int badgePadding) {
        manager.getBadge().setPadding(DensityUtils.txtPxToSp(badgePadding));
        invalidate();
        return this;
    }

    /**
     * Get a badge radius
     */
    public float getBadgeRadius() {
        return manager.getBadge().getRadius();
    }

    /**
     * Set the badge fixed radius. Radius will not respond to changes padding or width of text.
     * @param fixedBadgeRadius badge fixed radius value
     */
    public ImageBadgeView setFixedBadgeRadius(float fixedBadgeRadius) {
        manager.getBadge().setFixedRadiusSize(fixedBadgeRadius);
        invalidate();
        return this;
    }

    /**
     * Get the current typeface {@link Typeface} of a badge
     */
    public Typeface getBadgeTextFont() {
        return manager.getBadge().getBadgeTextFont();
    }

    /**
     * Set the typeface for a badge text
     * @param font Font for a badge text
     */
    public ImageBadgeView setBadgeTextFont(Typeface font) {
        manager.getBadge().setBadgeTextFont(font);
        invalidate();
        return this;
    }

    /**
     * Get the style of the badge text. Matches the {@link Typeface} text style
     */
    public int getBadgeTextStyle() {
        return manager.getBadge().getTextStyle();
    }

    /**
     * Set the style of the badge text. Matches the {@link Typeface} text style
     * @param badgeTextStyle Can be normal, bold, italic, bold_italic
     */
    public ImageBadgeView setBadgeTextStyle(int badgeTextStyle) {
        manager.getBadge().setTextStyle(badgeTextStyle);
        invalidate();
        return this;
    }

    /**
     * Get the background of a badge.
     */
    public int getBadgeBackground() {
        return manager.getBadge().getBadgeBackground();
    }

    /**
     * Get the background of a badge {@link Drawable} or null.
     */
    public Drawable getBadgeBackgroundDrawable() {
        return manager.getBadge().getBackgroundDrawable();
    }

    /**
     * Set the custom background of a badge
     * @param badgeBackground the {@link Drawable} background of a badge from resources
     */
    public ImageBadgeView setBadgeBackground(Drawable badgeBackground) {
        manager.getBadge().setBackgroundDrawable(badgeBackground);
        invalidate();
        return this;
    }

    /**
     * Clear counter badge value
     */
    public void clearBadge() {
        manager.getBadge().clearValue();
        invalidate();
    }

    /**
     * Get position of a badge {@link BadgePosition}.
     * @return {@link BadgePosition} position on ImageView by index
     */
    public int getBadgePosition() {
        return manager.getBadge().getPosition();
    }

    /**
     * Set badge position {@link BadgePosition} on ImageView
     * @param position on this view {@link BadgePosition} top_left, top_right, bottom_left, bottom_right
     */
    public ImageBadgeView setBadgePosition(int position) {
        manager.getBadge().setPosition(position);
        invalidate();
        return this;
    }
}
