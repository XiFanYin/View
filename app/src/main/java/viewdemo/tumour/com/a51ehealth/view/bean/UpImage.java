package viewdemo.tumour.com.a51ehealth.view.bean;

/**
 * Created by Administrator on 2018/2/6.
 */

public class UpImage {


    /**
     * data : {"zhaop_url":"http://47.93.136.56:7011//upload/images_from_app/doctor/certificate_photo/20180206161843_9522.jpg"}
     * error_code : 0
     * msg : Success
     */

    private DataBean data;
    private int error_code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * zhaop_url : http://47.93.136.56:7011//upload/images_from_app/doctor/certificate_photo/20180206161843_9522.jpg
         */

        private String zhaop_url;

        public String getZhaop_url() {
            return zhaop_url;
        }

        public void setZhaop_url(String zhaop_url) {
            this.zhaop_url = zhaop_url;
        }
    }
}
