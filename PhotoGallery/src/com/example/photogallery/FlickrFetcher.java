package com.example.photogallery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.Uri;
import android.util.Log;

public class FlickrFetcher {
//	
	private static final String TAG = "PhotoGalleryFragment";
	private static final String ENDPOINT = "https://api.flickr.com/services/rest";
	private static final String METHOD_GET_RECENT = "flickr.photos.getRecent";

	
	// just a note so I can view in browser the xml
	// https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=7964de9405ed1fa76fb75a266cb05165
	
	private static final String API_KEY = "7964de9405ed1fa76fb75a266cb05165";
	/**
	 * takes a url and returns a byte array
	 * 
	 * @param urlSpec	the url to read
	 * @return a byte array
	 * @throws IOException
	 */
	public byte[] getUrlBytes(String urlSpec) throws IOException {
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();

			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}

			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			return out.toByteArray();
		} finally {
			connection.disconnect();
		}
	}

	/**
	 * public method to return a string given a url
	 * 
	 * @param urlSpec the url to read
	 * @return String	the string that represents the URL contents
	 * @throws IOException
	 */
	public String getUrl(String urlSpec) throws IOException {
		return new String(getUrlBytes(urlSpec));
	}

	
	public void fetchItems() {
		String url = Uri.parse(ENDPOINT).buildUpon()
				.appendQueryParameter("method", METHOD_GET_RECENT)
				.appendQueryParameter("api_key", API_KEY)
				
				.build().toString();
		Log.i(TAG, "URL to parse is: " + url);
		String xmlString;
		try {
			xmlString = getUrl(url);
			Log.i(TAG, xmlString);
		} catch (IOException e) {
			Log.i(TAG, "error");
		}

		
	}
}
