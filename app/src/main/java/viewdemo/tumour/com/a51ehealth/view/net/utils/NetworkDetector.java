package viewdemo.tumour.com.a51ehealth.view.net.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import viewdemo.tumour.com.a51ehealth.view.app.App;


/**
 * Created by Administrator on 2017/8/12/012.
 * <p>
 * 检查网络是否可用
 */

public class NetworkDetector {

    /**
     * 判断网络是否可用
     *
     *
     */
    public static Boolean isNetworkReachable() {
        ConnectivityManager cm = (ConnectivityManager) App.getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        return (current.isAvailable());
    }


}
