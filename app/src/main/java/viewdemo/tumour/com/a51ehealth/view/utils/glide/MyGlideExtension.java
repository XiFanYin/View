package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import javax.microedition.khronos.opengles.GL;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import viewdemo.tumour.com.a51ehealth.view.R;
import viewdemo.tumour.com.a51ehealth.view.app.App;
import viewdemo.tumour.com.a51ehealth.view.net.Schedulers.RxSchedulers;

/**
 * Created by Administrator on 2018/2/10/010.
 */
@GlideExtension
public class MyGlideExtension {


    private MyGlideExtension() {
    }

    @GlideOption
    public static void imageProgressListener(RequestOptions options, String bigUrl, ImageView image) {

        ImageLoadingView load = new ImageLoadingView(image.getContext());
        load.setInsideCircleColor(R.color.eee);
        load.setOutsideCircleColor(R.color.eee);
        ImageProgressInterceptor.addListener(bigUrl, new ImageProgressListener() {
            @Override
            public void onProgress(double progress) {
                load.setTargetView(image);
                load.setProgress(progress);
            }
        });

    }
}
