package viewdemo.tumour.com.a51ehealth.view.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import viewdemo.tumour.com.a51ehealth.view.R;
import viewdemo.tumour.com.a51ehealth.view.app.App;
import viewdemo.tumour.com.a51ehealth.view.net.utils.NetworkDetector;


/**
 * Created by yinfeilong on 2017/8/28.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {

    public BaseActivity act;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindow();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设制竖屏
        //隐藏掉系统原先的导航栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getId());


        //二.处理相同逻辑
        dealCommon();

        initView();

        initListener();

        initData(NetworkDetector.isNetworkReachable());


    }

    protected void initWindow() {
    }




    public abstract int getId();

    protected abstract void initView();

    protected abstract void initListener();


    protected abstract void initData(boolean hasNetWork);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        ImmersionBar.with(this).destroy();
    }


    private void dealCommon() {

        act = this;

        View btnBack = findViewById(R.id.back);
        if (btnBack != null) {
            btnBack.setOnClickListener(this);
        }

        //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
        ImmersionBar.with(act).statusBarColor(R.color.colorAccent).fitsSystemWindows(true).init();


    }

    //自定义返回键的统一处理
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back:
//                返回逻辑
                finish();
                break;
        }


    }

    /**
     * 弹出吐司,子类里边也可以
     *
     * @param msg
     */
    public void showToast(final String msg) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(App.getApplication(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * 抽取积累的方法跳转界面
     */
    protected void jumpToActivity(Class<? extends Activity> actClass) {
        Intent intent = new Intent(act, actClass);
        startActivity(intent);
    }

    /**
     * 抽取积累的方法跳转界面 并且将自己关闭
     */
    protected void jumpToActivityAndFinish(Class<? extends Activity> actClass) {
        jumpToActivity(actClass);
        finish();
    }


}