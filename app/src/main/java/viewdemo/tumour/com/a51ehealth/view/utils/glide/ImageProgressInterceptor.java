package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/2/9.
 * 图片下载进度监听拦截器
 *
 *
 * 这里使用了一个Map来保存注册的监听器，
 * Map的键是一个URL地址。之所以要这么做，
 * 是因为你可能会使用Glide同时加载很多张图片，
 * 而这种情况下，必须要能区分出来每个下载进度的回调到底是对应哪个图片URL地址的。
 */

public class ImageProgressInterceptor implements Interceptor {

    //保存需要监听图片的集合
    static final Map<String, ImageProgressListener> LISTENER_MAP = new HashMap<>();

    /*添加监听的工具类*/
    public static void addListener(String url, ImageProgressListener listener) {
        LISTENER_MAP.put(url, listener);
    }

    /*移除监听的工具类*/
    public static void removeListener(String url) {
        LISTENER_MAP.remove(url);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原始的请求对象
        Request request = chain.request();
        //获取原始返回的响应对象
        Response response = chain.proceed(request);
        //获取请求的url
        String url = request.url().toString();
        //获取响应的相应体
        ResponseBody body = response.body();
        //创建一个新的响应对象并返回
        Response newResponse = response.newBuilder().body(new ImageProgressResponseBody(url, body)).build();
        return newResponse;

    }
}
