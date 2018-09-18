package viewdemo.tumour.com.a51ehealth.view.net;


import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import viewdemo.tumour.com.a51ehealth.view.net.API.Constant;
import viewdemo.tumour.com.a51ehealth.view.net.MeInterceptor.AddParamInterceptor;
import viewdemo.tumour.com.a51ehealth.view.net.MyConverter.MyConverterFactory;
import viewdemo.tumour.com.a51ehealth.view.net.cookie.CookiesManager;
import viewdemo.tumour.com.a51ehealth.view.net.tookenExceed.ProxyHandler;
import viewdemo.tumour.com.a51ehealth.view.utils.SPutils.SPUtils;


/**
 * Created by yinfeilong on 2017/8/25.
 */

public class RetrofitUtil {

    public static final int DEFAULT_TIMEOUT = 30;
    private static RetrofitUtil mInstance;
    private Retrofit mRetrofit;


    /**
     * 单例模式，生成该类对象
     *
     * @return
     */
    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    /**
     * 私有构造方法
     */
    private RetrofitUtil() {
        //可以添加自定义解析器和默认的解析器
        //添加响应式编程的适配器
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(MyConverterFactory.create())//可以添加自定义解析器和默认的解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加响应式编程的适配器
                .client(getOkHttpClient())
                .build();


    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AddParamInterceptor() {
                    @Override
                    public Map<String, String> getHeaderMap() {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("token", SPUtils.getString("token","222"));
                        return map;
                    }

                    @Override
                    public Map<String, String> getQueryParamMap() {
                        return null;
                    }

                    @Override
                    public Map<String, String> getFormBodyParamMap() {


                        return null;
                    }
                })
                .cookieJar(new CookiesManager())//如是混合开发，需要添加cookie管理
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }

    /**
     * 获取对应的API实例
     *
     * @param service
     * @param <T>
     * @return
     */

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    public <T> T getProxy(Class<T> tClass) {
        T t = mRetrofit.create(tClass);
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[] { tClass }, new ProxyHandler(t));
    }
}
