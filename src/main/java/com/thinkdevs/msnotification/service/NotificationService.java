package com.thinkdevs.msnotification.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import com.thinkdevs.msnotification.model.EmailRequest;
import com.thinkdevs.msnotification.model.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    @Value("${email.resend}")
    private String renderKey;
    private final TemplateEngine templateEngine;

    public NotificationResponse sendEmail(EmailRequest request) {
        var resend = new Resend(renderKey);
        var context = new Context();
        context.setVariable("fullName", request.getFullName());
        var htmlContent = templateEngine.process("email-template", context);
        log.info(htmlContent);
        request.setTemplate(htmlContent);
        SendEmailRequest sendEmailRequest = new SendEmailRequest.Builder()
                .from(request.getFrom())
                .to(request.getTo())
                .subject(request.getSubject())
                .html(request.getTemplate())
                .build();
        try {
            SendEmailResponse data = resend.emails().send(sendEmailRequest);
            log.info("data send ", data.getId());
            return NotificationResponse.builder()
                    .from(request.getFrom())
                    .to(request.getTo())
                    .subject(request.getSubject())
                    .template(request.getTemplate())
                    .fullName(request.getFullName())
                    .id(data.getId())
                    .build();
        } catch (ResendException e) {
            e.printStackTrace();
        }
        return null;
    }
}
