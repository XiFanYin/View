package viewdemo.tumour.com.a51ehealth.view.utils.PhoneUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import viewdemo.tumour.com.a51ehealth.view.R;


/**
 * Created by shadow on 2016/6/20.
 */
public class BottomPopUpDialog extends DialogFragment {


    private TextView mCancel;

    private LinearLayout mContentLayout;

    private Builder mBuilder;

    private static BottomPopUpDialog getInstance(Builder builder) {
        BottomPopUpDialog dialog = new BottomPopUpDialog();
        dialog.mBuilder = builder;
        return dialog;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //该方法需要放在onViewCreated比较合适, 若在 onStart 在部分机型(如:小米3)会出现闪烁的情况
        getDialog().getWindow().setBackgroundDrawableResource(mBuilder.mBackgroundShadowColor);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_pop_up_dialog, null);
        initView(view);
        registerListener(view);
        setCancelable(true);
        return view;
    }


    private void initView(View view) {
        mContentLayout = (LinearLayout) view.findViewById(R.id.pop_dialog_content_layout);
        mCancel = (TextView) view.findViewById(R.id.cancel);
        initItemView();
    }


    private void registerListener(View view) {

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dismiss();
                }
                return false;
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initItemView() {
        //循环添加item
        for (int i = 0; i < mBuilder.mDataArray.length; i++) {
            final PopupDialogItem dialogItem = new PopupDialogItem(getContext());
            dialogItem.refreshData(mBuilder.mDataArray[i]);

            //最后一项隐藏分割线
            if (i == mBuilder.mDataArray.length - 1) {
                dialogItem.hideLine();
            }

            //设置字体颜色
            if (mBuilder.mColorArray.size() != 0 && mBuilder.mColorArray.get(i) != 0) {
                dialogItem.setTextColor(mBuilder.mColorArray.get(i));
            }

            if (mBuilder.mLineColor != 0) {
                dialogItem.setLineColor(mBuilder.mLineColor);
            }

            mContentLayout.addView(dialogItem);

            dialogItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBuilder.mListener.onDialogClick(dialogItem.getItemContent());
                    if (mBuilder.mIsCallBackDismiss) dismiss();
                }
            });
        }
    }


    public static class Builder {

        private String[] mDataArray;

        private SparseIntArray mColorArray = new SparseIntArray();

        private BottomPopDialogOnClickListener mListener;

        private int mLineColor;

        private boolean mIsCallBackDismiss = false;

        private int mBackgroundShadowColor = R.color.transparent_70;


        /**
         * 设置item数据
         */
        public Builder setDialogData(String[] dataArray) {
            mDataArray = dataArray;
            return this;
        }

        /**
         * 设置监听item监听器
         */
        public Builder setItemOnListener(BottomPopDialogOnClickListener listener) {
            mListener = listener;
            return this;
        }


        /**
         * 设置字体颜色
         *
         * @param index item的索引
         * @param color res color
         */
        public Builder setItemTextColor(int index, int color) {
            mColorArray.put(index, color);
            return this;
        }

        /**
         * 设置item分隔线颜色
         */
        public Builder setItemLineColor(int color) {
            mLineColor = color;
            return this;
        }

        /**
         * 设置是否点击回调取消dialog
         */
        public Builder setCallBackDismiss(boolean dismiss) {
            mIsCallBackDismiss = dismiss;
            return this;
        }


        /**
         * 设置dialog背景阴影颜色
         */
        public Builder setBackgroundShadowColor(int color) {
            mBackgroundShadowColor = color;
            return this;
        }


        public BottomPopUpDialog create() {
            return BottomPopUpDialog.getInstance(this);
        }


        public BottomPopUpDialog show(FragmentManager manager, String tag) {
            BottomPopUpDialog dialog = create();
            dialog.show(manager, tag);
            return dialog;
        }


    }


    public interface BottomPopDialogOnClickListener {
        /**
         * item点击事件回调
         *
         * @param tag item字符串 用于识别item
         */
        void onDialogClick(String tag);
    }

}
