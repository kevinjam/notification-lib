package com.thinkdevs.msnotification.model;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class EmailRequest {
    private String from;
    private String to;
    private String subject;
    private String template;
    private String fullName;
}
