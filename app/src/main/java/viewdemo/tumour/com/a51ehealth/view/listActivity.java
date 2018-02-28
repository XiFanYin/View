package viewdemo.tumour.com.a51ehealth.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import viewdemo.tumour.com.a51ehealth.view.Adapter.listAdapter;
import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.bean.ImageUrl;
import viewdemo.tumour.com.a51ehealth.view.bean.imageData;

/**
 * Created by Administrator on 2018/2/26.
 */

public class listActivity extends BaseActivity {



    @Override
    public int getId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {



    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(boolean hasNetWork) {
        //获取FragmentManager对象，并添加
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, new listFragment(), listFragment.class.getSimpleName())
                .commit();
    }
}
