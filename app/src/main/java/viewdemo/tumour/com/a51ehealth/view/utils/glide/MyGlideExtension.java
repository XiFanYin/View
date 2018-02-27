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
 */
@GlideExtension
public class MyGlideExtension {


    private MyGlideExtension() {
    }

    @GlideOption
    public static void imageProgressListener(RequestOptions options, String bigUrl, ImageView image) {

        ImageLoadingView load3 = new ImageLoadingView(image.getContext());
        ImageProgressInterceptor.addListener(bigUrl, new ImageProgressListener() {
            @Override
            public void onProgress(double progress) {
                load3.setTargetView(image);
                load3.setProgress(progress);
            }


        });


    }
}
