package viewdemo.tumour.com.a51ehealth.view.net.MyConverter.string;



import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import viewdemo.tumour.com.a51ehealth.view.net.Exception.ApiException;

public class StringResponseBodyConverter implements Converter<ResponseBody, String> {

    @Override
    public String convert(ResponseBody value) throws IOException {

        try {
            String response= value.string();
            JSONObject obj = new JSONObject(response);

            String statu = obj.getString("status");//状态码

            String message = obj.getString("message"); //返回信息
            if ("0".equals(statu)) {//请求成功
                response = obj.getString("data");
            } else {//请求失败
                throw new ApiException(Integer.valueOf(statu), message);
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            value.close();
        }

        return "";


    }
}