package com.example.shareimage.util;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shareimage.FullImageActivity;

public class MyPagerAdapter extends PagerAdapter{

	private Context context;
	private ArrayList<String> images;
	private ViewGroup container; 
			
	public MyPagerAdapter(Context context, ArrayList<String> images) {
		this.context = context;
		this.images = images;
	}
	
	@Override
	public Object instantiateItem(final ViewGroup container, final int position) {
		ImageView view = new ImageView(context);
		File imageFile = new File(images.get(position));
		if (imageFile.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			view.setImageBitmap(bitmap);
		}
		else {
		}
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent fullImageIntent = new Intent(context.getApplicationContext() , FullImageActivity.class);
				fullImageIntent.putExtra("imagePath", images.get(position));
				context.startActivity(fullImageIntent);
			}
		});

		container.addView(view);
		
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return (view == object);
	}
	
	public void addImagePath(String imagePath){
		images.add(imagePath);
		
	}
}
