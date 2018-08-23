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

        //没有使用缓存，然后可能抛出来的异常
        if (e instanceof ConnectException) {

            Toast.makeText(context, "服务器连接超时", Toast.LENGTH_SHORT).show();

        } else if (e instanceof SocketTimeoutException) {

            Toast.makeText(context, "服务器响应超时", Toast.LENGTH_SHORT).show();

        } else if (e instanceof ApiException) {
            //ApiException处理
            ApiException exception = (ApiException) e;
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();

        } else {
            //使用缓存可能跑出来的异常，这里是一个异常集合
            if (e instanceof CompositeException) {
                CompositeException eee = (CompositeException) e;
                List<Throwable> exceptions = eee.getExceptions();
                for (int i = 0; i < exceptions.size(); i++) {
                    if (exceptions.get(i) instanceof ApiException) {
                        ApiException apiException = (ApiException) exceptions.get(i);
                        Toast.makeText(context, apiException.getMessage(), Toast.LENGTH_SHORT).show();
                    } else if (exceptions.get(i) instanceof SocketTimeoutException) {
                        Toast.makeText(context, "服务器响应超时", Toast.LENGTH_SHORT).show();
                    }

                }
            }


        }


    }
}


