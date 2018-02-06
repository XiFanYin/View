package viewdemo.tumour.com.a51ehealth.view.net.MyConverter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/8/11/011.
 * 自定义加密界解密的解析器，为了防止请求数据和返回数据有加解密需求
 */

public class MyConverterFactory extends Converter.Factory {


    private final Gson gson;

    public static MyConverterFactory create() {
        return new MyConverterFactory(new Gson());
    }

    private MyConverterFactory(Gson gson) {
        this.gson = gson;
    }


    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {


        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyRequestBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyResponseBodyConverter<>(gson, adapter);
    }
}
