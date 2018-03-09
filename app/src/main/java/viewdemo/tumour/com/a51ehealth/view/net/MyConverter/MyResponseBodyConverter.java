package viewdemo.tumour.com.a51ehealth.view.net.MyConverter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import viewdemo.tumour.com.a51ehealth.view.net.Exception.ApiException;

/**
 * MyResponseBodyConverter
 * Created by Administrator on 2017/8/11/011.
 */

public class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    MyResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.e("BeanJson", response);
        //Result2 类型是和服务器约定好的返回格式，再写接口的时候不必传递
        Result2 re = gson.fromJson(response, Result2.class);
        //关注的重点，自定义响应码中非0的情况，一律抛出ApiException异常。
        // 这样，我们就成功的将该异常交给onError()去处理了。
        if (re.getError_code() != 0) {
            value.close();
            throw new ApiException(re.getError_code(), re.getMsg());
        }

        MediaType mediaType = value.contentType();
        Charset charset = mediaType != null ? mediaType.charset(UTF_8) : UTF_8;
        //因为可能用到缓存，所以，需要吧整个数据都返回出去，同时Data里边也要是全部字段的格式
        ByteArrayInputStream bis = new ByteArrayInputStream(gson.toJson(re).getBytes());
        InputStreamReader reader = new InputStreamReader(bis, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }


    }
}
