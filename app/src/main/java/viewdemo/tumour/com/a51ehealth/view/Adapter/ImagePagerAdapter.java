package viewdemo.tumour.com.a51ehealth.view.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import viewdemo.tumour.com.a51ehealth.view.R;
import viewdemo.tumour.com.a51ehealth.view.TwoActivity;
import viewdemo.tumour.com.a51ehealth.view.bean.ImageUrl;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.GlideApp;

/**
 * Created by Administrator on 2018/2/27.
 */

public class ImagePagerAdapter extends PagerAdapter {

    private ArrayList<ImageUrl> mDatas;
    private Context mContext;


    public ImagePagerAdapter(ArrayList<ImageUrl> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View mView = LayoutInflater.from(container.getContext()).inflate(R.layout.home_weiboitem_imagedetails_item, null);
        ViewHolder holder = new ViewHolder(mView);
        GlideApp
                .with(mContext)
                .load(mDatas.get(position).getBigImage())
                .imageProgressListener(mDatas.get(position).getBigImage(), holder.photo_view)
                .thumbnail(GlideApp.with(mContext).load(mDatas.get(position).getSmallImage()).transforms(new ColorFilterTransformation(0x22222222)))
                .into(holder.photo_view);


        container.addView(mView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return mView;
    }

    public static class ViewHolder {
        public View rootView;
        public PhotoView photo_view;
        public RelativeLayout ImageViewItemLayout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.photo_view = (PhotoView) rootView.findViewById(R.id.photo_view);
            this.ImageViewItemLayout = (RelativeLayout) rootView.findViewById(R.id.ImageViewItemLayout);
        }

    }
}
