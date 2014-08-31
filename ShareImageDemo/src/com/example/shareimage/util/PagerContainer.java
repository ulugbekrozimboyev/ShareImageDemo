package com.example.shareimage.util;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class PagerContainer extends FrameLayout implements ViewPager.OnPageChangeListener{

	private ViewPager mPager;
	boolean mNeedsRedraw = false;

	public PagerContainer(Context context) {
		super(context);
		init();
	}

	public PagerContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PagerContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		setClipChildren(false);

		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}
	@Override
	protected void onFinishInflate() {
		try {
			mPager = (ViewPager) getChildAt(0);
			mPager.setOnPageChangeListener(this);
		} catch (Exception e) {
			throw new IllegalStateException("The root child of PagerContainer must be a ViewPager");
		}
	}

	public ViewPager getViewPager() {
		return mPager;
	}

	private Point mCenter = new Point();
	private Point mInitialTouch = new Point();

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mCenter.x = w / 2;
		mCenter.y = h / 2;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mInitialTouch.x = (int)ev.getX();
			mInitialTouch.y = (int)ev.getY();
		default:
			ev.offsetLocation(mCenter.x - mInitialTouch.x, mCenter.y - mInitialTouch.y);
			break;
		}

		return mPager.dispatchTouchEvent(ev);
	}
	@Override
	public void onPageScrollStateChanged(int state) {
		mNeedsRedraw = (state != ViewPager.SCROLL_STATE_IDLE);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		if (mNeedsRedraw) invalidate();
	}

	@Override
	public void onPageSelected(int position) { 
	}
}
