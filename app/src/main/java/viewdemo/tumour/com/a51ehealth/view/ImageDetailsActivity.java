package viewdemo.tumour.com.a51ehealth.view;

import android.app.SharedElementCallback;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import viewdemo.tumour.com.a51ehealth.view.Adapter.ImageFragmentPagerAdapter;
import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.bean.ImageUrl;
import viewdemo.tumour.com.a51ehealth.view.weight.GlideView.SlideViewPager;


public class ImageDetailsActivity extends BaseActivity {

    private SlideViewPager viewPager;
    private ArrayList<ImageUrl> data;
    private int position;
    private ImageFragmentPagerAdapter mAdapter;
    private TextView mImageDetailTopBar;


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
        //创建Adapter,这里必须用FragmentAdapter，否则会出现图片错乱
        mAdapter = new ImageFragmentPagerAdapter(getSupportFragmentManager(), data);
        //设置Adapter
        viewPager.setAdapter(mAdapter);
        //设置当前为第几张图片
        viewPager.setCurrentItem(position);
        //设置默认指示器是否显示
        if (data.size() == 1) {
            mImageDetailTopBar.setVisibility(View.GONE);
        } else {
            mImageDetailTopBar.setText((position + 1) + "/" + data.size());
        }
        //添加滚动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            public void onPageSelected(int position) {
                // 每当页数发生改变时重新设定一遍当前的页数和总页数
                mImageDetailTopBar.setText((position + 1) + "/" + data.size());
            }


            public void onPageScrollStateChanged(int state) {

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setEnterSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    super.onMapSharedElements(names, sharedElements);

                    Fragment currentFragment = (Fragment) viewPager.getAdapter().instantiateItem(viewPager, viewPager.getCurrentItem());
                    View view = currentFragment.getView();

                    if (view == null) {
                        return;
                    }
                    sharedElements.put(data.get(viewPager.getCurrentItem()).getBigImage(), view.findViewById(R.id.photo_view));
                }
            });
        }

    }


    @Override
    protected void initData(boolean hasNetWork) {

    }

    @Override
    public void finishAfterTransition() {
        //重写关闭方法，传递flag值
        listActivity.flag = data.get(viewPager.getCurrentItem()).getSmallImage();
        super.finishAfterTransition();
    }


}
