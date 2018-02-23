package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import android.widget.ImageView;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.ColorFilterTransformation;

/**
 * Created by Administrator on 2018/2/10/010.
 */
@GlideExtension
public class MyGlideExtension {


    private MyGlideExtension() {
    }

    @GlideOption
    public static void imageProgressListener(RequestOptions options, String smallUrl, String bigUrl, ImageView image) {

        ImageLoadingView load = new ImageLoadingView(image.getContext());
        GlideApp.with(image.getContext()).load(smallUrl).transforms(new ColorFilterTransformation(0x22222222)).into(image);
        ImageProgressInterceptor.addListener(bigUrl, new ImageProgressListener() {
            @Override
            public void onProgress(double progress) {
                load.setTargetView(image);
                load.setProgress(progress);
            }
        });

    }
}
