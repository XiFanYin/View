package viewdemo.tumour.com.a51ehealth.view.net.cookie;

/**
 * Created by Administrator on 2018/4/3.
 */

import android.util.Log;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import viewdemo.tumour.com.a51ehealth.view.app.App;

/**
 * 自动管理Cookies
 */
public class CookiesManager implements CookieJar {
    //存cookie的对象
    private final PersistentCookieStore cookieStore = new PersistentCookieStore(App.getApplication());

    @Override//当获取cookie时候调用---类似解析器
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);//存起来

            }
        }
    }

    @Override//当请求前调用---类似拦截器
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);//获取出来，添加到请求头中

        return cookies;
    }
}