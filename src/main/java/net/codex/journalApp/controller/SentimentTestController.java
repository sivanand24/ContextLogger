package net.codex.journalApp.controller;


import net.codex.journalApp.model.SentimentData;
import net.codex.journalApp.service.SentimentProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentiment")
public class SentimentTestController {
     @Autowired
     private SentimentProducerService sentimentProducerService;

     @PostMapping("/test")
    public String sendTestSentiment(@RequestParam String email, @RequestParam String sentiment){
         SentimentData data = SentimentData.builder().email(email).sentiment(sentiment).build();

         sentimentProducerService.sendSentiment(data);

         return "Sentiment sent successfully";
     }
}
