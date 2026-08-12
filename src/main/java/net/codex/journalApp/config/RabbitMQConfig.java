package net.codex.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;
@Configuration
public class RabbitMQConfig {

    public static final String WEEKLY_SENTIMENT_QUEUE = "weekly-sentiments";

    @Bean
    public Queue weeklySentimentsQueue() {
        return new Queue(WEEKLY_SENTIMENT_QUEUE, true);

        }
    }
