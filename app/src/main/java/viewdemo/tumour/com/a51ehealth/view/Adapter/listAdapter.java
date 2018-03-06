package viewdemo.tumour.com.a51ehealth.view.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import viewdemo.tumour.com.a51ehealth.view.R;
import viewdemo.tumour.com.a51ehealth.view.bean.ImageUrl;
import viewdemo.tumour.com.a51ehealth.view.bean.imageData;
import viewdemo.tumour.com.a51ehealth.view.weight.GlideView.DividerGridItemDecoration;

/**
 * Created by Administrator on 2018/2/26.
 */

public class listAdapter extends BaseQuickAdapter<imageData, BaseViewHolder> {


    private Activity act;

    public listAdapter(int layoutResId, @Nullable List<imageData> data, Activity act) {
        super(layoutResId, data);

        this.act = act;
    }

    @Override
    protected void convert(BaseViewHolder helper, imageData item) {
        helper.setText(R.id.tv, item.getInfo());
        RecyclerView recyc_image = helper.getView(R.id.recyclerView_item);
        GridLayoutManager gridLayoutManager = initGridLayoutManager(item.getUrl(), act);
        recyc_image.setLayoutManager(gridLayoutManager);
        recyc_image.addItemDecoration(new DividerGridItemDecoration(Color.WHITE));
        NineAdapter adapter = new NineAdapter(R.layout.image_item, item.getUrl(), act);
        recyc_image.setAdapter(adapter);
    }

    /**
     * 根据图片数量，初始化GridLayoutManager，并且设置列数，
     * 当图片 = 1 的时候，显示1列
     * 当图片<=4张的时候，显示2列
     * 当图片>4 张的时候，显示3列
     *
     * @return
     */
    private static GridLayoutManager initGridLayoutManager(ArrayList<ImageUrl> imageDatas, Context context) {
        GridLayoutManager gridLayoutManager;
        if (imageDatas != null) {
            switch (imageDatas.size()) {
                case 1:
                    //一个是上下文对象，另一个是一行显示几列的参数常量
                    gridLayoutManager = new GridLayoutManager(context, 1);
                    break;
                case 2:
                    gridLayoutManager = new GridLayoutManager(context, 2);
                    break;
                case 3:
                    gridLayoutManager = new GridLayoutManager(context, 3);
                    break;
                case 4:
                    gridLayoutManager = new GridLayoutManager(context, 2);
                    break;
                default:
                    gridLayoutManager = new GridLayoutManager(context, 3);
                    break;
            }
        } else {
            gridLayoutManager = new GridLayoutManager(context, 3);
        }
        return gridLayoutManager;
    }
}
