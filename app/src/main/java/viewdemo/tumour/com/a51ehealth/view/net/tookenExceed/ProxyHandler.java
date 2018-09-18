package viewdemo.tumour.com.a51ehealth.view.net.tookenExceed;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import viewdemo.tumour.com.a51ehealth.view.net.API.API;
import viewdemo.tumour.com.a51ehealth.view.net.Exception.ApiException;
import viewdemo.tumour.com.a51ehealth.view.net.RetrofitUtil;
import viewdemo.tumour.com.a51ehealth.view.utils.SPutils.SPUtils;


public class ProxyHandler implements InvocationHandler {

    private Object mProxyObject;


    public ProxyHandler(Object proxyObject) {
        mProxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        return Observable.just(1).flatMap(o -> {
                return (Observable<?>) method.invoke(mProxyObject, args);
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) {
                //这里return决定他是否继续订阅
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        //判断是不是token失效，这里假如token等于8失效
                        if (throwable instanceof ApiException) {
                            if (((ApiException) throwable).getErrorCode() == 8) {
                                //上边return的是这里的return，这里去请求token，如果请求成功就去创建一个可以重复订阅的
                                // 如果刷新token的请求也错误，他会直接return一个错误也就不会发生再次订阅，错误继续传递下去
                                //这里你可能会问为什么网络请求不去切换线程，你可以打印一下，他本身就是子线程去创建的流，所以不用切换线程。
                                return RetrofitUtil.
                                        getInstance()
                                        .create(API.class)
                                        .Login("wangyong", "111111")
                                        .flatMap(loginBean -> {
                                            SPUtils.saveString("token", loginBean.getData().getToken());
                                            //这里创建一个新流去return，保证了先去请求token，之后再去重复订阅
                                            return Observable.just(1);
                                        });
                            }
                        }
                        //如果不是token错误，会创建一个新的流，把错误传递下去
                        return Observable.error(throwable);
                    }
                });


            }
        });

    }


}
