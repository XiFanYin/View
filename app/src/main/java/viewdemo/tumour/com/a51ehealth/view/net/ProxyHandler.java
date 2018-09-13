package viewdemo.tumour.com.a51ehealth.view.net;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import viewdemo.tumour.com.a51ehealth.view.net.API.API;
import viewdemo.tumour.com.a51ehealth.view.net.Exception.ApiException;
import viewdemo.tumour.com.a51ehealth.view.utils.SPutils.SPUtils;


public class ProxyHandler implements InvocationHandler {

    private Object mProxyObject;


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
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable)  {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        //判断是不是token失效，这里假如token等于8失效
                        if (throwable instanceof ApiException) {
                            if (((ApiException) throwable).getErrorCode() == 8) {
                                return RetrofitUtil.
                                        getInstance()
                                        .create(API.class)
                                        .Login("wangyong", "111111")
                                        .flatMap(loginBean -> {
                                            SPUtils.saveString("token", loginBean.getData().getToken());
                                            Log.e("rrrrrrrrrrrrr", Thread.currentThread().getName());
                                            return Observable.just(1);
                                        });
                            }
                        }
                        Log.e("rrrrrrrrrrrrr", Thread.currentThread().getName());
                        return Observable.error(throwable);
                    }
                });


            }
        });

    }


}
