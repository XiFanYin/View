package viewdemo.tumour.com.a51ehealth.view.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import viewdemo.tumour.com.a51ehealth.view.bean.ImageUrl;
import viewdemo.tumour.com.a51ehealth.view.fragment.ImageFragment;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ImageFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ImageUrl> mDatas;

    public ImageFragmentPagerAdapter(FragmentManager fm, ArrayList<ImageUrl> mDatas) {
        super(fm);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }


    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(mDatas.get(position));
    }
}
