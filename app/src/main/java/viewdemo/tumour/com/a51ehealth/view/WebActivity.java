package viewdemo.tumour.com.a51ehealth.view;

import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

import okhttp3.Cookie;
import viewdemo.tumour.com.a51ehealth.view.app.App;
import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.net.cookie.PersistentCookieStore;
import viewdemo.tumour.com.a51ehealth.view.net.cookie.WebViewUtils;

/**
 * Created by Administrator on 2018/4/3.
 */

public class WebActivity extends BaseActivity {

    private WebView webView;

    private String url = "http://th5.yipuhui.com/h5//userInfo/toMyaccountBangzhuPage";

    @Override
    public int getId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.webView);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            if (0 != (getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE))
            { WebView.setWebContentsDebuggingEnabled(true);}
        }
    }

    @Override
    protected void initListener() {


        //覆盖WebView默认通过第三方或者是系统打开网页的行为，使网页可以在WebView中打开
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制网页在WebView中打开，
                //为false的时候，调用系统浏览器或者第三方浏览器打开
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    protected void initData(boolean hasNetWork) {
        PersistentCookieStore cookie = new PersistentCookieStore(App.getApplication());
        List<Cookie> cookies = cookie.getCookies();
        for (Cookie cookie2 : cookies) {
            if (WebViewUtils.syncCookie(url, "JSESSIONID="+cookie2.value())) {

          webView.loadUrl(url);

            } else {

                Log.e("rrrrrrrrrrrrr","设置失败");
            }
        }


    }
}
