package viewdemo.tumour.com.a51ehealth.view.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Administrator on 2018/2/6.
 */

public class App extends Application{
    //全局上下文
    private static App application;

    @Override
    public void onCreate() {
        super.onCreate();
        this.application = this;

    }

    /**
     * 提供全局上下文静态方法
     *
     * @return
     */
    public static App getApplication() {
        return application;
    }

    /**
     * 分包导致的荣云初始化失败
     *
     * @param base http://blog.csdn.net/daitu_liang/article/details/72987378
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

}
