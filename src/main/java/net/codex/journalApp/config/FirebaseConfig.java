package net.codex.journalApp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    public void initialize() throws IOException{
        FileInputStream serviceAccount =
                new FileInputStream("C:/Users/Sivanand/firebase/contextlogger-firebase-adminsdk-fbsvc-7f128d4463.json");

        FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }

        System.out.println("Firebase initialized successfully");
    }

}
