package com.example.bookinglaneadmin.customView;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * A Drawable that outsets another Drawable by a specified distance.
 * This is useful for compound Drawables that need more control over
 * padding.
 */
public class OutsetDrawable extends Drawable implements Drawable.Callback {
    private final Rect mTmpRect = new Rect();
    // Most of this is copied from ScaleDrawable.
    private OutsetState mOutsetState;
    private boolean mMutated;

    /*package*/ OutsetDrawable() {
        this(null, null);
    }

    public OutsetDrawable(Drawable drawable, int outset) {
        this(drawable, outset, outset, outset, outset);
    }

    public OutsetDrawable(Drawable drawable, int outsetLeft, int outsetTop,
                          int outsetRight, int outsetBottom) {
        this(null, null);

        mOutsetState.mDrawable = drawable;
        mOutsetState.mOutsetLeft = outsetLeft;
        mOutsetState.mOutsetTop = outsetTop;
        mOutsetState.mOutsetRight = outsetRight;
        mOutsetState.mOutsetBottom = outsetBottom;

        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    // overrides from Drawable.Callback

    private OutsetDrawable(OutsetState state, Resources res) {
        mOutsetState = new OutsetState(state, this, res);
    }

    public void invalidateDrawable(Drawable who) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    // overrides from Drawable

    public void unscheduleDrawable(Drawable who, Runnable what) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        mOutsetState.mDrawable.draw(canvas);
    }

    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations()
                | mOutsetState.mChangingConfigurations
                | mOutsetState.mDrawable.getChangingConfigurations();
    }

    @Override
    public boolean getPadding(Rect padding) {
        return mOutsetState.mDrawable.getPadding(padding);
    }

    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        mOutsetState.mDrawable.setVisible(visible, restart);
        return super.setVisible(visible, restart);
    }

    @Override
    public void setAlpha(int alpha) {
        mOutsetState.mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mOutsetState.mDrawable.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return mOutsetState.mDrawable.getOpacity();
    }

    @Override
    public boolean isStateful() {
        return mOutsetState.mDrawable.isStateful();
    }

    @Override
    protected boolean onStateChange(int[] state) {
        boolean changed = mOutsetState.mDrawable.setState(state);
        onBoundsChange(getBounds());
        return changed;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        final Rect r = mTmpRect;
        r.set(bounds);

        r.left += mOutsetState.mOutsetLeft;
        r.top += mOutsetState.mOutsetTop;
        r.right -= mOutsetState.mOutsetRight;
        r.bottom -= mOutsetState.mOutsetBottom;

        mOutsetState.mDrawable.setBounds(r.left, r.top, r.right, r.bottom);
    }

    @Override
    public int getIntrinsicWidth() {
        return mOutsetState.mDrawable.getIntrinsicWidth() + mOutsetState.mOutsetLeft + mOutsetState.mOutsetRight;
    }

    @Override
    public int getIntrinsicHeight() {
        return mOutsetState.mDrawable.getIntrinsicHeight() + mOutsetState.mOutsetTop + mOutsetState.mOutsetBottom;
    }

    @Override
    public ConstantState getConstantState() {
        if (mOutsetState.canConstantState()) {
            mOutsetState.mChangingConfigurations = getChangingConfigurations();
            return mOutsetState;
        }
        return null;
    }

    @Override
    public Drawable mutate() {
        if (!mMutated && super.mutate() == this) {
            mOutsetState.mDrawable.mutate();
            mMutated = true;
        }
        return this;
    }

    final static class OutsetState extends ConstantState {
        Drawable mDrawable;
        int mChangingConfigurations;

        int mOutsetLeft;
        int mOutsetTop;
        int mOutsetRight;
        int mOutsetBottom;

        boolean mCheckedConstantState;
        boolean mCanConstantState;

        OutsetState(OutsetState orig, OutsetDrawable owner, Resources res) {
            if (orig != null) {
                if (res != null) {
                    mDrawable = orig.mDrawable.getConstantState().newDrawable(res);
                } else {
                    mDrawable = orig.mDrawable.getConstantState().newDrawable();
                }
                mDrawable.setCallback(owner);
                // mDrawable.setLayoutDirection(orig.mDrawable.getLayoutDirection());
                mOutsetLeft = orig.mOutsetLeft;
                mOutsetTop = orig.mOutsetTop;
                mOutsetRight = orig.mOutsetRight;
                mOutsetBottom = orig.mOutsetBottom;
                mCheckedConstantState = mCanConstantState = true;
            }
        }

        @Override
        public Drawable newDrawable() {
            return new OutsetDrawable(this, null);
        }

        @Override
        public Drawable newDrawable(Resources res) {
            return new OutsetDrawable(this, res);
        }

        @Override
        public int getChangingConfigurations() {
            return mChangingConfigurations;
        }

        boolean canConstantState() {
            if (!mCheckedConstantState) {
                mCanConstantState = mDrawable.getConstantState() != null;
                mCheckedConstantState = true;
            }

            return mCanConstantState;
        }
    }
}
