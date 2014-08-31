package com.example.shareimage;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shareimage.util.MyPagerAdapter;
import com.example.shareimage.util.PagerContainer;

public class MainActivity extends Activity {

	private ShareImageApp app;
	private PagerContainer mContainer;
	private ViewPager pager;
	private int CHOOSE_PICTURE = 1;
	private String selectedImsgePath;
	private MyPagerAdapter adapter;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        app = (ShareImageApp) getApplication();
 
        mContainer = (PagerContainer) findViewById(R.id.pager_container);
                
        createPager();
        
    }


	private void createPager() {
		pager = mContainer.getViewPager();
        adapter = new MyPagerAdapter(MainActivity.this, app.getImageList());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount());
        pager.setPageMargin(15);
 
        pager.setClipChildren(false);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.open_gallery:
			Intent openGallery = new Intent();
			openGallery.setType("image/*");
			openGallery.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(openGallery, "Choose Picture"), CHOOSE_PICTURE);
			break;

		default:
			break;
		}
    	return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(resultCode == RESULT_OK){
    		if (requestCode == CHOOSE_PICTURE) {
				Uri selectedImsgeUri = data.getData();
				selectedImsgePath = getPath(selectedImsgeUri);
				app.addImagePath(selectedImsgePath);
				createPager();
			}
    	}
    }


	private String getPath(Uri uri) {
		if (uri == null) {
			return null;
		}
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor  cursor = managedQuery(uri, projection, null, null, null);
		if (cursor != null) {
			int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(columnIndex);
		}
		return uri.getPath();
	}
}
