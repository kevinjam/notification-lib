package com.thinkdevs.msnotification.controller;

import com.resend.services.emails.model.SendEmailResponse;
import com.thinkdevs.msnotification.model.EmailRequest;
import com.thinkdevs.msnotification.model.NotificationResponse;
import com.thinkdevs.msnotification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SendEmailController {

    private final NotificationService notificationService;

    @PostMapping("/send-email")
    public NotificationResponse sendEmail(@RequestBody EmailRequest emailRequest) {
        return notificationService.sendEmail(emailRequest);
    }
}
