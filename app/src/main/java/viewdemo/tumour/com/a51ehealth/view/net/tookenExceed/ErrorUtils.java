package viewdemo.tumour.com.a51ehealth.view.net.tookenExceed;


import android.app.Activity;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import viewdemo.tumour.com.a51ehealth.view.LoginActivity;
import viewdemo.tumour.com.a51ehealth.view.net.API.API;
import viewdemo.tumour.com.a51ehealth.view.net.Exception.ApiException;
import viewdemo.tumour.com.a51ehealth.view.net.RetrofitUtil;
import viewdemo.tumour.com.a51ehealth.view.utils.SPutils.SPUtils;

public class ErrorUtils {

    /**
     * 自动刷新token
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> tokenErrorHandler() {

        return upstream ->
                upstream.onErrorResumeNext(throwable -> {
                    if (throwable instanceof ApiException && ((ApiException) throwable).getErrorCode() == 8) {
                        //这里异步去请求，然后再确定返回值
                        return RetrofitUtil.
                                getInstance()
                                .create(API.class)
                                .Login("wangyong", "111111")
                                .flatMap(loginBean -> {
                                    SPUtils.saveString("token", loginBean.getData().getToken());
                                    SystemClock.sleep(3000);
                                    //这里创建一个新流去return，保证了先去请求token，之后再去重复订阅
                                    return Observable.error(new ApiException(-999, "这表示特殊错误，表示要重复去请求"));
                                });
                    } else {
                        //如果不是token错误，会创建一个新的流，把错误传递下去
                        return Observable.error(throwable);
                    }

                }).retryWhen(throwableObservable -> {

                    return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Throwable throwable) throws Exception {

                            if (throwable instanceof ApiException && ((ApiException) throwable).getErrorCode() == -999) {
                                return Observable.just(1);
                            } else {
                                //如果不是token错误，会创建一个新的流，把错误传递下去
                                return Observable.error(throwable);
                            }

                        }
                    });

                });


    }


    /**
     * 跳转登录，之后继续之前请求
     *
     * @param activity
     * @param <T>
     * @return
     */


    public static <T> ObservableTransformer<T, T> tokenErrorHandlerJumpLogin(Activity activity) {
        return upstream ->
                upstream
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                            @Override
                            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {

                                if (throwable instanceof ApiException && ((ApiException) throwable).getErrorCode() == 8) {
                                    //这里异步去请求，然后再确定返回值
                                    return new AvoidOnResult(activity)
                                            .startForResult(LoginActivity.class)
                                            .filter(it -> it.getResultCode() == Activity.RESULT_OK)
                                            .flatMap(it -> {
                                                boolean loginSucceeds = it.getData().getBooleanExtra("loginSucceed", false);
                                                if (loginSucceeds) {
                                                    return Observable.error(new ApiException(-999, "这表示特殊错误，表示要重复去请求"));
                                                } else {
                                                    return Observable.error(throwable);
                                                }
                                            });
                                } else {
                                    //如果不是token错误，会创建一个新的流，把错误传递下去
                                    return Observable.error(throwable);

                                }
                            }
                        })
                        .observeOn(Schedulers.io())
                        .retryWhen(throwableObservable -> {
                            return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                                @Override
                                public ObservableSource<?> apply(Throwable throwable) throws Exception {

                                    if (throwable instanceof ApiException && ((ApiException) throwable).getErrorCode() == -999) {
                                        return Observable.just(1);
                                    } else {
                                        //如果不是token错误，会创建一个新的流，把错误传递下去
                                        return Observable.error(throwable);
                                    }

                                }
                            });

                        });

    }

    /**
     * 跳转登录，之后继续之前请求
     *
     * @param fragment
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> tokenErrorHandlerJumpLogon(Fragment fragment) {
        return tokenErrorHandlerJumpLogin(fragment.getActivity());
    }
}
