package ru.nikartm.support;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import ru.nikartm.support.listener.OnBadgeCountChangeListener;
import ru.nikartm.support.util.DensityUtils;

/**
 * Image View with a badge like notification count
 * @author Ivan V on 18.02.2018.
 * @version 1.0
 */
public class ImageBadgeView extends android.support.v7.widget.AppCompatImageView {

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
        Log.d("1111111", "Badge: " + manager.getBadge());
    }

    public void setOnBadgeCountChangeListener(OnBadgeCountChangeListener listener) {
        onBadgeCountChangeListener = listener;
    }

    public int getBadgeValue() {
        return manager.getBadge().getValue();
    }

    public ImageBadgeView setBadgeValue(int badgeValue) {
        manager.getBadge().setValue(badgeValue);
        if (onBadgeCountChangeListener != null) {
            onBadgeCountChangeListener.onCountChange(badgeValue);
        }
        invalidate();
        return this;
    }

    public boolean isVisibleBadge() {
        return manager.getBadge().isVisible();
    }

    public ImageBadgeView visibleBadge(boolean visible) {
        manager.getBadge().setVisible(visible);
        invalidate();
        return this;
    }

    public boolean isRoundBadge() {
        return manager.getBadge().isRoundBadge();
    }

    public ImageBadgeView setRoundBadge(boolean roundBadge) {
        manager.getBadge().setRoundBadge(roundBadge);
        invalidate();
        return this;
    }

    public boolean isFixedRadius() {
        return manager.getBadge().isFixedRadius();
    }

    public ImageBadgeView setFixedRadius(boolean fixedRadius) {
        manager.getBadge().setFixedRadius(fixedRadius);
        invalidate();
        return this;
    }

    public boolean isBadgeOvalAfterFirst() {
        return manager.getBadge().isOvalAfterFirst();
    }

    public ImageBadgeView setBadgeOvalAfterFirst(boolean badgeOvalAfterFirst) {
        manager.getBadge().setOvalAfterFirst(badgeOvalAfterFirst);
        invalidate();
        return this;
    }

    public boolean isLimitValue() {
        return manager.getBadge().isLimitValue();
    }

    public ImageBadgeView setLimitBadgeValue(boolean badgeValueLimit) {
        manager.getBadge().setLimitValue(badgeValueLimit);
        invalidate();
        return this;
    }

    public int getBadgeColor() {
        return manager.getBadge().getBadgeColor();
    }

    public ImageBadgeView setBadgeColor(int badgeColor) {
        manager.getBadge().setBadgeColor(badgeColor);
        invalidate();
        return this;
    }

    public int getBadgeTextColor() {
        return manager.getBadge().getBadgeTextColor();
    }

    public ImageBadgeView setBadgeTextColor(int badgeTextColor) {
        manager.getBadge().setBadgeTextColor(badgeTextColor);
        invalidate();
        return this;
    }

    public float getBadgeTextSize() {
        return manager.getBadge().getBadgeTextSize();
    }

    public ImageBadgeView setBadgeTextSize(float badgeTextSize) {
        manager.getBadge().setBadgeTextSize(DensityUtils.dpToPx(badgeTextSize));
        invalidate();
        return this;
    }

    public float getBadgePadding() {
        return manager.getBadge().getPadding();
    }

    public ImageBadgeView setBadgePadding(int badgePadding) {
        manager.getBadge().setPadding(DensityUtils.txtPxToSp(badgePadding));
        invalidate();
        return this;
    }

    public float getBadgeRadius() {
        return manager.getBadge().getRadius();
    }

    public ImageBadgeView setFixedBadgeRadius(float fixedBadgeRadius) {
        manager.getBadge().setFixedRadiusSize(fixedBadgeRadius);
        invalidate();
        return this;
    }

    public Typeface getBadgeTextFont() {
        return manager.getBadge().getBadgeTextFont();
    }

    public ImageBadgeView setBadgeTextFont(Typeface font) {
        manager.getBadge().setBadgeTextFont(font);
        invalidate();
        return this;
    }

    public int getBadgeTextStyle() {
        return manager.getBadge().getTextStyle();
    }

    public ImageBadgeView setBadgeTextStyle(int badgeTextStyle) {
        manager.getBadge().setTextStyle(badgeTextStyle);
        invalidate();
        return this;
    }

    public int getBadgeBackground() {
        return manager.getBadge().getBadgeBackground();
    }

    public Drawable getBadgeBackgroundDrawable() {
        return manager.getBadge().getBackgroundDrawable();
    }

    public ImageBadgeView setBadgeBackground(Drawable badgeBackground) {
        manager.getBadge().setBackgroundDrawable(badgeBackground);
        invalidate();
        return this;
    }

    public ImageBadgeView setMaxBadgeValue(int maxBadgeValue) {
        manager.getBadge().setMaxValue(maxBadgeValue);
        invalidate();
        return this;
    }

    public int getMaxBadgeValue() {
        return manager.getBadge().getMaxValue();
    }

    public void clearBadge() {
        manager.getBadge().clearValue();
        invalidate();
    }
}
