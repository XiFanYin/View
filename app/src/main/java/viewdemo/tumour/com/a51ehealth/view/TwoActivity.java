package viewdemo.tumour.com.a51ehealth.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.security.MessageDigest;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import viewdemo.tumour.com.a51ehealth.view.app.App;
import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.GlideApp;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.ImageLoadingView;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.ImageProgressListener;

/**
 * Created by Administrator on 2018/2/23.
 */

public class TwoActivity extends BaseActivity {

    private ImageView image2;
    private String bigUrl;
    private String smallUrl;

    @Override
    public int getId() {
        return R.layout.activity_two;
    }

    @Override
    protected void initView() {
        image2 = findViewById(R.id.image2);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(boolean hasNetWork) {

        bigUrl = getIntent().getStringExtra("bigUrl");
        smallUrl = getIntent().getStringExtra("smallUrl");

        GlideApp.with(this)
                .load(bigUrl)
                .imageProgressListener(bigUrl, image2)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image2);


    }
}
