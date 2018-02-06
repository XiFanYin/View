package viewdemo.tumour.com.a51ehealth.view.net.Exception;

/**
 * Created by Administrator on 2017/8/11/011.
 * <p>
 * 和自己后台规定的错误码代码，只要求再onError的时候抛出错误
 *
 * http://www.jianshu.com/p/c105a4177982
 */

public class ApiErrorCode {


    /**
     * 未知错误
     */
    public static final int Unknown_Error = 1;

    /**
     * 后台服务器暂时不可用
     */
    public static final int Service_No_Work_Error = 2;
    /**
     * 请求参数错误，无效或者缺失
     */
    public static final int Short_Parameter_Error= 3;
    /**
     * 程序抛出的未捕获异常
     */
    public static final int No_Catch_Error = 5;

    /**
     * Call_id参数无效或已被使用过
     */
    public static final int Lose_Parameter_Error = 101;
    /**
     * 参数过多
     */
    public static final int Many_Parameters_Error = 102;

    /**
     *必选参数格式错误
     */
    public static final int Parameters_Format_Error = 103;

    /**
     * 无效的用户信息
     */
    public static final int Lost_User_Parameters_Error = 104;


}
