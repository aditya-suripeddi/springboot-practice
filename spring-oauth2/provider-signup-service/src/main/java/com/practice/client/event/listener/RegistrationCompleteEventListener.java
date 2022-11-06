package com.practice.client.event.listener;


import com.practice.client.entity.User;
import com.practice.client.event.RegistrationCompleteEvent;
import com.practice.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create and persist random token against the registered user
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationToken(token, user);

        // create url verifyRegistration endpoint
        // and random token in query param
        String url = event.getApplicationUrl()
                    + "/verifyRegistration?token="
                    + token;

        // send mail to user with this url
        // sendVerificationMail();  // not implemented

        log.info("Click the link to verify account: {}", url);
    }

}
