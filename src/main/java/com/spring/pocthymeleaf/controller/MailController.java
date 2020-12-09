package com.spring.pocthymeleaf.controller;

import com.spring.pocthymeleaf.model.SendMail;
import com.spring.pocthymeleaf.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;

@AllArgsConstructor
@RestController
@RequestMapping("/send")
public class MailController {

    @Autowired
    private final MailService emailService;

    private final SendMailServiceImpl sendMailService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestBody SendMail sendMail) throws MessagingException {
        emailService.sendEmail(sendMail);
    }

}
