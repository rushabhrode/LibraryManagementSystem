package com.example.library.eventListner;

import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.example.library.model.User;

@Component
public class UserModelListener implements ApplicationListener<BeforeConvertEvent<User>> {

    @Override
    public void onApplicationEvent(BeforeConvertEvent<User> event) {
        User user = event.getSource();
        user.normalizeFields();
    }
}