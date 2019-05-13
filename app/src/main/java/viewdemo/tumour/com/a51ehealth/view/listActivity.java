package viewdemo.tumour.com.a51ehealth.view;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import viewdemo.tumour.com.a51ehealth.view.Adapter.listAdapter;
import viewdemo.tumour.com.a51ehealth.view.base.BaseActivity;
import viewdemo.tumour.com.a51ehealth.view.bean.ImageUrl;
import viewdemo.tumour.com.a51ehealth.view.bean.imageData;

/**
 * Created by Administrator on 2018/2/26.
 */

public class listActivity extends BaseActivity {
    public static final String cat = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557742686839&di=834eb42da8df6c78facfce8c80fc9d86&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2Fw1408h880%2F20180209%2F8c5d-fyrkuxs5895248.jpg";
    public static final String cat_thumbnail = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2708713206,191050581&fm=26&gp=0.jpg";
    public static final String girl_1 = "http://img5.imgtn.bdimg.com/it/u=2210270529,1470505196&fm=11&gp=0.jpg";
    public static final String girl_1_1 = "http://pic1.win4000.com/wallpaper/2017-10-14/59e1bb9f01314.jpg";
    public static final String girl_2 = "http://img4.imgtn.bdimg.com/it/u=358796479,4085336161&fm=200&gp=0.jpg";
    public static final String girl_2_2 = "http://imgsrc.baidu.com/imgad/pic/item/e1fe9925bc315c600dce09d386b1cb13495477b6.jpg";
    public static final String girl_3 = "http://img5.imgtn.bdimg.com/it/u=2574540780,4027686843&fm=27&gp=0.jpg";
    public static final String girl_3_3 = "http://pic.yesky.com/uploadImages/2015/131/34/7YHR2JH18SVK.jpg";
    public static final String girl_4 = "http://img3.imgtn.bdimg.com/it/u=88624701,1581010806&fm=27&gp=0.jpg";
    public static final String girl_4_4 = "http://img5.xiazaizhijia.com/walls/20160108/1024x768_d33e81709cb5f3b.jpg";
    public static final String girl_5 = "http://img1.imgtn.bdimg.com/it/u=2544269114,2104066965&fm=27&gp=0.jpg";
    public static final String girl_5_5 = "http://img5.xiazaizhijia.com/walls/20160708/1440x900_2f172c09d079701.jpg";
    public static final String girl_6 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1604231005,747716338&fm=26&gp=0.jpg";
    public static final String girl_6_6 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557742759179&di=188c7cbaface079f85979397c19d259d&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201604%2F09%2F20160409151416_UxJTh.jpeg";
    public static final String girl_7 = "http://img3.imgtn.bdimg.com/it/u=1969777019,978878890&fm=200&gp=0.jpg";
    public static final String girl_7_7 = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=36cb4c8fc2177f3e0439f44d19b75eab/38dbb6fd5266d016ee2740119c2bd40735fa35c9.jpg";
    public static final String girl_8 = "http://img5.imgtn.bdimg.com/it/u=1963296401,1710893802&fm=200&gp=0.jpg";
    public static final String girl_8_8 = "http://imgsrc.baidu.com/imgad/pic/item/314e251f95cad1c88543451a743e6709c93d5116.jpg";
    public static final String girl_9 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3235909679,1979224161&fm=26&gp=0.jpg";
    public static final String girl_9_9 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557742801997&di=9bf05ece9d86eba273c4537dfea353cb&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180915%2F0b8534bad0354407acac0d17af8b0698.jpeg";
    public static final String girl_10 = "http://img5.imgtn.bdimg.com/it/u=865490461,298614916&fm=27&gp=0.jpg";
    public static final String girl_10_10 = "http://www.5857.com/uploadfile/2016/0725/20160725055407737.jpg";
    public static final String girl_11 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3331474088,637356087&fm=26&gp=0.jpg";
    public static final String girl_11_11 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557742849594&di=3564f1d20eb290a47644ccd02421c964&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201805%2F12%2F20180512100412_VnwYL.thumb.700_0.jpeg";
    public static final String girl_12 = "http://img5.imgtn.bdimg.com/it/u=3471110637,974441856&fm=27&gp=0.jpg";
    public static final String girl_12_12 = "http://pic.yesky.com/uploadImages/2015/290/32/TD37P8PSGN9Z.jpg";
    public static final String girl_13 = "http://img4.imgtn.bdimg.com/it/u=2523485076,2104293716&fm=27&gp=0.jpg";
    public static final String girl_13_13 = "http://old.bz55.com/uploads/allimg/150820/140-150R0091925.jpg";
    public static final String girl_14 = "http://img1.imgtn.bdimg.com/it/u=2016826161,3846692402&fm=27&gp=0.jpg";
    public static final String girl_14_14 = "http://lili.la/zb_users/upload/2017/05/201705171494959833124433.jpg";
    public final String bigUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557742870868&di=5cf73b1a2389c340501189c9c3a61434&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Faa64034f78f0f7368cc3aaba0b55b319ebc4134f.jpg";
    public final String smallUrl = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1527476420,4180809987&fm=26&gp=0.jpg";

    private RecyclerView recyclerView;

    private ArrayList<imageData> arr = new ArrayList<>();
    private listAdapter adapter;

    public static String flag;


    @Override
    public int getId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }


    @Override
    protected void initListener() {
        ArrayList<ImageUrl> Images = new ArrayList<>();
        Images.clear();
        ImageUrl imageurl = new ImageUrl(cat_thumbnail, cat);
        Images.add(imageurl);
        arr.add(new imageData("猫", Images));


        ArrayList<ImageUrl> Images1 = new ArrayList<>();
        Images1.clear();
        ImageUrl imageurl2 = new ImageUrl(girl_1, girl_1_1);
        ImageUrl imageurl3 = new ImageUrl(girl_2, girl_2_2);
        Images1.add(imageurl2);
        Images1.add(imageurl3);
        arr.add(new imageData("俩女孩", Images1));


        ArrayList<ImageUrl> Images2 = new ArrayList<>();
        Images2.clear();
        ImageUrl imageurl4 = new ImageUrl(girl_3, girl_3_3);
        ImageUrl imageurl5 = new ImageUrl(girl_4, girl_4_4);
        ImageUrl imageurl6 = new ImageUrl(girl_5, girl_5_5);
        Images2.add(imageurl4);
        Images2.add(imageurl5);
        Images2.add(imageurl6);
        arr.add(new imageData("三女孩", Images2));


        ArrayList<ImageUrl> Images3 = new ArrayList<>();
        Images3.clear();
        ImageUrl imageurl7 = new ImageUrl(girl_6, girl_6_6);
        ImageUrl imageurl8 = new ImageUrl(girl_7, girl_7_7);
        ImageUrl imageurl9 = new ImageUrl(girl_8, girl_8_8);
        ImageUrl imageurl10 = new ImageUrl(girl_9, girl_9_9);

        Images3.add(imageurl7);
        Images3.add(imageurl8);
        Images3.add(imageurl9);
        Images3.add(imageurl10);

        arr.add(new imageData("四女孩", Images3));


        ArrayList<ImageUrl> Images4 = new ArrayList<>();
        Images4.clear();
        ImageUrl imageurl13 = new ImageUrl(girl_10, girl_10_10);
        ImageUrl imageurl14 = new ImageUrl(girl_11, girl_11_11);
        ImageUrl imageurl15 = new ImageUrl(girl_12, girl_12_12);
        ImageUrl imageurl16 = new ImageUrl(girl_13, girl_13_13);
        ImageUrl imageurl17 = new ImageUrl(girl_14, girl_14_14);
        ImageUrl imageurl18 = new ImageUrl(smallUrl, bigUrl);
        Images4.add(imageurl13);
        Images4.add(imageurl14);
        Images4.add(imageurl15);
        Images4.add(imageurl16);
        Images4.add(imageurl17);
        Images4.add(imageurl18);
        arr.add(new imageData("六女孩", Images4));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setExitSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    super.onMapSharedElements(names, sharedElements);

                    ViewGroup vg = recyclerView.findViewWithTag(flag);
                    if (vg != null) {
                        View view = vg.findViewById(R.id.image_item);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            sharedElements.put(view.getTransitionName(), view);
                        }
                    } else {
                        names.clear();
                        sharedElements.clear();
                    }
                }
            });
        }
    }

    @Override
    protected void initData(boolean hasNetWork) {
        adapter = new listAdapter(R.layout.list_item, arr, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }


}
