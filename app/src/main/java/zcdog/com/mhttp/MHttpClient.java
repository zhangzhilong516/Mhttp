package zcdog.com.mhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import zcdog.com.mhttp.callback.JsonConvert;
import zcdog.com.mhttp.request.Engine;
import zcdog.com.mhttp.request.GetRequest;
import zcdog.com.mhttp.request.OkhttpEngine;
import zcdog.com.mhttp.request.PostRequest;
import zcdog.com.mhttp.utils.LogUtils;

/**
 * @author: zhangzhilong
 * @date: 2019/1/28
 * @des:
 */
public final class MHttpClient {

    private static final MHttpClient httpUtils = new MHttpClient();

    public Context getContext() {
        return context;
    }

    private Context context;
    private final Handler handler;
    private HttpConfig config;
    private Engine httpEngine;
    private JsonConvert jsonConvert;
    private MHttpClient(){
        this.handler = new Handler(Looper.getMainLooper());
        httpEngine = new OkhttpEngine();
        jsonConvert = JsonConvert.DEFAULT_CONVERTER;
        config = new HttpConfig.Builder().build();
        httpEngine.initConfig(config);
        LogUtils.init(config.isDebug());
    }

    public MHttpClient init(Context context){
        this.context = context;
        return this;
    }
    public void exchangeEngine(Engine engine){
        this.httpEngine = engine;
        httpEngine.initConfig(config);
    }

    public void config(HttpConfig httpConfig){
        this.config = httpConfig;
        httpEngine.initConfig(httpConfig);
    }

    public JsonConvert getJsonConvert(){
        return jsonConvert;
    }

    public Handler getMainHandler(){
        return handler;
    }

    public Engine engine(){
        return httpEngine;
    }

    public static MHttpClient getInstance(){
        return httpUtils;
    }

    public void runOnUiThread(Runnable r) {
        handler.post(r);
    }

    public static GetRequest get(){
        return new GetRequest();
    }
    public static PostRequest post(){
        return new PostRequest();
    }

}
