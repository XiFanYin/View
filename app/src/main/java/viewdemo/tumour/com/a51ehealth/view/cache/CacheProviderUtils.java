package viewdemo.tumour.com.a51ehealth.view.cache;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import viewdemo.tumour.com.a51ehealth.view.app.App;
import viewdemo.tumour.com.a51ehealth.view.net.RetrofitUtil;

/**
 * Created by Administrator on 2018/2/7.
 */

public class CacheProviderUtils {

    private static CacheProviderUtils mInstance;
    private final RxCache persistence;


    /**
     * 单例模式，生成该类对象
     *
     * @return
     */
    public static CacheProviderUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new CacheProviderUtils();
            }
        }

        return mInstance;
    }

    //构建RxCache的时候将useExpiredDataIfLoaderNotAvailable设置成true,
    // 会在数据为空或者发生错误时,忽视EvictProvider为true或者缓存过期的情况,继续使用缓存(前提是之前请求过有缓存)
    public CacheProviderUtils() {
        persistence = new RxCache.Builder()
                .useExpiredDataIfLoaderNotAvailable(true)
                .persistence(App.getApplication().getFilesDir(), new GsonSpeaker());

    }


    public <T> T using(Class<T> cache) {

        return persistence.using(cache);
    }

}
