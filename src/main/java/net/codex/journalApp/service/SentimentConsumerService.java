package net.codex.journalApp.service;

import net.codex.journalApp.model.SentimentData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "weekly-sentiments")
    public void consume(SentimentData sentimentData) {
        sendEmail(sentimentData);
    }

    private void sendEmail(SentimentData sentimentData) {
        emailService.sendEmail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
    }
}
