
/**
 *  
 * @return Instance of WallpaperSetting
 */
var WallpaperSetting = function() { 

}

/**
 * @param imagePath The directory for which we want the listing
 * @param successCallback The callback which will be called when directory listing is successful
 * @param failureCallback The callback which will be called when directory listing encouters an error
 */
WallpaperSetting.prototype.setWallPaper = function(imagePath,successCallback, failureCallback) {

	
    return PhoneGap.exec(successCallback,    //Callback which will be called when directory listing is successful
    					failureCallback,     //Callback which will be called when directory listing encounters an error
    					'WallpaperSetPlugin',  //Telling PhoneGap that we want to run "WallpaperSetting" Plugin
    					'setWallPaper',              //Telling the plugin, which action we want to perform
    					[imagePath]);        //Passing a list of arguments to the plugin, in this case this is the directory path
};

/**
 * <ul>
 * <li>Register the Directory Listing Javascript plugin.</li>
 * <li>Also register native call which will be called when this plugin runs</li>
 * </ul>
 */
PhoneGap.addConstructor(function() {
	//Register the javascript plugin with PhoneGap
	PhoneGap.addPlugin('wallpapersetting', new WallpaperSetting());
	
	//Register the native class of plugin with PhoneGap
	PluginManager.addService("WallpaperSetPlugin","com.sentiense.pongalapps.WallpaperSetPlugin");
});