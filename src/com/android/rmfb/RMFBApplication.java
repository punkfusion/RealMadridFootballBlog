package com.android.rmfb;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class RMFBApplication extends Application {
	
    @Override
    public void onCreate() {
        super.onCreate();

        // Create default Image display options.
        DisplayImageOptions options = 
                new DisplayImageOptions.Builder()
                .displayer(new FadeInBitmapDisplayer(500))
                .build();

        // Create global configuration and initialize ImageLoader with this configuration.
        ImageLoaderConfiguration config = 
                new ImageLoaderConfiguration.Builder(getApplicationContext())
                // TODO(suneet): Add a version check and use this from API 11+
                // TODO(suneet): Consider adding a cache for this.
                //.taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                //.taskExecutorForCachedImages(AsyncTask.THREAD_POOL_EXECUTOR)
                .defaultDisplayImageOptions(options)
                //.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75)
                .discCacheFileCount(100)
                //.enableLogging()
                .build();
        ImageLoader.getInstance().init(config);
    }

}
