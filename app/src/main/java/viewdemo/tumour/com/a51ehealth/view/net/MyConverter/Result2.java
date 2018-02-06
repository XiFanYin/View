package viewdemo.tumour.com.a51ehealth.view.net.MyConverter;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/11/011.
 * 基础的数据格式形式
 *http://www.jianshu.com/p/c105a4177982
 *
 */

public class Result2<T> implements Serializable {


    private  int  error_code;


    private String msg;


    private T data;


    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
