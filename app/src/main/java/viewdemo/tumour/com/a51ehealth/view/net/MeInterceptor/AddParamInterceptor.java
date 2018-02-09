package viewdemo.tumour.com.a51ehealth.view.net.MeInterceptor;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 目的是添加
 * 公共参数，请求头  追加到url后边    添加到请求体中
 * 1) Header
 * 2) Query Param
 * 3) POST Param form-data
 * 4) POST Param x-www-form-urlencoded
 */
public abstract class AddParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求对象
        Request originRequest = chain.request();
        //获取原本的请求对象
        Request.Builder newRequest = originRequest.newBuilder();
        //获取原本的请求头对象
        Headers.Builder newHeaderBuilder = originRequest.headers().newBuilder();
        //获取外部传递过来的头参数
        Map<String, String> headerMap = getHeaderMap();
        //如果外部传递了请求头信息
        if (headerMap != null && !headerMap.isEmpty()) {
            //循环遍历，添加到原本的的请求头对象中
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                newHeaderBuilder.add(entry.getKey(), entry.getValue());
            }
            //然后放入请求对象中
            newRequest.headers(newHeaderBuilder.build());
        }
        //判断请求方式是否是GET
        if ("GET".equals(originRequest.method())) {
            //获取GET请求的url
            HttpUrl.Builder newUrlBuilder = originRequest.url().newBuilder();
            //获取外部传递过来的参数
            Map<String, String> queryParamMap = getQueryParamMap();
            //循环遍历，拼接到新的请求url中
            if (queryParamMap != null && !queryParamMap.isEmpty()) {
                for (Map.Entry<String, String> entry : queryParamMap.entrySet()) {
                    newUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
                newRequest.url(newUrlBuilder.build());
            }
            //判断请求方式是否是POST
        } else if ("POST".equals(originRequest.method())) {
            //获取请求体
            RequestBody body = originRequest.body();
            //判断是否是表单请求
            if (body != null && body instanceof FormBody) {
                //转换成表单请求
                FormBody formBody = (FormBody) body;
                //创建一个存储key——value 的对象
                Map<String, String> formBodyParamMap = new HashMap<>();
                //获取原来表单参数的个数，
                int bodySize = formBody.size();
                //循环把原来的参数放入到存储数据对象中
                for (int i = 0; i < bodySize; i++) {
                    formBodyParamMap.put(formBody.name(i), formBody.value(i));
                }
                //获取外部传递过来的表单参数，循环添加到表单请求数据中
                Map<String, String> newFormBodyParamMap = getFormBodyParamMap();
                if (newFormBodyParamMap != null) {
                    formBodyParamMap.putAll(newFormBodyParamMap);
                    FormBody.Builder bodyBuilder = new FormBody.Builder();
                    for (Map.Entry<String, String> entry : formBodyParamMap.entrySet()) {
                        bodyBuilder.add(entry.getKey(), entry.getValue());
                    }
                    newRequest.method(originRequest.method(), bodyBuilder.build());
                }
                //判断请求方式是传递文件
            } else if (body != null && body instanceof MultipartBody) {
                //转换成文件请求
                MultipartBody multipartBody = (MultipartBody) body;
                //获取原来的传递的参数
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                //获取外部传递的表单数据
                Map<String, String> extraFormBodyParamMap = getFormBodyParamMap();
                //循环添加到请求数据中
                if (extraFormBodyParamMap != null) {
                    for (Map.Entry<String, String> entry : extraFormBodyParamMap.entrySet()) {
                        builder.addFormDataPart(entry.getKey(), entry.getValue());
                    }
                    List<MultipartBody.Part> parts = multipartBody.parts();
                    for (MultipartBody.Part part : parts) {
                        builder.addPart(part);
                    }
                    newRequest.post(builder.build());
                }

            }
        }
        //调用请求，获取响应对象
        return chain.proceed(newRequest.build());
    }

    public abstract Map<String, String> getHeaderMap();

    public abstract Map<String, String> getQueryParamMap();

    public abstract Map<String, String> getFormBodyParamMap();
}

