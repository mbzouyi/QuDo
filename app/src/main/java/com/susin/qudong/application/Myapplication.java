package com.susin.qudong.application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.susin.qudong.model.CheXing;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class Myapplication extends Application {

    //车车型号信息
    public static String parentname1;//
    public static String parentname2;//车具体系
    public static String parentname2jian;//车具体系
    public static String parentname;//
    public static String carname;//车具体型号
    public static String carId;//车具体id
    public static List<CheXing.Car.Car1.Car2> list=new ArrayList<>();
    public static String color="";

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("==============OkGo");
//log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
//log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}