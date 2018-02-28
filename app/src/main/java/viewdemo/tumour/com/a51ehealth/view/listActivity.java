package viewdemo.tumour.com.a51ehealth.view;

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
    public static final String cat = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/cat.jpg";
    public static final String cat_thumbnail = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/cat_thumbnail.jpg";
    public static final String girl_1 = "http://www.mypublic.top/long/1.jpg";
    public static final String girl_1_1 = "http://www.mypublic.top/long/1_1.jpg";
    public static final String girl_2 = "http://www.mypublic.top/long/2.jpg";
    public static final String girl_2_2 = "http://www.mypublic.top/long/2_2.jpg";
    public static final String girl_3 = "http://www.mypublic.top/long/3.jpg";
    public static final String girl_3_3 = "http://www.mypublic.top/long/3_3.jpg";
    public static final String girl_4 = "http://www.mypublic.top/long/4.jpg";
    public static final String girl_4_4 = "http://www.mypublic.top/long/4_4.jpg";
    public static final String girl_5 = "http://www.mypublic.top/long/5.jpg";
    public static final String girl_5_5 = "http://www.mypublic.top/long/5_5.jpg";
    public static final String girl_6 = "http://www.mypublic.top/long/6.jpg";
    public static final String girl_6_6 = "http://www.mypublic.top/long/6_6.jpg";
    public static final String girl_7 = "http://www.mypublic.top/long/7.jpg";
    public static final String girl_7_7 = "http://www.mypublic.top/long/7_7.jpg";
    public static final String girl_8 = "http://www.mypublic.top/long/8.jpg";
    public static final String girl_8_8 = "http://www.mypublic.top/long/8_8.jpg";
    public static final String girl_9 = "http://www.mypublic.top/long/9.jpg";
    public static final String girl_9_9 = "http://www.mypublic.top/long/9_9.jpg";
    public static final String girl_10 = "http://www.mypublic.top/long/10.jpg";
    public static final String girl_10_10 = "http://www.mypublic.top/long/10_10.jpg";
    public static final String girl_11 = "http://www.mypublic.top/long/11.jpg";
    public static final String girl_11_11 = "http://www.mypublic.top/long/11_11.jpg";
    public static final String girl_12 = "http://www.mypublic.top/long/12.jpg";
    public static final String girl_12_12 = "http://www.mypublic.top/long/12_12.jpg";
    public static final String girl_13 = "http://www.mypublic.top/long/13.jpg";
    public static final String girl_13_13 = "http://www.mypublic.top/long/13_13.jpg";
    public static final String girl_14 = "http://www.mypublic.top/long/14.jpg";
    public static final String girl_14_14 = "http://www.mypublic.top/long/14_14.jpg";
    public final String bigUrl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/girl.jpg";
    public final String smallUrl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/girl_thumbnail.jpg";

    private RecyclerView recyclerView;

    private ArrayList<imageData> arr = new ArrayList<>();
    private listAdapter adapter;

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
    }

    @Override
    protected void initData(boolean hasNetWork) {
        adapter = new listAdapter(R.layout.list_item, arr, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
