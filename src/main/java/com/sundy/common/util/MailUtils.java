package com.sundy.common.util;

import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年05月18日 14:21:56
 * 描述：的邮箱地址发送一封文本邮件
 */
public class MailUtils {
    private static final Logger LOG = Logger.getLogger(MailUtils.class);
    private MimeMessage message;
    private Properties props;
    private Session session;
    private MimeMultipart mp;
    private String name = "";
    private String password = "";

    /**
     * 完成发送邮件的初始化工作
     * @param host
     * @param name
     * @param password
     */
    public MailUtils(String host, String name, String password) {
        this.name = name;
        this.password = password;
        props = System.getProperties();
        // 设置SMTP主机
        props.put("mail.smtp.host", host);
        // 防止主机与DNS不匹配
        props.put("mail.smtp.localhost", "localHostAdress");
        // 设置SMTP验证属性
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        if("smtp.gmail.com".equals(host)){
			/*发送邮件默认是25端口,但是gmail邮件服务商用的不是25端口,而是465或587端口 所以  Properties加上如下设置就可以发邮件了：*/
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
        }else{
            props.setProperty("mail.smtp.port", "25");
            props.remove("mail.smtp.socketFactory.class");
            props.remove("mail.smtp.socketFactory.fallback");
            props.remove("mail.smtp.socketFactory.port");
        }

        // 获得邮件会话对象
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(name, password);
            }
        };
        session = Session.getDefaultInstance(props, auth);
        // 创建MIME邮件对象
        message = new MimeMessage(session);
        mp = new MimeMultipart();
    }

    /**
     * 设置邮件发送人
     *
     * @param from
     */
    public void setFrom(String from) {
        try {
            message.setFrom(new InternetAddress(from));
        } catch (MessagingException e) {
            LOG.debug(e.toString());
        }
    }

    /**
     * 设置邮件收件人
     *
     * @param to
     */
    public void setTo(String to) {
        try {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        } catch (MessagingException e) {
            LOG.debug(e.toString());
        }
    }

    /**
     * 设置邮件主题
     *
     * @param subject
     */
    public void setSubject(String subject) {
        try {
            message.setSubject(subject);
        } catch (Exception e) {
            LOG.debug(e.toString());
        }
    }

    /**
     * 设置邮件正文
     *
     * @param content
     */
    public void setContent(String content) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent(
                    "<meta http-equiv=Content-Type content=text/html; charset=gb2312>"
                            + content, "text/html;charset=GB2312");
            mp.addBodyPart(bp);
        } catch (Exception e) {
            LOG.debug(e.toString());
        }
    }

    /**
     * 添加附件
     *
     * @param filename
     */
    public void addAttachMent(String filename) {
        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filename);
            bp.setDataHandler(new DataHandler(fileds));
            bp.setFileName(MimeUtility.encodeText(fileds.getName())); //解决乱码的问题
            mp.addBodyPart(bp);
        } catch (Exception e) {
            LOG.debug(e.toString());
        }
    }

    /**
     * 发送邮件
     *
     * @return
     */
    public boolean send() {
        try {
            message.setContent(mp);
            message.saveChanges();
            // 创建SMTP邮件协议发送对象
            Transport transport = session.getTransport("smtp");
            // 取得与邮件服务器的连接
            transport.connect((String) props.get("mail.smtp.host"), name, password);
            // 通过邮件服务器发送邮件
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.toString(),e);
            return false;
        }
    }
}
