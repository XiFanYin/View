package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.EmptySignature;

import java.io.File;

import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import viewdemo.tumour.com.a51ehealth.view.MainActivity;
import viewdemo.tumour.com.a51ehealth.view.TwoActivity;

/**
 * Created by Administrator on 2018/2/10/010.
 * 自定义Glide的API让监听使用更方便，需要注意，外部传递的url必须和要下载的url一致，否则会出现监听不到，因为根本就没走网络请求，而是获取的缓存
 */
@GlideExtension
public class MyGlideExtension {


    private MyGlideExtension() {
    }

    @GlideOption
    public static void imageProgressListener(RequestOptions options, String bigUrl, ImageView image) {
        //创建一个自定义的进度效果
        ImageLoadingView load3 = new ImageLoadingView(image.getContext());
        ImageProgressInterceptor.addListener(bigUrl, new ImageProgressListener() {
            @Override
            public void onProgress(double progress) {
                //把传入的ImageView对象添加一个进度布局
                load3.setTargetView(image);
                //设置进度变化过程
                load3.setProgress(progress);
            }


        });


    }
}
