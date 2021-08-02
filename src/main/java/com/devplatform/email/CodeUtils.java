package com.devplatform.email;
import com.devplatform.entity.EmailMessage;
import com.devplatform.redis.RedisUtil;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Component
public class CodeUtils {

    @Resource
    JavaMailSender mailSender;

    @Resource
    RedisUtil redisUtil;

    //生成链接，并给接收的邮箱发送邮件
    public boolean sendCode(EmailMessage emailMessage){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
            String token = UUID.randomUUID().toString();  // 生成UUID
            redisUtil.set(token,emailMessage);
            redisUtil.expire(token,1800);
            mimeMessageHelper.setFrom("582373673@qq.com");
            mimeMessageHelper.setTo(emailMessage.getEmail());
            mimeMessageHelper.setSubject("加入团队");
            String html = "<html>\n"+
                    "<body>\n"+
                    "<p>请点击下发链接加入团队</>\n"+
                    "<a href=\"http://localhost:8080/index/lookCode/"+token+"\">http://localhost: 8080/lookCode/"+token+"</a>"+
                    "</body>\n"+
                    "</html>";
            mimeMessageHelper.setText(html,true);
            mailSender.send(message);
            System.out.println("发送成功");
            return true;
        }catch (Exception e){
            System.out.println("发送失败");
            return false;
        }
    }

    //判断token是否过期
    public boolean eqToken(String token){
        return redisUtil.hasKey(token);
    }

    //根据token查询用户信息
    public EmailMessage findEmailMessage(String token){
        return (EmailMessage) redisUtil.get(token);
    }
}
