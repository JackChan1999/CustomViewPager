package com.itheima.myviewpager66.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyScrollView extends ViewGroup {

	// 实现滑动效果
	// 1.手势识别器的定义
	private GestureDetector detector;

	private Scroller scroller;

	/**
	 * 带有两个参数的构造方法
	 *
	 * @param context
	 * @param attrs
	 */
	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	/**
	 * 初始化View
	 *
	 * @param context
	 */
	private void initView(Context context) {

		scroller = new Scroller(context);
		// 2.实例化手势识别器
		detector = new GestureDetector(context,
				new GestureDetector.SimpleOnGestureListener() {

					/**
					 * distanceX:在水平方向要移动的距离 distanceY:在竖直方向要移动的距离
					 */
					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
											float distanceX, float distanceY) {

						/**
						 * x:在水平方向移动指定的距离 y:在竖直方向移动指定的距离
						 */
						scrollBy((int) distanceX, 0);

						/**
						 * 移动到指定的坐标 x:移动到的X轴坐标 y:移动到的Y轴坐标
						 */
						// scrollTo(x, y);
						return true;
					}

				});
	}

	private float startX ;

	/**
	 * 当前页面的下标
	 */
	private int currentIndex;


	// 3.使用手势识别器
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);// 执行父类的onTouchEvent方法
		detector.onTouchEvent(event);
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN://按下
				//1.记录手指按下屏幕的坐标
				startX = event.getX();

				break;
			case MotionEvent.ACTION_MOVE://滑动

				break;
			case MotionEvent.ACTION_UP://离开

				//2.来到新的坐标
				float endX = event.getX();

				int tempIndex = currentIndex;

				//3.计算偏移量
				if((endX - startX) > getWidth()/2){
					//归位上一个页面
					tempIndex --;
				}else if((startX - endX) > getWidth()/2){
					//归位下一个页面
					tempIndex ++;
				}

				moveTo(tempIndex);


				break;
		}
		return true;
	}

	/**
	 * 根据下标位置，移动到指定的页面
	 * @param tempIndex 页面的下标
	 */
	private void moveTo(int tempIndex) {
		//屏蔽非法值
		if(tempIndex<0){
			tempIndex = 0;
		}
		if(tempIndex >getChildCount()-1){
			tempIndex = getChildCount()-1;
		}

		//移动到指定的坐标
		//tempIndex*getWidth():X轴的坐标
		//0:Y轴的坐标，但是是没有移动的


		//起始坐标是多少
		//		getScrollX();

		//移动的距离是多少
		float distanceX = tempIndex*getWidth() - getScrollX();
		//结束坐标
		//tempIndex*getWidth();


		//		scrollTo(tempIndex*getWidth(), 0);
		//		scroller.startScroll(getScrollX(), 0, distanceX, 0);
		scroller.startScroll(getScrollX(), 0, (int) distanceX, 0);

		invalidate();//导致onDraw();还导致computeScroll();

	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		if(scroller.computeScrollOffset()){

			//得到要移动到的坐标
			float currX = scroller.getCurrX();

			scrollTo((int)currX, 0);

			invalidate();//会导致computeScroll方法执行

		}


	}
	/**
	 * 布局显示过程中的一个方法，如果当前View是ViewGroup，有义务指定孩子的大小和位置
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 指定每一个孩子的位置和大小
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			// 指定孩子的位置和大小
			child.layout(i * getWidth(), 0, getWidth() + i * getWidth(),
					getHeight());
		}

	}

}
