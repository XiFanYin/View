package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import viewdemo.tumour.com.a51ehealth.view.net.MeInterceptor.AddParamInterceptor;
import viewdemo.tumour.com.a51ehealth.view.net.RetrofitUtil;

/**
 * Created by Administrator on 2018/2/9.
 */

public class OkHttpUtils {

    private static OkHttpUtils mInstance;
    private  OkHttpClient build;

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

    public OkHttpClient getOkHttpClient() {
        return build;
    }

    private OkHttpUtils() {

        build = new OkHttpClient.Builder()
                .addInterceptor(new ImageProgressInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .build();
    }
}
