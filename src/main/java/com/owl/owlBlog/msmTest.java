package com.owl.owlBlog;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.json.JSONException;

import java.io.IOException;

/**
 *  msm 测试 下面都是测试数据 不是真实数据
 *  使用腾讯云短信服务不要使用单元测试
 *  可能会出现未知错误 在github issue 上面有
 *  https://github.com/qcloudsms/qcloudsms_java 以后可能会集成到项目之中 但是目前对于这个项目来说没有什么太大的作用
 *
 */
public class msmTest {
//     短信应用 SDK AppID
    private static int appid = 1400009099; // 1400开头

//     短信应用 SDK AppKey
    private static  String appkey = "9ff91d87c2cd7cd0ea762f141975d1df37481d48700d70ac37470aefc60f9bad";

//     需要发送短信的手机号码
    private static String[] phoneNumbers ={"21212313123", "12345678902", "12345678903"};

//     短信模板 ID，需要在短信应用中申请
    private static  int templateId = 294945; // NOTE: 这里的模板 ID`7839`只是一个示例，真实的模板 ID 需要在短信控制台中申请
//     templateId7839对应的内容是"您的验证码是: {1}"
//     签名
    private static  String smsSign = "腾讯云"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台申请。

    public static void main(String[] args) {
        try {
            String[] params = {"5678","1"};// 数组具体的元素个数和模板中变量个数必须一致，例如示例中 templateId:5678 对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
    }
}
