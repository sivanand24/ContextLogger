package net.codex.journalApp.service;

import net.codex.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSchedulersTest {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void testFetchUsersAndSendSaMail(){
        userScheduler.fetchUsersAndSendSaMail();
    }
}
