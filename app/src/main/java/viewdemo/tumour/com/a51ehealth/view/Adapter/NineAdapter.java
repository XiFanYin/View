package viewdemo.tumour.com.a51ehealth.view.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import viewdemo.tumour.com.a51ehealth.view.MainActivity;
import viewdemo.tumour.com.a51ehealth.view.R;
import viewdemo.tumour.com.a51ehealth.view.TwoActivity;
import viewdemo.tumour.com.a51ehealth.view.bean.ImageUrl;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.GlideApp;

/**
 * Created by Administrator on 2018/2/26.
 */

public class NineAdapter extends BaseQuickAdapter<ImageUrl, BaseViewHolder> {


    private Activity act;

    public NineAdapter(int layoutResId, @Nullable List<ImageUrl> data, Activity act) {
        super(layoutResId, data);

        this.act = act;
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageUrl item) {

        ImageView imageView = helper.getView(R.id.image_item);
        GlideApp.with(act)
                .load(item.getSmallImage())
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(act, TwoActivity.class);
                intent.putExtra("bigUrl", item.getBigImage());
                intent.putExtra("smallUrl", item.getSmallImage());
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(act, imageView, act.getString(R.string.transition_image));
                ActivityCompat.startActivity(act, intent, compat.toBundle());
            }
        });

    }
}
