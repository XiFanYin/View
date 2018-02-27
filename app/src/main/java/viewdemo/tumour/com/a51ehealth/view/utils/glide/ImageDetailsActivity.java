package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

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
    private ImageDetailTopBar mImageDetailTopBar;

    @Override
    public int getId() {
        return R.layout.home_weiboitem_imagedetails;
    }

    @Override
    protected void initView() {
        //获取数据
        data = (ArrayList<ImageUrl>) getIntent().getSerializableExtra("images");
        position = getIntent().getIntExtra("position", 0);
        //找到控件
        viewPager = findViewById(R.id.viewpagerId);
        mImageDetailTopBar = findViewById(R.id.imageTopBar);

    }

    @Override
    protected void initListener() {
        //创建Adapter
        mAdapter = new ImagePagerAdapter(data, this);
        //设置Adapter
        viewPager.setAdapter(mAdapter);
        //设置当前为第几张图片
        viewPager.setCurrentItem(position);
        //预加载页数
        viewPager.setOffscreenPageLimit(data.size() - 1);
        //设置默认指示器是否显示
        if (data.size() == 1) {
            mImageDetailTopBar.setPageNumVisible(View.GONE);
        } else {
            mImageDetailTopBar.setPageNum((position + 1) + "/" + data.size());
        }
        //添加滚动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            public void onPageSelected(int position) {
                // 每当页数发生改变时重新设定一遍当前的页数和总页数
                mImageDetailTopBar.setPageNum((position + 1) + "/" + data.size());
            }


            public void onPageScrollStateChanged(int state) {

            }
        });
        //添加点击监听
        mAdapter.setOnSingleTagListener(new ImagePagerAdapter.OnSingleTagListener() {
            @Override
            public void onTag() {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }

            }
        });
    }


    @Override
    protected void initData(boolean hasNetWork) {

    }


}
