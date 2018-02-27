package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import android.util.Log;

import java.io.IOException;
import java.util.Map;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import viewdemo.tumour.com.a51ehealth.view.net.Schedulers.RxSchedulers;

/**
 * http://blog.csdn.net/guolin_blog/article/details/78357251
 * Created by Administrator on 2018/2/9.
 * 继承了ResponseBody类之后一定要重写contentType()、
 * contentLength()和source()这三个方法，
 * 我们在contentType()和contentLength()方法中直接就调用传入的原始ResponseBody的contentType()和contentLength()方法即可
 */
public class ImageProgressResponseBody extends ResponseBody {

    private BufferedSource bufferedSource;

    private ResponseBody responseBody;

    private ImageProgressListener listener;


    public ImageProgressResponseBody(String url, ResponseBody responseBody) {
        this.responseBody = responseBody;
        listener = ImageProgressInterceptor.LISTENER_MAP.get(url);
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    /*调用了原始ResponseBody的source()方法来去获取Source对象，
    接下来将这个Source对象封装到了一个ProgressSource对象当中，
    最终再用Okio的buffer()方法封装成BufferedSource对象返回。*/
    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(new ProgressSource(responseBody.source()));
        }
        return bufferedSource;
    }


    private class ProgressSource extends ForwardingSource {

        double totalBytesRead = 0;

        double currentProgress;

        ProgressSource(Source source) {
            super(source);
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            long bytesRead = super.read(sink, byteCount);
            long fullLength = responseBody.contentLength();
            if (bytesRead == -1) {
                totalBytesRead = fullLength;
            } else {
                totalBytesRead += bytesRead;
            }
            double progress = totalBytesRead / fullLength;
            Log.d("image_progress", "download progress is " + progress);
            if (listener != null && progress != currentProgress) {
                Observable.just(progress)
                        .observeOn(AndroidSchedulers.mainThread())//指定doOnTerminate的线程
                        .subscribe(new Consumer<Double>() {
                            @Override
                            public void accept(Double progress) throws Exception {
                                listener.onProgress(progress);
                            }
                        });
            }
            if (listener != null && totalBytesRead == fullLength) {
                String url = getKey(ImageProgressInterceptor.LISTENER_MAP, listener);
                ImageProgressInterceptor.removeListener(url);
            }
            currentProgress = progress;
            return bytesRead;
        }
    }


    //根据map的value获取map的key
    private synchronized String getKey(Map<String, ImageProgressListener> map, ImageProgressListener value) {
        String key = "";
        for (Map.Entry<String, ImageProgressListener> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                key = entry.getKey();
            }
        }
        return key;
    }
}