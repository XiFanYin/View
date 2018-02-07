package viewdemo.tumour.com.a51ehealth.view.cache;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;
import viewdemo.tumour.com.a51ehealth.view.bean.Patient;


/**
 * 不配置，表示永久存储
 *
 * @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
 * 防止接口出现问题而导致获取不到缓存数据
 * @ProviderKey("rrrrrrr")
 */
public interface Provider {


    Observable<Patient> getPatientInfo(Observable<Patient> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);


}
