package net.codex.journalApp.service;

import net.codex.journalApp.model.SentimentData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentimentProducerService {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public SentimentProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendSentiment(SentimentData sentimentData) {
        rabbitTemplate.convertAndSend(
                "weekly-sentiments",
                sentimentData
        );
    }
}
