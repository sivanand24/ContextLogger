package net.codex.journalApp.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;

public class SentimentAnalysisService {
    @Autowired
    private ChatClient chatClient;

    public String analyzeSentiment(String journalText) {

        return chatClient
                .prompt()
                .system("""
                    You are a sentiment analysis system.

                    Analyze the journal entry.

                    Return ONLY one of:
                    HAPPY
                    SAD
                    ANGRY
                    ANXIOUS

                    Do not provide an explanation.
                    """)
                .user(journalText)
                .call()
                .content();
    }
}
