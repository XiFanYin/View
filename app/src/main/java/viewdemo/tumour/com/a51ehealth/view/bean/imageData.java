package viewdemo.tumour.com.a51ehealth.view.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/26.
 */

public class imageData {


    public imageData() {
    }

    private String Info;


    private ArrayList<ImageUrl> url ;

    public imageData(String info, ArrayList<ImageUrl> url) {
        Info = info;
        this.url = url;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public ArrayList<ImageUrl> getUrl() {
        return url;
    }

    public void setUrl(ArrayList<ImageUrl> url) {
        this.url = url;
    }
}
