package viewdemo.tumour.com.a51ehealth.view.net.Exception;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.exceptions.CompositeException;


/**
 * Created by Administrator on 2017/8/12/012.
 * <p>
 * 统一错误的处理类
 * <p>
 * http://www.jianshu.com/p/c105a4177982
 */

public class ApiErrorHelper {


    public static void handleCommonError(Context context, Throwable e) {
        if (e instanceof ConnectException) {
            Toast.makeText(context, "请检查你的网络~", Toast.LENGTH_SHORT).show();
        } else if (e instanceof SocketTimeoutException) {

            Toast.makeText(context, "服务器响应超时", Toast.LENGTH_SHORT).show();

        } else if (e instanceof ApiException) {
            //ApiException处理
            ApiException exception = (ApiException) e;
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(context, "未知异常", Toast.LENGTH_SHORT).show();

        }


    }
}


