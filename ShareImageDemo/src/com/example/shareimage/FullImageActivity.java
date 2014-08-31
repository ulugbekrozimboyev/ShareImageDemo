package com.example.shareimage;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class FullImageActivity extends Activity{
	
	private String resImagePath;
	// TODO make title hide and show when image clicked
	private boolean isVisibleMenu = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_image_screen);
		Bundle extra = getIntent().getExtras();
		if(extra == null){
			return;
		}
		resImagePath = extra.getString("imagePath");
		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		File imageFile = new File(resImagePath);
		if (imageFile.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			imageView.setImageBitmap(bitmap);
		}

		
		ImageView shareImageView = (ImageView) findViewById(R.id.share_image);
		shareImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent shareIntent = new Intent(Intent.ACTION_SEND);
				shareIntent.setType("image/jpeg");
				Uri imageUri = Uri.parse(resImagePath);
				shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
				startActivity(Intent.createChooser(shareIntent, "share"));
			}
		});
	}
	
	private void setIsVisibleMenu(boolean visibility){
		isVisibleMenu = visibility;
	}

}
