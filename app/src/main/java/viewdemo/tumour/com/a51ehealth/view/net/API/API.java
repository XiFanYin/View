package viewdemo.tumour.com.a51ehealth.view.net.API;



import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import viewdemo.tumour.com.a51ehealth.view.bean.LoginBean;
import viewdemo.tumour.com.a51ehealth.view.bean.Patient;
import viewdemo.tumour.com.a51ehealth.view.bean.UpImage;

/**
 * Created by yinfeilong on 2017/8/25.
 */

public interface API {


    @FormUrlEncoded
    @POST("accountLogin.html")
    Observable<LoginBean> Login(@Field("loginname") String loginname, @Field("password") String password);




    @Multipart
    @POST("uploadDoctorcertificatephoto.html")
    Observable<UpImage> UpCertificate(@Part List<MultipartBody.Part> parts);


    @Streaming
    @G
    Observable<ResponseBody> downloadFile(@Url String url);




    @FormUrlEncoded
    @POST("getMyPatientList.html")
    Observable<Patient> getPatientInfo(@Field("page") int page, @Field("rows") int rows);


}
