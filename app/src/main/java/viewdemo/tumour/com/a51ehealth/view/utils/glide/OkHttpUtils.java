package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import viewdemo.tumour.com.a51ehealth.view.net.RetrofitUtil;

/**
 * Created by Administrator on 2018/2/9.
 */

public class OkHttpUtils {

    private static OkHttpUtils mInstance;
    private OkHttpClient build;

    /**
     * 单例模式，生成该类对象
     *
     * @return
     */
    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new OkHttpUtils();
            }
        }
        return mInstance;
    }

    /**
     * 对外暴漏一个返回OkHttpClient对象的方法
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return build;
    }

    /**
     * 私有化构造函数，方法中创建OkHttpClient对象，并添加自定义拦截器
     */

    private OkHttpUtils() {

        build = new OkHttpClient.Builder()
                .addInterceptor(new ImageProgressInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .build();
    }
}
