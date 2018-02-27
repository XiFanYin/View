package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import android.util.Log;

import java.util.ArrayList;

import viewdemo.tumour.com.a51ehealth.view.Adapter.ImagePagerAdapter;
import viewdemo.tumour.com.a51ehealth.view.R;
import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.bean.ImageUrl;


public class ImageDetailsActivity extends BaseActivity {

    private ImageDetailViewPager viewPager;
    private ArrayList<ImageUrl> data;
    private int position;
    private ImagePagerAdapter mAdapter;

    @Override
    public int getId() {
        return R.layout.home_weiboitem_imagedetails;
    }

    @Override
    protected void initView() {
        data = (ArrayList<ImageUrl>) getIntent().getSerializableExtra("images");
        position = getIntent().getIntExtra("position", 0);
        viewPager = findViewById(R.id.viewpagerId);


    }

    @Override
    protected void initListener() {

        mAdapter = new ImagePagerAdapter(data, this);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(position);
    }


    @Override
    protected void initData(boolean hasNetWork) {

    }


}
