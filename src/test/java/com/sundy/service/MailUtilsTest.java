package com.sundy.service;

import com.sundy.common.util.MailUtils;
import com.sundy.common.util.TimeUtils;
import org.junit.Test;

import java.util.Date;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年05月18日 14:32:03
 * 描述：
 */
public class MailUtilsTest {
    @Test
    public void sendMail(){//  metwamxounwhiehe
        MailUtils util = new MailUtils("smtp.qq.com", "1122334455@qq.com", "metwamxounwhiehe");//第三个参数为QQ的授权码
        util.setFrom("1122334455@qq.com");
        util.setTo("1122334455@qq.com");
        util.setSubject("dfg.");
        util.setContent(TimeUtils.parseToStr(new Date(), TimeUtils.SDF_STANDARD)+"<br> adbc");
        util.addAttachMent("C:\\Users\\admin\\Desktop\\周报月报\\个人工作周报（20180518）.xls");
        if(util.send()){
            System.out.println("发送成功....");
        }else {
            System.out.println("发送失败....");
        }
    }
}
