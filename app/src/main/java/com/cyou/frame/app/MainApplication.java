package com.cyou.frame.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.cyou.demo.youku.ui.act.CachedActivity;
import com.cyou.demo.youku.ui.act.CachingActivity;
import com.cyou.frame.util.ImageLoaderUtil;
import com.cyou.frame.util.PhoneUtils;
import com.cyou.model.library.net.RequestHttpClient;
import com.cyou.model.library.net.RequestManager;
import com.loopj.android.http.AsyncHttpClient;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.youku.player.YoukuPlayerBaseConfiguration;

import java.io.File;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by zhaobingfeng on 14-12-22.
 */
public class MainApplication extends Application {
    /** 获取屏幕的宽和高 */
    public static int screenHight = 0;
    public static int screenWidth = 0;
    public static DisplayMetrics displayMetrices;
    /**
     * 设备的 IMEI
     */
    public static String IMEI = "";
    /**
     * 获取全局的上下文
     */
    public static Context context;

    //youkuplayersdk 配置
    public static YoukuPlayerBaseConfiguration configuration;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        init();
    }

    /**
     */
    private void init() {
        context = getApplicationContext();

        initDeviceWidthAndHeight();

        ShareSDK.initSDK(context);
        //获取imei
        PhoneUtils.getIMEI(context);
        //请求缓存管理
        RequestManager.init(context);
        //请求初始化
        AsyncHttpClient instence = RequestHttpClient.getInstence(context);
//        RequestConfig.addHttpClientRASHead(instence);
//        instence.setUserAgent(PhoneUtils.getDeviceUA(context));
        initPicasso();
        initUIL();
        initYKPlayer();

    }


    private void initPicasso() {
//            final String imageCacheDir = /* 自定义目录 */ +"image";
//        Picasso picasso = new Picasso.Builder(context).memoryCache(
//                new LruCache(1024*1024*20)).downloader();
//            Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(new File(imageCacheDir))).build();
//            Picasso.setSingletonInstance(picasso);
        File cacheDir;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = context.getExternalCacheDir();
        } else {
            cacheDir = context.getCacheDir();
        }
        Downloader downloader = new OkHttpDownloader(cacheDir, Integer.MAX_VALUE);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(downloader);

        int memClass = ((ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE))
                .getLargeMemoryClass();
        int cacheSize = 1024 * 1024 * memClass / 8;
        builder.memoryCache(new com.squareup.picasso.LruCache(cacheSize));

        Picasso picasso = builder.build();
        Picasso.setSingletonInstance(picasso);
    }

    public void initUIL(){
        ImageLoaderUtil.init(context);
    }

    /**
     * 获取设备的宽和高 WangQing 2013-8-12 void
     */
    private void initDeviceWidthAndHeight() {
        displayMetrices = PhoneUtils.getAppWidthAndHeight(this);
        screenHight = displayMetrices.heightPixels;
        screenWidth = displayMetrices.widthPixels;
    }

    private void initYKPlayer(){
        configuration = new YoukuPlayerBaseConfiguration(this) {


            /**
             * 通过覆写该方法，返回“正在缓存视频信息的界面”，
             * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
             * 用户需要定义自己的缓存界面
             */
            @Override
            public Class<? extends Activity> getCachingActivityClass() {
                // TODO Auto-generated method stub
                return CachingActivity.class;
            }

            /**
             * 通过覆写该方法，返回“已经缓存视频信息的界面”，
             * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
             * 用户需要定义自己的已缓存界面
             */

            @Override
            public Class<? extends Activity> getCachedActivityClass() {
                // TODO Auto-generated method stub
                return CachedActivity.class;
            }

            /**
             * 配置视频的缓存路径，格式举例： /appname/videocache/
             * 如果返回空，则视频默认缓存路径为： /应用程序包名/videocache/
             *
             */
            @Override
            public String configDownloadPath() {
                // TODO Auto-generated method stub

                //return "/myapp/videocache/";			//举例
                return null;
            }
        };
    }
}
