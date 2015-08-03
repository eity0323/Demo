package com.suneee.test.demo;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

public class MainActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	
	// 默认存放图片的路径
	public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + "demo" + File.separator + "Images"
			+ File.separator;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initImageLoader();
		
		ImageView iv = (ImageView)findViewById(R.id.imageView);
		ImageLoader.getInstance().displayImage("drawable://R.drawable.ic_launcher", iv);
	}
	
	/** 初始化imageLoader */
	private void initImageLoader() {
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).showImageOnLoading(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisk(true).build();

		File cacheDir = new File(DEFAULT_SAVE_IMAGE_PATH);
		ImageLoaderConfiguration imageconfig = new ImageLoaderConfiguration.Builder(this).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory().diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(200).diskCache(new UnlimitedDiskCache(cacheDir))
				.diskCacheFileNameGenerator(new Md5FileNameGenerator()).defaultDisplayImageOptions(options).build();

		ImageLoader.getInstance().init(imageconfig);
	}
}
