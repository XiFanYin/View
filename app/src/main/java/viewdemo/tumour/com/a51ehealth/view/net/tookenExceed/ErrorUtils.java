package viewdemo.tumour.com.a51ehealth.view.net.tookenExceed;

import android.os.SystemClock;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.IntFunction;
import viewdemo.tumour.com.a51ehealth.view.net.API.API;
import viewdemo.tumour.com.a51ehealth.view.net.Exception.ApiException;
import viewdemo.tumour.com.a51ehealth.view.net.RetrofitUtil;
import viewdemo.tumour.com.a51ehealth.view.utils.SPutils.SPUtils;

public class ErrorUtils {


    public static <T> ObservableTransformer<T, T> specialErrorHandler() {

        return upstream ->
                upstream .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                            @Override
                            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
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
                            }
                        })
                        .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {

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
                            }
                        });


    }


}
