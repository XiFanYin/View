package viewdemo.tumour.com.a51ehealth.view.net;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import viewdemo.tumour.com.a51ehealth.view.bean.LoginBean;
import viewdemo.tumour.com.a51ehealth.view.net.API.API;
import viewdemo.tumour.com.a51ehealth.view.net.Exception.ApiException;
import viewdemo.tumour.com.a51ehealth.view.net.Observer.BaseObserver;
import viewdemo.tumour.com.a51ehealth.view.net.Schedulers.RxSchedulers;
import viewdemo.tumour.com.a51ehealth.view.utils.SPutils.SPUtils;


public class ProxyHandler implements InvocationHandler {

    private Object mProxyObject;

    int maxRetries = 1;
    int retryCount;

    public ProxyHandler(Object proxyObject) {
        mProxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return Observable.just(1).flatMap(o -> {
            try {
                return (Observable<?>) method.invoke(mProxyObject, args);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }).retryWhen(throwableObservable -> {
            return throwableObservable.concatMap(throwable -> {
                //判断是不是token失效，这里假如token等于8失效
                if (throwable instanceof ApiException) {
                    if (((ApiException) throwable).getErrorCode() == 8) {
                        //如果是token异常，需要去调用更新token
                        if (++retryCount <= maxRetries) {
                            Log.e("rrrrrrrrrr", "token错误引起的");
                            //当这里调用onNext的时候会触发再次请求
                            return refreshToken();
                        }
                    }
                }
                //如果不是直接抛出异常到表层
                return Observable.error(throwable);
            });
        });

    }

    private Observable<?> refreshToken() {

        RetrofitUtil.
                getInstance()
                .create(API.class)
                .Login("wangyong", "111111")
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        SPUtils.saveString("token", loginBean.getData().getToken());
                    }
                });

        return Observable.just(1);


    }
}
