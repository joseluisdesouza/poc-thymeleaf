package com.spring.pocthymeleaf.service;

import com.spring.pocthymeleaf.model.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;

    @Qualifier("templateEngine")
    @Autowired
    private SpringTemplateEngine templateEngine;


    public void sendEmail(SendMail sendMail) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(sendMail.getTo());
        helper.setSubject(sendMail.getSubject());
        Context context = new Context();
        context.setVariables(sendMail.getProperties());
        String html = templateEngine.process(sendMail.getEvent(), context);
        helper.setText(html, true);

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("title", sendMail.getTitle());
        templateModel.put("message", sendMail.getMessage());
        templateModel.put("comments", sendMail.getComments());

        this.sender.send(message);
        System.out.println("send email...");

    }
}
