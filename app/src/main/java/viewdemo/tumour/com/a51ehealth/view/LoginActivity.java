package viewdemo.tumour.com.a51ehealth.view;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.bean.LoginBean;
import viewdemo.tumour.com.a51ehealth.view.net.API.API;
import viewdemo.tumour.com.a51ehealth.view.net.Exception.ApiException;
import viewdemo.tumour.com.a51ehealth.view.net.Observer.BaseObserver;
import viewdemo.tumour.com.a51ehealth.view.net.RetrofitUtil;
import viewdemo.tumour.com.a51ehealth.view.utils.SPutils.SPUtils;

public class LoginActivity extends BaseActivity {

    private EditText name;
    private EditText pwd;
    private Button login;

    @Override
    public int getId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        name.setText("wangyong");
        pwd.setText("111111");
    }

    @Override
    protected void initListener() {
        login.setOnClickListener(this);
    }

    @Override
    protected void initData(boolean hasNetWork) {

    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.login:

                String names = name.getText().toString();
                String pwds = pwd.getText().toString();

                if (!TextUtils.isEmpty(names) && !TextUtils.isEmpty(pwds)) {
                    RetrofitUtil.
                            getInstance()
                            .create(API.class)
                            .Login(names, pwds)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new BaseObserver<LoginBean>() {
                                @Override
                                public void onNext(LoginBean loginBean) {
                                    SPUtils.saveString("token", loginBean.getData().getToken());
                                    Intent intent = new Intent();
                                    intent.putExtra("loginSucceed", true);
                                    setResult(Activity.RESULT_OK, intent);
                                    finish();
                                }

                            });


                } else {
                    Toast.makeText(act, "用户名和密码不能是空", Toast.LENGTH_SHORT).show();
                }


                break;

            default:
                Intent intent = new Intent();
                intent.putExtra("loginSucceed", false);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }

    }

}
