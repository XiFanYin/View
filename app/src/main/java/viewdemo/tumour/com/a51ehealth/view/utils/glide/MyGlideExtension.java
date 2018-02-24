package viewdemo.tumour.com.a51ehealth.view.utils.glide;

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
import viewdemo.tumour.com.a51ehealth.view.TwoActivity;

/**
 * Created by Administrator on 2018/2/10/010.
 */
@GlideExtension
public class MyGlideExtension {


    private MyGlideExtension() {
    }

    @GlideOption
    public static void imageProgressListener(RequestOptions options, String smallUrl, String bigUrl, ImageView image) {
        //获取Glide的缓存路径如果为null，表示没有缓存，如果有表示有缓存
        GlideUrl glideUrl = new GlideUrl(bigUrl);
        File file = DiskLruCacheWrapper.get(Glide.getPhotoCacheDir(image.getContext()), 250 * 1024 * 1024).get(new OriginalKey(glideUrl, EmptySignature.obtain()));

        if (file == null) {//表示没有缓存
            ImageLoadingView load = new ImageLoadingView(image.getContext());
            GlideApp.with(image.getContext()).load(smallUrl).transforms(new ColorFilterTransformation(0x22222222)).into(image);
            ImageProgressInterceptor.addListener(bigUrl, new ImageProgressListener() {
                @Override
                public void onProgress(double progress) {
                    load.setTargetView(image);
                    load.setProgress(progress);
                }
                @Override
                public void onSucceeful() {
                    GlideApp.with(image.getContext()).load(bigUrl).into(image);
                }
            });
        } else {//表示已经缓存

            GlideApp.with(image.getContext()).load(bigUrl).into(image);

        }




    }
}
