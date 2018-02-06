package viewdemo.tumour.com.a51ehealth.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxFragment;


import viewdemo.tumour.com.a51ehealth.view.app.App;
import viewdemo.tumour.com.a51ehealth.view.net.utils.NetworkDetector;


/**
 * Created by yinfeilong on 2017/8/28.
 */

public abstract class BaseFragment extends RxFragment {

    protected View mView;
    protected boolean isViewInitiated; //当前页面是否初始化
    protected boolean isVisibleToUser; //当前页面是否显示
    protected boolean isDataRequested; //是否已经请求了数据
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getContext();
        mView = inflater.inflate(getLayoutId(), null);
        isViewInitiated = true;
        initView(mView);
        initListener();
        prepareGetData();
        return mView;
    }

    /*初始化页面布局和数据*/
    protected abstract void initView(View view);

    /*布局*/
    public abstract int getLayoutId();

    /*服务器获取数据*/
    protected abstract void getData(boolean hasNetWork);

    protected abstract void initListener();

    /**
     * 当前页面是否展示
     *
     * @param isVisibleToUser 显示为true， 不显示为false
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareGetData();

    }

    /**
     * 如果只想第一次进入该页面请求数据，return prepareGetData(false)
     * 如果想每次进入该页面就请求数据，return prepareGetData(true)
     *
     * @return
     */
    public boolean prepareGetData() {
        return prepareGetData(false);
    }

    /**
     * 判断是否从服务器器获取数据
     *
     * @param isforceUpdate 强制更新的标记
     * @return
     */
    protected boolean prepareGetData(boolean isforceUpdate) {
        if (isViewInitiated && isVisibleToUser) {
            initBar();
        }
        if (isVisibleToUser && isViewInitiated && (!isDataRequested || isforceUpdate)) {
            /*从服务器获取数据*/
            getData(NetworkDetector.isNetworkReachable());
            isDataRequested = true;
            return true;
        }

        return false;
    }

    protected void initBar() {
    }

    ;

    /**
     * 弹出吐司,子类里边也可以
     *
     * @param msg
     */
    public void showToast(final String msg) {

        Toast.makeText(App.getApplication(), msg, Toast.LENGTH_SHORT).show();


    }
}
