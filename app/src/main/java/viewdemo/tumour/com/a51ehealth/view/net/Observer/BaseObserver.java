package viewdemo.tumour.com.a51ehealth.view.net.Observer;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import viewdemo.tumour.com.a51ehealth.view.app.App;
import viewdemo.tumour.com.a51ehealth.view.net.Exception.ApiErrorHelper;

/**
 * Created by Administrator on 2017/8/26/026.
 */

public abstract class BaseObserver <T> implements Observer <T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public abstract void onNext(@NonNull T t) ;

    @Override
    public void onError(@NonNull Throwable e) {
        //异常，统一交给该处理的类去处理
        ApiErrorHelper.handleCommonError(App.getApplication(),e);
    }

    @Override
    public void onComplete() {

    }
}
