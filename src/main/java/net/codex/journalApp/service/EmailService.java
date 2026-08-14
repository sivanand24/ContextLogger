package net.codex.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
 @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body){
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);
            log.info("Email sent successfully to {}", to);

        }catch (Exception e){
            log.error("Failed to send email to {}", to, e);
            throw new RuntimeException("Email sending failed", e);
        }
    }
}
