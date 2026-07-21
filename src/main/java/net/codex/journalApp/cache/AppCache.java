package net.codex.journalApp.cache;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppCache {
    private Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = null;
    }
}
