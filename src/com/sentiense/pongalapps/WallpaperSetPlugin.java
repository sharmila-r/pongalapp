/**
 * Example of Android PhoneGap Plugin
 */
package com.sentiense.pongalapps;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.phonegap.api.PluginResult.Status;

/**
 * PhoneGap plugin which can be involved in following manner from javascript
 * 
 * @author Sharmila R
 * 
 */
public class WallpaperSetPlugin extends Plugin {

	/** List Action */
	public static final String ACTION = "setWallPaper";
	public static final String TAG = "WallpaperSetPlugin";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phonegap.api.Plugin#execute(java.lang.String,
	 * org.json.JSONArray, java.lang.String)
	 */
	@Override
	public PluginResult execute(String action, JSONArray data, String callbackId) {
		Log.d(TAG, "Plugin Called");
		PluginResult result = null;
		if (ACTION.equals(action)) {
			try {
				String imagePath = data.getString(0);
				
				String path = imagePath.substring(imagePath.indexOf("www"));
//				Log.d(TAG, "Image Path is   " + path);
				InputStream input = ctx.getAssets().open(path);
				Bitmap s = BitmapFactory.decodeStream(input);
				WallpaperManager myWallpaperManager = WallpaperManager
						.getInstance(ctx.getApplicationContext());
				myWallpaperManager.setBitmap(s);
				result = new PluginResult(Status.OK, "WallPaper Set");
			} catch (JSONException jsonEx) {
				Log.d(TAG, "Got JSON Exception " + jsonEx.getMessage());
				result = new PluginResult(Status.JSON_EXCEPTION);
			} catch (IOException e) {
				Log.d(TAG, "Got IO Exception " + e.getMessage());
				result = new PluginResult(Status.IO_EXCEPTION);
			}
		} else {
			result = new PluginResult(Status.INVALID_ACTION);
			Log.d(TAG, "Invalid action : " + action + " passed");
		}
		return result;
	}
}
