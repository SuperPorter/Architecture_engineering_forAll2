package com.voodoo.code_architecture;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import java.util.Set;

/**
 * 创建者：Sunzeyu
 * <br>创建时间：上午 09:32 2019/4/15 015
 * <br>功能描述：
 */
public class BaseApplication extends MultiDexApplication{
    private static BaseApplication application = null;
    private static Context mcontext;
    private static String uid = "10001";
    private Set<Activity> allActivtiyes;
    public static BaseApplication getBaseApplication(){
        return application;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = getApplicationContext();
        application = this;
        init();
    }

    private void init() {

    }
    public static Context getContext(){
        return mcontext;
    }
    public void exitApp(){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    public void exitAppAll(){
        if (allActivtiyes != null) {
            synchronized (allActivtiyes) {
                for (Activity allActivtiye : allActivtiyes) {
                    allActivtiye.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    public static String getUId(){
        return uid;
    }
    public static void setUId(String userID) {
        uid = userID;
    }

}
