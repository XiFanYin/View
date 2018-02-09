package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Created by Administrator on 2018/2/9.
 */

@GlideModule
public class GlideConfiguration extends AppGlideModule {


    @Override//此方法是用来替换Glide组件的方法，在这里可以替换底层联网框架
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpUtils.getInstance().getOkHttpClient()));
    }
}