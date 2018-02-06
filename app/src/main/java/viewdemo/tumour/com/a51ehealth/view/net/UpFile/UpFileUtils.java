package viewdemo.tumour.com.a51ehealth.view.net.UpFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by yinfeilong on 2017/8/31.
 * http://www.jianshu.com/p/27fa68d390b3
 *
 *
 */

public class UpFileUtils {



    /**
     * 对应的 接口中的参数为  @Part  List<MultipartBody.Part> parts
     *
     * @param key       对应请求正文中name的值。对应的传递文件的一个key  ---对应多个
     * @param filePaths 文件路径数组
     * @param imageType 文件类型    MediaType.parse("image/png")
     * @return
     */

    public static List<MultipartBody.Part> files2Parts(String key, String[] filePaths, MediaType imageType) {
        //创建文件File对象集合
        List<MultipartBody.Part> parts = new ArrayList<>(filePaths.length);
        for (String filePath : filePaths) {
            File file = new File(filePath);
            // 根据类型及File对象创建RequestBody
            RequestBody requestBody = RequestBody.create(imageType, file);
            // 将RequestBody封装成MultipartBody.Part类型
            //第一个参数表示这个请求的key  第二个表示上传文件的名字， 第三个是封装过文件路径和文件类型的请求对象
            MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
            // 添加进集合
            parts.add(part);
        }
        return parts;
    }

    /**
     * 同上边方法一样 都是一个key  对应不同文件
     *
     * @param key
     * @param filePaths
     * @param imageType
     * @return 对应接口中的参数  @Body MultipartBody multipartBody   并且不需要@Multipart
     */
    public static MultipartBody filesToMultipartBody(String key, String[] filePaths, MediaType imageType) {
        //  创建文件MultipartBody对象集合
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String filePath : filePaths) {
            //创建文件File对象
            File file = new File(filePath);
            // 根据类型及File对象创建RequestBody
            RequestBody requestBody = RequestBody.create(imageType, file);
            //第一个参数表示这个请求的key  第二个表示上传文件的名字， 第三个是封装过文件路径和文件类型的请求对象
            builder.addFormDataPart(key, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }


    /**
     * 直接添加文本类型的Part到的MultipartBody的Part集合中
     *
     * @param parts    Part集合
     * @param key      参数名（name属性）
     * @param value    文本内容
     * @param position 插入的位置
     */

    public static void addTextPart(List<MultipartBody.Part> parts, String key, String value, int position) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);

        MultipartBody.Part part = MultipartBody.Part.createFormData(key, null, requestBody);
        parts.add(position, part);
    }





    /**
     * 添加文本类型的Part到的MultipartBody.Builder中
     * @param builder 用于构建MultipartBody的Builder
     * @param key 参数名（name属性）
     * @param value 文本内容
     */
    public static MultipartBody.Builder addTextPart(MultipartBody.Builder builder, String key, String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        // MultipartBody.Builder的addFormDataPart()有一个直接添加key value的重载，但坑的是这个方法
        // 不会设置编码类型，会出乱码，所以可以使用3个参数的，将中间的filename置为null就可以了
        // builder.addFormDataPart(key, value);
        // 还有一个坑就是，后台取数据的时候有可能是有顺序的，比如必须先取文本后取文件，
        // 否则就取不到（真弱啊...），所以还要注意add的顺序
        builder.addFormDataPart(key, null, requestBody);
        return builder;
    }
}