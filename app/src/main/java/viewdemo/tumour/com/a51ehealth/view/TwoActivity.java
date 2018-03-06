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
        //获取传入小图的url和大图的url地址
        bigUrl = getIntent().getStringExtra("bigUrl");
        smallUrl = getIntent().getStringExtra("smallUrl");
        /**
         * thumbnail操作符，可以设置本地加载图片，也可以设置网络加载图片为背景，这里设置小图位加载的预览图
         *
         * transforms操作符，变换图片，这里设置一个几乎透明的颜色过滤器，让用户可以感觉到大图加载完成有明显的视觉变化
         *
         */
        GlideApp
                .with(TwoActivity.this)
                .load(bigUrl)
                .imageProgressListener(bigUrl, image2)
                .thumbnail(GlideApp.with(TwoActivity.this).load(smallUrl).transforms(new ColorFilterTransformation(0x22222222)))
                .into(image2);


    }

}
