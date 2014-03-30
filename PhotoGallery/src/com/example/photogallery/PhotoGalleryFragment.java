package com.example.photogallery;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class PhotoGalleryFragment extends Fragment {

	GridView mGridView;

	
	
	public PhotoGalleryFragment() {
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			new FetchItemsTask().execute();
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_photo_gallery,
				container, false);
		
		mGridView = (GridView)v.findViewById(R.id.gridView);
		
		
		return v;
	}

	@SuppressWarnings("rawtypes")
	class FetchItemsTask extends AsyncTask {

		@Override
		protected Object doInBackground(Object... arg0) {
			
			new FlickrFetcher().fetchItems();
			return null;
		}
		
	}
}
