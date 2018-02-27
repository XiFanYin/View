package viewdemo.tumour.com.a51ehealth.view.utils.glide;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class ImageDetailViewPager extends ViewPager {


    public ImageDetailViewPager(Context context) {
        super(context);
    }


    public ImageDetailViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }


    }
}
