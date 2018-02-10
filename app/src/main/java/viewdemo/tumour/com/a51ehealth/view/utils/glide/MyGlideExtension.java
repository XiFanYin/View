package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Administrator on 2018/2/10/010.
 */
@GlideExtension
public class MyGlideExtension {


    private MyGlideExtension() {
    }

    @GlideOption
    public static void imageProgressListener(RequestOptions options, String url, ImageProgressListener imageProgressListener) {

        ImageProgressInterceptor.addListener(url, imageProgressListener);

    }
}
