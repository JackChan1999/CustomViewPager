package com.itheima.myviewpager66;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.itheima.myviewpager66.view.MyScrollView;

public class ScrollViewActivity extends Activity {

	private MyScrollView myscrollView;

	// 图片ID集合
	private int[] ids = { R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll);
		myscrollView = (MyScrollView) findViewById(R.id.myscrollView);



		for(int i=0;i<ids.length;i++){
			//对应的页面
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(ids[i]);

			//把每一个页面添加到自定义的MyScrollView 这个类总
			myscrollView.addView(imageView);
		}
	}

}
