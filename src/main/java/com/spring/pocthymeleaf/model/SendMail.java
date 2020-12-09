package com.spring.pocthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMail {

    private String[] to;
    private Map<String, Object> properties;
    private String subject;
    private String event;
    private String title;
    private String message;
    private String comments;

}
