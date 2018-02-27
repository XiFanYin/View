package viewdemo.tumour.com.a51ehealth.view.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/26.
 */

public class ImageUrl implements Serializable {


    private String bigImage;

    private String smallImage;

    public ImageUrl(String smallImage, String bigImage) {
        this.bigImage = bigImage;
        this.smallImage = smallImage;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }
}
