package com.itheima.myviewpager66;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.itheima.myviewpager66.view.MyViewPager;
import com.itheima.myviewpager66.view.MyViewPager.OnPageChangeListener;
/**
 * ============================================================
 * Copyright：${TODO}有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：MyViewPager66
 * Package_Name：com.itheima.myviewpager66
 * Version：1.0
 * time：2017/2/15 16:51
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class MainActivity extends Activity {

	private MyViewPager mViewPager;

	private int[] mImageIds = new int[] { R.drawable.a1, R.drawable.a2,
			R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6 };

	private RadioGroup rgGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mViewPager = (MyViewPager) findViewById(R.id.mypager);

		// 给自定义viewpager添加6张图片
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);
			mViewPager.addView(view);
		}

		// 添加测试页面
		View testView = View.inflate(this, R.layout.list_item, null);
		mViewPager.addView(testView, 1);// 将测试页面添加到第二个位置

		// 初始化RadioButton
		rgGroup = (RadioGroup) findViewById(R.id.rg_group);
		for (int i = 0; i < mImageIds.length + 1; i++) {
			RadioButton view = new RadioButton(this);
			view.setId(i);// 以当前位置为id
			if (i == 0) {
				view.setChecked(true);// 第一个默认选中
			}

			rgGroup.addView(view);
		}

		// 设置页面切换的监听
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				int id = position;// id和position相等, 可以直接将position当做id来用
				rgGroup.check(id);
			}
		});

		// radiobutton被选中的监听
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int position = checkedId;// id和position相等, 可以直接将id当做position来用
				mViewPager.setCurrentItem(position);
			}
		});
	}
}
