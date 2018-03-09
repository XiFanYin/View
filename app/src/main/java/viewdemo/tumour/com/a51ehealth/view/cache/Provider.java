package viewdemo.tumour.com.a51ehealth.view.cache;


import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import viewdemo.tumour.com.a51ehealth.view.bean.Patient;


/**
 * 不配置，表示永久存储
 * @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
 * 防止接口出现问题而导致获取不到缓存数据
 * @ProviderKey("rrrrrrr")
 *
 *DynamicKey 用做分页，一般里边传递page，这样可以分页缓存
 * *EvictDynamicKey根据网络来判断是去获取缓存 : 设置成true，表示不获取缓存,联网请求，并更新本地缓存，设置成false表示去获取缓存，不去请求
 */
public interface Provider {

    Observable<Patient> getPatientInfo(Observable<Patient> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);


}
