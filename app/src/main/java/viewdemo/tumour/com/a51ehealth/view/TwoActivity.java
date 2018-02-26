package viewdemo.tumour.com.a51ehealth.view;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.GlideApp;

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
        //切记不要跳过硬盘缓存diskCacheStrategy(DiskCacheStrategy.NONE)否则会加载不出来
        GlideApp.with(this)
                .load(bigUrl)
                .skipMemoryCache(true)
                .imageProgressListener(smallUrl, bigUrl, image2)
                .preload();


    }
}
