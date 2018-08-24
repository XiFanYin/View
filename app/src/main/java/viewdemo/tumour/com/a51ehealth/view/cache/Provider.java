package viewdemo.tumour.com.a51ehealth.view.cache;


import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.EvictDynamicKeyGroup;
import viewdemo.tumour.com.a51ehealth.view.bean.Patient;


/**
 * 不配置，表示永久存储
 * @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
 *
 * DynamicKey 用做分页，一般里边传递page，这样可以分页缓存,还有一个加强版本DynamicKeyGroup传入两个标记，比如不同用户获取到的分页数据是不同的效果，就需要两个标记一个是page 一个是用户标识
 *
 *
 * EvictDynamicKey根据网络来判断是去获取缓存 : 设置成true，表示不获取缓存,联网请求，并更新本地缓存，设置成false表示去获取缓存，不去请求
 * 继承关系为EvictProvider < EvictDynamicKey < EvictDynamicKeyGroup
 *
 *那么他们的区别是什么呢？
 *EvictDynamicKeyGroup 只会删除对应分页下,对应用户的缓存
 *EvictDynamicKey 会删除那个分页下的所有缓存,比如你请求的是第一页下user1的数据,它不仅会删除user1的数据还会删除当前分页下其他user2,user3...的数据
 *EvictProvider 会删除当前接口下的所有缓存,比如你请求的是第一页的数据,它不仅会删除第一页的数据,还会把这个接口下其他分页的数据全删除
 *
 *
 * @Expirable在方法上使用它并且给它设置为false(如果没使用这个注解,则默认为true),就可以保证这个接口的缓存数据,在每次需要自动清理时都幸免于难
 *
 *
 * 在最新的版本中某个接口返回值类型内部发生了改变,从而获取数据的方式发生了改变,但是存储在本地的数据,是未改变的版本,
 * 这样在反序列化时就可能发生错误,为了规避这个风险,作者就加入了数据迁移的功能，迁移功能很好理解，就是通过注解去标记哪个接口缓存需要清理
 *
 *
 *
 *
 */
public interface Provider {

    Observable<Patient> getPatientInfo(Observable<Patient> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);


}
