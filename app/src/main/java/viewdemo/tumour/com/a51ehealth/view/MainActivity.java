package viewdemo.tumour.com.a51ehealth.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.bean.LoginBean;
import viewdemo.tumour.com.a51ehealth.view.bean.Patient;
import viewdemo.tumour.com.a51ehealth.view.bean.UpImage;
import viewdemo.tumour.com.a51ehealth.view.cache.CacheProviderUtils;
import viewdemo.tumour.com.a51ehealth.view.cache.Provider;
import viewdemo.tumour.com.a51ehealth.view.net.API.API;
import viewdemo.tumour.com.a51ehealth.view.net.Observer.BaseObserver;
import viewdemo.tumour.com.a51ehealth.view.net.RetrofitUtil;
import viewdemo.tumour.com.a51ehealth.view.net.Schedulers.RxSchedulers;
import viewdemo.tumour.com.a51ehealth.view.net.UpFile.UpFileUtils;
import viewdemo.tumour.com.a51ehealth.view.net.utils.NetworkDetector;
import viewdemo.tumour.com.a51ehealth.view.utils.SPUtils;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.GlideApp;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.ImageLoadingView;

public class MainActivity extends BaseActivity {


    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private TextView tv;
    private ImageView image;
    private ProgressDialog pd;
    private File file = new File(Environment.getExternalStorageDirectory().getPath(), "wisdom_doctor.apk");
    private View btn5;
    private String url = "http://img1.3lian.com/2015/w7/90/d/1.jpg";


    @Override
    public int getId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        tv = findViewById(R.id.tv);
        image = findViewById(R.id.image);

    }

    @Override
    protected void initListener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    protected void initData(boolean hasNetWork) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1://普通请求

                method1();

                break;

            case R.id.btn2://上传图片

                method2();

                break;

            case R.id.btn3://下载文件
                new RxPermissions(MainActivity.this)
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    method3();

                                }
                            }
                        });

                break;

            case R.id.btn4://有网请求最新并缓存，没网获取本地缓存

                method4();

                break;

            case R.id.btn5:

                /**
                 * 待解决问题：
                 *
                 * 1.如果有小图，可以让加载大图的时候显示小图，没有小图的时候，再显示灰色背景，切背景可以有外部任意指定
                 * 2.让其用的时候，布局更加舒服
                 *
                 */
                ImageLoadingView loadingView = new ImageLoadingView(this);
                loadingView.setTargetView(image);
                GlideApp
                        .with(this)
                        .load(url)
                        .imageProgressListener(url, progress -> {
                            loadingView.setProgress(progress);
                        })
                        .skipMemoryCache(true)//进制内存缓存
                        .diskCacheStrategy(DiskCacheStrategy.NONE)//进制磁盘缓存
                        .into(image);


                break;


        }


    }

    private void method1() {

        RetrofitUtil.
                getInstance()
                .create(API.class)
                .Login("hewang", "030303")
                .compose(RxSchedulers.io_main())
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        SPUtils.saveString("token", loginBean.getData().getToken());

                        tv.setText(new Gson().toJson(loginBean) + "token必须保存起来，这样在上传图片和下载图片的时候，请求头里边需要放入的参数");
                    }


                });

    }

    private void method2() {
        String[] arr = {"/storage/emulated/0/crop_photo.jpg"};
        RetrofitUtil.
                getInstance()
                .create(API.class)
                .UpCertificate(UpFileUtils.files2Parts("key", arr, MediaType.parse("image/png")))
                .compose(RxSchedulers.io_main())
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<UpImage>() {
                    @Override
                    public void onNext(UpImage upImage) {
                        tv.setText(upImage.getData().getZhaop_url());
                        GlideApp.with(MainActivity.this).load(upImage.getData().getZhaop_url()).into(image);
                    }
                });
    }

    private void method3() {

        RetrofitUtil
                .getInstance()
                .create(API.class)
                .downloadFile("http://47.93.136.56:7012/app/doctorhelper/doctorhelper.apk")
                .subscribeOn(Schedulers.io())//指定联网的线程
                .observeOn(AndroidSchedulers.mainThread())//为了显示进度条，这里指定下边map的线程为Main线程
                .map(new Function<ResponseBody, ResponseBody>() {
                    @Override
                    public ResponseBody apply(@NonNull ResponseBody responseBody) throws Exception {
                        pd = new ProgressDialog(MainActivity.this);
                        pd.setMessage("下载中...");
                        //點擊以外的區域不消失
                        pd.setCanceledOnTouchOutside(false);
                        // 设置为水平进度条样式
                        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        pd.setMax(100);
                        pd.show();
                        return responseBody;
                    }
                })
                .observeOn(Schedulers.io())//指定下边读写文件的线程 io线程
                .map(new Function<ResponseBody, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull ResponseBody responseBody) throws Exception {
                        InputStream inputStream = responseBody.byteStream();
                        OutputStream outputStream = new FileOutputStream(file);

                        try {
                            byte[] fileReader = new byte[1024 * 1024 * 5];
                            long fileSize = responseBody.contentLength();
                            long fileSizeDownloaded = 0;
                            inputStream = responseBody.byteStream();
                            outputStream = new FileOutputStream(file);
                            while (true) {
                                int read = inputStream.read(fileReader);
                                if (read == -1) {
                                    break;
                                }
                                outputStream.write(fileReader, 0, read);
                                fileSizeDownloaded += read;
                                //下载进度的走势
                                Log.e("OkHttpDown", "file download: " + fileSizeDownloaded + " of " + fileSize);

                                pd.setProgress((int) (100 * fileSizeDownloaded / fileSize));
                            }
                            outputStream.flush();
                            return true;
                        } catch (Exception e) {
                            return false;
                        } finally {
                            if (inputStream != null) {
                                inputStream.close();
                            }

                            if (outputStream != null) {
                                outputStream.close();
                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//指定更新ui的线程，这里指定为Main线程
                .compose(this.<Boolean>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {

                        pd.dismiss();

                    }
                });
    }

    private void method4() {


        CacheProviderUtils
                .getInstance()
                .using(Provider.class)
                .getPatientInfo(RetrofitUtil
                        .getInstance()
                        .create(API.class)
                        .getPatientInfo(1, 2), new DynamicKey("eee"), new EvictDynamicKey(NetworkDetector.isNetworkReachable()))
                .compose(RxSchedulers.io_main())
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<Patient>() {
                    @Override
                    public void onNext(Patient patient) {
                        tv.setText(new Gson().toJson(patient) + "token必须保存起来，这样在上传图片和下载图片的时候，请求头里边需要放入的参数");

                        Log.e("BeanJson3", new Gson().toJson(patient));
                    }
                });


    }


}
