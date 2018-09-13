package viewdemo.tumour.com.a51ehealth.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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
import viewdemo.tumour.com.a51ehealth.view.net.cookie.WebViewUtils;
import viewdemo.tumour.com.a51ehealth.view.net.utils.NetworkDetector;
import viewdemo.tumour.com.a51ehealth.view.utils.PhoneUtils.BottomPopUpDialog;
import viewdemo.tumour.com.a51ehealth.view.utils.PhoneUtils.PhotoUtils;
import viewdemo.tumour.com.a51ehealth.view.utils.SPutils.SPUtils;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.GlideApp;

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
    private View btn6;
    private View btn7;
    private View btn8;
    private File file2;
    private Uri camera_uri;
    private static final int REQUEST_PICTURE = 400;
    private static final int REQUEST_CAMERA = 500;

    //这是是有裁剪回调的代码
//    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
//    private static final int CODE_RESULT_REQUEST = 300;
    public static final String smallUrl = "http://img1.imgtn.bdimg.com/it/u=2016826161,3846692402&fm=27&gp=0.jpg";
    public static final String bigUrl = "http://lili.la/zb_users/upload/2017/05/201705171494959833124433.jpg";

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
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
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
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        image.setOnClickListener(this);
    }

    @Override
    protected void initData(boolean hasNetWork) {
        method4();
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

                Glide.with(this)
                        .load(smallUrl)
                        .into(image);

                break;

            case R.id.btn6:


                method6();

                break;


            case R.id.btn7:

                jumpToActivity(listActivity.class);

                break;


            case R.id.image:

                Intent intent = new Intent(this, TwoActivity.class);
                intent.putExtra("bigUrl", bigUrl);
                intent.putExtra("smallUrl", smallUrl);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, image, getString(R.string.transition_image));
                ActivityCompat.startActivity(MainActivity.this, intent, compat.toBundle());

                break;

            case R.id.btn8:

                jumpToActivity(WebActivity.class);

                break;

            default:
                break;

        }


    }


    private void method1() {

        RetrofitUtil.
                getInstance()
                .create(API.class)
                .Login("wangyong", "111111")
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

    //注意：这里是先读取缓存，然后再请求网络的思路 ，注意不能使用合并操作符，会出现问题
    private void method4() {

        //只读取缓存
        CacheProviderUtils.getInstance().using(Provider.class)
                .getPatientInfo(Observable.empty(), new DynamicKey("eee"), new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//指定doOnTerminate的线程
                .subscribe(new BaseObserver<Patient>() {
                    @Override
                    public void onNext(Patient patient) {
                        tv.setText(new Gson().toJson(patient) + "token必须保存起来，这样在上传图片和下载图片的时候，请求头里边需要放入的参数");
                        Log.e("BeanJson3", new Gson().toJson(patient));

                    }
                });

        //只读取网络
        RetrofitUtil
                .getInstance()
                .create(API.class)
                .getPatientInfo(1, 2)
                .flatMap(it -> {//这里是添加缓存，如果API异常，就会直接在自定义的解析器中抛出异常，代码不会走到这里。
                    return CacheProviderUtils.getInstance().using(Provider.class)
                            .getPatientInfo(Observable.just(it), new DynamicKey("eee"), new EvictDynamicKey(true));
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())//指定doOnTerminate的线程
                .subscribe(new BaseObserver<Patient>() {
                    @Override
                    public void onNext(Patient patient) {
                        tv.setText(new Gson().toJson(patient) + "token必须保存起来，这样在上传图片和下载图片的时候，请求头里边需要放入的参数");
                        Log.e("BeanJson3", new Gson().toJson(patient));

                    }
                });


    }

    private void method6() {
        String[] data = {"拍照", "从相册中选择"};
        new BottomPopUpDialog.Builder()
                .setDialogData(data)
                .setItemTextColor(2, R.color.colorAccent)
                .setItemTextColor(4, R.color.colorAccent)
                .setCallBackDismiss(true)
                .setItemLineColor(R.color.line_color)
                .setItemOnListener(new BottomPopUpDialog.BottomPopDialogOnClickListener() {
                    @Override
                    public void onDialogClick(String tag) {

                        if ("拍照".equals(tag)) {

                            camera();

                        } else if ("从相册中选择".equals(tag)) {

                            imagePicker();
                        }

                    }
                })
                .show(getSupportFragmentManager(), "tag");

    }


    //打开相机
    private void camera() {
        new RxPermissions(this)
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            //创建照片存储路径
                            file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/zjyl/" + System.currentTimeMillis() + ".jpg");
                            file2.getParentFile().mkdirs();
                            //根据路径获取uri
                            camera_uri = Uri.fromFile(file2);
                            //如果版本是n，就从新设置这个uri
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                camera_uri = FileProvider.getUriForFile(MainActivity.this, "viewdemo.tumour.com.a51ehealth.view.FileProvider", file2);//通过FileProvider创建一个content类型的Uri
                                //添加权限
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            }
                            //将拍照结果保存至photo_file的Uri中，不保留在相册中
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_uri);
                            //开启相机
                            startActivityForResult(intent, REQUEST_CAMERA);

                        } else {

                            showToast("请在设置中打开拍照权限");
                        }

                    }
                });

    }

    //打开图库
    private void imagePicker() {

        new RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {

                            Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                            photoPickerIntent.setType("image/*");
                            startActivityForResult(photoPickerIntent, REQUEST_PICTURE);

                        } else {

                            showToast("请在设置中打开读取sd卡权限");
                        }

                    }
                });
    }


    @Override//这个是没有裁剪头像的回调代码
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICTURE://打开系统相册，获取图片地址

                    Uri uri = data.getData();
                    String path = PhotoUtils.getPath(this, uri);
                    //截取工具类中的多余的字符串，如果要前边的路径就会报错，还不知道原因
                    path = path.substring(7, path.length());
                    image.setImageBitmap(BitmapFactory.decodeFile(path));

                    break;


                case REQUEST_CAMERA://打开系统相机获取图片地址

                    String absolutePath = file2.getAbsolutePath();
                    image.setImageBitmap(BitmapFactory.decodeFile(absolutePath));
                    break;


            }

        }
    }

    //这是是有裁剪回调的代码
 /*   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case REQUEST_PICTURE://打开系统相册，获取图片地址
                    //通过裁剪路径获取裁剪的uri
                    Uri cropImageUri2 = Uri.fromFile(fileCropUri);
                    //通过系统回调回获取照片的uri
                    Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                    //如果版本大于N从新给uri复制
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        newUri = FileProvider.getUriForFile(this, "viewdemo.tumour.com.a51ehealth.view.FileProvider", new File(newUri.getPath()));

                    PhotoUtils.cropImageUri(this, newUri, cropImageUri2, 1, 1, 480, 480, CODE_RESULT_REQUEST);


                    break;


                case REQUEST_CAMERA:

                    //把裁剪的路径转换成uri
                    Uri cropImageUri = Uri.fromFile(fileCropUri);
                    //开启裁剪
                    PhotoUtils.cropImageUri(this, camera_uri, cropImageUri, 1, 1, 480, 480, CODE_RESULT_REQUEST);

                    break;

                case CODE_RESULT_REQUEST://裁剪的回调

                    image.setImageBitmap(BitmapFactory.decodeFile(fileCropUri.getAbsolutePath()));

                    break;


            }

        }
    }*/


}
