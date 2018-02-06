package viewdemo.tumour.com.a51ehealth.view.net.Exception;

/**
 * Created by Administrator on 2017/8/11/011.
 * 和后台协商好的错误码
 *http://www.jianshu.com/p/c105a4177982
 *
 */

public class ApiException extends RuntimeException {
    private int errorCode;


    public ApiException(int code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


}
