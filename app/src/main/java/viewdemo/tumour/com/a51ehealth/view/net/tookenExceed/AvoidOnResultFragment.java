package viewdemo.tumour.com.a51ehealth.view.net.tookenExceed;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class AvoidOnResultFragment extends Fragment {

    private Map<Integer, PublishSubject<ActivityResultInfo>> mSubjects = new HashMap<>();


    public AvoidOnResultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    //当前intent传递到这个Fragment中
    public Observable<ActivityResultInfo> startForResult(final Intent intent) {
        //创建一个上流
        final PublishSubject<ActivityResultInfo> subject = PublishSubject.create();
        //当这个流订阅的时候，我需要做的是
        return subject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                //把对应的回调储存起来
                mSubjects.put(subject.hashCode(), subject);
                //开始跳转
                startActivityForResult(intent, subject.hashCode());
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //移除回调，同时返回这个回调
        PublishSubject<ActivityResultInfo> subject = mSubjects.remove(requestCode);
        if (subject != null) {
            //把获取的结果发送成流，传递下去
            subject.onNext(new ActivityResultInfo(resultCode, data));
            //最后调用完成流
            subject.onComplete();
        }

    }

}