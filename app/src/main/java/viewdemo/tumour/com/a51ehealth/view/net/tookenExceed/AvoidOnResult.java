package viewdemo.tumour.com.a51ehealth.view.net.tookenExceed;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;

import io.reactivex.Observable;

public class AvoidOnResult {
    private static final String TAG = "AvoidOnResult";
    private AvoidOnResultFragment mAvoidOnResultFragment;

    public AvoidOnResult(Activity activity) {
        //获取当前activity中是否有已经添加的fragment
        mAvoidOnResultFragment = getAvoidOnResultFragment(activity);
    }

    //如果传递fragment,获取当前activity，然后继续给fragment挂载的activity添加无视图的fragment
    public AvoidOnResult(Fragment fragment) {
        this(fragment.getActivity());
    }

    private AvoidOnResultFragment getAvoidOnResultFragment(Activity activity) {

        AvoidOnResultFragment avoidOnResultFragment = findAvoidOnResultFragment(activity);
        //如果没有，那么就创建一个fragment添加进去
        if (avoidOnResultFragment == null) {
            avoidOnResultFragment = new AvoidOnResultFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(avoidOnResultFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return avoidOnResultFragment;
    }

    private AvoidOnResultFragment findAvoidOnResultFragment(Activity activity) {
        //获取当前activity中是否有已经添加的fragment
        return (AvoidOnResultFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }


    //跳转：把这个Intent传递到添加的Fragment中去，返回一个Observable对象
    private Observable<ActivityResultInfo> startForResult(Intent intent) {
        return mAvoidOnResultFragment.startForResult(intent);
    }

    //重载方法：直接传递类的字节码,原因是不需要给里边传递参数
    public Observable<ActivityResultInfo> startForResult(Class<?> clazz) {
        Intent intent = new Intent(mAvoidOnResultFragment.getActivity(), clazz);
        return startForResult(intent);
    }


}