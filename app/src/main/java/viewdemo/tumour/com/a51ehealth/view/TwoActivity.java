package viewdemo.tumour.com.a51ehealth.view;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.GlideApp;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.ImageLoadingView;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.ImageProgressInterceptor;
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

        GlideApp
                .with(TwoActivity.this)
                .load(bigUrl)
                .imageProgressListener(bigUrl, image2)
                .thumbnail(GlideApp.with(TwoActivity.this).load(smallUrl).transforms(new ColorFilterTransformation(0x22222222)))
                .into(image2);


    }

}
