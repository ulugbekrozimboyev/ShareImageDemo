package com.example.shareimage;

import java.util.ArrayList;

import android.app.Application;

public class ShareImageApp extends Application {
	
	private ArrayList<String> imageList = new ArrayList<String>();
	
	@Override
	public void onCreate() {
		// TODO read images from file
		super.onCreate();
	}
	
	public ArrayList<String> getImageList() {
		return imageList;
	}
	
	public void setImageList(ArrayList<String> imageList) {
		this.imageList = imageList;
	}

	public void addImagePath(String selectedImsgePath) {
		imageList.add(selectedImsgePath);
	}
	
}
